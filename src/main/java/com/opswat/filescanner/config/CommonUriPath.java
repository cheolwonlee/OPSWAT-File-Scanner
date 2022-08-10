package com.opswat.filescanner.config;

public class CommonUriPath {
    public static final String	FILE	            = "/file";

    public static final String	HASH	            = "/hash";

    public static String FILE(String file){
        StringBuilder sb = new StringBuilder();
        sb.append(FILE).append("/").append(file);
        return sb.toString();
    }

    public static String HASH(String hash){
        StringBuilder sb = new StringBuilder();
        sb.append(HASH).append("/").append(hash);
        return sb.toString();
    }
}
