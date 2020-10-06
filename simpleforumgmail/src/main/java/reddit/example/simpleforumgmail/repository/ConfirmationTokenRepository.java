package reddit.example.simpleforumgmail.repository;

import org.springframework.data.repository.CrudRepository;
import reddit.example.simpleforumgmail.models.ConfirmationToken;

public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, String> {

    ConfirmationToken findByConfirmationtoken(String confirmationToken);
}