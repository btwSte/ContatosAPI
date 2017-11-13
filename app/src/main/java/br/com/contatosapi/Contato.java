package br.com.contatosapi;


public class Contato {
    private String nome;
    private String telefone;
    private String foto;


    //Metodo para adicionar contatos
    public static Contato create(String nome, String telefone, String foto){
        Contato c = new Contato();
        c.setNome(nome);
        c.setTelefone(telefone);
        c.setFoto(foto);
        return c;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
