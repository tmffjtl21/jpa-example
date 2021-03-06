package com.osstem.jpa.example.service.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class PostDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String title;

    private PostDetailsDTO postDetails;

    private Set<PostCommentDTO> postComments = new HashSet<>();

    private Set<PostGroupDTO> postGroups = new HashSet<>();

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

    public PostDetailsDTO getPostDetails() {
        return postDetails;
    }

    public void setPostDetails(PostDetailsDTO postDetails) {
        this.postDetails = postDetails;
    }

    public Set<PostCommentDTO> getPostComments() {
        return postComments;
    }

    public void setPostComments(Set<PostCommentDTO> postComments) {
        this.postComments = postComments;
    }

    public Set<PostGroupDTO> getPostGroups() {
        return postGroups;
    }

    public void setPostGroups(Set<PostGroupDTO> postGroups) {
        this.postGroups = postGroups;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PostDTO that = (PostDTO) o;
        if (that.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
