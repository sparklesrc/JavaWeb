package app.controller.servicio;

import app.model.Servicio;
import app.dao.ServiciosDAO;
import app.dao.ExcepcionDAO;
import app.model.Local;
import java.util.Collection;
import java.util.List;

public class ServicioService {
    
    private ServiciosDAO servicioDAO = null;
    
    public ServicioService(){
      servicioDAO = new ServiciosDAO();
    }

    
    public List<Servicio> list() throws ExcepcionDAO {
        return servicioDAO.list();
    }

    public Servicio get(Servicio servicio) throws ExcepcionDAO {
        return servicioDAO.get(servicio);
    }

    public Servicio save(Servicio servicio) throws ExcepcionDAO {
        return servicioDAO.save(servicio);
    }
    
    public Servicio update(Servicio servicio) throws ExcepcionDAO {
        return servicioDAO.update(servicio);
    }
    
    public void delete(Servicio servicio) throws ExcepcionDAO {
         servicioDAO.delete(servicio);
    }
    
}
