package view.tabs;

import burp.api.montoya.MontoyaApi;

import javax.swing.*;

public abstract class TabFactory {
  String tabName;
  MontoyaApi api;

  public TabFactory(String tabName, MontoyaApi api) {
    this.tabName = tabName;
    this.api = api;
  }

  public abstract JPanel createTab();

  public String getTabName() {
    return tabName;
  }
}
