package coreapi.events;


import lombok.Getter;

import java.util.Date;

public class CreatedCustomerEvent extends BaseEvent<String> {
    @Getter private String firstname;
    @Getter private String lastname;
    @Getter private String mail;
    @Getter private String login;
    @Getter private String password;
    @Getter private Date registrationDate;
    @Getter private Boolean mailVerified;
    @Getter private String accountType;
    @Getter private Boolean active;
    @Getter private Boolean deleted;

    public CreatedCustomerEvent(String id, String firstname, String lastname, String mail, String login, String password, Date registrationDate, Boolean mailVerified, String accountType, Boolean active, Boolean deleted) {
        super(id);
        this.firstname = firstname;
        this.lastname = lastname;
        this.mail = mail;
        this.login = login;
        this.password = password;
        this.registrationDate = registrationDate;
        this.mailVerified = mailVerified;
        this.accountType = accountType;
        this.active = active;
        this.deleted = deleted;
    }
}