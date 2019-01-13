package me.christ9979.springbootjpa1.post;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    /**
     * @EntityGraph는 @NamedEntityGraph에 정의되어 있는 엔티티 그룹을 사용한다.
     * 그래프 타입(fetch 타입)을 설정 가능하다.
     * type = EntityGraph.EntityGraphType.FETCH : 명시된 애트리뷰트는 EAGER, 나머지는 LAZY 패치 (디폴트)
     * type = EntityGraph.EntityGraphType.LOAD : 명시된 애트리뷰트는 EAGER, 나머지는 설정된 패치 전략 따름
     */
    @EntityGraph(value = "Comment.post", type = EntityGraph.EntityGraphType.LOAD)
    /**
     * 아니면 다음과 같이 @NamedEntityGraph를 사용하지 않고 attributePaths를 줘서
     * 사용할 수도 있다.
     */
//    @EntityGraph(attributePaths = "post")
    Optional<Comment> getById(Long id);
}
