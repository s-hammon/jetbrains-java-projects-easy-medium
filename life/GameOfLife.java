package life;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

enum State {
    RUNNING, PAUSED, RESET,
}

public class GameOfLife extends JFrame {

    private JPanel infoPanel;
    private JPanel commandPanel;
    private UniversePanel universePanel;
    private UniverseCell[][] cellArray;
    private JLabel aliveLabel;
    private JLabel generationLabel;
    private JToggleButton playButton;
    private JButton resetButton;
    private int generation;
    volatile State state = State.RUNNING;
    volatile int n = 20;

    public GameOfLife() {
        super("Game of Life");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        infoPanel = new JPanel();
        infoPanel.setName("");
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        add(infoPanel, BorderLayout.PAGE_START);

        commandPanel = new JPanel();
        commandPanel.setName("Command Menu");
        commandPanel.setLayout(new BoxLayout(commandPanel, BoxLayout.X_AXIS));

        generationLabel = new JLabel();
        generationLabel.setName("GenerationLabel");
        generationLabel.setText("Generation #0");
        infoPanel.add(generationLabel);

        aliveLabel = new JLabel();
        aliveLabel.setName("AliveLabel");
        aliveLabel.setText("Alive: 0");
        infoPanel.add(aliveLabel);

        universePanel = new UniversePanel();
        universePanel.setName("Universe Panel");
        add(universePanel, BorderLayout.CENTER);

        this.playButton = new JToggleButton();
        playButton.setName("PlayToggleButton");
        playButton.setPreferredSize(new Dimension(40, 20));
        playButton.addActionListener(actionEvent -> {
            switch(state) {
                case PAUSED:
                    state = State.RUNNING;
                    break;
                case RUNNING:
                    state = State.PAUSED;
                    break;
            }
        });
        infoPanel.add(playButton);

        this.resetButton = new JButton();
        resetButton.setName("ResetButton");
        resetButton.setPreferredSize(new Dimension(40, 20));
        resetButton.addActionListener(actionEvent -> {
            try {
                n = 20;
            } catch (NumberFormatException ignored) {
            }
            state = State.RESET;
        });
        infoPanel.add(resetButton);

        setVisible(true);
    }

    public UniversePanel getUniversePanel() {
        return universePanel;
    }

    public JLabel getGenerationLabel() {
        return generationLabel;
    }

    public JLabel getAliveLabel() {
        return aliveLabel;
    }

    public void setPlayState(boolean playState) {
        if (playState) playButton.setText("Pause");
        else playButton.setText("Resume");
    }
}