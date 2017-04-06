package yanislav.com.autodata.events;

import java.util.List;

import yanislav.com.autodata.model.DetailsInfoData;
import yanislav.com.autodata.model.ImageHolder;

/**
 * Created by yani on 15.3.2017 г..
 */

public class CarDetailsLoadedEvent {
    private List<ImageHolder> imageHolderList;
    private List<DetailsInfoData> detailsInfoData;

    public CarDetailsLoadedEvent(List<ImageHolder> imageHolderList, List<DetailsInfoData> detailsInfoData) {
        this.imageHolderList = imageHolderList;
        this.detailsInfoData = detailsInfoData;
    }

    public List<ImageHolder> getImageHolderList() {
        return imageHolderList;
    }

    public List<DetailsInfoData> getDetailsInfoData() {
        return detailsInfoData;
    }
}
