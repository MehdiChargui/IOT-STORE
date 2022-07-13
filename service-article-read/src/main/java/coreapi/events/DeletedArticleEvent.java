package coreapi.events;

public class DeletedArticleEvent extends BaseEvent<String> {

    public DeletedArticleEvent(String id) {
        super(id);
    }
}
