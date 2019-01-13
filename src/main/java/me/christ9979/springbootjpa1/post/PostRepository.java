package me.christ9979.springbootjpa1.post;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByTitleStartsWith(String title);

    /**
     * 쿼리를 직접 작성하고 싶으면, @Query를 이용할수도 있다.
     */
    @Query("SELECT p FROM Post AS p WHERE p.title = ?1")
    /**
     * 다음과 같이 NamedParameter로 메소드의 인자값과 명시적으로 바인딩시킬 수 있다.
     * SqEL도 이용할 수 있는데, 하나의 예는 @Query에서 엔티티 이름을 #{entityName}으로
     * 표현할 수 있다.
     */
//    @Query("SELECT p FROM #{#entityName} AS p WHERE p.title = :title")
    /**
     * jpql로 만든 쿼리를 호출하기 위한 인터페이스 메서드
     */
    List<Post> findByTitle(@Param("title") String title);

    /**
     * @Query와 Sort 또는 Pageable도 같이 이용할수도 있다.
     */
    @Query("SELECT p FROM Post AS p WHERE p.title = ?1")
    List<Post> findByTitle(String title, Sort sort);
}
