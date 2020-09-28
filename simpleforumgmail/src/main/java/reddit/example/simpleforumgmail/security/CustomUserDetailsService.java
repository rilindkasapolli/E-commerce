package reddit.example.simpleforumgmail.security;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reddit.example.simpleforumgmail.models.Role;
import reddit.example.simpleforumgmail.models.User;
import reddit.example.simpleforumgmail.repository.UserRepository;
import reddit.example.simpleforumgmail.services.RoleService;
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
        List<Role> roles= new ArrayList<>();
        roles.add(roleServiceM.getRoleById(user.getRole()));
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(roles));
	/*	User user = userRepository.findUserByName(userName);
		return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(),
				getAuthorities(user)); */
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
       return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

  // private static Collection<? extends GrantedAuthority> getAuthorities(User user) {
    //	String[] userRoles = user.getRoles().stream().map((role) -> role.getName()).toArray(String[]::new);
//
    //	Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(roleServiceM.getRoleById(user.getRole()).getName() );
    //	return authorities;
   // }
}
