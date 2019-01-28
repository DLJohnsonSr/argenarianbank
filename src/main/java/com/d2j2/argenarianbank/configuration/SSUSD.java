package com.d2j2.argenarianbank.configuration;

import com.d2j2.argenarianbank.models.AppRole;
import com.d2j2.argenarianbank.models.AppUser;
import com.d2j2.argenarianbank.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Transactional
@Service
public class SSUSD implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    public SSUSD(UserRepository userRepo){
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        try {
            AppUser thisAppUser = userRepo.findByUsername(username);
            if (thisAppUser == null){
                return null;
            }
            return new org.springframework.security.core.userdetails.User(
                    thisAppUser.getUsername(),
                    thisAppUser.getPassword(),
                    getAuthorites(thisAppUser));
        }catch (Exception e){
            throw new UsernameNotFoundException("AppUser Not Found");
        }
    }

    private Set<GrantedAuthority>getAuthorites(AppUser thisAppUser){
        Set<GrantedAuthority>authorities = new HashSet<GrantedAuthority>();
        for(AppRole eachAppRole : thisAppUser.getRoles()){
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(eachAppRole.getRoleName());
            authorities.add(grantedAuthority);
        }
        return authorities;
    }
}
