package web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import web.model.Post;
import web.service.PostService;

//@Controller
@RestController
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    //@GetMapping(value = "/post")
    @ResponseBody
    @GetMapping(value = "/post/{id}")
    public Post getPostById(@PathVariable long id) {
        Post post = postService.getPostById(id).orElse(null);
        return post;
    }

    /*
    @GetMapping(value = "/post", produces = "text/plain;charset=utf-8")
    public ResponseEntity<Post> getPostById(@RequestParam long id) {
        Post post = postService.getPostById(id).orElse(null);
        return ResponseEntity.ok().header("Hello", "World").body(post);
    }

    */
}
