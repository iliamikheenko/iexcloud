package io.mikheenko.iexcloud.service.impl;

import io.mikheenko.iexcloud.mapper.StockInfoMapper;
import io.mikheenko.iexcloud.repository.StockInfoRepository;
import io.mikheenko.iexcloud.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatisticServiceImpl implements StatisticService {

    private final StockInfoRepository stockInfoRepository;
    private final StockInfoMapper stockInfoMapper;

    @Override
    @Scheduled(fixedRateString = "${fixed.statistic.delay}", initialDelay = 90000)
    public void getTopCompaniesByValue() {
        var topFivePrice = stockInfoRepository.findTop5ByOrderByLatestPriceDesc();
        System.out.println("Top five with highest price:");
        topFivePrice.stream()
                .map(stockInfoMapper::toDto)
                .forEach(System.out::println);
    }

    @Override
    @Scheduled(fixedRateString = "${fixed.statistic.delay}", initialDelay = 90000)
    public void getTopCompaniesByPercentageChange() {
        var topFivePercent = stockInfoRepository.findTop5ByOrderByChangePercent();
        System.out.println("Top five with highest dif of price:");
        topFivePercent.stream()
                .map(stockInfoMapper::toDto)
                .forEach(System.out::println);
    }
}