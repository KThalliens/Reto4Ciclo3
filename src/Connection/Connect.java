package Connection; // lo que se va a conectar con la base de datos


//todo este archivo es estandar.
import java.sql.Connection; //Librerias para acceder a la base de datos
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {

    public static Connection connect() { //crando el constructor
        // Ruta donde está al db creada
        //Se especifica la base de datos a la cual nos conectaremos

        //esto es lo que se cambia, la ruta, donde se encuentr ubicada pc
        String url = "jdbc:sqlite:C:/Users/rodri/OneDrive/Desktop/MINTIC/RetosJava2021/reto4/src/CuerpoDeAgua.db"; //solo se cambia esta linea, para el reto       


        Connection conn = null;

        try {
            // Creamos la conexión
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been stablished.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}