package app.controller.servicio;

import app.model.Servicio;
import app.dao.ServiciosDAO;
import app.dao.ExcepcionDAO;
import java.util.Collection;

public class ServicioService {
    private ServiciosDAO dao=null;


    public ServicioService() {
        dao = new ServiciosDAO();
    }    
    
    public Collection<Servicio> buscarPorNombre(String nombre)
            throws ExcepcionDAO {
        return dao.listar();
    }


    public Servicio insertar(Servicio servicio)
            throws ExcepcionDAO {
        return dao.insertar(servicio);
    }

    public Servicio obtener(int idServicio) throws ExcepcionDAO {
        return dao.obtener(idServicio);
    }

    public void eliminar(int idServicio) throws ExcepcionDAO {
        dao.eliminar(idServicio);
    }

    public Servicio actualizar(Servicio servicio)
            throws ExcepcionDAO {
        return dao.actualizar(servicio);
    }

    public static void listar() throws ExcepcionDAO {
        ServiciosDAO dao = new ServiciosDAO();
        
        Collection<Servicio> servicios = dao.listar();
        
        for(Servicio servicio : servicios){
            System.out.println(servicio.getId() 
                    + " " + servicio.getDescripcion()
                    + " " + servicio.getCostoHora());
        }
    }    
}
