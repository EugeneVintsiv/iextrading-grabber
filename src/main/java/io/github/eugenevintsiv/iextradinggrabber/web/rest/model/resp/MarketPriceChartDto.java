package io.github.eugenevintsiv.iextradinggrabber.web.rest.model.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Provide info about prices per date")
public class MarketPriceChartDto {

    @ApiModelProperty(value = "Date for prices charts")
    private LocalDate date;
    @ApiModelProperty(value = "Company price at open")
    private BigDecimal openPrice;
    @ApiModelProperty(value = "Company high price per day")
    private BigDecimal highPrice;
    @ApiModelProperty(value = "Company price at close")
    private BigDecimal closePrice;
    @ApiModelProperty(value = "Company vwap price")
    private BigDecimal vwapPrice;
}
