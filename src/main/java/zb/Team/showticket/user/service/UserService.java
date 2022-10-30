package zb.Team.showticket.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zb.Team.showticket.user.domain.UserRepository;
import zb.Team.showticket.user.domain.Users;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 로그인 가능 여부 확인
    public Optional<Users> findValidUser(String email, String password){
        return userRepository.findByEmail(email).stream()
                .filter(users -> users.getPassword().equals(password) && users.isVerify())
                .findFirst();
    }

    // token을 통한 id,email 확인
    public Optional<Users> findByIdAndEmail(Long id, String email){
        return userRepository.findById(id)
                .stream().filter(users -> users.getEmail().equals(email))
                .findFirst();
    }
}
