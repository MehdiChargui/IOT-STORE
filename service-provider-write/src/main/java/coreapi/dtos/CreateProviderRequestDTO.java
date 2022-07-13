package coreapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProviderRequestDTO {
    private String designation;
    private String description;
    private String phoneNumber;
    private String mail;
    private String link;
    private String address;
    private String postalCode;
    private String city;
    private String country;
}

