/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control.bean;

import javax.faces.context.FacesContext;

/**
 *
 * @author alberto
 */
public class Bean {
    
    public static Bean getSessionBean(String name){
        return (Bean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(name);
    }
    
    public static void setSessionBean(String name , Bean bean){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(name, bean);
    }

    public static void cleanBean(String manejadorAgenda) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(manejadorAgenda);
    }
}
