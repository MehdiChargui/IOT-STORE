package coreapi.commands;


import lombok.Getter;

public class UpdateProviderCommand extends BaseCommand<String>{

    @Getter private String designation;
    @Getter private String description;
    @Getter private String phoneNumber;
    @Getter private String mail;
    @Getter private String link;
    @Getter private String address;
    @Getter private String postalCode;
    @Getter private String city;
    @Getter private String country;
    @Getter private Boolean active;
    @Getter private Boolean deleted;

    public UpdateProviderCommand(String id, String designation, String description, String phoneNumber, String mail, String link, String address, String postalCode, String city, String country, Boolean active, Boolean deleted) {
        super(id);
        this.designation = designation;
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.mail = mail;
        this.link = link;
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
        this.active = active;
        this.deleted = deleted;
    }
}