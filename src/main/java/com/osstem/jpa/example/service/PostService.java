package com.osstem.jpa.example.service;

import com.osstem.jpa.example.service.dto.PostDTO;

import java.util.List;

public interface PostService {
    void addPost(PostDTO postDTO);

    void updatePost(PostDTO postDTO);

    void removePost(Long id);

    List<PostDTO> readPost();
}
