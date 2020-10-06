package reddit.example.simpleforumgmail.services;

import reddit.example.simpleforumgmail.models.Review;

public interface ReviewService {

    Iterable<Review> listAllReviews();

    Review getReviewById(Integer id);

    Review saveReview(Review Review);


}
