package app.controller.solicitudalquiler;

import app.controller.solicitudalquiler.*;
import app.model.SolicitudAlquiler;
import app.dao.SolicitudAlquilerDAO;
import app.dao.ExcepcionDAO;
import java.util.Collection;
import java.util.List;

public class SolicitudAlquilerService {

    private SolicitudAlquilerDAO solicitudalquilerDAO = null;

    public SolicitudAlquilerService() {
        solicitudalquilerDAO = new SolicitudAlquilerDAO();
    }

    public SolicitudAlquiler insertar(SolicitudAlquiler solicitudalquiler)
            throws ExcepcionDAO {
        return solicitudalquilerDAO.insertar(solicitudalquiler);
    }

    public Collection<SolicitudAlquiler> get(SolicitudAlquiler solicitudalquiler) throws ExcepcionDAO {
        return solicitudalquilerDAO.get(solicitudalquiler);
    }

    public void eliminar(SolicitudAlquiler solicitudalquiler) throws ExcepcionDAO {
        solicitudalquilerDAO.eliminar(solicitudalquiler);
    }

    public SolicitudAlquiler actualizar(SolicitudAlquiler solicitudalquiler)
            throws ExcepcionDAO {
        return solicitudalquilerDAO.actualizar(solicitudalquiler);
    }

    public List<SolicitudAlquiler> list() {
        return null;
    }
}
