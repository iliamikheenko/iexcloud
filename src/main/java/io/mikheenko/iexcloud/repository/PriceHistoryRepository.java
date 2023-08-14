package io.mikheenko.iexcloud.repository;

import io.mikheenko.iexcloud.model.PriceHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceHistoryRepository extends JpaRepository<PriceHistory, Long> {
}