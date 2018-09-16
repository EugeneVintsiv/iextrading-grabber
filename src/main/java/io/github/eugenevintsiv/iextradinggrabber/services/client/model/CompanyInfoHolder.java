package io.github.eugenevintsiv.iextradinggrabber.services.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompanyInfoHolder {

    @JsonProperty("quote")
    private QuoteResponse quote;
    @JsonProperty("logo")
    private LogoResponse logo;
    @JsonProperty("company")
    private CompanyResponse company;
    @JsonProperty("price")
    private Double price;
    @JsonProperty("chart")
    private List<ChartResponse> chart = new ArrayList<>();
}
