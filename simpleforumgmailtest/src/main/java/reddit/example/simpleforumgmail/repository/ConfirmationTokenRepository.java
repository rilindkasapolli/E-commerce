package reddit.example.simpleforumgmail.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import reddit.example.simpleforumgmail.models.ConfirmationToken;
@Repository
public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, String> {

    ConfirmationToken findByConfirmationtoken(String confirmationToken);
}