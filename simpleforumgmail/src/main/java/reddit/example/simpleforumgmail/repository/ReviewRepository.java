package reddit.example.simpleforumgmail.repository;

import org.springframework.data.repository.CrudRepository;
import reddit.example.simpleforumgmail.models.Review;

public interface ReviewRepository extends CrudRepository<Review, Integer> {
}
