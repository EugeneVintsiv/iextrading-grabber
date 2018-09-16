package io.github.eugenevintsiv.iextradinggrabber.web.rest.model.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Provide info about market")
public class MarketDto {

    @ApiModelProperty(value = "Market id in current system")
    private String id;

    @ApiModelProperty(value = "Company Symbol(Abbrev) value")
    private String companySymbol;
    @ApiModelProperty(value = "Company Full name")
    private String companyName;
    @ApiModelProperty(value = "Sector of company stock")
    private String sector;
    @ApiModelProperty(value = "Url to company logo")
    private String logoUrl;
    @ApiModelProperty(value = "List of company prices at different states(types)")
    private List<MarketPriceDto> prices = new ArrayList<>();
    @ApiModelProperty(value = "List of prices per dates")
    private List<MarketPriceChartDto> priceCharts = new ArrayList<>();
}
