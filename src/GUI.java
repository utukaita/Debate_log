import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import static javax.swing.GroupLayout.Alignment.CENTER;

public class GUI extends JFrame {
    private AbstractTableModel generalCompetitiveTableModel;
    private AbstractTableModel generalPracticeTableModel;
    private AbstractTableModel competitiveDebateTableModel;
    private AbstractTableModel tableModel11;
    private AbstractTableModel practiceDebateTableModel;
    private AbstractTableModel tableModel21;
    private AbstractTableModel tableModel22;
    private AbstractTableModel debaterTableModel;
    private AbstractTableModel tableModel;
    private JTable generalCompetitiveTable;
    private JTable generalPracticeTable;
    private JTable competitiveDebateTable;
    private JTable table11;
    private JTable practiceDebateTable;
    private JTable table21;
    private JTable table22;
    private JTable debaterTable;
    private JTable table;
    //Labels
    //General
    private JLabel sykRate;
    private JLabel propRate;
    private JLabel l101;
    private JLabel l102;
    //Debate
    private JLabel l201;
    private JLabel l202;
    //Debate competitive
    private JLabel l11;
    private JLabel l12;
    private JLabel l13;
    private JLabel l14;
    //Debate practice
    private JLabel l21;
    private JLabel l22;
    private JLabel l23;
    private JLabel l24;
    //Debater
    // ---
    //Competitive
    private JLabel l401;
    private JLabel l402;
    private JLabel l403;
    private JLabel l404;
    private JLabel l405;
    private JLabel l406;
    private JLabel l407;
    private JLabel l408;
    //Practice
    private JLabel l501;
    private JLabel l502;
    private JLabel l503;
    private JLabel l504;
    private JLabel l505;
    private JLabel l506;
    private JLabel l507;
    private JLabel l508;
    private JLabel l509;
    private JLabel l510;

    private Application application;
    private JMenuBar menuBar;
    // Buttons
    // Main
    private JButton button11;
    private JButton button12;
    private JButton button13;
    // History
    private JButton button21;
    private JButton button22;
    private JButton button23;
    private JButton button24;
    // General
    private JButton button31;
    // Debate
    private JButton button41;
    private JButton button42;
    private JButton button43;
    private JButton button44;
    private JButton button45;
    // Debater
    private JButton button51;
    private JButton button52;
    private JButton button53;
    // Competitive
    private JButton button61;
    private JButton button62;
    // Practice
    private JButton button71;
    private JButton button72;

    private JComboBox<String> comboBox;
    private JTextField comboBoxTextField;
    private DefaultComboBoxModel<String> comboBoxModel;
    private JFrame f;
    private JFrame f1;
    private JFrame f2;
    private JFrame f3;
    private JFrame f4;
    private JFrame f5;
    private JTextField textField;
    // Check boxes
    // Competitive
    private JCheckBox sykPropCheckBox;
    private JCheckBox sykOppCheckBox;
    private JCheckBox propCheckBox1;
    private JCheckBox oppCheckBox1;
    // Practice
    private JCheckBox propCheckBox2;
    private JCheckBox oppCheckBox2;
    // Text fields
    // Competitive
    private JTextField textField1;
    private JTextField textField2;
    // Practice
    private JTextField textField3;
    // Debater name fields
    // Competitive
    private JTextField textField11;
    private JTextField textField12;
    private JTextField textField13;
    private JTextField textField14;
    // Practice
    private JTextField textField21;
    private JTextField textField22;
    private JTextField textField23;
    private JTextField textField24;
    private JTextField textField25;
    private JTextField textField26;
    private JTextField textField27;
    private JTextField textField28;

    // Variables
    private String motion;
    private String[] names1;
    private String[] names2;
    private boolean propWins;
    private boolean propWinsHasValue;
    private boolean sykProp;
    private boolean sykPropHasValue;
    private String enemy;

    public GUI(Application application) {
        super("Debate Log");
        this.application = application;

        // Frame
        setSize(800, 600);

        // Menu
        menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem quitItem = new JMenuItem("Quit");
        quitItem.addActionListener((ActionEvent e) ->
        {
            int result = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to exit?", null, JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                try (ObjectOutputStream out =
                             new ObjectOutputStream(new FileOutputStream(application.getFname()))){
                    ArrayList<Practice> practices = application.getPractices();
                    out.writeObject(practices);
                    ArrayList<Competitive> competitives = application.getCompetitives();
                    out.writeObject(competitives);
                    ArrayList<Debater> debaters = application.getDebaters();
                    out.writeObject(debaters);
                    System.out.println("hophep");
                } catch (Exception ex) {System.out.println(ex);}
                System.exit(0);
            }
        });
        fileMenu.add(quitItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        // "card stack" layout for having multiple panels and switching between them
        CardLayout cardLayout = new CardLayout();
        setLayout(cardLayout);

        // three panels, one of which will be shown at a time
        final String MAIN_PANEL_NAME = "main";
        // Under main
        final String COMPETITIVE_PANEL_NAME = "competitive";
        final String PRACTICE_PANEL_NAME = "practice";
        final String HISTORY_PANEL_NAME = "history";
        // Under history
        final String GENERAL_PANEL_NAME = "general";
        final String DEBATE_PANEL_NAME = "debate";
        final String DEBATER_PANEL_NAME = "debater";

        JPanel mainPanel = new JPanel();
        add(mainPanel, MAIN_PANEL_NAME);
        // Under main
        JPanel competitivePanel = new JPanel();
        add(competitivePanel, COMPETITIVE_PANEL_NAME);
        JPanel practicePanel = new JPanel();
        add(practicePanel, PRACTICE_PANEL_NAME);
        JPanel historyPanel = new JPanel();
        add(historyPanel, HISTORY_PANEL_NAME);
        // Under history
        JPanel generalPanel = new JPanel();
        add(generalPanel, GENERAL_PANEL_NAME);
        JPanel debatePanel = new JPanel();
        add(debatePanel, DEBATE_PANEL_NAME);
        JPanel debaterPanel = new JPanel();
        add(debaterPanel, DEBATER_PANEL_NAME);

        // MAIN WINDOW

        GroupLayout mainLayout = new GroupLayout (mainPanel);
        mainPanel.setLayout (mainLayout);
        mainLayout.setAutoCreateGaps (true);
        mainLayout.setAutoCreateContainerGaps (true);

        // Competitive button
        button11 = new JButton("New competitive debate");
        button11.addActionListener((ActionEvent e) ->
        {
            cardLayout.show(getContentPane(), COMPETITIVE_PANEL_NAME);
            propWinsHasValue = false;
            motion = null;
            names1 = new String[4];
        });

        // Practice button
        button12 = new JButton("New practice debate");
        button12.addActionListener((ActionEvent e) ->
        {
            cardLayout.show(getContentPane(), PRACTICE_PANEL_NAME);
            propWinsHasValue = false;
            motion = null;
            names1 = new String[4];
            names2 = new String[4];
        });

        // History button
        button13 = new JButton("History");
        button13.addActionListener((ActionEvent e) ->
        {
            cardLayout.show(getContentPane(), HISTORY_PANEL_NAME);
        });

        // Main layout
        mainLayout.setHorizontalGroup(
                mainLayout.createSequentialGroup()
                        .addComponent(button11)
                        .addComponent(button12)
                        .addComponent(button13));
        mainLayout.setVerticalGroup(
                mainLayout.createParallelGroup(CENTER)
                        .addComponent(button11)
                        .addComponent(button12)
                        .addComponent(button13));

        // HISTORY WINDOW

        GroupLayout historyLayout = new GroupLayout (historyPanel);
        historyPanel.setLayout (historyLayout);
        historyLayout.setAutoCreateGaps (true);
        historyLayout.setAutoCreateContainerGaps (true);

        // History back button

        button21 = new JButton("Back");
        button21.addActionListener((ActionEvent e) ->
        {
            cardLayout.show(getContentPane(), MAIN_PANEL_NAME);
        });

        // General button
        button22 = new JButton("General information");
        button22.addActionListener((ActionEvent e) ->
        {
            cardLayout.show(getContentPane(), GENERAL_PANEL_NAME);
            sykRate.setText("Competitive Syk win rate: " + String.format("%.2g%n", application.sykWinRate()));
            propRate.setText("Practice prop win rate: " + String.format("%.2g%n", application.practiceWinningSide()));
            updateGeneralCompetitiveTable();
            updateGeneralPracticeTable();
        });

        // Debate button
        button23 = new JButton("Browse debates");
        button23.addActionListener((ActionEvent e) ->
        {
            cardLayout.show(getContentPane(), DEBATE_PANEL_NAME);
            updateCompetitiveDebateTable();
            updatePracticeDebateTable();
        });

        // Debater button
        button24 = new JButton("Debater statistics");
        button24.addActionListener((ActionEvent e) ->
        {
            cardLayout.show(getContentPane(), DEBATER_PANEL_NAME);
            updateDebaterTable();
        });

        // History layout
        historyLayout.setHorizontalGroup(
                historyLayout.createSequentialGroup()
                        .addComponent(button21)
                        .addComponent(button22)
                        .addComponent(button23)
                        .addComponent(button24));
        historyLayout.setVerticalGroup(
                historyLayout.createParallelGroup()
                        .addComponent(button21)
                        .addComponent(button22)
                        .addComponent(button23)
                        .addComponent(button24));

        // GENERAL WINDOW

        // General back button
        button31 = new JButton("Back");
        button31.addActionListener((ActionEvent e) ->
        {
            cardLayout.show(getContentPane(), HISTORY_PANEL_NAME);
        });
        generalPanel.add(button31);

        // Competitive syk rate text
        sykRate = new JLabel();
        generalPanel.add(sykRate);

        // Practice prop rate text
        propRate = new JLabel();
        generalPanel.add(propRate);

        // Competitive type syk rate text
        l101 = new JLabel("Syk win rate by motion type: ");
        generalPanel.add(l101);

        // Competitive type syk rate table
        generalCompetitiveTableModel = new AbstractTableModel ()
        {
            public int getColumnCount () { return 2; } // two fields in data class
            public int getRowCount () { return application.getCompetitiveMotionTypes().length; } // one row for each data item
            // returns the value that is shown in specific cell
            public Object getValueAt (int row, int column)
            {
                if (column == 0)
                    return application.getCompetitiveMotionTypes()[row];
                else
                    return String.format("%.2g%n",application.getCompetitiveMotionTypeSykRate(application.getCompetitiveMotionTypes())[row]);
            }
        };
        generalCompetitiveTable = new JTable (generalCompetitiveTableModel);
        generalPanel.add(generalCompetitiveTable);

        // Practice type frequency text
        l102 = new JLabel("Practice motion type frequency: ");
        generalPanel.add(l102);

        // Practice type frequency table
        generalPracticeTableModel = new AbstractTableModel ()
        {
            public int getColumnCount () { return 2; } // two fields in data class
            public int getRowCount () { return application.getPracticeMotionTypes().length; } // one row for each data item
            // returns the value that is shown in specific cell
            public Object getValueAt (int row, int column)
            {
                if (column == 0)
                    return application.getPracticeMotionTypes()[row];
                else
                    return String.format("%.2g%n",application.getPracticeMotionTypeFrequency(application.getPracticeMotionTypes())[row]);
            }
        };

        generalPracticeTable = new JTable (generalPracticeTableModel);
        generalPanel.add(generalPracticeTable);

        // General layout
        GroupLayout generalLayout = new GroupLayout (generalPanel);
        generalPanel.setLayout (generalLayout);
        generalLayout.setAutoCreateGaps (true);
        generalLayout.setAutoCreateContainerGaps (true);
        generalLayout.setHorizontalGroup(
                generalLayout.createSequentialGroup()
                        .addComponent(button21)
                        .addComponent(button22)
                        .addComponent(button23)
                        .addComponent(button24));
        generalLayout.setVerticalGroup(
                generalLayout.createBaselineGroup(true, true)
                        .addComponent(button21)
                        .addComponent(button22)
                        .addComponent(button23)
                        .addComponent(button24));

        // DEBATE WINDOW

        // Debate back button
        button41 = new JButton("Back");
        button41.addActionListener((ActionEvent e) ->
        {
            cardLayout.show(getContentPane(), HISTORY_PANEL_NAME);
        });
        debatePanel.add(button41);

        // Competitive debate table text
        l201 = new JLabel("Competitive");
        debatePanel.add(l201);

        // Competitive debate table
        competitiveDebateTableModel = new AbstractTableModel ()
        {
            public int getColumnCount () { return 2; } // two fields in data class
            public int getRowCount () { return application.getCompetitives().size(); } // one row for each data item
            // returns the value that is shown in specific cell
            public Object getValueAt (int row, int column)
            {
                if (column == 0)
                    return application.getCompetitives().get(row).getMotion();
                else
                    return application.getCompetitives().get(row).getDate();
            }
        };
        competitiveDebateTable = new JTable (competitiveDebateTableModel);
        competitiveDebateTable.getColumnModel().getColumn(0).setPreferredWidth(400);
        competitiveDebateTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        debatePanel.add(competitiveDebateTable);

        // Competitive debate button

        button42 = new JButton(("More info"));
        button42.addActionListener((ActionEvent e) ->
        {
            int selected = competitiveDebateTable.getSelectedRow (); // returns -1 if none selected
            if (selected >= 0) {

                // Title
                f1 = new JFrame(application.getCompetitives().get(selected).getMotion());
                f1.setSize(800, 400);
                f1.setLayout(new FlowLayout());

                // Syk side text
                if(application.getCompetitives().get(selected).getSykProp())
                    l11 = new JLabel("Syk is proposition.");
                else
                    l11 = new JLabel("Syk is opposition.");
                f1.add(l11);

                // Debaters
                tableModel11 = new AbstractTableModel ()
                {
                    public int getColumnCount () { return 2; } // two fields in data class
                    public int getRowCount () { return 4; } // one row for each data item
                    // returns the value that is shown in specific cell
                    public Object getValueAt (int row, int column)
                    {
                        if (column == 0)
                            return "Speech " + (row+1);
                        else
                            return application.getCompetitives().get(selected).getTeam1()[row].getName();
                    }
                };
                table11 = new JTable (tableModel11);
                f1.add(table11);

                // Winner text
                if(application.getCompetitives().get(selected).getSykProp()==application.getCompetitives().get(selected).getPropWins())
                    l13 = new JLabel("Syk wins");
                else
                    l13 = new JLabel("Syk loses");
                f1.add(l13);

                // Enemy text
                l12 = new JLabel("against " + application.getCompetitives().get(selected).getEnemy() + ".");
                f1.add(l12);

                // Date
                l14 = new JLabel(application.getCompetitives().get(selected).getDate());
                f1.add(l14);

                f1.show();
            }
        });
        debatePanel.add(button42);

        // Competitive delete button

        button43 = new JButton("Delete");
        button43.addActionListener((ActionEvent e) ->
                {
                    int selected = competitiveDebateTable.getSelectedRow();
                    if (selected >= 0){
                        application.deleteCompetitive(selected);
                        f = new JFrame("Alert");
                        f.add(new JLabel("Debate deleted."));
                        f.setSize(300, 300);
                        f.setVisible(true);
                    }
                }
        );
        debatePanel.add(button43);

        // Practice debate table text
        l202 = new JLabel("Practice");
        debatePanel.add(l202);

        // Practice debate table
        practiceDebateTableModel = new AbstractTableModel ()
        {
            public int getColumnCount () { return 2; } // two fields in data class
            public int getRowCount () { return application.getPractices().size(); } // one row for each data item
            // returns the value that is shown in specific cell
            public Object getValueAt (int row, int column)
            {
                if (column == 0)
                    return application.getPractices().get(row).getMotion();
                else
                    return application.getPractices().get(row).getDate();
            }
        };
        practiceDebateTable = new JTable (practiceDebateTableModel);
        practiceDebateTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        practiceDebateTable.getColumnModel().getColumn(0).setPreferredWidth(400);
        practiceDebateTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        debatePanel.add(practiceDebateTable);

        // Practice debate button

        button44 = new JButton(("More info"));
        button44.addActionListener((ActionEvent e) ->
        {
            int selected = practiceDebateTable.getSelectedRow (); // returns -1 if none selected
            if (selected >= 0) {

                // Title
                f2 = new JFrame(application.getPractices().get(selected).getMotion());
                f2.setSize(800, 400);
                f2.setLayout(new FlowLayout());

                // Proposition text
                l21 = new JLabel("Proposition");
                f2.add(l21);

                // Proposition table
                tableModel21 = new AbstractTableModel ()
                {
                    public int getColumnCount () { return 2; } // two fields in data class
                    public int getRowCount () { return 4; } // one row for each data item
                    // returns the value that is shown in specific cell
                    public Object getValueAt (int row, int column)
                    {
                        if (column == 0)
                            return "Speech " + (row+1);
                        else
                            return application.getPractices().get(selected).getTeam1()[row].getName();
                    }
                };
                table21 = new JTable (tableModel21);
                f2.add(table21);

                // Opposition text
                l22 = new JLabel("Opposition");
                f2.add(l22);

                // Opposition table
                tableModel22 = new AbstractTableModel ()
                {
                    public int getColumnCount () { return 2; } // two fields in data class
                    public int getRowCount () { return 4; } // one row for each data item
                    // returns the value that is shown in specific cell
                    public Object getValueAt (int row, int column)
                    {
                        if (column == 0)
                            return "Speech " + (row+1);
                        else
                            return application.getPractices().get(selected).getTeam2()[row].getName();
                    }
                };
                table22 = new JTable (tableModel22);
                f2.add(table22);

                // Winner text
                if (application.getPractices().get(selected).getPropWins())
                    l23 = new JLabel ("Proposition wins!");
                else
                    l23 = new JLabel ("Opposition wins!");
                f2.add(l23);

                // Date text
                l24 = new JLabel("Date: " + application.getPractices().get(selected).getDate());
                f2.add(l24);
                f2.setVisible(true);
            }
        });
        debatePanel.add(button44);

        // Practice delete button

        button45 = new JButton("Delete");
        button45.addActionListener((ActionEvent e) ->
                {
                    int selected = practiceDebateTable.getSelectedRow();
                    if (selected >= 0){
                        application.deletePractice(selected);
                        f = new JFrame("Alert");
                        f.add(new JLabel("Debate deleted."));
                        f.setSize(300, 300);
                        f.setVisible(true);
                    }
                }
        );
        debatePanel.add(button45);

        // DEBATER WINDOW

        // Debater back button
        button51 = new JButton("Back");
        button51.addActionListener((ActionEvent e) ->
        {
            cardLayout.show(getContentPane(), HISTORY_PANEL_NAME);
        });
        debaterPanel.add(button51);

        // Debater table
        debaterTableModel = new AbstractTableModel ()
        {
            public int getColumnCount () { return 2; } // two fields in data class
            public int getRowCount () { return application.getDebaters().size(); } // one row for each data item
            // returns the value that is shown in specific cell
            public Object getValueAt (int row, int column)
            {
                if (column == 0)
                    return application.getDebaters().get(row).getName();
                else
                    return String.format("%.2g%n", application.getDebaters().get(row).winRate());
            }
        };
        debaterTable = new JTable (debaterTableModel);
        debaterPanel.add(debaterTable);

        // Debater button

        button52 = new JButton(("More info"));
        button52.addActionListener((ActionEvent e) ->
        {
            int selected = debaterTable.getSelectedRow (); // returns -1 if none selected
            if (selected >= 0) {
                f3 = new JFrame(application.getDebaters().get(selected).getName());
                tableModel = new AbstractTableModel ()
                {
                    public int getColumnCount () { return 2; } // two fields in data class
                    public int getRowCount () { return 4; } // one row for each data item
                    // returns the value that is shown in specific cell
                    public Object getValueAt (int row, int column)
                    {
                        if (column == 0)
                            return "Speech " + (row+1);
                        else
                            return String.format("%.2g%n", application.getDebaters().get(selected).winRate(row));
                    }
                };
                table = new JTable (tableModel);
                f3.add(table);
                f3.setSize(300, 300);
                f3.setVisible(true);
            }
        });
        debaterPanel.add(button52);

        // Debater delete button

        button53 = new JButton("Delete");
        button53.addActionListener((ActionEvent e) ->
                {
                    int selected = debaterTable.getSelectedRow();
                    if (selected >= 0 && application.getDebaters().get(selected).getDebates()==0){
                        application.deleteDebater(selected);
                        f = new JFrame("Alert");
                        f.add(new JLabel("Debater deleted."));
                        f.setSize(300, 300);
                        f.setVisible(true);
                    }
                }
        );
        debaterPanel.add(button53);


        // COMPETITIVE WINDOW

        // Competitive back button

        button61 = new JButton("Back");
        button61.addActionListener((ActionEvent e) ->
        {
            cardLayout.show(getContentPane(), MAIN_PANEL_NAME);
        });
        competitivePanel.add(button61);

        // Competitive text
        l401 = new JLabel("Competitive motion:");
        competitivePanel.add(l401);

        // Text field
        textField1 = new JTextField(30);
        textField1.addActionListener((ActionEvent e) ->
                updateMotion(textField1.getText()));
        competitivePanel.add(textField1);

        // Competitive motion text
        l402 = new JLabel("Enemy:");
        competitivePanel.add(l402);

        // Text field
        textField2 = new JTextField(10);
        textField2.addActionListener((ActionEvent e) ->
                updateEnemy(textField2.getText()));
        competitivePanel.add(textField2);

        // Competitive syk text
        l403 = new JLabel("Syk side:");
        competitivePanel.add(l403);

        // Competitive syk prop check box
        sykPropCheckBox = new JCheckBox("Proposition", false);
        sykPropCheckBox.addItemListener(
                (ItemEvent e) ->
                {
                    if (e.getStateChange() == 1) {
                        updateSide(true);
                        sykPropHasValue = true;
                    } else {
                        updateSide(false);
                        sykPropHasValue = false;
                    }
                });
        competitivePanel.add(sykPropCheckBox);

        // Competitive opp check box
        sykOppCheckBox = new JCheckBox("Opposition", false);
        sykOppCheckBox.addItemListener(
                (ItemEvent e) ->
                {
                    if (e.getStateChange() == 1) {
                        updateSide(false);
                        sykPropHasValue = true;
                    } else {
                        updateSide(true);
                        sykPropHasValue = false;
                    }
                });
        competitivePanel.add(sykOppCheckBox);

        // Competitive winner text
        l404 = new JLabel("Winner:");
        competitivePanel.add(l404);

        // Competitive prop check box
        propCheckBox1 = new JCheckBox("Proposition", false);
        propCheckBox1.addItemListener(
                (ItemEvent e) ->
                {
                    if (e.getStateChange() == 1) {
                        updateWinner(true, true);
                        propWinsHasValue = true;
                    } else {
                        updateWinner(false, true);
                        propWinsHasValue = false;
                    }
                });
        competitivePanel.add(propCheckBox1);

        // Competitive opp check box
        oppCheckBox1 = new JCheckBox("Opposition", false);
        oppCheckBox1.addItemListener(
                (ItemEvent e) ->
                {
                    if (e.getStateChange() == 1) {
                        updateWinner(false, true);
                        propWinsHasValue = true;
                    } else {
                        updateWinner(true, true);
                        propWinsHasValue = false;
                    }
                });
        competitivePanel.add(oppCheckBox1);

        // Competitive prop table

        l405 = new JLabel("Speech 1");
        competitivePanel.add(l405);
        textField11 = new JTextField(10);
        textField11.addActionListener((ActionEvent e) ->
                updateSpeaker(textField11.getText(), true, 1));
        competitivePanel.add(textField11);
        l406 = new JLabel("Speech 2");
        competitivePanel.add(l406);
        textField12 = new JTextField(10);
        textField12.addActionListener((ActionEvent e) ->
                updateSpeaker(textField12.getText(), true, 2));
        competitivePanel.add(textField12);
        l407 = new JLabel("Speech 3");
        competitivePanel.add(l407);
        textField13 = new JTextField(10);
        textField13.addActionListener((ActionEvent e) ->
                updateSpeaker(textField13.getText(), true, 3));
        competitivePanel.add(textField13);
        l408 = new JLabel("Speech 4");
        competitivePanel.add(l408);
        textField14 = new JTextField(10);
        textField14.addActionListener((ActionEvent e) ->
                updateSpeaker(textField14.getText(), true, 4));
        competitivePanel.add(textField14);

        // Competitive add button
        button62 = new JButton(("Add"));
        button62.addActionListener((ActionEvent e) ->
        {
            String message = addNewCompetitiveDebate();
            f4 = new JFrame("Alert");
            f4.add(new JLabel(message));
            f4.setSize(300, 300);
            f4.setVisible(true);
        });
        competitivePanel.add(button62);

        // PRACTICE WINDOW

        // Practice back button
        button71 = new JButton("Back");
        button71.addActionListener((ActionEvent e) ->
        {
            cardLayout.show(getContentPane(), MAIN_PANEL_NAME);
        });
        practicePanel.add(button71);

        // Practice text
        l501 = new JLabel("Practice motion:");
        practicePanel.add(l501);

        // Text field
        textField3 = new JTextField(30);
        textField3.addActionListener((ActionEvent e) ->
                updateMotion(textField3.getText()));
        practicePanel.add(textField3);

        // Practice winner text
        l502 = new JLabel("Winner:");
        practicePanel.add(l502);

        // Prop check box
        propCheckBox2 = new JCheckBox("Proposition", false);
        propCheckBox2.addItemListener(
                (ItemEvent e) ->
                {
                    if (e.getStateChange() == 1) {
                        updateWinner(true, false);
                        propWinsHasValue = true;
                    } else {
                        updateWinner(false, false);
                        propWinsHasValue = false;
                    }
                });
        practicePanel.add(propCheckBox2);

        // Opp check box
        oppCheckBox2 = new JCheckBox("Opposition", false);
        oppCheckBox2.addItemListener(
                (ItemEvent e) ->
                {
                    if (e.getStateChange() == 1) {
                        updateWinner(false, false);
                        propWinsHasValue = true;
                    } else {
                        updateWinner(true, false);
                        propWinsHasValue = false;
                    }
                });
        practicePanel.add(oppCheckBox2);

        // Practice prop table

        l503 = new JLabel("Speech 1");
        practicePanel.add(l503);
        textField21 = new JTextField(10);
        textField21.addActionListener((ActionEvent e) ->
                updateSpeaker(textField21.getText(), true, 1));
        practicePanel.add(textField21);
        l504 = new JLabel("Speech 2");
        practicePanel.add(l504);
        textField22 = new JTextField(10);
        textField22.addActionListener((ActionEvent e) ->
                updateSpeaker(textField22.getText(), true, 2));
        practicePanel.add(textField22);
        l505 = new JLabel("Speech 3");
        practicePanel.add(l505);
        textField23 = new JTextField(10);
        textField23.addActionListener((ActionEvent e) ->
                updateSpeaker(textField23.getText(), true, 3));
        practicePanel.add(textField23);
        l506 = new JLabel("Speech 4");
        practicePanel.add(l506);
        textField24 = new JTextField(10);
        textField24.addActionListener((ActionEvent e) ->
                updateSpeaker(textField24.getText(), true, 4));
        practicePanel.add(textField24);

        // Practice opp table

        l507 = new JLabel("Speech 1");
        practicePanel.add(l507);
        textField25 = new JTextField(10);
        textField25.addActionListener((ActionEvent e) ->
                updateSpeaker(textField25.getText(), false, 1));
        practicePanel.add(textField25);
        l508 = new JLabel("Speech 2");
        practicePanel.add(l508);
        textField26 = new JTextField(10);
        textField26.addActionListener((ActionEvent e) ->
                updateSpeaker(textField26.getText(), false, 2));
        practicePanel.add(textField26);
        l509 = new JLabel("Speech 3");
        practicePanel.add(l509);
        textField27 = new JTextField(10);
        textField27.addActionListener((ActionEvent e) ->
                updateSpeaker(textField27.getText(), false, 3));
        practicePanel.add(textField27);
        l510 = new JLabel("Speech 4");
        practicePanel.add(l510);
        textField28 = new JTextField(10);
        textField28.addActionListener((ActionEvent e) ->
                updateSpeaker(textField28.getText(), false, 4));
        practicePanel.add(textField28);

        // Practice add button
        button72 = new JButton(("Add"));
        button72.addActionListener((ActionEvent e) ->
        {
            String message = addNewPracticeDebate();
            f5 = new JFrame("Alert");
            f5.add(new JLabel(message));
            f5.setSize(300, 300);
            f5.setVisible(true);
        });
        practicePanel.add(button72);


        // Visibility
        setVisible(true);
    }

    // Methods

    public void updateGeneralCompetitiveTable(){
        generalCompetitiveTableModel.fireTableRowsInserted(0, application.getCompetitiveMotionTypes().length);
        generalCompetitiveTableModel.fireTableDataChanged();
    }

    public void updateGeneralPracticeTable(){
        generalPracticeTableModel.fireTableRowsInserted(0, application.getPracticeMotionTypes().length);
        generalPracticeTableModel.fireTableDataChanged();
    }

    public void updateCompetitiveDebateTable(){
        competitiveDebateTableModel.fireTableRowsInserted(0, application.getCompetitives().size());
        competitiveDebateTableModel.fireTableDataChanged();
    }

    public void updatePracticeDebateTable(){
        practiceDebateTableModel.fireTableRowsInserted(0, application.getPractices().size());
        practiceDebateTableModel.fireTableDataChanged();
    }

    public void updateDebaterTable(){
        debaterTableModel.fireTableRowsInserted(0, application.getDebaters().size());
        debaterTableModel.fireTableDataChanged();
    }

    public void updateMotion(String text) {
        motion = text;
    }

    public void updateEnemy(String text) {
        enemy = text;
    }

    public void updateSide(boolean direction) {
        sykProp = direction;
        if (direction)
            sykOppCheckBox.setSelected(false);
        else
            sykPropCheckBox.setSelected(false);
    }

    public void updateWinner(boolean direction, boolean isCompetitive) {
        propWins = direction;
        if (isCompetitive) {
            if (direction)
                oppCheckBox1.setSelected(false);
            else
                propCheckBox1.setSelected(false);
        } else {
            if (direction)
                oppCheckBox2.setSelected(false);
            else
                propCheckBox2.setSelected(false);
        }
    }

    public void updateSpeaker(String name, boolean side, int role) {
        if (side)
            names1[role - 1] = name;
        else
            names2[role - 1] = name;
    }

    public String addNewCompetitiveDebate() {
        if (motion == null) return "Motion missing.";
        String[] words = motion.split(" ");
        if (words.length < 3) return "Motion too short.";
        if (!words[0].equals("This") || !words[1].equals("house")) return "Motion must begin by 'This house'.";
        if (enemy == null) return "Enemy team missing.";
        if (!sykPropHasValue) return "Indicate which side SYK was on.";
        if (!propWinsHasValue) return "Indicate the winning side.";
        for (int i = 0; i < 4; i++) {
            if (names1[i] == null) return "A debater name missing.";
            if (names1[i].equals("")) return "A debater name missing.";
        }
        application.addCompetitive(motion, enemy, sykProp, propWins, names1);
        return "New competitive debate added.";
    }

    public String addNewPracticeDebate() {
        if (motion == null) return "Motion missing.";
        String[] words = motion.split(" ");
        if (words.length < 3) return "Motion too short.";
        if (!words[0].equals("This") || !words[1].equals("house")) return "Every motion must begin by 'This house'.";
        if (!propWinsHasValue) return "Indicate the winning side.";
        for (int i = 0; i < 4; i++) {
            if (names1[i] == null || names2[i] == null) return "A debater name missing.";
            if (names1[i].equals("") || names2[i].equals("")) return "A debater name missing.";
        }
        application.addPractice(motion, propWins, names1, names2);
        return "New practice debate added.";
    }
}