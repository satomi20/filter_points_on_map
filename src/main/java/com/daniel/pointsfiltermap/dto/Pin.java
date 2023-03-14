package com.daniel.pointsfiltermap.dto;

import com.daniel.pointsfiltermap.service.JsonToObjectWriter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "service",
        "coordinates"
})

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Pin implements Serializable{

    @JsonProperty("id")
    private int id;
    @JsonProperty("service")
    private String serviceName;
    @JsonProperty("coordinates")
    private Coordinates coordinates;

    @JsonCreator
    public Pin(@JsonProperty("id") int id, @JsonProperty("service")String serviceName, @JsonProperty("coordinates")Coordinates coordinates) {
        this.id = id;
        this.serviceName = serviceName;
        this.coordinates = coordinates;
    }
}