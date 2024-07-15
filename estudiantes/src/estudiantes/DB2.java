/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package estudiantes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Mateo
 */
public class DB2 {
    
    // Datos de conexión a tu base de datos
    
    
    private static final String URL = "jdbc:mysql://localhost:3306/usa el nombre de tu base de datos";
    private static final String USUARIO = "usa tu usuario";
    private static final String CONTRASENA = "usa tu contraseña";

    // Método para obtener la conexión a la base de datos; se llamará en cada funcioon
    public static Connection obtenerConexion() {
        Connection conexion = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            System.out.println("Conexión exitosa a la base de datos");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        return conexion;
    }
     public static void cerrarConexion(Connection conexion) {
        if (conexion != null) {
            try {
                conexion.close();
                System.out.println("Conexión cerrada");
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
     
      public static boolean verificarCuenta(String nombreUsuario, String contrasena) {
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean cuentaExiste = false;

        try {
            conexion = obtenerConexion();
            if (conexion != null) {
                String consulta = "SELECT COUNT(*) AS cuenta_existe FROM cuentas_usuario " +
                        "WHERE nombre_usuario = ? AND contraseña = ?";
                statement = conexion.prepareStatement(consulta);
                statement.setString(1, nombreUsuario);
                statement.setString(2, contrasena);
                resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    int cuenta = resultSet.getInt("cuenta_existe");
                    cuentaExiste = cuenta == 1;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar la cuenta: " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (conexion != null) conexion.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }

        return cuentaExiste;
    }
     
      
    // Buscas el dni de uno, editas lo que quieras; si dejas el espacio vacio no toca nada; sino queda como estaba

    public static void actualizarEstudiantePorDNI(String dniBuscado, String nuevoNombre, String nuevoCurso, String nuevaDivision, String nuevoTurno) {
    Connection conexion = null;
    PreparedStatement statement = null;

    try {
        conexion = obtenerConexion();
        if (conexion != null) {
            StringBuilder consulta = new StringBuilder("UPDATE estudiantes SET ");
            boolean seActualizoAlgo = false;

            if (!nuevoNombre.isEmpty()) {
                consulta.append("nombre = ?, ");
                seActualizoAlgo = true;
            }

            if (!nuevoCurso.isEmpty()) {
                consulta.append("curso = ?, ");
                seActualizoAlgo = true;
            }

            if (!nuevaDivision.isEmpty()) {
                consulta.append("division = ?, ");
                seActualizoAlgo = true;
            }

            if (!nuevoTurno.isEmpty()) {
                consulta.append("turno = ?, ");
                seActualizoAlgo = true;
            }

            if (seActualizoAlgo) {
                // Eliminamos la última coma y espacio antes del WHERE
                consulta.delete(consulta.length() - 2, consulta.length());
                consulta.append(" WHERE dni = ?;");

                statement = conexion.prepareStatement(consulta.toString());

                int index = 1;
                if (!nuevoNombre.isEmpty()) {
                    statement.setString(index++, nuevoNombre);
                }
                if (!nuevoCurso.isEmpty()) {
                    statement.setString(index++, nuevoCurso);
                }
                if (!nuevaDivision.isEmpty()) {
                    statement.setString(index++, nuevaDivision);
                }
                if (!nuevoTurno.isEmpty()) {
                    statement.setString(index++, nuevoTurno);
                }

                statement.setString(index, dniBuscado);

                int filasActualizadas = statement.executeUpdate();
                if (filasActualizadas > 0) {
                    System.out.println("Estudiante actualizado exitosamente.");
                } else {
                    System.out.println("No se encontró ningún estudiante con el DNI proporcionado.");
                }
            } else {
                System.out.println("No se proporcionaron datos para actualizar.");
            }
        }
    } catch (SQLException e) {
        System.out.println("Error al actualizar estudiante por DNI: " + e.getMessage());
    } finally {
        try {
            if (statement != null) statement.close();
            if (conexion != null) conexion.close();
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
}
    
    // Esto  retorna una lista al introducir el nombre que buscas

    public static List<String> buscarNombresPorLetra(char letra) {
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<String> nombres = new ArrayList<>();

        try {
            conexion = obtenerConexion();
            if (conexion != null) {
                String consulta = "SELECT nombre FROM estudiantes WHERE nombre LIKE ?";
                statement = conexion.prepareStatement(consulta);
                statement.setString(1, letra + "%");
                resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    String nombre = resultSet.getString("Nombre");
                    nombres.add(nombre);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar nombres por letra: " + e.getMessage());
         } finally {
        try {
            if (statement != null) statement.close();
            if (conexion != null) conexion.close();
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }

        return nombres;
    }
    
    public static List<String> buscarEstudiantesPorCurso(String cursoBuscado) {
        List<String> nombres = new ArrayList<>();

        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            conexion = obtenerConexion();
            if (conexion != null) {
                String consulta = "SELECT nombre FROM estudiantes WHERE curso = ?";
                statement = conexion.prepareStatement(consulta);
                statement.setString(1, cursoBuscado);
                resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    String nombre = resultSet.getString("nombre");
                    nombres.add(nombre);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar estudiantes por curso: " + e.getMessage());
        } finally {
        try {
            if (statement != null) statement.close();
            if (conexion != null) conexion.close();
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }

        return nombres;
    }
    
    // LLamalo desde el boton, porque no tiene parametros

     public static List<String> obtenerNombresEnOrdenAlfabetico() {
        List<String> nombres = new ArrayList<>();
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            conexion = obtenerConexion();
            if (conexion != null) {
                String consulta = "SELECT nombre FROM estudiantes ORDER BY nombre";
                statement = conexion.prepareStatement(consulta);
                resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    String nombre = resultSet.getString("nombre");
                    nombres.add(nombre);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener nombres en orden alfabético: " + e.getMessage());
        } finally {
        try {
            if (statement != null) statement.close();
            if (conexion != null) conexion.close();
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
        return nombres;
    }
     
     public static List<String> obtenerNombresEnOrdenAlfabetico_ZA() {
        List<String> nombres = new ArrayList<>();
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            conexion = obtenerConexion();
            if (conexion != null) {
                String consulta = "SELECT nombre FROM estudiantes ORDER BY nombre DESC";
                statement = conexion.prepareStatement(consulta);
                resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    String nombre = resultSet.getString("nombre");
                    nombres.add(nombre);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener nombres en orden alfabético: " + e.getMessage());
        } finally {
        try {
            if (statement != null) statement.close();
            if (conexion != null) conexion.close();
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
        return nombres;
    }
