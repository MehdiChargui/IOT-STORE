package coreapi.events;

public class DeletedCategoryEvent extends BaseEvent<String> {

    public DeletedCategoryEvent(String id) {
        super(id);
    }
}
