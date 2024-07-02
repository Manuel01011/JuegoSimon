package instrumentos.logic;
import instrumentos.data.Data;
import instrumentos.data.XmlPersister;
import instrumentos.presentation.tipos.game;

import java.awt.*;
public class Service {
    // se crea una insatancia estatica para poder acceder a la informacion de la data desde el controller
    private static Service theInstance;

    public static Service instance(){
        if (theInstance == null) theInstance = new Service();
        return theInstance;
    }
    //atributo donde accedemos a la info de los datos
    private Data data;
    private Service(){
        try {
            data = XmlPersister.instance().load();
        }
        catch (Exception e)
        {
            data = new Data();
        }
    }

    public void create(Reder a, game b) throws Exception{
      data.iniciarPatron(a,b);
    }

    public Data getData() {
        return data;
    }

 }
