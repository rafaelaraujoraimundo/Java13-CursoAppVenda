/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.prosaude.vendas.model;

/**
 *
 * @author rafael.araujo
 */
public class Fornecedores extends Clientes{
    
    //atributos
            private String cnpj;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
      
    @Override
    public String toString(){
        return  this.cnpj+ " - " + this.getNome();
    }
           
    
}
