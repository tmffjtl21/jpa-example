package com.osstem.jpa.example.repository;

import com.osstem.jpa.example.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Post findFirstByOrderByCreatedDateDesc();
}
