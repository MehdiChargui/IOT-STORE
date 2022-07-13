package coreapi.events;

public class DeletedReviewEvent extends BaseEvent<String> {

    public DeletedReviewEvent(String id) {
        super(id);
    }
}
