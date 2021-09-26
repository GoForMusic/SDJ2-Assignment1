package heating.model.radiator;


import heating.model.RadiatorState;

import java.beans.PropertyChangeListener;

public class OffState implements RadiatorState {
    private final int POWER = 0;

    @Override
    public void turnUp(Radiator radiator) {
        radiator.setPowerState(new Power1State());
    }

    @Override
    public void turnDown(Radiator radiator) {

    }

    @Override
    public int getPower() {
        return POWER;
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }

    @Override
    public void addPropertyChangeListener(String eventName, PropertyChangeListener listener) {

    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {

    }

    @Override
    public void removePropertyChangeListener(String eventName, PropertyChangeListener listener) {

    }
}
