
package control.bean.view;

import control.bean.Bean;
import control.bean.session.SessionVarsBean;
import control.bean.session.SessionVarsBean;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import model.dao.Dao;
import model.pojos.Cliente;
import model.pojos.Empleado;
import model.pojos.EstatusCartera;
import model.pojos.EstatusCliente;
import model.pojos.HistoriaCartera;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 * Bean que nos sirve para hacer la asignacion de cartera pendiente a los cobradores
 * @author alberto
 */
public class AsignacionCartera {

    private List<Cliente> clientes;
    private List<Empleado> cobradores;
    private SessionVarsBean sessionVarsBean;
    private int idClienteSeleccionado;
    private int idEmpleadoSeleccionado;
    
    public AsignacionCartera() {
        System.out.println("creando bean asignacion");
        sessionVarsBean=(SessionVarsBean) Bean.getSessionBean("sessionVarsBean");
        generarClientes();
        generarCobradores();
    }

    /**
     * genera la lista de cobradores dependiendo del usuario que hizo login
     */
    private void generarCobradores() {
        cobradores=new LinkedList<Empleado>();
        Set<Empleado> empleados = sessionVarsBean.getEmpleado().getAllEmpleados();
        for(Empleado emp:empleados){
            if(emp.getEmpleados().isEmpty()){
                cobradores.add(emp);
            }
        }
    }

    /**
     * devuelve la lista de clientes (cartera) pendientes por asignar
     */
    private void generarClientes() {
        Dao dao = sessionVarsBean.getDao();
        clientes=new LinkedList<Cliente>();
        List<Cliente> clientesSelect = dao.executeSelect(Cliente.class, null);
        for(Cliente cl:clientesSelect){
            if(cl.getEstatusCliente().getIdEstatusCliente()==3){
                clientes.add(cl);
            }
        }
    }

    /**
     * @return the clientes
     */
    public List<Cliente> getClientes() {
        return clientes;
    }

    /**
     * @param clientes the clientes to set
     */
    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    /**
     * @return the cobradores
     */
    public List<Empleado> getCobradores() {
        return cobradores;
    }

    /**
     * @param cobradores the cobradores to set
     */
    public void setCobradores(List<Empleado> cobradores) {
        this.cobradores = cobradores;
    }


    
    public String asignarCartera(){
        System.out.println("el cliente seleccionado "+idClienteSeleccionado);
        if(idClienteSeleccionado==-1 || idEmpleadoSeleccionado==-1){
            return "asignacionCartera";
        }
        Cliente clienteSeleccionado=buscarCliente(idClienteSeleccionado);
        Empleado empleadoSeleccionado=buscarEmpleado(idEmpleadoSeleccionado);
        Date date=Calendar.getInstance(TimeZone.getTimeZone("GMT-6:00")).getTime();
        List<Criterion> criterios=new LinkedList<Criterion>();
        criterios.add(Restrictions.eq("idEstatusCartera",1));
        List<EstatusCartera> executeSelect = sessionVarsBean.getDao().executeSelect(EstatusCartera.class, criterios);
        HistoriaCartera cartera=new HistoriaCartera(empleadoSeleccionado, executeSelect.get(0), clienteSeleccionado, clienteSeleccionado.getHistoriaCarteras().size(), date, null, null, null);
        Dao dao = sessionVarsBean.getDao();
        List<Criterion> criteriosCliente=new LinkedList<Criterion>();
        criteriosCliente.add(Restrictions.eq("idEstatusCliente", 1));
        clienteSeleccionado.setEstatusCliente((EstatusCliente)dao.executeSelect(EstatusCliente.class, criteriosCliente).get(0));
        Transaction transaction = dao.beginTransaction();
        dao.save(cartera);
        dao.update(clienteSeleccionado);
        dao.endTransaction(transaction, true);
        System.out.println("acabe de guardar la historia");
        return "menuPrincipal";
    }

    private Cliente buscarCliente(int idClienteSeleccionado) {
        for(Cliente cl:clientes){
            if(cl.getIdCliente()==idClienteSeleccionado){
                return cl;
            }
        }
        return null;
    }

    private Empleado buscarEmpleado(int idEmpleadoSeleccionado) {
        for(Empleado em:cobradores){
            if(em.getIdEmpleado()==idEmpleadoSeleccionado){
                return em;
            }
        }
        return null;
    }

    /**
     * @return the idClienteSeleccionado
     */
    public int getIdClienteSeleccionado() {
        return idClienteSeleccionado;
    }

    /**
     * @param idClienteSeleccionado the idClienteSeleccionado to set
     */
    public void setIdClienteSeleccionado(int idClienteSeleccionado) {
        this.idClienteSeleccionado = idClienteSeleccionado;
    }

    /**
     * @return the idEmpleadoSeleccionado
     */
    public int getIdEmpleadoSeleccionado() {
        return idEmpleadoSeleccionado;
    }

    /**
     * @param idEmpleadoSeleccionado the idEmpleadoSeleccionado to set
     */
    public void setIdEmpleadoSeleccionado(int idEmpleadoSeleccionado) {
        this.idEmpleadoSeleccionado = idEmpleadoSeleccionado;
    }

}
