package com.osstem.jpa.example.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * The DirectoryProperty entity.
 *
 * @author LEE TAEJIN
 * @since 2019.10.18
 */
@Entity
@Table(name = "DIRECTORY_PROPERTY")
public class DirectoryProperty extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false, length = 50)
    private String id;

    @Column(name = "show_poster")
    private Boolean showPoster;

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Directory directory;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getShowPoster() {
        return showPoster;
    }

    public void setShowPoster(Boolean showPoster) {
        this.showPoster = showPoster;
    }

    public Directory getDirectory() {
        return directory;
    }

    public void setDirectory(Directory directory) {
        this.directory = directory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DirectoryProperty that = (DirectoryProperty) o;
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
