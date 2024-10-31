public class Alimentar extends Cuidado {
    private int quantidade;
    private String tipoComida;

    public Alimentar(int intensidade, int quantidade, String tipoComida) {
        super(intensidade);
        this.quantidade = quantidade;
        this.tipoComida = tipoComida;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getTipoComida() {
        return tipoComida;
    }

    public void setTipoComida(String tipoComida) {
        this.tipoComida = tipoComida;
    }

    @Override
    public void aplicarCuidado() {
        System.out.println("Alimentando o Tamagotchi com " + quantidade + " unidades de " + tipoComida + " com intensidade " + getIntensidade());
        // Lógica para aumentar a saciedade do Tamagotchi
    }

    public void alimentar() {
        aplicarCuidado();
    }
}
