package model.checks;

import burp.api.montoya.MontoyaApi;
import burp.api.montoya.http.message.HttpRequestResponse;
import burp.api.montoya.http.message.requests.HttpRequest;
import burp.api.montoya.scanner.audit.issues.AuditIssue;
import burp.api.montoya.scanner.audit.issues.AuditIssueConfidence;
import burp.api.montoya.scanner.audit.issues.AuditIssueSeverity;
import model.factories.MultipartRequestFactory;
import model.scan.BaseConfigModel;
import model.scan.Downloader;
import model.scan.Sender;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static model.utilities.Constants.*;

public class PhpChecks extends UploaderCheck {
  ////////////////////////////////////////
  // PUBLIC FUNCTIONS
  ////////////////////////////////////////
  public PhpChecks(
      HttpRequestResponse response, MontoyaApi api, Downloader downloader
  ) {
    super(4, api);
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
      if (_baseConfigModel.pngFileType())
        rcePngIdatChunkCheck();

      if (_baseConfigModel.gifFileType())
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
    boolean          isVulnerable;

    for (String extension : getFileExts()) {
      for (String type : contentTypes) {
        if(isInterrupted())
          return;

        String      newFilename = getFilename("basicRceCheck", extension);
        String      randomStr   = getRandomStr();
        String      payload     = getPayload(randomStr);
        String      searchStr   = getSearchStr(randomStr);
        HttpRequest request;
        HttpRequestResponse reDownloadReqResp = null;

        if (type.equals(ORIGIN_CONT_TYPE))
          request = _requestFactory.getRequestWPayloadNFilename(payload, newFilename);
        else
          request = _requestFactory.getRequestWPayloadNFilenameNMime(payload, newFilename, type);

        HttpRequestResponse requestResponse = new Sender(api, request).send();

        if (_downloader.isUsed()) {
          /* todo: update this to make a request to the filename minus
                   the fuzz string. so that ".php%00.png" works and downloader
                   will request ".php"
          */
          reDownloadReqResp = _downloader.download(newFilename);
          isVulnerable = vulnerabilityPresent(reDownloadReqResp, searchStr);
        }
        else {
          isVulnerable = vulnerabilityPresent(requestResponse, searchStr);
        }

        if (isVulnerable) {
          report(AuditIssue.auditIssue(
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
              reDownloadReqResp
          ));
        }
      }
    }
    // todo: this check as completed
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


  ////////////////////////////////////////
  // PRIVATE FIELDS
  ////////////////////////////////////////
  private final MultipartRequestFactory _requestFactory;
  private final Downloader              _downloader;

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
  private BaseConfigModel _baseConfigModel;


  //---------------------------------------------------------------------------
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
}
