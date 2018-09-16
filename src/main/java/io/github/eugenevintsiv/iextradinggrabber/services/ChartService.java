package io.github.eugenevintsiv.iextradinggrabber.services;

import io.github.eugenevintsiv.iextradinggrabber.web.rest.model.resp.MarketPriceChartDto;

import java.util.List;
import java.util.Set;

public interface ChartService {
    List<MarketPriceChartDto> getCharts(Set<String> companySymbols);
}
