package com.osstem.jpa.example.mapper;

import com.osstem.jpa.example.domain.Post;
import com.osstem.jpa.example.service.dto.PostDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.Instant;

@Mapper(componentModel = "spring")
public interface PostMapper {

    PostDTO toDTO(Post entity);

    @Mapping(target = "createdBy", constant = "tjlee")
    @Mapping(target = "createdDate", defaultExpression = "java(getNow)")
    @Mapping(target = "lastModifiedBy", constant = "tjlee")
    @Mapping(target = "lastModifiedDate", defaultExpression = "java(getNow)")
    Post toEntity(PostDTO dto);

    default Instant getNow(){
        return Instant.now();
    }
}
