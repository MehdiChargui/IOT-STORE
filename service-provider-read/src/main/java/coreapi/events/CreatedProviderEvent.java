package coreapi.events;


import lombok.Getter;

import java.util.Date;

public class CreatedProviderEvent extends BaseEvent<String> {

    @Getter
    private String designation;
    @Getter
    private String description;
    @Getter
    private String phoneNumber;
    @Getter
    private String mail;
    @Getter
    private String link;
    @Getter
    private String address;
    @Getter
    private String postalCode;
    @Getter
    private String city;
    @Getter
    private String country;
    @Getter
    private Date creationDate;
    @Getter
    private Boolean active;
    @Getter
    private Boolean deleted;

    public CreatedProviderEvent(String id, String designation, String description, String phoneNumber, String mail, String link, String address, String postalCode, String city, String country, Date creationDate, Boolean active, Boolean deleted) {
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
        this.creationDate = creationDate;
        this.active = active;
        this.deleted = deleted;
    }
}