package web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import web.model.Post;
import web.model.request.CreatePostRequest;
import web.service.PostService;

//@Controller
@RestController
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    //@GetMapping(value = "/post")
    //@ResponseBody
    @GetMapping(value = "/post/{id}")
    public Post getPostById(@PathVariable long id) {
        Post post = postService.getPostById(id).orElse(null);
        return post;
    }

    // mi adunk meg egy JSON-t és az lesz az új poszt (postmanban)
    @PostMapping(value = "/post")
    public Post createNewPost(@RequestBody CreatePostRequest request) {
        return postService.createNewPost(request);
    }



    /*
    //postmanban kitöltött és újy posztként létrehozva:
    @PostMapping(value = "/post")
    public Post createNewPost(CreatePostRequest request) {
        return postService.createNewPost(request);
    }

    */

    /*
    @GetMapping(value = "/post", produces = "text/plain;charset=utf-8")
    public ResponseEntity<Post> getPostById(@RequestParam long id) {
        Post post = postService.getPostById(id).orElse(null);
        return ResponseEntity.ok().header("Hello", "World").body(post);
    }

    */
}
