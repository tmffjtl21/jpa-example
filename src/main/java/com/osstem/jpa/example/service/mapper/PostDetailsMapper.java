package com.osstem.jpa.example.service.mapper;

import com.osstem.jpa.example.domain.PostDetails;
import com.osstem.jpa.example.service.dto.PostDetailsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {PostMapper.class})
public interface PostDetailsMapper {

    PostDetailsDTO toDTO(PostDetails postComment);

    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    PostDetails toEntity(PostDetailsDTO dto);

    default PostDetails postDetailsFromId(Long id) {
        if (id == null) {
            return null;
        }
        PostDetails postDetails = new PostDetails();
        postDetails.setId(id);
        return postDetails;
    }
}
