package io.github.eugenevintsiv.iextradinggrabber.persistant.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "market")
public class Market {

    @Id
    private String id;

    private String companySymbol;
    private String companyName;
    private String sector;
    private String logoUrl;
    private List<MarketPrice> prices;
    private List<MarketPriceChart> priceCharts;

}
