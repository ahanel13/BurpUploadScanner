package controller;

import burp.api.montoya.MontoyaApi;
import view.UploadScannerPanel;
import view.tabs.AboutTab;

public class UploadScannerController {
    private final UploadScannerPanel view;
    private final MontoyaApi api;
    public UploadScannerController(MontoyaApi montoyaApi, UploadScannerPanel scannerPanel) {
        api    = montoyaApi;
        view   = scannerPanel;

        api.logging().logToOutput("Generating UI");
        addTabs();
        registerView();
        api.logging().logToOutput("UI Generated");
    }

    private void registerView() {
        api.userInterface().registerSuiteTab("Upload Scanner", view);
    }

    private void addTabs() {
        // Order matters
        addAboutTab();
        addGlobalConfigurationTab();
        addDoneUploadsTab();
    }

    private void addAboutTab() {
        AboutTab about = new AboutTab();
        view.addTab(about.getTabName(),about);
    }

    private void addDoneUploadsTab() {
        // This tab will keep track of uploads and downloads
    }

    private void addGlobalConfigurationTab() {
        // This was use in the previous project, is this useful?
    }
}
