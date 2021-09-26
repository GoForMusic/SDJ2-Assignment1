package heating.model.temperature;

import java.util.ArrayList;

public class TemperatureList {
    private ArrayList<Temperature> list;

    public TemperatureList(){
        list = new ArrayList<>();
    }

    public void addTemperature(Temperature temperature)
    {
        list.add(temperature);
    }

    public Temperature getTemperature(int index) {
        return list.get(index);
    }

    public Temperature getLastTemperature(String id) {
        if (list.size() < 1) {
            return null;
        }
        if (id == null) {
            return list.get(list.size() - 1);
        }
        for (int i = list.size() - 1; i >= 0; i--) {
            if (id.equals(list.get(i).getId())) {
                return list.get(i);
            }
        }
        return null;
    }

}
