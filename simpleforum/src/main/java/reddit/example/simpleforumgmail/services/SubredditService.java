package reddit.example.simpleforumgmail.services;

import reddit.example.simpleforumgmail.models.Subreddit;

public interface SubredditService {

    Iterable<Subreddit> listAllSubreddits();
    Subreddit getSubredditById(Integer id);
    Subreddit saveSubreddit(Subreddit subreddit);

}
