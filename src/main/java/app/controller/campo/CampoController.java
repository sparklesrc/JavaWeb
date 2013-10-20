/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller.campo;

import app.model.Campo;
import app.model.Local;
import app.zelper.Constants;
import app.zelper.Helper;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alumno
 */
@WebServlet("/adm/campos")
public class CampoController extends HttpServlet {

    private CampoService service;

    public CampoController() {
        service = new CampoService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int action = Helper.toInteger(request.getParameter("action"), Constants.ACTION_LIST);
        
        switch (action) {
            case Constants.ACTION_CREATE:
                this.create(request, response);
                break;
                
            case Constants.ACTION_UPDATE:
                this.update(request, response);
                break;

            case Constants.ACTION_DELETE:
                this.delete(request, response);
                break;

            default:
                this.list(request, response);
                break;
        }

    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Campo campo = new Campo();
        campo.setDescripcion(request.getParameter("descripcion"));
        campo.setCosto_hora(Double.parseDouble(request.getParameter("costo")));
        campo.setLocal(null);
        campo.setTipo(Integer.parseInt(request.getParameter("tipo")));
        
        Long id = Long.parseLong(request.getParameter("id"));
        if(id > 0){
            campo.setId(id);
            service.update(campo);
        }else{
            service.save(campo);
        }

        response.sendRedirect(request.getContextPath()+"/adm/local");
    }
    

    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Local> locales = service.list();
        request.setAttribute("local", locales);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/adm/local/local.jsp");
        rd.forward(request, response);

    }

    protected void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("campo", new Campo());

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/adm/local/localForm.jsp");
        rd.forward(request, response);

    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Campo local = new Campo();
        local.setId(Long.parseLong(request.getParameter("id")));

        request.setAttribute("campo", service.get(local));

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/adm/local/localForm.jsp");
        rd.forward(request, response);

    }
    
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Campo campo = new Campo();
        campo.setId(Long.parseLong(request.getParameter("id")));
        service.delete(campo);

        response.sendRedirect(request.getContextPath()+"/adm/campo");

    }
    
}
