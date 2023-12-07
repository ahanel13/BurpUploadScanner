package model;

import burp.api.montoya.MontoyaApi;
import burp.api.montoya.persistence.PersistedObject;
import model.utilities.BurpPersistedObject;
import model.utilities.ResourceLoader;

import java.io.IOException;
import java.util.Properties;

public class ScanConfigModel extends BurpPersistedObject {
    public ScanConfigModel(MontoyaApi api, PersistedObject persistedObject) throws Exception {
        super(api, _SCAN_CONFIG_MODEL_KEY);
        _defaultProps             = ResourceLoader.loadPropertyFile(_SCAN_CONFIG_MODEL_PROPERTIES);

        try {
            _retrieve();
        } catch (Exception e){
            setDefaultValues();
        }
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
