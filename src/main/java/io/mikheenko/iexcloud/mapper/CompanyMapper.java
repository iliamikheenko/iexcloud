package io.mikheenko.iexcloud.mapper;

import io.mikheenko.iexcloud.dto.CompanyDto;
import io.mikheenko.iexcloud.model.Company;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    Company toEntity(CompanyDto companyDto);
}
