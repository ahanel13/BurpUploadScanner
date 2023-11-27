package controller;

import burp.api.montoya.MontoyaApi;
import controller.tabControllers.GlobalConfigController;
import model.ConfigModel;
import view.UploadScannerPanel;
import view.tabs.AboutTab;

public class UploadScannerController {

    public UploadScannerController(MontoyaApi montoyaApi, UploadScannerPanel scannerPanel) throws Exception {
        api  = montoyaApi;
        view = scannerPanel;

        api.logging().logToOutput("Generating UI");
        addTabs();
        registerView();
        api.logging().logToOutput("UI Generated");
    }

    private final UploadScannerPanel     view;
    private final MontoyaApi             api;
    private       GlobalConfigController globalConfigController;
    private       ConfigModel            globalConfigModel;

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

    private void addGlobalConfigurationTab() {
        globalConfigModel      = new ConfigModel();
        globalConfigController = new GlobalConfigController(globalConfigModel);
        view.addTab(globalConfigController.getTabName(), globalConfigController.getTab());
    }
}
