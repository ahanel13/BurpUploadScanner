package view.templates;

import model.utilities.ResourceLoader;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.Properties;

public class BaseConfigTemplate extends JPanel {
  
  // PUBLIC FUNCTIONS //
  public BaseConfigTemplate() throws IOException {
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    setBorder(new EmptyBorder(10, 10, 10, 10));
    addScanCheckOptions();
    addFileTypeOptions();
    addFileOptions();
    addPayloadOptions();
    addGeneralOptions();
    
    addFileTypeListener();
    addScanCheckListener();
  }
  
  
  
  // PUBLIC METHODS //
  // GETTERS
  public JCheckBox getReplaceContentType() { return _replaceContentType; }
  public JCheckBox getAddToLoggingChkBox() { return _addToLoggingChkBox; }
  public JCheckBox getWgetCurlPayloads()   { return _wgetCurlPayloads; }
  public JCheckBox getReplaceFileName()    { return _replaceFileName; }
  public JCheckBox getReplaceFileSize()    { return _replaceFileSize; }
  public String getThrottleValue()         { return _throttleValue.getText(); }
  public String getSleepTime()             { return _sleepTime.getText(); }
  public JCheckBox getGifFileType()                { return _gifFileType; }
  public JCheckBox getPngFileType()                { return _pngFileType; }
  public JCheckBox getJpegFileType()               { return _jpegFileType; }
  public JCheckBox getTiffFileType()               { return _tiffFileType; }
  public JCheckBox getIcoFileType()                { return _icoFileType; }
  public JCheckBox getSvgFileType()                { return _svgFileType; }
  public JCheckBox getMvgFileType()                { return _mvgFileType; }
  public JCheckBox getPdfFileType()                { return _pdfFileType; }
  public JCheckBox getMp4FileType()                { return _mp4FileType; }
  public JCheckBox getDocxFileType()               { return _docxFileType; }
  public JCheckBox getXlsxFileType()               { return _xlsxFileType; }
  public JCheckBox getSwfFileType()                { return _swfFileType; }
  public JCheckBox getCsvFileType()                { return _csvFileType; }
  public JCheckBox getZipFileType()                { return _zipFileType; }
  public JCheckBox getGzipFileType()               { return _gzipFileType; }
  public JCheckBox getHtmlFileType()               { return _htmlFileType; }
  public JCheckBox getXmlFileType()                { return _xmlFileType; }
  public JCheckBox getActivescanScanCheck()        { return _activescanScanCheck; }
  public JCheckBox getImagetragickScanCheck()      { return _imagetragickScanCheck; }
  public JCheckBox getMagickScanCheck()            { return _magickScanCheck; }
  public JCheckBox getGsScanCheck()                { return _gsScanCheck; }
  public JCheckBox getLibavformatScanCheck()       { return _libavformatScanCheck; }
  public JCheckBox getPhpScanCheck()               { return _phpScanCheck; }
  public JCheckBox getJspScanCheck()               { return _jspScanCheck; }
  public JCheckBox getAspScanCheck()               { return _aspScanCheck; }
  public JCheckBox getHtaccessScanCheck()          { return _htaccessScanCheck; }
  public JCheckBox getCgiScanCheck()               { return _cgiScanCheck; }
  public JCheckBox getSsiScanCheck()               { return _ssiScanCheck; }
  public JCheckBox getXxeScanCheck()               { return _xxeScanCheck; }
  public JCheckBox getXssScanCheck()               { return _xssScanCheck; }
  public JCheckBox getEicarScanCheck()             { return _eicarScanCheck; }
  public JCheckBox getPdfInjectionScanCheck()      { return _pdfScanCheck; }
  public JCheckBox getSsrfScanCheck()              { return _ssrfScanCheck; }
  public JCheckBox getCsvInjectionScanCheck()      { return _csvScanCheck; }
  public JCheckBox getPathTraversalScanCheck()     { return _pathTraversalScanCheck; }
  public JCheckBox getPolyglotScanCheck()          { return _polyglotScanCheck; }
  public JCheckBox getFingerpingScanCheck()        { return _fingerpingScanCheck; }
  public JCheckBox getQuirksScanCheck()            { return _quirksScanCheck; }
  public JCheckBox getUrlReplacerScanCheck()       { return _urlReplacerScanCheck; }
  public JCheckBox getRecursiveUploaderScanCheck() { return _recursiveUploaderScanCheck; }
  public JCheckBox getFuzzerScanCheck()            { return _fuzzerScanCheck; }
  public JCheckBox getDosScanCheck()               { return _dosScanCheck; }
  
  
  // SETTERS
  public void setReplaceContentType(boolean b)         {_replaceContentType.setSelected(b);}
  public void setReplaceFileName(boolean b)            {_replaceFileName.setSelected(b);}
  public void setReplaceFileSize(boolean b)            {_replaceFileSize.setSelected(b);}
  public void setAddToLoggingChkBox(boolean b)         {_addToLoggingChkBox.setSelected(b);}
  public void setWgetCurlPayloads(boolean b)           {_wgetCurlPayloads.setSelected(b);}
  public void setSleepTime(short s)                    {_sleepTime.setText(String.valueOf(s));}
  public void setThrottleValue(short s)                {_throttleValue.setText(String.valueOf(s));}
  public void setGifFileType(boolean b)                {_gifFileType.setSelected(b);}
  public void setPngFileType(boolean b)                {_pngFileType.setSelected(b);}
  public void setJpegFileType(boolean b)               {_jpegFileType.setSelected(b);}
  public void setTiffFileType(boolean b)               {_tiffFileType.setSelected(b);}
  public void setIcoFileType(boolean b)                {_icoFileType.setSelected(b);}
  public void setSvgFileType(boolean b)                {_svgFileType.setSelected(b);}
  public void setMvgFileType(boolean b)                {_mvgFileType.setSelected(b);}
  public void setPdfFileType(boolean b)                {_pdfFileType.setSelected(b);}
  public void setMp4FileType(boolean b)                {_mp4FileType.setSelected(b);}
  public void setDocxFileType(boolean b)               {_docxFileType.setSelected(b);}
  public void setXlsxFileType(boolean b)               {_xlsxFileType.setSelected(b);}
  public void setSwfFileType(boolean b)                {_swfFileType.setSelected(b);}
  public void setCsvFileType(boolean b)                {_csvFileType.setSelected(b);}
  public void setZipFileType(boolean b)                {_zipFileType.setSelected(b);}
  public void setGzipFileType(boolean b)               {_gzipFileType.setSelected(b);}
  public void setHtmlFileType(boolean b)               {_htmlFileType.setSelected(b);}
  public void setXmlFileType(boolean b)                {_xmlFileType.setSelected(b);}
  public void setActivescanScanCheck(boolean b)        {_activescanScanCheck.setSelected(b);}
  public void setImagetragickScanCheck(boolean b)      {_imagetragickScanCheck.setSelected(b);}
  public void setMagickScanCheck(boolean b)            {_magickScanCheck.setSelected(b);}
  public void setGsScanCheck(boolean b)                {_gsScanCheck.setSelected(b);}
  public void setLibavformatScanCheck(boolean b)       {_libavformatScanCheck.setSelected(b);}
  public void setPhpScanCheck(boolean b)               {_phpScanCheck.setSelected(b);}
  public void setJspScanCheck(boolean b)               {_jspScanCheck.setSelected(b);}
  public void setAspScanCheck(boolean b)               {_aspScanCheck.setSelected(b);}
  public void setHtaccessScanCheck(boolean b)          {_htaccessScanCheck.setSelected(b);}
  public void setCgiScanCheck(boolean b)               {_cgiScanCheck.setSelected(b);}
  public void setSsiScanCheck(boolean b)               {_ssiScanCheck.setSelected(b);}
  public void setXxeScanCheck(boolean b)               {_xxeScanCheck.setSelected(b);}
  public void setXssScanCheck(boolean b)               {_xssScanCheck.setSelected(b);}
  public void setEicarScanCheck(boolean b)             {_eicarScanCheck.setSelected(b);}
  public void setPdfInjectionScanCheck(boolean b)      {_pdfScanCheck.setSelected(b);}
  public void setSsrfScanCheck(boolean b)              {_ssrfScanCheck.setSelected(b);}
  public void setCsvInjectionScanCheck(boolean b)      {_csvScanCheck.setSelected(b);}
  public void setPathTraversalScanCheck(boolean b)     {_pathTraversalScanCheck.setSelected(b);}
  public void setPolyglotScanCheck(boolean b)          {_polyglotScanCheck.setSelected(b);}
  public void setFingerpingScanCheck(boolean b)        {_fingerpingScanCheck.setSelected(b);}
  public void setQuirksScanCheck(boolean b)            {_quirksScanCheck.setSelected(b);}
  public void setUrlReplacerScanCheck(boolean b)       {_urlReplacerScanCheck.setSelected(b);}
  public void setRecursiveUploaderScanCheck(boolean b) {_recursiveUploaderScanCheck.setSelected(b);}
  public void setFuzzerScanCheck(boolean b)            {_fuzzerScanCheck.setSelected(b);}
  public void setDosScanCheck(boolean b)               {_dosScanCheck.setSelected(b);}
  
  public void addFileTypeListener(ItemListener l)               { _showFileType.addItemListener(l);}
  public void addScanCheckListener(ItemListener l)              { _showScanChecks.addItemListener(l);}
  
  public void setScanChecksVisibility(boolean visibility) {
    _activescanScanCheck.setVisible(visibility);
    _imagetragickScanCheck.setVisible(visibility);
    _magickScanCheck.setVisible(visibility);
    _gsScanCheck.setVisible(visibility);
    _libavformatScanCheck.setVisible(visibility);
    _phpScanCheck.setVisible(visibility);
    _jspScanCheck.setVisible(visibility);
    _aspScanCheck.setVisible(visibility);
    _htaccessScanCheck.setVisible(visibility);
    _cgiScanCheck.setVisible(visibility);
    _ssiScanCheck.setVisible(visibility);
    _xxeScanCheck.setVisible(visibility);
    _xssScanCheck.setVisible(visibility);
    _eicarScanCheck.setVisible(visibility);
    _pdfScanCheck.setVisible(visibility);
    _ssrfScanCheck.setVisible(visibility);
    _csvScanCheck.setVisible(visibility);
    _pathTraversalScanCheck.setVisible(visibility);
    _polyglotScanCheck.setVisible(visibility);
    _fingerpingScanCheck.setVisible(visibility);
    _quirksScanCheck.setVisible(visibility);
    _urlReplacerScanCheck.setVisible(visibility);
    _recursiveUploaderScanCheck.setVisible(visibility);
    _fuzzerScanCheck.setVisible(visibility);
    _dosScanCheck.setVisible(visibility);
  }
  
  public void setFileTypesVisibility(boolean visibility){
    _gifFileType.setVisible(visibility);
    _pngFileType.setVisible(visibility);
    _jpegFileType.setVisible(visibility);
    _tiffFileType.setVisible(visibility);
    _icoFileType.setVisible(visibility);
    _svgFileType.setVisible(visibility);
    _mvgFileType.setVisible(visibility);
    _pdfFileType.setVisible(visibility);
    _mp4FileType.setVisible(visibility);
    _docxFileType.setVisible(visibility);
    _xlsxFileType.setVisible(visibility);
    _swfFileType.setVisible(visibility);
    _csvFileType.setVisible(visibility);
    _zipFileType.setVisible(visibility);
    _gzipFileType.setVisible(visibility);
    _htmlFileType.setVisible(visibility);
    _xmlFileType.setVisible(visibility);
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
  private static final int       STD_HEIGHT     = 30;
  private static final Dimension STD_INPUT_SIZE = new Dimension(STD_WIDTH, STD_HEIGHT);
  private final Properties       _properties    = ResourceLoader.loadPropertyFile(CLASS_PROPERTIES);
  
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
  private static final String MAGICK             = "magick";
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
  
  // UI components
  private final JPanel _generalOptions  = getSectionPanel();
  private final JPanel _fileOptions     = getSectionPanel();
  private final JPanel _payloadOptions  = getSectionPanel();
  private final JPanel scanCheckSection = getSectionPanel();
  private final JPanel fileTypeSection  = getSectionPanel();
  private       JLabel _throttleTimeLabel;
  private       JLabel _sleepTimeLabel;
  
  // UI inputs
  private final JCheckBox _showFileType               = createOptionCheckBox(SHOW_FILES);
  private final JCheckBox _gifFileType                = createOptionCheckBox(GIF);
  private final JCheckBox _pngFileType                = createOptionCheckBox(PNG);
  private final JCheckBox _jpegFileType               = createOptionCheckBox(JPEG);
  private final JCheckBox _tiffFileType               = createOptionCheckBox(TIFF);
  private final JCheckBox _icoFileType                = createOptionCheckBox(ICO);
  private final JCheckBox _svgFileType                = createOptionCheckBox(SVG);
  private final JCheckBox _mvgFileType                = createOptionCheckBox(MVG);
  private final JCheckBox _pdfFileType                = createOptionCheckBox(PDF);
  private final JCheckBox _mp4FileType                = createOptionCheckBox(MP4);
  private final JCheckBox _docxFileType               = createOptionCheckBox(DOCX);
  private final JCheckBox _xlsxFileType               = createOptionCheckBox(EXCEL);
  private final JCheckBox _swfFileType                = createOptionCheckBox(FLASH);
  private final JCheckBox _csvFileType                = createOptionCheckBox(CSV);
  private final JCheckBox _zipFileType                = createOptionCheckBox(ZIP);
  private final JCheckBox _gzipFileType               = createOptionCheckBox(GZIP);
  private final JCheckBox _htmlFileType               = createOptionCheckBox(HTML);
  private final JCheckBox _xmlFileType                = createOptionCheckBox(XML);
  private final JCheckBox _showScanChecks             = createOptionCheckBox(SHOW_SCAN_CHECKS);
  private final JCheckBox _activescanScanCheck        = createOptionCheckBox(ACTIVE_SCAN);
  private final JCheckBox _imagetragickScanCheck      = createOptionCheckBox(IMAGETRAGICK);
  private final JCheckBox _magickScanCheck            = createOptionCheckBox(MAGICK);
  private final JCheckBox _gsScanCheck                = createOptionCheckBox(GS);
  private final JCheckBox _libavformatScanCheck       = createOptionCheckBox(LIBAVFORMAT);
  private final JCheckBox _phpScanCheck               = createOptionCheckBox(PHP);
  private final JCheckBox _jspScanCheck               = createOptionCheckBox(JSP);
  private final JCheckBox _aspScanCheck               = createOptionCheckBox(ASP);
  private final JCheckBox _htaccessScanCheck          = createOptionCheckBox(HTACCESS);
  private final JCheckBox _cgiScanCheck               = createOptionCheckBox(CGI);
  private final JCheckBox _ssiScanCheck               = createOptionCheckBox(SSI);
  private final JCheckBox _xxeScanCheck               = createOptionCheckBox(XXE);
  private final JCheckBox _xssScanCheck               = createOptionCheckBox(XSS);
  private final JCheckBox _eicarScanCheck             = createOptionCheckBox(EICAR);
  private final JCheckBox _pdfScanCheck               = createOptionCheckBox(PDF_INJECTION);
  private final JCheckBox _ssrfScanCheck          = createOptionCheckBox(SSRF);
  private final JCheckBox _csvScanCheck           = createOptionCheckBox(CSV_INJECTION);
  private final JCheckBox _pathTraversalScanCheck = createOptionCheckBox(PATH_TRAVERSAL);
  private final JCheckBox _polyglotScanCheck          = createOptionCheckBox(POLYGLOT);
  private final JCheckBox _fingerpingScanCheck        = createOptionCheckBox(FINGERPING);
  private final JCheckBox _quirksScanCheck            = createOptionCheckBox(QUIRKS);
  private final JCheckBox _urlReplacerScanCheck       = createOptionCheckBox(URL_REPLACER);
  private final JCheckBox _recursiveUploaderScanCheck = createOptionCheckBox(RECURSIVE_UPLOADER);
  private final JCheckBox _fuzzerScanCheck            = createOptionCheckBox(FUZZER);
  private final JCheckBox _dosScanCheck               = createOptionCheckBox(DOS);
  private final JCheckBox _addToLoggingChkBox         = createOptionCheckBox(ADD_TO_LOGGING);
  private final JCheckBox _replaceContentType         = createOptionCheckBox(REPLACE_CONTENT_TYPE);
  private final JCheckBox _replaceFileName            = createOptionCheckBox(REPLACE_FILE_NAME);
  private final JCheckBox _replaceFileSize            = createOptionCheckBox(REPLACE_FILE_SIZE);
  private final JCheckBox _wgetCurlPayloads           = createOptionCheckBox(WGET_CURL_LABEL);
  private JTextField _throttleValue;
  private JTextField _sleepTime;
  
  private int _mainHeight;
  
  ////////////////////////////////////////
  // PRIVATE METHODS //
  ////////////////////////////////////////
  private void addFileTypeListener() {
    addFileTypeListener(new ItemListener() {
      @Override
      public void itemStateChanged(ItemEvent e) {
        setFileTypesVisibility(e.getStateChange() == ItemEvent.SELECTED);
      }
    });
  }
  
  private void addScanCheckListener() {
    addScanCheckListener(new ItemListener() {
      @Override
      public void itemStateChanged(ItemEvent e) {
        setScanChecksVisibility(e.getStateChange() == ItemEvent.SELECTED);
      }
    });
  }
  
  // Adding UI Components to main JPanel
  private void addScanCheckOptions() {
    setScanChecksVisibility(false);
    
    scanCheckSection.add(_showScanChecks);
    scanCheckSection.add(_activescanScanCheck);
    scanCheckSection.add(_imagetragickScanCheck);
    scanCheckSection.add(_magickScanCheck);
    scanCheckSection.add(_gsScanCheck);
    scanCheckSection.add(_libavformatScanCheck);
    scanCheckSection.add(_phpScanCheck);
    scanCheckSection.add(_jspScanCheck);
    scanCheckSection.add(_aspScanCheck);
    scanCheckSection.add(_htaccessScanCheck);
    scanCheckSection.add(_cgiScanCheck);
    scanCheckSection.add(_ssiScanCheck);
    scanCheckSection.add(_xxeScanCheck);
    scanCheckSection.add(_xssScanCheck);
    scanCheckSection.add(_eicarScanCheck);
    scanCheckSection.add(_pdfScanCheck);
    scanCheckSection.add(_ssrfScanCheck);
    scanCheckSection.add(_csvScanCheck);
    scanCheckSection.add(_pathTraversalScanCheck);
    scanCheckSection.add(_polyglotScanCheck);
    scanCheckSection.add(_fingerpingScanCheck);
    scanCheckSection.add(_quirksScanCheck);
    scanCheckSection.add(_urlReplacerScanCheck);
    scanCheckSection.add(_recursiveUploaderScanCheck);
    scanCheckSection.add(_fuzzerScanCheck);
    scanCheckSection.add(_dosScanCheck);
    
    add(scanCheckSection);
  }
  
  private void addFileTypeOptions() {
    setFileTypesVisibility(false);
    
    fileTypeSection.add(_showFileType);
    fileTypeSection.add(_gifFileType);
    fileTypeSection.add(_pngFileType);
    fileTypeSection.add(_jpegFileType);
    fileTypeSection.add(_tiffFileType);
    fileTypeSection.add(_icoFileType);
    fileTypeSection.add(_svgFileType);
    fileTypeSection.add(_mvgFileType);
    fileTypeSection.add(_pdfFileType);
    fileTypeSection.add(_mp4FileType);
    fileTypeSection.add(_docxFileType);
    fileTypeSection.add(_xlsxFileType);
    fileTypeSection.add(_swfFileType);
    fileTypeSection.add(_csvFileType);
    fileTypeSection.add(_zipFileType);
    fileTypeSection.add(_gzipFileType);
    fileTypeSection.add(_htmlFileType);
    fileTypeSection.add(_xmlFileType);
    
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
    sectionPanel.setMaximumSize(new Dimension(STD_WIDTH, STD_HEIGHT * 51));
    sectionPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
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
