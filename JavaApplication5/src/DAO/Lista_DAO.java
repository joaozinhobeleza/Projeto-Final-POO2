package DAO;

import DTO.Tarefa_DTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Lista_DAO {

    public void inserir(Tarefa_DTO lista) {
        String sql = "INSERT INTO lista (check_list, titulo, conteudo, prazo) VALUES (?, ?, ?, ?)";

        try (Connection conn = Banco_DAO.Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, lista.getCheckList());
            stmt.setString(2, lista.getTitulo());
            stmt.setString(3, lista.getConteudo());
            stmt.setString(4, lista.getPrazo());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir item da lista: " + e.getMessage(), e);
        }
    }

    public List<Tarefa_DTO> listar() {
        String sql = "SELECT * FROM lista";
        List<Tarefa_DTO> listas = new ArrayList<>();

        try (Connection conn = Banco_DAO.Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Tarefa_DTO l = new Tarefa_DTO();
                l.setID(rs.getInt("id"));
                l.setCheckList(rs.getString("check_list"));
                l.setTitulo(rs.getString("titulo"));
                l.setConteudo(rs.getString("conteudo"));
                l.setPrazo(rs.getString("prazo"));
                listas.add(l);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar itens da lista: " + e.getMessage(), e);
        }

        return listas;
    }

    public Tarefa_DTO buscarPorId(int id) {
        String sql = "SELECT * FROM lista WHERE id = ?";

        try (Connection conn = Banco_DAO.Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Tarefa_DTO(
                    rs.getInt("id"),
                    rs.getString("check_list"),
                    rs.getString("titulo"),
                    rs.getString("conteudo"),
                    rs.getString("prazo")
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar item: " + e.getMessage(), e);
        }

        return null;
    }

    public void atualizar(Tarefa_DTO lista) {
        String sql = "UPDATE lista SET check_list=?, titulo=?, conteudo=?, prazo=? WHERE id=?";

        try (Connection conn = Banco_DAO.Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, lista.getCheckList());
            stmt.setString(2, lista.getTitulo());
            stmt.setString(3, lista.getConteudo());
            stmt.setString(4, lista.getPrazo());
            stmt.setInt(5, lista.getID());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar item: " + e.getMessage(), e);
        }
    }

    public void deletar(Tarefa_DTO tarefa_DTO) {
        String sql = "DELETE FROM lista WHERE id=?";

        try (Connection conn = Banco_DAO.Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            String id = JOptionPane.showInputDialog(null, "informe o id");
            
            stmt.setInt(1, Integer.parseInt(id));
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar item: " + e.getMessage(), e);
        }
    }
}
