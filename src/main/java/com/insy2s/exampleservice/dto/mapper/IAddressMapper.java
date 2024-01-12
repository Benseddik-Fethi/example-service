package com.insy2s.exampleservice.dto.mapper;

import com.insy2s.exampleservice.domain.Address;
import com.insy2s.exampleservice.dto.response.AddressResponse;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface IAddressMapper {
    Address toEntity(AddressResponse addressResponse);

    AddressResponse toDto(Address address);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Address partialUpdate(AddressResponse addressResponse, @MappingTarget Address address);
}
