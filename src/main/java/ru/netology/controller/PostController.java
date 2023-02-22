package ru.netology.controller;

import org.springframework.web.bind.annotation.*;
import ru.netology.model.Post;
import ru.netology.service.PostService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
  private final PostService service;

  public PostController(PostService service) {
    this.service = service;
  }

  @GetMapping
  public List<Post> all() throws IOException {
    return service.all();
  }

  @GetMapping("/{id}")
  public Post getById(@PathVariable long id) {
      return this.service.getById(id);
  }

  @PostMapping
  public Post save(@RequestBody Post post) {
      return service.save(post);
  }
  @DeleteMapping("/{id}")
  public void removeById(@PathVariable long id) {
      this.service.removeById(id);
  }
}
