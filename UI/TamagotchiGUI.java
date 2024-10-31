import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TamagotchiGUI extends JFrame { // Classe TamaGUI herda de JFrame, que é a janela principal da aplicação
    private Tamagotchi tamagotchi; // Objeto da classe Tamagotchi

    public TamagotchiGUI() { // Construtor da classe
        tamagotchi = new Tamagotchi(); // Inicializa o objeto Tamagotchi

        // Configuração da janela principal
        setTitle("Tamagotchi");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fecha a aplicação ao fechar a janela
        setLocationRelativeTo(null); // Centraliza a janela
        setLayout(new BorderLayout());

        initUI(); // Método que cria os elementos da interface

        setVisible(true); // Exibe a janela
    }

    private void initUI() { // Método que cria os elementos da interface
        // Painel principal para exibir as informações
        JPanel panel = new JPanel(); // Cria um painel
        panel.setLayout(new GridLayout(3, 1)); // Define o layout do painel

        // Informações do Tamagotchi
        JLabel statusLabel = new JLabel("Status do Tamagotchi"); // Cria um rótulo
        JLabel energiaLabel = new JLabel("Energia: " + tamagotchi.getEnergia()); 
        JLabel fomeLabel = new JLabel("Fome: " + tamagotchi.getFome());

        panel.add(statusLabel); // Adiciona os rótulos ao painel
        panel.add(energiaLabel);
        panel.add(fomeLabel);

        // Adiciona o painel à janela principal
        add(panel, BorderLayout.CENTER);

        // Botões de interação
        JPanel buttonPanel = new JPanel(); // Cria um painel
        buttonPanel.setLayout(new FlowLayout()); // Define o layout do painel

        JButton carinhoButton = new JButton("Dar Carinho"); // Cria botões
        JButton alimentarButton = new JButton("Alimentar");
        JButton hidratarButton = new JButton("Hidratar");

        // Adiciona ação aos botões
        carinhoButton.addActionListener(e -> { // Adiciona um evento ao botão (ActionListener chama os métodos correspondentes do Tamagotchi e atualiza as informações)
            tamagotchi.receberCarinho();
            updateStatus(energiaLabel, fomeLabel); // Atualiza as informações do Tamagotchi
        });

        alimentarButton.addActionListener(e -> {
            tamagotchi.alimentar();
            updateStatus(energiaLabel, fomeLabel);
        });

        hidratarButton.addActionListener(e -> {
            tamagotchi.hidratar();
            updateStatus(energiaLabel, fomeLabel);
        });

        buttonPanel.add(carinhoButton);
        buttonPanel.add(alimentarButton);
        buttonPanel.add(hidratarButton);

        add(buttonPanel, BorderLayout.SOUTH); // Adiciona o painel de botões à janela principal
    }

    private void updateStatus(JLabel energiaLabel, JLabel fomeLabel) { // Método que atualiza as informações do Tamagotchi
        energiaLabel.setText("Energia: " + tamagotchi.getEnergia());
        fomeLabel.setText("Fome: " + tamagotchi.getFome());
    }

    public static void main(String[] args) { // Método principal
        new TamagotchiGUI(); // Cria um objeto da classe TamagotchiGUI
    }
}
