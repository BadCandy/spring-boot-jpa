package me.christ9979.springbootjpa1.post;

import org.springframework.beans.factory.annotation.Value;

/**
 * Projection을 위한 인터페이스.
 * 클래스로도 구현이 가능하다.
 */
public interface CommentSummary {

    String getComment();

    int getUp();

    int getDown();

    /**
     * Default 메서드를 이용해서 Closed Projection을 구현한다.
     * 다음과 같은 경우는 필요한 프로퍼티만 가져오므로 쿼리 최적화가 가능하다.
     */
    default String getVotes() {
        return getUp() + " " + getDown();
    }

    /**
     * @Value(SpEL)을 이용해서 Open Projection을 구현한다.
     * 다음과 같은 경우는 모든 프로퍼티를 가져오므로 쿼리 최적화를 할 수 없다.
     */
//    @Value("#{target.up + ' ' + target.down}")
//    String getVotes();
}

/**
 * Projection을 위한 클래스.
 *
 */
//public class CommentSummary {
//
//    private String comment;
//
//    private int up;
//
//    private int down;
//
//    public CommentSummary(String comment, int up, int down) {
//        this.comment = comment;
//        this.up = up;
//        this.down = down;
//    }
//
//    public String getComment() {
//        return comment;
//    }
//
//    public void setComment(String comment) {
//        this.comment = comment;
//    }
//
//    public int getUp() {
//        return up;
//    }
//
//    public void setUp(int up) {
//        this.up = up;
//    }
//
//    public int getDown() {
//        return down;
//    }
//
//    public void setDown(int down) {
//        this.down = down;
//    }
//
//    public String getVotes() {
//        return this.up + " " + this.down;
//    }
//}
