package view;

import javax.swing.*;
import java.awt.*;

public class UploadScannerPanel extends JPanel {
  private final JTabbedPane tabbedPane;

  public UploadScannerPanel() {
    super(new BorderLayout());
    tabbedPane = new JTabbedPane();
    add(tabbedPane, BorderLayout.CENTER);
  }

  public void addTab(String tabName, JPanel newTab) {
    tabbedPane.addTab(tabName, newTab);
  }
}
