package heating.model.radiator;

import heating.model.HeatingModel;
import heating.model.RadiatorState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Radiator implements RadiatorState {
    private RadiatorState currentState;
    private PropertyChangeSupport support;

    public Radiator()
    {
        if(currentState==null){
            currentState=new OffState();
        }
        support = new PropertyChangeSupport(this);
    }


    @Override
    public void turnUp(Radiator radiator) {
        currentState.turnUp(this);
    }

    @Override
    public void turnDown(Radiator radiator) {
        currentState.turnDown(this);
    }

    public int getPower() {
        return currentState.getPower();
    }

    public void setPowerState(RadiatorState state){
        currentState = state;
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    @Override
    public void addPropertyChangeListener(String eventName, PropertyChangeListener listener) {
        support.addPropertyChangeListener(eventName,listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(String eventName, PropertyChangeListener listener) {
        support.removePropertyChangeListener(eventName,listener);
    }
}
