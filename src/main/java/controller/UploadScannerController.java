package controller;

import burp.api.montoya.MontoyaApi;
import burp.api.montoya.http.message.HttpRequestResponse;
import burp.api.montoya.persistence.PersistedObject;
import controller.tabControllers.BaseConfigController;
import controller.tabControllers.ScanTabController;
import model.BaseConfigModel;
import model.ScanModel;
import view.UploadScannerMenuContext;
import view.UploadScannerPanel;
import view.tabs.AboutTab;
import view.tabs.BaseConfigTab;
import view.tabs.ScanTab;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UploadScannerController {
  ////////////////////////////////////////
  // PUBLIC FUNCTIONS
  ////////////////////////////////////////
  public UploadScannerController(
      MontoyaApi montoyaApi, UploadScannerPanel scannerPanel,
      UploadScannerMenuContext context
  ) throws Exception {
    api                   = montoyaApi;
    view                  = scannerPanel;
    persistedObject       = api.persistence().extensionData();
    scanTabControllerList = new ArrayList<>();
    menuContext           = context;

    api.logging().logToOutput("Generating UI");
    addTabs();
    registerView();
    registerMenuContext();
    api.logging().logToOutput("UI Generated");
  }

  ////////////////////////////////////////
  // PRIVATE FIELDS
  ////////////////////////////////////////
  private final UploadScannerPanel       view;
  private final MontoyaApi               api;
  private final PersistedObject          persistedObject;
  private final UploadScannerMenuContext menuContext;
  private final List<ScanTabController>  scanTabControllerList;
  private       BaseConfigModel          defaultScanOptions;
  private       BaseConfigController     defaultConfigController;

  ////////////////////////////////////////
  // PRIVATE METHODS
  ////////////////////////////////////////
  private void registerMenuContext() {
    api.userInterface().registerContextMenuItemsProvider(menuContext);
    menuContext.addEventListenerToMenuItem(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {

          HttpRequestResponse requestResponse = menuContext.getRequestResponse();
          ScanModel         scanModel   = new ScanModel(api, defaultScanOptions, requestResponse);
          ScanTab           scanTabView = view.addScanTab(requestResponse);
          ScanTabController newScanTab  = new ScanTabController(scanModel, scanTabView);
          scanTabControllerList.add(newScanTab);
        }
        catch (IOException ex) {
          throw new RuntimeException(ex);
        }
      }
    });
  }

  private void registerView() {api.userInterface().registerSuiteTab("Upload Scanner", view);}

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
    BaseConfigTab defaultScanOptionsView = new BaseConfigTab();
    defaultScanOptions      = new BaseConfigModel(persistedObject);
    defaultConfigController = new BaseConfigController(defaultScanOptions, defaultScanOptionsView);
    view.addTab(defaultConfigController.getTabName(), defaultConfigController.getTab());
  }
}
