package io.github.eugenevintsiv.iextradinggrabber.services.impl;

import io.github.eugenevintsiv.iextradinggrabber.persistant.repository.MarketRepository;
import io.github.eugenevintsiv.iextradinggrabber.services.MarketService;
import io.github.eugenevintsiv.iextradinggrabber.services.mapper.MarketMapper;
import io.github.eugenevintsiv.iextradinggrabber.web.rest.model.req.MarketFilerParams;
import io.github.eugenevintsiv.iextradinggrabber.web.rest.model.resp.MarketDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service("marketServiceImpl")
public class MarketServiceImpl implements MarketService {

    private final MarketMapper marketMapper;
    private final MarketRepository marketRepository;

    @Autowired
    public MarketServiceImpl(MarketMapper marketMapper, MarketRepository marketRepository) {
        this.marketMapper = marketMapper;
        this.marketRepository = marketRepository;
    }

    @Override
    public List<MarketDto> receiveFromExternal(MarketFilerParams filterParams) {
        return new ArrayList<>();
    }

}
