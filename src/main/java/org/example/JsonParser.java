package org.example;

public class JsonParser {
    private boolean success;
    private String errorCode;
    private String extra;

    public boolean isSuccess() {
        return success;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getExtra() {
        return extra;
    }

    @Override
    public String toString() {
        return "success=" + success+ " errorCode=" + errorCode==null ?" N/A" : errorCode +
                "\n extra=" + extra ==null ?" N/A" : extra ;
    }
}
