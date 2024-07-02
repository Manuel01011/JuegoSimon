package instrumentos.presentation.tipos;
import instrumentos.logic.Reder;
import instrumentos.logic.Service;

import javax.swing.*;
import java.beans.PropertyChangeListener;
public class Controller{
    //se accede a la data para el manejo de la misma con una relacion escalable
    public Controller() {
    }

    public void empezar(){
    Service.instance().getData().start();
    }

    public void crear(Reder a,game b) throws Exception {
        Service.instance().create(a,b);
    }
    public int ronda(){
        return Service.instance().getData().getRonda();
    }

    public int indexPatron(){
        return Service.instance().getData().indexPatron;
    }

    public int tamPatron(){
        return Service.instance().getData().getPatron().size();
    }

    public boolean perdio(){
       return Service.instance().getData().isGameOver();
    }

    public boolean patroon(){
        return Service.instance().getData().isCreatingPatter();
    }

    public void check(int a,game b){
        Service.instance().getData().revisar(a,b);
    }

    public int contador(){return Service.instance().getData().getContador();}

}
