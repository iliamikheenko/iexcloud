package io.mikheenko.iexcloud.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockInfo {

    @Id
    private String symbol;

    private Long companyId;

    private String companyName;

    private Double latestPrice;

    private Double previousClose;

    private Double changePercent;
}