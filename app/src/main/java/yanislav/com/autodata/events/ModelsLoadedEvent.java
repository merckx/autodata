package yanislav.com.autodata.events;

import java.util.List;

import yanislav.com.autodata.model.Model;

/**
 * Created by yani on 21.2.2017 г..
 */

public class ModelsLoadedEvent {
    private List<Model> models;

    public ModelsLoadedEvent(List<Model> models) {
        this.models = models;
    }

    public List<Model> getModels() {
        return models;
    }
}
