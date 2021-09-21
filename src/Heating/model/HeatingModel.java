package Heating.model;

import java.beans.PropertyChangeListener;

public interface HeatingModel {
    public void addListener(String name, PropertyChangeListener listener);
}
