package reddit.example.simpleforumgmail.repository;

import org.springframework.data.repository.CrudRepository;

import reddit.example.simpleforumgmail.models.Subreddit;

public interface SubredditRepository extends CrudRepository<Subreddit, Integer> {
    Subreddit findSubredditByName(String name);

}