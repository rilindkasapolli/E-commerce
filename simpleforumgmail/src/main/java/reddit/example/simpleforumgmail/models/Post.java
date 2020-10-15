package reddit.example.simpleforumgmail.models;


import com.sun.istack.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
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
    @Column(name = "email")
    private String email;
    @Column(name="timecreated")
    private LocalDateTime timeCreated;

    @Lob
    @Nullable
    private byte[] image;

    public Post() {

    }



    @Column(name = "subreddit_id")
    private int subreddit;

    @Column(name = "user_id")
    private int user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.EAGER)

    private Set<Comment> comments;

    @OneToMany(mappedBy = "post", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<Likes> likes;


    public String getEmail() {
        return email;
    }

    public void setEmail(String username) {
        this.email = username;
    }



    public Set<Likes> getLikes() {
        return likes;
    }

    public void setLikes(Set<Likes> likes) {
        this.likes = likes;
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(LocalDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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



    public String givenTwoDateTimes(LocalDateTime l, LocalDateTime lNow) {


        long  y = ChronoUnit.YEARS.between(l , lNow);
        long m = ChronoUnit.MONTHS.between(l,lNow);
        long w = ChronoUnit.WEEKS.between(l,lNow);
        long d = ChronoUnit.DAYS.between(l,lNow);
        long h = ChronoUnit.HOURS.between(l,lNow);
        long min = ChronoUnit.MINUTES.between(l,lNow);
        long s = ChronoUnit.SECONDS.between(l,lNow);
        if(s<60){
            if(s==1){
                return s + " second";
            }
            else return s + " seconds";
        }else if (min<60){
            if(min==1){
                return min + " minute";
            }
            else return min + " minutes";
        }
        else if(h<24){
            if(h==1){
                return h + " hour";
            }
            else return h + " hours";
        }
        else if(d<7) {
            if(d==1){
                return d + " day";
            }
            else return d + " days";
        }
        else if(w<4){
            if(w==1){
                return w + " week";
            }
            else return w + " weeks";
        }
        else if(m<12){
            if(m==1){
                return m + " month";
            }
            else return m + " months";
        }
        else if (y<5000){
            if(y==1){
                return y + " year";
            }
            else return y + " years";
        }
        else return null;
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
