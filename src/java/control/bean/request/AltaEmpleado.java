/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control.bean.request;

import control.bean.Bean;
import control.bean.session.SessionVarsBean;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import model.dao.Dao;
import model.pojos.Empleado;
import model.pojos.EstatusEmpleado;
import model.pojos.JerarquiaEmpleado;
import model.pojos.Rol;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author alberto
 */
public class AltaEmpleado extends Bean{

    private Empleado empleado;
    private String message;
    private  SessionVarsBean sessionVarsBean;
    
    /**
     * Creates a new instance of AltaUsuarios
     */
    public AltaEmpleado() {
        empleado=new Empleado();
        sessionVarsBean= Bean.getSessionBean("sessionVarsBean")==null ? new SessionVarsBean() :
                (SessionVarsBean) Bean.getSessionBean("sessionVarsBean");
        
    }

    /**
     * metodo que crea la lista de roles de acuerdo al usuario logeado
     */
    private Rol getRol() {
        Empleado log = sessionVarsBean.getEmpleado();
        Criterion crit=Restrictions.eq("idRol", log.getRol().getIdRol()-1);
        List<Criterion> crits=new ArrayList<Criterion>();
        crits.add(crit);
        return (Rol) sessionVarsBean.getDao().executeSelect(Rol.class, crits).get(0);
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
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Metodo para guardar un empleado
     * @return altaEmpleado= Error al guardar al empleado , menuPrincipal = todo correcto
     */
    public String submitGuardarEmpleado(){
        boolean camposCorrectos=validarCampos();
        if(!camposCorrectos){
            return "altaEmpleado";
        }
        Dao dao = sessionVarsBean.getDao();
        empleado.setRol(getRol());
        empleado.setEstatusEmpleado((EstatusEmpleado)dao.executeSelect(EstatusEmpleado.class, null).get(0));
        JerarquiaEmpleado jerarquiaEmpleado=new JerarquiaEmpleado(empleado, sessionVarsBean.getEmpleado());
        Transaction transaction = dao.beginTransaction();
        dao.save(empleado);
        dao.save(jerarquiaEmpleado);
        dao.endTransaction(transaction, true);
        dao.refreshObject(sessionVarsBean.getEmpleado());
        return "menuPrincipal";
    }

    private boolean validarCampos() {
        if(empleado.getApellidoMaterno().equals("")|| empleado.getApellidoPaterno().equals("")
                || empleado.getPrimerNombre().equals("") ||empleado.getUsuario().equals("")|| empleado.getPassword().equals("")){
            return false;
        }
        return true;
    }
    
}
