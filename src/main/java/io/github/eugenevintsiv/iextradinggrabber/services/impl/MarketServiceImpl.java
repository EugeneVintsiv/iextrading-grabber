package io.github.eugenevintsiv.iextradinggrabber.services.impl;

import io.github.eugenevintsiv.iextradinggrabber.persistant.domain.Market;
import io.github.eugenevintsiv.iextradinggrabber.persistant.repository.MarketRepository;
import io.github.eugenevintsiv.iextradinggrabber.services.MarketService;
import io.github.eugenevintsiv.iextradinggrabber.services.client.IexTradingMarketClient;
import io.github.eugenevintsiv.iextradinggrabber.services.client.model.CompanyInfoHolder;
import io.github.eugenevintsiv.iextradinggrabber.services.mapper.MarketMapper;
import io.github.eugenevintsiv.iextradinggrabber.web.rest.model.req.MarketFilerParams;
import io.github.eugenevintsiv.iextradinggrabber.web.rest.model.resp.MarketDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Transactional
@Service("marketServiceImpl")
public class MarketServiceImpl implements MarketService {

    private final IexTradingMarketClient iexTradingMarketClient;
    private final MarketMapper marketMapper;
    private final MarketRepository marketRepository;

    @Autowired
    public MarketServiceImpl(IexTradingMarketClient iexTradingMarketClient,
                             MarketMapper marketMapper,
                             MarketRepository marketRepository) {
        this.iexTradingMarketClient = iexTradingMarketClient;
        this.marketMapper = marketMapper;
        this.marketRepository = marketRepository;
    }

    @Override
    public List<MarketDto> receiveFromExternal(MarketFilerParams filterParams) {
        final Map<String, CompanyInfoHolder> marketData = iexTradingMarketClient.getMarketData(filterParams.getSymbolsStr(), filterParams.getTypesStr(), filterParams.getRange());
        if (CollectionUtils.isEmpty(marketData)) return new ArrayList<>();
        final List<Market> newMarkets = marketMapper.toMarkets(marketData);
        marketRepository.deleteAllByCompanySymbolIgnoreCaseIn(marketData.keySet());
        marketRepository.saveAll(newMarkets);
        return marketMapper.toMarketDto(newMarkets);
    }

}
