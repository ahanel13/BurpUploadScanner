package view.templates;

import model.utilities.ResourceLoader;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;
import java.util.Properties;

public class BaseConfigTemplate extends JPanel {
  
  // PUBLIC FUNCTIONS //
  public BaseConfigTemplate() throws IOException {
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    setBorder(new EmptyBorder(10, 10, 10, 10));
    setPreferredSize(new Dimension(STD_WIDTH, 270));
    
    _properties     = ResourceLoader.loadPropertyFile(CLASS_PROPERTIES);
    
    addScanCheckOptions();
    addFileTypeOptions();
    addFileOptions();
    addPayloadOptions();
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
  
  // Property Keys
  private static final String CLASS_PROPERTIES     = "BaseConfigView.properties";
  private static final String WGET_CURL_LABEL      = "wget.curl.label";
  private static final String REPLACE_FILE_NAME    = "replace.file.name";
  private static final String REPLACE_FILE_SIZE    = "replace.file.size";
  private static final String REPLACE_CONTENT_TYPE = "replace.content.type";
  private static final String ADD_TO_LOGGING       = "add.to.logging.tab";
  private static final String THROTTLE_TIME        = "throttle.time";
  private static final String SLEEP_TIME_LABEL     = "sleep.time.label";
  
  private static final String SHOW_SCAN_CHECKS   = "show.scan.checks";
  private static final String ACTIVE_SCAN        = "activescan";
  private static final String IMAGETRAGICK       = "imagetragick";
  private static final String magick             = "magick";
  private static final String GS                 = "gs";
  private static final String LIBAVFORMAT        = "libavformat";
  private static final String PHP                = "php";
  private static final String JSP                = "jsp";
  private static final String ASP                = "asp";
  private static final String HTACCESS           = "htaccess";
  private static final String CGI                = "cgi";
  private static final String SSI                = "ssi";
  private static final String XXE                = "xxe";
  private static final String XSS                = "xss";
  private static final String EICAR              = "eicar";
  private static final String PDF_INJECTION      = "pdf";
  private static final String SSRF               = "ssrf";
  private static final String CSV_INJECTION      = "csv_spreadsheet";
  private static final String PATH_TRAVERSAL     = "path_traversal";
  private static final String POLYGLOT           = "polyglot";
  private static final String FINGERPING         = "fingerping";
  private static final String QUIRKS             = "quirks";
  private static final String URL_REPLACER       = "url_replacer";
  private static final String RECURSIVE_UPLOADER = "recursive_uploader";
  private static final String FUZZER             = "fuzzer";
  private static final String DOS                = "dos";
  
  private static final String SHOW_FILES = "show.files";
  private static final String GIF        = "GIF";
  private static final String PNG        = "PNG";
  private static final String JPEG       = "JPEG";
  private static final String TIFF       = "TIFF";
  private static final String ICO        = "ICO";
  private static final String SVG        = "SVG";
  private static final String MVG        = "MVG";
  private static final String PDF        = "PDF";
  private static final String MP4        = "MP4";
  private static final String DOCX       = "DOCX";
  private static final String EXCEL      = "EXCEL";
  private static final String FLASH      = "FLASH";
  private static final String CSV        = "CSV";
  private static final String ZIP        = "ZIP";
  private static final String GZIP       = "GZIP";
  private static final String HTML       = "HTML";
  private static final String XML        = "XML";
  
  private final Properties _properties;
  
  // UI components
  private final JPanel _generalOptions = getSectionPanel();
  private final JPanel _fileOptions    = getSectionPanel();
  private final JPanel _payloadOptions = getSectionPanel();
  private       JLabel _throttleTimeLabel;
  private       JLabel _sleepTimeLabel;
  
  // UI inputs
  private final JCheckBox showFileType                = createOptionCheckBox(SHOW_FILES);
  private final JCheckBox gifFileType                 = createOptionCheckBox(GIF);
  private final JCheckBox pngFileType                 = createOptionCheckBox(PNG);
  private final JCheckBox jpegFileType                = createOptionCheckBox(JPEG);
  private final JCheckBox tiffFileType                = createOptionCheckBox(TIFF);
  private final JCheckBox icoFileType                 = createOptionCheckBox(ICO);
  private final JCheckBox svgFileType                 = createOptionCheckBox(SVG);
  private final JCheckBox mvgFileType                 = createOptionCheckBox(MVG);
  private final JCheckBox pdfFileType                 = createOptionCheckBox(PDF);
  private final JCheckBox mp4FileType                 = createOptionCheckBox(MP4);
  private final JCheckBox docxFileType                = createOptionCheckBox(DOCX);
  private final JCheckBox xlsxFileType                = createOptionCheckBox(EXCEL);
  private final JCheckBox swfFileType                 = createOptionCheckBox(FLASH);
  private final JCheckBox csvFileType                 = createOptionCheckBox(CSV);
  private final JCheckBox zipFileType                 = createOptionCheckBox(ZIP);
  private final JCheckBox gzipFileType                = createOptionCheckBox(GZIP);
  private final JCheckBox htmlFileType                = createOptionCheckBox(HTML);
  private final JCheckBox xmlFileType                 = createOptionCheckBox(XML);
  private final JCheckBox showScanChecks              = createOptionCheckBox(SHOW_SCAN_CHECKS);
  private final JCheckBox activescanScanCheck         = createOptionCheckBox(ACTIVE_SCAN);
  private final JCheckBox imagetragickScanCheck       = createOptionCheckBox(IMAGETRAGICK);
  private final JCheckBox magickScanCheck             = createOptionCheckBox(magick);
  private final JCheckBox gsScanCheck                 = createOptionCheckBox(GS);
  private final JCheckBox libavformatScanCheck        = createOptionCheckBox(LIBAVFORMAT);
  private final JCheckBox phpScanCheck                = createOptionCheckBox(PHP);
  private final JCheckBox jspScanCheck                = createOptionCheckBox(JSP);
  private final JCheckBox aspScanCheck                = createOptionCheckBox(ASP);
  private final JCheckBox htaccessScanCheck           = createOptionCheckBox(HTACCESS);
  private final JCheckBox cgiScanCheck                = createOptionCheckBox(CGI);
  private final JCheckBox ssiScanCheck                = createOptionCheckBox(SSI);
  private final JCheckBox xxeScanCheck                = createOptionCheckBox(XXE);
  private final JCheckBox xssScanCheck                = createOptionCheckBox(XSS);
  private final JCheckBox eicarScanCheck              = createOptionCheckBox(EICAR);
  private final JCheckBox pdfScanCheck                = createOptionCheckBox(PDF_INJECTION);
  private final JCheckBox ssrfScanCheck               = createOptionCheckBox(SSRF);
  private final JCheckBox csv_spreadsheetScanCheck    = createOptionCheckBox(CSV_INJECTION);
  private final JCheckBox path_traversalScanCheck     = createOptionCheckBox(PATH_TRAVERSAL);
  private final JCheckBox polyglotScanCheck           = createOptionCheckBox(POLYGLOT);
  private final JCheckBox fingerpingScanCheck         = createOptionCheckBox(FINGERPING);
  private final JCheckBox quirksScanCheck             = createOptionCheckBox(QUIRKS);
  private final JCheckBox url_replacerScanCheck       = createOptionCheckBox(URL_REPLACER);
  private final JCheckBox recursive_uploaderScanCheck = createOptionCheckBox(RECURSIVE_UPLOADER);
  private final JCheckBox fuzzerScanCheck             = createOptionCheckBox(FUZZER);
  private final JCheckBox dosScanCheck                = createOptionCheckBox(DOS);
  private final JCheckBox _addToLoggingChkBox         = createOptionCheckBox(ADD_TO_LOGGING);
  private final JCheckBox _replaceContentType         = createOptionCheckBox(REPLACE_CONTENT_TYPE);
  private final JCheckBox _replaceFileName            = createOptionCheckBox(REPLACE_FILE_NAME);
  private final JCheckBox _replaceFileSize            = createOptionCheckBox(REPLACE_FILE_SIZE);
  private final JCheckBox _wgetCurlPayloads           = createOptionCheckBox(WGET_CURL_LABEL);
  private JTextField _throttleValue;
  private JTextField _sleepTime;
  
  ////////////////////////////////////////
  // PRIVATE METHODS //
  ////////////////////////////////////////
  // Adding UI Components to main JPanel
  private void addScanCheckOptions() {
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
  
  private void addPayloadOptions() {
    _payloadOptions.add(_wgetCurlPayloads);
    _payloadOptions.add(getSleepOptions());
    add(_payloadOptions);
  }
  
  private void addFileOptions() {
    _fileOptions.add(_replaceFileSize);
    _fileOptions.add(_replaceFileName);
    _fileOptions.add(_replaceContentType);
    add(_fileOptions);
  }
  
  private void addGeneralOptions() {
    _generalOptions.add(_addToLoggingChkBox);
    _generalOptions.add(getThrottleTimeGroup());
    add(_generalOptions);
  }
  
  // Initializers for groups of components
  //////////////////////////////////////////////
  private JPanel getThrottleTimeGroup() {
    final JPanel throttleTimeGroup = getTextFileGroupPanel();
    
    _throttleTimeLabel = new JLabel();
    _throttleTimeLabel.setText(_properties.getProperty(THROTTLE_TIME));
    throttleTimeGroup.add(_throttleTimeLabel);
    
    _throttleValue = new JTextField(STD_JTEXT_COLS);
    throttleTimeGroup.add(_throttleValue);
    
    return throttleTimeGroup;
  }
  
  private JPanel getSleepOptions() {
    final JPanel sleepOptions = getTextFileGroupPanel();
    
    _sleepTimeLabel = new JLabel();
    _sleepTimeLabel.setText(_properties.getProperty(SLEEP_TIME_LABEL));
    sleepOptions.add(_sleepTimeLabel);
    
    _sleepTime = new JTextField(STD_JTEXT_COLS);
    sleepOptions.add(_sleepTime);
    return sleepOptions;
  }
  
  // Utilities
  //////////////////////////////////////////
  private JPanel getSectionPanel() {
    JPanel sectionPanel = new JPanel();
    sectionPanel.setLayout(new BoxLayout(sectionPanel, BoxLayout.Y_AXIS));
    // sectionPanel.setBorder(STD_BORDER);
    sectionPanel.setAlignmentX(CENTER_ALIGNMENT);
    sectionPanel.setMaximumSize(new Dimension(STD_WIDTH, 1000));
    return sectionPanel;
  }
  
  private JCheckBox createOptionCheckBox(String propertyKey) {
    JCheckBox checkBox = new JCheckBox();
    checkBox.setHorizontalTextPosition(SwingConstants.LEFT);
    checkBox.setPreferredSize(STD_INPUT_SIZE);
    checkBox.setText(_properties.getProperty(propertyKey));
    checkBox.setAlignmentX(RIGHT_ALIGNMENT);
    return checkBox;
  }
  
  private JPanel getTextFileGroupPanel() {
    JPanel tempPanel = new JPanel();
    tempPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
    tempPanel.setAlignmentX(RIGHT_ALIGNMENT);
    tempPanel.setPreferredSize(STD_INPUT_SIZE);
    return tempPanel;
  }
}
