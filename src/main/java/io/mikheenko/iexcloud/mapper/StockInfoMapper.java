package io.mikheenko.iexcloud.mapper;

import io.mikheenko.iexcloud.dto.StockInfoDto;
import io.mikheenko.iexcloud.model.StockInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StockInfoMapper {

    StockInfoDto toDto(StockInfo stockInfo);
}
