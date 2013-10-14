package app.dao;

import app.model.Campo;
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
public class CampoDAO extends BaseDAO {

    public Collection<Campo> buscarPorNombre(String descripcion)
            throws ExcepcionDAO {
        String query = "select id, descripcion, estado, tipo, costo_hora, id_local from campo where descripcion like ?";
        Collection<Campo> lista = new ArrayList<Campo>();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConexionDB.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setString(1, "%" + descripcion + "%");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Campo item = new Campo();
                item.setId(rs.getInt("id"));
                item.setDescripcion(rs.getString("descripcion"));
                item.setEstado(rs.getInt("estado"));
                item.setTipo(rs.getInt("tipo"));
                item.setCosto_hora(rs.getDouble("costo_hora"));
                item.setLocal();
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

    public Campo insertar(Campo campo) throws ExcepcionDAO {
        String query = "insert into campo(descripcion, estado, tipo, costo_hora, id_local) values (?,?)";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConexionDB.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setString(1, campo.getDescripcion());
            stmt.setInt(2, campo.getEstado());
            stmt.setInt(3, campo.getTipo());            
            stmt.setDouble(4, campo.getCostoHora());
            stmt.setObject(5, campo.getLocal().getId());
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
            campo.setId(id);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new ExcepcionDAO(e.getMessage());
        } finally {
            this.cerrarResultSet(rs);
            this.cerrarStatement(stmt);
            this.cerrarConexion(con);
        }
        return campo;
    }

    public void eliminar(Campo campo) throws ExcepcionDAO {
        String query = "delete from campo WHERE id=?";
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConexionDB.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setInt(1, (int) campo.getId());
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

    public Campo actualizar(Campo item) throws ExcepcionDAO {
        String query = "update campo set descripcion=?, estado=?, tipo=?, costo_hora=?, id_local=? where id=?";
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConexionDB.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setString(1, item.getDescripcion());
            stmt.setInt(2, item.getEstado());
            stmt.setInt(3, item.getTipo());
            stmt.setDouble(4, item.getCostoHora());
            stmt.setDouble(5, item.getLocal().getId());
            stmt.setLong(6, item.getId());            
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
    public List<Campo> list() throws ExcepcionDAO {
        List<Campo> c = new ArrayList<Campo>();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConexionDB.obtenerConexion();
            String query = "select id,descripcion, estado, tipo, costo_hora, id_local "
                    + " from campo order by id";
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Campo item = new Campo();
                item.setId(rs.getInt("id"));
                item.setDescripcion(rs.getString("descripcion"));
                item.setEstado(rs.getInt("estado"));
                item.setTipo(rs.getInt("tipo"));                
                item.setCosto_hora(rs.getDouble("costo_hora"));
                item.setLocal();
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
    public Collection<Campo> get(Campo campo) throws ExcepcionDAO {

        String query = "select id, descripcion, estado, tipo, costo_hora, id_local from campo where id like ?";
        Collection<Campo> lista = new ArrayList<Campo>();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConexionDB.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setString(1, "%" + campo.getId() + "%");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Campo item = new Campo();
                item.setId(rs.getInt("id"));
                item.setDescripcion(rs.getString("descripcion"));
                item.setEstado(rs.getInt("estado"));
                item.setTipo(rs.getInt("tipo"));
                item.setCosto_hora(rs.getDouble("costo_hora"));
                item.setLocal();
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

}
