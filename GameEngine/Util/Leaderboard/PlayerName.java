package GameEngine.Util.Leaderboard;

import GameEngine.GameLauncher;
import GameEngine.Graphics.AssetManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class PlayerName extends JFrame {
    AssetManager assetManager;

    GameLauncher gameLauncher;

    public String playerName;
    public boolean Confirm;
    public boolean Activated;

    public PlayerName(AssetManager assetManager)
    {
        this.assetManager = assetManager;
    }

    public void Add()
    {
        int x = 550;
        int y = 340;

        setTitle("Game Name Input");
        setSize(220, 100);
        setLayout(new BorderLayout());

        setUndecorated(true);
        setLocationRelativeTo(GameLauncher.window);
        setVisible(true);

        // Position the second frame relative to the first
        Point frame1Location = GameLauncher.window.getLocation();
        this.setLocation(frame1Location.x + x, frame1Location.y + y);

        // Add a ComponentListener to the first frame to track movement
        GameLauncher.window.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentMoved(ComponentEvent e) {
                // Get the new location of frame1
                Point newLocation = GameLauncher.window.getLocation();

                // Move frame2 relative to frame1's new position
                setLocation(newLocation.x + x, newLocation.y + y);
            }
        });

        // Create the input panel
        JPanel inputPanel = new JPanel(new FlowLayout());
        JLabel nameLabel = new JLabel("Enter your name: ");
        JTextField nameField = new JTextField(12);
        JButton submitButton = new JButton("Submit");

        nameLabel.setFont(assetManager.Pixel);
        nameLabel.setFont(nameLabel.getFont().deriveFont(30f));
        nameLabel.setForeground(new Color(73, 29, 0));
        inputPanel.setBackground(new Color(255, 241, 188));

        submitButton.setFont(assetManager.Pixel);
        submitButton.setFont(submitButton.getFont().deriveFont(20f));
        submitButton.setForeground(new Color(255, 253, 208));
        submitButton.setBackground(new Color(197, 104, 45));

        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(submitButton);
        this.setAlwaysOnTop(true);

        // Add the input panel to the frame
        add(inputPanel, BorderLayout.CENTER);

        // Handle the submit button action
        submitButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerName = nameField.getText();
                if (playerName.isEmpty()) {
                    JDialog dialog = new JDialog();
                    dialog.setAlwaysOnTop(true);
                    JOptionPane.showMessageDialog(dialog, "Name cannot be empty!");
                } else {
                    nameField.setText("");
                    Confirm = true;
                    Activated = true;
                    setVisible(false);
                }
            }
        });
    }

    public void update()
    {

    }

    public void input()
    {

    }

    public void render(Graphics2D g)
    {
        g.drawImage(assetManager.Shade, 0, 0, assetManager.getScreenWidth(), assetManager.getScreenHeight(), null);


        assetManager.TextBox(250, 150, 800, 400, g);
        assetManager.PrintText("CONGRATULATIONS, YOU WIN!!", 325, 270, 0, 60, true, g);
        assetManager.PrintText("       Click to\nEnter Your Name", 570, 335, 25, 25, true, g);
    }

}
