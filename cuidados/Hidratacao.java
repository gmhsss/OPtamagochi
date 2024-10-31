public class Hidratacao extends Cuidado {
    private int quantidadeAgua;
    private int frequencia;

    public Hidratacao(int intensidade, int quantidadeAgua, int frequencia) {
        super(intensidade);
        this.quantidadeAgua = quantidadeAgua;
        this.frequencia = frequencia;
    }

    public int getQuantidadeAgua() {
        return quantidadeAgua;
    }

    public void setQuantidadeAgua(int quantidadeAgua) {
        this.quantidadeAgua = quantidadeAgua;
    }

    public int getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(int frequencia) {
        this.frequencia = frequencia;
    }

    @Override
    public void aplicarCuidado() {
        System.out.println("Hidratando o Tamagotchi com " + quantidadeAgua + "ml de água a cada " + frequencia + " horas com intensidade " + getIntensidade());
        // Lógica para aumentar a hidratação do Tamagotchi
    }

    public void hidratar() {
        aplicarCuidado();
    }
}
