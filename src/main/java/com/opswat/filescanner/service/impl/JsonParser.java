package com.opswat.filescanner.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opswat.filescanner.exceptions.JsonFormatException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service
public class JsonParser {
    private Gson gson;

    @PostConstruct
    public void init(){
        gson = new GsonBuilder().create();
    }

    public <T> T parseJson(String json, Class<T> clazz) throws JsonFormatException{
        try {
            return gson.fromJson(json, clazz);
        }catch (Exception e){
            throw new JsonFormatException(e.getMessage());
        }
    }
}
