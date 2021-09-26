package heating.model;

import heating.model.radiator.Radiator;

public interface RadiatorState extends HeatingModel {
    void turnUp(Radiator radiator);
    void turnDown(Radiator radiator);
    int getPower();
}
