

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.JOptionPane;

import Connection.Connect; //Importar cadena de Conexion

public class TabPaneController {

    @FXML private Button IngresarButton;
    @FXML private TextField BDNombre; //Caja de texto ingreso para Base de Datos(BD)
    @FXML private TextField BDId_cuerpo_agua;//Caja de texto ingreso para Base de Datos(BD)
    @FXML private TextField BDMunicipio;//Caja de texto ingreso para Base de Datos(BD)
    @FXML private TextField BDT_CuerpoDeAgua;//Caja de texto ingreso para Base de Datos(BD)
    @FXML private TextField BDTipo_Agua;//Caja de texto ingreso para Base de Datos(BD)
    @FXML private TextField BDIRCA;   //Caja de texto ingreso para Base de Datos(BD)
    @FXML private TextArea DatosIngresados;
    @FXML private Button ProcesarButton;
    @FXML private TextArea Salidas;
    @FXML private Button ObtenerButton;
    @FXML private Button BuscarBotton;
    @FXML private TextField Id_cuerpo_agua;
    @FXML private TextField Nombre;
    @FXML private TextField Municipio;
    @FXML private TextField T_CuerpoDeAgua;
    @FXML private TextField Tipo_Agua;
    @FXML private TextField IRCA;
    @FXML private Button EditarBotton;
    @FXML private Button EliminarBotton;
    @FXML private TextField TabEditarID;

    
    ArrayList<String>Lista_Cuerpo =new ArrayList<>(); //realizando la creacion del arreglo
    CuerpoDeAgua[] arrayCuerpos= new CuerpoDeAgua[100];


    @FXML void addbasededatos(ActionEvent event) { //Ingresar los datos a la BDatos CuerpoDEAgua
        //Establecer la conexion a base de datos. 
        Connect ObjConexion =new Connect();
        //capturar los datos ingresados en las cajas de textos. 
        //declarar los tipos de datos: 
        //este diseño que viene a continuacion va definido por lo que se definio y organizo en la base de datos
        //las declaracion de variables debe ser diferente al nombre que declaramos en las cajas de texto y que vamos a buscar.
         //aca vamos capturando el fxid de la informacion de la ventana.
        String BDnombre=BDNombre.getText(); //el BDnombre , debe ser llamado diferente al BDNombre.getText
        int  BDID =Integer.parseInt(BDId_cuerpo_agua.getText());
        String BDmunicipio =BDMunicipio.getText(); 
        String BDtipocuerpoagua = BDT_CuerpoDeAgua.getText();
        String BDtipoagua =BDTipo_Agua.getText();
        double BDirca =Double.parseDouble(BDIRCA.getText());

        //consulta para la base de datos. Cuando se va a crear algo en la base de datos. se usa este metodo. 
       try(Connection conn = ObjConexion.connect(); Statement stmt = conn.createStatement()){
           stmt.executeUpdate( //este metodo se usa, para insertar, modificar y eliminar; si solo es consultar es el "stmt.executeQuery"
               //hacer una insercion.INSERT INTO, es propio del SQl, luego se coloca el orden de la base de datos
           //los nombres que van dentro del INSERT es el que esta en la base de datos. 
               "INSERT INTO DatosCuerpoAgua (Nombre,ID,Municipio,TipoCuerpoAgua,TipoAgua,ClasificacionIRCA)"
               + "VALUES(" //hace referencia a los valores que se van a insertar en la base de datos
               //son los nombres que se definieron arriba. Cada vez que se va a insertar un texto hay que ingresarlo en comillas sencillas.
               +"'" + BDnombre +"'" + ","+ BDID + "," + "'" +BDmunicipio +"'" +"," +"'" + BDtipocuerpoagua +"'" + "," + "'" +BDtipoagua + "'" +"," + BDirca+");");
           System.out.println("Cuerpo de Agua creado");
           JOptionPane.showMessageDialog(null,"Cuerpo de Agua creado");
       } catch(SQLException e){ //capturar las exepciones que se puedan generar
           System.out.println(e.getMessage());
       }
        //Vaciar las Cajas de Texto al ingresar un nuevo cuerpo de Agua
        BDNombre.setText("");
        BDId_cuerpo_agua.setText("");
        BDMunicipio.setText("");
        BDT_CuerpoDeAgua.setText("");
        BDTipo_Agua.setText("");
        BDIRCA.setText("");
   }

    @FXML void BuscarID(ActionEvent event) {
        Id_cuerpo_agua.setText(TabEditarID.getText().trim());
        //Alerta cuando se digite un ID errado; 
        //Si el ID esta vacio, con espacio se genera el error
        if(Id_cuerpo_agua.getText().isEmpty() || TabEditarID.getText().equalsIgnoreCase(" ")){
            Alert Alerta = new Alert(Alert.AlertType.ERROR);//estructura del Alert. Error
            Alerta.setHeaderText(null);
            Alerta.setTitle("");
            Alerta.setContentText("Desea Editar o Eliminar.Favor digitar el ID correspondiente");
            Alerta.showAndWait();
        }
        else{
            int IDConsultado = Integer.parseInt(TabEditarID.getText());//Buscando la ID digita en el boton
            //String nom = nombres.getText();
            Connect objConexion = new Connect(); //Establecer la conexión con la base de datos
            String BuscarID= "SELECT * FROM DatosCuerpoAgua WHERE ID ="+IDConsultado;
            System.out.println(BuscarID);
            try(Connection conn = objConexion.connect(); Statement stmt = conn.createStatement()){
            ResultSet resultSet = stmt.executeQuery(BuscarID); //Ejecutar la consulta
                Nombre.setText(resultSet.getString(1)); 
                Id_cuerpo_agua.setText(resultSet.getString(2));
                Municipio.setText(resultSet.getString(3));
                T_CuerpoDeAgua.setText(resultSet.getString(4));
                Tipo_Agua.setText(resultSet.getString(5));
                IRCA.setText(resultSet.getString(6));
            } catch (SQLException error) {
                JOptionPane.showMessageDialog(null, "ID no encontrada"); //mensaje en la ventana interactiva
                System.out.println("ID no encontrada");
                Id_cuerpo_agua.setText("");
                System.out.println(error.getMessage());

                //Vaciar las Cajas de Texto Cuando no exita ID
                Nombre.setText("");
                Id_cuerpo_agua.setText("");
                Municipio.setText("");
                T_CuerpoDeAgua.setText("");
                Tipo_Agua.setText("");
                IRCA.setText("");
            }
        }
    }

    @FXML void EditarInformacion(ActionEvent event) {
        int IDConsultado = Integer.parseInt(TabEditarID.getText());//Buscando la ID digita en el boton
        Connect objConexion = new Connect(); //Establecer la conexión con la base de datos
        //Capturar el valor de cada uno de los datos de las cajas de texto Ventana Editar/Eliminar
        String BDnombre=Nombre.getText(); 
        //int  BDID =Integer.parseInt(Id_cuerpo_agua.getText());//Nose puede editar, llave primaria
        String BDmunicipio =Municipio.getText(); 
        String BDtipocuerpoagua = T_CuerpoDeAgua.getText();
        String BDtipoagua =Tipo_Agua.getText();
        double BDirca =Double.parseDouble(IRCA.getText());

        //La sentencia UPDATE se utiliza para modificar los valores en la BDatos Cuerpode Agua
        try(Connection conn = objConexion.connect(); Statement stmt = conn.createStatement()){
            stmt.executeUpdate("UPDATE  DatosCuerpoAgua SET "
            + "Nombre="+"'" + BDnombre +"'"+"," + "Municipio="+"'" +BDmunicipio +"'" +"," +"TipoCuerpoAgua="+"'" + BDtipocuerpoagua +"'" + "," + "TipoAgua="+"'" +BDtipoagua + "'" +","+ "ClasificacionIRCA="+ BDirca+ " WHERE ID="+IDConsultado);
            System.out.println("Cuerpo de Agua Modificado");
            JOptionPane.showMessageDialog(null, "Cuerpo de Agua Modificado"); //mensaje en la ventana interactiv
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "El Cuerpo de Agua no fue encontrado"); //mensaje en la ventana interactiv
            System.out.println("El Cuerpo de Agua no fue encontrado");
        }
    }

    @FXML void EliminarInformacion(ActionEvent event) {
        Connect objConexion = new Connect(); //Establecer la conexión con la base de datos
        //Capturar los valores digitados en las cajas de texto
        int IDConsultado = Integer.parseInt(Id_cuerpo_agua.getText());
       //Realizar la eliminación del registro en la tabla DatosCuerpoAgua.
       try(Connection conn = objConexion.connect(); Statement stmt = conn.createStatement()){
           stmt.executeUpdate("DELETE FROM DatosCuerpoAgua WHERE ID = "+IDConsultado);
           JOptionPane.showMessageDialog(null,"ID Cuerpo de Agua eliminado");
           System.out.println("ID Cuerpo de Agua eliminado");
       } catch (SQLException error) {
           System.out.println(error.getMessage());
       }
       //Vaciar las Cajas de Texto a Eliminar Cuerpo de Agua
       Nombre.setText("");
       Id_cuerpo_agua.setText("");
       Municipio.setText("");
       T_CuerpoDeAgua.setText("");
       Tipo_Agua.setText("");
       IRCA.setText("");

    }

    @FXML void addlinea(ActionEvent event) {
        //Mostrar los datos guardados en la base de datos de cuerpo de agua
        Connect ObjConexion = new Connect();
        String query = "SELECT * FROM DatosCuerpoAgua" ; //consulta SELECT:las columnas que quiero o si son todas: (SELECT *); y FROM:nombre de la tabla
        DatosIngresados.setEditable(false);//se define una variable para la lista de los productos a visualizar en el textarea
        try(Connection conn = ObjConexion.connect(); Statement stmt = conn.createStatement()){
            ResultSet resultBDCuerpo = stmt.executeQuery(query);//se va a obtener un resultado de la base de datos, a consultar
                while(resultBDCuerpo.next()){
                String BDnombre=resultBDCuerpo.getString(1);
                String BDID =resultBDCuerpo.getString(2);
                String BDmunicipio =resultBDCuerpo.getString(3);
                String BDtipocuerpoagua = resultBDCuerpo.getString(4);
                String BDtipoagua =resultBDCuerpo.getString(5);
                String BDirca =resultBDCuerpo.getString(6);

                System.out.println(BDnombre+ BDID+BDmunicipio+BDtipocuerpoagua+BDtipoagua+BDirca+"\n"); //imprimir en consola
                DatosIngresados.appendText(BDnombre+" "+ BDID+" "+BDmunicipio+" "+BDtipocuerpoagua+" "+BDtipoagua+" "+BDirca+" "+"\n");
                
                String Lista=BDnombre+" "+ BDID+" "+BDmunicipio+" "+BDtipocuerpoagua+" "+BDtipoagua+" "+BDirca;
                Lista_Cuerpo.add(Lista);
                    } 
        } catch (SQLException e){
            System.out.println("Dato Obtenido");  
            System.out.println(e.getMessage()); //e captura un error que arroje el sql

        }
    }

    @FXML void resullinea(ActionEvent event) {
        //Mostrar el resultado de los datos guardados en la base de datos de cuerpo de agua
        Connect ObjConexion = new Connect();
        String query = "SELECT * FROM DatosCuerpoAgua" ; //consulta SELECT:las columnas que quiero o si son todas: (SELECT *); y FROM:nombre de la tabla
        DatosIngresados.setEditable(false);//se define una variable para la lista de los productos a visualizar en el textarea
        try(Connection conn = ObjConexion.connect(); Statement stmt = conn.createStatement()){
        ResultSet resultBDCuerpo = stmt.executeQuery(query);//se va a obtener un resultado de la base de datos, a consultar
        while(resultBDCuerpo.next()){
                } 
            CuerpoDeAgua[] arrayCuerpos= new CuerpoDeAgua[100]; 
            double cont_total=0;
            int cont_medios=0; //Contadores
            String cuerpo=""; //variable para guardar la informacion. 
            for (int i=0; i<Lista_Cuerpo.size(); i++) {
                String[] array =Lista_Cuerpo.get(i).split(" "); //el nuevo array para hacer la separacion por espacio. 
                arrayCuerpos[i] =new CuerpoDeAgua(array[0],Integer.parseInt(array[1]),array[2],array[3],array[4], Double.parseDouble(array[5])); 
            }
            for (int i=0; i<Lista_Cuerpo.size(); i++) { //recorrido de el arreglo para hallar los recpectivas salidas
                if((arrayCuerpos[i].getIRCA())>0 && (arrayCuerpos[i].getIRCA())<=5){
                    cuerpo="SIN RIESGO";
                    cont_total+=1;
                }else{
                    if((arrayCuerpos[i].getIRCA())>=5.1 && (arrayCuerpos[i].getIRCA()<=14)){
                        cuerpo="BAJO";
                        cont_total+=1;
                    }else{
                        if((arrayCuerpos[i].getIRCA())>=14.1 && (arrayCuerpos[i].getIRCA())<=35){
                            cuerpo="MEDIO";
                            cont_total+=1;
                            cont_medios+=1;               
                        }else{
                            if((arrayCuerpos[i].getIRCA())>=35.1 && (arrayCuerpos[i].getIRCA())<=80){
                                cuerpo="ALTO";
                            }else{
                                if((arrayCuerpos[i].getIRCA())>=80.1 && (arrayCuerpos[i].getIRCA())<=100){
                                    cuerpo="INVIABLE SANITARIAMENTE"; 
                                }
                            }
        
                        }
                    }
                }
                Salidas.appendText(cuerpo+"\n");
            }
            String Cont_total=String.format(Locale.ROOT,"%.2f",cont_total);
            Salidas.appendText(Cont_total+"\n");

                for (int i=0; i<Lista_Cuerpo.size(); i++) {
                    if((arrayCuerpos[i].getIRCA())>=14.1 && (arrayCuerpos[i].getIRCA())<=35){
                        Salidas.appendText(arrayCuerpos[i].getNombre()+" ");
                        }   
                    }
                    if(cont_medios==0){
                        Salidas.appendText("NA");   
                    }
            
                    Salidas.appendText("\n");
            
                    double IRCA_menor=arrayCuerpos[0].getIRCA();
                    String IRCA_nomb=arrayCuerpos[0].getNombre();   
                    double Id_menor=arrayCuerpos[0].getId_cuerpo_agua();
            
                    for (int i=0; i<Lista_Cuerpo.size(); i++) {
                            if (IRCA_menor>arrayCuerpos[i].getIRCA()){
                            IRCA_menor= arrayCuerpos[i].getIRCA();
                            IRCA_nomb= arrayCuerpos[i].getNombre();
                            Id_menor=arrayCuerpos[i].getId_cuerpo_agua();
                            }
                        }
                        String ID_menor=String.format(Locale.ROOT,"%.2f",Id_menor);
                        Salidas.appendText(IRCA_nomb+" "+ID_menor+" ");                        
                        Salidas.appendText("\n");
                        System.out.println("Salidas esperadas");       
        } catch (SQLException e){
            System.out.println(e.getMessage()); //e captura un error que arroje el sql
        }

    }

}