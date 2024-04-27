package model.checks;

import burp.api.montoya.MontoyaApi;
import burp.api.montoya.scanner.audit.issues.AuditIssue;

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
}
///////////////////////////////////////
// END CLASS UploaderCheck
///////////////////////////////////////