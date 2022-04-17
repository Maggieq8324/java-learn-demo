package com.coisini.mongodb.model;

/**
 * @Description 统一消息
 * @author coisini
 * @date Apr 17, 2022
 * @version 1.0
 */
public class ResponseMessage<T> {

    private String status;
    private String message;
    private T data;

    public static ResponseMessage<?> ok() {
        return create("0", (String)null, (Object)null);
    }

    public static ResponseMessage<?> ok(String message) {
        return create("0", message, (Object)null);
    }

    public static <T> ResponseMessage<T> ok(String message, T data) {
        return create("0", message, data);
    }

    public static <T> ResponseMessage<T> ok(T data) {
        return create("0", (String)null, data);
    }

    public static ResponseMessage<?> error() {
        return create("1", (String)null, (Object)null);
    }

    public static ResponseMessage<?> error(String message) {
        return create("1", message, (Object)null);
    }

    public static <T> ResponseMessage<T> error(String message, T data) {
        return create("1", message, data);
    }

    private static <T> ResponseMessage<T> create(String status, String message, T data) {
        ResponseMessage<T> t = new ResponseMessage();
        t.setStatus(status);
        t.setMessage(message);
        t.setData(data);
        return t;
    }

    public ResponseMessage() {
    }

    public String getStatus() {
        return this.status;
    }

    public String getMessage() {
        return this.message;
    }

    public T getData() {
        return this.data;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public void setData(final T data) {
        this.data = data;
    }

}
