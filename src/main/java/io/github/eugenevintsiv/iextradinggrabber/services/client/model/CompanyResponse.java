package io.github.eugenevintsiv.iextradinggrabber.services.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "symbol",
        "companyName",
        "exchange",
        "industry",
        "website",
        "description",
        "CEO",
        "issueType",
        "sector",
        "tags"
})
public class CompanyResponse {

    @JsonProperty("symbol")
    private String symbol;
    @JsonProperty("companyName")
    private String companyName;
    @JsonProperty("exchange")
    private String exchange;
    @JsonProperty("industry")
    private String industry;
    @JsonProperty("website")
    private String website;
    @JsonProperty("description")
    private String description;
    @JsonProperty("CEO")
    private String cEO;
    @JsonProperty("issueType")
    private String issueType;
    @JsonProperty("sector")
    private String sector;
    @JsonProperty("tags")
    private List<String> tags = new ArrayList<>();
}