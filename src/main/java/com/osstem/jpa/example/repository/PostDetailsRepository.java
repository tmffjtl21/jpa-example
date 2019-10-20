package com.osstem.jpa.example.repository;

import com.osstem.jpa.example.domain.PostDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostDetailsRepository extends JpaRepository<PostDetails, Long> {
}
