package com.osstem.jpa.example.service.mapper;

import com.osstem.jpa.example.domain.Post;
import com.osstem.jpa.example.service.dto.PostDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {PostDetailsMapper.class, PostCommentMapper.class})
public interface PostMapper {

    @Mapping(target = "postCommentDTOs", source = "postComments")
    @Mapping(target = "postDetailsDTO", source = "postDetails")
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
}
