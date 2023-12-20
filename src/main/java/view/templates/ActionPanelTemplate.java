package view.templates;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class ActionPanelTemplate extends JPanel{
  
  // PUBLIC FUNCTIONS //
  public ActionPanelTemplate(){
    setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
    _preflightRequestBtn = new JButton("Send Preflight Request");
    _redownloaderBtn     = new JButton("Send ReDownloader Request");
    _startScanBtn        = new JButton("Start Scan");
    _stopScanBtn         = new JButton("Stop Scan");
    _progressBar         = new JProgressBar(0, 100);
    
    JPanel reDownloaderConfigPanel = getReDownloaderPanel();
    JPanel scanControls            = getScanControls();
    
    _stopScanBtn        .setEnabled(false);
    _redownloaderBtn    .setEnabled(false);
    _preflightRequestBtn.setEnabled(false);
    
    add(reDownloaderConfigPanel);
    add(scanControls);
  }
  
  // PRIVATE DATA //
  private static final String  REDOWNLOADER_STATUS_TEXT = "ReDownloader Configured: ";
  
  private final JButton      _preflightRequestBtn;
  private final JButton      _startScanBtn;
  private final JButton      _stopScanBtn;
  private final JButton      _redownloaderBtn;
  private final Boolean      _reDownloaderStat = false;
  private final JProgressBar _progressBar;
  
  // PRIVATE METHODS //
  private JPanel getScanControls(){
    JPanel controlPanel = new JPanel();
    controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
    controlPanel.setBorder(new LineBorder(Color.GRAY));
    
    JPanel buttonGroup = new JPanel();
    buttonGroup.setLayout(new FlowLayout());
    buttonGroup.add(_startScanBtn, BorderLayout.WEST);
    buttonGroup.add(_stopScanBtn, BorderLayout.EAST);
    
    _progressBar.setValue(0);
    _progressBar.setStringPainted(true);
    
    controlPanel.add(buttonGroup);
    controlPanel.add(_progressBar);
    return controlPanel;
  }
  
  private JPanel getReDownloaderPanel(){
    JPanel configPanel = new JPanel();
    configPanel.setLayout(new BoxLayout(configPanel, BoxLayout.Y_AXIS));
    configPanel.setBorder(new LineBorder(Color.GRAY));
    
    JLabel configStatus = new JLabel(REDOWNLOADER_STATUS_TEXT + _reDownloaderStat.toString());
    configStatus.setAlignmentX(CENTER_ALIGNMENT);
    configPanel.add(configStatus);
    
    JPanel preflightPanel = new JPanel();
    preflightPanel.add(_preflightRequestBtn);
    
    JPanel reDownloaderPanel = new JPanel();
    reDownloaderPanel.add(_redownloaderBtn);
    
    JPanel buttonGroup = new JPanel();
    buttonGroup.setLayout(new FlowLayout());
    buttonGroup.add(preflightPanel);
    buttonGroup.add(reDownloaderPanel);
    
    configPanel.add(buttonGroup);
    return configPanel;
  }
}
