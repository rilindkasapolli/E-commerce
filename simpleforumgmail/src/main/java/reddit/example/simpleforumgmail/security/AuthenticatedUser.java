package reddit.example.simpleforumgmail.security;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import reddit.example.simpleforumgmail.models.Role;
import reddit.example.simpleforumgmail.models.User;
import reddit.example.simpleforumgmail.services.RoleServiceM;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;


public class AuthenticatedUser extends org.springframework.security.core.userdetails.User {
    @Autowired
   public static RoleServiceM roleServiceM;

    private static final long serialVersionUID = 1L;
    private User user;

    public AuthenticatedUser(User user) {
        super(user.getEmail(), user.getPassword(), getAuthorities(user));
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(User user) {
        final List<SimpleGrantedAuthority> authorities = new LinkedList<>();

        Role role = roleServiceM.getRoleById(user.getRole());

            if (role.getName() == "ROLE_ADMIN") {
                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            }
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));


        return authorities;

	/*	Set<String> roleAndPermissions = new HashSet<>();
		List<Role> roles = user.getRoles();
		
		for (Role role : roles)
		{
			roleAndPermissions.add(role.getName());
		}
		String[] roleNames = new String[roleAndPermissions.size()];
		Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(roleAndPermissions.toArray(roleNames));
		return authorities; */

    }
}