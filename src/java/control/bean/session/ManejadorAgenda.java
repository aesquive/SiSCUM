package control.bean.session;

import control.bean.Bean;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import model.dao.Dao;
import model.pojos.Cliente;
import model.pojos.EstatusConvenio;
import model.pojos.HistoriaCartera;
import model.pojos.HistoriaConvenio;
import model.pojos.HistoriaPagos;
import model.pojos.Vivienda;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author alberto
 */
public class ManejadorAgenda {

    private List<Cliente> clientes;
    private int tipoAccion;
    private SessionVarsBean sessionVarsBean;
    private Cliente clienteActual;
    private Vivienda viviendaActual;
    private HistoriaConvenio convenio;
    private HistoriaPagos pago;
    private Dao dao;
    private int indiceCliente;
    private String mensaje;
    private boolean finalizado;
    
    /**
     * Creates a new instance of ManejadorAgenda
     */
    public ManejadorAgenda() {
        indiceCliente = 0;
        finalizado=false;
        tipoAccion = -1;
        sessionVarsBean = (SessionVarsBean) Bean.getSessionBean("sessionVarsBean");
        dao = sessionVarsBean.getDao();
        dao.refreshObject(sessionVarsBean.getEmpleado());
        generarClientes();
        clienteActual = clientes.get(indiceCliente);
        if(clientes.isEmpty()){
            clienteActual=new Cliente();
            setMensaje("No hay registro de clientes");
            finalizado=true;
        }
        viviendaActual = (Vivienda) clienteActual.getViviendas().iterator().next();
        convenio = new HistoriaConvenio();
        pago = new HistoriaPagos();
    }

    /**
     * este metodo va a sacar a los clientes que se les debe cobrar NOTA Hay que
     * hacer este metodo dependiendo las reglas de negocio y la logistica de los
     * scores
     */
    private void generarClientes() {
        clientes = new LinkedList<Cliente>();
        Set<HistoriaCartera> cartera = sessionVarsBean.getEmpleado().getHistoriaCarteras();
        for (HistoriaCartera car : cartera) {
            if (car.getEstatusCartera().getIdEstatusCartera() == 1) {
                clientes.add(car.getCliente());
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
     * @return the tipoAccion
     */
    public int getTipoAccion() {
        return tipoAccion;
    }

    /**
     * @param tipoAccion the tipoAccion to set
     */
    public void setTipoAccion(int tipoAccion) {
        this.tipoAccion = tipoAccion;
    }

    /**
     * @return the clienteActual
     */
    public Cliente getClienteActual() {
        return clienteActual;
    }

    /**
     * @param clienteActual the clienteActual to set
     */
    public void setClienteActual(Cliente clienteActual) {
        this.clienteActual = clienteActual;
    }

    /**
     * @return the viviendaActual
     */
    public Vivienda getViviendaActual() {
        return viviendaActual;
    }

    /**
     * @param viviendaActual the viviendaActual to set
     */
    public void setViviendaActual(Vivienda viviendaActual) {
        this.viviendaActual = viviendaActual;
    }

    /**
     * @return the convenio
     */
    public HistoriaConvenio getConvenio() {
        return convenio;
    }

    /**
     * @param convenio the convenio to set
     */
    public void setConvenio(HistoriaConvenio convenio) {
        this.convenio = convenio;
    }

    /**
     * @return the pagos
     */
    public HistoriaPagos getPago() {
        return pago;
    }

    /**
     * @param pagos the pagos to set
     */
    public void setPago(HistoriaPagos pagos) {
        this.pago = pagos;
    }

    /**
     * maneja el submit de la pagina de la agenda cuando se hace un convenio
     *
     * @return
     */
    public String submitConvenio() {
        HistoriaCartera cartera = obtenerHistoriaCartera();
        if (cartera == null) {
            setMensaje("Error al buscar la cartera");
            return "agenda";
        }
        convenio.setFechaCreacion(Calendar.getInstance().getTime());
        convenio.setHistoriaCartera(cartera);
        convenio.setEstatusConvenio((EstatusConvenio) dao.executeSelectOneCriterion(EstatusConvenio.class, Restrictions.eq("idEstatusConvenio", 1)).get(0));
        if (convenio.getFechaCompromiso() != null && convenio.getMonto() != 0) {
            Transaction transaction = dao.beginTransaction();
            dao.save(convenio);
            dao.endTransaction(transaction, true);
            return recorrerCliente();
        }
        setMensaje("Revisar los campos");
        return "agenda";
    }

    /**
     * maneja el submit de la pagina de la agenda cuando se hace un pago
     *
     * @return
     */
    public String submitPago() {
        HistoriaCartera cartera = obtenerHistoriaCartera();
        if (cartera == null) {

            setMensaje("Error al buscar la cartera");
            return "agenda";
        }
        pago.setFechaPago(Calendar.getInstance().getTime());
        pago.setHistoriaCartera(cartera);
        if (pago.getMonto() != 0.0) {
            Transaction transaction = dao.beginTransaction();
            dao.save(pago);
            dao.endTransaction(transaction, true);
            return recorrerCliente();
        }
        setMensaje("Revisar los campos");
        return "agenda";
    }

    /**
     * maneja el submit de la pagina de la agenda cuando no se encuentra el
     * cliente
     *
     * @return
     */
    public String submitNoEsta() {
        return recorrerCliente();
    }

    private String recorrerCliente() {
        indiceCliente++;
        if (indiceCliente < clientes.size()) {
            clienteActual = clientes.get(indiceCliente);
            limpiarVariables();
            return "agenda";
        }
        finalizado=true;
        setMensaje("No hay mas clientes disponibles");
        return "menuPrincipal";
    }

    private HistoriaCartera obtenerHistoriaCartera() {
        List<Criterion> crits = new LinkedList<Criterion>();
        crits.add(Restrictions.eq("cliente", clienteActual));
        crits.add(Restrictions.eq("empleado", sessionVarsBean.getEmpleado()));
        List executeSelect = dao.executeSelect(HistoriaCartera.class, crits);
        if (executeSelect.isEmpty()) {
            return null;
        }
        return (HistoriaCartera) executeSelect.get(0);
    }


    private void limpiarVariables() {
        indiceCliente=0;
        pago=new HistoriaPagos();
        convenio=new HistoriaConvenio();
        setMensaje("");
    }

    /**
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * @return the finalizado
     */
    public boolean isFinalizado() {
        return finalizado;
    }

    /**
     * @param finalizado the finalizado to set
     */
    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }
}
