package yanislav.com.autodata.events;

import yanislav.com.autodata.model.Model;

/**
 * Created by yani on 25.2.2017 г..
 */

public class ModelSelectedEvent {
    private Model model;

    public ModelSelectedEvent(Model model) {
        this.model = model;
    }

    public Model getModel() {
        return model;
    }
}
