package heating.model.radiator;

import heating.model.RadiatorState;

import java.beans.PropertyChangeListener;

public class Power1State implements RadiatorState {

    public final int POWER=1;

    @Override
    public void turnUp(Radiator radiator) {
        radiator.setPowerState(new Power2State());
    }

    @Override
    public void turnDown(Radiator radiator) {
        radiator.setPowerState(new OffState());
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
