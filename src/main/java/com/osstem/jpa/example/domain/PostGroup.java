package com.osstem.jpa.example.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * The PostComment entity.
 *
 * @author LEE TAEJIN
 * @since 2019.10.18
 */
@Entity
@Table(name = "POST_GROUP")
public class PostGroup extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", length = 50)
    private String title;

    @Column(name = "content", length = 255)
    private String content;

    @ManyToOne
    @JoinColumn(name = "directory_id", foreignKey = @ForeignKey(name = "FK_upost_group_directory"))
    private Directory directory;

    @ManyToMany
    @JoinTable(name = "POST_GROUP_POST",
            joinColumns = @JoinColumn(name="post_group_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name="post_id", referencedColumnName="id"))
    private Set<Post> posts = new HashSet<>();

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Directory getDirectory() {
        return directory;
    }

    public void setDirectory(Directory directory) {
        this.directory = directory;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PostGroup that = (PostGroup) o;
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
