
package CapaAccesoDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClsBdAgenda {
    private Connection con = null;
    private Statement consulta;    
    private ResultSet resultado;
    private ClsRegAgenda r ;
    
    //constructor

    public ClsBdAgenda(String direccion) {
        try {
            con = DriverManager.getConnection("jdbc:ucanaccess://" + direccion);
            consulta = con.createStatement();
            System.out.println("conexion realizada con exito  ");
        }catch (Exception e){
            System.out.println("error conexiòn  " + e);
        }            
    }
    
    public ResultSet cargar() throws SQLException{
        String comando = "SELECT * FROM TbAgenda";
        consulta.execute(comando);
        resultado = consulta.getResultSet();       
        return resultado;
    }
    
    public void mostrar() throws SQLException{
        r.Cedula = resultado.getNString("Cedula");
        r.Nombre = resultado.getNString("Nombre");
        r.TlfFijo = resultado.getNString("TlfFijo");
        r.TlfMovil1 = resultado.getNString("TlfMovil1");
        r.TlfMovil2 = resultado.getNString("TlfMovil2");
        r.Direccion = resultado.getNString("Direccion");
    }
    
    public ClsRegAgenda siguiente(){
        r = new ClsRegAgenda();
        try {
            if(resultado.next()) mostrar();            
        } catch (SQLException ex) {
            System.out.println("fin de tabla " + ex);
        }
        return r;
    }
    
}
