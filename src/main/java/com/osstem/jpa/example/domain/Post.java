package com.osstem.jpa.example.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.ParamDef;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * The Post entity.
 *
 * @author LEE TAEJIN
 * @since 2019.10.18
 */
@Entity
@FilterDef(name="dateDueFilter", parameters= {
        @ParamDef( name="dateDue", type="date" ),
})
@Filters( {
        @Filter(name="dateDueFilter", condition="dateDue = :dateDue"),
})
@Table(name = "POST")
public class Post extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", length = 50)
    private String title;

    @OneToMany(mappedBy = "post", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private Set<PostComment> postComments = new HashSet<>();

    @OneToOne(mappedBy = "post", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true, optional = false)
    private PostDetails postDetails;

    @ManyToMany(mappedBy = "posts")
    private Set<Directory> directories = new HashSet<>();

    @ManyToMany(mappedBy = "posts")
    private Set<PostGroup> postGroups = new HashSet<>();

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

    public Set<PostComment> getPostComments() {
        return postComments;
    }

    public void setPostComments(Set<PostComment> postComments) {
        this.postComments = postComments;
    }

    public Post addPostComment(PostComment postComment) {
        postComment.setPost(this);
        this.postComments.add(postComment);
        return this;
    }

    public PostDetails getPostDetails() {
        return postDetails;
    }

    public void setPostDetails(PostDetails postDetails) {
        if (postDetails == null) {
            if (this.postDetails != null) {
                this.postDetails.setPost(null);
            }
        } else {
            postDetails.setPost(this);
        }
        this.postDetails = postDetails;
    }

    public Set<Directory> getDirectories() {
        return directories;
    }

    public void setDirectories(Set<Directory> directories) {
        this.directories = directories;
    }

    public Set<PostGroup> getPostGroups() {
        return postGroups;
    }

    public void setPostGroups(Set<PostGroup> postGroups) {
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
        Post that = (Post) o;
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
