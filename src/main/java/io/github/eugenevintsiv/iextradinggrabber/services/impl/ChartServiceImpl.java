package io.github.eugenevintsiv.iextradinggrabber.services.impl;

import io.github.eugenevintsiv.iextradinggrabber.persistant.domain.Market;
import io.github.eugenevintsiv.iextradinggrabber.persistant.domain.MarketPriceChart;
import io.github.eugenevintsiv.iextradinggrabber.persistant.repository.MarketRepository;
import io.github.eugenevintsiv.iextradinggrabber.services.ChartService;
import io.github.eugenevintsiv.iextradinggrabber.services.mapper.MarketMapper;
import io.github.eugenevintsiv.iextradinggrabber.web.rest.model.resp.MarketPriceChartDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    public List<MarketPriceChartDto> getCharts(Set<String> companySymbols, LocalDate from, LocalDate to) {
        List<Market> allByCompanySymbol = marketRepository.findAllByCompanySymbolIgnoreCaseIn(companySymbols);
        allByCompanySymbol = filterForCharts(from, to, allByCompanySymbol);
        final List<MarketPriceChartDto> result = marketMapper.toPriceChartDto(allByCompanySymbol);
        result.sort(sortComparator);
        return result;
    }

    private List<Market> filterForCharts(LocalDate from, LocalDate to, List<Market> allByCompanySymbol) {
        if (from != null || to != null) {
            allByCompanySymbol = allByCompanySymbol.stream()
                    .peek(m -> m.setPriceCharts(availablePerFilter(m, from, to)))
                    .filter(market -> !CollectionUtils.isEmpty(market.getPriceCharts()))
                    .collect(Collectors.toList());
        }
        return allByCompanySymbol;
    }

    private List<MarketPriceChart> availablePerFilter(Market m, LocalDate from, LocalDate to) {
        return m.getPriceCharts().stream().filter(c -> expressionForDates(from, to, c)).collect(Collectors.toList());
    }

    private boolean expressionForDates(LocalDate from, LocalDate to, MarketPriceChart c) {
        boolean expression = true;
        if (from != null) expression = from.isBefore(c.getDate()) || from.isEqual(c.getDate());
        if (to != null) expression = expression && (to.isAfter(c.getDate()) || to.isEqual(c.getDate()));
        return expression;
    }
}
