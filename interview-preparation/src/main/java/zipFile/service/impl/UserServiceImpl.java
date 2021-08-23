package zipFile.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import zipFile.config.security.UserPrincipal;
import zipFile.dto.User;
import zipFile.model.UserDetail;
import zipFile.repo.UserDetailRepository;
import zipFile.service.UserService;

@Service
@Transactional
@Qualifier("userDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService{
    @Autowired
    private UserDetailRepository userDetailRepository;

    @Override
    public UserDetails loadUserByUsername(String userKey) throws UsernameNotFoundException {
        Optional<UserDetail> userDetail = userDetailRepository.findUserByUserKey(userKey);
        if (userDetail.isPresent()) {
            User user = new User();
            user.setUserId(userDetail.get().getUserKey());
            user.setAuthorities(new String[]{"ADD","EDIT","DELETE"});//Some constant values
            user.setUserRoles(new String[]{"Admin"});//Some constant values

            UserPrincipal userPrincipal = new UserPrincipal(user);
            return userPrincipal;
        }else{
            throw new UsernameNotFoundException("user not found by userkey=" + userKey);
        }
    }
}