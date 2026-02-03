package com.eimc.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;

///  Builder Pattern for HttpResponse
@SuperBuilder

/// Doesn't include empty fields in response
@JsonInclude(NON_DEFAULT)
public class HttpResponse {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    protected LocalDateTime timeStamp;
    protected Map<?,?> data;
    protected String message;
    protected HttpStatus status;
    protected int statusCode;
    protected String path;

    protected String requestMethod;
    protected String developerMessage;

    public HttpResponse() {}

    public HttpResponse(LocalDateTime timeStamp,
                        Map<?, ?> data,
                        String message,
                        HttpStatus status,
                        int statusCode,
                        String path,
                        String requestMethod,
                        String developerMessage)
    {
        this.timeStamp = timeStamp;
        this.data = data;
        this.message = message;
        this.status = status;
        this.statusCode = statusCode;
        this.path = path;
        this.requestMethod = requestMethod;
        this.developerMessage = developerMessage;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public Map<?, ?> getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getPath() {
        return path;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setData(Map<?, ?> data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }

}
