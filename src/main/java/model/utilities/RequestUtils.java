package model.utilities;

import burp.api.montoya.http.message.HttpRequestResponse;

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

public static String getFileHash(HttpRequestResponse request) {
  //todo: implement
  return "fake fileHash";
}

}
