import burp.api.montoya.BurpExtension;
import burp.api.montoya.MontoyaApi;
import view.UploadScannerPanel;

@SuppressWarnings("unused")
public class UploadScannerExtension implements BurpExtension {
  MontoyaApi api;
  UploadScannerPanel view;

  @Override
  public void initialize(MontoyaApi montoyaApi) {
    log("Initializing Extension...", false);

    api   = montoyaApi;
    view  = createUI();
    api.extension().setName("Upload Scanner");

    log("Extension Initialized.", false);
  }

  private UploadScannerPanel createUI() {
    UploadScannerPanel temporaryPanel = null;

    try {
      log("Generating UI", false);
      temporaryPanel = new UploadScannerPanel(api);
      log("UI Generated", false);
    }
    catch (Exception e) {
      log("Failed to generate UI", true);
      log(e.toString(), true);
    }

    return temporaryPanel;
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