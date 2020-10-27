package com.osstem.jpa.example.service.impl;

import com.osstem.jpa.example.repository.DirectoryPropertyRepository;
import com.osstem.jpa.example.repository.DirectoryRepository;
import com.osstem.jpa.example.service.DirectoryService;
import com.osstem.jpa.example.service.dto.DirectoryDTO;
import com.osstem.jpa.example.service.mapper.DirectoryMapper;
import com.osstem.jpa.example.service.mapper.DirectoryPropertyMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DirectoryServiceImpl implements DirectoryService {

    private DirectoryRepository directoryRepository;

    private DirectoryMapper directoryMapper;

    private DirectoryPropertyRepository directoryPropertyRepository;

    private DirectoryPropertyMapper directoryPropertyMapper;

    public DirectoryServiceImpl(DirectoryMapper directoryMapper, DirectoryRepository directoryRepository,
                                DirectoryPropertyMapper directoryPropertyMapper, DirectoryPropertyRepository directoryPropertyRepository) {
        this.directoryMapper = directoryMapper;
        this.directoryRepository = directoryRepository;
        this.directoryPropertyMapper = directoryPropertyMapper;
        this.directoryPropertyRepository = directoryPropertyRepository;
    }

    @Override
    public void createDirectory(DirectoryDTO directoryDTO) {
        directoryRepository.save(directoryMapper.toEntity(directoryDTO));
    }

    @Override
    public void updateDirectory(DirectoryDTO directoryDTO) {

    }

    @Override
    public List<DirectoryDTO> readDirectory() {
        return null;
    }

    @Override
    public void removDirectory(String id) {

    }
}
