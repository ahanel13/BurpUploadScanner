package view;

import burp.api.montoya.MontoyaApi;
import view.tabs.about.AboutTabFactory;

import javax.swing.*;
import java.awt.*;

public class UploadScannerPanel {
  private final MontoyaApi api;
  private final JPanel mainPanel;
  private JTabbedPane tabbedPane;

  public UploadScannerPanel(MontoyaApi montoyaApi) {
    api         = montoyaApi;
    mainPanel   = new JPanel(new BorderLayout());
    tabbedPane  = new JTabbedPane();
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
    // This tab will keep track of uploads and downloads
  }

  private void addGlobalConfigurationTab() {
    // This was use in the previous project, is this useful?
  }
}
