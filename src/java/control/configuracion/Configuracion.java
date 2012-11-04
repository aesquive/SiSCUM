package control.configuracion;

import control.bean.Bean;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.naming.ldap.HasControls;
import util.xml.XMLReader;

/**
 *  Bean que se encarga de leer el archivo cfg/configuracion.xml para 
 *  ver datos de configuracion del sistema
 * @author alberto
 */
public class Configuracion{

    private final static String rutaArchivo="/var/www/CobranzaUrbi/cfg/configuracion.xml";
    private static Map<String,String> mapeo=new HashMap<String, String>();
    

    private static void leerArchivoConfiguracion() {
        XMLReader reader=new XMLReader(rutaArchivo);
        mapeo=reader.getMap();
    }
    
    public static String getValue(boolean leerNuevamente , String llave){
        if(leerNuevamente || mapeo.isEmpty()){
            leerArchivoConfiguracion();
        }
        return mapeo.get(llave);
    }
}
