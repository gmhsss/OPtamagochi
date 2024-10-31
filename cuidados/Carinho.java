public class Carinho extends Cuidado {
    private int duracao;

    public Carinho(int intensidade, int duracao) {
        super(intensidade);
        this.duracao = duracao;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    @Override
    public void aplicarCuidado() {
        System.out.println("Dando carinho ao Tamagotchi por " + duracao + " minutos com intensidade " + getIntensidade());
        // Lógica para aumentar a felicidade do Tamagotchi
    }

    public void darCarinho() {
        aplicarCuidado();
    }
}
