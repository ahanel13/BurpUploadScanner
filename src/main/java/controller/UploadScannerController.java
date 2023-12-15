package controller;

import burp.api.montoya.MontoyaApi;
import burp.api.montoya.http.message.HttpRequestResponse;
import burp.api.montoya.persistence.PersistedObject;
import controller.tabControllers.ScanConfigController;
import controller.tabControllers.ScanTabController;
import model.ScanConfigModel;
import model.ScanModel;
import view.UploadScannerMenuContext;
import view.UploadScannerPanel;
import view.tabs.AboutTab;
import view.tabs.DefaultConfigTab;
import view.tabs.ScanTab;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UploadScannerController {

    public UploadScannerController(MontoyaApi montoyaApi, UploadScannerPanel scannerPanel,
                                   UploadScannerMenuContext context
    ) throws Exception {
        api                   = montoyaApi;
        view                  = scannerPanel;
        persistedObject       = api.persistence().extensionData();
        scanTabControllerList = new ArrayList<>();
        menuContext               = context;

        api.logging().logToOutput("Generating UI");
        addTabs();
        registerView();
        registerMenuContext();
        api.logging().logToOutput("UI Generated");
    }

    private final UploadScannerPanel       view;
    private final MontoyaApi               api;
    private final PersistedObject          persistedObject;
    private       ScanConfigModel          defaultScanOptions;
    private       ScanConfigController     defaultConfigController;
    private final UploadScannerMenuContext menuContext;
    private final List<ScanTabController>  scanTabControllerList;

    private void registerMenuContext() {
        api.userInterface().registerContextMenuItemsProvider(menuContext);
        menuContext.addEventListenerToMenuItem(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HttpRequestResponse requestResponse = menuContext.getRequestResponse();
                ScanModel           scanModel       = new ScanModel(defaultScanOptions, requestResponse);
                ScanTab             scanTabView     = view.addScanTab(requestResponse);
                ScanTabController   newScanTab      = new ScanTabController(scanModel, scanTabView);
                scanTabControllerList.add(newScanTab);
            }
        });
    }

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

    private void addGlobalConfigurationTab() throws IOException {
        DefaultConfigTab defaultScanOptionsView = new DefaultConfigTab();
        defaultScanOptions                      = new ScanConfigModel(persistedObject);
        defaultConfigController                 = new ScanConfigController(defaultScanOptions, defaultScanOptionsView);
        view.addTab(defaultConfigController.getTabName(), defaultConfigController.getTab());
    }
}
