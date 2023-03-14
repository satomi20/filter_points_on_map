package com.daniel.pointsfiltermap.service;

import java.io.IOException;

public interface DocumentService {

    <T> T convertJson(String filepath, Class<T> data) throws IOException;


}
