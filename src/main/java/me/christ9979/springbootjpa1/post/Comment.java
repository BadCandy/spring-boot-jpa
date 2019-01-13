package me.christ9979.springbootjpa1.post;

import javax.persistence.*;

/**
 * @NamedEntityGraph는 @Entity에서 재사용할 여러 엔티티 그룹을
 * 정의할 때 사용한다.
 * 여기서는 Repository 인터페이스에서 @EntityGraph에서 사용하여 fetch 타입을
 * 지정해줄 것이다.
 */
@NamedEntityGraph(name = "Comment.post",
                attributeNodes = @NamedAttributeNode("post"))
@Entity
public class Comment {

    @Id @GeneratedValue
    private Long id;

    private String comment;

    /**
     * 기본적으로 @ManyToOne 같이 @~One이 붙은 프로퍼티들은 fetch = FetchType.EAGER가
     * default이다. 이 뜻은 Select 쿼리시 연관된 post도 같이 가져온다는 뜻이다.
     *
     * 반대로, @OneToMany 같이 @~Many가 붙은 프로퍼티들은 fetch = FetchType.LAZY가
     * default이다.
     */
//    @ManyToOne
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    private int up;

    private int down;

    private boolean best;

    public int getUp() {
        return up;
    }

    public void setUp(int up) {
        this.up = up;
    }

    public int getDown() {
        return down;
    }

    public void setDown(int down) {
        this.down = down;
    }

    public boolean isBest() {
        return best;
    }

    public void setBest(boolean best) {
        this.best = best;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
