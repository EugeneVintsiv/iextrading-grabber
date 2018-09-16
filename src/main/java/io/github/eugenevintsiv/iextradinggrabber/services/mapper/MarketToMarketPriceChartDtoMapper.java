package io.github.eugenevintsiv.iextradinggrabber.services.mapper;

import io.github.eugenevintsiv.iextradinggrabber.persistant.domain.Market;
import io.github.eugenevintsiv.iextradinggrabber.persistant.domain.MarketPriceChart;
import io.github.eugenevintsiv.iextradinggrabber.web.rest.model.resp.MarketPriceChartDto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MarketToMarketPriceChartDtoMapper {

    public static List<MarketPriceChartDto> toPriceChartDto(List<Market> markets) {
        return markets.parallelStream()
                .flatMap(MarketToMarketPriceChartDtoMapper::getMarketPriceChartDtoBuilder)
                .collect(Collectors.toList());
    }

    private static Stream<MarketPriceChartDto> getMarketPriceChartDtoBuilder(Market m) {
        final MarketPriceChartDto.MarketPriceChartDtoBuilder result = MarketPriceChartDto.builder().companySymbol(m.getCompanySymbol());
        final List<MarketPriceChartDto> collect = m.getPriceCharts()
                .parallelStream()
                .map(pc -> buildDto(result, pc))
                .collect(Collectors.toList());
        return collect.stream();
    }

    private static MarketPriceChartDto buildDto(MarketPriceChartDto.MarketPriceChartDtoBuilder result, MarketPriceChart pc) {
        return result
                .date(pc.getDate())
                .openPrice(pc.getOpenPrice())
                .closePrice(pc.getClosePrice())
                .highPrice(pc.getHighPrice())
                .vwapPrice(pc.getVwapPrice())
                .build();
    }

}
