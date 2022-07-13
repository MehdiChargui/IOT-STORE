package coreapi.events;

public class DeletedOrderEvent extends BaseEvent<String> {

    public DeletedOrderEvent(String id) {
        super(id);
    }
}
