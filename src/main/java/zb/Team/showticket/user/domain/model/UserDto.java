package zb.Team.showticket.user.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import zb.Team.showticket.user.domain.Users;

@Getter
@Setter
@AllArgsConstructor
public class UserDto {

    private String email;
    private Long id;

    public static UserDto from(Users users){
        return new UserDto(users.getEmail(),users.getId());
    }
}
