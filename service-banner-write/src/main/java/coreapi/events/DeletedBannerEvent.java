package coreapi.events;

public class DeletedBannerEvent extends BaseEvent<String> {

    public DeletedBannerEvent(String id) {
        super(id);

    }

}
