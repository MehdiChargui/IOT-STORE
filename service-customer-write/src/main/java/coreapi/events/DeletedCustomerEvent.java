package coreapi.events;

public class DeletedCustomerEvent extends BaseEvent<String> {

    public DeletedCustomerEvent(String id) {
        super(id);
    }
}
