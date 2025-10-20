package DTO;

import DTO.Perfil_DTO;

public class usuarioLogado {

    private static Perfil_DTO usuarioAtual;
    private static Perfil_DTO NomeAtual;
    private static Perfil_DTO IdAtual;
    private static Perfil_DTO Senha;

    public static void setUsuario(Perfil_DTO usuario) {
        usuarioAtual = usuario;
    }
    
    public static void setNome(Perfil_DTO nome){
        NomeAtual = nome;
    }

    public static Perfil_DTO getUsuarioAtual() {
        return usuarioAtual;
    }    
    
    public static Perfil_DTO getNome() {
        return NomeAtual;
    }

    public static Perfil_DTO getIdAtual() {
        return IdAtual;
    }

    public static void setIdAtual(Perfil_DTO IdAtual) {
        usuarioLogado.IdAtual = IdAtual;
    }

    public static void limparUsuario() {
        usuarioAtual = null;
        NomeAtual = null;
        IdAtual = null;
        Senha = null;
    }

    public static boolean estaLogado() {
        return usuarioAtual != null;
    }

    public static Perfil_DTO getSenha() {
        return Senha;
    }

    public static void setSenha(Perfil_DTO Senha) {
        usuarioLogado.Senha = Senha;
    }
}
