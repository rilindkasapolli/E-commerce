package reddit.example.simpleforumgmail.models;

import javax.persistence.*;

@Entity(name="comment_likes")
public class CommentLikes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "comment_id")
    private int comment;

    @Column(name = "user_id")
    private int user;





    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

}
