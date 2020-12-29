package cl.ripley.login.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RecoverPasswordRequest {
    private String username;

}