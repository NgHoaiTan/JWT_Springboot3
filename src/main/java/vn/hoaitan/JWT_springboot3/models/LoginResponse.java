package vn.hoaitan.JWT_springboot3.models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class LoginResponse {
    private String token;
    private long expiresIn;


}
