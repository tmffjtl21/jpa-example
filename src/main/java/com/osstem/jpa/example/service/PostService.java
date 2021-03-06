package com.osstem.jpa.example.service;

import com.osstem.jpa.example.service.dto.PostDTO;

import java.util.List;

public interface PostService {
    PostDTO createPost(PostDTO postDTO);

    void updatePost(PostDTO postDTO);

    List<PostDTO> getAllPost();

    void removePost(Long id);

    List<PostDTO> getAllPostByFilter(String filter1, String filter2);
}
