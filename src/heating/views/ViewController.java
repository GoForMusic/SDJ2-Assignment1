package heating.views;

import heating.core.ViewHandler;
import heating.core.ViewModelFactory;

public interface ViewController {
    public void init(ViewHandler vh, ViewModelFactory vfm);
}
