package reddit.example.simpleforumgmail.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import reddit.example.simpleforumgmail.models.User;
import reddit.example.simpleforumgmail.repository.UserRepository;

import java.util.Map;

@Service
public class CustomOidcUserService extends OidcUserService {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser oidcUser = super.loadUser(userRequest);
        Map attributes = oidcUser.getAttributes();
        GoogleOAuth2UserInfo userInfo = new GoogleOAuth2UserInfo();
        userInfo.setEmail((String) attributes.get("email"));
        userInfo.setId((String) attributes.get("sub"));
        userInfo.setPassword("$2y$12$4Y7/wX8lv8VjhreuM0V/uu2MPSpkikyCqWqo5fNHyWSOuqBx8Jg62");
        userInfo.setEnabled(true);
        userInfo.setName((String) attributes.get("name"));
        updateUser(userInfo);

        return oidcUser;
    }


    private void updateUser(GoogleOAuth2UserInfo userInfo) {
        User user = userRepository.findByEmail(userInfo.getEmail());
        if (user == null) {
            user = new User();
        }
        user.setEmail(userInfo.getEmail());

        user.setPassword(userInfo.getPassword());
        user.setName(userInfo.getName());
        user.setEnabled(userInfo.isEnabled());
        user.setRole(1);

        userRepository.save(user);

    }
}