package me.christ9979.springbootjpa1.post;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long>, JpaSpecificationExecutor<Comment> {

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

    /**
     * Projection이란 entity의 일부 데이터만 가져오는 것이다.
     *
     * 아래의 쿼리 메소드는 CommentSummary에 따라 Open Project인지 Closed Projection인지 결정된다.
     * Closed Projection은 가져오려는 애트리뷰트를
     * 인터페이스 또는 클래스에 명시하여 쿼리 메소드의 반환값으로 사용한다.
     * 가져오려는 프로퍼티가 무엇인지 알기 때문에 호출 쿼리에서는
     * 관련있는 컬럼들만 사용한다.
     *
     * Open Projection은 가져오려는 애트리뷰트를
     * 명시하지 못하므로 모든 프로퍼티를 가져온다.
     *
     * Class<T> type으로 반환 타입을 지정해주는 이유는, 인터페이스의 쿼리메소드는
     * 이름이 같으면 컴파일 에러가 나는데, 이를 방지하기 위함이다. 또한 비슷한 용도의
     * 메서드를 반복 작성하는 작업을 없애준다.
     */
    <T> List<T> findByPost_Id(Long id, Class<T> type);


}
