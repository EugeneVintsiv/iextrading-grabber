package io.github.eugenevintsiv.iextradinggrabber.services.mapper;

import io.github.eugenevintsiv.iextradinggrabber.persistant.domain.Market;
import io.github.eugenevintsiv.iextradinggrabber.persistant.domain.MarketPrice;
import io.github.eugenevintsiv.iextradinggrabber.persistant.domain.MarketPriceChart;
import io.github.eugenevintsiv.iextradinggrabber.services.client.model.ChartResponse;
import io.github.eugenevintsiv.iextradinggrabber.services.client.model.CompanyInfoHolder;
import io.github.eugenevintsiv.iextradinggrabber.services.client.model.CompanyResponse;
import io.github.eugenevintsiv.iextradinggrabber.services.client.model.LogoResponse;
import io.github.eugenevintsiv.iextradinggrabber.services.client.model.QuoteResponse;
import io.github.eugenevintsiv.iextradinggrabber.shared.enums.PriceType;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.github.eugenevintsiv.iextradinggrabber.shared.enums.PriceType.CLOSE;
import static io.github.eugenevintsiv.iextradinggrabber.shared.enums.PriceType.DELAYED_PRICE;
import static io.github.eugenevintsiv.iextradinggrabber.shared.enums.PriceType.EXTENDED_PRICE;
import static io.github.eugenevintsiv.iextradinggrabber.shared.enums.PriceType.LATEST_PRICE;
import static io.github.eugenevintsiv.iextradinggrabber.shared.enums.PriceType.OPEN;

public class IexMarketInfoMapper {

    public static List<Market> toMarkets(Map<String, CompanyInfoHolder> marketData) {
        return marketData.entrySet().parallelStream()
                .map(m -> {
                    final String companySymbol = m.getKey();
                    final CompanyInfoHolder companyInfo = m.getValue();
                    final Market.MarketBuilder builder = Market.builder();
                    builder.companySymbol(companySymbol);
                    fillCompanyInfo(builder, companyInfo.getCompany());
                    fillCompanyLogo(builder, companyInfo.getLogo());
                    fillCompanyPrices(builder, companyInfo.getQuote());
                    fillCompanyPriceCharts(builder, companyInfo.getChart());
                    return builder.build();
                }).collect(Collectors.toList());
    }

    private static void fillCompanyInfo(Market.MarketBuilder builder, CompanyResponse company) {
        builder.companyName(company.getCompanyName());
        builder.sector(company.getSector());
    }

    private static void fillCompanyLogo(Market.MarketBuilder builder, LogoResponse response) {
        builder.logoUrl(StringUtils.isEmpty(response.getUrl()) ? "" : response.getUrl());
    }

    private static void fillCompanyPrices(Market.MarketBuilder builder, QuoteResponse response) {
        List<MarketPrice> prices = new ArrayList<>();

        prices.add(buildMarketPrice(OPEN, response.getOpenTime(), response.getOpen()));
        prices.add(buildMarketPrice(CLOSE, response.getCloseTime(), response.getClose()));
        prices.add(buildMarketPrice(LATEST_PRICE, response.getLatestTime(), response.getLatestPrice()));
        prices.add(buildMarketPrice(DELAYED_PRICE, response.getDelayedPriceTime(), response.getDelayedPrice()));
        prices.add(buildMarketPrice(EXTENDED_PRICE, response.getExtendedPriceTime(), response.getExtendedPrice()));

        builder.prices(prices);
    }

    private static void fillCompanyPriceCharts(Market.MarketBuilder builder, List<ChartResponse> chart) {
        final List<MarketPriceChart> priceCharts = chart.parallelStream()
                .map(IexMarketInfoMapper::buildMarketPrice)
                .collect(Collectors.toList());
        builder.priceCharts(priceCharts);
    }

    private static MarketPriceChart buildMarketPrice(ChartResponse c) {
        return MarketPriceChart.builder()
                .date(c.getDate())
                .openPrice(toBigDecimal(c.getOpen()))
                .closePrice(toBigDecimal(c.getClose()))
                .vwapPrice(toBigDecimal(c.getVwap()))
                .build();
    }

    private static MarketPrice buildMarketPrice(PriceType type, LocalDateTime time, double price) {
        return MarketPrice.builder()
                .type(type)
                .time(time)
                .value(toBigDecimal(price))
                .build();
    }

    private static BigDecimal toBigDecimal(double price) {
        return BigDecimal.valueOf(price);
    }
}
