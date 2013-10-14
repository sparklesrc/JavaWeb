package app.controller.socio;

import app.controller.socio.*;
import app.model.Socio;
import app.dao.SocioDAO;
import app.dao.ExcepcionDAO;
import app.model.Local;
import java.util.Collection;
import java.util.List;

public class SocioService {

    private SocioDAO socioDAO = null;

    public SocioService() {
        socioDAO = new SocioDAO();
    }

    public Collection<Socio> buscarPorNombre(String pat, String mat)
            throws ExcepcionDAO {
        return socioDAO.list();
    }

    public Socio insertar(Socio socio)
            throws ExcepcionDAO {
        return socioDAO.insertar(socio);
    }

    public Collection<Socio> get(Socio socio) throws ExcepcionDAO {
        return socioDAO.get(socio);
    }

    public void eliminar(Socio socio) throws ExcepcionDAO {
        socioDAO.eliminar(socio);
    }

    public Socio actualizar(Socio socio)
            throws ExcepcionDAO {
        return socioDAO.actualizar(socio);
    }

    public List<Local> list() {
        return null;
    }
}
