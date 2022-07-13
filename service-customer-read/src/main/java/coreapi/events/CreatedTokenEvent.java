package coreapi.events;


import lombok.Getter;

import java.util.Date;

public class CreatedTokenEvent extends BaseEvent<String> {
    @Getter private String _userId;
    @Getter
    private String token;
    @Getter
    private Date createdAt;

    public CreatedTokenEvent(String id,String _userId, String token, Date createdAt) {
        super(id);
        this._userId=_userId;
        this.token = token;
        this.createdAt = createdAt;
    }
}