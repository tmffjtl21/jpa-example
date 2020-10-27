package com.osstem.jpa.example.service.mapper;

import com.osstem.jpa.example.domain.PostComment;
import com.osstem.jpa.example.service.dto.PostCommentDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PostMapper.class})
public interface PostCommentMapper {

    PostCommentDTO toDTO(PostComment postComment);

    List<PostCommentDTO> postCommentsToPostCommentDTOs(List<PostComment> postComments);

    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    PostComment toEntity(PostCommentDTO dto);

    List<PostComment> postCommentDTOsToPostComments(List<PostCommentDTO> postCommentDTOs);

    @AfterMapping
    default public void setConstraintsOnEntity(PostCommentDTO dto, @MappingTarget PostComment entity) {
        if (entity.getId() != null && entity.getId() == 0) entity.setId(null);
    }
}
