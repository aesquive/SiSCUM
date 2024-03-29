package model.pojos;
// Generated 11/10/2012 12:23:06 PM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * Cliente generated by hbm2java
 */
public class Cliente  implements java.io.Serializable {


     private Integer idCliente;
     private EstatusCliente estatusCliente;
     private String primerNombre;
     private String segundoNombre;
     private String apellidoPaterno;
     private String apellidoMaterno;
     private Set viviendas = new HashSet(0);
     private Set historiaCarteras = new HashSet(0);

    public Cliente() {
    }

    public Cliente(EstatusCliente estatusCliente, String primerNombre, String segundoNombre, String apellidoPaterno, String apellidoMaterno, Set viviendas, Set historiaCarteras) {
       this.estatusCliente = estatusCliente;
       this.primerNombre = primerNombre;
       this.segundoNombre = segundoNombre;
       this.apellidoPaterno = apellidoPaterno;
       this.apellidoMaterno = apellidoMaterno;
       this.viviendas = viviendas;
       this.historiaCarteras = historiaCarteras;
    }
   
    public Integer getIdCliente() {
        return this.idCliente;
    }
    
    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }
    public EstatusCliente getEstatusCliente() {
        return this.estatusCliente;
    }
    
    public void setEstatusCliente(EstatusCliente estatusCliente) {
        this.estatusCliente = estatusCliente;
    }
    public String getPrimerNombre() {
        return this.primerNombre;
    }
    
    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }
    public String getSegundoNombre() {
        return this.segundoNombre;
    }
    
    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }
    public String getApellidoPaterno() {
        return this.apellidoPaterno;
    }
    
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }
    public String getApellidoMaterno() {
        return this.apellidoMaterno;
    }
    
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }
    public Set getViviendas() {
        return this.viviendas;
    }
    
    public void setViviendas(Set viviendas) {
        this.viviendas = viviendas;
    }
    public Set getHistoriaCarteras() {
        return this.historiaCarteras;
    }
    
    public void setHistoriaCarteras(Set historiaCarteras) {
        this.historiaCarteras = historiaCarteras;
    }




}


