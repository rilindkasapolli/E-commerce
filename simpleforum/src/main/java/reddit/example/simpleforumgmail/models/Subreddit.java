package reddit.example.simpleforumgmail.models;


import javax.persistence.*;
import java.util.Set;

@Entity(name = "subreddit")
public class Subreddit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idsubreddit;
    private String name;

    public Subreddit() {

    }

    @OneToMany(mappedBy = "subreddit", cascade = CascadeType.ALL, fetch = FetchType.EAGER)

    private Set<Post> posts;

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public int getIdsubreddit() {
        return idsubreddit;
    }

    public void setIdsubreddit(int idsubreddit) {
        this.idsubreddit = idsubreddit;
    }

    public String getName() {
        return name;
    }

    public void setName(String subredditname) {
        this.name = subredditname;
    }
}
