package com.osstem.jpa.example.repository;

import com.osstem.jpa.example.domain.PostGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostGroupRepository extends JpaRepository<PostGroup, Long> {
    PostGroup findFirstByOrderByCreatedDateDesc();
}
