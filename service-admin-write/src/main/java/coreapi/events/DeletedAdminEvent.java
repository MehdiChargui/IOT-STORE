package coreapi.events;

public class DeletedAdminEvent extends BaseEvent<String> {

    public DeletedAdminEvent(String id) {
        super(id);
    }
}
