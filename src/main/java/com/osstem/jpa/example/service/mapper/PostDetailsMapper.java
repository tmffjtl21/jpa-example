package com.osstem.jpa.example.service.mapper;

import com.osstem.jpa.example.domain.PostDetails;
import com.osstem.jpa.example.service.dto.PostDetailsDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PostDetailsMapper {

    PostDetailsDTO toDTO(PostDetails postComment);

    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    PostDetails toEntity(PostDetailsDTO dto);

    @AfterMapping
    default public void setConstraintsOnEntity(PostDetailsDTO dto, @MappingTarget PostDetails entity) {
        if (entity.getId() != null && entity.getId() == 0) entity.setId(null);
    }
}
