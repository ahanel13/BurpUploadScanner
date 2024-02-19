package view.templates;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class ActionPanelTemplate extends JPanel {
  ////////////////////////////////////////
  // PUBLIC FIELDS
  ////////////////////////////////////////
  public final JButton      preflightRequestBtn;
  public final JButton      startScanBtn;
  public final JButton      stopScanBtn;
  public final JButton      downloaderBtn;
  public final Boolean      downloaderStat = false;
  public final JProgressBar progressBar;

  ////////////////////////////////////////
  // PUBLIC FUNCTIONS
  ////////////////////////////////////////
  public ActionPanelTemplate() {
    setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
    preflightRequestBtn = new JButton("Send Preflight Request");
    downloaderBtn       = new JButton("Send Downloader Request");
    startScanBtn        = new JButton("Start Scan");
    stopScanBtn         = new JButton("Stop Scan");
    progressBar         = new JProgressBar(0, 100);

    JPanel downloaderConfigPanel = getDownloaderPanel();
    JPanel scanControls          = getScanControls();

    stopScanBtn.setEnabled(false);
    downloaderBtn.setEnabled(false);
    preflightRequestBtn.setEnabled(false);

    add(downloaderConfigPanel);
    add(scanControls);
  }

  ////////////////////////////////////////
  // PRIVATE FIELDS
  ////////////////////////////////////////
  private static final String REDOWNLOADER_STATUS_TEXT = "Downloader Configured: ";

  ////////////////////////////////////////
  // PRIVATE METHODS
  ////////////////////////////////////////
  private JPanel getScanControls() {
    JPanel controlPanel = new JPanel();
    controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
    controlPanel.setBorder(new LineBorder(Color.GRAY));

    JPanel buttonGroup = new JPanel();
    buttonGroup.setLayout(new FlowLayout());
    buttonGroup.add(startScanBtn, BorderLayout.WEST);
    buttonGroup.add(stopScanBtn, BorderLayout.EAST);

    progressBar.setValue(0);
    progressBar.setStringPainted(true);

    controlPanel.add(buttonGroup);
    controlPanel.add(progressBar);
    return controlPanel;
  }

  private JPanel getDownloaderPanel() {
    JPanel configPanel = new JPanel();
    configPanel.setLayout(new BoxLayout(configPanel, BoxLayout.Y_AXIS));
    configPanel.setBorder(new LineBorder(Color.GRAY));

    JLabel configStatus = new JLabel(REDOWNLOADER_STATUS_TEXT + downloaderStat.toString());
    configStatus.setAlignmentX(CENTER_ALIGNMENT);
    configPanel.add(configStatus);

    JPanel preflightPanel = new JPanel();
    preflightPanel.add(preflightRequestBtn);

    JPanel downloaderPanel = new JPanel();
    downloaderPanel.add(downloaderBtn);

    JPanel buttonGroup = new JPanel();
    buttonGroup.setLayout(new FlowLayout());
    buttonGroup.add(preflightPanel);
    buttonGroup.add(downloaderPanel);

    configPanel.add(buttonGroup);
    return configPanel;
  }
}
