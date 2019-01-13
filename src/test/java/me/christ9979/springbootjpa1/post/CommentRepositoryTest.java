package me.christ9979.springbootjpa1.post;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CommentRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @Autowired
    CommentRepository commentRepository;

    /**
     * 패치 전략을 테스트하기 위한 테스트
     */
    @Test
    public void entityGraphTest() {

        /**
         * 커스텀 패치 전략 적용
         */
        commentRepository.getById(1l);

        System.out.println("==========================");

        /**
         * 기본 패치 전략 사용
         */
        commentRepository.findById(1l);
    }

    /**
     * Projection을 위한 테스트
     */
    @Test
    public void projectTest() {

        Post post = new Post();
        post.setTitle("jps");
        Post savedPost = postRepository.save(post);

        Comment comment = new Comment();
        comment.setComment("spring data jpa projection");
        comment.setPost(savedPost);
        comment.setUp(10);
        comment.setDown(1);
        commentRepository.save(comment);

        commentRepository.findByPost_Id(savedPost.getId(), CommentSummary.class).forEach(c -> {
            System.out.println("=================");
            System.out.println(c.getVotes());
            System.out.println("=================");

        });

        commentRepository.findByPost_Id(savedPost.getId(), CommentOnly.class).forEach(c -> {
            System.out.println("=================");
            System.out.println(c.getComment());
            System.out.println("=================");

        });
    }
}