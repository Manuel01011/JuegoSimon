package instrumentos.presentation.tipos;

import java.util.List;
import java.util.Observer;
public class Model extends java.util.Observable{
    @Override
    public void addObserver(Observer o) {
        super.addObserver(o);

    }
    public Model() {
    }

}
