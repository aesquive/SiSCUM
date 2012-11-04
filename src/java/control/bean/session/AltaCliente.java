
package control.bean.session;

import java.util.ArrayList;
import java.util.List;
import model.dao.Dao;
import model.pojos.Cliente;
import model.pojos.EstatusCliente;
import model.pojos.EstatusVivienda;
import model.pojos.Vivienda;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.MapModel;

/**
 *  Bean para poder dar de alta un cliente
 * @author alberto
 */
public class AltaCliente {

    /**
     * variable para guardar datos del cliente
     */
    private Cliente cliente;
    /**
     * variable para guardar datos de la vivienda
     */
    private Vivienda vivienda;
    /**
     * variable de la conexion de la base de datos
     */
    private Dao dao;
    public AltaCliente(){
        cliente=new Cliente();
        vivienda=new Vivienda();
        dao=new Dao();
    }

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the vivienda
     */
    public Vivienda getVivienda() {
        return vivienda;
    }

    /**
     * @param vivienda the vivienda to set
     */
    public void setVivienda(Vivienda vivienda) {
        this.vivienda = vivienda;
    }

    /**
     * metodo para guardar el cliente
     * @return 
     */
    public String guardarCliente(){
        List<Criterion> crit=new ArrayList<Criterion>();
        crit.add(Restrictions.eq("idEstatusCliente", 3));
        cliente.setEstatusCliente((EstatusCliente)dao.executeSelect(EstatusCliente.class, crit).get(0));
        vivienda.setCliente(cliente);
        List<Criterion> critViv=new ArrayList<Criterion>();
        critViv.add(Restrictions.eq("idEstatusVivienda", 1));
        vivienda.setEstatusVivienda((EstatusVivienda)dao.executeSelect(EstatusVivienda.class, critViv).get(0));
        Transaction transaction = dao.beginTransaction();
        dao.save(cliente);
        dao.save(vivienda);
        dao.endTransaction(transaction, true);
        return "menuPrincipal";
    }
}
