package app.dao;

import app.model.Local;
import app.model.Servicio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import app.zelper.ConexionDB;
import java.util.List;

/**
 *
 * @author LAB704-00
 */
public class ServiciosDAO extends BaseDAO {

    public List<Servicio> list() throws ExcepcionDAO {
        List<Servicio> lista = new ArrayList<Servicio>();

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConexionDB.obtenerConexion();
            String query = "select * from servicio order;";
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Servicio item = new Servicio();
                item.setId(rs.getInt("id"));
                item.setDescripcion(rs.getString("descripcion"));
                item.setCostoHora(rs.getDouble("costo_hora"));
                lista.add(item);
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            this.cerrarResultSet(rs);
            this.cerrarStatement(stmt);
            this.cerrarConexion(con);
        }
        return lista;
    }

    public Servicio get(Servicio servicio)
            throws ExcepcionDAO {
        String query = "select * from servicio where id = ?";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
       Servicio servicioOriginal = new Servicio();
        try {
            con = ConexionDB.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setLong(1,servicio.getId());
            rs = stmt.executeQuery();
                
            
            while (rs.next()) {
                servicioOriginal.setId(rs.getInt("id"));
                servicioOriginal.setDescripcion(rs.getString("descripcion"));
                servicioOriginal.setCostoHora(rs.getDouble("costo_hora"));
            }
        } catch (SQLException e) {
              System.err.println(e.getMessage());
        } finally {
            this.cerrarResultSet(rs);
            this.cerrarStatement(stmt);
            this.cerrarConexion(con);
        }
        return servicioOriginal;
    }

    public Servicio save(Servicio servicio) throws ExcepcionDAO {
        String query = "insert into servicio(descripcion,costo_hora) values (?,?)";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConexionDB.obtenerConexion();
            stmt = con.prepareStatement(query);

            stmt.setString(1, servicio.getDescripcion());
            stmt.setDouble(2, servicio.getCostoHora());
            int i = stmt.executeUpdate();
            if (i != 1) {
                throw new SQLException("No se pudo insertar");
            }
            int id = 0;
            query = "select last_insert_id()";
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
            servicio.setId(id);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            this.cerrarResultSet(rs);
            this.cerrarStatement(stmt);
            this.cerrarConexion(con);
        }
        return servicio;
    }
    
     public Servicio update(Servicio servicio) throws ExcepcionDAO {
        String query = "update servicio descripcion=?,costo_hora=? where id=?";
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConexionDB.obtenerConexion();
            stmt = con.prepareStatement(query);
             stmt.setString(1, servicio.getDescripcion());
            stmt.setDouble(2, servicio.getCostoHora());
            stmt.setLong(3, servicio.getId());
            
            int i = stmt.executeUpdate();
            if (i != 1) {
                throw new SQLException("No se pudo actualizar");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            this.cerrarStatement(stmt);
            this.cerrarConexion(con);
        }
        return servicio;
    }

    public void delete(Servicio servicio) throws ExcepcionDAO {
        String query = "delete from servicio WHERE id=?";
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConexionDB.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setLong(1, servicio.getId());
            int i = stmt.executeUpdate();
            if (i != 1) {
                throw new SQLException("No se pudo eliminar");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            this.cerrarStatement(stmt);
            this.cerrarConexion(con);
        }
    }

   
}
