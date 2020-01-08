/**
 * 
 */
package com.shopping.my.service.common;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shopping.my.entity.UserEntity;
import com.shopping.my.model.common.UserContext;
import com.shopping.my.repo.UserRepo;

/**
 * @author Syed Irfan
 *
 */
@Service
public class CustomUserDetailService implements UserDetailsService {	
	
	@Autowired
    UserRepo userRepository;
	
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userNameOrEmail) throws UsernameNotFoundException {
		 UserEntity user = userRepository.findByUsernameOrEmail(userNameOrEmail, userNameOrEmail)
	                .orElseThrow(() -> 
	                        new UsernameNotFoundException("User not found with username or email : " + userNameOrEmail)
	        );
		 return UserContext.create(user);
	}
	
	// This method is used by JWTAuthenticationFilter
    @Transactional
    public UserDetails loadUserById(Long id) {
        UserEntity user = userRepository.findById(id).orElseThrow(
            () -> new UsernameNotFoundException("User not found with id : " + id)
        );

        return UserContext.create(user);
    }

}
