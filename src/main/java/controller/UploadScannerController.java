package controller;

import burp.api.montoya.MontoyaApi;
import burp.api.montoya.persistence.PersistedObject;
import controller.tabControllers.ScanConfigController;
import model.ScanConfigModel;
import view.UploadScannerPanel;
import view.tabs.AboutTab;
import view.templates.IntellijBaseConfig;

public class UploadScannerController {

    public UploadScannerController(MontoyaApi montoyaApi, UploadScannerPanel scannerPanel) throws Exception {
        api                   = montoyaApi;
        view                  = scannerPanel;
        persistedObject       = api.persistence().extensionData();

        api.logging().logToOutput("Generating UI");
        addTabs();
        registerView();
        api.logging().logToOutput("UI Generated");
    }

    private final UploadScannerPanel       view;
    private final MontoyaApi               api;
    private final PersistedObject          persistedObject;
    private       ScanConfigModel          defaultScanOptions;
    private       ScanConfigController     defaultConfigController;

    private void registerView() {
        api.userInterface().registerSuiteTab("Upload Scanner", view);
    }

    private void addTabs() throws Exception {
        // Order matters
        addAboutTab();
        addGlobalConfigurationTab();
        addDoneUploadsTab();
    }

    private void addAboutTab() throws Exception {
        AboutTab aboutTab = new AboutTab();
        view.addTab(aboutTab.getTabName(), aboutTab);
    }

    private void addDoneUploadsTab() {
        // This tab will keep track of uploads and downloads
    }

    private void addGlobalConfigurationTab() throws Exception {
        defaultScanOptions      = new ScanConfigModel(api, persistedObject);
        IntellijBaseConfig defaultScanOptionsView = new IntellijBaseConfig();
        defaultConfigController = new ScanConfigController(defaultScanOptions, defaultScanOptionsView);
        view.addTab(defaultConfigController.getTabName(), defaultConfigController.getTab());
    }
}
