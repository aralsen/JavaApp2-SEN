package com.umututkuk.app.service.rest.weather.mapper;

import org.csystem.app.weather.repository.data.entity.PlaceInfo;
import org.mapstruct.Mapper;

@Mapper(implementationName = "PlaceInfoMapperImpl", componentModel = "spring")
public interface IPlaceInfoMapper {
    PlaceInfo toPlaceInfo(org.csystem.app.weather.repository.backup.data.entity.PlaceInfo placeInfo);
}
