package com.osstem.jpa.example.service.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class PostDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String title;

    private PostDetailsDTO postDetailsDTO;

    private Set<PostCommentDTO> postCommentDTOs = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public PostDetailsDTO getPostDetailsDTO() {
        return postDetailsDTO;
    }

    public void setPostDetailsDTO(PostDetailsDTO postDetailsDTO) {
        this.postDetailsDTO = postDetailsDTO;
    }

    public Set<PostCommentDTO> getPostCommentDTOs() {
        return postCommentDTOs;
    }

    public void setPostCommentDTOs(Set<PostCommentDTO> postCommentDTOs) {
        this.postCommentDTOs = postCommentDTOs;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
