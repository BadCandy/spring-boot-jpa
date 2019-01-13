package me.christ9979.springbootjpa1.post;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByTitleStartsWith(String title);

    /**
     * 쿼리를 직접 작성하고 싶으면, @Query를 이용할수도 있다.
     */
    @Query("SELECT p FROM Post AS p WHERE p.title = ?1")
    /**
     * jpql로 만든 쿼리를 호출하기 위한 인터페이스 메서드
     */
    List<Post> findByTitle(String title);

    /**
     * @Query와 Sort 또는 Pageable도 같이 이용할수도 있다.
     */
    @Query("SELECT p FROM Post AS p WHERE p.title = ?1")
    List<Post> findByTitle(String title, Sort sort);
}
