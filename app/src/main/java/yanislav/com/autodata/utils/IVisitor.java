package yanislav.com.autodata.utils;

import yanislav.com.autodata.model.Brand;
import yanislav.com.autodata.model.CarListInfoData;
import yanislav.com.autodata.model.DetailsInfoData;
import yanislav.com.autodata.model.ImageHolder;
import yanislav.com.autodata.model.ImagesInfoData;
import yanislav.com.autodata.model.Model;
import yanislav.com.autodata.model.Submodel;

/**
 * Created by yanislav on 4/26/17.
 */

public interface IVisitor<T>
{
    T visit(Brand brand);

    T visit(CarListInfoData carListInfoData);

    T visit(DetailsInfoData detailsInfoData);

    T visit(ImageHolder imageHolder);

    T visit(ImagesInfoData imagesInfoData);

    T visit(Model model);

    T visit(Submodel submodel);
}
