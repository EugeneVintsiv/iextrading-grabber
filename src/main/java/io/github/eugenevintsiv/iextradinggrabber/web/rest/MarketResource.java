package io.github.eugenevintsiv.iextradinggrabber.web.rest;

import io.github.eugenevintsiv.iextradinggrabber.services.MarketService;
import io.github.eugenevintsiv.iextradinggrabber.web.rest.model.resp.MarketDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "Market resource", description = "Provides an API to receive data from IexTrading Platform")
@RequestMapping(value = "/api/v0/market")
@RestController
public class MarketResource {

    private final MarketService marketService;

    @Autowired
    public MarketResource(MarketService marketService) {
        this.marketService = marketService;
    }

    @GetMapping
    @ApiOperation("Returns data received from IexTrading Platform. Filtering support.")
    public ResponseEntity<List<MarketDto>> getAll() {
        final List<MarketDto> markets = marketService.receiveFromExternal();
        return ResponseEntity.ok(markets);
    }

}
