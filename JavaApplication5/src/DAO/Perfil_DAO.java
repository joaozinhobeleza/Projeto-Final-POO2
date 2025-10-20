package DAO;

import DTO.Perfil_DTO;
import DTO.usuarioLogado;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Perfil_DAO {

    public boolean inserir(Perfil_DTO usuario) {
        String sql = "INSERT INTO usuario (nome, usuario, senha) VALUES (?, ?, ?)";

        try (Connection conn = Banco_DAO.Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getUsuario());
            stmt.setString(3, usuario.getSenha());
            return true;

        } catch (SQLException e) {
            System.err.println("Erro ao inserir usuário: " + e.getMessage());
            return false;
        }
    }

    public List<Perfil_DTO> listar() {
        String sql = "SELECT * FROM usuario";
        List<Perfil_DTO> usuarios = new ArrayList<>();

        try (Connection conn = Banco_DAO.Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Perfil_DTO u = new Perfil_DTO();
                u.setID(rs.getInt("id"));
                u.setNome(rs.getString("nome"));
                u.setUsuario(rs.getString("usuario"));
                u.setSenha(rs.getString("senha"));
                usuarios.add(u);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar usuários: " + e.getMessage(), e);
        }

        return usuarios;
    }

    public Perfil_DTO buscarPorUsuario(String usuario, String senha) {
        String sql = "SELECT * FROM usuario WHERE usuario = ? AND senha = ?";

        try (Connection conn = Banco_DAO.Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Perfil_DTO(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("usuario"),
                        rs.getString("senha")
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar usuário: " + e.getMessage(), e);
        }

        return null;
    }

    public void atualizar(Perfil_DTO usuario) {
        String sql = "UPDATE usuario SET nome=?, usuario=?, senha=? WHERE id=?";

        try (Connection conn = Banco_DAO.Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getUsuario());
            stmt.setString(3, usuario.getSenha());
            stmt.setInt(4, usuario.getID());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar usuário: " + e.getMessage(), e);
        }
    }

    public void deletar(Perfil_DTO perfil_DTO) {
        String sql = "DELETE FROM usuario WHERE usuario = ? AND senha = ?";

        try (Connection conn = Banco_DAO.Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            String usuario = JOptionPane.showInputDialog(null, "informe o usuario");
            String senha = JOptionPane.showInputDialog(null, "informe a senha");

            stmt.setString(1, usuario);
            stmt.setString(2, senha);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar usuário: " + e.getMessage(), e);
        }
    }

    public boolean validarLogin(Perfil_DTO perfil_DTO) {
        Connection conn = Banco_DAO.Conexao.getConnection();

        if (conn == null) {
            JOptionPane.showMessageDialog(null, "Erro de conexão com o banco!");
            return false;
        }

        String sql = "SELECT * FROM usuario WHERE usuario = ? AND senha = ?";

        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, perfil_DTO.getUsuario());
            pstm.setString(2, perfil_DTO.getSenha());

            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Login bem-sucedido!");

                perfil_DTO.setNome(rs.getString("nome"));
                perfil_DTO.setID(rs.getInt("id"));
                perfil_DTO.setSenha(rs.getString("senha"));
                usuarioLogado.setNome(perfil_DTO);
                usuarioLogado.setIdAtual(perfil_DTO);
                usuarioLogado.setSenha(perfil_DTO);

                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Login falhou. Verifique suas credenciais.");
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao fazer login: " + e.getMessage());
            return false;
        }
    }
}
