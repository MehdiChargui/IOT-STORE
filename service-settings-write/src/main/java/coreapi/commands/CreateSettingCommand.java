package coreapi.commands;

import coreapi.models.Links;
import coreapi.models.MailList;
import lombok.Getter;

import java.util.List;

public class CreateSettingCommand extends BaseCommand<String>{
   @Getter private String description;
   @Getter private List<MailList> mailList;
   @Getter private List<Links> links;

    public CreateSettingCommand(String id, String description, List<MailList> mailLists, List<Links> links) {
        super(id);
        this.description = description;
        this.mailList = mailLists;
        this.links = links;
    }
}

