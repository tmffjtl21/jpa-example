package com.osstem.jpa.example.web.rest;

import com.osstem.jpa.example.service.PostService;
import com.osstem.jpa.example.service.dto.PostDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostResource {

    private PostService postService;

    public PostResource(PostService postService) {
        this.postService = postService;
    }

    /**
     * POST  /post : Create Post
     */
    @PostMapping("/post")
    @ApiOperation( value="addPost" , notes = "1) Add Post Entity")
    public ResponseEntity<PostDTO> addPost(@RequestBody PostDTO postDTO) throws Exception {
        postService.addPost(postDTO);
        return ResponseEntity.created(null).build();
    }

    /**
     * PUT  /post : Update Post
     */
    @PutMapping("/post")
    @ApiOperation( value="updatePost" , notes = "1) Modify Post Entity")
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO) throws Exception {
        postService.updatePost(postDTO);
        return ResponseEntity.accepted().build();
    }

    /**
     * READ  /post : Read Post
     */
    @GetMapping("/post")
    @ApiOperation( value="readPost" , notes = "1) Read Post Entity")
    public ResponseEntity<List<PostDTO>> readPost() throws Exception {
        List<PostDTO> list = postService.readPost();
        return list.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(list);
    }

    /**
     * DELETE  /post : Delete Post
     */
    @DeleteMapping("/post")
    @ApiOperation( value="removePost" , notes = "1) Delete Post Entity")
    public ResponseEntity<PostDTO> removePost(@RequestParam Long id) throws Exception {
        postService.removePost(id);
        return ResponseEntity.accepted().build();
    }
}
