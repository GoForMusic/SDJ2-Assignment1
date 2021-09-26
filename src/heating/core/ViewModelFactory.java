package heating.core;

import heating.views.main.HeatingVM;

public class ViewModelFactory {
    private ModelFactory modelFactory;

    //add scenes
    private HeatingVM heatingVM;

    public ViewModelFactory(ModelFactory mf)
    {
        modelFactory = mf;
    }

    //return the specific VIEW MODEL
    public HeatingVM getHeatingVM() {
        if(heatingVM == null)
        {
            heatingVM = new HeatingVM(modelFactory);
        }
        return heatingVM;
    }
}
