package reddit.example.simpleforumgmail.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reddit.example.simpleforumgmail.repository.ConfirmationTokenRepository;

@Service
public class ConfirmationTokenServiceM {
    @Autowired
    ConfirmationTokenRepository confirmationTokenRepository;

    public void deleteToken(String confirmationToken) {
        confirmationTokenRepository.deleteById(confirmationToken);
    }
}
