package io.github.eugenevintsiv.iextradinggrabber.services;

import io.github.eugenevintsiv.iextradinggrabber.web.rest.model.req.MarketFilerParams;
import io.github.eugenevintsiv.iextradinggrabber.web.rest.model.resp.MarketDto;

import java.util.List;

public interface MarketService {
    List<MarketDto> receiveFromExternal(MarketFilerParams filterParams);
}
