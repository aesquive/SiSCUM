package model.pojos;
// Generated 11/10/2012 12:23:06 PM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * EstatusConvenio generated by hbm2java
 */
public class EstatusConvenio  implements java.io.Serializable {


     private int idEstatusConvenio;
     private String desEstatusConvenio;
     private Set historiaConvenios = new HashSet(0);

    public EstatusConvenio() {
    }

	
    public EstatusConvenio(int idEstatusConvenio) {
        this.idEstatusConvenio = idEstatusConvenio;
    }
    public EstatusConvenio(int idEstatusConvenio, String desEstatusConvenio, Set historiaConvenios) {
       this.idEstatusConvenio = idEstatusConvenio;
       this.desEstatusConvenio = desEstatusConvenio;
       this.historiaConvenios = historiaConvenios;
    }
   
    public int getIdEstatusConvenio() {
        return this.idEstatusConvenio;
    }
    
    public void setIdEstatusConvenio(int idEstatusConvenio) {
        this.idEstatusConvenio = idEstatusConvenio;
    }
    public String getDesEstatusConvenio() {
        return this.desEstatusConvenio;
    }
    
    public void setDesEstatusConvenio(String desEstatusConvenio) {
        this.desEstatusConvenio = desEstatusConvenio;
    }
    public Set getHistoriaConvenios() {
        return this.historiaConvenios;
    }
    
    public void setHistoriaConvenios(Set historiaConvenios) {
        this.historiaConvenios = historiaConvenios;
    }




}


