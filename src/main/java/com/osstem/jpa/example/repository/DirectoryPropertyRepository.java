package com.osstem.jpa.example.repository;

import com.osstem.jpa.example.domain.DirectoryProperty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectoryPropertyRepository extends JpaRepository<DirectoryProperty, String> {
}
