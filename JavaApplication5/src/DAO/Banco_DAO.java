package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Banco_DAO {

    public static class Conexao { // 👈 Tornar a classe estática

        private static final String DB_TYPE = "mysql"; // ou "mysql"
        private static final String HOST = "localhost";
        private static final int PORT = 3306;
        private static final String DATABASE = "lista";
        private static final String USER = "root";
        private static final String PASSWORD = "root";

        public static Connection getConnection() {
            String url = construirURL(DB_TYPE, HOST, PORT, DATABASE);

            try {
                if (DB_TYPE.equalsIgnoreCase("mysql")) {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                } else if (DB_TYPE.equalsIgnoreCase("mariadb")) {
                    Class.forName("org.mariadb.jdbc.Driver");
                }

                return DriverManager.getConnection(url, USER, PASSWORD);

            } catch (ClassNotFoundException e) {
                throw new RuntimeException("Driver JDBC não encontrado para " + DB_TYPE + ": " + e.getMessage(), e);
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao conectar ao banco de dados (" + DB_TYPE + "): " + e.getMessage(), e);
            }
        }

        private static String construirURL(String tipo, String host, int port, String db) {
            switch (tipo.toLowerCase()) {
                case "mysql":
                    return String.format("jdbc:mysql://%s:%d/%s?useSSL=false&serverTimezone=UTC", host, port, db);
                case "mariadb":
                    return String.format("jdbc:mariadb://%s:%d/%s", host, port, db);
                default:
                    throw new IllegalArgumentException("Tipo de banco não suportado: " + tipo);
            }
        }

        // Teste rápido de conexão
        public static void main(String[] args) {
            try (Connection conn = getConnection()) {
                System.out.println("✅ Conexão estabelecida com sucesso usando " + DB_TYPE.toUpperCase());
            } catch (Exception e) {
                System.err.println("❌ Falha na conexão: " + e.getMessage());
            }
        }
    }
}
