package coreapi.events;

public class DeletedSettingEvent extends BaseEvent<String> {

    public DeletedSettingEvent(String id) {
        super(id);
    }
}
