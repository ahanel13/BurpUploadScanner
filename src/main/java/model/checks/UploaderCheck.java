package model.checks;

import burp.api.montoya.MontoyaApi;
import burp.api.montoya.core.Marker;
import burp.api.montoya.http.message.HttpRequestResponse;
import burp.api.montoya.scanner.audit.issues.AuditIssue;

import java.util.LinkedList;
import java.util.List;

///////////////////////////////////////
// CLASS UploaderCheck
///////////////////////////////////////
public abstract class UploaderCheck extends Thread {

  public int getTotalChecks() {
    return totalChecks;
  }

  // this is just for progress updating, count functions not requests
  protected final  int       totalChecks;
  protected final MontoyaApi api;

  protected void report(AuditIssue issue) {
    api.siteMap().add(issue);
  }

  protected UploaderCheck(int checks, MontoyaApi api) {
    totalChecks = checks;
    this.api    = api;
  }

  protected HttpRequestResponse highlightResponse(HttpRequestResponse resp, String match) {
    List<Marker> highlights = new LinkedList<>();
    String       response   = resp.response().toString();

    int start = 0;

    while (start < response.length())
    {
        start = response.indexOf(match, start);

        if (start == -1)
        {
            break;
        }

        Marker marker = Marker.marker(start, start+match.length());
        highlights.add(marker);

        start += match.length();
    }
    return resp.withResponseMarkers(highlights);
  }

}
///////////////////////////////////////
// END CLASS UploaderCheck
///////////////////////////////////////