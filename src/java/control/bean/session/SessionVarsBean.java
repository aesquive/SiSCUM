package control.bean.session;


import control.configuracion.Configuracion;
import control.bean.Bean;
import model.dao.Dao;
import model.pojos.Empleado;

/**
 * Bean que mantiene todas las variables de sesion del Sistema 
 * @author alberto
 */
public class SessionVarsBean extends Bean{

    
    /**
     * el empleado en sesion
     */
    private Empleado empleado;
    /**
     * conexion con la base de datos , solo una por sesion (de esta manera no abriremos muchas sesiones)
     */
    private Dao dao;
    /**
     * Constructor
     */
    public SessionVarsBean(){
        dao=new Dao();
    }

    /**
     * @return the empleado
     */
    public Empleado getEmpleado() {
        return empleado;
    }

    /**
     * @param empleado the empleado to set
     */
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
    
    


    
    /**
     * @return the dao
     */
    public Dao getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(Dao dao) {
        this.dao = dao;
    }


}
