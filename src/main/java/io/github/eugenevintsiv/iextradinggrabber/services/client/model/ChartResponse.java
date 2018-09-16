package io.github.eugenevintsiv.iextradinggrabber.services.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "date",
        "open",
        "high",
        "low",
        "close",
        "volume",
        "unadjustedVolume",
        "change",
        "changePercent",
        "vwap",
        "label",
        "changeOverTime"
})
public class ChartResponse {

    @JsonProperty("date")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate date;
    @JsonProperty("open")
    private double open;
    @JsonProperty("high")
    private double high;
    @JsonProperty("low")
    private double low;
    @JsonProperty("close")
    private double close;
    @JsonProperty("volume")
    private int volume;
    @JsonProperty("unadjustedVolume")
    private int unadjustedVolume;
    @JsonProperty("change")
    private double change;
    @JsonProperty("changePercent")
    private double changePercent;
    @JsonProperty("vwap")
    private double vwap;
    @JsonProperty("label")
    private String label;
    @JsonProperty("changeOverTime")
    private double changeOverTime;
}