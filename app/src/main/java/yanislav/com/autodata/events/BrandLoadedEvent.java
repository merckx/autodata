package yanislav.com.autodata.events;

import java.util.List;

import yanislav.com.autodata.model.Brand;

/**
 * Created by yani on 20.2.2017 г..
 */

public class BrandLoadedEvent {
    private List<Brand> brands;

    public BrandLoadedEvent(List<Brand> brands) {
        this.brands = brands;
    }

    public List<Brand> getBrands() {
        return brands;
    }
}
