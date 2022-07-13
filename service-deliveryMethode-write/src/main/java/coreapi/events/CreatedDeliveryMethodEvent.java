package coreapi.events;


import lombok.Getter;

public class CreatedDeliveryMethodEvent extends BaseEvent<String> {
    @Getter private String name;
    @Getter private Boolean active;
    @Getter private Boolean deleted;

    public CreatedDeliveryMethodEvent(String id, String name, Boolean active, Boolean deleted) {
        super(id);
        this.name = name;
        this.active = active;
        this.deleted = deleted;
    }
}
