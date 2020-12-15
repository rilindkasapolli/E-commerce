package reddit.example.simpleforumgmail.models;


import com.sun.istack.NotNull;
import com.sun.istack.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.*;


@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    @NotNull
    @Size(min=12, max=50)
    private String email;

    private String password;
    @Column(name = "about")
    private String about;
    @Lob
    @Nullable
    private byte[] profilepicture;


    private boolean enabled;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)

    private Set<Post> posts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)

    private Set<Likes> likes;

    @OneToMany(mappedBy = "user", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)

    private Set<CommentLikes> commentlikes;



    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)

    private Set<Comment> comments;

    public User() {
    }

    @Column(name = "role_id")
    private int role;

    public Set<CommentLikes> getCommentlikes() {
        return commentlikes;
    }

    public void setCommentlikes(Set<CommentLikes> commentlikes) {
        this.commentlikes = commentlikes;
    }

    public int getRole() {
        return role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public Set<Likes> getLikes() {
        return likes;
    }

    public void setLikes(Set<Likes> likes) {
        this.likes = likes;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }



    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }


    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public byte[] getProfilepicture() {
        return profilepicture;
    }

    public void setProfilepicture(byte[] profilepicture) {
        this.profilepicture = profilepicture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}



