package yanislav.com.autodata.events;

import java.util.List;

import yanislav.com.autodata.model.CarListInfoData;
import yanislav.com.autodata.model.ImageHolder;

/**
 * Created by yani on 12.3.2017 г..
 */

public class CarListLoadedEvent {
    private List<ImageHolder> imageHolderList;
    private List<CarListInfoData> carListInfoDataList;

    public CarListLoadedEvent(List<ImageHolder> imageHolderList, List<CarListInfoData> carListInfoDataList) {
        this.imageHolderList = imageHolderList;
        this.carListInfoDataList = carListInfoDataList;
    }

    public List<ImageHolder> getImageHolderList() {
        return imageHolderList;
    }

    public List<CarListInfoData> getCarListInfoDataList() {
        return carListInfoDataList;
    }
}
