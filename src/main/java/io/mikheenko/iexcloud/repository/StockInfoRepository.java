package io.mikheenko.iexcloud.repository;

import io.mikheenko.iexcloud.model.StockInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockInfoRepository extends JpaRepository<StockInfo, Long> {
    List<StockInfo> findTop5ByOrderByLatestPriceDesc();
    List<StockInfo> findTop5ByOrderByChangePercent();
}