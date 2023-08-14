package io.mikheenko.iexcloud.dto;

import lombok.Data;

@Data
public class StockInfoDto {

    private String symbol;
    private String companyName;
    private Double latestPrice;
    private Double previousClose;
    private Double changePercent;
}