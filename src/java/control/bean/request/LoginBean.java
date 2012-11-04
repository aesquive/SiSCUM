/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control.bean.request;

import control.bean.Bean;
import control.bean.session.SessionVarsBean;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import mensajes.Mensajes;
import model.pojos.Empleado;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author alberto
 */
public class LoginBean extends Bean{

    private SessionVarsBean sessionVarsBean;
    private Empleado empleado;
    private String message;
    
    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
        System.out.println("creando login bean");
//        Bean.setSessionBean("sessionVarsBean", new SessionVarsBean());
        sessionVarsBean= Bean.getSessionBean("sessionVarsBean")==null ? new SessionVarsBean() :
                (SessionVarsBean) Bean.getSessionBean("sessionVarsBean");
        message="";
        empleado=new Empleado();
    }
    
    
    /**
     * Verifica el login del usuario
     * @return menuPrincipal -> login satisfactorio , index-> otro caso 
     */
    public String submitVerificacionEmpleado(){
        System.out.println("entra al submit");
        String user=getEmpleado().getUsuario()==null ? "" : getEmpleado().getUsuario();
        String pass=getEmpleado().getPassword()==null ? "" :getEmpleado().getPassword();
        List<Criterion> criterion=new ArrayList<Criterion>();
        criterion.add(Restrictions.eq("usuario", user));
        criterion.add(Restrictions.eq("password", pass));
        System.out.println("usuario "+user);
        System.out.println("pas "+pass);
        List<Empleado> executeSelect = sessionVarsBean.getDao().executeSelect(Empleado.class,criterion);
        System.out.println("cuantos "+executeSelect.size());
        if(executeSelect.isEmpty()){
            empleado.setUsuario(null);
            empleado.setPassword(null);
            setMessage(Mensajes.ERROR_LOGIN);
            return "index";
        }
        sessionVarsBean.setEmpleado(executeSelect.get(0));
        System.out.println("rol "+sessionVarsBean.getEmpleado().getRol().getIdRol());
        Bean.setSessionBean("sessionVarsBean", sessionVarsBean);
        return "menuPrincipal";
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

}
