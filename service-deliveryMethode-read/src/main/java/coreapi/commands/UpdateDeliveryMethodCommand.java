package coreapi.commands;


import lombok.Getter;

public class UpdateDeliveryMethodCommand extends BaseCommand<String>{

    @Getter private String name;
    @Getter private Boolean active;
    @Getter private Boolean deleted;

    public UpdateDeliveryMethodCommand(String id, String name, Boolean active, Boolean deleted) {
        super(id);
        this.name = name;
        this.active = active;
        this.deleted = deleted;
    }

}
