package view.templates;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class ActionPanelTemplate extends JPanel{
  
  public final JButton      preflightRequestBtn;
  public final JButton      startScanBtn;
  public final JButton      stopScanBtn;
  public final JButton      reDownloaderBtn;
  public final Boolean      reDownloaderStat = false;
  public final JProgressBar progressBar;
  
  // PUBLIC FUNCTIONS //
  public ActionPanelTemplate(){
    setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
    preflightRequestBtn = new JButton("Send Preflight Request");
    reDownloaderBtn     = new JButton("Send ReDownloader Request");
    startScanBtn        = new JButton("Start Scan");
    stopScanBtn = new JButton("Stop Scan");
    progressBar = new JProgressBar(0, 100);
    
    JPanel reDownloaderConfigPanel = getReDownloaderPanel();
    JPanel scanControls            = getScanControls();
    
    stopScanBtn.setEnabled(false);
    reDownloaderBtn.setEnabled(false);
    preflightRequestBtn.setEnabled(false);
    
    add(reDownloaderConfigPanel);
    add(scanControls);
  }
  
  // PRIVATE DATA //
  private static final String  REDOWNLOADER_STATUS_TEXT = "ReDownloader Configured: ";
  
  // PRIVATE METHODS //
  private JPanel getScanControls(){
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
  
  private JPanel getReDownloaderPanel(){
    JPanel configPanel = new JPanel();
    configPanel.setLayout(new BoxLayout(configPanel, BoxLayout.Y_AXIS));
    configPanel.setBorder(new LineBorder(Color.GRAY));
    
    JLabel configStatus = new JLabel(REDOWNLOADER_STATUS_TEXT + reDownloaderStat.toString());
    configStatus.setAlignmentX(CENTER_ALIGNMENT);
    configPanel.add(configStatus);
    
    JPanel preflightPanel = new JPanel();
    preflightPanel.add(preflightRequestBtn);
    
    JPanel reDownloaderPanel = new JPanel();
    reDownloaderPanel.add(reDownloaderBtn);
    
    JPanel buttonGroup = new JPanel();
    buttonGroup.setLayout(new FlowLayout());
    buttonGroup.add(preflightPanel);
    buttonGroup.add(reDownloaderPanel);
    
    configPanel.add(buttonGroup);
    return configPanel;
  }
}
