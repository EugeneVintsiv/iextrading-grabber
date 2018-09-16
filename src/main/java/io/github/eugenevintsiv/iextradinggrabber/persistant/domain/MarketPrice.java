package io.github.eugenevintsiv.iextradinggrabber.persistant.domain;

import io.github.eugenevintsiv.iextradinggrabber.shared.enums.PriceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "marketPrice")
public class MarketPrice {

    private PriceType type;
    private LocalDateTime time;
    private BigDecimal value;
}