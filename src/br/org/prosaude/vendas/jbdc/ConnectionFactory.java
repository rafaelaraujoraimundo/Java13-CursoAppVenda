/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.prosaude.vendas.jbdc;
import java.sql.DriverManager;
import java.sql.Connection;



/*a*/
/**
 *
 * @author rafael.araujo
 */
public class ConnectionFactory {
    
    public Connection getConnection(){
     try{
       return DriverManager.getConnection("jdbc:mysql://127.0.0.1/bdvendas?verifyServerCertificate=false&useSSL=true&useTimezone=true&serverTimezone=UTC","usuariocurso","123") ;  
     }
     catch (Exception erro){
         throw new RuntimeException(erro);
         
     }
        
    }
}
