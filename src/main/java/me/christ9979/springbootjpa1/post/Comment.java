package me.christ9979.springbootjpa1.post;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * @NamedEntityGraph는 @Entity에서 재사용할 여러 엔티티 그룹을
 * 정의할 때 사용한다.
 * 여기서는 Repository 인터페이스에서 @EntityGraph에서 사용하여 fetch 타입을
 * 지정해줄 것이다.
 */
@NamedEntityGraph(name = "Comment.post",
                attributeNodes = @NamedAttributeNode("post"))
/**
 * Auditing 기능중 @CreatedDate, @LastModifiedDate 기능을 활성화하기 위해
 * @EntityListeners(AuditingEntityListener.class) 설정으로 Auditing 리스너를
 * 등록한다.
 *
 * @CreatedBy, @LastModifiedBy를 사용하려면 @EnableJpaAuditing(auditorAwareRef = "Bean name")으로
 * 유저를 명시하는데 사용하는 빈을 등록해주어 한다.
 */
@EntityListeners(AuditingEntityListener.class)
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

    /**
     * Auditing 기능을 위한 프로퍼티들.
     * @CreatedDate, @CreatedBy, @LastModifiedDate, @LastModifiedBy을
     * 설정한다.
     *
     * @~By에는 @ManyToOne이라는 것을 붙여 항상 매핑시켜줘야 동작한다.
     *
     */
    @CreatedDate
    private Date created;

    @CreatedBy
    @ManyToOne
    private Account createdBy;

    @LastModifiedDate
    private Date updated;

    @LastModifiedBy
    @ManyToOne
    private Account updatedBy;

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

    /**
     * @PrePersist는 콜백으로써 Entity가 persistent되기 전에 호출이 된다.
     * 이 외에도 @PreRemove, @PostRemove, @PostPersist 등이 있다.
     */
    @PrePersist
    public void prePersist() {
        System.out.println("Pre Persist is called");
    }

}
