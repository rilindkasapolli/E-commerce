package reddit.example.simpleforumgmail.models;

import javax.persistence.*;
import java.util.Objects;

@Entity(name="likes")
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "post_id")
    private int post;

    @Column(name = "user_id")
    private int user;





    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPostLike() {
        return post;
    }

    public void setPostLike(int postLike) {
        this.post = postLike;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }
}
