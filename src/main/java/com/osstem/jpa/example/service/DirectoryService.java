package com.osstem.jpa.example.service;

import com.osstem.jpa.example.service.dto.DirectoryDTO;

import java.util.List;

public interface DirectoryService {
    void createDirectory(DirectoryDTO directoryDTO);

    void updateDirectory(DirectoryDTO directoryDTO);

    List<DirectoryDTO> readDirectory();

    void removDirectory(String id);
}
