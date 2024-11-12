abstract class Cuidado {
    private int intensidade;

    public Cuidado(int intensidade) {
        this.intensidade = intensidade;
    }

    public int getIntensidade() {
        return intensidade;
    }

    public abstract void aplicarCuidado(Tamagotchi tamagotchi);
}

// Classe Alimentar que reduz a fome
class Alimentar extends Cuidado {
    public Alimentar(int intensidade) {
        super(intensidade);
    }

    @Override
    public void aplicarCuidado(Tamagotchi tamagotchi) {
        tamagotchi.alterarFome(-getIntensidade());
        System.out.println("Você alimentou o Tamagotchi, reduzindo sua fome em " + getIntensidade() + " pontos.");
    }
}

// Classe Brincar que aumenta a fome e reduz energia
class Brincar extends Cuidado {
    public Brincar(int intensidade) {
        super(intensidade);
    }

    @Override
    public void aplicarCuidado(Tamagotchi tamagotchi) {
        tamagotchi.alterarEnergia(-getIntensidade());
        tamagotchi.alterarFome(getIntensidade() / 2);
        System.out.println("Você brincou com o Tamagotchi, reduzindo sua energia em " + getIntensidade() + " pontos e aumentando sua fome.");
    }
}

// Classe Dormir que recupera energia
class Dormir extends Cuidado {
    public Dormir(int intensidade) {
        super(intensidade);
    }

    @Override
    public void aplicarCuidado(Tamagotchi tamagotchi) {
        tamagotchi.alterarEnergia(getIntensidade());
        System.out.println("O Tamagotchi dormiu e recuperou " + getIntensidade() + " pontos de energia.");
    }
}
