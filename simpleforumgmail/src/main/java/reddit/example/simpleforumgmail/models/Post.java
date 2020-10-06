package reddit.example.simpleforumgmail.models;


import com.sun.istack.Nullable;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int postid;
    @Column(name = "name")
    private String name;
    private String description;
    private int karma;
    private String username;
    @OneToMany(mappedBy = "postreview", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Review> reviews = new HashSet<Review>(0);
    @Lob
    @Nullable
    private byte[] image;

    public Post() {

    }

    @Column(name = "subreddit_id")
    private int subreddit;


    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.EAGER)

    private Set<Comment> comments;

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getKarma() {
        return karma;
    }

    public void setKarma(int karma) {
        this.karma = karma;
    }

    public byte[] getImage() {
        return image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Arrays.equals(image, post.image);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(image);
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getSubreddit() {
        return subreddit;
    }

    public void setSubreddit(int subreddit) {
        this.subreddit = subreddit;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public int getPostid() {
        return postid;
    }

    public void setPostid(int postid) {
        this.postid = postid;
    }

    public String getName() {
        return name;
    }

    public void setName(String postname) {
        this.name = postname;
    }

}
