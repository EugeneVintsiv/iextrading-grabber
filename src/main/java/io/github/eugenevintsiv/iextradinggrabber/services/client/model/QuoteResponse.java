package io.github.eugenevintsiv.iextradinggrabber.services.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.github.eugenevintsiv.iextradinggrabber.services.client.model.deserializer.LongToLocalDateTimeDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
        "symbol",
        "companyName",
        "primaryExchange",
        "sector",
        "calculationPrice",
        "open",
        "openTime",
        "close",
        "closeTime",
        "high",
        "low",
        "latestPrice",
        "latestSource",
        "latestTime",
        "latestUpLocalDateTime",
        "latestVolume",
        "iexRealtimePrice",
        "iexRealtimeSize",
        "iexLastUpLocalDateTimed",
        "delayedPrice",
        "delayedPriceTime",
        "extendedPrice",
        "extendedChange",
        "extendedChangePercent",
        "extendedPriceTime",
        "previousClose",
        "change",
        "changePercent",
        "iexMarketPercent",
        "iexVolume",
        "avgTotalVolume",
        "iexBidPrice",
        "iexBidSize",
        "iexAskPrice",
        "iexAskSize",
        "marketCap",
        "peRatio",
        "week52High",
        "week52Low",
        "ytdChange"
})
public class QuoteResponse {

    @JsonProperty("symbol")
    private String symbol;
    @JsonProperty("companyName")
    private String companyName;
    @JsonProperty("primaryExchange")
    private String primaryExchange;
    @JsonProperty("sector")
    private String sector;
    @JsonProperty("calculationPrice")
    private String calculationPrice;
    @JsonProperty("open")
    private double open;
    @JsonDeserialize(using = LongToLocalDateTimeDeserializer.class)
    @JsonProperty("openTime")
    private LocalDateTime openTime;
    @JsonProperty("close")
    private double close;
    @JsonDeserialize(using = LongToLocalDateTimeDeserializer.class)
    @JsonProperty("closeTime")
    private LocalDateTime closeTime;
    @JsonProperty("high")
    private double high;
    @JsonProperty("low")
    private double low;
    @JsonProperty("latestPrice")
    private double latestPrice;
    @JsonProperty("latestSource")
    private String latestSource;
    @JsonProperty("latestTime")
    @JsonDeserialize(using = LongToLocalDateTimeDeserializer.class)
    private LocalDateTime latestTime;
    @JsonDeserialize(using = LongToLocalDateTimeDeserializer.class)
    @JsonProperty("latestUpLocalDateTime")
    private LocalDateTime latestUpLocalDateTime;
    @JsonProperty("latestVolume")
    private int latestVolume;
    @JsonProperty("iexRealtimePrice")
    private double iexRealtimePrice;
    @JsonProperty("iexRealtimeSize")
    private int iexRealtimeSize;
    @JsonDeserialize(using = LongToLocalDateTimeDeserializer.class)
    @JsonProperty("iexLastUpLocalDateTimed")
    private LocalDateTime iexLastUpLocalDateTimed;
    @JsonProperty("delayedPrice")
    private double delayedPrice;
    @JsonDeserialize(using = LongToLocalDateTimeDeserializer.class)
    @JsonProperty("delayedPriceTime")
    private LocalDateTime delayedPriceTime;
    @JsonProperty("extendedPrice")
    private double extendedPrice;
    @JsonProperty("extendedChange")
    private int extendedChange;
    @JsonProperty("extendedChangePercent")
    private int extendedChangePercent;
    @JsonDeserialize(using = LongToLocalDateTimeDeserializer.class)
    @JsonProperty("extendedPriceTime")
    private LocalDateTime extendedPriceTime;
    @JsonProperty("previousClose")
    private double previousClose;
    @JsonProperty("change")
    private double change;
    @JsonProperty("changePercent")
    private double changePercent;
    @JsonProperty("iexMarketPercent")
    private double iexMarketPercent;
    @JsonProperty("iexVolume")
    private int iexVolume;
    @JsonProperty("avgTotalVolume")
    private int avgTotalVolume;
    @JsonProperty("iexBidPrice")
    private double iexBidPrice;
    @JsonProperty("iexBidSize")
    private int iexBidSize;
    @JsonProperty("iexAskPrice")
    private double iexAskPrice;
    @JsonProperty("iexAskSize")
    private int iexAskSize;
    @JsonProperty("marketCap")
    private Long marketCap;
    @JsonProperty("peRatio")
    private double peRatio;
    @JsonProperty("week52High")
    private double week52High;
    @JsonProperty("week52Low")
    private double week52Low;
    @JsonProperty("ytdChange")
    private double ytdChange;
}