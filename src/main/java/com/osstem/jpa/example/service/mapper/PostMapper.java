package com.osstem.jpa.example.service.mapper;

import com.osstem.jpa.example.domain.Post;
import com.osstem.jpa.example.service.dto.PostDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Optional;

@Mapper(componentModel = "spring", uses = {PostDetailsMapper.class, PostCommentMapper.class, DirectoryMapper.class})
public interface PostMapper {

    PostDTO toDTO(Post entity);

    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    Post toEntity(PostDTO dto);

    default Post postFromId(Long id) {
        if (id == null) {
            return null;
        }
        Post that = new Post();
        that.setId(id);
        return that;
    }

    @AfterMapping
    default public void setConstraintsOnEntity(PostDTO dto, @MappingTarget Post entity) {
        // entity 양방향 관계 등록
        Optional.ofNullable(entity.getPostDetails())
                .ifPresent(postDetails -> postDetails.setPost(entity));
        Optional.ofNullable(entity.getPostComments())
                .ifPresent(postComments -> postComments.forEach(comment -> comment.setPost(entity)));
        Optional.ofNullable(entity.getPostGroups())
                .ifPresent(postGroups -> postGroups.forEach(group -> group.addPost(entity)));
    }

    @AfterMapping
    default public void setConstraintsOnDTO(Post entity, @MappingTarget PostDTO dto) {
    }
}
