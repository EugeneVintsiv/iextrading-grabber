package io.github.eugenevintsiv.iextradinggrabber.services.impl;

import com.google.common.collect.Sets;
import io.github.eugenevintsiv.iextradinggrabber.persistant.repository.MarketRepository;
import io.github.eugenevintsiv.iextradinggrabber.services.client.IexTradingMarketClient;
import io.github.eugenevintsiv.iextradinggrabber.services.mapper.MarketMapper;
import io.github.eugenevintsiv.iextradinggrabber.shared.enums.IexRangeType;
import io.github.eugenevintsiv.iextradinggrabber.web.rest.model.req.MarketFilerParams;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

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

    @Mock
    private MarketMapper mapper;

    @InjectMocks
    private
    MarketServiceImpl marketService;

    @Test
    public void shouldCallIexMarketWithProperParams() {
        final MarketFilerParams filterParams = new MarketFilerParams(Sets.newHashSet("FB", "AAPL"), 1, IexRangeType.MONTH.name());
        final String shouldRequestSymbolsStr = "FB,AAPL";
        final String shouldRequestTypesStr = "quote,logo,company,price,chart";
        final String shouldRequestRangeStr = "1m";
        when(marketClient.getMarketData(anyString(), anyString(), anyString())).thenReturn(new ArrayList<>());

        marketService.receiveFromExternal(filterParams);
        verify(marketClient, times(1)).getMarketData(shouldRequestSymbolsStr, shouldRequestTypesStr, shouldRequestRangeStr);
        verifyZeroInteractions(repository);
    }

}