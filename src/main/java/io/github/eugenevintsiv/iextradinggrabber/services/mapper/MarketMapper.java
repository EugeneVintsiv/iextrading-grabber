package io.github.eugenevintsiv.iextradinggrabber.services.mapper;

import io.github.eugenevintsiv.iextradinggrabber.persistant.domain.Market;
import io.github.eugenevintsiv.iextradinggrabber.services.client.model.CompanyInfoHolder;
import io.github.eugenevintsiv.iextradinggrabber.web.rest.model.resp.MarketDto;
import io.github.eugenevintsiv.iextradinggrabber.web.rest.model.resp.MarketPriceChartDto;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring")
public interface MarketMapper {

    List<MarketDto> toMarketDto(List<Market> markets);
    MarketDto toMarketDto(Market market);

    default List<Market> toMarkets(Map<String, CompanyInfoHolder> marketData) {
        return IexMarketInfoMapper.toMarkets(marketData);
    }

    default List<MarketPriceChartDto> toPriceChartDto(List<Market> markets) {
        return MarketToMarketPriceChartDtoMapper.toPriceChartDto(markets);
    }
}
