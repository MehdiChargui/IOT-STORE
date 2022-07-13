package coreapi.events;

import lombok.Getter;

public class UpdatedAdminEvent extends BaseEvent<String> {
    @Getter private String firstname;
    @Getter private String lastname;
    @Getter private String address;
    @Getter private String postalCode;
    @Getter private String phoneNumber;
    @Getter private String function;
    @Getter private String mail;
    @Getter private String login;
    @Getter private String password;
    @Getter private int role;
    @Getter private Boolean deleted;

    public UpdatedAdminEvent(String id, String firstname, String lastname, String address, String postalCode, String phoneNumber, String function, String mail, String login, String password, int role, Boolean deleted) {
        super(id);
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.function = function;
        this.mail = mail;
        this.login = login;
        this.password = password;
        this.role = role;
        this.deleted = deleted;
    }
}

