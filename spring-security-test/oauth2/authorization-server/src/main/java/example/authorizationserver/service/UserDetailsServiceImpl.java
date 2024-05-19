package example.authorizationserver.service;

import example.authorizationserver.domain.SysUserEntity;
import example.authorizationserver.repository.JpaUserRepository;
import jakarta.annotation.Resource;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final JpaUserRepository userRepository;

    public UserDetailsServiceImpl(JpaUserRepository userRepository) {
        this.userRepository = userRepository;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        SysUserEntity sysUserEntity = userRepository.findSysUserEntityByUsername(username);
        List<SimpleGrantedAuthority> grantedAuthorityList = Arrays.asList("USER").stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());

        return new User(username,sysUserEntity.getPassword(),grantedAuthorityList);
    }
}
