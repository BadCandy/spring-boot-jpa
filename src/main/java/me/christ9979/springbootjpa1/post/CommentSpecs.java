package me.christ9979.springbootjpa1.post;

import org.springframework.data.jpa.domain.Specification;

/**
 * Specifications를 이용한 쿼리 메소드를 호출하기 위해 static 메소드를 정의한다.
 * 즉, 이는 우리가 재사용할 스펙들을 미리 정의해 놓는 것이다.
 */
public class CommentSpecs {


    public static Specification<Comment> isBest() {
        return (Specification<Comment>)
                (root, criteriaQuery, criteriaBuilder) ->
                        criteriaBuilder.isTrue(root.get(Comment_.best));
    }

    public static Specification<Comment> isGood() {
        return (Specification<Comment>)
                (root, criteriaQuery, criteriaBuilder) ->
                        criteriaBuilder.greaterThan(root.get(Comment_.up), 10);
    }
}
