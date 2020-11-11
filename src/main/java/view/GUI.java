package view;

import controller.AvatorCreator;
import controller.GameManager;
import controller.MapCreator;
import model.Database;
import model.avators.avator;
import model.avators.heros.Hero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.List;

import static model.helpers.Universal.*;

public class GUI extends JFrame {

    //Grid Layout
    private GridLayout grid = new GridLayout();

    //Hero Creation And Selection
    private JComboBox<String> listCreate = new JComboBox<>();
    private JComboBox<String> listSelect = new JComboBox<>();

    private JLabel labelCreate = new JLabel("Create Hero");
    private JLabel labelSelect = new JLabel("Select Hero");
    private JLabel labelStats = new JLabel("Statistics");
    private JLabel labelInput = new JLabel("Name");
    private JLabel labelAction = new JLabel("Action");
    private JLabel picLabel = new JLabel();

    private JPanel panelCreate = new JPanel();
    private JPanel panelSelect = new JPanel();

    private JButton buttonCreate = new JButton("Create New Hero");
    private JButton buttonSelect = new JButton("Select Existing Hero");
    private JButton buttonCreate2 = new JButton("Create");
    private JButton buttonDelete = new JButton("Delete");

    private JButton buttonCancel = new JButton("Cancel");
    private JButton buttonCancelMain = new JButton("Cancel");


    //Interactions
    private JPanel panelEncounter = new JPanel();
    private JPanel panelEncounter2 = new JPanel();

    private JButton buttonStart = new JButton("Start");

    //Generalized Panels
    private JPanel panelMain = new JPanel();
    private JPanel panelMenu = new JPanel();
    private JPanel panelMap = new JPanel();
    private JPanel panelGrid = new JPanel();
    private JPanel panelStats = new JPanel();
    private JPanel panelInput = new JPanel();

    //Text Fields
    private JTextField input = new JTextField();


    //Generalized Button Options
    private JRadioButton rFlee = new JRadioButton("Flee");
    private JRadioButton rBattle = new JRadioButton("Battle");

    private JButton buttonChange = new JButton("Console View");
    private JButton buttonOkEncounter = new JButton("OK");
    private ButtonGroup buttonAllEncounter = new ButtonGroup();

    //Scrolling Capabilities
    private JScrollPane scrollPane;

    public GUI() {
        setTitle("Swingy");
        setSize(1300, 950);
        setResizable(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        panelMain.setLayout(new BorderLayout());
        //initialise components
        initialise(this);
        System.out.println("Welcome to swingy!!!!!!!");
        begin(this);
        this.setVisible(true);
    }

    public void begin(GUI window){
        panelMenu.add(buttonCreate);
        panelMenu.add(buttonSelect);
        panelMenu.add(buttonChange);

        panelMenu.add(panelStats);
        panelMenu.add(panelCreate);
        panelMenu.add(panelSelect);
        panelMenu.add(panelEncounter);

        panelMenu.add(scrollPane);
        panelMenu.add(picLabel);
        panelMenu.add(panelGrid);
        panelMenu.add(buttonCancelMain);

        panelMain.add(panelMenu, BorderLayout.EAST);
        panelMain.add(panelMenu, BorderLayout.WEST);
        panelMain.add(panelMenu, BorderLayout.CENTER);

        scrollPane.setVisible(false);

        window.setContentPane(panelMain);
    }


    private void initialise(GUI window) {
        logTextArea = new JTextArea("", 40, 25);
        logTextArea.setEditable(false);
        scrollPane = new JScrollPane(logTextArea);

        ((FlowLayout)panelMap.getLayout()).setVgap(0);

        //Reading Image Files::  BufferedImage img = ImageIO.read(url);
        BufferedImage mainImage = controller.ImageException.imageUpload("swingy-main/src/main/java/view/images/game.jpg");
        //Images To Be Used
        Image scaledImage = mainImage.getScaledInstance(window.getWidth() / 2, window.getHeight(), Image.SCALE_DEFAULT);
        picLabel = new JLabel(new ImageIcon(scaledImage));

        //Panel Initialisations
        panelMenu.setPreferredSize(new Dimension(window.getWidth() / 4, window.getHeight()));
        panelMenu.setBackground(Color.gray
        );
        panelMap.setPreferredSize(new Dimension(window.getWidth() / 2, window.getHeight()));
        panelMap.setBackground(Color.gray);
        panelGrid.setPreferredSize(new Dimension(window.getWidth() / 2, window.getHeight()));
        panelGrid.setBackground(Color.gray);

        listCreate.addItem("WonderWoman");
        listCreate.addItem("StarGirl");
        listCreate.addItem("BlackWidow");
        listCreate.addActionListener(new listListener());
        listCreate.setPreferredSize(new Dimension(180, 50));
        listSelect.setPreferredSize(new Dimension(180, 50));

        //Button Initialisations
        buttonCreate.setPreferredSize(new Dimension(180, 90));
        buttonCreate.addActionListener(new createListener());
        buttonSelect.setPreferredSize(new Dimension(180, 90));
        buttonSelect.addActionListener(new selectListener());
        buttonChange.setPreferredSize(new Dimension(180, 90));
        buttonChange.addActionListener(new changeListener());

        buttonCreate2.setPreferredSize(new Dimension(180, 30));
        buttonCreate2.addActionListener(new heroCreateListener());
        buttonCreate2.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonCreate2.setMaximumSize(getSize());

        buttonOkEncounter.setPreferredSize(new Dimension(180, 30));
        buttonOkEncounter.addActionListener(new heroEncounterListener());
        buttonOkEncounter.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonOkEncounter.setMaximumSize(getSize());

        buttonStart.setPreferredSize(new Dimension(180, 30));
        buttonStart.addActionListener(new startListener());
        buttonStart.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonStart.setMaximumSize(getSize());

        buttonDelete.setPreferredSize(new Dimension(180, 30));
        buttonDelete.addActionListener(new deleteListener());
        buttonDelete.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonDelete.setMaximumSize(getSize());
        buttonDelete.setForeground(Color.red);

        buttonCancel.setPreferredSize(new Dimension(180, 30));
        buttonCancel.addActionListener(new cancelListener());
        buttonCancel.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonCancel.setMaximumSize(getSize());

        buttonCancelMain.setPreferredSize(new Dimension(180, 30));
        buttonCancelMain.addActionListener(new cancelMainListener());
        buttonCancelMain.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonCancel.setMaximumSize(getSize());

        labelCreate.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelSelect.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelAction.setAlignmentX(Component.CENTER_ALIGNMENT);
        input.setPreferredSize(new Dimension(160, 55));

        Box box = Box.createVerticalBox();
        panelStats.setPreferredSize(new Dimension(250, 150));
        labelStats.setAlignmentX(Component.CENTER_ALIGNMENT);
        box.add(labelStats);
        panelStats.add(box);
        panelStats.setVisible(false);
        panelInput.add(labelInput);
        panelInput.add(input);

        panelCreate.setPreferredSize(new Dimension(300, 200));
        panelCreate.setLayout(new BoxLayout(panelCreate, BoxLayout.Y_AXIS));
        panelCreate.add(labelCreate);
        panelCreate.add(listCreate);
        panelCreate.add(panelInput);
        panelCreate.add(buttonCreate2);
        panelCreate.setVisible(false);
        listCreate.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelSelect.setPreferredSize(new Dimension(300, 200));
        panelSelect.setLayout(new BoxLayout(panelSelect, BoxLayout.Y_AXIS));
        panelSelect.add(labelSelect);
        panelSelect.add(listSelect);
        panelSelect.add(buttonStart);
        panelSelect.add(buttonDelete);
        panelSelect.setVisible(false);
        listSelect.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelEncounter.setPreferredSize(new Dimension(300, 100));
        panelEncounter.setLayout(new BoxLayout(panelEncounter, BoxLayout.Y_AXIS));
        panelEncounter2.setLayout(new BoxLayout(panelEncounter2, BoxLayout.X_AXIS));
        buttonAllEncounter.add(rBattle);
        buttonAllEncounter.add(rFlee);
        rBattle.setSelected(true);
        panelEncounter2.add(rBattle);
        panelEncounter2.add(new Box.Filler(new Dimension(50, 0), new Dimension(50, 0), new Dimension(50, 0)));
        panelEncounter2.add(rFlee);
        panelEncounter.add(labelAction);
        panelEncounter.add(new Box.Filler(new Dimension(0, 10), new Dimension(0, 10), new Dimension(0, 10)));
        panelEncounter.add(panelEncounter2);
        panelEncounter.add(new Box.Filler(new Dimension(0, 10), new Dimension(0, 10), new Dimension(0, 10)));
        panelEncounter.add(buttonOkEncounter);

        panelEncounter.setVisible(false);
        panelGrid.setLayout(grid);
        panelGrid.setVisible(false);
        buttonCancelMain.setVisible(false);
    }


    //Action Listeners To Be Put Into their Own Class Within The Controller

    private class listListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int v =listCreate.getSelectedIndex();
          //  Display.displayHero();
        }
    }

    private class createListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            panelSelect.remove(buttonCancel);
            panelCreate.add(buttonCancel);
            buttonCreate.setVisible(false);
            buttonSelect.setVisible(false);
            buttonChange.setVisible(false);
            panelCreate.setVisible(true);

//            Display.displayHero();
        }
    }


    private class selectListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Database.getInstance().printDatabase();
            if (!bIsHero)
                logTextArea.setText("Heroes Don't Exist");
            else {
                panelCreate.remove(buttonCancel);
                panelSelect.add(buttonCancel);
                buttonCreate.setVisible(false);
                buttonSelect.setVisible(false);
                buttonChange.setVisible(false);
                listSelect.removeAllItems();
                List<Hero> heroList = Database.getInstance().extractDatabase();
                for (Hero a : heroList) {
                    listSelect.addItem(a.getName());
                }
                panelSelect.setVisible(true);
                bIsHero = false;
            }
        }
    }

    private class changeListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            GUI.this.dispose();
            console.begin();
        }
    }

    private class heroCreateListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int selection = listCreate.getSelectedIndex();
            avator avator = null;
            if (selection == 0)
                avator = AvatorCreator.newHero(input.getText(), "WonderWoman");
            else if (selection == 1)
                avator = AvatorCreator.newHero(input.getText(), "StarGirl");
            else if (selection == 2)
                avator = AvatorCreator.newHero(input.getText(), "BlackWidow");

            if (avator != null) {
                Database.getInstance().insertHero((Hero) avator);
                panelCreate.setVisible(false);
                buttonCreate.setVisible(true);
                buttonSelect.setVisible(true);
                buttonChange.setVisible(true);
            }
        }
    }



    //Map Controller
    private void map() {
        int v = 0;
        while (v < map.getMapSize()) {
            int t = 0;
            while (t < map.getMapSize()) {
                final int x = t;
                final int y = v;
                int position = map.getMap()[t][v];

                final JPanel block = new JPanel();
                ((FlowLayout)block.getLayout()).setHgap(0);
                ((FlowLayout)block.getLayout()).setVgap(0);
                block.setBorder(BorderFactory.createLineBorder(Color.darkGray));
                if (position == 1)
                    block.setBackground(new Color(70, 196, 222));
                else if (position == 2)
                    block.setBackground(new Color(72, 61, 139));
                else if (position == 8)
                    block.setBackground(new Color(101, 7, 64));
                else
                    block.setBackground(new Color(230, 230, 250));
                panelGrid.add(block);

                block.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if ((x + 1) < map.getMapSize() && map.getMap()[x + 1][y] == 1)
                            GameManager.move(1);
                        else if ((y - 1) >= 0 && map.getMap()[x][y - 1] == 1)
                            GameManager.move(2);
                        else if ((x - 1) <= 0 && map.getMap()[x - 1][y] == 1)
                            GameManager.move(3);
                        else if ((y + 1) < map.getMapSize() && map.getMap()[x][y + 1] == 1)
                            GameManager.move(4);

                        if (bEncounterPhase) {
                            panelEncounter.setVisible(true);
                            bEncounterPhase = false;
                        } else
                            GameManager.win();

                        panelGrid.removeAll();
                        grid.setRows(map.getMapSize());
                        grid.setColumns(map.getMapSize());
                        grid.setHgap(-1);
                        grid.setVgap(-1);
                        panelGrid.setLayout(grid);
                        map();
                        panelGrid.revalidate();
                        panelGrid.repaint();
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                    }
                });

                t++;
            }
            v++;
        }
    }


    private class startListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            panelSelect.setVisible(false);
            scrollPane.setVisible(true);
            picLabel.setVisible(false);
            hero = Database.getInstance().HeroDetails(listSelect.getSelectedItem().toString());
            map = MapCreator.generateMap(hero);
            System.out.println(hero.getName() + " is here!!!");
            grid.setRows(map.getMapSize());
            grid.setColumns(map.getMapSize());
            grid.setHgap(-1);
            grid.setVgap(-1);
            panelGrid.setVisible(true);
            picLabel.setVisible(false);
            labelStats.setText("<html> "+ "Name:: " + hero.getName() + "</br>" +
                    "Type" + hero.getType() + "</br>" +
                    "Level" + hero.getLevel() + "</br>" +
                    "XP" + hero.getX() + "</br>" +
                    "Attack" + hero.getAttack() + "</br>" +
                    "Defense" + hero.getLf() + "</br>" +
                    "HP" + hero.getLf() + " </html>");
            panelStats.setVisible(true);
            buttonCancelMain.setVisible(true);
            //Map Functionality
            map();
        }
    }


    private class heroEncounterListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            buttonCancelMain.setVisible(false);
            if (rBattle.isSelected())
                GameManager.battle(2);
            else if (rFlee.isSelected())
                GameManager.flee();
            GameManager.win();

            panelGrid.removeAll();
            grid.setRows(map.getMapSize());
            grid.setColumns(map.getMapSize());
            grid.setHgap(-1);
            grid.setVgap(-1);
            panelGrid.setLayout(grid);
            map();
            panelGrid.revalidate();
            panelGrid.repaint();

            buttonCancelMain.setVisible(true);
            labelStats.setText("<html>Name: " + hero.getName() + "<br>" +
                    "Type: " + hero.getType() + "<br>" +
                    "Level: " + hero.getLevel() + "<br>" +
                    "Experience: " + hero.getXp() + "<br>" +
                    "Attack: " + hero.getAttack() + "<br>" +
                    "Defense: " + hero.getDefense() + "<br>" +
                    "Health: " + hero.getLf() + "<br>" + "</html>");
            panelEncounter.setVisible(false);
        }
    }

    private class deleteListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int option = JOptionPane.YES_OPTION;
            int selection = JOptionPane.showConfirmDialog(GUI.this, "Are you sure?!?!", "Death", option);

            if (selection == JOptionPane.YES_OPTION) {
                Database.getInstance().deleteHero(listCreate.getSelectedItem().toString());
                Database.getInstance().printDatabase();
                if (nbHero == 0) {
                    panelSelect.setVisible(false);
                    buttonCreate.setVisible(true);
                    buttonSelect.setVisible(true);
                    buttonChange.setVisible(true);
                    bIsHero = false;
                } else {
                    listSelect.removeAllItems();
                    List<Hero> heroList = Database.getInstance().extractDatabase();
                    for (Hero a : heroList) {
                        listSelect.addItem(a.getName());
                    }
                }
            }
        }
    }

    private class cancelListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            panelEncounter.setVisible(false);
            panelCreate.setVisible(false);
            panelSelect.setVisible(false);
            buttonCreate.setVisible(true);
            buttonSelect.setVisible(true);
            buttonChange.setVisible(true);
        }
    }

    private class cancelMainListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            panelStats.setVisible(false);
            panelEncounter.setVisible(false);
            panelGrid.removeAll();
            panelGrid.setVisible(false);
            picLabel.setVisible(true);

            buttonCancelMain.setVisible(false);
            buttonCancelMain.setVisible(true);
            buttonSelect.setVisible(true);
            buttonChange.setVisible(true);
        }
    }

}
