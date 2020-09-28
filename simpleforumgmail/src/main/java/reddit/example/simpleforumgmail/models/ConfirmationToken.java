package reddit.example.simpleforumgmail.models;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
public class ConfirmationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private long tokenid;


    private String confirmationtoken;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createddate;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    public ConfirmationToken(User user) {
        this.user = user;
        createddate = new Date();
        confirmationtoken = UUID.randomUUID().toString();
    }

    public ConfirmationToken() {

    }

    public long getTokenid() {
        return tokenid;
    }

    public void setTokenid(long tokenid) {
        this.tokenid = tokenid;
    }

    public String getConfirmationtoken() {
        return confirmationtoken;
    }

    public void setConfirmationtoken(String confirmationoken) {
        this.confirmationtoken = confirmationoken;
    }

    public Date getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Date createdDate) {
        this.createddate = createdDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // getters and setters
}