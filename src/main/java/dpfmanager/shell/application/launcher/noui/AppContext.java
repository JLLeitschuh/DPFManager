package dpfmanager.shell.application.launcher.noui;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Adrià Llorens on 07/04/2016.
 */
public class AppContext {

  private static ApplicationContext ctx;

  public static void loadContext(String path) {
    ctx = new ClassPathXmlApplicationContext(path);
  }

  public static ApplicationContext getApplicationContext() {
    return ctx;
  }
}