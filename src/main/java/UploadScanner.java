import burp.api.montoya.BurpExtension;
import burp.api.montoya.MontoyaApi;
import view.UploadScannerPanel;

@SuppressWarnings("unused")
public class UploadScanner implements BurpExtension {
  MontoyaApi api;
  UploadScannerPanel ui;
  @Override
  public void initialize(MontoyaApi api) {
      this.api = api;
      log("Initializing Extension...", false);
      api.extension().setName("Upload Scanner");
      createUI();
      log("Extension Initialized.", false);
  }

  private void createUI() {
    try {
      log("Generating UI", false);
      ui = new UploadScannerPanel(api);
      log("UI Generated", false);
    } catch (Exception e) {
      log("Failed to generate UI", true);
      log(e.toString(), true);
    }
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