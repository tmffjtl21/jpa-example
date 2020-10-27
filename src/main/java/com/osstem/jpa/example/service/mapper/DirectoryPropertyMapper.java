package com.osstem.jpa.example.service.mapper;

import com.osstem.jpa.example.domain.DirectoryProperty;
import com.osstem.jpa.example.service.dto.DirectoryPropertyDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DirectoryPropertyMapper {

    DirectoryPropertyDTO toDTO(DirectoryProperty entity);

    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    DirectoryProperty toEntity(DirectoryPropertyDTO dto);

    default DirectoryProperty DirectoryPropertyFromId(String id) {
        if (id == null) {
            return null;
        }
        DirectoryProperty that = new DirectoryProperty();
        that.setId(id);
        return that;
    }
}
