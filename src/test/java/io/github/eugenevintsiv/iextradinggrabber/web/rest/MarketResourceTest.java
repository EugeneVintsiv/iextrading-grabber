package io.github.eugenevintsiv.iextradinggrabber.web.rest;

import io.github.eugenevintsiv.iextradinggrabber.services.MarketService;
import io.github.eugenevintsiv.iextradinggrabber.web.rest.model.req.MarketFilerParams;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MarketResourceTest {

    @MockBean
    private MarketService marketService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldNotProcessRequestOnAllEmptyParams() throws Exception {
        this.mockMvc.perform(get("/api/v0/market"))
//                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void shouldNotProcessRequestOnEmptySymbolsInvalidParams() throws Exception {
        final MockHttpServletRequestBuilder getRequest = get("/api/v0/market")
                .param("rangeType", "MONTH")
                .param("rangeCount", "1");
        this.mockMvc.perform(getRequest)
//                .andDo(print())
                .andExpect(status().is4xxClientError());
        verifyZeroInteractions(marketService);
    }

    @Test
    public void shouldNotProcessRequestOnEmptyRangeTypeInvalidParams() throws Exception {
        final MockHttpServletRequestBuilder getRequest = get("/api/v0/market")
                .param("symbols", "s")
                .param("rangeCount", "1");
        this.mockMvc.perform(getRequest)
//                .andDo(print())
                .andExpect(status().is4xxClientError());
        verifyZeroInteractions(marketService);
    }

    @Test
    public void shouldNotProcessRequestOnEmptyRangeCountInvalidParams() throws Exception {
        final MockHttpServletRequestBuilder getRequest = get("/api/v0/market")
                .param("symbols", "s")
                .param("rangeType", "MONTH");
        this.mockMvc.perform(getRequest)
//                .andDo(print())
                .andExpect(status().is4xxClientError());
        verifyZeroInteractions(marketService);
    }

    @Test
    public void shouldProcessRequestAllValidParams() throws Exception {
        when(marketService.receiveFromExternal(Mockito.any(MarketFilerParams.class))).thenReturn(anyList());
        final MockHttpServletRequestBuilder getRequest = get("/api/v0/market")
                .param("symbols", "aapl")
                .param("symbols", "fb")
                .param("rangeCount", "1")
                .param("rangeType", "MONTH");
        this.mockMvc.perform(getRequest).andExpect(status().isOk());
        verify(marketService, times(1)).receiveFromExternal(any());
    }
    
}