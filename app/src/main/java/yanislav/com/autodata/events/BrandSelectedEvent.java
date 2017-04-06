package yanislav.com.autodata.events;

import yanislav.com.autodata.model.Brand;

/**
 * Created by yani on 20.2.2017 г..
 */

public class BrandSelectedEvent {
    private Brand brand;

    public BrandSelectedEvent(Brand brand) {
        this.brand = brand;
    }

    public Brand getBrand() {
        return brand;
    }
}
