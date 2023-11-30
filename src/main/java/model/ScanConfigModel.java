package model;

import burp.api.montoya.persistence.PersistedObject;
import model.utilities.ResourceLoader;

import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

public class ScanConfigModel implements Serializable {
    public ScanConfigModel(PersistedObject persistedObject) throws IOException {
        _persistedScanConfigModel = persistedObject.getChildObject(_SCAN_CONFIG_MODEL_KEY);
        _defaultProps             = ResourceLoader.loadPropertyFile(_SCAN_CONFIG_MODEL_PROPERTIES);

        // Check if there is data stored in persistence
        if (_persistedScanConfigModel != null) {
            // Retrieve values from persistence
            _addToLoggingTab    = persistedObject.getBoolean(_ADD_TO_LOGGING_TAB_KEY);
            _throttleTime       = persistedObject.getShort(_THROTTLE_TIME_KEY);
            _replaceFileName    = persistedObject.getBoolean(_REPLACE_FILE_NAME_KEY);
            _replaceContentType = persistedObject.getBoolean(_REPLACE_CONTENT_TYPE_KEY);
            _replaceFileSize    = persistedObject.getBoolean(_REPLACE_FILE_SIZE_KEY);
            _sleepTime          = persistedObject.getShort(_SLEEP_TIME_KEY);
            _wgetCurlPayloads   = persistedObject.getBoolean(_WGET_CURL_PAYLOADS_KEY);
        } else {
            setDefaultValues();
        }
    }

    public void setAddToLoggingTab(boolean addToLoggingTab) {
        this._addToLoggingTab = addToLoggingTab;
        _persistedScanConfigModel.setBoolean(_ADD_TO_LOGGING_TAB_KEY, this._addToLoggingTab);
    }

    public void setThrottleTime(short throttleTime) {
        this._throttleTime = throttleTime;
        _persistedScanConfigModel.setInteger(_THROTTLE_TIME_KEY, this._throttleTime);

    }

    public void setReplaceFileName(boolean replaceFileName) {
        this._replaceFileName = replaceFileName;
        _persistedScanConfigModel.setBoolean(_REPLACE_FILE_NAME_KEY, this._replaceFileName);
    }

    public void setReplaceContentType(boolean replaceContentType) {
        this._replaceContentType = replaceContentType;
        _persistedScanConfigModel.setBoolean(_REPLACE_CONTENT_TYPE_KEY, this._replaceContentType);

    }

    public void setReplaceFileSize(boolean replaceFileSize) {
        this._replaceFileSize = replaceFileSize;
        _persistedScanConfigModel.setBoolean(_REPLACE_FILE_SIZE_KEY, this._replaceFileSize);
    }

    public void setSleepTime(short sleepTime) {
        this._sleepTime = sleepTime;
        _persistedScanConfigModel.setShort(_SLEEP_TIME_KEY, this._sleepTime);
    }

    public void setWgetCurlPayloads(boolean wgetCurlPayloads) {
        this._wgetCurlPayloads = wgetCurlPayloads;
        _persistedScanConfigModel.setBoolean(_WGET_CURL_PAYLOADS_KEY, this._wgetCurlPayloads);
    }

    public boolean addToLoggingTab() {
        return _addToLoggingTab;
    }

    public short throttleTime() {
        return _throttleTime;
    }

    public boolean replaceFileName() {
        return _replaceFileName;
    }

    public boolean replaceContentType() {
        return _replaceContentType;
    }

    public boolean replaceFileSize() {
        return _replaceFileSize;
    }

    public short sleepTime() {
        return _sleepTime;
    }

    public boolean wgetCurlPayloads() {
        return _wgetCurlPayloads;
    }

    public void reset() throws IOException {
        setDefaultValues();
    }

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
    private final        PersistedObject _persistedScanConfigModel;
    private final        Properties      _defaultProps;

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

    private void setDefaultValues() {
        _addToLoggingTab    = Boolean.parseBoolean(_defaultProps.getProperty(_ADD_TO_LOGGING_TAB_KEY));
        _throttleTime       = Short.parseShort(_defaultProps.getProperty(_THROTTLE_TIME_KEY));
        _replaceFileName    = Boolean.parseBoolean(_defaultProps.getProperty(_REPLACE_FILE_NAME_KEY));
        _replaceContentType = Boolean.parseBoolean(_defaultProps.getProperty(_REPLACE_CONTENT_TYPE_KEY));
        _replaceFileSize    = Boolean.parseBoolean(_defaultProps.getProperty(_REPLACE_FILE_SIZE_KEY));
        _sleepTime          = Short.parseShort(_defaultProps.getProperty(_SLEEP_TIME_KEY));
        _wgetCurlPayloads   = Boolean.parseBoolean(_defaultProps.getProperty(_WGET_CURL_PAYLOADS_KEY));
    }
}
