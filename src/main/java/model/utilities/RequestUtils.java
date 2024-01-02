package model.utilities;

import java.net.MalformedURLException;
import java.net.URL;

public class RequestUtils {
  public static boolean isValidURL(String url) {
    try {
      new URL(url);
      return true;
    }
    catch (MalformedURLException e) {
      return false;
    }
  }
}
