/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller.socio;

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet("/adm/socio")
public class SocioController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Cuando se ingrese en la url la direccion /adm/local
        //vendra aqui y esto devolvera al cliente la pagina local.jsp
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/adm/local/socio.jsp");
        rd.forward(req, resp);
        
    }

    
}
