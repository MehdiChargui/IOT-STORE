package coreapi.dtos;

import coreapi.models.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomerSignUpRequestDTO {
    private String firstname;
    private String lastname;
    private String phoneNumber;
    private List<Address> addresses;
    private String function;
    private String mail;
    private String login;
    private String password;
    private String accountType;
}

