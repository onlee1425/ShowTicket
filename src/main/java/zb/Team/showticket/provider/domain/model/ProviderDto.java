package zb.Team.showticket.provider.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import zb.Team.showticket.provider.domain.entity.Provider;

@Getter
@Setter
@AllArgsConstructor

/**
 * provider get Info 확인정보
 */
public class ProviderDto {

    private String email;
    private String name;
    private String phone;

    public static ProviderDto from(Provider provider){
        return new ProviderDto(provider.getEmail(), provider.getName(), provider.getPhone());
    }
}
