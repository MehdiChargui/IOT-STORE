package coreapi.events;

public class DeletedDeliveryMethodEvent extends BaseEvent<String> {

    public DeletedDeliveryMethodEvent(String id) {
        super(id);
    }
}
