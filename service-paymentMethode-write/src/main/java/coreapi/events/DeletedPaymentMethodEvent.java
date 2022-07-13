package coreapi.events;

public class DeletedPaymentMethodEvent extends BaseEvent<String> {

    public DeletedPaymentMethodEvent(String id) {
        super(id);
    }
}
