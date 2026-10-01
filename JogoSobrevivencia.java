import java.util.Random;
import java.util.Scanner;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        IO.println("========================================");
        IO.println("       SOBREVIVÊNCIA NA ILHA ");
        IO.println("========================================");
        IO.print("Digite o nome do sobrevivente: ");
        String nome = scanner.nextLine();

        Sobrevivente jogador = new Sobrevivente(nome);
        int diasSobrevividos = 0;
        int objetivoDias = 20;


        int usosFogueira = 0;
        int limiteFogueira = 3;


        jogador.adicionarItem(new Comida("Ração de Emergência", 15));


        while (jogador.estaVivo() && diasSobrevividos < objetivoDias) {
            diasSobrevividos++;
            IO.println("\n----------------------------------------");
            IO.println("DIA " + diasSobrevividos + " de " + objetivoDias + " | Saúde: " + jogador.getSaude() + "/100");
            IO.println("----------------------------------------");

            if (random.nextInt(100) < 30) {
                IO.println("🌩️ Uma tempestade atingiu a ilha! Você passou frio (-15 de saúde).");
                jogador.modificarSaude(-10);
            }

            if (!jogador.estaVivo()) break;

            // Menu de Ações
            IO.println("\nO que deseja fazer hoje?");
            IO.println("1. Explorar a ilha em busca de recursos");
            IO.println("2. Caçar um animal selvagem");
            IO.println("3. Acender uma fogueira (+10 HP) [" + usosFogueira + "/" + limiteFogueira + " usadas]");
            IO.println("4. Usar um item do inventário");
            IO.println("5. Apenas descansar");
            IO.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();

            if (opcao == 1) {
                IO.println("\n🔍 Você saiu para explorar a ilha...");
                int achado = random.nextInt(4);
                if (achado == 0) {
                    jogador.adicionarItem(new Comida("Frutas Silvestres", 20));
                } else if (achado == 1) {
                    jogador.adicionarItem(new Ferramenta("Machado de Pedra", 25));
                } else if (achado == 2) {
                    IO.println("⚠️ Você caiu em uma armadilha natural e se machucou! (-15 HP)");
                    jogador.modificarSaude(-15);
                } else {
                    IO.println("🐾 Um animal selvagem te surpreendeu e atacou! (-20 HP)");
                    jogador.modificarSaude(-20);
                }
            } else if (opcao == 2) {
                IO.println("\n🗡️ Você foi caçar...");
                if (random.nextBoolean()) {
                    IO.println("Sucesso! Você caçou um javali.");
                    jogador.adicionarItem(new Comida("Carne Assada", 35));
                } else {
                    IO.println("O animal reagiu e te atacou! (-20 HP)");
                    jogador.modificarSaude(-20);
                }
            } else if (opcao == 3) {
                if (usosFogueira < limiteFogueira) {
                    usosFogueira++;
                    IO.println("\n🔥 Você juntou lenha e acendeu uma fogueira. O calor te fez bem!");
                    IO.println("Usos de fogueira restantes: " + (limiteFogueira - usosFogueira));
                    jogador.modificarSaude(10);
                } else {
                    IO.println("\n🚫 Você já usou a fogueira o número máximo de vezes (" + limiteFogueira + "x) nesta sessão!");
                    IO.println("Você perdeu tempo tentando acendê-la sem madeira suficiente.");
                }
            } else if (opcao == 4) {
                if (jogador.temItens()) {
                    jogador.listarInventario();
                    IO.print("Digite o número do item para usar: ");
                    int itemIndex = scanner.nextInt() - 1;
                    jogador.usarItem(itemIndex);
                } else {
                    IO.println("Você não possui itens no inventário!");
                }
            } else if (opcao == 5) {
                IO.println("\n😴 Você decidiu poupar energias e descansou no acampamento.");
            } else {
                IO.println("Opção inválida! Você perdeu o dia tentando decidir o que fazer.");
            }


            if (jogador.estaVivo()) {
                jogador.modificarSaude(-5);
            }
        }

        IO.println("\n========================================");
        if (jogador.estaVivo()) {
            IO.println("PARABÉNS, " + jogador.getNome() + "! Você sobreviveu aos " + objetivoDias + " dias e foi resgatado!");
        } else {
            IO.println("GAME OVER! " + jogador.getNome() + " não resistiu aos perigos da ilha.");
        }
        IO.println("========================================");
    }
