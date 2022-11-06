package zb.Team.showticket.provider.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zb.Team.showticket.provider.domain.Provider;
import zb.Team.showticket.provider.domain.ProviderRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProviderService {
    private final ProviderRepository providerRepository;

    // 로그인 가능 여부 확인
    public Optional<Provider> findValidProvider(String email, String password){
        return providerRepository.findByEmail(email).stream()
                .filter(provider -> provider.getPassword().equals(password) && provider.isVerify())
                .findFirst();
    }

    // token을 통한 id,email 확인
    public Optional<Provider> findByIdAndEmail(Long id, String email){
        return providerRepository.findById(id)
                .stream().filter(provider -> provider.getEmail().equals(email))
                .findFirst();
    }
}
