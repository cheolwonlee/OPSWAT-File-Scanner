package com.opswat.filescanner.exceptions;

public class GeneralApiException extends ApiException{
    private String json;
    public GeneralApiException(String json) {
        super("");
        this.json = json;
    }

    public String getJson() {
        return json;
    }
}
