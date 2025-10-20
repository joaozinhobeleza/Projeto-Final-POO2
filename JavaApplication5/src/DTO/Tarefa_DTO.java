package DTO;

public class Tarefa_DTO {
    
    String Check_List, titulo, conteudo, prazo;
    int ID;

    public Tarefa_DTO(int ID, String titulo, String conteudo, String prazo) {
        this.ID = ID;
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.prazo = prazo;
    }

    public Tarefa_DTO() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getPrazo() {
        return prazo;
    }

    public void setPrazo(String prazo) {
        this.prazo = prazo;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
