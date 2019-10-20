package com.osstem.jpa.example.service.impl;

import com.osstem.jpa.example.domain.Post;
import com.osstem.jpa.example.domain.PostComment;
import com.osstem.jpa.example.domain.PostDetails;
import com.osstem.jpa.example.repository.PostCommentRepository;
import com.osstem.jpa.example.repository.PostDetailsRepository;
import com.osstem.jpa.example.repository.PostRepository;
import com.osstem.jpa.example.service.PostService;
import com.osstem.jpa.example.service.dto.PostCommentDTO;
import com.osstem.jpa.example.service.dto.PostDTO;
import com.osstem.jpa.example.service.dto.PostDetailsDTO;
import com.osstem.jpa.example.service.mapper.PostMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostServiceImplTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostCommentRepository postCommentRepository;

    @Autowired
    private PostDetailsRepository postDetailsRepository;

    @Autowired
    private PostMapper postMapper;

    @Test
    public void POST_POSTDETAILS_POSTCOMMENT_한번에_들어가도록() {
        PostDTO postDTO = defaultDataInsert();
        assertThat(postDTO.getPostCommentDTOs().size()).isEqualTo(1);
        assertThat(postDTO.getPostDetailsDTO().getContent()).isEqualTo("content1");
    }

    @Test
    public void POST를_수정() {
        PostDTO postDTO = defaultDataInsert();
        postDTO.setTitle("test2");
        postService.updatePost(postDTO);
        PostDTO result = postMapper.toDTO(postRepository.findById(postDTO.getId()).get());

        assertThat(result.getTitle()).isEqualTo("test2");
        assertThat(result.getPostCommentDTOs().iterator().next().getReview()).isEqualTo("review1");
        assertThat(result.getPostDetailsDTO().getContent()).isEqualTo("content1");
    }

    @Test
    public void POST_COMMENT만_수정() {
        PostDTO postDTO = defaultDataInsert();

        logger.error("POST_COMMENT만_수정 ID : {}", postDTO.getId());

        PostCommentDTO postCommentDTO = postDTO.getPostCommentDTOs().iterator().next();
        postCommentDTO.setReview("review2");
        postDTO.getPostCommentDTOs().clear();
        postDTO.getPostCommentDTOs().add(postCommentDTO);
        postService.updatePost(postDTO);
        PostDTO result = postMapper.toDTO(postRepository.findById(postDTO.getId()).get());

        assertThat(result.getTitle()).isEqualTo("test1");
        assertThat(result.getPostCommentDTOs().iterator().next().getReview()).isEqualTo("review2");
        assertThat(result.getPostDetailsDTO().getContent()).isEqualTo("content1");
    }

    @Test
    public void POST_DETAILS만_수정() {
        PostDTO postDTO = defaultDataInsert();

        logger.error("POST_DETAILS만_수정 ID : {}", postDTO.getId());

        postDTO.getPostDetailsDTO().setContent("content2");
        postService.updatePost(postDTO);
        PostDTO result = postMapper.toDTO(postRepository.findById(postDTO.getId()).get());

        assertThat(result.getTitle()).isEqualTo("test1");
        assertThat(result.getPostCommentDTOs().iterator().next().getReview()).isEqualTo("review1");
        assertThat(result.getPostDetailsDTO().getContent()).isEqualTo("content2");
    }

    @Test
    public void POST를_지우면_POSTCOMMENT와_POSTDETAILS까_함께_지워지도록() {
        PostDTO postDTO = defaultDataInsert();
        postService.removePost(postDTO.getId());
        Optional<Post> post = postRepository.findById(postDTO.getId());
        Optional<PostComment> postComment = postCommentRepository.findById(postDTO.getPostDetailsDTO().getId());
        Optional<PostDetails> postDetails = postDetailsRepository.findById(postDTO.getPostCommentDTOs().iterator().next().getId());

        assertThat(post.isPresent()).isFalse();
        assertThat(postComment.isPresent()).isFalse();
        assertThat(postDetails.isPresent()).isFalse();
    }

    @Test
    public void POST를_조회하면_POSTCOMMENT와_POSTDETAILS가_함께_나오도록() {
        PostDTO postDTO = defaultDataInsert();

        assertThat(postDTO.getTitle()).isEqualTo("test1");
        assertThat(postDTO.getPostCommentDTOs().iterator().next().getReview()).isEqualTo("review1");
        assertThat(postDTO.getPostDetailsDTO().getContent()).isEqualTo("content1");
    }

    private PostDTO defaultDataInsert(){
        PostDTO postDTO = new PostDTO();
        postDTO.setTitle("test1");
        PostCommentDTO postCommentDTO = new PostCommentDTO();
        postCommentDTO.setReview("review1");
        Set<PostCommentDTO> postCommentDTOS = new HashSet<>();
        postCommentDTOS.add(postCommentDTO);
        postDTO.setPostCommentDTOs(postCommentDTOS);
        PostDetailsDTO postDetailsDTO = new PostDetailsDTO();
        postDetailsDTO.setContent("content1");
        postDTO.setPostDetailsDTO(postDetailsDTO);
        PostDTO returnDTO = postService.createPost(postDTO);
        return postMapper.toDTO(postRepository.findById(returnDTO.getId()).get());
    }
}