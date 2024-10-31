public abstract class Cuidado {
    private int intensidade;

    public Cuidado(int intensidade) {
        this.intensidade = intensidade;
    }

    public int getIntensidade() {
        return intensidade;
    }

    public void setIntensidade(int intensidade) {
        this.intensidade = intensidade;
    }

    // Método abstrato para ser implementado nas subclasses
    public abstract void aplicarCuidado();
}
