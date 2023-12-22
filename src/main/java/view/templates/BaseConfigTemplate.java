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
  
  public void setScanChecksVisibility(boolean visibility) {
    activescanScanCheck.setVisible(visibility);
    imagetragickScanCheck.setVisible(visibility);
    magickScanCheck.setVisible(visibility);
    gsScanCheck.setVisible(visibility);
    libavformatScanCheck.setVisible(visibility);
    phpScanCheck.setVisible(visibility);
    jspScanCheck.setVisible(visibility);
    aspScanCheck.setVisible(visibility);
    htaccessScanCheck.setVisible(visibility);
    cgiScanCheck.setVisible(visibility);
    ssiScanCheck.setVisible(visibility);
    xxeScanCheck.setVisible(visibility);
    xssScanCheck.setVisible(visibility);
    eicarScanCheck.setVisible(visibility);
    pdfScanCheck.setVisible(visibility);
    ssrfScanCheck.setVisible(visibility);
    csv_spreadsheetScanCheck.setVisible(visibility);
    path_traversalScanCheck.setVisible(visibility);
    polyglotScanCheck.setVisible(visibility);
    fingerpingScanCheck.setVisible(visibility);
    quirksScanCheck.setVisible(visibility);
    url_replacerScanCheck.setVisible(visibility);
    recursive_uploaderScanCheck.setVisible(visibility);
    fuzzerScanCheck.setVisible(visibility);
    dosScanCheck.setVisible(visibility);
  }
  
  public void setFileTypesVisibility(boolean visibility){
    gifFileType.setVisible(visibility);
    pngFileType.setVisible(visibility);
    jpegFileType.setVisible(visibility);
    tiffFileType.setVisible(visibility);
    icoFileType.setVisible(visibility);
    svgFileType.setVisible(visibility);
    mvgFileType.setVisible(visibility);
    pdfFileType.setVisible(visibility);
    mp4FileType.setVisible(visibility);
    docxFileType.setVisible(visibility);
    xlsxFileType.setVisible(visibility);
    swfFileType.setVisible(visibility);
    csvFileType.setVisible(visibility);
    zipFileType.setVisible(visibility);
    gzipFileType.setVisible(visibility);
    htmlFileType.setVisible(visibility);
    xmlFileType.setVisible(visibility);
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
  private static final int       STD_WIDTH      = 250;
  private static final int       STD_JTEXT_COLS = 3;
  private static final Dimension STD_INPUT_SIZE = new Dimension(STD_WIDTH, 30);
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
  
  private static final String SHOW_SCAN_CHECKS     = "show.scan.checks";
  private static final String activescan           = "activescan";
  private static final String imagetragick         = "imagetragick";
  private static final String magick               = "magick";
  private static final String gs                   = "gs";
  private static final String libavformat          = "libavformat";
  private static final String php                  = "php";
  private static final String jsp                  = "jsp";
  private static final String asp                  = "asp";
  private static final String htaccess             = "htaccess";
  private static final String cgi                  = "cgi";
  private static final String ssi                  = "ssi";
  private static final String xxe                  = "xxe";
  private static final String xss                  = "xss";
  private static final String eicar                = "eicar";
  private static final String pdf                  = "pdf";
  private static final String ssrf                 = "ssrf";
  private static final String csv_spreadsheet      = "csv_spreadsheet";
  private static final String path_traversal       = "path_traversal";
  private static final String polyglot             = "polyglot";
  private static final String fingerping           = "fingerping";
  private static final String quirks               = "quirks";
  private static final String url_replacer         = "url_replacer";
  private static final String recursive_uploader   = "recursive_uploader";
  private static final String fuzzer               = "fuzzer";
  private static final String dos                  = "dos";
  
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
  private JCheckBox showScanChecks;
  private JCheckBox activescanScanCheck;
  private JCheckBox imagetragickScanCheck;
  private JCheckBox magickScanCheck;
  private JCheckBox gsScanCheck;
  private JCheckBox libavformatScanCheck;
  private JCheckBox phpScanCheck;
  private JCheckBox jspScanCheck;
  private JCheckBox aspScanCheck;
  private JCheckBox htaccessScanCheck;
  private JCheckBox cgiScanCheck;
  private JCheckBox ssiScanCheck;
  private JCheckBox xxeScanCheck;
  private JCheckBox xssScanCheck;
  private JCheckBox eicarScanCheck;
  private JCheckBox pdfScanCheck;
  private JCheckBox ssrfScanCheck;
  private JCheckBox csv_spreadsheetScanCheck;
  private JCheckBox path_traversalScanCheck;
  private JCheckBox polyglotScanCheck;
  private JCheckBox fingerpingScanCheck;
  private JCheckBox quirksScanCheck;
  private JCheckBox url_replacerScanCheck;
  private JCheckBox recursive_uploaderScanCheck;
  private JCheckBox fuzzerScanCheck;
  private JCheckBox dosScanCheck;
  
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
    showScanChecks              = createOptionCheckBox(SHOW_SCAN_CHECKS);
    activescanScanCheck         = createOptionCheckBox(activescan);
    imagetragickScanCheck       = createOptionCheckBox(imagetragick);
    magickScanCheck             = createOptionCheckBox(magick);
    gsScanCheck                 = createOptionCheckBox(gs);
    libavformatScanCheck        = createOptionCheckBox(libavformat);
    phpScanCheck                = createOptionCheckBox(php);
    jspScanCheck                = createOptionCheckBox(jsp);
    aspScanCheck                = createOptionCheckBox(asp);
    htaccessScanCheck           = createOptionCheckBox(htaccess);
    cgiScanCheck                = createOptionCheckBox(cgi);
    ssiScanCheck                = createOptionCheckBox(ssi);
    xxeScanCheck                = createOptionCheckBox(xxe);
    xssScanCheck                = createOptionCheckBox(xss);
    eicarScanCheck              = createOptionCheckBox(eicar);
    pdfScanCheck                = createOptionCheckBox(pdf);
    ssrfScanCheck               = createOptionCheckBox(ssrf);
    csv_spreadsheetScanCheck    = createOptionCheckBox(csv_spreadsheet);
    path_traversalScanCheck     = createOptionCheckBox(path_traversal);
    polyglotScanCheck           = createOptionCheckBox(polyglot);
    fingerpingScanCheck         = createOptionCheckBox(fingerping);
    quirksScanCheck             = createOptionCheckBox(quirks);
    url_replacerScanCheck       = createOptionCheckBox(url_replacer);
    recursive_uploaderScanCheck = createOptionCheckBox(recursive_uploader);
    fuzzerScanCheck             = createOptionCheckBox(fuzzer);
    dosScanCheck                = createOptionCheckBox(dos);
    setScanChecksVisibility(false);
    
    JPanel scanCheckSection = getSectionPanel();
    scanCheckSection.add(showScanChecks);
    scanCheckSection.add(activescanScanCheck);
    scanCheckSection.add(imagetragickScanCheck);
    scanCheckSection.add(magickScanCheck);
    scanCheckSection.add(gsScanCheck);
    scanCheckSection.add(libavformatScanCheck);
    scanCheckSection.add(phpScanCheck);
    scanCheckSection.add(jspScanCheck);
    scanCheckSection.add(aspScanCheck);
    scanCheckSection.add(htaccessScanCheck);
    scanCheckSection.add(cgiScanCheck);
    scanCheckSection.add(ssiScanCheck);
    scanCheckSection.add(xxeScanCheck);
    scanCheckSection.add(xssScanCheck);
    scanCheckSection.add(eicarScanCheck);
    scanCheckSection.add(pdfScanCheck);
    scanCheckSection.add(ssrfScanCheck);
    scanCheckSection.add(csv_spreadsheetScanCheck);
    scanCheckSection.add(path_traversalScanCheck);
    scanCheckSection.add(polyglotScanCheck);
    scanCheckSection.add(fingerpingScanCheck);
    scanCheckSection.add(quirksScanCheck);
    scanCheckSection.add(url_replacerScanCheck);
    scanCheckSection.add(recursive_uploaderScanCheck);
    scanCheckSection.add(fuzzerScanCheck);
    scanCheckSection.add(dosScanCheck);
    
    add(scanCheckSection);
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
    setFileTypesVisibility(false);
    
    JPanel fileTypeSection = getSectionPanel();
    
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
  
  private JPanel getSectionPanel() {
    JPanel sectionPanel = new JPanel();
    sectionPanel.setLayout(new BoxLayout(sectionPanel, BoxLayout.Y_AXIS));
    // sectionPanel.setBorder(STD_BORDER);
    sectionPanel.setAlignmentX(CENTER_ALIGNMENT);
    sectionPanel.setMaximumSize(new Dimension(STD_WIDTH, 1000));
    return sectionPanel;
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
    checkBox.setPreferredSize(STD_INPUT_SIZE);
    checkBox.setText(_properties.getProperty(propertyKey));
    checkBox.setAlignmentX(RIGHT_ALIGNMENT);
    return checkBox;
  }
}
