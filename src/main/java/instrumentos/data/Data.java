package instrumentos.data;
import instrumentos.logic.Reder;
import instrumentos.presentation.tipos.game;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Data {
    //el proyecto se realizo con una arquitecura por capaz para poder realisar un programa escalable
    //en esta clase se encuentra el almacenamiento y manejo del arreglo del arreglo del patron
    @XmlElementWrapper(name = "Patrones")
    @XmlElement(name = "Patron")
    public ArrayList<Integer> patron;
    public boolean creatingPatter = true;
    public int contador = 0 , ticks, indexPatron,dark,ronda = 0;
    public Random random;
    public boolean gameOver;
    public int getContador() {
        return contador;
    }

    public boolean isGameOver() {
        return gameOver;
    }


    public boolean isCreatingPatter() {
        return creatingPatter;
    }

    public int getRonda() {
        return ronda;
    }

    //inicializo el patron
    public void start(){
        random = new Random();
        patron = new ArrayList<Integer>();
        indexPatron = 0;
        dark =2;
        contador=0;
        ticks=0;
        gameOver=false;
    }

    //se crea el patron con valores aleatorio del 1 al 4, asi aumentando cada vez que se replica de forma correcta
    public void iniciarPatron(Reder a, game b){
        ticks++;
        if(ticks % 20 == 0) {
            contador = 0;
            if (dark >= 0) {
                dark--;
            }
        }
        if(creatingPatter) {
            if (dark <= 0 && patron != null) {
                if (indexPatron >= patron.size()) {
                    contador = random.nextInt(40)%4 + 1;
                    patron.add(contador);
                    indexPatron = 0;
                    creatingPatter = false;
                } else {
                    contador = patron.get(indexPatron);
                    indexPatron++;
                }
                dark = 2;
                b.playSoundForColor(contador);
            }
        } else if (indexPatron  == patron.size()) {
            creatingPatter = true;
            indexPatron = 0;
            dark =2;
            ronda++;
        }
        if(a != null) {
            a.repaint();
        }
    }

    //se revisa la conconrdancia del patron con la secuancia realizada por el usurio
    public void revisar(int flashed,game b) {
        if (patron.get(indexPatron) == flashed) {//se revisa cada posicion del patron si councide avanza
            indexPatron++;
        } else {//si no se declara que se equivoco
            b.playBotonSound();
            gameOver = true;
        }
    }

    public List<Integer> getPatron() {
        return patron;
    }
 }
