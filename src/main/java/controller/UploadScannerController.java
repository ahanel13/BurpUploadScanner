package controller;

import burp.api.montoya.MontoyaApi;
import burp.api.montoya.ui.contextmenu.ContextMenuItemsProvider;
import controller.tabControllers.GlobalConfigController;
import model.ConfigModel;
import view.UploadScannerMenuContext;
import view.UploadScannerPanel;
import view.tabs.AboutTab;
import view.tabs.ScanTab;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class UploadScannerController implements ContextMenuItemsProvider {

    public UploadScannerController(MontoyaApi montoyaApi, UploadScannerPanel scannerPanel,
                                   UploadScannerMenuContext burpMenuContext) throws Exception {
        api         = montoyaApi;
        view        = scannerPanel;
        menuContext = burpMenuContext;
        scanTabList = new ArrayList<>();

        api.logging().logToOutput("Generating UI");
        addTabs();
        registerView();
        registerMenuContext();
        api.logging().logToOutput("UI Generated");
    }

    private final UploadScannerMenuContext menuContext;
    private final UploadScannerPanel       view;
    private final MontoyaApi               api;
    private final List<ScanTab>            scanTabList;
    private       ConfigModel              globalConfigModel;
    private       GlobalConfigController   globalConfigController;

    private void registerMenuContext() {
        api.userInterface().registerContextMenuItemsProvider(menuContext);
        menuContext.addEventListenerToMenuItem(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ScanTab newScanTab = new ScanTab(globalConfigModel, menuContext.getRequestResponse());
                scanTabList.add(newScanTab);
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

    private void addGlobalConfigurationTab() {
        globalConfigModel      = new ConfigModel();
        globalConfigController = new GlobalConfigController(globalConfigModel);
        view.addTab(globalConfigController.getTabName(), globalConfigController.getTab());
    }
}
