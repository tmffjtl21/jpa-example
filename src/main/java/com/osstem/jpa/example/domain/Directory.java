package com.osstem.jpa.example.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * The Directory entity.
 *
 * @author LEE TAEJIN
 * @since 2019.10.18
 */
@Entity
@Table(name = "DIRECTORY")
public class Directory extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false, length = 50)
    private String id;

    @Column(name = "pid", length = 50)
    private String pid;

    @Column(name = "name", length = 50)
    private String name;

    @ManyToMany
    @JoinTable(name = "POST_DIRECTORY",
            joinColumns = @JoinColumn(name="directory_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name="post_id", referencedColumnName="id"))
    private Set<Post> posts = new HashSet<>();

    @OneToMany(mappedBy = "directory", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PostGroup> postGroups = new HashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
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
        Directory that = (Directory) o;
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
