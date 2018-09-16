package io.github.eugenevintsiv.iextradinggrabber.services.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import io.github.eugenevintsiv.iextradinggrabber.persistant.domain.Market;
import io.github.eugenevintsiv.iextradinggrabber.persistant.repository.MarketRepository;
import io.github.eugenevintsiv.iextradinggrabber.services.client.IexTradingMarketClient;
import io.github.eugenevintsiv.iextradinggrabber.shared.enums.IexRangeType;
import io.github.eugenevintsiv.iextradinggrabber.web.rest.model.req.MarketFilerParams;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MarketServiceImplTest {

    @Mock
    private IexTradingMarketClient marketClient;

    @Mock
    private MarketRepository repository;

    @InjectMocks
    private
    MarketServiceImpl marketService;

    private Mocks mocks;

    @Before
    public void before() throws Exception {
        mocks = new Mocks();
    }

    @Test
    public void shouldReturnExistingMarketSymbols() {
        when(repository.findAll()).thenReturn(mocks.marketsWithUniqueSymbols());
        final Set<String> allSymbols = marketService.getAllSymbols();
        verify(repository, times(1)).findAll();
        assertEquals(allSymbols.size(), 2);
    }

    @Test
    public void shouldReturnEmptyList_WhenMarketsNotExists() {
        final List<Market> emptyList = Lists.newArrayList();
        when(repository.findAll()).thenReturn(emptyList);

        final Set<String> allSymbols = marketService.getAllSymbols();

        verify(repository, times(1)).findAll();
        assertEquals(allSymbols.size(), emptyList.size());
    }

    @Test
    public void shouldNotExistingDuplicatesInCaseMultipleSameMarketsExists() {
        final List<Market> marketsList = mocks.marketsWithDuplicatedSymbols();

        when(repository.findAll()).thenReturn(marketsList);
        final Set<String> allSymbols = marketService.getAllSymbols();
        verify(repository, times(1)).findAll();
        assertEquals(allSymbols.size(), 2);
    }

    @Test
    public void shouldCallIexMarketWithProperParams() {
        final MarketFilerParams filterParams = new MarketFilerParams(Sets.newHashSet("FB", "AAPL"), 1, IexRangeType.MONTH.name());
        final String shouldRequestSymbolsStr = "FB,AAPL";
        final String shouldRequestTypesStr = "quote,logo,company,price,chart";
        final String shouldRequestRangeStr = "1m";
        when(marketClient.getMarketData(anyString(), anyString(), anyString())).thenReturn(new HashMap<>());

        marketService.receiveFromExternal(filterParams);
        verify(marketClient, times(1)).getMarketData(shouldRequestSymbolsStr, shouldRequestTypesStr, shouldRequestRangeStr);
        verifyZeroInteractions(repository);
    }

    class Mocks {
        private Market fbMarket = Market.builder().companySymbol("FB").build();
        private Market aaplMarket = Market.builder().companySymbol("AAPL").build();

        List<Market> marketsWithUniqueSymbols() {
            return Lists.newArrayList(fbMarket, aaplMarket);
        }

        List<Market> marketsWithDuplicatedSymbols() {
            return Lists.newArrayList(fbMarket, aaplMarket, aaplMarket, fbMarket);
        }
    }

}