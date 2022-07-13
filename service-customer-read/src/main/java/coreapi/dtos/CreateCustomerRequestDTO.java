package coreapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomerRequestDTO {
    private String firstname;
    private String lastname;
    private String mail;
    private String login;
    private String password;
    private String accountType;
}

