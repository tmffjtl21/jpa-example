package com.osstem.jpa.example.service.mapper;

import com.osstem.jpa.example.domain.Directory;
import com.osstem.jpa.example.service.dto.DirectoryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DirectoryMapper {

    DirectoryDTO toDTO(Directory entity);

    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    Directory toEntity(DirectoryDTO dto);

    default Directory DirectoryFromId(String id) {
        if (id == null) {
            return null;
        }
        Directory that = new Directory();
        that.setId(id);
        return that;
    }
}
