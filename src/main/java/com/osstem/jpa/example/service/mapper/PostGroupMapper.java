package com.osstem.jpa.example.service.mapper;

import com.osstem.jpa.example.domain.PostGroup;
import com.osstem.jpa.example.service.dto.PostGroupDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PostGroupMapper {

    PostGroupDTO toDTO(PostGroup entity);

    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    PostGroup toEntity(PostGroupDTO dto);

    @AfterMapping
    default public void setConstraintsOnEntity(PostGroupDTO dto, @MappingTarget PostGroup entity) {
        if (entity.getId() != null && entity.getId() == 0) entity.setId(null);
    }
}
