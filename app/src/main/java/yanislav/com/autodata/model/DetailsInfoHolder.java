package yanislav.com.autodata.model;

import java.util.List;

/**
 * Created by yanislav on 4/17/17.
 */

public class DetailsInfoHolder
{
    private List<DetailsInfoData> detailsInfoDataList;
    private List<ImageHolder> imageHolderList;

    public DetailsInfoHolder(List<DetailsInfoData> detailsInfoDataList, List<ImageHolder> imageHolderList)
    {
        this.detailsInfoDataList = detailsInfoDataList;
        this.imageHolderList = imageHolderList;
    }

    public List<DetailsInfoData> getDetailsInfoDataList()
    {
        return detailsInfoDataList;
    }

    public List<ImageHolder> getImageHolderList()
    {
        return imageHolderList;
    }
}
