package yanislav.com.autodata.events;

import yanislav.com.autodata.model.ImageHolder;

/**
 * Created by yani on 26.3.2017 г..
 */

public class CarouselImageSelectedEvent {
    private ImageHolder imageHolder;

    public CarouselImageSelectedEvent(ImageHolder imageHolder) {
        this.imageHolder = imageHolder;
    }

    public ImageHolder getImageHolder() {
        return imageHolder;
    }
}
