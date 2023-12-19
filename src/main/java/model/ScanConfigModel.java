package model;

import burp.api.montoya.persistence.PersistedObject;
import model.utilities.ResourceLoader;

import java.io.IOException;
import java.util.Properties;

public class ScanConfigModel implements Cloneable{
  // PUBLIC FUNCTIONS //
  public ScanConfigModel(PersistedObject persistedObject) throws IOException{
    _extensionData            = persistedObject;
    _persistedScanConfigModel = persistedObject.getChildObject(_SCAN_CONFIG_MODEL_KEY);
    _defaultProps             = ResourceLoader.loadPropertyFile(_SCAN_CONFIG_MODEL_PROPERTIES);
    
    // Check if there is data stored in persistence
    if(_persistedScanConfigModel != null){
      // Retrieve values from persistence
      _addToLoggingTab    = _persistedScanConfigModel.getBoolean(_ADD_TO_LOGGING_TAB_KEY);
      _replaceFileName    = _persistedScanConfigModel.getBoolean(_REPLACE_FILE_NAME_KEY);
      _replaceContentType = _persistedScanConfigModel.getBoolean(_REPLACE_CONTENT_TYPE_KEY);
      _replaceFileSize    = _persistedScanConfigModel.getBoolean(_REPLACE_FILE_SIZE_KEY);
      _wgetCurlPayloads   = _persistedScanConfigModel.getBoolean(_WGET_CURL_PAYLOADS_KEY);
      _throttleTime       = _persistedScanConfigModel.getShort(_THROTTLE_TIME_KEY);
      _sleepTime          = _persistedScanConfigModel.getShort(_SLEEP_TIME_KEY);
    }
    else{
      _persistedScanConfigModel = PersistedObject.persistedObject();
      setDefaultValues();
    }
  }
  
// PUBLIC METHODS //
  @Override
  public ScanConfigModel clone(){
    try{
      // class only has primitives, essentially a deep copy
      
      return (ScanConfigModel) super.clone();
    }
    catch(CloneNotSupportedException e){
      throw new AssertionError();
    }
  }
  
  public void persist(){
    _persistedScanConfigModel.setBoolean(_ADD_TO_LOGGING_TAB_KEY, this._addToLoggingTab);
    _persistedScanConfigModel.setBoolean(_REPLACE_FILE_NAME_KEY, this._replaceFileName);
    _persistedScanConfigModel.setBoolean(_REPLACE_CONTENT_TYPE_KEY, this._replaceContentType);
    _persistedScanConfigModel.setBoolean(_REPLACE_FILE_SIZE_KEY, this._replaceFileSize);
    _persistedScanConfigModel.setBoolean(_WGET_CURL_PAYLOADS_KEY, this._wgetCurlPayloads);
    _persistedScanConfigModel.setShort(_THROTTLE_TIME_KEY, this._throttleTime);
    _persistedScanConfigModel.setShort(_SLEEP_TIME_KEY, this._sleepTime);
    _extensionData.setChildObject(_SCAN_CONFIG_MODEL_KEY ,_persistedScanConfigModel);
  }
  
  public void setAddToLoggingTab(boolean addToLoggingTab){
    this._addToLoggingTab = addToLoggingTab;
  }
  
  public void setThrottleTime(short throttleTime){
    this._throttleTime = throttleTime;
  }
  
  public void setReplaceFileName(boolean replaceFileName){
    this._replaceFileName = replaceFileName;
  }
  
  public void setReplaceContentType(boolean replaceContentType){
    this._replaceContentType = replaceContentType;
  }
  
  public void setReplaceFileSize(boolean replaceFileSize){
    this._replaceFileSize = replaceFileSize;
  }
  
  public void setSleepTime(short sleepTime){
    this._sleepTime = sleepTime;
  }
  
  public void setWgetCurlPayloads(boolean wgetCurlPayloads){
    this._wgetCurlPayloads = wgetCurlPayloads;
  }
  
  public boolean addToLoggingTab()   {return _addToLoggingTab;}
  
  public short throttleTime()        {return _throttleTime;}
  
  public boolean replaceFileName()   {return _replaceFileName;}
  
  public boolean replaceContentType(){return _replaceContentType;}
  
  public boolean replaceFileSize()   {return _replaceFileSize;}
  
  public short sleepTime()           {return _sleepTime;}
  
  public boolean wgetCurlPayloads()  {return _wgetCurlPayloads;}
  
  public void reset() throws IOException{
    setDefaultValues();
  }
  
// PRIVATE DATA //
  // Constants for magic strings
  private static final String          _ADD_TO_LOGGING_TAB_KEY       = "add.to.logging.tab";
  private static final String          _THROTTLE_TIME_KEY            = "throttle.time";
  private static final String          _REPLACE_FILE_NAME_KEY        = "replace.file.name";
  private static final String          _REPLACE_CONTENT_TYPE_KEY     = "replace.content.type";
  private static final String          _REPLACE_FILE_SIZE_KEY        = "replace.file.size";
  private static final String          _SLEEP_TIME_KEY               = "sleep.time";
  private static final String          _WGET_CURL_PAYLOADS_KEY       = "wget.curl.payloads";
  private static final String          _SCAN_CONFIG_MODEL_KEY        = "scan.config.model";
  private static final String          _SCAN_CONFIG_MODEL_PROPERTIES = "ScanConfigModel.properties";
  private final        PersistedObject _extensionData;
  private final        Properties      _defaultProps;
  private              PersistedObject _persistedScanConfigModel;
  
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
  
// PRIVATE METHODS //
  private void setDefaultValues(){
    _addToLoggingTab    = Boolean.parseBoolean(_defaultProps.getProperty(_ADD_TO_LOGGING_TAB_KEY));
    _throttleTime       = Short.parseShort(_defaultProps.getProperty(_THROTTLE_TIME_KEY));
    _replaceFileName    = Boolean.parseBoolean(_defaultProps.getProperty(_REPLACE_FILE_NAME_KEY));
    _replaceContentType = Boolean.parseBoolean(_defaultProps.getProperty(_REPLACE_CONTENT_TYPE_KEY));
    _replaceFileSize    = Boolean.parseBoolean(_defaultProps.getProperty(_REPLACE_FILE_SIZE_KEY));
    _sleepTime          = Short.parseShort(_defaultProps.getProperty(_SLEEP_TIME_KEY));
    _wgetCurlPayloads   = Boolean.parseBoolean(_defaultProps.getProperty(_WGET_CURL_PAYLOADS_KEY));
  }
}
