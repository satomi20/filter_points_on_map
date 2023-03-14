package com.daniel.pointsfiltermap.controller;

import com.daniel.pointsfiltermap.dto.Coordinates;
import com.daniel.pointsfiltermap.dto.Map;
import com.daniel.pointsfiltermap.dto.Pin;
import com.daniel.pointsfiltermap.service.DocumentService;
import com.daniel.pointsfiltermap.service.JsonToObjectWriter;
import com.daniel.pointsfiltermap.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MapController {

    private MapService mapService;

    @Autowired
    public MapController(MapService mapService) {
        this.mapService = mapService;
    }

    @GetMapping("/map")
    public String getMap(Model model) throws IOException {
        List<Coordinates> coordinates = mapService.getAllCoordinates("src/main/resources/pins.json");
        model.addAttribute("coordinates", coordinates);
        return "map";
    }

    @GetMapping("/filter")
    public String getFilter(@RequestParam("services") List<String> services, Model model) throws IOException {
        List<Coordinates> coordinates = mapService.getAllCoordinates("src/main/resources/pins.json", services);
        model.addAttribute("coordinates", coordinates);
        model.addAttribute("selectedService", services);
        return "map";
    }
}
