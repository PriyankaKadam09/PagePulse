package com.priyanka.pagepulse;

public class AuditResponse {

    private String url;
    private int status;
    private boolean reachable;
    private long responseTime;
    private String message;

    public AuditResponse(String url, int status, boolean reachable, long responseTime, String message) {
        this.url = url;
        this.status = status;
        this.reachable = reachable;
        this.responseTime = responseTime;
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public int getStatus() {
        return status;
    }

    public boolean isReachable() {
        return reachable;
    }

    public long getResponseTime() {
        return responseTime;
    }

    public String getMessage() {
        return message;
    }
}