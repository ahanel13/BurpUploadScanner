package view.templates;

import model.utilities.ResourceLoader;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;
import java.util.Properties;

public class BaseConfigTemplate extends JPanel {
  
  // PUBLIC FUNCTIONS //
  public BaseConfigTemplate() throws IOException {
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    setBorder(new EmptyBorder(20, 20, 20, 20));
    
    _fileOptions    = new JPanel();
    _payloadOptions = new JPanel();
    _generalOptions = new JPanel();
    _properties     = ResourceLoader.loadPropertyFile(CLASS_PROPERTIES);
    
    addScanCheckOptions();
    addFileTypeOptions();
    addFileOptions();
    getThrottleTimeGroup();
    addPayloadOptions();
    addSleepOptions();
    addGeneralOptions();
  }
  
  // PUBLIC METHODS //
  public JCheckBox getReplaceContentType() {return _replaceContentType;}
  public JCheckBox getAddToLoggingChkBox()                      {return _addToLoggingChkBox;}
  public JCheckBox getWgetCurlPayloads()                        {return _wgetCurlPayloads;}
  public JCheckBox getReplaceFileName()                         {return _replaceFileName;}
  public JCheckBox getReplaceFileSize()                         {return _replaceFileSize;}
  public String getThrottleValue()                              {return _throttleValue.getText();}
  public String getSleepTime()                                  {return _sleepTime.getText();}
  
  public void setReplaceContentType(boolean replaceContentType) {_replaceContentType.setSelected(replaceContentType);}
  public void setReplaceFileName(boolean replaceFileName)       {_replaceFileName.setSelected(replaceFileName);}
  public void setReplaceFileSize(boolean replaceFileSize)       {_replaceFileSize.setSelected(replaceFileSize);}
  public void setAddToLoggingChkBox(boolean addToLogging)       {_addToLoggingChkBox.setSelected(addToLogging);}
  public void setWgetCurlPayloads(boolean wgetCurlPayloads)     {_wgetCurlPayloads.setSelected(wgetCurlPayloads);}
  public void setSleepTime(short sleepTime)                     {_sleepTime.setText(String.valueOf(sleepTime));}
  public void setThrottleValue(short throttleValue)             {_throttleValue.setText(String.valueOf(throttleValue));}
  
  public void hidFileTypes(){
    gifFileType.setVisible(false);
    pngFileType.setVisible(false);
    jpegFileType.setVisible(false);
    tiffFileType.setVisible(false);
    icoFileType.setVisible(false);
    svgFileType.setVisible(false);
    mvgFileType.setVisible(false);
    pdfFileType.setVisible(false);
    mp4FileType.setVisible(false);
    docxFileType.setVisible(false);
    xlsxFileType.setVisible(false);
    swfFileType.setVisible(false);
    csvFileType.setVisible(false);
    zipFileType.setVisible(false);
    gzipFileType.setVisible(false);
    htmlFileType.setVisible(false);
    xmlFileType.setVisible(false);
  }
  
  public void showFileTypes(){
    gifFileType.setVisible(true);
    pngFileType.setVisible(true);
    jpegFileType.setVisible(true);
    tiffFileType.setVisible(true);
    icoFileType.setVisible(true);
    svgFileType.setVisible(true);
    mvgFileType.setVisible(true);
    pdfFileType.setVisible(true);
    mp4FileType.setVisible(true);
    docxFileType.setVisible(true);
    xlsxFileType.setVisible(true);
    swfFileType.setVisible(true);
    csvFileType.setVisible(true);
    zipFileType.setVisible(true);
    gzipFileType.setVisible(true);
    htmlFileType.setVisible(true);
    xmlFileType.setVisible(true);
  }
  
  // PACKAGE-PRIVATE METHODS //
  void addColors() {
    // Color constants
    final Color FILE_OPTIONS_COLOR    = new Color(110, 165, 117);
    final Color PAYLOAD_OPTIONS_COLOR = new Color(110, 117, 165);
    final Color GENERAL_OPTIONS_COLOR = new Color(165, 110, 149);
    final Color BACKGROUND_COLOR      = new Color(165, 110, 110);
    
    _fileOptions.setBackground(FILE_OPTIONS_COLOR);
    _payloadOptions.setBackground(PAYLOAD_OPTIONS_COLOR);
    _generalOptions.setBackground(GENERAL_OPTIONS_COLOR);
    setBackground(BACKGROUND_COLOR);
  }
  
  // PRIVATE DATA //
  private static final Dimension STD_INPUT_SIZE = new Dimension(200, 30);
  private static final int       STD_JTEXT_COLS = 3;
  private static final Border    STD_BORDER     = new EmptyBorder(5, 5, 5, 5);
  
  // Property Keys
  private static final String CLASS_PROPERTIES     = "BaseConfigView.properties";
  private static final String WGET_CURL_LABEL      = "wget.curl.label";
  private static final String REPLACE_FILE_NAME    = "replace.file.name";
  private static final String REPLACE_FILE_SIZE    = "replace.file.size";
  private static final String REPLACE_CONTENT_TYPE = "replace.content.type";
  private static final String ADD_TO_LOGGING       = "add.to.logging.tab";
  private static final String THROTTLE_TIME        = "throttle.time";
  private static final String SLEEP_TIME_LABEL     = "sleep.time.label";
  private static final String SHOW_FILES           = "show.files";
  private static final String GIF                  = "GIF";
  private static final String PNG                  = "PNG";
  private static final String JPEG                 = "JPEG";
  private static final String TIFF                 = "TIFF";
  private static final String ICO                  = "ICO";
  private static final String SVG                  = "SVG";
  private static final String MVG                  = "MVG";
  private static final String PDF                  = "PDF";
  private static final String MP4                  = "MP4";
  private static final String DOCX                 = "DOCX";
  private static final String EXCEL                = "EXCEL";
  private static final String FLASH                = "FLASH";
  private static final String CSV                  = "CSV";
  private static final String ZIP                  = "ZIP";
  private static final String GZIP                 = "GZIP";
  private static final String HTML                 = "HTML";
  private static final String XML                  = "XML";
  
  private final Properties _properties;
  
  // UI components
  private final JPanel _generalOptions;
  private final JPanel _fileOptions;
  private final JPanel _payloadOptions;
  private       JLabel _throttleTimeLabel;
  private       JLabel _sleepTimeLabel;
  
  // UI inputs
  private JCheckBox showFileType;
  private JCheckBox gifFileType;
  private JCheckBox pngFileType;
  private JCheckBox jpegFileType;
  private JCheckBox tiffFileType;
  private JCheckBox icoFileType;
  private JCheckBox svgFileType;
  private JCheckBox mvgFileType;
  private JCheckBox pdfFileType;
  private JCheckBox mp4FileType;
  private JCheckBox docxFileType;
  private JCheckBox xlsxFileType;
  private JCheckBox swfFileType;
  private JCheckBox csvFileType;
  private JCheckBox zipFileType;
  private JCheckBox gzipFileType;
  private JCheckBox htmlFileType;
  private JCheckBox xmlFileType;
  
  private JCheckBox  _addToLoggingChkBox;
  private JCheckBox  _replaceContentType;
  private JCheckBox  _wgetCurlPayloads;
  private JCheckBox  _replaceFileName;
  private JCheckBox  _replaceFileSize;
  private JTextField _throttleValue;
  private JTextField _sleepTime;
  
  ////////////////////////////////////////
  // PRIVATE METHODS //
  ////////////////////////////////////////
  // Adding UI Components to main JPanel
  private void addScanCheckOptions() {
  
  }
  
  private void addFileTypeOptions() {
    showFileType = createOptionCheckBox(SHOW_FILES);
    gifFileType  = createOptionCheckBox(GIF);
    pngFileType  = createOptionCheckBox(PNG);
    jpegFileType = createOptionCheckBox(JPEG);
    tiffFileType = createOptionCheckBox(TIFF);
    icoFileType  = createOptionCheckBox(ICO);
    svgFileType  = createOptionCheckBox(SVG);
    mvgFileType  = createOptionCheckBox(MVG);
    pdfFileType  = createOptionCheckBox(PDF);
    mp4FileType  = createOptionCheckBox(MP4);
    docxFileType = createOptionCheckBox(DOCX);
    xlsxFileType = createOptionCheckBox(EXCEL);
    swfFileType  = createOptionCheckBox(FLASH);
    csvFileType  = createOptionCheckBox(CSV);
    zipFileType  = createOptionCheckBox(ZIP);
    gzipFileType = createOptionCheckBox(GZIP);
    htmlFileType = createOptionCheckBox(HTML);
    xmlFileType  = createOptionCheckBox(XML);
    
    // hidFileTypes();
    JPanel fileTypeSection = new JPanel();
    fileTypeSection.setLayout(new BoxLayout(fileTypeSection, BoxLayout.Y_AXIS));
    fileTypeSection.setBorder(STD_BORDER);
    fileTypeSection.setAlignmentX(CENTER_ALIGNMENT);
    fileTypeSection.add(showFileType);
    fileTypeSection.add(gifFileType);
    fileTypeSection.add(pngFileType);
    fileTypeSection.add(jpegFileType);
    fileTypeSection.add(tiffFileType);
    fileTypeSection.add(icoFileType);
    fileTypeSection.add(svgFileType);
    fileTypeSection.add(mvgFileType);
    fileTypeSection.add(pdfFileType);
    fileTypeSection.add(mp4FileType);
    fileTypeSection.add(docxFileType);
    fileTypeSection.add(xlsxFileType);
    fileTypeSection.add(swfFileType);
    fileTypeSection.add(csvFileType);
    fileTypeSection.add(zipFileType);
    fileTypeSection.add(gzipFileType);
    fileTypeSection.add(htmlFileType);
    fileTypeSection.add(xmlFileType);
    
    add(fileTypeSection);
  }
  
  private void addPayloadOptions() {
    _payloadOptions.setLayout(new BoxLayout(_payloadOptions, BoxLayout.Y_AXIS));
    _payloadOptions.setBorder(STD_BORDER);
    _payloadOptions.setAlignmentX(CENTER_ALIGNMENT);
    _payloadOptions.add(getWgetCurlOpt());
    add(_payloadOptions);
  }
  
  private void addFileOptions() {
    _fileOptions.setLayout(new BoxLayout(_fileOptions, BoxLayout.Y_AXIS));
    _fileOptions.setBorder(STD_BORDER);
    _fileOptions.add(getReplaceFileSizeOpt());
    _fileOptions.add(getReplaceFileNameOpt());
    _fileOptions.add(getReplaceFileContentOpt());
    add(_fileOptions);
  }
  
  private void addGeneralOptions() {
    _generalOptions.setLayout(new BoxLayout(_generalOptions, BoxLayout.Y_AXIS));
    _generalOptions.setBorder(STD_BORDER);
    _generalOptions.add(getAddToLoggingOpt());
    _generalOptions.add(getThrottleTimeGroup());
    add(_generalOptions);
  }
  
  // Initializers for groups of components
  //////////////////////////////////////////////
  private void addSleepOptions() {
    final JPanel sleepOptions = getTextFileGroupPanel();
    
    _sleepTimeLabel = new JLabel();
    _sleepTimeLabel.setText(_properties.getProperty(SLEEP_TIME_LABEL));
    sleepOptions.add(_sleepTimeLabel);
    
    _sleepTime = new JTextField(STD_JTEXT_COLS);
    sleepOptions.add(_sleepTime);
    
    _payloadOptions.add(sleepOptions);
  }
  
  private JPanel getWgetCurlOpt() {
    _wgetCurlPayloads = createOptionCheckBox(WGET_CURL_LABEL);
    JPanel opts = getCheckBoxBorderJPanel();
    opts.add(_wgetCurlPayloads, BorderLayout.EAST);
    return opts;
  }
  
  private JPanel getReplaceFileSizeOpt() {
    _replaceFileSize = createOptionCheckBox(REPLACE_FILE_SIZE);
    JPanel fileSizeOpt = getCheckBoxBorderJPanel();
    fileSizeOpt.add(_replaceFileSize, BorderLayout.EAST);
    return fileSizeOpt;
  }
  
  private JPanel getReplaceFileNameOpt() {
    _replaceFileName = createOptionCheckBox(REPLACE_FILE_NAME);
    JPanel replaceFileNameOpt = getCheckBoxBorderJPanel();
    replaceFileNameOpt.add(_replaceFileName, BorderLayout.EAST);
    
    return replaceFileNameOpt;
  }
  
  private JPanel getReplaceFileContentOpt() {
    _replaceContentType = createOptionCheckBox(REPLACE_CONTENT_TYPE);
    JPanel replaceFileContOpt = getCheckBoxBorderJPanel();
    replaceFileContOpt.add(_replaceContentType, BorderLayout.EAST);
    
    return replaceFileContOpt;
  }
  
  private JPanel getCheckBoxBorderJPanel() {
    JPanel tempPanel = new JPanel();
    tempPanel.setLayout(new BorderLayout());
    tempPanel.setAlignmentX(CENTER_ALIGNMENT);
    tempPanel.setMaximumSize(STD_INPUT_SIZE);
    return tempPanel;
  }
  
  private JPanel getAddToLoggingOpt() {
    _addToLoggingChkBox = createOptionCheckBox(ADD_TO_LOGGING);
    JPanel opt = getCheckBoxBorderJPanel();
    opt.add(_addToLoggingChkBox);
    return opt;
  }
  
  private JPanel getTextFileGroupPanel() {
    JPanel tempPanel = new JPanel();
    tempPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
    tempPanel.setMaximumSize(STD_INPUT_SIZE);
    tempPanel.setAlignmentX(CENTER_ALIGNMENT);
    return tempPanel;
  }
  
  private JPanel getThrottleTimeGroup() {
    final JPanel throttleTimeGroup = getTextFileGroupPanel();
    
    _throttleTimeLabel = new JLabel();
    _throttleTimeLabel.setText(_properties.getProperty(THROTTLE_TIME));
    throttleTimeGroup.add(_throttleTimeLabel);
    
    _throttleValue = new JTextField(STD_JTEXT_COLS);
    throttleTimeGroup.add(_throttleValue);
    
    return throttleTimeGroup;
  }
  
  // Utilities
  //////////////////////////////////////////
  private JCheckBox createOptionCheckBox(String propertyKey) {
    JCheckBox checkBox = new JCheckBox();
    checkBox.setHorizontalTextPosition(10);
    checkBox.setText(_properties.getProperty(propertyKey));
    checkBox.setAlignmentX(RIGHT_ALIGNMENT);
    return checkBox;
  }
}
