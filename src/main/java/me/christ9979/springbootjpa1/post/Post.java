package me.christ9979.springbootjpa1.post;

import javax.persistence.*;
import java.util.Date;

@Entity
/**
 * jpql로 직접 쿼리를 작성하고 싶다면 @NamedQuery를 이용한다.
 * Repository 인터페이스에도 관련 메서드를 작성해야한다.
 * native sql로 작성하고 싶다면, @NamedNativeQuery를 이용한다.
 */
@NamedQuery(name = "Post.findByTitle", query = "SELECT p FROM Post AS p WHERE p.title = ?1")
public class Post {

    @Id @GeneratedValue
    private Long id;

    private String title;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
