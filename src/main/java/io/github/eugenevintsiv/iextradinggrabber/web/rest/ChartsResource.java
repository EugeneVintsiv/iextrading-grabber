package io.github.eugenevintsiv.iextradinggrabber.web.rest;

import io.github.eugenevintsiv.iextradinggrabber.services.ChartService;
import io.github.eugenevintsiv.iextradinggrabber.web.rest.model.resp.MarketPriceChartDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Api(value = "Chart resource", description = "Provides an API to receive stored chart data")
@RequestMapping(value = "/api/v0/chart")
@RestController
public class ChartsResource {

    private final ChartService chartService;

    @Autowired
    public ChartsResource(ChartService chartService) {
        this.chartService = chartService;
    }

    @GetMapping
    @ApiOperation("Returns all charts for company symbols.")
    public ResponseEntity<List<MarketPriceChartDto>> getStoredSymbols(
            @ApiParam(value = "Companies abbrev", required = true)
            @RequestParam Set<String> companySymbols,
            @ApiParam(value = "Filter dateFrom for chart. Format, like: 2000-10-31", required = true)
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @ApiParam(value = "Filter dateTo for chart. Format, like: 2000-11-31", required = true)
            @RequestParam(required = false)  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to
            ) {
        final Set<String> symbols = companySymbols.stream().map(String::toUpperCase).collect(Collectors.toSet());
        final List<MarketPriceChartDto> charts = chartService.getCharts(symbols, from, to);
        return ResponseEntity.ok(charts);
    }
}
