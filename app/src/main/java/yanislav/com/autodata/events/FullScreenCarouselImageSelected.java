package yanislav.com.autodata.events;

import yanislav.com.autodata.model.ImagesInfoData;

/**
 * Created by yani on 28.3.2017 г..
 */

public class FullScreenCarouselImageSelected {
    ImagesInfoData imagesInfoData;

    public FullScreenCarouselImageSelected(ImagesInfoData imagesInfoData) {
        this.imagesInfoData = imagesInfoData;
    }

    public ImagesInfoData getImagesInfoData() {
        return imagesInfoData;
    }
}
