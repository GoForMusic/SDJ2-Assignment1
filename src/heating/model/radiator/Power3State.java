package heating.model.radiator;

import heating.model.RadiatorState;

import java.beans.PropertyChangeListener;

public class Power3State implements RadiatorState {

    private final int POWER =3 ;
    private Thread temp=null;

    public Power3State(Radiator radiator)
    {
        temp = new Thread(()->{
            sleep(radiator);
    });
        temp.setDaemon(true);
        temp.start();
    }

    @Override
    public void turnUp(Radiator radiator) {

    }

    @Override
    public void turnDown(Radiator radiator) {
        temp.interrupt();
        radiator.setPowerState(new Power2State());
    }

    @Override
    public int getPower() {
        return POWER;
    }


    private void sleep(Radiator radiator)
    {
        try
        {
            Thread.sleep(10000);
            radiator.turnDown(radiator);   // The radiator is turned down after 10 seconds since the thread is called in the constructor.
        }
        catch (InterruptedException e)
        {
            System.out.println("Turn down has been pressed during the thread");
        }
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
