package cl.ripley.login.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RecoverPasswordResponse {
    private String token;
}
