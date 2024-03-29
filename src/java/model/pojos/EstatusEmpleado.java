package model.pojos;
// Generated 11/10/2012 12:23:06 PM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * EstatusEmpleado generated by hbm2java
 */
public class EstatusEmpleado  implements java.io.Serializable {


     private int idEstatusEmpleado;
     private String desEstatusEmpleado;
     private Set empleados = new HashSet(0);

    public EstatusEmpleado() {
    }

	
    public EstatusEmpleado(int idEstatusEmpleado) {
        this.idEstatusEmpleado = idEstatusEmpleado;
    }
    public EstatusEmpleado(int idEstatusEmpleado, String desEstatusEmpleado, Set empleados) {
       this.idEstatusEmpleado = idEstatusEmpleado;
       this.desEstatusEmpleado = desEstatusEmpleado;
       this.empleados = empleados;
    }
   
    public int getIdEstatusEmpleado() {
        return this.idEstatusEmpleado;
    }
    
    public void setIdEstatusEmpleado(int idEstatusEmpleado) {
        this.idEstatusEmpleado = idEstatusEmpleado;
    }
    public String getDesEstatusEmpleado() {
        return this.desEstatusEmpleado;
    }
    
    public void setDesEstatusEmpleado(String desEstatusEmpleado) {
        this.desEstatusEmpleado = desEstatusEmpleado;
    }
    public Set getEmpleados() {
        return this.empleados;
    }
    
    public void setEmpleados(Set empleados) {
        this.empleados = empleados;
    }




}


