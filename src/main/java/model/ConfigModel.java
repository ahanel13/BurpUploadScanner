package model;

public class ConfigModel {

    public ConfigModel() {}

    public void setAddToLoggingTab(boolean addToLoggingTab) {
        this.addToLoggingTab = addToLoggingTab;
    }

    public void setThrottleTime(int throttleTime) {
        this.throttleTime = throttleTime;
    }

    public void setReplaceFileName(boolean replaceFileName) {
        this.replaceFileName = replaceFileName;
    }

    public void setReplaceContentType(boolean replaceContentType) {
        this.replaceContentType = replaceContentType;
    }

    public void setReplaceFileSize(boolean replaceFileSize) {
        this.replaceFileSize = replaceFileSize;
    }

    public void setSleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
    }

    public void setWgetCurlPayloads(boolean wgetCurlPayloads) {
        this.wgetCurlPayloads = wgetCurlPayloads;
    }
    // Logging Options
    private boolean addToLoggingTab;
    // Scanning Configs
    private int     throttleTime;
    private boolean replaceFileName;
    private boolean replaceContentType;
    private boolean replaceFileSize;
    // Payload Configs
    private int     sleepTime;
    private boolean wgetCurlPayloads;
}
