/**
 * <h1>ReportGenerator.java</h1> <p> This program is free software: you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later version; or, at your
 * choice, under the terms of the Mozilla Public License, v. 2.0. SPDX GPL-3.0+ or MPL-2.0+. </p>
 * <p> This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License and the Mozilla Public License for more details. </p> <p> You should
 * have received a copy of the GNU General Public License and the Mozilla Public License along with
 * this program. If not, see <a href="http://www.gnu.org/licenses/">http://www.gnu.org/licenses/</a>
 * and at <a href="http://mozilla.org/MPL/2.0">http://mozilla.org/MPL/2.0</a> . </p> <p> NB: for the
 * (c) statement, include Easy Innova SL or other company/Person contributing the code. </p> <p> (c)
 * 2015 Easy Innova, SL </p>
 *
 * @author Victor Munoz Sola
 * @version 1.0
 * @since 16/10/2015
 */


package dpfmanager.shell.modules.report.util;

import dpfmanager.conformancechecker.tiff.implementation_checker.rules.RuleResult;
import dpfmanager.shell.core.config.BasicConfig;
import dpfmanager.shell.modules.messages.messages.ExceptionMessage;
import dpfmanager.shell.modules.report.core.GlobalReport;
import dpfmanager.shell.modules.report.core.IndividualReport;
import dpfmanager.shell.modules.report.core.ReportGeneric;

import com.easyinnova.tiff.model.TiffDocument;
import com.easyinnova.tiff.model.types.IFD;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDJpeg;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDXObjectImage;

import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

/**
 * The class Report PDF
 */
public class ReportPDF extends ReportGeneric {

  /**
   * The Init posy.
   */
  static int init_posy = 800;

  /**
   * Parse an individual report to PDF.
   *
   * @param outputfile the outputfile
   * @param ir         the individual report.
   */
  public void parseIndividual(String outputfile, IndividualReport ir) {
    try {
      int epErr = ir.getNEpErr(), epWar = ir.getNEpWar();
      int blErr = ir.getNBlErr(), blWar = ir.getNBlWar();
      int it0Err = ir.getNItErr(0), it0War = ir.getNItWar(0);
      int it1Err = ir.getNItErr(1), it2War = ir.getNItWar(1);
      int it2Err = ir.getNItErr(2), it1War = ir.getNItWar(2);
      List<RuleResult> pcValidation = ir.getPcValidation();
      int pcErr = ir.getPCErrors().size(), pcWar = ir.getPCWarnings().size();

      PDFParams pdfParams = new PDFParams();
      pdfParams.init(PDPage.PAGE_SIZE_A4);

      PDFont font = PDType1Font.HELVETICA_BOLD;
      int pos_x = 200;
      int font_size = 18;
      pdfParams.y = 700;

      // Logo
      PDXObjectImage ximage;
      float scale = 3;
      synchronized (this) {
        InputStream inputStream = getFileStreamFromResources("images/logo.jpg");
        ximage = new PDJpeg(pdfParams.getDocument(), inputStream);
        pdfParams.getContentStream().drawXObject(ximage, pos_x, pdfParams.y, 645 / scale, 300 / scale);
      }

      // Report Title
      pdfParams.y -= 30;
      pdfParams = writeText(pdfParams, "INDIVIDUAL REPORT", pos_x, font, font_size);

      // Image
      pos_x = 50;
      int image_height = 130;
      int image_width = 200;
      pdfParams.y -= (image_height + 30);
      int image_pos_y = pdfParams.y;
      String imgPath = outputfile + "img.jpg";
      int ids = 0;
      while (new File(imgPath).exists()) imgPath = outputfile + "img" + ids++ + ".jpg";
      boolean check = tiff2Jpg(ir.getFilePath(), imgPath);
      BufferedImage bimg;
      if (!check) {
        bimg = ImageIO.read(getFileStreamFromResources("html/img/noise.jpg"));
      } else {
        bimg = ImageIO.read(new File(imgPath));
      }
      image_width = image_height * bimg.getWidth() / bimg.getHeight();
      if (image_width > 200) {
        image_width = 200;
        image_height = image_width * bimg.getHeight() / bimg.getWidth();
      }
      ximage = new PDJpeg(pdfParams.getDocument(), bimg);
      pdfParams.getContentStream().drawXObject(ximage, pos_x, pdfParams.y, image_width, image_height);
      if (check) new File(imgPath).delete();

      // Image name & path
      font_size = 12;
      pdfParams.y += image_height - 12;
      pdfParams = writeText(pdfParams, ir.getFileName(), pos_x + image_width + 10, font, font_size);
      font_size = 11;
      pdfParams.y -= 32;
      pdfParams = writeText(pdfParams, ir.getFilePath(), pos_x + image_width + 10, font, font_size);

      // Image alert
      pdfParams.y -= 30;
      font_size = 9;
      if (blErr + epErr + it0Err + it1Err + it2Err + pcErr > 0) {
        pdfParams = writeText(pdfParams, "This file does NOT conform to conformance checker", pos_x + image_width + 10, font, font_size, Color.red);
      } else if (blWar + epWar + it0War + it1War + it2War + pcWar > 0) {
        pdfParams = writeText(pdfParams, "This file conforms to conformance checker, BUT it has some warnings", pos_x + image_width + 1, font, font_size, Color.orange);
      } else {
        pdfParams = writeText(pdfParams, "This file conforms to conformance checker", pos_x + image_width + 1, font, font_size, Color.green);
      }

      // Summary table
      pdfParams.y -= 20;
      font_size = 8;
      pdfParams = writeText(pdfParams, "Errors", pos_x + image_width + 140, font, font_size);
      pdfParams = writeText(pdfParams, "Warnings", pos_x + image_width + 180, font, font_size);
      String dif = "";

      if (ir.hasBlValidation()) {
        pdfParams.y -= 20;
        pdfParams.getContentStream().drawLine(pos_x + image_width + 10, pdfParams.y + 15, pos_x + image_width + 230, pdfParams.y + 15);
        pdfParams = writeText(pdfParams, "Baseline", pos_x + image_width + 10, font, font_size);
        dif = ir.getCompareReport() != null ? getDif(ir.getCompareReport().getNBlErr(), blErr) : "";
        pdfParams = writeText(pdfParams, blErr + dif, pos_x + image_width + 150, font, font_size, blErr > 0 ? Color.red : Color.black);
        dif = ir.getCompareReport() != null ? getDif(ir.getCompareReport().getNBlWar(), blWar) : "";
        pdfParams = writeText(pdfParams, blWar + dif, pos_x + image_width + 200, font, font_size, blWar > 0 ? Color.orange : Color.black);
      }
      if (ir.hasEpValidation()) {
        pdfParams.y -= 20;
        pdfParams = writeText(pdfParams, "Tiff/Ep", pos_x + image_width + 10, font, font_size);
        dif = ir.getCompareReport() != null ? getDif(ir.getCompareReport().getNEpErr(), epErr) : "";
        pdfParams = writeText(pdfParams, epErr + dif, pos_x + image_width + 150, font, font_size, epErr > 0 ? Color.red : Color.black);
        dif = ir.getCompareReport() != null ? getDif(ir.getCompareReport().getNEpWar(), epWar) : "";
        pdfParams = writeText(pdfParams, epWar + dif, pos_x + image_width + 200, font, font_size, epWar > 0 ? Color.orange : Color.black);
      }
      if (ir.hasItValidation(0)) {
        pdfParams.y -= 20;
        pdfParams = writeText(pdfParams, "Tiff/It", pos_x + image_width + 10, font, font_size);
        dif = ir.getCompareReport() != null ? getDif(ir.getCompareReport().getNItErr(0), it0Err) : "";
        pdfParams = writeText(pdfParams, it0Err + dif, pos_x + image_width + 150, font, font_size, it0Err > 0 ? Color.red : Color.black);
        dif = ir.getCompareReport() != null ? getDif(ir.getCompareReport().getNItWar(0), it0War) : "";
        pdfParams = writeText(pdfParams, it0War + dif, pos_x + image_width + 200, font, font_size, it0War > 0 ? Color.orange : Color.black);
      }
      if (ir.hasItValidation(0)) {
        pdfParams.y -= 20;
        pdfParams = writeText(pdfParams, "Tiff/It-P1", pos_x + image_width + 10, font, font_size);
        dif = ir.getCompareReport() != null ? getDif(ir.getCompareReport().getNItErr(1), it1Err) : "";
        pdfParams = writeText(pdfParams, it1Err + dif, pos_x + image_width + 150, font, font_size, it1Err > 0 ? Color.red : Color.black);
        dif = ir.getCompareReport() != null ? getDif(ir.getCompareReport().getNItWar(1), it1War) : "";
        pdfParams = writeText(pdfParams, it1War + dif, pos_x + image_width + 200, font, font_size, it1War > 0 ? Color.orange : Color.black);
      }
      if (ir.hasItValidation(0)) {
        pdfParams.y -= 20;
        pdfParams = writeText(pdfParams, "Tiff/It-P2", pos_x + image_width + 10, font, font_size);
        dif = ir.getCompareReport() != null ? getDif(ir.getCompareReport().getNItErr(2), it2Err) : "";
        pdfParams = writeText(pdfParams, it2Err + dif, pos_x + image_width + 150, font, font_size, it2Err > 0 ? Color.red : Color.black);
        dif = ir.getCompareReport() != null ? getDif(ir.getCompareReport().getNItWar(2), it2War) : "";
        pdfParams = writeText(pdfParams, it2War + dif, pos_x + image_width + 200, font, font_size, it2War > 0 ? Color.orange : Color.black);
      }
      pdfParams.y -= 20;
      pdfParams = writeText(pdfParams, "Policy checker", pos_x + image_width + 10, font, font_size);
      dif = ir.getCompareReport() != null ? getDif(ir.getCompareReport().getPCErrors().size(), pcErr) : "";
      pdfParams = writeText(pdfParams, pcErr + dif, pos_x + image_width + 150, font, font_size, pcErr > 0 ? Color.red : Color.black);
      dif = ir.getCompareReport() != null ? getDif(ir.getCompareReport().getPCWarnings().size(), pcWar) : "";
      pdfParams = writeText(pdfParams, pcWar + dif, pos_x + image_width + 200, font, font_size, pcWar > 0 ? Color.orange : Color.black);

      // Tags
      font_size = 10;
      if (pdfParams.y > image_pos_y) pdfParams.y = image_pos_y;
      pdfParams.y -= 20;
      pdfParams = writeText(pdfParams, "Tags", pos_x, font, font_size);
      font_size = 7;
      pdfParams.y -= 20;
      pdfParams = writeText(pdfParams, "IFD", pos_x, font, font_size);
      pdfParams = writeText(pdfParams, "Tag ID", pos_x + 40, font, font_size);
      pdfParams = writeText(pdfParams, "Tag Name", pos_x + 80, font, font_size);
      pdfParams = writeText(pdfParams, "Value", pos_x + 200, font, font_size);
      for (ReportTag tag : getTags(ir)) {
        if (tag.expert) continue;
        String sDif = "";
        if (tag.dif < 0) sDif = "(-)";
        else if (tag.dif > 0) sDif = "(+)";
        pdfParams.y -= 18;
        pdfParams = writeText(pdfParams, tag.index + "", pos_x, font, font_size);
        pdfParams = writeText(pdfParams, tag.tv.getId() + sDif, pos_x + 40, font, font_size);
        pdfParams = writeText(pdfParams, tag.tv.getName(), pos_x + 80, font, font_size);
        pdfParams = writeText(pdfParams, tag.tv.getDescriptiveValue(), pos_x + 200, font, font_size);
      }

      // File structure
      font_size = 10;
      pdfParams.y -= 20;
      pdfParams = writeText(pdfParams, "File Structure", pos_x, font, font_size);
      TiffDocument td = ir.getTiffModel();
      IFD ifd = td.getFirstIFD();
      font_size = 7;
      while (ifd != null) {
        pdfParams.y -= 20;
        String typ = " - Main image";
        if (ifd.hasSubIFD() && ifd.getImageSize() < ifd.getsubIFD().getImageSize())
          typ = " - Thumbnail";
        ximage = new PDJpeg(pdfParams.getDocument(), getFileStreamFromResources("images/doc.jpg"));
        pdfParams.getContentStream().drawXObject(ximage, pos_x, pdfParams.y, 5, 7);
        pdfParams = writeText(pdfParams, ifd.toString() + typ, pos_x + 7, font, font_size);
        if (ifd.getsubIFD() != null) {
          pdfParams.y -= 18;
          if (ifd.getImageSize() < ifd.getsubIFD().getImageSize()) typ = " - Main image";
          else typ = " - Thumbnail";
          pdfParams.getContentStream().drawXObject(ximage, pos_x + 15, pdfParams.y, 5, 7);
          pdfParams = writeText(pdfParams, "SubIFD" + typ, pos_x + 15 + 7, font, font_size);
        }
        if (ifd.containsTagId(34665)) {
          pdfParams.y -= 18;
          pdfParams.getContentStream().drawXObject(ximage, pos_x + 15, pdfParams.y, 5, 7);
          pdfParams = writeText(pdfParams, "EXIF", pos_x + 15 + 7, font, font_size);
        }
        if (ifd.containsTagId(700)) {
          pdfParams.y -= 18;
          pdfParams.getContentStream().drawXObject(ximage, pos_x + 15, pdfParams.y, 5, 7);
          pdfParams = writeText(pdfParams, "XMP", pos_x + 15 + 7, font, font_size);
        }
        if (ifd.containsTagId(33723)) {
          pdfParams.y -= 18;
          pdfParams.getContentStream().drawXObject(ximage, pos_x + 15, pdfParams.y, 5, 7);
          pdfParams = writeText(pdfParams, "IPTC", pos_x + 15 + 7, font, font_size);
        }
        ifd = ifd.getNextIFD();
      }

      // Conformance
      if (ir.checkBL) {
        pdfParams = writeErrorsWarnings(pdfParams, font, ir.getBaselineErrors(), ir.getBaselineWarnings(), pos_x, "Baseline");
      }
      if (ir.checkEP) {
        pdfParams = writeErrorsWarnings(pdfParams, font, ir.getEPErrors(), ir.getEPWarnings(), pos_x, "Tiff/EP");
      }
      if (ir.checkIT0) {
        pdfParams = writeErrorsWarnings(pdfParams, font, ir.getITErrors(0), ir.getITWarnings(0), pos_x, "Tiff/IT");
      }
      if (ir.checkIT1) {
        pdfParams = writeErrorsWarnings(pdfParams, font, ir.getITErrors(1), ir.getITWarnings(1), pos_x, "Tiff/IT-1");
      }
      if (ir.checkIT2) {
        pdfParams = writeErrorsWarnings(pdfParams, font, ir.getITErrors(2), ir.getITWarnings(2), pos_x, "Tiff/IT-2");
      }
      if (ir.checkPC) {
        pdfParams = writeErrorsWarnings2(pdfParams, font, ir.getPCErrors(), ir.getPCWarnings(), pos_x, "Policy Checker");
      }

      pdfParams.getContentStream().close();

      pdfParams.getDocument().save(outputfile);
      pdfParams.getDocument().close();

      ir.setPDFDocument(outputfile);
    } catch (Exception tfe) {
      context.send(BasicConfig.MODULE_MESSAGE, new ExceptionMessage("Exception in ReportPDF", tfe));
    }
  }

  /**
   * Parse a global report to PDF format.
   *
   * @param pdffile the file name.
   * @param gr      the global report.
   */
  public void parseGlobal(String pdffile, GlobalReport gr) {
    try {
      PDFParams pdfParams = new PDFParams();
      pdfParams.init(PDPage.PAGE_SIZE_A4);

      PDFont font = PDType1Font.HELVETICA_BOLD;
      int pos_x = 200;
      pdfParams.y = 700;
      int font_size = 18;
      // Logo
      PDXObjectImage ximage = new PDJpeg(pdfParams.getDocument(), getFileStreamFromResources("images/logo.jpg"));
      float scale = 3;
      pdfParams.getContentStream().drawXObject(ximage, pos_x, pdfParams.y, 645 / scale, 300 / scale);

      // Report Title
      pdfParams.y -= 30;
      pdfParams = writeText(pdfParams, "MULTIPLE REPORT", pos_x, font, font_size);
      pdfParams.y -= 30;
      font_size = 15;
      pdfParams = writeText(pdfParams, "Processed files: " + gr.getIndividualReports().size(), pos_x, font, font_size, Color.cyan);

      // Summary table
      pos_x = 100;
      pdfParams.y -= 30;
      font_size = 8;
      Color col = gr.getReportsPc() == gr.getReportsCount() ? Color.green : Color.red;
      pdfParams = writeText(pdfParams, gr.getReportsPc() + "", pos_x, font, font_size, col);
      pdfParams = writeText(pdfParams, "conforms to Policy checker", pos_x + 30, font, font_size, col);
      if (gr.getHasBl()) {
        pdfParams.y -= 15;
        col = gr.getReportsBl() == gr.getReportsCount() ? Color.green : Color.red;
        pdfParams = writeText(pdfParams, gr.getReportsBl() + "", pos_x, font, font_size, col);
        pdfParams = writeText(pdfParams, "conforms to Baseline Profile", pos_x + 30, font, font_size, col);
      }
      if (gr.getHasEp()) {
        pdfParams.y -= 15;
        col = gr.getReportsEp() == gr.getReportsCount() ? Color.green : Color.red;
        pdfParams = writeText(pdfParams, gr.getReportsEp() + "", pos_x, font, font_size, col);
        pdfParams = writeText(pdfParams, "conforms to Tiff/EP Profile", pos_x + 30, font, font_size, col);
      }
      if (gr.getHasIt0()) {
        pdfParams.y -= 15;
        col = gr.getReportsIt0() == gr.getReportsCount() ? Color.green : Color.red;
        pdfParams = writeText(pdfParams, gr.getReportsIt0() + "", pos_x, font, font_size, col);
        pdfParams = writeText(pdfParams, "conforms to Tiff/IT Profile", pos_x + 30, font, font_size, col);
      }
      if (gr.getHasIt1()) {
        pdfParams.y -= 15;
        col = gr.getReportsIt1() == gr.getReportsCount() ? Color.green : Color.red;
        pdfParams = writeText(pdfParams, gr.getReportsIt2() + "", pos_x, font, font_size, col);
        pdfParams = writeText(pdfParams, "conforms to Tiff/IT P1 Profile", pos_x + 30, font, font_size, col);
      }
      if (gr.getHasIt2()) {
        pdfParams.y -= 15;
        col = gr.getReportsIt2() == gr.getReportsCount() ? Color.green : Color.red;
        pdfParams = writeText(pdfParams, gr.getReportsIt2() + "", pos_x, font, font_size, col);
        pdfParams = writeText(pdfParams, "conforms to Tiff/IT P2 Profile", pos_x + 30, font, font_size, col);
      }

      // Pie chart
      pdfParams.y += 10;
      if (pdfParams.y > 565) pdfParams.y = 565;
      pos_x += 200;
      int graph_size = 40;
      BufferedImage image = new BufferedImage(graph_size * 10, graph_size * 10, BufferedImage.TYPE_INT_ARGB);
      Graphics2D g2d = image.createGraphics();
      Double doub = (double) gr.getReportsOk() / gr.getReportsCount();
      double extent = 360d * doub;
      g2d.setColor(Color.green);
      g2d.fill(new Arc2D.Double(0, 0, graph_size * 10, graph_size * 10, 90, 360, Arc2D.PIE));
      g2d.setColor(Color.red);
      g2d.fill(new Arc2D.Double(0, 0, graph_size * 10, graph_size * 10, 90, 360 - extent, Arc2D.PIE));
      ximage = new PDJpeg(pdfParams.getDocument(), image);
      pdfParams.getContentStream().drawXObject(ximage, pos_x, pdfParams.y, graph_size, graph_size);
      pdfParams.y += graph_size - 10;
      font_size = 7;
      pdfParams = writeText(pdfParams, gr.getReportsOk() + " passed", pos_x + 50, font, font_size, Color.green);
      pdfParams.y -= 10;
      pdfParams = writeText(pdfParams, gr.getReportsKo() + " failed", pos_x + 50, font, font_size, Color.red);
      pdfParams.y -= 10;
      pdfParams = writeText(pdfParams, "Global score " + (doub * 100) + "%", pos_x + 50, font, font_size, Color.black);

      // Individual Tiff images list
      pos_x = 100;
      pdfParams.y -= 50;
      for (IndividualReport ir : gr.getIndividualReports()) {
        int image_height = 65;
        int image_width = 100;

        // Draw image
        String imgPath = pdffile + "img.jpg";
        int ids = 0;
        while (new File(imgPath).exists()) imgPath = pdffile + "img" + ids++ + ".jpg";
        boolean check = tiff2Jpg(ir.getFilePath(), imgPath);
        BufferedImage bimg;
        if (!check) {
          bimg = ImageIO.read(getFileStreamFromResources("html/img/noise.jpg"));
        } else {
          bimg = ImageIO.read(new File(imgPath));
        }
        image_width = image_height * bimg.getWidth() / bimg.getHeight();
        if (image_width > 100) {
          image_width = 100;
          image_height = image_width * bimg.getHeight() / bimg.getWidth();
        }

        // Check if we need new page before draw image
        int maxHeight = getMaxHeight(ir, image_height);
        if (newPageNeeded(pdfParams.y - maxHeight)) {
          pdfParams.setContentStream(newPage(pdfParams.getContentStream(), pdfParams.getDocument()));
          pdfParams.y = init_posy;
        }

        int initialy = pdfParams.y;
        int initialx = 100;

        pdfParams.y -= maxHeight;
        int maxy = pdfParams.y;

        ximage = new PDJpeg(pdfParams.getDocument(), bimg);
        pdfParams.getContentStream().drawXObject(ximage, pos_x, pdfParams.y, image_width, image_height);
        if (check) new File(imgPath).delete();

        // Values
        image_width = initialx;
        pdfParams.y = initialy;
        pdfParams = writeText(pdfParams, ir.getFileName(), pos_x + image_width + 10, font, font_size, Color.gray);
        font_size = 6;
        pdfParams.y -= 10;
        pdfParams = writeText(pdfParams, "Conformance Checker", pos_x + image_width + 10, font, font_size, Color.black);
        pdfParams.getContentStream().drawLine(pos_x + image_width + 10, pdfParams.y - 5, image_width + 150, pdfParams.y - 5);
        pdfParams.y -= 2;

        if (ir.hasBlValidation()) {
          pdfParams.y -= 10;
          pdfParams = writeText(pdfParams, "Baseline", pos_x + image_width + 10, font, font_size, Color.black);
          pdfParams = writeText(pdfParams, ir.getBaselineErrors().size() + " errors", pos_x + image_width + 70, font, font_size, ir.getBaselineErrors().size() > 0 ? Color.red : Color.black);
          pdfParams = writeText(pdfParams, ir.getBaselineWarnings().size() + " warnings", pos_x + image_width + 120, font, font_size, ir.getBaselineWarnings().size() > 0 ? Color.red : Color.black);
        }
        if (ir.hasEpValidation()) {
          pdfParams.y -= 10;
          pdfParams = writeText(pdfParams, "Tiff/EP", pos_x + image_width + 10, font, font_size, Color.black);
          pdfParams = writeText(pdfParams, ir.getEPErrors().size() + " errors", pos_x + image_width + 70, font, font_size, ir.getEPErrors().size() > 0 ? Color.red : Color.black);
          pdfParams = writeText(pdfParams, ir.getEPWarnings().size() + " warnings", pos_x + image_width + 120, font, font_size, ir.getEPWarnings().size() > 0 ? Color.red : Color.black);
        }
        if (ir.hasItValidation(0)) {
          pdfParams.y -= 10;
          pdfParams = writeText(pdfParams, "Tiff/IT", pos_x + image_width + 10, font, font_size, Color.black);
          pdfParams = writeText(pdfParams, ir.getITErrors(0).size() + " errors", pos_x + image_width + 70, font, font_size, ir.getITErrors(0).size() > 0 ? Color.red : Color.black);
          pdfParams = writeText(pdfParams, ir.getITWarnings(0).size() + " warnings", pos_x + image_width + 120, font, font_size, ir.getITWarnings(0).size() > 0 ? Color.red : Color.black);
        }
        if (ir.hasItValidation(1)) {
          pdfParams.y -= 10;
          pdfParams = writeText(pdfParams, "Tiff/IT-1", pos_x + image_width + 10, font, font_size, Color.black);
          pdfParams = writeText(pdfParams, ir.getITErrors(1).size() + " errors", pos_x + image_width + 70, font, font_size, ir.getITErrors(1).size() > 0 ? Color.red : Color.black);
          pdfParams = writeText(pdfParams, ir.getITWarnings(1).size() + " warnings", pos_x + image_width + 120, font, font_size, ir.getITWarnings(1).size() > 0 ? Color.red : Color.black);
        }
        if (ir.hasItValidation(2)) {
          pdfParams.y -= 10;
          pdfParams = writeText(pdfParams, "Tiff/IT-2", pos_x + image_width + 10, font, font_size, Color.black);
          pdfParams = writeText(pdfParams, ir.getITErrors(2).size() + " errors", pos_x + image_width + 70, font, font_size, ir.getITErrors(2).size() > 0 ? Color.red : Color.black);
          pdfParams = writeText(pdfParams, ir.getITWarnings(2).size() + " warnings", pos_x + image_width + 120, font, font_size, ir.getITWarnings(2).size() > 0 ? Color.red : Color.black);
        }
        if (ir.checkPC) {
          pdfParams.y -= 10;
          pdfParams = writeText(pdfParams, "Policy checker", pos_x + image_width + 10, font, font_size, Color.black);
          pdfParams = writeText(pdfParams, ir.getPCErrors().size() + " errors", pos_x + image_width + 70, font, font_size, ir.getPCErrors().size() > 0 ? Color.red : Color.black);
          pdfParams = writeText(pdfParams, ir.getPCWarnings().size() + " warnings", pos_x + image_width + 120, font, font_size, ir.getPCWarnings().size() > 0 ? Color.red : Color.black);
        }
        if (pdfParams.y < maxy) maxy = pdfParams.y;

        // Chart
        pdfParams.y = initialy;
        pdfParams.y -= 10;
        pdfParams.y -= 10;
        graph_size = 25;
        image = new BufferedImage(graph_size * 10, graph_size * 10, BufferedImage.TYPE_INT_ARGB);
        g2d = image.createGraphics();
        doub = (double) ir.calculatePercent();
        extent = 360d * doub / 100.0;
        g2d.setColor(Color.gray);
        g2d.fill(new Arc2D.Double(0, 0, graph_size * 10, graph_size * 10, 90, 360, Arc2D.PIE));
        g2d.setColor(Color.red);
        g2d.fill(new Arc2D.Double(0, 0, graph_size * 10, graph_size * 10, 90, 360 - extent, Arc2D.PIE));
        ximage = new PDJpeg(pdfParams.getDocument(), image);
        pdfParams.getContentStream().drawXObject(ximage, pos_x + image_width + 180, pdfParams.y - graph_size, graph_size, graph_size);
        pdfParams.y += graph_size - 10;
        if (doub < 100) {
          pdfParams.y = pdfParams.y - 10 - graph_size / 2;
          pdfParams = writeText(pdfParams, "Failed", pos_x + image_width + 180 + graph_size + 10, font, font_size, Color.red);
        }
        pdfParams.y = pdfParams.y - 10 - graph_size / 2;
        pdfParams = writeText(pdfParams, "Score " + doub + "%", pos_x + image_width + 180 + graph_size + 10, font, font_size, Color.gray);
        if (pdfParams.y < maxy) maxy = pdfParams.y;

        pdfParams.y = maxy - 20;
      }

      // Full individual reports
      ArrayList<PDDocument> toClose = new ArrayList<PDDocument>();
      for (IndividualReport ir : gr.getIndividualReports()) {
        if (!ir.containsData()) continue;
        PDDocument doc = PDDocument.load(ir.getPDFDocument());
        List<PDPage> l = doc.getDocumentCatalog().getAllPages();
        for (PDPage pag : l) {
          pdfParams.getDocument().addPage(pag);
        }
        toClose.add(doc);
      }

      pdfParams.getContentStream().close();

      pdfParams.getDocument().save(pdffile);
      pdfParams.getDocument().close();

      for (PDDocument doc : toClose) {
        doc.close();
      }
    } catch (Exception tfe) {
      context.send(BasicConfig.MODULE_MESSAGE, new ExceptionMessage("Exception in ReportPDF", tfe));
    }
  }

  private int getMaxHeight(IndividualReport ir, int image_height) {
    int height = 15;
    if (ir.hasBlValidation()) {
      height += 10;
    }
    if (ir.hasEpValidation()) {
      height += 10;
    }
    if (ir.hasItValidation(0)) {
      height += 10;
    }
    if (ir.hasItValidation(1)) {
      height += 10;
    }
    if (ir.hasItValidation(2)) {
      height += 10;
    }
    if (ir.checkPC) {
      height += 10;
    }
    if (image_height > height) {
      height = image_height;
    }
    return height;
  }

  /**
   * New page needed boolean.
   *
   * @param pos_y the pos y
   * @return the boolean
   */
  boolean newPageNeeded(int pos_y) {
    return pos_y < 100;
  }

  /**
   * Write errors warnings int.
   *
   * @param pdfParams the pdf params
   * @param font      the font
   * @param errors    the errors
   * @param warnings  the warnings
   * @param pos_x     the pos x
   * @param type      the type
   * @return the int
   * @throws Exception the exception
   */
  private PDFParams writeErrorsWarnings(PDFParams pdfParams, PDFont font, List<RuleResult> errors, List<RuleResult> warnings, int pos_x, String type) throws Exception {
    int total = 0;
    int font_size = 10;
    pdfParams.y -= 20;

    pdfParams = writeText(pdfParams, type + " Conformance", pos_x, font, font_size);
    font_size = 8;
    if ((errors != null && errors.size() > 0) || (warnings != null && warnings.size() > 0)) {
      pdfParams.y -= 20;
      pdfParams = writeText(pdfParams, "Type", pos_x, font, font_size);
      pdfParams = writeText(pdfParams, "Location", pos_x + 50, font, font_size);
      pdfParams = writeText(pdfParams, "Description", pos_x + 100, font, font_size);
    }
    if (errors != null) {
      for (RuleResult val : errors) {
        pdfParams.y -= 20;
        pdfParams = writeText(pdfParams, "Error", pos_x, font, font_size, Color.red);
        pdfParams = writeText(pdfParams, val.getLocation(), pos_x + 50, font, font_size);
        pdfParams = writeText(pdfParams, val.getDescription(), pos_x + 100, font, font_size);
        total++;
      }
    }
    if (warnings != null) {
      for (RuleResult val : warnings) {
        pdfParams.y -= 20;
        pdfParams = writeText(pdfParams, "Warning", pos_x, font, font_size, Color.orange);
        pdfParams = writeText(pdfParams, val.getLocation(), pos_x + 50, font, font_size);
        pdfParams = writeText(pdfParams, val.getDescription(), pos_x + 100, font, font_size);
        total++;
      }
    }
    if (total == 0) {
      pdfParams.y -= 20;
      PDXObjectImage ximage = new PDJpeg(pdfParams.getDocument(), getFileStreamFromResources("images/ok.jpg"));
      pdfParams.getContentStream().drawXObject(ximage, pos_x + 8, pdfParams.y, 5, 5);
      pdfParams = writeText(pdfParams, "This file conforms to " + type, pos_x + 15, font, font_size, Color.green);
    }
    return pdfParams;
  }

  private PDFParams writeErrorsWarnings2(PDFParams pdfParams, PDFont font, List<RuleResult> errors, List<RuleResult> warnings, int pos_x, String type) throws Exception {
    int total = 0;
    int font_size = 10;
    pdfParams.y -= 20;
    pdfParams = writeText(pdfParams, type + " Conformance", pos_x, font, font_size);

    font_size = 8;
    if ((errors != null && errors.size() > 0) || (warnings != null && warnings.size() > 0)) {
      pdfParams.y -= 20;
      pdfParams = writeText(pdfParams, "Type", pos_x, font, font_size);
      pdfParams = writeText(pdfParams, "Location", pos_x + 50, font, font_size);
      pdfParams = writeText(pdfParams, "Description", pos_x + 100, font, font_size);
    }
    if (errors != null) {
      for (RuleResult val : errors) {
        pdfParams.y -= 20;
        pdfParams = writeText(pdfParams, "Error", pos_x, font, font_size, Color.red);
        pdfParams = writeText(pdfParams, val.getLocation(), pos_x + 50, font, font_size);
        pdfParams = writeText(pdfParams, val.getDescription(), pos_x + 100, font, font_size);
        total++;
      }
    }
    if (warnings != null) {
      for (RuleResult val : warnings) {
        pdfParams.y -= 20;
        pdfParams = writeText(pdfParams, "Warning", pos_x, font, font_size, Color.orange);
        pdfParams = writeText(pdfParams, val.getLocation(), pos_x + 50, font, font_size);
        pdfParams = writeText(pdfParams, val.getDescription(), pos_x + 100, font, font_size);
        total++;
      }
    }
    if (total == 0) {
      pdfParams.y -= 20;
      PDXObjectImage ximage = new PDJpeg(pdfParams.getDocument(), getFileStreamFromResources("images/ok.jpg"));
      pdfParams.getContentStream().drawXObject(ximage, pos_x + 8, pdfParams.y, 5, 5);
      pdfParams = writeText(pdfParams, "This file conforms to " + type, pos_x + 15, font, font_size, Color.green);
    }
    return pdfParams;
  }

  /**
   * New page pd page content stream.
   *
   * @param contentStream the content stream
   * @param document      the document
   * @return the pd page content stream
   * @throws Exception the exception
   */
  PDPageContentStream newPage(PDPageContentStream contentStream, PDDocument document) throws Exception {
    contentStream.close();
    PDPage page = new PDPage(PDPage.PAGE_SIZE_A4);
    document.addPage(page);
    return new PDPageContentStream(document, page);
  }

  /**
   * Write text.
   *
   * @param pdfParams the content stream and document
   * @param text      the text
   * @param x         the x
   * @param font      the font
   * @param font_size the font size
   * @throws Exception the exception
   */
  private PDFParams writeText(PDFParams pdfParams, String text, int x, PDFont font, int font_size) throws Exception {
    return writeText(pdfParams, text, x, font, font_size, Color.black);
  }

  /**
   * Write text.
   *
   * @param pdfParams the content stream and document
   * @param text      the text
   * @param x         the x
   * @param font      the font
   * @param font_size the font size
   * @param color     the color
   * @throws Exception the exception
   */
  private PDFParams writeText(PDFParams pdfParams, String text, int x, PDFont font, int font_size, Color color) throws Exception {
    PDPageContentStream contentStream = pdfParams.getContentStream();
    try {
      if (newPageNeeded(pdfParams.y)) {
        contentStream = newPage(contentStream, pdfParams.getDocument());
        pdfParams.y = init_posy;
      }


      contentStream.beginText();
      contentStream.setFont(font, font_size);
      contentStream.setNonStrokingColor(color);
      contentStream.moveTextPositionByAmount(x, pdfParams.y);
      contentStream.drawString(text);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      contentStream.endText();
      pdfParams.setContentStream(contentStream);
      return pdfParams;
    }
  }
}
