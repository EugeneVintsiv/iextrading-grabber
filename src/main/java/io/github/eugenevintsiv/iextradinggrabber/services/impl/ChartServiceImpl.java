package io.github.eugenevintsiv.iextradinggrabber.services.impl;

import io.github.eugenevintsiv.iextradinggrabber.persistant.domain.Market;
import io.github.eugenevintsiv.iextradinggrabber.persistant.repository.MarketRepository;
import io.github.eugenevintsiv.iextradinggrabber.services.ChartService;
import io.github.eugenevintsiv.iextradinggrabber.services.mapper.MarketMapper;
import io.github.eugenevintsiv.iextradinggrabber.web.rest.model.resp.MarketPriceChartDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

@Transactional
@Service("chartServiceImpl")
public class ChartServiceImpl implements ChartService {

    private final MarketMapper marketMapper;
    private final MarketRepository marketRepository;
    private final Comparator<MarketPriceChartDto> sortComparator = Comparator.comparing(MarketPriceChartDto::getCompanySymbol)
            .thenComparing(MarketPriceChartDto::getDate).reversed();

    @Autowired
    public ChartServiceImpl(MarketMapper marketMapper, MarketRepository marketRepository) {
        this.marketMapper = marketMapper;
        this.marketRepository = marketRepository;
    }

    @Override
    public List<MarketPriceChartDto> getCharts(Set<String> companySymbols) {
        final List<Market> allByCompanySymbol = marketRepository.findAllByCompanySymbolIgnoreCaseIn(companySymbols);
        final List<MarketPriceChartDto> result = marketMapper.toPriceChartDto(allByCompanySymbol);
        result.sort(sortComparator);
        return result;
    }
}
