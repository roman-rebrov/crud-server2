package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

// Stub
@Repository
public class PostRepository {

  private final ConcurrentHashMap<Long, Post> posts = new ConcurrentHashMap<>();
  private AtomicLong ID = new AtomicLong(0);


  public List<Post> all() {
    return new ArrayList<Post>(this.posts.values());
  }

  public Optional<Post> getById(long id) {

    if (this.posts.containsKey(id)) {
      Post post = posts.get(id);
      return Optional.of(post);
    }else {
      throw new NotFoundException("Not found");
    }
  }

  public Post save(Post post) {

    Post newPost = null;
    if (post.getId() == 0L){
      long newID = ID.incrementAndGet();
      newPost = new Post(newID, post.getContent());
      this.posts.put(newID, newPost);
    }else if(post.getId() > 0L){
      if (this.posts.containsKey(post.getId())){
        this.posts.put(post.getId(), post);
        newPost = post;
      }else{
        throw new NotFoundException("Not found");
      }

    }
    return newPost;
  }

  public void removeById(long id) {
    if (this.posts.containsKey(id)) {
      posts.remove(id);
    }else {
      throw new NotFoundException("Not found");
    }
  }
}
