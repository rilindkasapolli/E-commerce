package reddit.example.simpleforumgmail.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reddit.example.simpleforumgmail.models.Review;
import reddit.example.simpleforumgmail.models.User;
import reddit.example.simpleforumgmail.repository.ReviewRepository;

@Service
public class ReviewServiceM implements ReviewService {
    @Autowired
    public ReviewRepository reviewRepository;

    @Override
    public Iterable<Review> listAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public Review getReviewById(Integer id) {
        return reviewRepository.findById(id).orElse(null);
    }

    @Override
    public Review saveReview(Review Review) {
        return reviewRepository.save(Review);
    }
}
