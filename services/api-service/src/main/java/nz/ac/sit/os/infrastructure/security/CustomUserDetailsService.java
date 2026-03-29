package nz.ac.sit.os.infrastructure.security;

import nz.ac.sit.os.domain.auth.Role;
import nz.ac.sit.os.domain.auth.RoleRepository;
import nz.ac.sit.os.domain.auth.User;
import nz.ac.sit.os.domain.auth.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @program: os
 * @description: Implement how to get user details
 * @author: Lucas Wang
 * @email: lucas.wang.1024@gmail.com
 * @date: 20/03/2026 23:39
 **/
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found:" + username));

        List<Role> roles = roleRepository.findRolesByUserId(user.getId());
        user.setRoles(roles);

        SecurityUser sUser = new SecurityUser(user);
        return new org.springframework.security.core.userdetails.User(
                sUser.getUsername(),
                sUser.getPassword(),
                sUser.isEnabled(),
                true, true, true,
                sUser.getAuthorities()
        );
    }
}