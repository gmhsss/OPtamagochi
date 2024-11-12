import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TamagotchiGUI extends JFrame {
    private Jogador jogador;
    private Tamagotchi tamagotchiSelecionado;
    private Planta plantaSelecionada;
    private JLabel energiaLabel, fomeLabel, plantaEnergiaLabel;
    private JComboBox<String> tamagotchiComboBox;

    public TamagotchiGUI() {
        setTitle("Tamagotchi");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        jogador = Jogador.carregarDados();

        // Verifica se a lista de Tamagotchis está vazia e adiciona um Tamagotchi padrão
        if (jogador.getBichinhos().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Não há Tamagotchis para carregar. Adicionando um Tamagotchi padrão.");
            Tamagotchi tamagotchiPadrao = new Tamagotchi.Gato("Tamagotchi Padrão");
            jogador.adicionarTamagotchi(tamagotchiPadrao);
        }

        // Verifica se a lista de Plantas está vazia e adiciona uma Planta padrão
        if (jogador.getPlantas().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Não há Plantas para carregar. Adicionando uma Planta padrão.");
            Planta plantaPadrao = new Planta("Planta Padrão");
            jogador.adicionarPlanta(plantaPadrao);
        }

        tamagotchiSelecionado = jogador.getBichinhos().get(0);
        plantaSelecionada = jogador.getPlantas().get(0);

        initInteractionScreen();
    }

    private void initInteractionScreen() {
        getContentPane().removeAll();
        repaint();

        JPanel interactionPanel = new JPanel();
        interactionPanel.setLayout(new BoxLayout(interactionPanel, BoxLayout.Y_AXIS));

        // ComboBox para selecionar o Tamagotchi
        tamagotchiComboBox = new JComboBox<>();
        for (Tamagotchi tamagotchi : jogador.getBichinhos()) {
            tamagotchiComboBox.addItem(tamagotchi.getNome());
        }

        tamagotchiComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = tamagotchiComboBox.getSelectedIndex();
                tamagotchiSelecionado = jogador.getBichinhos().get(index);
                updateStatus();
            }
        });

        energiaLabel = new JLabel("Energia do Tamagotchi: " + tamagotchiSelecionado.getEnergia());
        fomeLabel = new JLabel("Fome do Tamagotchi: " + tamagotchiSelecionado.getFome());
        plantaEnergiaLabel = new JLabel("Energia da Planta: " + plantaSelecionada.getEnergia());

        JButton brincarButton = new JButton("Brincar com Tamagotchi");
        brincarButton.addActionListener(e -> {
            Cuidado brincar = new Brincar(10);
            brincar.aplicarCuidado(tamagotchiSelecionado);
            updateStatus();
        });

        JButton alimentarButton = new JButton("Alimentar Tamagotchi");
        alimentarButton.addActionListener(e -> {
            Cuidado alimentar = new Alimentar(20);
            alimentar.aplicarCuidado(tamagotchiSelecionado);
            updateStatus();
        });

        JButton dormirButton = new JButton("Dormir com Tamagotchi");
        dormirButton.addActionListener(e -> {
            Cuidado dormir = new Dormir(15);
            dormir.aplicarCuidado(tamagotchiSelecionado);
            updateStatus();
        });

        JButton regarButton = new JButton("Regar Planta");
        regarButton.addActionListener(e -> {
            plantaSelecionada.interagir();
            updateStatus();
        });

        interactionPanel.add(new JLabel("Escolha o Tamagotchi:"));
        interactionPanel.add(tamagotchiComboBox);
        interactionPanel.add(energiaLabel);
        interactionPanel.add(fomeLabel);
        interactionPanel.add(plantaEnergiaLabel);
        interactionPanel.add(brincarButton);
        interactionPanel.add(alimentarButton);
        interactionPanel.add(dormirButton);
        interactionPanel.add(regarButton);

        add(interactionPanel);
        setVisible(true);
        revalidate();
        repaint();
    }

    private void updateStatus() {
        energiaLabel.setText("Energia do Tamagotchi: " + tamagotchiSelecionado.getEnergia());
        fomeLabel.setText("Fome do Tamagotchi: " + tamagotchiSelecionado.getFome());
        plantaEnergiaLabel.setText("Energia da Planta: " + plantaSelecionada.getEnergia());
    }
}
