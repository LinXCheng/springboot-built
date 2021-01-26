package com.lx.attendance.utils;


public class ResultSet<T> {

    private boolean success = true;
    private String msg;
    private T result;

    public ResultSet() {
    }

    public ResultSet(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public ResultSet(boolean success, String msg, T result) {
        this.success = success;
        this.msg = msg;
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
