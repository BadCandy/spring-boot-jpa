package me.christ9979.springbootjpa1.post;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static me.christ9979.springbootjpa1.post.CommentSpecs.isBest;
import static me.christ9979.springbootjpa1.post.CommentSpecs.isGood;

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

    /**
     * Specifications를 이용한 쿼리 메서드 호출 테스트
     * 코드가 깔끔해지고 명확해진다는 장점이 있다.
     *
     * CommentSpecs에서 정의한 Specifications를 사용한다.
     * QueryDsl에 더불어 괜찮은 유지보수나 확장성에 있어 방법이다.
     */
    @Test
    public void specsTest() {
        commentRepository.findAll(isBest().or(isGood()), PageRequest.of(0, 10));
    }

    /**
     * QBE(Query By Example)을 이용한 쿼리 메소드 테스트.
     * QBE란 Example을 만들어서 동적으로 쿼리를 실행하는 기술이다.
     *
     * Example = Probe + ExampleMatcher
     * Probe : 필드에 어떤 값들을 가지고 있는 도메인 객체
     * ExampleMatcher : Prove에 들어있는 그 필드의 값들을 어떻게 쿼리할 데이터와 비교할지 정의
     *
     * nested 또는 프로퍼티 그룹 제약 조건을 못 만들고, 문자열을 제외한 타입에 대한
     * 조건을 수행하는것이 너무 제한적이여서 많이 사용하지 않을 것이다..
     */
    @Test
    public void queryByExampleTest() {

        Comment prove = new Comment();
        prove.setBest(true);

        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny()
                .withIgnorePaths("up", "down");

        Example<Comment> example = Example.of(prove, exampleMatcher);

        commentRepository.findAll(example);
    }
}