/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller.local;

import app.dao.ExcepcionDAO;
import app.dao.LocalDAO;
import app.model.Local;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author alumno
 */
public class LocalService {
    
    
    private LocalDAO localDAO = null;
    
    public LocalService(){
        localDAO=new LocalDAO();
    }
    
    
    public List<Local> list(){
    return null;
    }
   
    public Collection<Local> get(Local local) throws ExcepcionDAO{
    return localDAO.get(local);
    }
    
    public Local save(Local local) throws ExcepcionDAO{
        return localDAO.insertar(local);
    }
    
    public Local update(Local local) throws ExcepcionDAO{
        return localDAO.actualizar(local);
    }
    
    public void delete(Local local) throws ExcepcionDAO{
        localDAO.eliminar(local);
    }
    
    
    
}
