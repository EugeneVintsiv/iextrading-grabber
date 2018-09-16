package io.github.eugenevintsiv.iextradinggrabber.web.rest.model.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "Provide info about prices per date")
public class MarketPriceChartDto extends PriceChartDto {

    @ApiModelProperty(value = "Company Symbol(Abbrev) value")
    private String companySymbol;

    @Builder
    public MarketPriceChartDto(LocalDate date, BigDecimal openPrice, BigDecimal highPrice, BigDecimal closePrice, BigDecimal vwapPrice, String companySymbol) {
        super(date, openPrice, highPrice, closePrice, vwapPrice);
        this.companySymbol = companySymbol;
    }
}
