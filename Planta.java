import java.io.Serializable;

public class Planta implements Serializable {
    private String nome;
    private int energia;

    public Planta(String nome) {
        this.nome = nome;
        this.energia = 100;
    }

    public String getNome() {
        return nome;
    }

    public int getEnergia() {
        return energia;
    }

    public void alterarEnergia(int valor) {
        energia = Math.max(0, Math.min(100, energia + valor));
    }

    public void interagir() {
        System.out.println("Você regou a planta " + nome + "!");
        alterarEnergia(10);
    }
}
