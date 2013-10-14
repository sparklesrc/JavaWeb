/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller.campo;

import app.controller.campo.*;
import app.dao.ExcepcionDAO;
import app.dao.CampoDAO;
import app.model.Campo;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author alumno
 */
public class CampoService {
    
    
    private CampoDAO campoDAO = null;
    
    public CampoService(){
        campoDAO=new CampoDAO();
    }
    
    
    public List<Campo> list(){
    return null;
    }
   
    public Collection<Campo> get(Campo campo) throws ExcepcionDAO{
    return campoDAO.get(campo);
    }
    
    public Campo save(Campo campo) throws ExcepcionDAO{
        return campoDAO.insertar(campo);
    }
    
    public Campo update(Campo campo) throws ExcepcionDAO{
        return campoDAO.actualizar(campo);
    }
    
    public void delete(Campo campo) throws ExcepcionDAO{
        campoDAO.eliminar(campo);
    }
    
    
    
}
