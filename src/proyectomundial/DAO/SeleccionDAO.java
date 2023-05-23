package proyectomundial.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import proyectomundial.model.Resultados;
import proyectomundial.model.Seleccion;
import proyectomundial.util.BasedeDatos;
import static proyectomundial.util.BasedeDatos.ejecutarSQL;

/**
 *
 * @author miguelropero
 */
public class SeleccionDAO {

    public SeleccionDAO() {
        BasedeDatos.conectar();
    }

    public boolean registrarSeleccion(Seleccion seleccion) {

        String sql = "INSERT INTO a_Penaranda12.seleccion (nombres, continente, dt, nacionalidad) values("
                + "'" + seleccion.getNombre() + "', "
                + "'" + seleccion.getContinente() + "', "
                + "'" + seleccion.getDt() + "', "
                + "'" + seleccion.getNacionalidad() + "')";

        //BasedeDatos.conectar();
        boolean registro = BasedeDatos.ejecutarActualizacionSQL(sql);
        //BasedeDatos.desconectar();
        return registro;
    }

    public List<Seleccion> getSelecciones() {

        String sql = "SELECT nombres, continente, dt, nacionalidad FROM a_Penaranda12.seleccion";
        List<Seleccion> selecciones = new ArrayList<Seleccion>();

        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);

            if (result != null) {

                while (result.next()) {
                    Seleccion seleccion = new Seleccion(result.getString("nombres"), result.getString("continente"), result.getString("dt"), result.getString("nacionalidad"));
                    selecciones.add(seleccion);
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando selecciones");
        }

        return selecciones;
    }

    public List<Seleccion> getSeleccionesBusqueda(String nombreSeleccion) {
        System.out.println("LLEGAMOS HASTA GETSELECCIONESBUSQUEDA");
        String sql = "SELECT nombres, continente, dt, nacionalidad FROM a_Penaranda12.seleccion WHERE nombres LIKE ?";
        List<Seleccion> selecciones = new ArrayList<Seleccion>();

        try {
            if (BasedeDatos.conexion == null) {
                // Mostrar un mensaje de error o lanzar una excepción
                System.out.println("No hay conexión a la base de datos");
                return selecciones;
            }
            // Preparar la consulta SQL y establecer el parámetro
            PreparedStatement stmt = BasedeDatos.conexion.prepareStatement(sql);
            stmt.setString(1, "%" + nombreSeleccion + "%");

            // Ejecutar la consulta y obtener el resultado
            ResultSet result = stmt.executeQuery();

            if (result != null) {
                while (result.next()) {
                    Seleccion seleccion = new Seleccion(result.getString("nombres"), result.getString("continente"), result.getString("dt"), result.getString("nacionalidad"));
                    selecciones.add(seleccion);
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando selecciones");
        }

        return selecciones;
    }

    public String[][] getSeleccionesMatriz() {

        String[][] matrizSelecciones = null;
        List<Seleccion> selecciones = getSelecciones();

        if (selecciones != null && selecciones.size() > 0) {

            matrizSelecciones = new String[selecciones.size()][4];

            int x = 0;
            for (Seleccion seleccion : selecciones) {

                matrizSelecciones[x][0] = seleccion.getNombre();
                matrizSelecciones[x][1] = seleccion.getContinente();
                matrizSelecciones[x][2] = seleccion.getDt();
                matrizSelecciones[x][3] = seleccion.getNacionalidad();
                x++;
            }
        }

        return matrizSelecciones;
    }

    public int totalPartidos() {
        int totalPartidos = 0;
        try {
            ResultSet result = BasedeDatos.ejecutarSQL("select count(*) as total from a_penaranda12.seleccion");
            if (result.next()) {
                totalPartidos = result.getInt("total");
            }
        } catch (Exception e) {
            // Manejar la excepción o propagarla hacia arriba
        }
        return totalPartidos;
    }

    /*// cantidad de directos tecnicos
        JTextArea nacionadlidad = new JTextArea();
        cantidad.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
        cantidad.setAlignmentY(JTextArea.CENTER_ALIGNMENT);
        JLabel label3 = new JLabel();
        label3.setBackground(Color.LIGHT_GRAY);
        label3.setForeground(Color.white);
        a.add(label3);
        String texto_dt = "cantidad de nacionalidades por dt\n\n";
        for (Seleccion seleccion : seleccionesBusqueda) {
            texto_dt += seleccion.getNacionalidad() + ": " + seleccion.getCantidad_directores_tecnicos() + "\n";
        }
        label3.setText(texto_dt);*/
    
    public List<Seleccion> equipos_continente() {
        String sql = "select continente, count(continente) as conteo from a_penaranda12.seleccion s group by continente having count(continente)>1";
        List<Seleccion> selecciones = new ArrayList<Seleccion>();
        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);
            if (result != null) {

                while (result.next()) {
                    String nombres = result.getString("continente");
                    int asia = result.getInt("conteo");
                    System.out.println(nombres + " : " + asia);
                    Seleccion seleccion = new Seleccion(result.getString("continente"), result.getInt("conteo"));

                    selecciones.add(seleccion);

                }
            }

        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando selecciones continentes");
        }
        return selecciones;

    }

    public List<Seleccion> cantidadnacionalidades() {
        String sql = "SELECT nacionalidad, COUNT(*) AS cantidad_directores_tecnicos\n"
                + "FROM a_penaranda12.seleccion s \n"
                + "GROUP BY nacionalidad;";
        List<Seleccion> selecciones = new ArrayList<Seleccion>();
        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);
            if (result != null) {

                while (result.next()) {
                    String nombres = result.getString("nacionalidad");
                    int conteo = result.getInt("cantidad_directores_tecnicos");
                    System.out.println(nombres + " : " + conteo);
                    Seleccion seleccion = new Seleccion(result.getString("nacionalidad"), result.getInt("cantidad_directores_tecnicos"));

                    selecciones.add(seleccion);

                }
            }

        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando selecciones continentes");
        }

        return selecciones;

    }

}
