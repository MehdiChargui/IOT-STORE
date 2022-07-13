package coreapi.commands;

import coreapi.models.Address;
import lombok.Getter;

import java.util.Date;
import java.util.List;

public class CreateCustomerSignUpCommand extends BaseCommand<String>{
    @Getter private String firstname;
    @Getter private String lastname;
    @Getter private String phoneNumber;
    @Getter private List<Address> addresses;
    @Getter private String function;
    @Getter private String mail;
    @Getter private String login;
    @Getter private String password;

    @Getter private Date registrationDate;
    @Getter private Boolean mailVerified;
    @Getter private String accountType;
    @Getter private Boolean active;
    @Getter private Boolean deleted;

    public CreateCustomerSignUpCommand(String id, String firstname, String lastname, String phoneNumber, List<Address> addresses, String function, String mail, String login, String password,Date registrationDate,Boolean mailVerified,String accountType, Boolean active, Boolean deleted) {
        super(id);
        this.firstname = firstname;
        this.lastname = lastname;
        this.phoneNumber = phoneNumber;
        this.addresses = addresses;
        this.function = function;
        this.mail = mail;
        this.login = login;
        this.password = password;
        this.registrationDate=registrationDate;
        this.mailVerified=mailVerified;
        this.accountType=accountType;
        this.active = active;
        this.deleted = deleted;
    }
}