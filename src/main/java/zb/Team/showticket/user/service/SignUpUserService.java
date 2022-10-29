package zb.Team.showticket.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zb.Team.showticket.user.domain.SignUpForm;
import zb.Team.showticket.user.domain.Users;
import zb.Team.showticket.user.domain.UserRepository;

@Service
@RequiredArgsConstructor
public class SignUpUserService {

    private final UserRepository userRepository;

    public Users signUp(SignUpForm form){
        return userRepository.save(Users.from(form));
    }
}
