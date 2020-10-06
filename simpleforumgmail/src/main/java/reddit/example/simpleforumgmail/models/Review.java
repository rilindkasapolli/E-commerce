package reddit.example.simpleforumgmail.models;


import com.sun.istack.Nullable;

import javax.persistence.*;

@Entity(name = "review")
public class Review {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private float rating;
    @Column(name = "user_id")
    private int userreview;
    @Column(name = "post_id")
    private int postreview;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getUser() {
        return userreview;
    }

    public void setUser(int user) {
        this.userreview = user;
    }

    public int getPost() {
        return postreview;
    }

    public void setPost(int post) {
        this.postreview = post;
    }
}
