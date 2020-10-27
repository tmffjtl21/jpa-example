package com.osstem.jpa.example.repository;

import com.osstem.jpa.example.domain.Directory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectoryRepository extends JpaRepository<Directory, String> {
}
