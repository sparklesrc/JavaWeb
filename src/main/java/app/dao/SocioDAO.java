package app.dao;

import app.model.Socio;
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
public class SocioDAO extends BaseDAO {

    public Collection<Socio> buscarPorNombre(String pat, String mat)
            throws ExcepcionDAO {
        String query = "select id, email, nombres, paterno, materno, celular, sexo, direcion from socio where paterno like ? and materno like ?";
        Collection<Socio> lista = new ArrayList<Socio>();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConexionDB.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setString(1, "%" + pat + "%");
            stmt.setString(2, "%" + mat + "%");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Socio item = new Socio();
                item.setId(rs.getInt("id"));
                item.setEmail(rs.getString("email"));
                item.setNombres(rs.getString("nombres"));
                item.setPaterno(rs.getString("paterno"));                
                item.setMaterno(rs.getString("materno"));                
                item.setCelular(rs.getString("celular"));
                item.setSexo(rs.getString("sexo"));
                item.setDireccion(rs.getString("direccion"));
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

    public Socio insertar(Socio socio) throws ExcepcionDAO {
        String query = "insert into socio (email, nombres, paterno, materno, celular, sexo, direccion) values (?,?,?,?,?,?,?)";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConexionDB.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setString(1, socio.getEmail());
            stmt.setString(2, socio.getNombres());
            stmt.setString(3, socio.getPaterno());
            stmt.setString(4, socio.getMaterno());
            stmt.setString(5, socio.getCelular());
            stmt.setString(6, socio.getSexo());
            stmt.setString(7, socio.getDireccion());
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
            socio.setId(id);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new ExcepcionDAO(e.getMessage());
        } finally {
            this.cerrarResultSet(rs);
            this.cerrarStatement(stmt);
            this.cerrarConexion(con);
        }
        return socio;
    }

    public void eliminar(Socio socio) throws ExcepcionDAO {
        String query = "delete from socio WHERE id=?";
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConexionDB.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setInt(1, (int) socio.getId());
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

    public Socio actualizar(Socio item) throws ExcepcionDAO {
        String query = "update socio set email=?, nombres=?, paterno=?, materno=?, celular=?, sexo=?, direccion=? where id=?";
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConexionDB.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setString(1, item.getEmail());
            stmt.setString(2, item.getNombres());
            stmt.setString(3, item.getPaterno());
            stmt.setString(4, item.getMaterno());
            stmt.setString(5, item.getCelular());
            stmt.setString(6, item.getSexo());
            stmt.setString(7, item.getDireccion());
            stmt.setLong(8, item.getId());
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
    public List<Socio> list() throws ExcepcionDAO {
        List<Socio> c = new ArrayList<Socio>();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConexionDB.obtenerConexion();
            String query = "select id,email, nombres, paterno, materno, celular, sexo, direccion"
                    + " from socio order by id";
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Socio item = new Socio();
                item.setId(rs.getInt("id"));
                item.setEmail(rs.getString("email"));
                item.setNombres(rs.getString("nombres"));
                item.setPaterno(rs.getString("paterno"));
                item.setMaterno(rs.getString("materno"));
                item.setCelular(rs.getString("celular"));
                item.setSexo(rs.getString("sexo"));
                item.setDireccion(rs.getString("direccion"));
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
    public Collection<Socio> get(Socio socio) throws ExcepcionDAO {

        String query = "select id, email, nombres, paterno, materno, celular, sexo, direccion from socio where id like ?";
        Collection<Socio> lista = new ArrayList<Socio>();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConexionDB.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setString(1, "%" + socio.getId() + "%");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Socio item = new Socio();
                item.setId(rs.getInt("id"));
                item.setEmail(rs.getString("email"));
                item.setNombres(rs.getString("nombres"));
                item.setPaterno(rs.getString("paterno"));
                item.setMaterno(rs.getString("materno"));
                item.setCelular(rs.getString("celular"));
                item.setSexo(rs.getString("sexo"));
                item.setDireccion(rs.getString("direccion"));
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
