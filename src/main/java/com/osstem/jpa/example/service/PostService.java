package com.osstem.jpa.example.service;

import com.osstem.jpa.example.service.dto.PostDTO;

import java.util.List;

public interface PostService {
    void createPost(PostDTO postDTO);

    void updatePost(PostDTO postDTO);

    List<PostDTO> readPost();

    void removePost(Long id);
}
