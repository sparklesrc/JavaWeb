package app.controller.servicio;

import app.model.Servicio;
import app.dao.ServiciosDAO;
import app.dao.ExcepcionDAO;
import app.model.Local;
import java.util.Collection;
import java.util.List;

public class ServicioService {

    private ServiciosDAO servicioDAO = null;

    public ServicioService() {
        servicioDAO = new ServiciosDAO();
    }

    public Collection<Servicio> buscarPorNombre(String nombre)
            throws ExcepcionDAO {
        return servicioDAO.list();
    }

    public Servicio insertar(Servicio servicio)
            throws ExcepcionDAO {
        return servicioDAO.insertar(servicio);
    }

    public Collection<Servicio> get(Servicio servicio) throws ExcepcionDAO {
        return servicioDAO.get(servicio);
    }

    public void eliminar(Servicio servicio) throws ExcepcionDAO {
        servicioDAO.eliminar(servicio);
    }

    public Servicio actualizar(Servicio servicio)
            throws ExcepcionDAO {
        return servicioDAO.actualizar(servicio);
    }

    public List<Local> list() {
        return null;
    }
}
