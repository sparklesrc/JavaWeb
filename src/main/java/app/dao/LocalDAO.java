/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.dao;
import app.model.Local;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author alumno
 */
public class LocalDAO extends BaseDAO{
    
    
    /*
     LIST - SELECT
     GET - ID
     SAVE - INSERT
     UPDATE - UPDATE
     DELETE - DELETE
     */
    
    
    public Local insertar(Local local) throws ExcepcionDAO {
        String query = "insert into categoria(nombre,descripcion) values (?,?)";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConexionDB.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setString(1, categoria.getNombre());
            stmt.setString(2, categoria.getDescripcion());
            int i = stmt.executeUpdate();
            if (i != 1) {
                throw new SQLException("No se pudo insertar");
            }
            // Obtener el ultimo id
            int id = 0;
            query = "select last_insert_id()";
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
            categoria.setIdCategoria(id);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new ExcepcionDAO(e.getMessage());
        } finally {
            this.cerrarResultSet(rs);
            this.cerrarStatement(stmt);
            this.cerrarConexion(con);
        }
        return categoria;
    }
    
    
    
}
