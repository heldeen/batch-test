package net.eldeen.batch;

import java.util.Map;

/**
 * User: heldeen
 * Date: 3/6/14
 */
public class BatchRequest {

  private int requestId;
  private String uri;
  private String method;
  private Map<String, String> headers;


  public int getRequestId() {
    return requestId;
  }

  public void setRequestId(int requestId) {
    this.requestId = requestId;
  }

  public String getUri() {
    return uri;
  }

  public void setUri(String uri) {
    this.uri = uri;
  }

  public String getMethod() {
    return method;
  }

  public void setMethod(String method) {
    this.method = method;
  }

  public Map<String, String> getHeaders() {
    return headers;
  }

  public void setHeaders(Map<String, String> headers) {
    this.headers = headers;
  }
}
