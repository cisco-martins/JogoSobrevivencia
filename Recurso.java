public abstract class Recurso implements Coletavel {
    protected String nome;

    public Recurso(String nome) {
        this.nome = nome;
    }

    @Override
    public String getNome() {
        return nome;
    }
}
