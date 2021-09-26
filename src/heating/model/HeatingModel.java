package heating.model;

import java.beans.PropertyChangeListener;

public interface HeatingModel {
    void addPropertyChangeListener(String eventName,PropertyChangeListener listener);
}
