public class Comida extends Recurso {
    private int energia;

    public Comida(String nome, int energia) {
        super(nome);
        this.energia = energia;
    }

    @Override
    public void usar(Sobrevivente sobrevivente) {
        IO.println("Você comeu " + nome + " e recuperou " + energia + " de saúde.");
        sobrevivente.modificarSaude(energia);
    }
}