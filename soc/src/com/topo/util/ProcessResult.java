package com.topo.util;

public class ProcessResult<K>
{
  private K data;
  private String message = "";
  private boolean success;

  public K getData()
  {
    return this.data;
  }
  public void setData(K data) {
    this.data = data;
  }
  public String getMessage() {
    return this.message;
  }
  public void setMessage(String message) {
    this.message = message;
  }
  public boolean isSuccess() {
    return this.success;
  }
  public void setSuccess(boolean success) {
    this.success = success;
  }
}