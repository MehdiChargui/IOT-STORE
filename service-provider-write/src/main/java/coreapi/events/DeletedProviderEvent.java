package coreapi.events;

public class DeletedProviderEvent extends BaseEvent<String> {

    public DeletedProviderEvent(String id) {
        super(id);
    }
}
