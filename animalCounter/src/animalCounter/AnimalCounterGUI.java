package animalCounter;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AnimalCounterGUI extends JFrame {
	private static final long serialVersionUID = 1L;

    private Sheep sheep;
    private Alligator alligator;

    private JLabel welcomeLabel;
    private JLabel gatorLabel;
    private JLabel sheepLabel;

    private JButton addGatorButton;
    private JButton addSheepButton;
    private JButton displayButton;
    private JButton resetButton;
    private JButton exitButton;

    private JRadioButton gatorRadio;
    private JRadioButton sheepRadio;

    public AnimalCounterGUI() {

        sheep = new Sheep();
        alligator = new Alligator(sheep);

        setTitle("Animal Counter");
        setSize(400, 400);
        setLayout(new GridLayout(10, 1));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Welcome Label
        welcomeLabel = new JLabel("Welcome to the Animal Counter!", SwingConstants.CENTER);
        add(welcomeLabel);

        // Count Labels
        gatorLabel = new JLabel("Alligators: 0");
        sheepLabel = new JLabel("Sheep: 0");

        add(gatorLabel);
        add(sheepLabel);

        // Buttons
        addGatorButton = new JButton("Add Alligator");
        addSheepButton = new JButton("Add Sheep");
        displayButton = new JButton("Display Counts");
        resetButton = new JButton("Reset Counts");
        exitButton = new JButton("Exit");

        add(addGatorButton);
        add(addSheepButton);
        add(displayButton);
        add(resetButton);

        // Radio Buttons
        gatorRadio = new JRadioButton("Alligator");
        sheepRadio = new JRadioButton("Sheep");

        ButtonGroup group = new ButtonGroup();
        group.add(gatorRadio);
        group.add(sheepRadio);

        add(gatorRadio);
        add(sheepRadio);

        add(exitButton);

        // Single Inner Class Event Handler
        ButtonHandler handler = new ButtonHandler();

        addGatorButton.addActionListener(handler);
        addSheepButton.addActionListener(handler);
        displayButton.addActionListener(handler);
        resetButton.addActionListener(handler);
        exitButton.addActionListener(handler);

        setVisible(true);
    }

    // Inner Class Event Handler
    private class ButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == addGatorButton) {
                alligator.incrementCount();
                checkMessages();
            }

            if (e.getSource() == addSheepButton) {
                sheep.incrementCount();
                checkMessages();
            }

            if (e.getSource() == displayButton) {
                JOptionPane.showMessageDialog(null,
                        "Alligators: " + alligator.getCount() +
                                "\nSheep: " + sheep.getCount());
            }

            if (e.getSource() == resetButton) {

                if (gatorRadio.isSelected()) {
                    alligator.resetCount();
                } else if (sheepRadio.isSelected()) {
                    sheep.resetCount();
                } else {
                    JOptionPane.showMessageDialog(null, "Select an animal to reset.");
                }

                checkMessages();
            }

            if (e.getSource() == exitButton) {
                System.exit(0);
            }

            updateLabels();
        }
    }

    private void updateLabels() {
        gatorLabel.setText("Alligators: " + alligator.getCount());
        sheepLabel.setText("Sheep: " + sheep.getCount());
    }

    private void checkMessages() {

        if (alligator.getCount() > sheep.getCount()) {
            JOptionPane.showMessageDialog(null,
                    "Please add more sheep for the hungry alligators");
        }

        if (alligator.getCount() == 0) {
            JOptionPane.showMessageDialog(null,
                    "No alligators now so the sheep are safe");
        }
    }
}