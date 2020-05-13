/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.prosaude.vendas.model;

import br.org.prosaude.vendas.view.Frmclientes;
import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author rafael.araujo
 */
public class Utilitarios {

    //Limpar a Tela
    public void limparTela(JPanel container) {
     Component components[] = container.getComponents();
     for (Component component: components){
         if (component instanceof JTextField){
             ((JTextField) component).setText(null);
         }
     }
    }
    
}
