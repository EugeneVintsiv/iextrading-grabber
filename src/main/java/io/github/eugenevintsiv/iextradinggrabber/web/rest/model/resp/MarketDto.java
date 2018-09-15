package io.github.eugenevintsiv.iextradinggrabber.web.rest.model.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Provide info about market")
public class MarketDto {

    @ApiModelProperty(value = "Market id")
    private String id;
}
