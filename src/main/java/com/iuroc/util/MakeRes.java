package com.iuroc.util;

public class MakeRes<Data extends Object> {
    private boolean success;
    private String message;
    private Data data;

    public MakeRes() {
        this(true);
    }

    public MakeRes(boolean success) {
        this(success, success ? "操作成功" : "操作失败");
    }

    public MakeRes(String message) {
        this(true, message);
    }

    public MakeRes(boolean success, String message) {
        this(success, message, null);
    }

    public MakeRes(boolean success, String message, Data data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public MakeRes<Data> data(Data data) {
        this.success = true;
        this.message = "操作成功";
        this.data = data;
        return this;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public Data getData() {
        return this.data;
    }

    public boolean getSuccess() {
        return this.success;
    }
}