/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.dao;

import app.model.Local;
import app.zelper.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author alumno
 */
public class LocalDAO extends BaseDAO {

    /*
     LIST - SELECT
     GET - ID
     SAVE - INSERT
     UPDATE - UPDATE
     DELETE - DELETE
     */
    public Collection<Local> listar() throws ExcepcionDAO {
        Collection<Local> c = new ArrayList<Local>();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConexionDB.obtenerConexion();
            String query = "select id,direccion,descripcion, estado, maps, telefono"
                    + " from local order by id";
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Local item = new Local();
                item.setId(rs.getInt("id"));
                item.setDireccion(rs.getString("direccion"));
                item.setDescripcion(rs.getString("descripcion"));
                item.setEstado(Integer.parseInt(rs.getString("estado")));
                item.setMaps(rs.getString("maps"));
                item.setTelefono(rs.getString("telefono"));
                c.add(item);
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new ExcepcionDAO(e.getMessage());
        } finally {
            this.cerrarResultSet(rs);
            this.cerrarStatement(stmt);
            this.cerrarConexion(con);
        }
        return c;
    }

    /*Busa por ID*/
    public Collection<Local> get(Local local) throws ExcepcionDAO {

        String query = "select id, direccion, descripcion, estado, maps, telefono from local where id like ?";
        Collection<Local> lista = new ArrayList<Local>();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConexionDB.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setString(1, "%" + local.getId() + "%");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Local item = new Local();
                item.setId(rs.getInt("id_categoria"));
                item.setDescripcion(rs.getString("descripcion"));
                item.setDireccion(rs.getString("direccion"));
                item.setEstado(Integer.parseInt(rs.getString("estado")));
                item.setMaps(rs.getString("maps"));
                item.setTelefono(rs.getString("telefono"));
                lista.add(item);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new ExcepcionDAO(e.getMessage());
        } finally {
            this.cerrarResultSet(rs);
            this.cerrarStatement(stmt);
            this.cerrarConexion(con);
        }
        System.out.println(lista.size());
        return lista;

    }

    /*Inserta Local*/
    public Local insertar(Local local) throws ExcepcionDAO {
        String query = "insert into local(direccion, descripcion,"
                + "estado, maps, telefono) values (?,?,?,?,?)";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConexionDB.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setString(1, local.getDireccion());
            stmt.setString(2, local.getDescripcion());
            stmt.setInt(2, local.getEstado());
            stmt.setString(2, local.getMaps());
            stmt.setString(2, local.getTelefono());
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
            local.setId(id);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new ExcepcionDAO(e.getMessage());
        } finally {
            this.cerrarResultSet(rs);
            this.cerrarStatement(stmt);
            this.cerrarConexion(con);
        }
        return local;
    }

    /*Update Local*/
    public Local actualizar(Local item) throws ExcepcionDAO {
        String query = "update local set direccion=?,descripcion=?, estado=?, maps=?, telefono=?"
                + " where id_categoria=?";
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConexionDB.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setString(1, item.getDireccion());
            stmt.setString(2, item.getDescripcion());
            stmt.setInt(3, item.getEstado());
            stmt.setString(2, item.getMaps());
            stmt.setString(2, item.getTelefono());
            int i = stmt.executeUpdate();
            if (i != 1) {
                throw new SQLException("No se pudo actualizar");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new ExcepcionDAO(e.getMessage());
        } finally {
            this.cerrarStatement(stmt);
            this.cerrarConexion(con);
        }
        return item;
    }

    /*Delete Local*/
    public void eliminar(Local local) throws ExcepcionDAO {
        String query = "delete from local WHERE id=?";
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConexionDB.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setInt(1, (int) local.getId());
            int i = stmt.executeUpdate();
            if (i != 1) {
                throw new SQLException("No se pudo eliminar");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new ExcepcionDAO(e.getMessage());
        } finally {
            this.cerrarStatement(stmt);
            this.cerrarConexion(con);
        }
    }
}
