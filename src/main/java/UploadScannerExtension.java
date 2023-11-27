import burp.api.montoya.BurpExtension;
import burp.api.montoya.MontoyaApi;
import controller.UploadScannerController;
import view.UploadScannerMenuContext;
import view.UploadScannerPanel;

import java.io.IOException;

@SuppressWarnings("unused")
public class UploadScannerExtension implements BurpExtension {
    @Override
    public void initialize(MontoyaApi montoyaApi) {
        try {
            log("Initializing Extension...", false);

            api         = montoyaApi;
            view        = new UploadScannerPanel();
            menuContext = new UploadScannerMenuContext();
            controller  = getExtensionController();

            api.extension().setName("Upload Scanner");

            log("Extension Initialized.", false);
        } catch (Exception e) {
            log(e.toString(), true);
        }
    }

    private MontoyaApi               api;
    private UploadScannerPanel       view;
    private UploadScannerController  controller;
    private UploadScannerMenuContext menuContext;

    private UploadScannerController getExtensionController() throws Exception {
        try {
            return new UploadScannerController(api, view, menuContext);
        } catch (IOException e) {
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
            } else {
                api.logging().logToOutput(output);
            }
        } else {
            System.out.println(output);
        }
    }
}