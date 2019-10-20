package com.osstem.jpa.example.service.impl;

import com.osstem.jpa.example.domain.Post;
import com.osstem.jpa.example.domain.PostComment;
import com.osstem.jpa.example.domain.PostDetails;
import com.osstem.jpa.example.repository.PostCommentRepository;
import com.osstem.jpa.example.repository.PostDetailsRepository;
import com.osstem.jpa.example.repository.PostRepository;
import com.osstem.jpa.example.service.PostService;
import com.osstem.jpa.example.service.dto.PostDTO;
import com.osstem.jpa.example.service.mapper.PostCommentMapper;
import com.osstem.jpa.example.service.mapper.PostDetailsMapper;
import com.osstem.jpa.example.service.mapper.PostMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    private PostMapper postMapper;

    private PostCommentRepository postCommentRepository;

    private PostCommentMapper postCommentMapper;

    private PostDetailsRepository postDetailsRepository;

    private PostDetailsMapper postDetailsMapper;

    public PostServiceImpl(PostRepository postRepository, PostMapper postMapper,
                           PostCommentRepository postCommentRepository, PostCommentMapper postCommentMapper,
                           PostDetailsRepository postDetailsRepository, PostDetailsMapper postDetailsMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
        this.postCommentRepository = postCommentRepository;
        this.postCommentMapper = postCommentMapper;
        this.postDetailsRepository = postDetailsRepository;
        this.postDetailsMapper = postDetailsMapper;
    }

    @Override
    public void createPost(PostDTO postDTO) {
        Post post = postMapper.toEntity(postDTO);
        post.setCreatedBy("tjlee");
        post.setCreatedDate(Instant.now());
        post.setLastModifiedBy("tjlee");
        post.setLastModifiedDate(Instant.now());
        final Post post1 = postRepository.save(post);

        List<PostComment> postComments = postCommentMapper.postCommentDTOsToPostComments(new ArrayList<>(postDTO.getPostCommentDTOs()));
        postComments.forEach(comment -> {
            comment.setCreatedBy("tjlee");
            comment.setCreatedDate(Instant.now());
            comment.setLastModifiedBy("tjlee");
            comment.setLastModifiedDate(Instant.now());
            comment.setPost(post1);
            postCommentRepository.save(comment);
        });

        PostDetails postDetails = postDetailsMapper.toEntity(postDTO.getPostDetailsDTO());
        postDetails.setCreatedBy("tjlee");
        postDetails.setCreatedDate(Instant.now());
        postDetails.setLastModifiedBy("tjlee");
        postDetails.setLastModifiedDate(Instant.now());
        postDetails.setPost(post1);
        postDetailsRepository.save(postDetails);
    }

    @Override
    public void updatePost(PostDTO postDTO) {
        Post post = postMapper.toEntity(postDTO);
        postRepository.save(post);
    }

    @Override
    public List<PostDTO> readPost() {
        return postRepository.findAll().stream().map(postMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public void removePost(Long id) {
        postRepository.deleteById(id);
    }
}
