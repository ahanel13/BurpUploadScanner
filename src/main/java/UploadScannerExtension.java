import burp.api.montoya.BurpExtension;
import burp.api.montoya.MontoyaApi;
import controller.UploadScannerController;
import view.UploadScannerPanel;

import java.io.IOException;

@SuppressWarnings("unused")
public class UploadScannerExtension implements BurpExtension {
  MontoyaApi              api;
  UploadScannerPanel      view;
  UploadScannerController controller;

  @Override
  public void initialize(MontoyaApi montoyaApi) {
    try {
      log("Initializing Extension...", false);

      api        = montoyaApi;
      view       = new UploadScannerPanel();
      controller = getExtensionController();

      api.extension().setName("Upload Scanner");

      log("Extension Initialized.", false);
    }
    catch (Exception e) {
      log(e.toString(), true);
    }
  }

  private UploadScannerController getExtensionController() throws Exception {
    try {
      return new UploadScannerController(api, view);
    }
    catch (IOException e) {
      log(e.toString(), true);
      log("Unloading Extension", false);
      api.extension().unload();
    }
    return null;
  }

  private void log(String output, boolean error) {
    if (api != null) {
      if (error) {
        api.logging().logToError(output);
      }
      else {
        api.logging().logToOutput(output);
      }
    }
    else {
      System.out.println(output);
    }
  }
}