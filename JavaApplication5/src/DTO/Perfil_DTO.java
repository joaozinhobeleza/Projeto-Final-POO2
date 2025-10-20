package DTO;

public class Perfil_DTO {

    String Nome, Usuario, Senha;
    int ID;

    public Perfil_DTO(int ID, String Nome, String Usuario, String Senha) {
        this.Nome = Nome;
        this.Usuario = Usuario;
        this.Senha = Senha;
        this.ID = ID;
    }

    public Perfil_DTO() {
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String Senha) {
        this.Senha = Senha;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
