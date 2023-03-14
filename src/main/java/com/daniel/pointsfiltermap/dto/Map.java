package com.daniel.pointsfiltermap.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "services",
        "pins"
})
@Getter
@Setter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class Map implements Serializable{

    @JsonProperty("services")
    private List<String> services;
    @JsonProperty("pins")
    private List<Pin> pins;

    @JsonCreator
    public Map(@JsonProperty("services") List<String> services, @JsonProperty("pins") List<Pin> pins) {
        this.services = services;
        this.pins = pins;
    }
}
