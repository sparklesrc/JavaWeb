package app.dao;

import app.model.Servicio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import app.zelper.ConexionDB;

/**
 *
 * @author LAB704-00
 */
public class ServiciosDAO extends BaseDAO {

    public Collection<Servicio> buscarPorNombre(String nombre)
            throws ExcepcionDAO {
        String query = "select id, descripcion, costo_hora from servicio where descripcion like ?";
        Collection<Servicio> lista = new ArrayList<Servicio>();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConexionDB.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setString(1, "%" + nombre + "%");
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
            throw new ExcepcionDAO(e.getMessage());
        } finally {
            this.cerrarResultSet(rs);
            this.cerrarStatement(stmt);
            this.cerrarConexion(con);
        }
        System.out.println(lista.size());
        return lista;
    }

    public Servicio insertar(Servicio servicio) throws ExcepcionDAO {
        String query = "insert into servicio(descripcion, costo_hora) values (?,?)";
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

    public Categoria obtener(int idCategoria) throws ExcepcionDAO {
        Categoria item = new Categoria();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String query = "select id_categoria, nombre, descripcion from categoria where id_categoria=?";
            con = ConexionDB.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setInt(1, idCategoria);
            rs = stmt.executeQuery();
            if (rs.next()) {
                item.setIdCategoria(rs.getInt(1));
                item.setNombre(rs.getString(2));
                item.setDescripcion(rs.getString(3));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new ExcepcionDAO(e.getMessage());
        } finally {
            this.cerrarResultSet(rs);
            this.cerrarStatement(stmt);
            this.cerrarConexion(con);
        }
        return item;
    }

    public void eliminar(int idCategoria) throws ExcepcionDAO {
        String query = "delete from categoria WHERE id_categoria=?";
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConexionDB.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setInt(1, idCategoria);
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

    public Categoria actualizar(Categoria item) throws ExcepcionDAO {
        String query = "update categoria set nombre=?,descripcion=? where id_categoria=?";
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConexionDB.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setString(1, item.getNombre());
            stmt.setString(2, item.getDescripcion());
            stmt.setInt(3, item.getIdCategoria());
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

    public Collection<Categoria> listar() throws ExcepcionDAO {
        Collection<Categoria> c = new ArrayList<Categoria>();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConexionDB.obtenerConexion();
            String query = "select id_categoria,nombre,descripcion from categoria order by nombre";
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Categoria item = new Categoria();
                item.setIdCategoria(rs.getInt("id_categoria"));
                item.setNombre(rs.getString("nombre"));
                item.setDescripcion(rs.getString("descripcion"));
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
}
