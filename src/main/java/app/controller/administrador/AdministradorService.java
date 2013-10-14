/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller.administrador;

import app.controller.administrador.*;
import app.dao.ExcepcionDAO;
import app.dao.AdministradorDAO;
import app.model.Administrador;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author alumno
 */
public class AdministradorService {
    
    
    private AdministradorDAO administradorDAO = null;
    
    public AdministradorService(){
        administradorDAO=new AdministradorDAO();
    }
    
    
    public List<Administrador> list(){
    return null;
    }
   
    public Collection<Administrador> get(Administrador administrador) throws ExcepcionDAO{
    return administradorDAO.get(administrador);
    }
    
    public Administrador save(Administrador administrador) throws ExcepcionDAO{
        return administradorDAO.insertar(administrador);
    }
    
    public Administrador update(Administrador administrador) throws ExcepcionDAO{
        return administradorDAO.actualizar(administrador);
    }
    
    public void delete(Administrador administrador) throws ExcepcionDAO{
        administradorDAO.eliminar(administrador);
    }
    
    
    
}
