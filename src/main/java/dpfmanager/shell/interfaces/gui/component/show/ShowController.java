/**
 * <h1>ShowController.java</h1> <p> This program is free software: you can redistribute it
 * and/or modify it under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any later version; or,
 * at your choice, under the terms of the Mozilla Public License, v. 2.0. SPDX GPL-3.0+ or MPL-2.0+.
 * </p> <p> This program is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
 * PURPOSE. See the GNU General Public License and the Mozilla Public License for more details. </p>
 * <p> You should have received a copy of the GNU General Public License and the Mozilla Public
 * License along with this program. If not, see <a href="http://www.gnu.org/licenses/">http://www.gnu.org/licenses/</a>
 * and at <a href="http://mozilla.org/MPL/2.0">http://mozilla.org/MPL/2.0</a> . </p> <p> NB: for the
 * © statement, include Easy Innova SL or other company/Person contributing the code. </p> <p> ©
 * 2015 Easy Innova, SL </p>
 *
 * @author Adria Llorens
 * @version 1.0
 * @since 23/7/2015
 */

package dpfmanager.shell.interfaces.gui.component.show;

import dpfmanager.shell.core.mvc.DpfController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.IOFileFilter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Adria Llorens on 17/03/2016.
 */
public class ShowController extends DpfController<ShowModel, ShowView> {

  Integer count;

  public ShowController(){
    count = 0;
  }

  public void showSingleReport(String type, String path, boolean completedPath) {
    switch (type) {
      case "xml":
        if (!completedPath) path += "summary.xml";
        loadComboBox(path, "xml");
        break;
      case "mets":
        loadComboBox(path, "mets.xml");
        break;
      case "json":
        if (!completedPath) path += "summary.json";
        loadComboBox(path, "json");
        break;
      case "pdf":
        if (!completedPath) path += "report.pdf";
        loadComboBox(path, "pdf");
        break;
      default:
        break;
    }
  }

  public void showSingleReportFX(String type, String path, boolean completedPath) {
    switch (type) {
      case "html":
        if (!completedPath) path += "report.html";
        getView().showWebView(path);
        break;
      case "xml":
        getView().showTextArea();
        break;
      case "mets":
        getView().showTextArea();
        break;
      case "json":
        getView().showTextArea();
        break;
      case "pdf":
        if (!completedPath) path += "report.pdf";
        getView().showPdfView(path);
        break;
    }

    // ComboBox
    if (!type.equals("html")) {
      getView().clearComboBox();
      getView().addComboChilds(getModel().getComboChilds());
      getView().selectComboChild(getModel().getSelectedChild());
      if (count > 1){
        getView().showComboBox();
      }
    }

    getView().hideLoading();
  }

  public void loadComboBox(String filePath, String extension){
    count = 0;
    File file = new File(filePath);
    String selectedName = file.getName().replace("." + extension, "");
    File folder = file;
    if (folder.isFile()){
      folder = folder.getParentFile();
    }

    // ComboBox items
    ObservableList<String> comboChilds = FXCollections.observableArrayList();

    // First add summary
    File summary = new File(folder.getPath() + "/summary." + extension);
    if (extension.equals("pdf")){
      summary = new File(folder.getPath() + "/report." + extension);
    }
    if (summary.exists() && summary.isFile()) {
      getView().setCurrentReportParams(summary.getParent(), extension);
      comboChilds.add(summary.getName().replace("." + extension, ""));
      count++;
    }

    // Add all individuals
    getView().setCurrentReportParams(folder.getPath(), extension);
    IOFileFilter filter = customFilter(extension);
    IOFileFilter filterDir = customFilterDir(folder.getPath());
    Collection<File> childs = FileUtils.listFiles(folder, filter, filterDir);
    for (File child : childs){
      String onlyName = child.getName().replace("."+extension, "");
      comboChilds.add(onlyName);
      count++;
    }

    getModel().setComboChilds(comboChilds);
    getModel().setSelectedChild(selectedName);
  }

  public IOFileFilter customFilter(String extension){
    return new IOFileFilter() {
      @Override
      public boolean accept(File file) {
        if (file.isDirectory()){
          return false;
        }
        if (file.getName().startsWith("summary") || file.getName().startsWith("report")){
          return false;
        }
        if (extension.equals("xml") && file.getName().endsWith(extension) && !file.getName().endsWith(".mets.xml")){
          return true;
        } else if (!extension.equals("xml")) {
          return file.getName().endsWith(extension);
        }
        return false;
      }

      @Override
      public boolean accept(File file, String s) {
        return true;
      }
    };
  }

  public IOFileFilter customFilterDir(String path){
    return new IOFileFilter() {
      @Override
      public boolean accept(File file) {
        return true;
      }

      @Override
      public boolean accept(File file, String s) {
        return file.getPath().equals(path);
      }
    };
  }

  public String getContent(String path) {
    String content = "";
    try {
      byte[] encoded = Files.readAllBytes(Paths.get(path));
      content = new String(encoded);
      if (path.endsWith("json")){
        content = new GsonBuilder().setPrettyPrinting().create().toJson(new JsonParser().parse(content));
      }
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    return content;
  }

}
