package io.github.eugenevintsiv.iextradinggrabber.web.rest.model.resp;

import io.github.eugenevintsiv.iextradinggrabber.shared.enums.PriceType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Provide info about prices at different states(types)")
public class PriceDto {

    @ApiModelProperty(value = "Price state(type)")
    private PriceType type;
    @ApiModelProperty(value = "Time for price")
    private LocalDateTime time;
    @ApiModelProperty(value = "Price value")
    private BigDecimal value;
}
