package coreapi.commands;

import lombok.Getter;

import java.util.Date;

public class CreateTokenCommand extends BaseCommand<String>{
    @Getter private String _userId;
    @Getter private String token;
    @Getter private Date createdAt;

    public CreateTokenCommand(String id,String _userId, String token, Date createdAt) {
        super(id);
        this._userId=_userId;
        this.token = token;
        this.createdAt = createdAt;
    }
}