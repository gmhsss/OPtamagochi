import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TamagotchiGUI extends JFrame {
    private Jogador jogador;
    private Tamagotchi tamagotchiSelecionado;
    private Planta plantaSelecionada;
    private JLabel energiaLabel, fomeLabel, plantaEnergiaLabel;
    private JComboBox<String> tamagotchiComboBox;
    private JComboBox<String> plantaComboBox;

    public TamagotchiGUI() {
        setTitle("Tamagotchi");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Carregar dados do jogador e iniciar interface
        jogador = Jogador.carregarDados();
        inicializarDados();
        initSelectionScreen();
    }

    private void inicializarDados() {
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

        // Seleciona o primeiro Tamagotchi e a primeira Planta por padrão
        tamagotchiSelecionado = jogador.getBichinhos().get(0);
        plantaSelecionada = jogador.getPlantas().get(0);
    }

    private void initSelectionScreen() {
        getContentPane().removeAll();
        repaint();

        JPanel selectionPanel = new JPanel();
        selectionPanel.setLayout(new BoxLayout(selectionPanel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Escolha o Tamagotchi:");
        selectionPanel.add(label);

        // ComboBox para selecionar o Tamagotchi
        tamagotchiComboBox = new JComboBox<>();
        for (Tamagotchi tamagotchi : jogador.getBichinhos()) {
            tamagotchiComboBox.addItem(tamagotchi.getNome());
        }

        tamagotchiComboBox.addActionListener(e -> {
            int index = tamagotchiComboBox.getSelectedIndex();
            tamagotchiSelecionado = jogador.getBichinhos().get(index);
            updateStatus();
        });

        selectionPanel.add(tamagotchiComboBox);

        // ComboBox para selecionar a Planta
        JLabel plantaLabel = new JLabel("Escolha a Planta:");
        selectionPanel.add(plantaLabel);

        plantaComboBox = new JComboBox<>();
        for (Planta planta : jogador.getPlantas()) {
            plantaComboBox.addItem(planta.getNome());
        }

        plantaComboBox.addActionListener(e -> {
            int index = plantaComboBox.getSelectedIndex();
            plantaSelecionada = jogador.getPlantas().get(index);
            updateStatus();
        });

        selectionPanel.add(plantaComboBox);

        JButton iniciarButton = new JButton("Iniciar Interação");
        iniciarButton.addActionListener(e -> initInteractionScreen());
        selectionPanel.add(iniciarButton);

        add(selectionPanel);
        setVisible(true);
        revalidate();
        repaint();
    }

    private void initInteractionScreen() {
        getContentPane().removeAll();
        repaint();

        JPanel interactionPanel = new JPanel();
        interactionPanel.setLayout(new BoxLayout(interactionPanel, BoxLayout.Y_AXIS));

        // Labels para mostrar o status
        energiaLabel = new JLabel("Energia do Tamagotchi: " + tamagotchiSelecionado.getEnergia());
        fomeLabel = new JLabel("Fome do Tamagotchi: " + tamagotchiSelecionado.getFome());
        plantaEnergiaLabel = new JLabel("Energia da Planta: " + plantaSelecionada.getEnergia());

        // Botões de interação com o Tamagotchi
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

        // Botão de interação com a Planta
        JButton regarButton = new JButton("Regar Planta");
        regarButton.addActionListener(e -> {
            plantaSelecionada.interagir();
            updateStatus();
        });

        // Adiciona os componentes ao painel
        interactionPanel.add(new JLabel("Interação com o Tamagotchi:"));
        interactionPanel.add(energiaLabel);
        interactionPanel.add(fomeLabel);
        interactionPanel.add(brincarButton);
        interactionPanel.add(alimentarButton);
        interactionPanel.add(dormirButton);

        interactionPanel.add(new JLabel("Interação com a Planta:"));
        interactionPanel.add(plantaEnergiaLabel);
        interactionPanel.add(regarButton);

        JButton voltarButton = new JButton("Voltar à Seleção");
        voltarButton.addActionListener(e -> initSelectionScreen());
        interactionPanel.add(voltarButton);

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
