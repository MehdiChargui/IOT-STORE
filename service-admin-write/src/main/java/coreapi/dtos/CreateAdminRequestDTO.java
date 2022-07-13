package coreapi.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAdminRequestDTO {
    private String firstname;
    private String lastname;
    private String address;
    private String postalCode;
    private String function;
    private String phoneNumber;
    private String mail;
    private String login;
    private String password;
    private int role;

}

