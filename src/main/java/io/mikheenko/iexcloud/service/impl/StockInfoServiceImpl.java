package io.mikheenko.iexcloud.service.impl;

import io.mikheenko.iexcloud.dto.CompanyDto;
import io.mikheenko.iexcloud.feign.StockInfoClient;
import io.mikheenko.iexcloud.mapper.CompanyMapper;
import io.mikheenko.iexcloud.model.Company;
import io.mikheenko.iexcloud.model.StockInfo;
import io.mikheenko.iexcloud.repository.CompanyRepository;
import io.mikheenko.iexcloud.repository.StockInfoRepository;
import io.mikheenko.iexcloud.service.StockInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Service
@RequiredArgsConstructor
class StockInfoServiceImpl implements StockInfoService {

    private final CompanyMapper companyMapper;
    private final StockInfoClient stockInfoClient;
    private final CompanyRepository companyRepository;
    private final StockInfoRepository stockInfoRepository;
    private final ExecutorService executorService = Executors.newFixedThreadPool(20);

    @Value("${batch.size}")
    private int batchSize;

    @Value("${company.info.token}")
    private String token;

    @Override
    @EventListener(ApplicationReadyEvent.class)
    public void fetchSymbolsAndSave() {
        List<CompanyDto> companies = stockInfoClient.fetchCompanyInfo(token);
        companies.stream()
                //TODO check mapper here
                //.map(companyMapper::toEntity)
                .map(c -> Company.builder().symbol(c.getSymbol()).isEnabled(c.isEnabled()).build())
                .limit(150)
                .forEach(companyRepository::save);
        fetchAndSaveStockInfo();
    }

    private void fetchAndSaveStockInfo(){
        while (true){
            List<Company> savedCompanies = companyRepository.findAllByIsEnabledTrue();

            for (int i = 0; i < savedCompanies.size(); i += batchSize) {
                List<Company> batch = savedCompanies.subList(i, Math.min(i + batchSize, savedCompanies.size()));
                fetchAndSaveStockInfoAsync(batch);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private void fetchAndSaveStockInfoAsync(List<Company> companies) {
        List<CompletableFuture<Void>> futures = companies.stream()
                .map(this::fetchAndSaveStockInfoAsync)
                .toList();

        CompletableFuture<Void> allOf = CompletableFuture.allOf(
                futures.toArray(new CompletableFuture[0])
        );

        allOf.join();
    }

    private CompletableFuture<Void> fetchAndSaveStockInfoAsync(Company company) {
        return CompletableFuture.runAsync(() -> {
            StockInfo stockInfo = stockInfoClient.getStockInfo(company.getSymbol(), token);
            stockInfo.setCompanyId(company.getId());
            stockInfoRepository.save(stockInfo);
        }, executorService);
    }
}