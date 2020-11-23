package reddit.example.simpleforumgmail.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reddit.example.simpleforumgmail.models.Role;
import reddit.example.simpleforumgmail.models.User;
import reddit.example.simpleforumgmail.repository.UserRepository;
import reddit.example.simpleforumgmail.services.RoleServiceM;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private RoleServiceM roleServiceM;
    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(userName);
        List<Role> roles = new ArrayList<>();
        roles.add(roleServiceM.getRoleById(user.getRole()));

        if (user.getEmail() == null || user.getPassword() == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        } else if (!user.isEnabled()) {
            throw new UsernameNotFoundException("Please verify your account!");
        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.isEnabled(), true, true, true, mapRolesToAuthorities(roles));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

}
