package zb.Team.showticket.user.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import zb.Team.showticket.user.domain.entity.Users;

@Getter
@Setter
@AllArgsConstructor
/**
 * get Info 확인정보
 */
public class UserDto {

    private String email;
    private Long id;
    private String name;
    private Integer credit;
    private String type;

    public static UserDto from(Users users){
        return new UserDto(users.getEmail(),users.getId(), users.getName(), users.getCredit(), users.getType());
    }
}
