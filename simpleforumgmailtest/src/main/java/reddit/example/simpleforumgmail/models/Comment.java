package reddit.example.simpleforumgmail.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Set;

@Entity(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int commentid;
    private String description;
    private String username;
    private String email;
    private String postname;
    @Column(name = "commentkarma")
    private int commentkarma;

    @Column(name="timecreated")
    private LocalDateTime timeCreated;


    @Column(name = "post_id")
    private int post;


    @Column(name = "user_id")
    private int user;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)

    private Set<CommentLikes> commentlikes;

    public Comment() {

    }

    public int getCommentkarma() {
        return commentkarma;
    }

    public void setCommentkarma(int commentkarma) {
        this.commentkarma = commentkarma;
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



    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(LocalDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }

    public Set<CommentLikes> getCommentlikes() {
        return commentlikes;
    }

    public void setCommentlikes(Set<CommentLikes> commentlikes) {
        this.commentlikes = commentlikes;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPostname() {
        return postname;
    }

    public void setPostname(String postname) {
        this.postname = postname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getPost() {
        return post;
    }

    public void setPost(int post) {
        this.post = post;
    }

    public int getCommentid() {
        return commentid;
    }

    public void setCommentid(int commentid) {
        this.commentid = commentid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
