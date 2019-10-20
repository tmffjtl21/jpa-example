package com.osstem.jpa.example.repository;

import com.osstem.jpa.example.domain.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostCommentRepository extends JpaRepository<PostComment, Long> {
}
