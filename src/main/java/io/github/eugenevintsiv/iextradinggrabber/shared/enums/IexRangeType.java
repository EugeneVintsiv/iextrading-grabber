package io.github.eugenevintsiv.iextradinggrabber.shared.enums;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@ApiModel
public enum IexRangeType {
    DAY("d"),
    MONTH("m"),
    YEAR("y");

    private String iexRangeAbbrev;

}
