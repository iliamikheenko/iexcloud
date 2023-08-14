package io.mikheenko.iexcloud.feign;

import io.mikheenko.iexcloud.dto.CompanyDto;
import io.mikheenko.iexcloud.model.StockInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "iexapis", url = "https://cloud.iexapis.com")
public interface StockInfoClient {

    @GetMapping("/stable/ref-data/symbols")
    List<CompanyDto> fetchCompanyInfo(@RequestParam("token") String token);

    @GetMapping("/stable/stock/{symbol}/quote")
    StockInfo getStockInfo(@PathVariable("symbol") String symbol,
                           @RequestParam("token") String token);
}