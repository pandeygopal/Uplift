package com.hack.UpLift.Services;
import com.hack.UpLift.Model.Post;
import com.hack.UpLift.Repo.postRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Arrays;

@Service
public class PostService  {

    @Autowired
    private postRepo postRepository;
    @Transactional
    public Post addPost(Post post, MultipartFile imageFile) throws IOException {
        System.out.println("add product called");
        System.out.println(imageFile.getOriginalFilename());
        post.setImageName(imageFile.getOriginalFilename());
        System.out.println(imageFile.getContentType());
        post.setImageType(imageFile.getContentType());
        //System.out.println(Arrays.toString(imageFile.getBytes()));
        post.setImageData(imageFile.getBytes());
        post.setPostLike(0);
        post.setPostComments(0);
        Post savedPost = postRepository.save(post);
        System.out.println("Post saved with ID: " + savedPost.getPostId());
        return savedPost;
    }
    public Iterable<Post> getAllPosts() {
        System.out.println("get all post service called");
        return postRepository.findAll();
    }

}
