package view;

import burp.api.montoya.http.message.HttpRequestResponse;
import burp.api.montoya.ui.UserInterface;
import view.tabs.ScanTab;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class UploadScannerPanel extends JTabbedPane {
  public UploadScannerPanel(UserInterface userInterface) {
    apiUI       = userInterface;
    scanTabList = new ArrayList<>();
  }

  public ScanTab addScanTab(HttpRequestResponse requestResponse) throws IOException {
    ScanTab tempScanTab = new ScanTab(requestResponse, apiUI);
    String  scanTabName = "Scan " + (scanTabList.size() + 1);
    scanTabList.add(tempScanTab);
    addTab(scanTabName, tempScanTab);
    return tempScanTab;
  }

  private final List<ScanTab> scanTabList;
  private final UserInterface apiUI;
}
