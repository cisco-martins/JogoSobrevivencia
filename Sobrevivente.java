import java.util.ArrayList;
import java.util.List;

public class Sobrevivente {
    private String nome;
    private int saude;
    private List<Coletavel> inventario = new ArrayList<>();

    public Sobrevivente(String nome) {
        this.nome = nome;
        this.saude = 100;
    }

    public void modificarSaude(int qtd) {
        this.saude += qtd;
        if (this.saude > 100) this.saude = 100;
        if (this.saude < 0) this.saude = 0;
    }

    public void adicionarItem(Coletavel item) {
        inventario.add(item);
        IO.println("[+] Você obteve: " + item.getNome());
    }

    public void usarItem(int index) {
        if (index >= 0 && index < inventario.size()) {
            Coletavel item = inventario.remove(index);
            item.usar(this);
        } else {
            IO.println("Opção de item inválida!");
        }
    }

    public void listarInventario() {
        if (inventario.size() == 0) {
            IO.println("Inventário vazio.");
            return;
        }
        IO.println("--- SEU INVENTÁRIO ---");
        for (int i = 0; i < inventario.size(); i++) {
            IO.println((i + 1) + ". " + inventario.get(i).getNome());
        }
    }

    public boolean estaVivo() { return saude > 0; }
    public String getNome() { return nome; }
    public int getSaude() { return saude; }
    public boolean temItens() { return inventario.size() > 0; }
}
