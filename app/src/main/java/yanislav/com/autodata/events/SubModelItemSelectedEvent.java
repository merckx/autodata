package yanislav.com.autodata.events;

import yanislav.com.autodata.model.Submodel;

/**
 * Created by yani on 12.3.2017 г..
 */

public class SubModelItemSelectedEvent {
    private Submodel submodel;

    public SubModelItemSelectedEvent(Submodel submodel) {
        this.submodel = submodel;
    }

    public Submodel getSubmodel() {
        return submodel;
    }
}
