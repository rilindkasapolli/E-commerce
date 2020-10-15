package reddit.example.simpleforumgmail.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reddit.example.simpleforumgmail.models.Subreddit;
import reddit.example.simpleforumgmail.repository.SubredditRepository;

@Service
public class SubredditServiceM implements SubredditService {

    private SubredditRepository subredditRepository;

    @Autowired
    private void setSubredditRepository(SubredditRepository subredditRepository) {
        this.subredditRepository = subredditRepository;
    }

    @Override
    public Iterable<Subreddit> listAllSubreddits() {
        return subredditRepository.findAll();
    }

    @Override
    public Subreddit getSubredditById(Integer id) {
        return subredditRepository.findById(id).orElse(null);
    }

    @Override
    public Subreddit saveSubreddit(Subreddit subreddit) {
        return subredditRepository.save(subreddit);
    }

    public void deleteSubreddit(Integer id) {
        subredditRepository.deleteById(id);
    }
}
