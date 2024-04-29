package model.factories;

import burp.api.montoya.http.message.ContentType;
import burp.api.montoya.http.message.params.HttpParameter;
import burp.api.montoya.http.message.params.HttpParameterType;
import burp.api.montoya.http.message.params.ParsedHttpParameter;
import burp.api.montoya.http.message.requests.HttpRequest;

import java.util.ArrayList;
import java.util.List;

// https://www.w3.org/Protocols/rfc1341/7_2_Multipart.html
// https://discordapp.com/channels/1159124119074381945/1164175825474686996/1195010198310502480
public class MultipartRequestFactory {
  public final HttpRequest baseRequest;

  ////////////////////////////////////////
  // PUBLIC FUNCTIONS
  ////////////////////////////////////////
  //---------------------------------------------------------------------------
  public MultipartRequestFactory(HttpRequest httpRequest) {
    baseRequest = httpRequest;
    _parts      = parseMultiPartRequest();
  }

  ////////////////////////////////////////
  // PUBLIC METHODS
  ////////////////////////////////////////
  //---------------------------------------------------------------------------
  /* This function will replace every value with the given payload for the
   * every occurrence of a MULTIPART_ATTRIBUTE with the name of "filename"
   * baseRequest.withUpdatedParameters(parameters) doesn't support MULTIPART_ATTRIBUTE
   */
  public HttpRequest getRequestWPayloadNFilename(String payload, String newFilename) {
    String newBody = baseRequest.bodyToString();

    for (int i = 0; i < _parts.size(); i++) {
      if (
          _parts.get(i).type() == HttpParameterType.MULTIPART_ATTRIBUTE &&
          _parts.get(i).name().equals("filename")
      ) {
        newBody = newBody.replace(_parts.get(i).value(), newFilename);
        i++;
        newBody = newBody.replace(_parts.get(i).value(), payload);
      }
    }

    return baseRequest.withBody(newBody);
  }

  //---------------------------------------------------------------------------
  public HttpRequest getRequestWPayloadNFilenameNMime(
      String payload, String newFilename, String contentType
  ) {
    String newBody = baseRequest.bodyToString();

    newBody = newBody.replaceFirst("Content-Type:\\s.*", "Content-Type: " + contentType);

    for (int i = 0; i < _parts.size(); i++) {
      if (
          _parts.get(i).type() == HttpParameterType.MULTIPART_ATTRIBUTE &&
          _parts.get(i).name().equals("filename")
      ) {
        newBody = newBody.replace(_parts.get(i).value(), newFilename);
        i++;
        newBody = newBody.replace(_parts.get(i).value(), payload);
      }
    }

    return baseRequest.withBody(newBody);
  }

  ////////////////////////////////////////
  // PRIVATE FIELDS
  ////////////////////////////////////////
  private final List<ParsedHttpParameter> _parts;

  ////////////////////////////////////////
  // PRIVATE METHODS
  ////////////////////////////////////////
  //---------------------------------------------------------------------------
  private List<ParsedHttpParameter> parseMultiPartRequest() {
    // Check if the request is multipart
    if (baseRequest.contentType() != ContentType.MULTIPART) {
      // Request is not multipart, return empty list
      return new ArrayList<>();
    }

    List<ParsedHttpParameter> parts = new ArrayList<>();
    for (ParsedHttpParameter part : baseRequest.parameters()) {
      if (
          part.type() == HttpParameterType.MULTIPART_ATTRIBUTE ||
          part.type() == HttpParameterType.BODY
      ) {
        parts.add(part);
      }
    }

    return parts;
  }

  //---------------------------------------------------------------------------
  private HttpParameter parameterFor(ParsedHttpParameter parameter) {
    return parameterFor(parameter, parameter.value());
  }

  //---------------------------------------------------------------------------
  private HttpParameter parameterFor(ParsedHttpParameter param, String payload) {
    String            name = param.name();
    HttpParameterType type = param.type();
    return HttpParameter.parameter(name, payload, type);
  }
}
