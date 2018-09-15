package io.github.eugenevintsiv.iextradinggrabber.services.mapper;

import io.github.eugenevintsiv.iextradinggrabber.persistant.domain.Market;
import io.github.eugenevintsiv.iextradinggrabber.web.rest.model.resp.MarketDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MarketMapper {

    List<MarketDto> toMarketDto(List<Market> markets);
    MarketDto toMarketDto(Market market);
}
