package model;

import burp.api.montoya.persistence.PersistedObject;
import model.utilities.ResourceLoader;

import java.io.IOException;
import java.util.Properties;

public class BaseConfigModel implements Cloneable {
  ////////////////////////////////////////
  // PUBLIC FUNCTIONS
  ////////////////////////////////////////
  public BaseConfigModel(PersistedObject persistedObject) throws IOException {
    _extensionData            = persistedObject;
    _persistedScanConfigModel = persistedObject.getChildObject(_SCAN_CONFIG_MODEL_KEY);
    _defaultProps             = ResourceLoader.loadPropertyFile(_SCAN_CONFIG_MODEL_PROPERTIES);

    // Check if there is data stored in persistence
    if (_persistedScanConfigModel != null) {
      // Retrieve values from persistence
      _addToLoggingTab    = _persistedScanConfigModel.getBoolean(_ADD_TO_LOGGING_TAB_KEY);
      _replaceFileName    = _persistedScanConfigModel.getBoolean(_REPLACE_FILE_NAME_KEY);
      _replaceContentType = _persistedScanConfigModel.getBoolean(_REPLACE_CONTENT_TYPE_KEY);
      _replaceFileSize    = _persistedScanConfigModel.getBoolean(_REPLACE_FILE_SIZE_KEY);
      _wgetCurlPayloads   = _persistedScanConfigModel.getBoolean(_WGET_CURL_PAYLOADS_KEY);
      _throttleTime       = _persistedScanConfigModel.getShort(_THROTTLE_TIME_KEY);
      _sleepTime          = _persistedScanConfigModel.getShort(_SLEEP_TIME_KEY);
    }
    else {
      _persistedScanConfigModel = PersistedObject.persistedObject();
      setDefaultValues();
    }
    setDefaultValues(); //todo: update persistence for the entire class
  }

  ////////////////////////////////////////
  // PUBLIC METHODS
  ////////////////////////////////////////
  @Override
  public BaseConfigModel clone() {
    try {
      // class only has primitives, essentially a deep copy
      return (BaseConfigModel) super.clone();
    }
    catch (CloneNotSupportedException e) {
      throw new AssertionError();
    }
  }

  public void persist() {
    _persistedScanConfigModel.setBoolean(_ADD_TO_LOGGING_TAB_KEY, this._addToLoggingTab);
    _persistedScanConfigModel.setBoolean(_REPLACE_FILE_NAME_KEY, this._replaceFileName);
    _persistedScanConfigModel.setBoolean(_REPLACE_CONTENT_TYPE_KEY, this._replaceContentType);
    _persistedScanConfigModel.setBoolean(_REPLACE_FILE_SIZE_KEY, this._replaceFileSize);
    _persistedScanConfigModel.setBoolean(_WGET_CURL_PAYLOADS_KEY, this._wgetCurlPayloads);
    _persistedScanConfigModel.setShort(_THROTTLE_TIME_KEY, this._throttleTime);
    _persistedScanConfigModel.setShort(_SLEEP_TIME_KEY, this._sleepTime);
    _extensionData.setChildObject(_SCAN_CONFIG_MODEL_KEY, _persistedScanConfigModel);
  }

  // SETTERS
  public void setAddToLoggingTab(boolean b) {this._addToLoggingTab = b;}
  public void setThrottleTime(short s)             {this._throttleTime = s;}
  public void setReplaceFileName(boolean b)        {this._replaceFileName = b;}
  public void setReplaceContentType(boolean b)     {this._replaceContentType = b;}
  public void setReplaceFileSize(boolean b)        {this._replaceFileSize = b;}
  public void setSleepTime(short s)                {this._sleepTime = s;}
  public void setWgetCurlPayloads(boolean b)       {this._wgetCurlPayloads = b;}
  public void setGifFileType(boolean b)            {this._gifFileType = b;}
  public void setPngFileType(boolean b)            {this._pngFileType = b;}
  public void setJpegFileType(boolean b)           {this._jpegFileType = b;}
  public void setTiffFileType(boolean b)           {this._tiffFileType = b;}
  public void setIcoFileType(boolean b)            {this._icoFileType = b;}
  public void setSvgFileType(boolean b)            {this._svgFileType = b;}
  public void setMvgFileType(boolean b)            {this._mvgFileType = b;}
  public void setPdfFileType(boolean b)            {this._pdfFileType = b;}
  public void setMp4FileType(boolean b)            {this._mp4FileType = b;}
  public void setDocxFileType(boolean b)           {this._docxFileType = b;}
  public void setXlsxFileType(boolean b)           {this._xlsxFileType = b;}
  public void setSwfFileType(boolean b)            {this._swfFileType = b;}
  public void setCsvFileType(boolean b)            {this._csvFileType = b;}
  public void setZipFileType(boolean b)            {this._zipFileType = b;}
  public void setGzipFileType(boolean b)           {this._gzipFileType = b;}
  public void setHtmlFileType(boolean b)           {this._htmlFileType = b;}
  public void setXmlFileType(boolean b)            {this._xmlFileType = b;}
  public void setActivescanScanCheck(boolean b)    {this._activescanScanCheck = b;}
  public void setImagetragickScanCheck(boolean b)  {this._imagetragickScanCheck = b;}
  public void setMagickScanCheck(boolean b)        {this._magickScanCheck = b;}
  public void setGsScanCheck(boolean b)            {this._gsScanCheck = b;}
  public void setLibavformatScanCheck(boolean b)   {this._libavformatScanCheck = b;}
  public void setPhpScanCheck(boolean b)           {this._phpScanCheck = b;}
  public void setJspScanCheck(boolean b)           {this._jspScanCheck = b;}
  public void setAspScanCheck(boolean b)           {this._aspScanCheck = b;}
  public void setHtaccessScanCheck(boolean b)      {this._htaccessScanCheck = b;}
  public void setCgiScanCheck(boolean b)           {this._cgiScanCheck = b;}
  public void setSsiScanCheck(boolean b)           {this._ssiScanCheck = b;}
  public void setXxeScanCheck(boolean b)           {this._xxeScanCheck = b;}
  public void setXssScanCheck(boolean b)           {this._xssScanCheck = b;}
  public void setEicarScanCheck(boolean b)         {this._eicarScanCheck = b;}
  public void setPdfInjectionScanCheck(boolean b)  {this._pdfInjectionScanCheck = b;}
  public void setSsrfScanCheck(boolean b)          {this._ssrfScanCheck = b;}
  public void setCsvInjectionScanCheck(boolean b)  {this._csvInjectionScanCheck = b;}
  public void setPathTraversalScanCheck(boolean b) {this._pathTraversalScanCheck = b;}
  public void setPolyglotScanCheck(boolean b)      {this._polyglotScanCheck = b;}
  public void setFingerpingScanCheck(boolean b)    {this._fingerpingScanCheck = b;}
  public void setQuirksScanCheck(boolean b)        {this._quirksScanCheck = b;}
  public void setUrlReplacerScanCheck(boolean b)   {this._urlReplacerScanCheck = b;}
  public void setRecursiveUploader(boolean b)      {this._recursiveUploaderScanCheck = b;}
  public void setFuzzerScanCheck(boolean b)        {this._fuzzerScanCheck = b;}
  public void setDosScanCheck(boolean b)           {this._dosScanCheck = b;}

  // GETTERS
  public boolean addToLoggingTab() {return _addToLoggingTab;}
  public short throttleTime()                 {return _throttleTime;}
  public boolean replaceFileName()            {return _replaceFileName;}
  public boolean replaceContentType()         {return _replaceContentType;}
  public boolean replaceFileSize()            {return _replaceFileSize;}
  public short sleepTime()                    {return _sleepTime;}
  public boolean wgetCurlPayloads()           {return _wgetCurlPayloads;}
  public boolean gifFileType()                {return _gifFileType;}
  public boolean pngFileType()                {return _pngFileType;}
  public boolean jpegFileType()               {return _jpegFileType;}
  public boolean tiffFileType()               {return _tiffFileType;}
  public boolean icoFileType()                {return _icoFileType;}
  public boolean svgFileType()                {return _svgFileType;}
  public boolean mvgFileType()                {return _mvgFileType;}
  public boolean pdfFileType()                {return _pdfFileType;}
  public boolean mp4FileType()                {return _mp4FileType;}
  public boolean docxFileType()               {return _docxFileType;}
  public boolean xlsxFileType()               {return _xlsxFileType;}
  public boolean swfFileType()                {return _swfFileType;}
  public boolean csvFileType()                {return _csvFileType;}
  public boolean zipFileType()                {return _zipFileType;}
  public boolean gzipFileType()               {return _gzipFileType;}
  public boolean htmlFileType()               {return _htmlFileType;}
  public boolean xmlFileType()                {return _xmlFileType;}
  public boolean activescanScanCheck()        {return _activescanScanCheck;}
  public boolean imagetragickScanCheck()      {return _imagetragickScanCheck;}
  public boolean magickScanCheck()            {return _magickScanCheck;}
  public boolean gsScanCheck()                {return _gsScanCheck;}
  public boolean libavformatScanCheck()       {return _libavformatScanCheck;}
  public boolean phpScanCheck()               {return _phpScanCheck;}
  public boolean jspScanCheck()               {return _jspScanCheck;}
  public boolean aspScanCheck()               {return _aspScanCheck;}
  public boolean htaccessScanCheck()          {return _htaccessScanCheck;}
  public boolean cgiScanCheck()               {return _cgiScanCheck;}
  public boolean ssiScanCheck()               {return _ssiScanCheck;}
  public boolean xxeScanCheck()               {return _xxeScanCheck;}
  public boolean xssScanCheck()               {return _xssScanCheck;}
  public boolean eicarScanCheck()             {return _eicarScanCheck;}
  public boolean pdfInjectionScanCheck()      {return _pdfInjectionScanCheck;}
  public boolean ssrfScanCheck()              {return _ssrfScanCheck;}
  public boolean csvInjectionScanCheck()      {return _csvInjectionScanCheck;}
  public boolean pathTraversalScanCheck()     {return _pathTraversalScanCheck;}
  public boolean polyglotScanCheck()          {return _polyglotScanCheck;}
  public boolean fingerpingScanCheck()        {return _fingerpingScanCheck;}
  public boolean quirksScanCheck()            {return _quirksScanCheck;}
  public boolean urlReplacerScanCheck()       {return _urlReplacerScanCheck;}
  public boolean recursiveUploaderScanCheck() {return _recursiveUploaderScanCheck;}
  public boolean fuzzerScanCheck()            {return _fuzzerScanCheck;}
  public boolean dosScanCheck()               {return _dosScanCheck;}

  public void reset() throws IOException {
    setDefaultValues();
  }

  ////////////////////////////////////////
  // PRIVATE FIELDS
  ////////////////////////////////////////
  // Constants for magic strings
  private static final String _ADD_TO_LOGGING_TAB_KEY       = "add.to.logging.tab";
  private static final String _THROTTLE_TIME_KEY            = "throttle.time";
  private static final String _REPLACE_FILE_NAME_KEY        = "replace.file.name";
  private static final String _REPLACE_CONTENT_TYPE_KEY     = "replace.content.type";
  private static final String _REPLACE_FILE_SIZE_KEY        = "replace.file.size";
  private static final String _SLEEP_TIME_KEY               = "sleep.time";
  private static final String _WGET_CURL_PAYLOADS_KEY       = "wget.curl.payloads";
  private static final String _SCAN_CONFIG_MODEL_KEY        = "scan.config.model";
  private static final String _SCAN_CONFIG_MODEL_PROPERTIES = "BaseConfigModel.properties";

  // Constants for file type keys
  private static final String GIF_FILE_TYPE_KEY  = "gif.file.type";
  private static final String PNG_FILE_TYPE_KEY  = "png.file.type";
  private static final String JPEG_FILE_TYPE_KEY = "jpeg.file.type";
  private static final String TIFF_FILE_TYPE_KEY = "tiff.file.type";
  private static final String ICO_FILE_TYPE_KEY  = "ico.file.type";
  private static final String SVG_FILE_TYPE_KEY  = "svg.file.type";
  private static final String MVG_FILE_TYPE_KEY  = "mvg.file.type";
  private static final String PDF_FILE_TYPE_KEY  = "pdf.file.type";
  private static final String MP4_FILE_TYPE_KEY  = "mp4.file.type";
  private static final String DOCX_FILE_TYPE_KEY = "docx.file.type";
  private static final String XLSX_FILE_TYPE_KEY = "xlsx.file.type";
  private static final String SWF_FILE_TYPE_KEY  = "swf.file.type";
  private static final String CSV_FILE_TYPE_KEY  = "csv.file.type";
  private static final String ZIP_FILE_TYPE_KEY  = "zip.file.type";
  private static final String GZIP_FILE_TYPE_KEY = "gzip.file.type";
  private static final String HTML_FILE_TYPE_KEY = "html.file.type";
  private static final String XML_FILE_TYPE_KEY  = "xml.file.type";

  // Constants for scan check keys
  private static final String ACTIVESCAN_SCAN_CHECK_KEY         = "activescan.scan.check";
  private static final String IMAGETRAGICK_SCAN_CHECK_KEY       = "imagetragick.scan.check";
  private static final String MAGICK_SCAN_CHECK_KEY             = "magick.scan.check";
  private static final String GS_SCAN_CHECK_KEY                 = "gs.scan.check";
  private static final String LIBAVFORMAT_SCAN_CHECK_KEY        = "libavformat.scan.check";
  private static final String PHP_SCAN_CHECK_KEY                = "php.scan.check";
  private static final String JSP_SCAN_CHECK_KEY                = "jsp.scan.check";
  private static final String ASP_SCAN_CHECK_KEY                = "asp.scan.check";
  private static final String HTACCESS_SCAN_CHECK_KEY           = "htaccess.scan.check";
  private static final String CGI_SCAN_CHECK_KEY                = "cgi.scan.check";
  private static final String SSI_SCAN_CHECK_KEY                = "ssi.scan.check";
  private static final String XXE_SCAN_CHECK_KEY                = "xxe.scan.check";
  private static final String XSS_SCAN_CHECK_KEY                = "xss.scan.check";
  private static final String EICAR_SCAN_CHECK_KEY              = "eicar.scan.check";
  private static final String PDF_INJECTION_SCAN_CHECK_KEY      = "pdf.injection.scan.check";
  private static final String SSRF_SCAN_CHECK_KEY               = "ssrf.scan.check";
  private static final String CSV_INJECTION_SCAN_CHECK_KEY      = "csv.injection.scan.check";
  private static final String PATH_TRAVERSAL_SCAN_CHECK_KEY     = "path.traversal.scan.check";
  private static final String POLYGLOT_SCAN_CHECK_KEY           = "polyglot.scan.check";
  private static final String FINGERPING_SCAN_CHECK_KEY         = "fingerping.scan.check";
  private static final String QUIRKS_SCAN_CHECK_KEY             = "quirks.scan.check";
  private static final String URL_REPLACER_SCAN_CHECK_KEY       = "url.replacer.scan.check";
  private static final String RECURSIVE_UPLOADER_SCAN_CHECK_KEY = "recursive.uploader.scan.check";
  private static final String FUZZER_SCAN_CHECK_KEY             = "fuzzer.scan.check";
  private static final String DOS_SCAN_CHECK_KEY                = "dos.scan.check";

  private final PersistedObject _extensionData;
  private final Properties      _defaultProps;
  private       PersistedObject _persistedScanConfigModel;

  // Logging Options
  private boolean _addToLoggingTab;

  // Scanning Configs
  private short   _throttleTime;
  private boolean _replaceFileName;
  private boolean _replaceContentType;
  private boolean _replaceFileSize;

  // Payload Configs
  private short   _sleepTime;
  private boolean _wgetCurlPayloads;

  // File Types
  private boolean _gifFileType;
  private boolean _pngFileType;
  private boolean _jpegFileType;
  private boolean _tiffFileType;
  private boolean _icoFileType;
  private boolean _svgFileType;
  private boolean _mvgFileType;
  private boolean _pdfFileType;
  private boolean _mp4FileType;
  private boolean _docxFileType;
  private boolean _xlsxFileType;
  private boolean _swfFileType;
  private boolean _csvFileType;
  private boolean _zipFileType;
  private boolean _gzipFileType;
  private boolean _htmlFileType;
  private boolean _xmlFileType;

  // Scan Checks
  private boolean _activescanScanCheck;
  private boolean _imagetragickScanCheck;
  private boolean _magickScanCheck;
  private boolean _gsScanCheck;
  private boolean _libavformatScanCheck;
  private boolean _phpScanCheck;
  private boolean _jspScanCheck;
  private boolean _aspScanCheck;
  private boolean _htaccessScanCheck;
  private boolean _cgiScanCheck;
  private boolean _ssiScanCheck;
  private boolean _xxeScanCheck;
  private boolean _xssScanCheck;
  private boolean _eicarScanCheck;
  private boolean _pdfInjectionScanCheck;
  private boolean _ssrfScanCheck;
  private boolean _csvInjectionScanCheck;
  private boolean _pathTraversalScanCheck;
  private boolean _polyglotScanCheck;
  private boolean _fingerpingScanCheck;
  private boolean _quirksScanCheck;
  private boolean _urlReplacerScanCheck;
  private boolean _recursiveUploaderScanCheck;
  private boolean _fuzzerScanCheck;
  private boolean _dosScanCheck;

  ////////////////////////////////////////
  // PRIVATE METHODS
  ////////////////////////////////////////
  private void setDefaultValues() {
    _addToLoggingTab    = Boolean.parseBoolean(_defaultProps.getProperty(_ADD_TO_LOGGING_TAB_KEY));
    _throttleTime       = Short.parseShort(_defaultProps.getProperty(_THROTTLE_TIME_KEY));
    _replaceFileName    = Boolean.parseBoolean(_defaultProps.getProperty(_REPLACE_FILE_NAME_KEY));
    _replaceContentType = Boolean.parseBoolean(_defaultProps.getProperty(_REPLACE_CONTENT_TYPE_KEY));
    _replaceFileSize    = Boolean.parseBoolean(_defaultProps.getProperty(_REPLACE_FILE_SIZE_KEY));
    _sleepTime          = Short.parseShort(_defaultProps.getProperty(_SLEEP_TIME_KEY));
    _wgetCurlPayloads   = Boolean.parseBoolean(_defaultProps.getProperty(_WGET_CURL_PAYLOADS_KEY));

    // Initialize file type booleans
    _gifFileType  = Boolean.parseBoolean(_defaultProps.getProperty(GIF_FILE_TYPE_KEY));
    _pngFileType  = Boolean.parseBoolean(_defaultProps.getProperty(PNG_FILE_TYPE_KEY));
    _jpegFileType = Boolean.parseBoolean(_defaultProps.getProperty(JPEG_FILE_TYPE_KEY));
    _tiffFileType = Boolean.parseBoolean(_defaultProps.getProperty(TIFF_FILE_TYPE_KEY));
    _icoFileType  = Boolean.parseBoolean(_defaultProps.getProperty(ICO_FILE_TYPE_KEY));
    _svgFileType  = Boolean.parseBoolean(_defaultProps.getProperty(SVG_FILE_TYPE_KEY));
    _mvgFileType  = Boolean.parseBoolean(_defaultProps.getProperty(MVG_FILE_TYPE_KEY));
    _pdfFileType  = Boolean.parseBoolean(_defaultProps.getProperty(PDF_FILE_TYPE_KEY));
    _mp4FileType  = Boolean.parseBoolean(_defaultProps.getProperty(MP4_FILE_TYPE_KEY));
    _docxFileType = Boolean.parseBoolean(_defaultProps.getProperty(DOCX_FILE_TYPE_KEY));
    _xlsxFileType = Boolean.parseBoolean(_defaultProps.getProperty(XLSX_FILE_TYPE_KEY));
    _swfFileType  = Boolean.parseBoolean(_defaultProps.getProperty(SWF_FILE_TYPE_KEY));
    _csvFileType  = Boolean.parseBoolean(_defaultProps.getProperty(CSV_FILE_TYPE_KEY));
    _zipFileType  = Boolean.parseBoolean(_defaultProps.getProperty(ZIP_FILE_TYPE_KEY));
    _gzipFileType = Boolean.parseBoolean(_defaultProps.getProperty(GZIP_FILE_TYPE_KEY));
    _htmlFileType = Boolean.parseBoolean(_defaultProps.getProperty(HTML_FILE_TYPE_KEY));
    _xmlFileType  = Boolean.parseBoolean(_defaultProps.getProperty(XML_FILE_TYPE_KEY));

    // Initialize scan check booleans
    _activescanScanCheck        = Boolean.parseBoolean(_defaultProps.getProperty(ACTIVESCAN_SCAN_CHECK_KEY));
    _imagetragickScanCheck      = Boolean.parseBoolean(_defaultProps.getProperty(IMAGETRAGICK_SCAN_CHECK_KEY));
    _magickScanCheck            = Boolean.parseBoolean(_defaultProps.getProperty(MAGICK_SCAN_CHECK_KEY));
    _gsScanCheck                = Boolean.parseBoolean(_defaultProps.getProperty(GS_SCAN_CHECK_KEY));
    _libavformatScanCheck       = Boolean.parseBoolean(_defaultProps.getProperty(LIBAVFORMAT_SCAN_CHECK_KEY));
    _phpScanCheck               = Boolean.parseBoolean(_defaultProps.getProperty(PHP_SCAN_CHECK_KEY));
    _jspScanCheck               = Boolean.parseBoolean(_defaultProps.getProperty(JSP_SCAN_CHECK_KEY));
    _aspScanCheck               = Boolean.parseBoolean(_defaultProps.getProperty(ASP_SCAN_CHECK_KEY));
    _htaccessScanCheck          = Boolean.parseBoolean(_defaultProps.getProperty(HTACCESS_SCAN_CHECK_KEY));
    _cgiScanCheck               = Boolean.parseBoolean(_defaultProps.getProperty(CGI_SCAN_CHECK_KEY));
    _ssiScanCheck               = Boolean.parseBoolean(_defaultProps.getProperty(SSI_SCAN_CHECK_KEY));
    _xxeScanCheck               = Boolean.parseBoolean(_defaultProps.getProperty(XXE_SCAN_CHECK_KEY));
    _xssScanCheck               = Boolean.parseBoolean(_defaultProps.getProperty(XSS_SCAN_CHECK_KEY));
    _eicarScanCheck             = Boolean.parseBoolean(_defaultProps.getProperty(EICAR_SCAN_CHECK_KEY));
    _pdfInjectionScanCheck      = Boolean.parseBoolean(_defaultProps.getProperty(PDF_INJECTION_SCAN_CHECK_KEY));
    _ssrfScanCheck              = Boolean.parseBoolean(_defaultProps.getProperty(SSRF_SCAN_CHECK_KEY));
    _csvInjectionScanCheck      = Boolean.parseBoolean(_defaultProps.getProperty(CSV_INJECTION_SCAN_CHECK_KEY));
    _pathTraversalScanCheck     = Boolean.parseBoolean(_defaultProps.getProperty(PATH_TRAVERSAL_SCAN_CHECK_KEY));
    _polyglotScanCheck          = Boolean.parseBoolean(_defaultProps.getProperty(POLYGLOT_SCAN_CHECK_KEY));
    _fingerpingScanCheck        = Boolean.parseBoolean(_defaultProps.getProperty(FINGERPING_SCAN_CHECK_KEY));
    _quirksScanCheck            = Boolean.parseBoolean(_defaultProps.getProperty(QUIRKS_SCAN_CHECK_KEY));
    _urlReplacerScanCheck       = Boolean.parseBoolean(_defaultProps.getProperty(URL_REPLACER_SCAN_CHECK_KEY));
    _recursiveUploaderScanCheck = Boolean.parseBoolean(_defaultProps.getProperty(RECURSIVE_UPLOADER_SCAN_CHECK_KEY));
    _fuzzerScanCheck            = Boolean.parseBoolean(_defaultProps.getProperty(FUZZER_SCAN_CHECK_KEY));
    _dosScanCheck               = Boolean.parseBoolean(_defaultProps.getProperty(DOS_SCAN_CHECK_KEY));
  }
}
