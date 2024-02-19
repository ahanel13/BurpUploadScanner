package model.checks;

import burp.api.montoya.MontoyaApi;
import burp.api.montoya.http.message.HttpRequestResponse;
import burp.api.montoya.http.message.requests.HttpRequest;
import burp.api.montoya.scanner.audit.issues.AuditIssue;
import burp.api.montoya.scanner.audit.issues.AuditIssueConfidence;
import burp.api.montoya.scanner.audit.issues.AuditIssueSeverity;
import model.Downloader;
import model.Sender;
import model.utilities.MultipartRequestFactory;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import static model.utilities.Constants.*;

public class PhpChecks {
  ////////////////////////////////////////
  // PUBLIC FUNCTIONS
  ////////////////////////////////////////
  public PhpChecks(
      MontoyaApi api, MultipartRequestFactory reqFactory, Downloader downloader
  ) {
    _api            = api;
    _requestFactory = reqFactory;
    _downloader     = downloader;
  }

  ////////////////////////////////////////
  // PUBLIC METHODS
  ////////////////////////////////////////
  public List<AuditIssue> basicRceCheck() {
    List<AuditIssue> issue = new ArrayList<>();
    boolean          isVulnerable;

    for (String extension : getFileExts()) {
      for (String type : contentTypes) {
        String      newFilename = getFilename("basicRceCheck", extension);
        String      randomStr   = getRandomStr();
        String      payload     = getPayload(randomStr);
        String      searchStr   = getSearchStr(randomStr);
        HttpRequest request;

        if (type.equals(ORIGIN_CONT_TYPE))
          request = _requestFactory.getRequestWPayloadNFilename(payload, newFilename);
        else
          request = _requestFactory.getRequestWPayloadNFilenameNMime(payload, newFilename, type);

        HttpRequestResponse requestResponse = new Sender(_api, request).send();

        if (_downloader.isUsed()) {
          /* todo: update this to make a request to the filename minus
                   the fuzz string. so that ".php%00.png" works and downloader
                   will request ".php"
          */
          HttpRequestResponse reDownloadReqResp = _downloader.download(newFilename);
          isVulnerable = vulnerabilityPresent(reDownloadReqResp, searchStr);
        }
        else {
          isVulnerable = vulnerabilityPresent(requestResponse, searchStr);
        }

        if (isVulnerable) {
          issue.add(AuditIssue.auditIssue(
              "PHP RCE",
              "PHP RCE",
              "Rem",
              request.url(),
              AuditIssueSeverity.HIGH,
              AuditIssueConfidence.CERTAIN,
              "",
              "",
              AuditIssueSeverity.HIGH,
              requestResponse,
              requestResponse
          ));
        }
      }
    }
    return issue;
  }

  ////////////////////////////////////////
  // PRIVATE METHODS
  ////////////////////////////////////////
  private String getFilename(String checkName, String extension) {
    if (extension.equals(ORIGIN_FILE_EXT))
      return "php" + getRandomStr() + checkName + _downloader.getFileExtension();
    else
      return "php" + getRandomStr() + checkName + extension;
  }

  private static String getRandomStr() {
    final int DEFAULT_LENGTH = 10;
    return getRandomStr(DEFAULT_LENGTH);
  }

  private static String getRandomStr(int strLen) {
    final String       CHARACTERS     = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    final SecureRandom random         = new SecureRandom();

    StringBuilder sb = new StringBuilder(strLen);
    for (int i = 0; i < strLen; i++) {
      sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
    }
    return sb.toString();
  }

  private static String getPayload(String expectedStr) {
    return "<?php echo \"" + expectedStr + "-InJ\" . \"eCt.\".\"TeSt\";?>";
  }

  private static String getSearchStr(String expectedStr) {
    return expectedStr + "-InJeCt.TeSt";
  }

  private Iterable<? extends String> getFileExts() {
    List<String> result = new ArrayList<>();
    String  originalExt = _downloader.getFileExtension();

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

  private static boolean vulnerabilityPresent(HttpRequestResponse responseResponse,
                                              String searchStr) {
    return responseResponse.response().toString().contains(searchStr);
  }

  ////////////////////////////////////////
  // PRIVATE FIELDS
  ////////////////////////////////////////
  private static final List<String> fileExtensions = List.of(
      ".php", ".php5", ".phtml");
  private static final List<String> contentTypes   = List.of(
      ORIGIN_CONT_TYPE, "application/x-php", "application/octet-stream");


  // Check newFilename.orgExt
  // Check newFilename.orgExt; custContType

  // Check newFilename.orgExt.custExt
  // Check newFilename.orgExt.custExt; custContType

  // Check newFilename.custExt
  // Check newFilename.custExt; custContType

  // Check newFilename.custExt.orgExt
  // Check newFilename.custExt.orgExt; custContType
  public static final String[][] PHP_CHECKS = {
      //extension, content-type
      {"", ""},
      {ORIGIN_FILE_EXT, ORIGIN_CONT_TYPE},
      {ORIGIN_FILE_EXT, "application/x-php"},
      {ORIGIN_FILE_EXT, "application/octet-stream"},
      {".php", "application/x-php"},
      {".php", "application/x-php"},
      {".php", "application/octet-stream"},
      {".php", ORIGIN_CONT_TYPE},
      {".php5", "application/x-php"},
      {".php5", "application/octet-stream"},
      {".php5", ORIGIN_CONT_TYPE},
      {".phtml", "application/x-php"},
      {".phtml", "application/octet-stream"},
      {".phtml", ORIGIN_CONT_TYPE},
  };

  private final MontoyaApi              _api;
  private final MultipartRequestFactory _requestFactory;
  private final Downloader              _downloader;
}
