package com.osstem.jpa.example.service.impl;

import com.osstem.jpa.example.domain.Post;
import com.osstem.jpa.example.domain.PostComment;
import com.osstem.jpa.example.domain.PostDetails;
import com.osstem.jpa.example.repository.PostCommentRepository;
import com.osstem.jpa.example.repository.PostDetailsRepository;
import com.osstem.jpa.example.repository.PostGroupRepository;
import com.osstem.jpa.example.repository.PostRepository;
import com.osstem.jpa.example.service.PostService;
import com.osstem.jpa.example.service.dto.PostDTO;
import com.osstem.jpa.example.service.mapper.PostMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
    private PostGroupRepository postGroupRepository;

    @Autowired
    private PostMapper postMapper;

    @Test
    public void POST_POSTDETAILS_POSTCOMMENT_한번에_들어가도록() {
        /**
         * 이와같이 실행 시 3개의 인서트와 2번의 getGeneratedKeys 쿼리가 발생한다.
         * 1. insert into POST
         * 3. insert into POST_COMMENT
         * 5. insert into POST_DETAILS
         *
         * 별개로 Post에서 CascadeType.PERSIST를 제거한 후 아래를 실행하면 PostComment를 먼저 저장하라는 오류 발생
         * 참조 : https://vladmihalcea.com/the-best-way-to-map-a-onetoone-relationship-with-jpa-and-hibernate/
         */
        Post post = new Post();
        post.setTitle("test1");
        PostDetails postDetails = new PostDetails();
        postDetails.setContent("content1");
        post.setPostDetails(postDetails);
        PostComment postComment = new PostComment();
        postComment.setReview("review1");
        post.addPostComment(postComment);
        postRepository.save(post);
//        /**
//         * PostGroup N:N 양방향 매핑 추가
//         * 1. insert into POST_GROUP
//         * 2. insert into POST_GROUP_POST
//         */
//        PostGroup postGroup = new PostGroup();
//        postGroup.setTitle("postGroup1");
//        postGroup.setContent("postGroupContent1");
//        post.addPostGroup(postGroup);
//        postRepository.save(post);
    }

    @Test
    public void POST를_지우면_POSTCOMMENT와_POSTDETAILS까_함께_지워지도록() {
//        Post post = postRepository.findFirstByOrderByCreatedDateDesc();
//        postRepository.delete(post);
//        assertThat(postRepository.findById(post.getId()).isPresent()).isFalse();
//        assertThat(postCommentRepository.findById(post.getId()).isPresent()).isFalse();
//        assertThat(postDetailsRepository.findById(post.getId()).isPresent()).isFalse();
//        PostGroup postGroup = postGroupRepository.findFirstByOrderByCreatedDateDesc();
//        postGroupRepository.delete(postGroup);

        Post post = postRepository.findFirstByOrderByCreatedDateDesc();
//        post.setPostDetails(null);
        postRepository.delete(post);
    }

    @Test
    public void POST를_수정() {
        Post post = postRepository.findFirstByOrderByCreatedDateDesc();
        post.setTitle("test2");
        PostDTO result = postMapper.toDTO(postRepository.findFirstByOrderByCreatedDateDesc());
    }
//
//    @Test
//    public void POST_COMMENT만_수정() {
//        PostDTO postDTO = defaultDataInsert();
//        PostCommentDTO postCommentDTO = postDTO.getPostComments().iterator().next();
//        postCommentDTO.setReview("review2");
//        postDTO.getPostComments().clear();
//        postDTO.getPostComments().add(postCommentDTO);
//        postService.updatePost(postDTO);
//        PostDTO result = postMapper.toDTO(postRepository.findById(postDTO.getId()).get());
//
//        assertThat(result.getTitle()).isEqualTo("test1");
//        assertThat(result.getPostComments().iterator().next().getReview()).isEqualTo("review2");
//        assertThat(result.getPostDetailsDTO().getContent()).isEqualTo("content1");
//    }
//
//    @Test
//    public void POST_DETAILS만_수정() {
//        PostDTO postDTO = defaultDataInsert();
//        postDTO.getPostDetailsDTO().setContent("content2");
//        postService.updatePost(postDTO);
//        PostDTO result = postMapper.toDTO(postRepository.findById(postDTO.getId()).get());
//
//        assertThat(result.getTitle()).isEqualTo("test1");
//        assertThat(result.getPostComments().iterator().next().getReview()).isEqualTo("review1");
//        assertThat(result.getPostDetailsDTO().getContent()).isEqualTo("content2");
//    }
//
//    @Test
//    public void POST를_지우면_POSTCOMMENT와_POSTDETAILS까_함께_지워지도록() {
//        PostDTO postDTO = defaultDataInsert();
//        postService.removePost(postDTO.getId());
//        Optional<Post> post = postRepository.findById(postDTO.getId());
//        Optional<PostComment> postComment = postCommentRepository.findById(postDTO.getPostDetailsDTO().getId());
//        Optional<PostDetails> postDetails = postDetailsRepository.findById(postDTO.getPostComments().iterator().next().getId());
//
//        assertThat(post.isPresent()).isFalse();
//        assertThat(postComment.isPresent()).isFalse();
//        assertThat(postDetails.isPresent()).isFalse();
//    }
//
//    @Test
//    public void POST를_조회하면_POSTCOMMENT와_POSTDETAILS가_함께_나오도록() {
//        PostDTO postDTO = defaultDataInsert();
//
//        assertThat(postDTO.getTitle()).isEqualTo("test1");
//        assertThat(postDTO.getPostComments().iterator().next().getReview()).isEqualTo("review1");
//        assertThat(postDTO.getPostDetailsDTO().getContent()).isEqualTo("content1");
//    }

}