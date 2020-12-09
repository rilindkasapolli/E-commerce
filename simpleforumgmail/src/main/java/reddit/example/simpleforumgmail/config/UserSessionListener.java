package reddit.example.simpleforumgmail.config;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class UserSessionListener implements HttpSessionListener {
    static final String ONLINE_USERS = "OnlineUsers";

    public UserSessionListener() {
    }

    public void sessionCreated(HttpSessionEvent se) {
        ServletContext context = se.getSession().getServletContext();
        Integer onlineUsersCount = 0;
        Object attributeValue = context.getAttribute("OnlineUsers");
        if (attributeValue != null) {
            onlineUsersCount = (Integer)attributeValue;
        }

        context.setAttribute("OnlineUsers", onlineUsersCount + 1);
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        ServletContext context = se.getSession().getServletContext();
        Integer onlineUsersCount = (Integer)context.getAttribute("OnlineUsers");
        context.setAttribute("OnlineUsers", onlineUsersCount - 1);
    }
}