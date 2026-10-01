public class Ferramenta extends Recurso {
    private int protecao;

    public Ferramenta(String nome, int protecao) {
        super(nome);
        this.protecao = protecao;
    }

    @Override
    public void usar(Sobrevivente sobrevivente) {
        IO.println("Você usou " + nome + " para se proteger e ganhou " + protecao + " de resistência!");
        sobrevivente.modificarSaude(protecao);
    }
}