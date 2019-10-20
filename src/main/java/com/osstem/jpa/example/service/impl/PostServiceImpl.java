package com.osstem.jpa.example.service.impl;

import com.osstem.jpa.example.domain.Post;
import com.osstem.jpa.example.mapper.PostMapper;
import com.osstem.jpa.example.repository.PostRepository;
import com.osstem.jpa.example.service.PostService;
import com.osstem.jpa.example.service.dto.PostDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    private PostMapper postMapper;

    public PostServiceImpl(PostRepository postRepository, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }

    @Override
    public void addPost(PostDTO postDTO) {
        Post post = postMapper.toEntity(postDTO);
        postRepository.save(post);
    }

    @Override
    public void updatePost(PostDTO postDTO) {
        Post post = postMapper.toEntity(postDTO);
        postRepository.save(post);
    }

    @Override
    public void removePost(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public List<PostDTO> readPost() {
        return postRepository.findAll().stream().map(postMapper::toDTO).collect(Collectors.toList());
    }
}
