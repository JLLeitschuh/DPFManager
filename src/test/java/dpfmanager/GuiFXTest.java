package dpfmanager;

import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;


import org.controlsfx.control.spreadsheet.SpreadsheetView;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.matcher.base.NodeMatchers;
import org.testfx.api.FxToolkit;

/**
 * Created by Adria Llorens on 05/10/2015.
 */
public class GuiFXTest extends FxRobot {

  final static int width = 970;
  final static int height = 950;
  static SpreadsheetView view;

  //Set properties for headless mode
  static {
    System.setProperty("awt.headless", "true");
    System.setProperty("testfx.robot", "glass");
    System.setProperty("testfx.headless", "true");
//    System.setProperty("prism.order", "sw");
//    System.setProperty("prism.text", "t2k");
  }

  private static GuiTest controller;

  @Before
  public void before() throws Exception {
    FxToolkit.registerPrimaryStage();
    FxToolkit.setupStage(stage -> {
      view = new SpreadsheetView();
      StackPane sceneRoot = new StackPane(view);

      stage.setScene(new Scene(sceneRoot, width, height));
      stage.setX(0);
      stage.setY(0);
      stage.show();
    });
    FxToolkit.setupApplication(MainApp.class);
    FxToolkit.showStage();
    Thread.sleep(5000);
  }

  @Test
  public void testFirstScreen() throws Exception {
    // given:

    // when:

    // then:
    FxAssert.verifyThat("#txtBox1", NodeMatchers.hasText("Select a file"));
  }

  @Test
  public void testFX() throws Exception {
    // given:
    clickOn("#butAbout");//.moveTo("New").clickOn("Text Document");
//    write("myTextfile.txt").push(ENTER);

    // when:
//    drag(".file").dropTo("#trash-can");

    // then:
//    verifyThat("#aboutTitle", containsText("About DPF Manager"));
//    Thread.sleep(2000);

//    FxAssert.verifyThat("#txtBox1", NodeMatchers.hasText("Select a file"));
    FxAssert.verifyThat("#aboutTitle", NodeMatchers.hasText("About DPF Manager"));
  }

  @Test
  public void testFail() throws Exception {
    // given:

    // when:
    clickOn("#butReport");//.moveTo("New").clickOn("Text Document");

    // then:
    FxAssert.verifyThat("#txtBox1", NodeMatchers.hasText("Select a file"));
  }

  @After
  public void after() throws Exception {
    FxToolkit.hideStage();
  }

}
