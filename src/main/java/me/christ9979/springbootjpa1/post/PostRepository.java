package me.christ9979.springbootjpa1.post;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

    /**
     * 업데이트 쿼리를 작성한다.
     * 직접 update 쿼리나 delete 쿼리를 작성하는것은 추천하지 않는다.
     * 일일이 @Modifying의 clearAutomatically, flushAutomatically 값을 바꾸어 주어야 한다.
     * 그 외에 직접 쿼리를 작성하면 이벤트를 처리하는 콜백 기능도 사용할 수 없다.
     */
//    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Modifying
    @Query("UPDATE Post p SET p.title = ?1 WHERE p.id = ?2")
    int updateTitle(String hibernate, Long id);
}
