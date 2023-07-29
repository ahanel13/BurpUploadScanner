package view;

import burp.api.montoya.MontoyaApi;
import view.tabs.about.AboutTabFactory;

import javax.swing.*;
import java.awt.*;

public class UploadScannerPanel {
  private final MontoyaApi api;
  private JPanel mainPanel;
  private JTabbedPane tabbedPane;

  public UploadScannerPanel(MontoyaApi api) {
    this.api = api;
    buildPanel();
    addTabs();
    registerPanel();
  }

  private void registerPanel() {
    mainPanel.add(tabbedPane, BorderLayout.CENTER);
    api.userInterface().registerSuiteTab("Upload Scanner", mainPanel);
  }

  private void addTabs() {
    // Order matters
    addAboutTab();
    addGlobalConfigurationTab();
    addDoneUploadsTab();
  }

  private void addAboutTab() {
    AboutTabFactory about = new AboutTabFactory("About", api);
    tabbedPane.add(about.getTabName(),about.createTab());
  }

  private void addDoneUploadsTab() {

  }

  private void addGlobalConfigurationTab() {
  }

  private void buildPanel() {
    mainPanel = new JPanel(new BorderLayout());
    tabbedPane = new JTabbedPane();
  }
}
