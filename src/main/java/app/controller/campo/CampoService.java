/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller.campo;

import app.controller.campo.*;
import app.dao.ExcepcionDAO;
import app.dao.CampoDAO;
import app.model.Campo;
import app.model.Local;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author alumno
 */
public class CampoService {
    
    private CampoDAO campoDao = null;
    
    public CampoService(){
        campoDao = new CampoDAO();
    }
    
    public List<Local> list() {
        return campoDao.list();
    }

    public Campo get(Campo campo) {
        return campoDao.get(campo);
    }

    public Campo save(Campo campo) {
        return campoDao.save(campo);
    }
    
    public Campo update(Campo campo) {
        return campoDao.update(campo);
    }
    
    public void delete(Campo campo) {
        campoDao.delete(campo);
    }
    
}
