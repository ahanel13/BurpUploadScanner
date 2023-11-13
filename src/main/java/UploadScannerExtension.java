import burp.api.montoya.BurpExtension;
import burp.api.montoya.MontoyaApi;
import controller.UploadScannerController;
import view.UploadScannerPanel;

@SuppressWarnings("unused")
public class UploadScannerExtension implements BurpExtension {
  MontoyaApi api;
  UploadScannerPanel view;
  UploadScannerController controller;

  @Override
  public void initialize(MontoyaApi montoyaApi) {
    log("Initializing Extension...", false);

    api         = montoyaApi;
    view        = new UploadScannerPanel();
    controller  = getExtensionController();

    api.extension().setName("Upload Scanner");

    log("Extension Initialized.", false);
  }

  private UploadScannerController getExtensionController() {
    return new UploadScannerController(api, view);
  }

  private void log(String output, boolean error){
    if(api != null){
      if(error){
        api.logging().logToError(output);
      } else {
        api.logging().logToOutput(output);
      }
    } else {
      System.out.println(output);
    }
  }
}