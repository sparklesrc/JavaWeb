package app.dao;

import app.model.Campo;
import app.model.SolicitudAlquiler;
import app.model.Local;
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
public class SolicitudAlquilerDAO extends BaseDAO {

    public SolicitudAlquiler insertar(SolicitudAlquiler solicitudalquiler) throws ExcepcionDAO {
        String query = "insert into solicitudalquiler(hora_inicio, hora_fin, dia, servicios, estado, id_socio, id_campo) values (?,?)";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConexionDB.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setString(1, solicitudalquiler.getHoraInicio());
            stmt.setString(2, solicitudalquiler.getHoraFin());
            stmt.setInt(3, solicitudalquiler.getDia());            
            stmt.setString(4, solicitudalquiler.getServicios());
            stmt.setInt(5, solicitudalquiler.getEstado());
            stmt.setLong(6, solicitudalquiler.getSocio().getId());
            stmt.setLong(7, solicitudalquiler.getCampo().getId());
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
            solicitudalquiler.setId(id);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new ExcepcionDAO(e.getMessage());
        } finally {
            this.cerrarResultSet(rs);
            this.cerrarStatement(stmt);
            this.cerrarConexion(con);
        }
        return solicitudalquiler;
    }

    public void eliminar(SolicitudAlquiler solicitudalquiler) throws ExcepcionDAO {
        String query = "delete from solicitudalquiler WHERE id=?";
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConexionDB.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setInt(1, (int) solicitudalquiler.getId());
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

    public SolicitudAlquiler actualizar(SolicitudAlquiler item) throws ExcepcionDAO {
        String query = "update solicitudalquiler set hora_inicio=?, hora_fin=?, dia=?, servicios=?, estado=?, "
                + " id_socio=?, id_campo=? where id=?";
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConexionDB.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setString(1, item.getHoraInicio());
            stmt.setString(2, item.getHoraFin());
            stmt.setInt(3, item.getDia());
            stmt.setString(4, item.getServicios());
            stmt.setInt(5, item.getEstado());
            stmt.setLong(6, item.getSocio().getId());
            stmt.setLong(7, item.getCampo().getId());            
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
    public List<SolicitudAlquiler> list() throws ExcepcionDAO {
        List<SolicitudAlquiler> c = new ArrayList<SolicitudAlquiler>();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConexionDB.obtenerConexion();
            String query = "select id,hora_inicio, hora_fin, dia, servicios, estado, id_socio, id_campo "
                    + " from solicitudalquiler order by id";
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();
            while (rs.next()) {
                SolicitudAlquiler item = new SolicitudAlquiler();
                Socio socio = new Socio();
                Campo campo = new Campo();
                item.setId(rs.getInt("id"));
                item.setHoraInicio(rs.getString("hora_inicio"));
                item.setHoraFin(rs.getString("hora_fin"));
                item.setEstado(rs.getInt("estado"));
                item.setDia(rs.getInt("dia"));
                item.setServicios(rs.getString("servicios"));                
                item.setEstado(rs.getInt("estado"));
                socio.setId(rs.getLong("id_socio"));
                campo.setId(rs.getLong("id_campo"));
                item.setSocio(socio);
                item.setCampo(campo);
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
    public Collection<SolicitudAlquiler> get(SolicitudAlquiler solicitudalquiler) throws ExcepcionDAO {

        String query = "select id, hora_inicio, hora_fin, dia, servicios, estado, id_socio, id_campo from solicitudalquiler where id like ?";
        Collection<SolicitudAlquiler> lista = new ArrayList<SolicitudAlquiler>();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConexionDB.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setString(1, "%" + solicitudalquiler.getId() + "%");
            rs = stmt.executeQuery();
            while (rs.next()) {
                SolicitudAlquiler item = new SolicitudAlquiler();
                Socio socio = new Socio();
                Campo campo = new Campo();
                item.setId(rs.getInt("id"));
                item.setHoraInicio(rs.getString("hora_inicio"));
                item.setHoraFin(rs.getString("hora_fin"));
                item.setEstado(rs.getInt("estado"));
                item.setDia(rs.getInt("dia"));
                item.setServicios(rs.getString("servicios"));                
                item.setEstado(rs.getInt("estado"));
                socio.setId(rs.getLong("id_socio"));
                campo.setId(rs.getLong("id_campo"));
                item.setSocio(socio);
                item.setCampo(campo);
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
