/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.prosaude.vendas.dao;

import br.org.prosaude.vendas.jbdc.ConnectionFactory;
import br.org.prosaude.vendas.model.Clientes;
import br.org.prosaude.vendas.model.Fornecedores;
import br.org.prosaude.vendas.model.Produtos;
import java.sql.Connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author rafael.araujo
 */
public class ProdutosDAO {

    private Connection con;

    //COnectar ao banco
    public ProdutosDAO() {
        this.con = new ConnectionFactory().getConnection();
    }

    ///Metodo Cadastrar Produtos
    public void cadastrarProdutos(Produtos obj) {
        try {
            String sql = "insert into tb_produtos(descricao,preco,qtd_estoque,for_id)values (?,?,?,?) ";
            PreparedStatement smt = con.prepareStatement(sql);
            smt.setString(1, obj.getDescricao());
            smt.setDouble(2, obj.getPreco());
            smt.setInt(3, obj.getQtd_estoque());
            smt.setInt(4, obj.getFornecedor().getId());
            smt.execute();
            smt.close();

            JOptionPane.showMessageDialog(null, "Produto: " + obj.getDescricao() + "\n"
                    + "Cadastrado com sucesso!");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
        }
    }

    public void AlterarProdutos(Produtos obj) {
        try {
            String sql = "update tb_produtos set descricao = ?,preco = ?,qtd_estoque = ?,for_id = ? where id = ?";
            PreparedStatement smt = con.prepareStatement(sql);
            smt.setString(1, obj.getDescricao());
            smt.setDouble(2, obj.getPreco());
            smt.setInt(3, obj.getQtd_estoque());
            smt.setInt(4, obj.getFornecedor().getId());
            smt.setInt(5, obj.getId());
            smt.execute();
            smt.close();

            JOptionPane.showMessageDialog(null, "Produto: " + obj.getDescricao() + "\n"
                    + "Alterado com sucesso!");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
        }
    }

    public List<Produtos> listaProdutos() {
        try {

            //1 passo cria a lista
            List<Produtos> lista = new ArrayList<>();

            // 2 passo criar o sql, organizar e executar
            String sql = "SELECT p.id, p.descricao, p.preco, p.qtd_estoque, f.nome FROM tb_produtos as p left join tb_fornecedores as F on (p.for_id = f.id)";

            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Produtos obj = new Produtos();
                Fornecedores f = new Fornecedores();
                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getInt("p.preco"));
                obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));

                // PEga o Objeto class fornecedores e seta o nome dele
                f.setNome(rs.getString("f.nome"));
                // linka o objeto fornecedor com o obj Produtos
                obj.setFornecedor(f);
                // Adiciona na variavel lista do tipo arraylist o objeto capturado após o Select usado no comando ResultSet
                lista.add(obj);

            }
            return lista;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro:" + e);
            return null;
        }
    }

    public void excluirProdutos(Produtos obj) {
        try {
            //1 Passo - criar o comando sql
            String sql = "delete from tb_produtos where id = ?";

            //2 Passo - conectar o banco de dados e organizar ocomando sql
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getId());
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Cliente: " + obj.getId() + " - " + obj.getDescricao() + "\n"
                    + "Excluido com sucesso!");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
        }
    }

    public List<Produtos> ListarProdutosPorNome(String nome) {
        try {

            //1 passo cria a lista
            List<Produtos> lista = new ArrayList<>();

            // 2 passo criar o sql, organizar e executar
            String sql = "SELECT p.id, p.descricao, p.preco, p.qtd_estoque, f.nome FROM tb_produtos as p "
                    + "left join tb_fornecedores as F on (p.for_id = f.id) where p.descricao like ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Produtos obj = new Produtos();
                Fornecedores f = new Fornecedores();
                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getInt("p.preco"));
                obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));

                // PEga o Objeto class fornecedores e seta o nome dele
                f.setNome(rs.getString("f.nome"));
                // linka o objeto fornecedor com o obj Produtos
                obj.setFornecedor(f);
                // Adiciona na variavel lista do tipo arraylist o objeto capturado após o Select usado no comando ResultSet
                lista.add(obj);

            }
            return lista;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro:" + e);
            return null;
        }
    }

    public Produtos ConsultaProdutosPorNome(String nome) {
        try {

            //1 passo cria a lista
            // 2 passo criar o sql, organizar e executar
            String sql = "SELECT p.id, p.descricao, p.preco, p.qtd_estoque, f.nome FROM tb_produtos as p "
                    + "left join tb_fornecedores as F on (p.for_id = f.id) where p.descricao = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
             Produtos obj = new Produtos();
                Fornecedores f = new Fornecedores();
            if (rs.next()) {
               
                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getInt("p.preco"));
                obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));

                // PEga o Objeto class fornecedores e seta o nome dele
                f.setNome(rs.getString("f.nome"));
                // linka o objeto fornecedor com o obj Produtos
                obj.setFornecedor(f);
                // Adiciona na variavel lista do tipo arraylist o objeto capturado após o Select usado no comando ResultSet
                
            }
        return obj;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro:" + e);
            return null;
        }
       
    }
}
