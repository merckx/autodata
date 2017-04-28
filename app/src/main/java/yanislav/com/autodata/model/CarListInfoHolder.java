package yanislav.com.autodata.model;

import java.util.List;

/**
 * Created by yanislav on 4/17/17.
 */

public class CarListInfoHolder
{
    private List<CarListInfoData> carListInfoDataList;
    private List<ImageHolder> imageHolderList;

    public CarListInfoHolder(List<CarListInfoData> carListInfoDataList, List<ImageHolder> imageHolderList)
    {
        this.carListInfoDataList = carListInfoDataList;
        this.imageHolderList = imageHolderList;
    }

    public List<CarListInfoData> getCarListInfoDataList()
    {
        return carListInfoDataList;
    }

    public List<ImageHolder> getImageHolderList()
    {
        return imageHolderList;
    }
}
