package yanislav.com.autodata.events;

import java.util.List;

import yanislav.com.autodata.model.ImagesInfoData;

/**
 * Created by yani on 26.3.2017 г..
 */

public class ImagesInfoDataLoadedEvent {
    private List<ImagesInfoData> imagesInfoData;

    public ImagesInfoDataLoadedEvent(List<ImagesInfoData> imagesInfoData) {
        this.imagesInfoData = imagesInfoData;
    }

    public List<ImagesInfoData> getImagesInfoData() {
        return imagesInfoData;
    }
}
