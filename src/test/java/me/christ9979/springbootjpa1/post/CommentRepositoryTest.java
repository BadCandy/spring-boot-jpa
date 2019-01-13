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
    public void getComment() {

        /**
         * 커스텀 패치 전략 적용
         */
        commentRepository.getById(1l);

        System.out.println("==========================");

        /**
         * 기본 패치 전략 사용
         */
        commentRepository.findById(1l)
;    }
}