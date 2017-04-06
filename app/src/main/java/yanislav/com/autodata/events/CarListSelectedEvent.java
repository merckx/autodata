package yanislav.com.autodata.events;

import yanislav.com.autodata.model.CarListInfoData;

/**
 * Created by yani on 23.3.2017 г..
 */

public class CarListSelectedEvent {
    private CarListInfoData carListInfoData;

    public CarListSelectedEvent(CarListInfoData carListInfoData) {
        this.carListInfoData = carListInfoData;
    }

    public CarListInfoData getCarListInfoData() {
        return carListInfoData;
    }
}
