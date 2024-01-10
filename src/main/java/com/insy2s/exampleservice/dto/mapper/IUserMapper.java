package com.insy2s.exampleservice.dto.mapper;

import com.insy2s.exampleservice.domain.User;
import com.insy2s.exampleservice.dto.request.UserPersonalUpdateRequest;
import com.insy2s.exampleservice.dto.request.UserRequest;
import com.insy2s.exampleservice.dto.response.UserResponse;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {IAddressMapper.class})
public interface IUserMapper {
    User toEntity(UserResponse userResponse);

    @AfterMapping
    default void linkAddresses(@MappingTarget User user) {
        user.getAddresses().forEach(address -> address.setUser(user));
    }

    UserResponse toDto(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(UserResponse userResponse, @MappingTarget User user);

    User toEntity(UserRequest userRequest);

    UserRequest toDto1(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(UserRequest userRequest, @MappingTarget User user);

    User toEntity(UserPersonalUpdateRequest userPersonalUpdateRequest);

    UserPersonalUpdateRequest toDto2(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(UserPersonalUpdateRequest userPersonalUpdateRequest, @MappingTarget User user);
}