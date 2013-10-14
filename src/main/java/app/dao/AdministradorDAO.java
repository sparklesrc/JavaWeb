/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.dao;

import app.model.Administrador;
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
public class AdministradorDAO extends BaseDAO {

    /*
     LIST - SELECT
     GET - ID
     SAVE - INSERT
     UPDATE - UPDATE
     DELETE - DELETE
     */
    public List<Administrador> list() throws ExcepcionDAO {
        List<Administrador> c = new ArrayList<Administrador>();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConexionDB.obtenerConexion();
            String query = "select id,usuario,password"
                    + " from general order by id";
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Administrador item = new Administrador();
                item.setId(rs.getInt("id"));
                item.setUsuario(rs.getString("usuario"));
                item.setPassword(rs.getString("password"));
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
    public Collection<Administrador> get(Administrador administrador) throws ExcepcionDAO {

        String query = "select id, usuario, password from administrador where id like ?";
        Collection<Administrador> lista = new ArrayList<Administrador>();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConexionDB.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setString(1, "%" + administrador.getId() + "%");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Administrador item = new Administrador();
                item.setId(rs.getInt("id"));
                item.setUsuario(rs.getString("usuario"));
                item.setPassword(rs.getString("password"));
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

    /*Inserta Administrador*/
    public Administrador insertar(Administrador administrador) throws ExcepcionDAO {
        String query = "insert into administrador(usuario, password)"
                + " values (?,?)";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConexionDB.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setString(1, administrador.getUsuario());
            stmt.setString(2, administrador.getPassword());
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
            administrador.setId(id);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new ExcepcionDAO(e.getMessage());
        } finally {
            this.cerrarResultSet(rs);
            this.cerrarStatement(stmt);
            this.cerrarConexion(con);
        }
        return administrador;
    }

    /*Update Administrador*/
    public Administrador actualizar(Administrador item) throws ExcepcionDAO {
        String query = "update administrador set usuario=?,password=?"
                + " where id=?";
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConexionDB.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setString(1, item.getUsuario());
            stmt.setString(2, item.getPassword());
            stmt.setLong(3, item.getId());
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

    /*Delete Administrador*/
    public void eliminar(Administrador administrador) throws ExcepcionDAO {
        String query = "delete from administrador WHERE id=?";
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConexionDB.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setInt(1, (int) administrador.getId());
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
