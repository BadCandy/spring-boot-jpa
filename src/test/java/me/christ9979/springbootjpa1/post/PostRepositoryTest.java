package me.christ9979.springbootjpa1.post;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * EntityManager가 Transient 상태의 entity인지 Detached 상태의 entity인지 판단하는 방법
     * 1. entity의 @Id 프로퍼티를 찾는다.
     * 2. 해당 프로퍼티의 값이 null이면 Transient 상태로 판단하고 아니면 Detached 상태로 판단한다.
     *
     * jpa의 기능을 이용하기 위해서는 persistent 상태인 entity를 이용해야 하는데,
     * 실수하지 않도록 save() 메서드가 반환하는 entity를 사용하도록 하자.
     */
    @Test
    public void save() {

        /**
         * entity가 Transient -> Persistent 상태로 변경되는 예제
         * save()를 호출할 경우 내부에서 EntityManager.persist() 메소드가 호출된다.
         * 즉 메소드에 넘긴 entity를 persistent 상태로 변경한다.
         */
        Post post = new Post();
        post.setTitle("jpa");
        Post savedPost = postRepository.save(post); // Transient -> Persistent

        assertThat(entityManager.contains(post)).isTrue();
        assertThat(entityManager.contains(savedPost)).isTrue();
        assertThat(savedPost == post);


        /**
         * entity가 Detached -> Transient 상태로 변경되는 예제
         * save()를 호출할 경우 내부에서 EntityManager.merge() 메소드가 호출된다.
         * 즉 메소드에 넘긴 entity의 복사본을 만들고, 그 복사본을 다시 persistent 상태로 변경하여
         * 그 복사본을 반환한다.
         */
        Post postUpdate = new Post();
        postUpdate.setId(post.getId());
        postUpdate.setTitle("hibernate");
        Post updatedPost = postRepository.save(postUpdate); // Detached -> Persistent

        assertThat(entityManager.contains(updatedPost)).isTrue();
        assertThat(entityManager.contains(postUpdate)).isFalse();
        assertThat(updatedPost == postUpdate);

        List<Post> all = postRepository.findAll();
        assertThat(all.size()).isEqualTo(1);
    }
}