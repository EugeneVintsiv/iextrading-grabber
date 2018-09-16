package io.github.eugenevintsiv.iextradinggrabber.web.rest.model.req;

import com.google.common.base.Joiner;
import io.github.eugenevintsiv.iextradinggrabber.shared.enums.IexRangeType;
import io.github.eugenevintsiv.iextradinggrabber.web.rest.validator.annotation.EnumValidator;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Set;

@Getter
@AllArgsConstructor
public class MarketFilerParams {
    @NotEmpty
    @ApiModelProperty(notes = "companies abbrevs", required = true)
    private final Set<String> symbols;

    @NotNull
    @Positive
    @ApiModelProperty(notes = "count of periods", required = true)
    private final Integer rangeCount;

    @NotBlank
    @EnumValidator(enumClazz=IexRangeType.class)
    @ApiModelProperty(allowableValues = "DAY, MONTH, YEAR", notes = "type of period", required = true)
    private final String rangeType;


    @ApiModelProperty(hidden = true)
    public String getSymbolsStr() {
        return Joiner.on(",").join(symbols);
    }

    @ApiModelProperty(hidden = true)
    public String getTypesStr() {
        return "quote,logo,company,price,chart";
    }

    @ApiModelProperty(hidden = true)
    public String getRange() {
        return String.format("%d%s", rangeCount, IexRangeType.valueOf(rangeType).getIexRangeAbbrev());
    }

}
