package io.mikheenko.iexcloud.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CompanyDto {

    @JsonProperty("symbol")
    private String symbol;
    @JsonProperty("isEnabled")
    private boolean isEnabled;

}