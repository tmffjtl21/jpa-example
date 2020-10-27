package com.osstem.jpa.example.service.impl;

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
import java.util.List;
import java.util.Optional;
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
    public PostDTO createPost(PostDTO postDTO) {
        return postMapper.toDTO(postRepository.save(postMapper.toEntity(postDTO)));
    }

    @Override
    public void updatePost(PostDTO postDTO) {
        postRepository.findById(postDTO.getId()).ifPresent(post -> post.setTitle(postDTO.getTitle()));
        Optional.ofNullable(postDTO.getPostComments())
                .ifPresent(comments -> comments
                        .forEach(commentDTO -> postCommentRepository.findById(commentDTO.getId())
                                .ifPresent(comment -> comment.setReview(commentDTO.getReview()))));
        Optional.ofNullable(postDTO.getPostDetails())
                .ifPresent(s -> postDetailsRepository.findById(s.getId())
                        .ifPresent(detail -> detail.setContent(s.getContent())));
    }

    @Override
    public List<PostDTO> getAllPost() {
        return postRepository.findAll().stream().map(postMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public void removePost(Long id) {
        postRepository.deleteById(id);
    }
}
