package com.daniel.pointsfiltermap.service;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;

@Service
public class JsonToObjectWriter implements DocumentService{

    @Override
    public <T> T convertJson(String filepath, Class<T> data) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        File jsonFile = new File(filepath);
        return objectMapper.readValue(jsonFile, data);
    }

}
