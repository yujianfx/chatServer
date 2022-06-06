package top.wangudiercai.chatserver.common;

public class Result<T> {
  private int code;
  private String msg;
  private T data;

  public Result() {}

  public Result(int code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  public Result(int code, String msg, T data) {
    this.code = code;
    this.msg = msg;
    this.data = data;
  }

  public static <T> Result<T> success() {
    return new Result<>(0, "success");
  }

  public static <T> Result<T> success(T data) {
    return new Result<>(0, "success", data);
  }

  public static <T> Result<T> error(int code, String msg) {
    return new Result<>(code, msg);
  }
  public static <T> Result<T> error(int code, String msg, T data) {
    return new Result<>(code, msg, data);
  }
  public static <T> Result<T> error(int code, T data) {
    return new Result<>(code, "error", data);
  }
  public static <T> Result<T> error(int code) {
    return new Result<>(code, "error");
  }
  public static <T> Result<T> error(String msg) {
    return new Result<>(1, msg);
  }
  public static <T> Result<T> error(String msg, T data) {
    return new Result<>(1, msg, data);
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }
}
