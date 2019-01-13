package me.christ9979.springbootjpa1.post;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PostControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    PostRepository postRepository;

    @Test
    public void getPost() throws Exception {

        Post post = new Post();
        post.setTitle("jpa");
        postRepository.save(post);

        mockMvc.perform(get("/posts/" + post.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("jpa"));
    }

    @Test
    public void getPosts() throws Exception {

        createPosts();

        mockMvc.perform(get("/posts/")
                .param("page", "0")
                .param("size", "10")
                .param("sort", "created,desc")
                .param("sort", "title"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].title", is("jpa")));
    }

    @Test
    public void getPostsByHateoas() throws Exception {

        createPosts();

        mockMvc.perform(get("/posts/hateoas")
                .param("page", "0")
                .param("size", "10")
                .param("sort", "created,desc")
                .param("sort", "title"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].title", is("jpa")));
    }

    private void createPosts() {

        int postsCount = 100;
        while (postsCount > 0) {
            Post post = new Post();
            post.setTitle("jpa");
            postRepository.save(post);
            postsCount--;
        }
    }
}