package com.daniel.pointsfiltermap.service;
import com.daniel.pointsfiltermap.dto.Coordinates;
import com.daniel.pointsfiltermap.dto.Map;
import com.daniel.pointsfiltermap.dto.Pin;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

@Service
public class MapService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MapService.class);

    public List<Pin> getPinsByServices(List<Pin> pins,String filter){
        List<Pin> sorted = new ArrayList<>();
        for (Pin pin: pins){
            if (pin.getServiceName().equals(filter)){
                sorted.add(pin);
            }
        }
        return sorted;
    }

    public List<Coordinates> getAllCoordinates(String filepath){
        List<Coordinates> coordinates = new ArrayList<>();
        try {
            DocumentService documentService = new JsonToObjectWriter();
            Map map = documentService.convertJson(filepath, Map.class);
            List<Pin> pins = map.getPins();
            for (Pin pin : pins) {
                coordinates.add(new Coordinates(pin.getCoordinates().getLat(), pin.getCoordinates().getLng()));
            }
        } catch (FileNotFoundException e) {
            LOGGER.error("File not found: " + e.getMessage());
        } catch (IOException e) {
            LOGGER.error("Error reading file: " + e.getMessage());
        } catch (ClassCastException e) {
            LOGGER.error("Unexpected object type: " + e.getMessage());
        } catch (NullPointerException e) {
            LOGGER.error("Null value encountered: " + e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Unexpected exception: " + e.getMessage());
        }
            return coordinates;

    }

    public List<Coordinates> getAllCoordinates(String filepath, List<String> services) throws IOException {
        List<Coordinates> coordinates = new ArrayList<>();
        try {
            MapService mapService = new MapService();
            DocumentService documentService = new JsonToObjectWriter();
            Map map = documentService.convertJson(filepath, Map.class);
            if (map == null) {
                LOGGER.error("Map object is null.");
                throw new NullPointerException("Map object is null.");
            }
            List<Pin> pins = map.getPins();
            if (pins == null) {
                LOGGER.error("Pins list is null.");
                throw new NullPointerException("Pins list is null.");
            }
            for (String service : services) {
                List<Pin> sortedService = mapService.getPinsByServices(pins, service);
                for (Pin pin : sortedService) {
                    if (pin.getCoordinates() == null) {
                        LOGGER.error("Pin coordinates are null.");
                        throw new NullPointerException("Pin coordinates are null.");
                    }
                    coordinates.add(new Coordinates(pin.getCoordinates().getLat(), pin.getCoordinates().getLng()));
                }
            }
            return coordinates;
        } catch (FileNotFoundException e) {
            LOGGER.error("File not found: " + e.getMessage());
        } catch (JsonParseException e) {
            LOGGER.error("JSON parsing error: " + e.getMessage());
        } catch (JsonMappingException e) {
            LOGGER.error("Invalid JSON format: " + e.getMessage());
        } catch (NullPointerException e) {
            LOGGER.error("Null object encountered: " + e.getMessage());
        } catch (ConcurrentModificationException e) {
            LOGGER.error("Concurrent modification detected: " + e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Unexpected error: " + e.getMessage());
        }
        return coordinates;

    }

    public static void main(String[] args) throws IOException {
        MapService mapService = new MapService();
        JsonToObjectWriter jsonToObjectWriter = new JsonToObjectWriter();
        Map map = jsonToObjectWriter.convertJson("src/main/resources/pins.json", Map.class);
        List<Coordinates> coordinates = mapService.getAllCoordinates("src/main/resources/pin.json");
        System.out.println(coordinates);
    }


}
