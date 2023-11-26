package model.utilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class ResourceLoader {

  public static String loadResource(String resourceName) throws Exception {
    InputStream inputStream  = null;
    String      fileContents = "";

    try {
      // load given resource as inputStream
      inputStream = ResourceLoader.class.getClassLoader().getResourceAsStream(resourceName);

      if (inputStream != null) {
        // Read the content of the resource
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        fileContents = reader.lines().collect(Collectors.joining(System.lineSeparator()));

      } else {
        throw new FileNotFoundException("Resource not found: " + resourceName);
      }
    } catch (Exception e) {
      throw new Exception("Failed to load resource \"" + resourceName + "\": " + e);
    }

    inputStream.close();
    return fileContents;
  }
}
