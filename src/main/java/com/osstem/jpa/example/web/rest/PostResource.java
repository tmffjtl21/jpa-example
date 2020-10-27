package com.osstem.jpa.example.web.rest;

import com.osstem.jpa.example.service.PostService;
import com.osstem.jpa.example.service.dto.PostDTO;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class PostResource {

    private final Logger log = LoggerFactory.getLogger(PostResource.class);

    private static final String ENTITY_NAME = "post";

    private PostService postService;

    public PostResource(PostService postService) {
        this.postService = postService;
    }

    /**
     * POST  /post : Create Post
     *
     * @param ctx the PostDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body null, or with status 400 (Bad Request) if the ctx has ID
     * @throws Exception if the Exception occurred
     */
    @PostMapping("/post")
    @ApiOperation(value = "createPost", notes = "1) Create Post Entity")
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO ctx) throws Exception {

        log.debug("REST request to createPost: {}", ctx);
        PostDTO postDTO = postService.createPost(ctx);
        return ResponseEntity.created(new URI("")).body(postDTO);
    }

    /**
     * PUT  /post : Update Post
     *
     * @param ctx the PostDTO to Update
     * @return the ResponseEntity with status 202 (Accepted) and with body null, or with status 400 (Bad Request) if the ctx don't have ID
     * @throws Exception if the Exception occurred
     */
    @PutMapping("/post")
    @ApiOperation(value = "updatePost", notes = "1) Update Post Entity")
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO ctx) throws Exception {
        log.debug("REST request to updatePost: {}", ctx);
        postService.updatePost(ctx);
        return ResponseEntity.accepted().body(null);
    }

    /**
     * READ  /post : Read All Post
     *
     * @return the ResponseEntity with status 200 (OK) and the list of post in body
     * @throws Exception
     */
    @GetMapping("/post")
    @ApiOperation(value = "getAllPost", notes = "1) Read Post Entity")
    public ResponseEntity<List<PostDTO>> getAllPost() throws Exception {
        List<PostDTO> list = postService.getAllPost();
        return list.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(list);
    }

    /**
     * DELETE  /post/{id} : Delete Post
     *
     * @param id the PostDTO to Delete
     * @return the ResponseEntity with status 202 (Accepted) and with body null, or with status 400 (Bad Request) if the ctx don't have ID
     * @throws Exception if the Exception occurred
     */
    @DeleteMapping("/post/{id}")
    @ApiOperation(value = "removePost", notes = "1) Delete Post Entity")
    public ResponseEntity<PostDTO> removePost(@PathVariable Long id) throws Exception {
        postService.removePost(id);
        return ResponseEntity.accepted().body(null);
    }

    /**
     * READ  /post : Read All Post
     *
     * @return the ResponseEntity with status 200 (OK) and the list of post in body
     * @throws Exception
     */
    @GetMapping("/post/filtering")
    @ApiOperation(value = "getAllPost", notes = "1) Read Post Entity By Filter")
    public ResponseEntity<List<PostDTO>> getAllPost(@RequestParam(required = false) String filter1,
                                                    @RequestParam(required = false) String filter2) throws Exception {
        List<PostDTO> list = postService.getAllPostByFilter(filter1, filter2);
        return list.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(list);
    }
}
