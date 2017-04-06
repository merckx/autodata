package yanislav.com.autodata.events;

import java.util.List;

import yanislav.com.autodata.model.Submodel;

/**
 * Created by yani on 25.2.2017 г..
 */

public class SubModelsLoadedEvent {
    private List<Submodel> submodelList;

    public SubModelsLoadedEvent(List<Submodel> submodelList) {
        this.submodelList = submodelList;
    }

    public List<Submodel> getSubmodelList() {
        return submodelList;
    }
}
