package model.scan;

import burp.api.montoya.http.message.HttpRequestResponse;
import model.utilities.RequestUtils;

import java.util.HashMap;
import java.util.Map;

public class ScanLog {

//-----------------------------------------------------------------------------
public void addLog(
    String fileName, HttpRequestResponse uploadRequest,
    HttpRequestResponse preflightRequest, HttpRequestResponse downloadRequest
) {
  String       fileHash = RequestUtils.getFileHash(uploadRequest);
  CheckHistory history  = new CheckHistory(
      fileName, fileHash, uploadRequest, preflightRequest, downloadRequest);
  historyMap.put(fileName, history);
}

//-----------------------------------------------------------------------------
public void addLog(
    String fileName, HttpRequestResponse uploadRequest,
    HttpRequestResponse downloadRequest
) {
  String       fileHash = RequestUtils.getFileHash(uploadRequest);
  CheckHistory history  = new CheckHistory(
      fileName, fileHash, uploadRequest, null, downloadRequest);
  historyMap.put(fileName, history);
}

private Map<String, CheckHistory> historyMap = new HashMap<>();

private record CheckHistory(
    String fileName,
    String fileHash,
    HttpRequestResponse uploadRequest,
    HttpRequestResponse preFlightRequest,
    HttpRequestResponse downloadRequest
){}

}
