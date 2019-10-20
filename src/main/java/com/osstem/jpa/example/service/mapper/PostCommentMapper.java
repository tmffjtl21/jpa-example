package com.osstem.jpa.example.service.mapper;

import com.osstem.jpa.example.domain.PostComment;
import com.osstem.jpa.example.service.dto.PostCommentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

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

    default PostComment postCommentFromId(Long id) {
        if (id == null) {
            return null;
        }
        PostComment postComment = new PostComment();
        postComment.setId(id);
        return postComment;
    }
}
