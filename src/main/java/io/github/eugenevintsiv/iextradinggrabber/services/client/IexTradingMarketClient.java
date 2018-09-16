package io.github.eugenevintsiv.iextradinggrabber.services.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "market", url = "https://api.iextrading.com/1.0/")
public interface IexTradingMarketClient {
    @GetMapping(value = "stock/market/batch")
    List<Object> getMarketData(@RequestParam(name = "symbols") String companies,
                               @RequestParam(name = "types") String types,
                               @RequestParam(name = "range") String range);
}