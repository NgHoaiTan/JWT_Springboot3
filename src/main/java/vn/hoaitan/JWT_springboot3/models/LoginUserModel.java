package vn.hoaitan.JWT_springboot3.models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class LoginUserModel {
    private String email;
    private String password;
}
