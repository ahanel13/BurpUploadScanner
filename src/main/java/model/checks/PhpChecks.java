package model.checks;

import burp.api.montoya.MontoyaApi;
import burp.api.montoya.http.message.HttpRequestResponse;
import burp.api.montoya.http.message.requests.HttpRequest;
import burp.api.montoya.scanner.audit.issues.AuditIssue;
import burp.api.montoya.scanner.audit.issues.AuditIssueConfidence;
import burp.api.montoya.scanner.audit.issues.AuditIssueSeverity;
import model.factories.MultipartRequestFactory;
import model.scan.Downloader;
import model.scan.ScanLog;
import model.scan.ScanModel;
import model.scan.Sender;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static model.utilities.Constants.*;


public class PhpChecks extends UploaderCheck {
  ////////////////////////////////////////
  // PUBLIC FUNCTIONS
  ////////////////////////////////////////
  public PhpChecks(
      HttpRequestResponse response, MontoyaApi api, Downloader downloader,
      ScanLog scanLog
  ) {
    super(4, api, scanLog);
    _requestFactory = new MultipartRequestFactory(response.request());
    _downloader     = downloader;
  }

  //---------------------------------------------------------------------------
  @Override
  public void run() {
    try {
      // Basic RCE Check
      basicRceCheck();

      // Backdoor File Check
      rceBackdoorFileCheck();

      // PNG Metadata Check
      if (_scanModel.pngFileType())
        rcePngIdatChunkCheck();

      if (_scanModel.gifFileType())
        rceGifContentCheck();

      api.logging().logToOutput("Completed PHP Checks.");
    } catch (InterruptedException e){
      api.logging().logToOutput("PHP Scans Interrupted");
    }
    catch (ExecutionException e) {
      api.logging().logToError("Something went wrong.", e);
      throw new RuntimeException(e);
    }
  }

  ////////////////////////////////////////
  // PUBLIC METHODS
  ////////////////////////////////////////
  //---------------------------------------------------------------------------
  public void basicRceCheck() throws InterruptedException, ExecutionException {
    if(basicRceCheckCompleted)
      return; // EXIT HERE

    if(fileExts2Check.isEmpty())
      fileExts2Check = getFileExts(_downloader.getFileExtension());

    for (Iterator<String> iterator = fileExts2Check.iterator(); iterator.hasNext(); ) {
      String extension = iterator.next();
      for (String type : contentTypes) {

        // Handles if the scan had been stopped by user
        if (isInterrupted())
          return;

        String              newFilename       = getFilename("basicRceCheck", extension);
        String              randomStr         = getRandomStr();
        String              payload           = getPayload(randomStr);
        String              searchStr         = getSearchStr(randomStr);
        HttpRequest         request;
        boolean             isVulnerable;
        HttpRequestResponse reDownloadReqResp = null;

        if (type.equals(ORIGIN_CONT_TYPE))
          request = _requestFactory.getRequestWPayloadNFilename(payload, newFilename);
        else
          request = _requestFactory.getRequestWPayloadNFilenameNMime(payload, newFilename, type);

        // todo: store the file name and hash for exporting
        HttpRequestResponse requestResponse = new Sender(api, request).send();


        if (_downloader.isUsed()) {
          /* todo: update this to make a request to the filename minus
                   the fuzz string. so that ".php%00.png" works and downloader
                   will request ".php"
          */
          reDownloadReqResp = _downloader.download(newFilename);
          isVulnerable      = vulnerabilityPresent(reDownloadReqResp, searchStr);
        }
        else
          isVulnerable = vulnerabilityPresent(requestResponse, searchStr);


        if (isVulnerable) {
          report(request.url(), requestResponse, reDownloadReqResp, payload, searchStr);
        }

        scanLog.addLog(newFilename, requestResponse, reDownloadReqResp);
      }
      iterator.remove(); //remove extension list to check (for scan resume)
    }

    // prevents check from being restarted if completed and resumed
    basicRceCheckCompleted = true;
  }

  //---------------------------------------------------------------------------
  public void rceBackdoorFileCheck() {
    // todo: implement
  }

  //---------------------------------------------------------------------------
  /* Technique Source: PNG with payload in idat chunk that is PHP code taken from
   * https://www.idontplaydarts.com/2012/06/encoding-web-shells-in-png-idat-chunks/
   */
  public void rcePngIdatChunkCheck() {
    //todo: implement
    /* We simply assume that a server that is stripping *all* metadata cannot strip an idatchunk as it is part of the
     * image data (obviously) However, we could do other variations of the not-yet-deflated images, that when
     * transformed with imagecopyresize or imagecopyresample would even survive that. When implementing that, a
     * generic approach which allows resizing first to sizes self._image_formating_width, self
     * ._image_formating_height etc. */
  }

  //---------------------------------------------------------------------------
  public void rceGifContentCheck() {
    //todo: implement
  }

  //---------------------------------------------------------------------------
  public void setScanModel(ScanModel scanModel) {
    _scanModel = scanModel;
  }

  //---------------------------------------------------------------------------
  protected void report(
      String url, HttpRequestResponse reqResp, HttpRequestResponse downReqResp,
      String payload, String highlight
  ) {
    super.report(AuditIssue.auditIssue(
        "PHP RCE",
        "The payload `" + payload + "` was injected into the file upload. " +
        "The application rendered the payload as `" + highlight + "`",
        "Remediate",
        url,
        AuditIssueSeverity.HIGH,
        AuditIssueConfidence.CERTAIN,
        "",
        "",
        AuditIssueSeverity.HIGH,
        reqResp,
        super.highlightResponse(downReqResp, highlight)
    ));
  }

  ////////////////////////////////////////
  // PRIVATE FIELDS
  ////////////////////////////////////////
  private final MultipartRequestFactory _requestFactory;
  private final Downloader              _downloader;

  private List<String> fileExts2Check         = new ArrayList<>();
  private boolean      basicRceCheckCompleted = false;

  ////////////////////////////////////////
  // PRIVATE METHODS
  ////////////////////////////////////////
  //---------------------------------------------------------------------------
  private String getFilename(String checkName, String extension) {
    if (extension.equals(ORIGIN_FILE_EXT))
      return "php" + getRandomStr() + checkName + _downloader.getFileExtension();
    else
      return "php" + getRandomStr() + checkName + extension;
  }

  //---------------------------------------------------------------------------
  private static String getRandomStr() {
    final int DEFAULT_LENGTH = 10;
    return getRandomStr(DEFAULT_LENGTH);
  }

  //---------------------------------------------------------------------------
  private static String getRandomStr(int strLen) {
    final String       CHARACTERS     = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    final SecureRandom random         = new SecureRandom();

    StringBuilder sb = new StringBuilder(strLen);
    for (int i = 0; i < strLen; i++) {
      sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
    }
    return sb.toString();
  }

  //---------------------------------------------------------------------------
  private static String getPayload(String expectedStr) {
    return "<?php echo \"" + expectedStr + "-InJ\" . \"eCt.\".\"TeSt\";?>";
  }

  //---------------------------------------------------------------------------
  private static String getSearchStr(String expectedStr) {
    return expectedStr + "-InJeCt.TeSt";
  }

  //---------------------------------------------------------------------------
  private static List<String> getFileExts(String originalExt) {
    List<String> result = new ArrayList<>();

    result.add(originalExt);

    for (String extension : fileExtensions) {
      result.add(extension);
      result.add(originalExt + extension);

      for (String fuzzStr : EXTENSION_FUZZ_PAYLOADS) {
        result.add(extension + fuzzStr);
        result.add(fuzzStr + extension);
        result.add(extension + fuzzStr + originalExt);
      }
    }

    return result;
  }

  //---------------------------------------------------------------------------
  private static boolean vulnerabilityPresent(
      HttpRequestResponse responseResponse, String searchStr)
  {
    return responseResponse.response().toString().contains(searchStr);
  }

  ////////////////////////////////////////
  // PRIVATE FIELDS
  ////////////////////////////////////////
  private static final List<String> fileExtensions = List.of(
      ".php", ".php5", ".phtml");
  private static final List<String> contentTypes   = List.of(
      ORIGIN_CONT_TYPE, "application/x-php", "application/octet-stream");

  private ScanModel _scanModel;
}
