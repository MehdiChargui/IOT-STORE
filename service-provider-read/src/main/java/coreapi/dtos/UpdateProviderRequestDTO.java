package coreapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProviderRequestDTO {
    private String id;
    private String designation;
    private String description;
    private String phoneNumber;
    private String mail;
    private String link;
    private String address;
    private String postalCode;
    private String city;
    private String country;
    private Boolean active;
    private Boolean deleted;
}

