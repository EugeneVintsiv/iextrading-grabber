package io.github.eugenevintsiv.iextradinggrabber.persistant.repository;

import io.github.eugenevintsiv.iextradinggrabber.persistant.domain.Market;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository("marketRepository")
public interface MarketRepository extends MongoRepository<Market, String> {
    int deleteAllByCompanySymbolIgnoreCaseIn(Set<String> companySymbols);

}
