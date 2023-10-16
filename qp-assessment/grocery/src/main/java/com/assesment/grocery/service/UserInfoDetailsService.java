package com.assesment.grocery.service;

import com.assesment.grocery.entity.User;
import com.assesment.grocery.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserInfoDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserInfoDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findById(Long.parseLong(username));
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("User not found");
        }
        String role = user.get().getRole();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));
        return new org.springframework.security.core.userdetails.User(user.get().getUserName(), new BCryptPasswordEncoder().encode(user.get().getPassword()), authorities );

    }
}
