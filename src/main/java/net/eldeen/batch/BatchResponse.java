package net.eldeen.batch;

import java.util.Map;

/**
 * User: heldeen
 * Date: 3/6/14
 */
public class BatchResponse {
  private int requestId;
  private int status;
  private Object response;
  private Map<String, String> headers;

  public int getRequestId() {
    return requestId;
  }

  public void setRequestId(int requestId) {
    this.requestId = requestId;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public Object getResponse() {
    return response;
  }

  public void setEntity(Object response) {
    this.response = response;
  }

  public Map<String, String> getHeaders() {
    return headers;
  }

  public void setHeaders(Map<String, String> headers) {
    this.headers = headers;
  }
}
