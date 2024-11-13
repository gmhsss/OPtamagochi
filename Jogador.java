import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Jogador {
    private String nome;
    private List<Tamagotchi> bichinhos;
    private List<Planta> plantas;

    public Jogador(String nome) {
        this.nome = nome;
        this.bichinhos = new ArrayList<>();
        this.plantas = new ArrayList<>();
    }

    public void adicionarTamagotchi(Tamagotchi tamagotchi) {
        bichinhos.add(tamagotchi);
    }

    public void adicionarPlanta(Planta planta) {
        plantas.add(planta);
    }

    public List<Tamagotchi> getBichinhos() {
        return bichinhos;
    }

    public List<Planta> getPlantas() {
        return plantas;
    }

    public String getNome() {
        return nome;
    }

    // Método para carregar dados de arquivo
    public static Jogador carregarDados() {
        Jogador jogador = new Jogador("Jogador Carregado");

        File arquivo = new File("jogo_salvo.txt");
        if (!arquivo.exists()) {
            // Cria um arquivo com dados de exemplo
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo))) {
                writer.write("Jogador: Jogador Carregado");
                writer.newLine();
                writer.write("Tamagotchis:");
                writer.newLine();
                writer.write("GatoTamagotchi,100,50"); // Exemplo de Tamagotchi
                writer.newLine();
                writer.write("Plantas:");
                writer.newLine();
                writer.write("PlantaExemplo,100");
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Jogador:")) {
                    jogador.nome = line.split(":")[1].trim();
                } else if (line.startsWith("Tamagotchis:")) {
                    while ((line = reader.readLine()) != null && !line.startsWith("Plantas:")) {
                        String[] partes = line.split(",");
                        String nomeTamagotchi = partes[0];
                        int energia = Integer.parseInt(partes[1]);
                        int fome = Integer.parseInt(partes[2]);
                        Tamagotchi tamagotchi = new Tamagotchi.Gato(nomeTamagotchi);
                        tamagotchi.alterarEnergia(energia - 100);
                        tamagotchi.alterarFome(fome - 50);
                        jogador.adicionarTamagotchi(tamagotchi);
                    }
                } else if (line.startsWith("Plantas:")) {
                    while ((line = reader.readLine()) != null) {
                        String[] partes = line.split(",");
                        String nomePlanta = partes[0];
                        int energiaPlanta = Integer.parseInt(partes[1]);
                        jogador.adicionarPlanta(new Planta(nomePlanta));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jogador;
    }

    public void salvarDados() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("jogo_salvo.txt"))) {
            writer.write("Jogador: " + nome);
            writer.newLine();

            writer.write("Tamagotchis:");
            writer.newLine();
            for (Tamagotchi tamagotchi : bichinhos) {
                writer.write(tamagotchi.getNome() + "," + tamagotchi.getEnergia() + "," + tamagotchi.getFome());
                writer.newLine();
            }

            writer.write("Plantas:");
            writer.newLine();
            for (Planta planta : plantas) {
                writer.write(planta.getNome() + "," + planta.getEnergia());
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
