package com.daniel.pointsfiltermap.dto;


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
@JsonPropertyOrder({ "lat", "lng" })
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Coordinates implements Serializable{

    @JsonProperty("lat")
    private double lat;
    @JsonProperty("lng")
    private double lng;

    @JsonCreator
    public Coordinates( @JsonProperty("lat") double lat,  @JsonProperty("lng") double lng) {
        this.lat = lat;
        this.lng = lng;
    }

}
