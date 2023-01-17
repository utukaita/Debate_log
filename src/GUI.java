import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;

import static javax.swing.GroupLayout.Alignment.CENTER;
import static javax.swing.GroupLayout.Alignment.LEADING;

public class GUI extends JFrame {

    // Table models
    private AbstractTableModel generalCompetitiveTableModel;
    private AbstractTableModel generalPracticeTableModel;
    private AbstractTableModel competitiveDebateTableModel;
    private AbstractTableModel tableModel11;
    private AbstractTableModel practiceDebateTableModel;
    private AbstractTableModel tableModel21;
    private AbstractTableModel tableModel22;
    private AbstractTableModel debaterTableModel;
    private AbstractTableModel tableModel31;
    private AbstractTableModel tableModel32;
    private AbstractTableModel tableModel33;

    // Tables
    private JTable generalCompetitiveTable;
    private JTable generalPracticeTable;
    private JTable competitiveDebateTable;
    private JTable table11;
    private JTable practiceDebateTable;
    private JTable table21;
    private JTable table22;
    private JTable debaterTable;
    private JTable table31;
    private JTable table32;
    private JTable table33;

    // Scroll panes
    private JScrollPane generalCompetitiveScrollPane;
    private JScrollPane generalPracticeScrollPane;
    private JScrollPane competitiveDebateScrollPane;
    private JScrollPane practiceDebateScrollPane;
    private JScrollPane debaterScrollPane;
    // Under debater
    private JScrollPane scrollPane31;
    private JScrollPane scrollPane32;
    private JScrollPane scrollPane33;


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
    //Debate practice
    private JLabel l21;
    private JLabel l22;
    private JLabel l23;
    //Under debater
    private JLabel l31;
    private JLabel l32;
    private JLabel l33;
    private JLabel l34;
    private JLabel l35;
    private JLabel l36;

    //Table 3
    //Competitive
    private JLabel l400;
    private JLabel l401;
    private JLabel l402;
    private JLabel l403;
    private JLabel l404;
    private JLabel l405;
    private JLabel l406;
    private JLabel l407;
    private JLabel l408;
    private JLabel l409;
    //Practice
    private JLabel l500;
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
    private JLabel l511;
    private JLabel l512;
    private JLabel l513;

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

    // Frames
    private JFrame f;
    private JFrame f1;
    private JFrame f2;
    private JFrame f3;
    private JFrame f4;
    private JFrame f5;

    // Layouts
    private GroupLayout mainLayout;
    private GroupLayout historyLayout;
    private GroupLayout generalLayout;
    private GroupLayout debateLayout;
    // Under debate
    private GroupLayout f1Layout;
    private GroupLayout f2Layout;
    //
    private GroupLayout debaterLayout;
    // Under debater
    private GroupLayout f3Layout;
    //
    private GroupLayout competitiveLayout;
    private GroupLayout practiceLayout;
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
    private JTextField textField4;
    // Practice
    private JTextField textField3;
    private JTextField textField5;
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

    // Style
    // Font
    Font  BOLD  = new Font(Font.DIALOG,  Font.BOLD, 15);

    //Border

    Border BLACK_BORDER = BorderFactory.createLineBorder(Color.BLACK);

    // Variables
    private String motion;
    private String[] names1;
    private String[] names2;
    private boolean propWins;
    private boolean propWinsHasValue;
    private boolean sykProp;
    private boolean sykPropHasValue;
    private String enemy;
    private Date now = new Date();
    private String date = now.getDate()+"/"+(now.getMonth()+1)+"/"+(now.getYear()+1900);
    private int[] dateList = new int[3];

    public GUI(Application application) {
        super("Debate Log");
        this.application = application;

        // Frame
        setSize(800, 600);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

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

        // Competitive button
        button11 = new JButton("New competitive debate");
        button11.addActionListener((ActionEvent e) ->
        {
            cardLayout.show(getContentPane(), COMPETITIVE_PANEL_NAME);
            propWinsHasValue = false;
            motion = "";
            enemy = "";
            names1 = new String[4];
            date = now.getDate()+"/"+(now.getMonth()+1)+"/"+(now.getYear()+1900);
            clearCompetitiveText();
        });

        // Practice button
        button12 = new JButton("New practice debate");
        button12.addActionListener((ActionEvent e) ->
        {
            cardLayout.show(getContentPane(), PRACTICE_PANEL_NAME);
            propWinsHasValue = false;
            motion = "";
            names1 = new String[4];
            names2 = new String[4];
            date = now.getDate()+"/"+(now.getMonth()+1)+"/"+(now.getYear()+1900);
            clearPracticeText();
        });

        // History button
        button13 = new JButton("History");
        button13.addActionListener((ActionEvent e) ->
        {
            cardLayout.show(getContentPane(), HISTORY_PANEL_NAME);
        });

        // Main layout
        mainLayout = new GroupLayout(mainPanel);
        mainPanel.setLayout (mainLayout);
        mainLayout.setAutoCreateGaps (true);
        mainLayout.setAutoCreateContainerGaps (true);
        mainLayout.setHorizontalGroup(
                mainLayout.createSequentialGroup()
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(mainLayout.createParallelGroup(CENTER)
                                .addComponent(button11)
                                .addComponent(button12)
                                .addComponent(button13))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
        mainLayout.setVerticalGroup(
                mainLayout.createSequentialGroup()
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(button11)
                        .addComponent(button12)
                        .addComponent(button13)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );


        // HISTORY WINDOW



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
            propRate.setText("Practice prop win rate:      " + String.format("%.2g%n", application.practiceWinningSide()));
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
            application.sortDebaters();
        });

        // History layout
        historyLayout = new GroupLayout (historyPanel);
        historyPanel.setLayout (historyLayout);
        historyLayout.setAutoCreateGaps (true);
        historyLayout.setAutoCreateContainerGaps (true);
        historyLayout.setHorizontalGroup(
                historyLayout.createSequentialGroup()
                        .addComponent(button21)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(historyLayout.createParallelGroup(CENTER)
                                .addComponent(button22)
                                .addComponent(button23)
                                .addComponent(button24))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(button21));
        historyLayout.setVerticalGroup(
                historyLayout.createSequentialGroup()
                        .addComponent(button21)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(button22)
                        .addComponent(button23)
                        .addComponent(button24)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(button21));

        // GENERAL WINDOW

        // General back button
        button31 = new JButton("Back");
        button31.addActionListener((ActionEvent e) ->
        {
            cardLayout.show(getContentPane(), HISTORY_PANEL_NAME);
        });

        // Competitive syk rate text
        sykRate = new JLabel();
        sykRate.setFont(BOLD);
        sykRate.setBorder(BLACK_BORDER);

        // Practice prop rate text
        propRate = new JLabel();
        propRate.setFont(BOLD);
        propRate.setBorder(BLACK_BORDER);

        // Competitive type syk rate text
        l101 = new JLabel("Syk win rate by motion type:      ");

        // Competitive type syk rate table
        // A table for win rates for each motion type in competitive debates
        generalCompetitiveTableModel = new AbstractTableModel ()
        {
            // Determining the width of the table
            public int getColumnCount () { return 2; }
            // Determining the height of the debate based on the number of different motion types that have been debated
            public int getRowCount () { return application.getCompetitiveMotionTypes().length; }
            // The following method returns the value for each cell
            public Object getValueAt (int row, int column)
            {
                if (column == 0)
                    // For each row on column 0, returning a motion type
                    return application.getCompetitiveMotionTypes()[row];
                else
                    // For each row on column 1, returning the corresponding win rate
                    return String.format("%.2g%n",application.getCompetitiveMotionTypeSykRate(application.getCompetitiveMotionTypes())[row]);
            }
            // The following method returns the name for each column
            public String getColumnName (int column)
            {
                if (column == 0)
                    // For column 0
                    return "Motion type";
                else
                    // For column 1 (As there are only 2 columns)
                    return "Win rate";
            }
        };
        generalCompetitiveTable = new JTable (generalCompetitiveTableModel);
        generalCompetitiveScrollPane = new JScrollPane(generalCompetitiveTable);

        // Practice type frequency text
        l102 = new JLabel("Practice motion type frequency: ");

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
            public String getColumnName (int column)
            {
                if (column == 0)
                    return "Motion type";
                else
                    return "Frequency";
            }
        };

        generalPracticeTable = new JTable (generalPracticeTableModel);
        generalPracticeScrollPane = new JScrollPane(generalPracticeTable);

        // General layout
        generalLayout = new GroupLayout (generalPanel);
        generalPanel.setLayout (generalLayout);
        generalLayout.setAutoCreateGaps (true);
        generalLayout.setAutoCreateContainerGaps (true);
        generalLayout.setHorizontalGroup(
                generalLayout.createSequentialGroup()
                        .addComponent(button31)
                        .addGroup(generalLayout.createParallelGroup(LEADING)
                                .addComponent(sykRate)
                                .addGroup(generalLayout.createSequentialGroup()
                                        .addComponent(l101)
                                        .addComponent(generalCompetitiveScrollPane))
                                .addComponent(propRate)
                                .addGroup(generalLayout.createSequentialGroup()
                                        .addComponent(l102)
                                        .addComponent(generalPracticeScrollPane)))
                        .addComponent(button31));
        generalLayout.setVerticalGroup(
                generalLayout.createSequentialGroup()
                        .addComponent(button31)
                        .addComponent(sykRate)
                        .addGroup(generalLayout.createParallelGroup(LEADING)
                                .addComponent(l101)
                                .addComponent(generalCompetitiveScrollPane))
                        .addComponent(propRate)
                        .addGroup(generalLayout.createParallelGroup(LEADING)
                                .addComponent(l102)
                                .addComponent(generalPracticeScrollPane))
                        .addComponent(button31));

        // DEBATE WINDOW

        // Debate back button
        button41 = new JButton("Back");
        button41.addActionListener((ActionEvent e) ->
        {
            cardLayout.show(getContentPane(), HISTORY_PANEL_NAME);
            closeFrames();
        });

        // Competitive debate table text
        l201 = new JLabel("Competitive: ");

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
                else {
                    return application.getCompetitives().get(row).getDateString();
                }
            }
            public String getColumnName (int column)
            {
                if (column == 0)
                    return "Motion";
                else
                    return "Date";
            }
        };
        competitiveDebateTable = new JTable (competitiveDebateTableModel);
        competitiveDebateTable.getColumnModel().getColumn(0).setPreferredWidth(400);
        competitiveDebateTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        competitiveDebateScrollPane = new JScrollPane(competitiveDebateTable);


        // Competitive table info button

        button42 = new JButton(("More info"));
        button42.addActionListener((ActionEvent e) ->
        {
            closeFrames();
            int selected = competitiveDebateTable.getSelectedRow (); // returns -1 if none selected
            if (selected >= 0 && application.getCompetitives() != null) {

                // Title
                f1 = new JFrame(application.getCompetitives().get(selected).getMotion() +
                        " " + application.getCompetitives().get(selected).getDateString());
                f1.setSize(800, 400);

                // Syk side text
                if(application.getCompetitives().get(selected).getSykProp())
                    l11 = new JLabel("Syk is proposition.");
                else
                    l11 = new JLabel("Syk is opposition.");

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

                // Winner text
                if(application.getCompetitives().get(selected).getSykProp()==application.getCompetitives().get(selected).getPropWins())
                    l12 = new JLabel("Syk wins against " + application.getCompetitives().get(selected).getEnemy() + ".");
                else
                    l12 = new JLabel("Syk loses against " + application.getCompetitives().get(selected).getEnemy() + ".");

                // Layout
                f1Layout = new GroupLayout (f1.getContentPane());
                f1.getContentPane().setLayout (f1Layout);
                f1Layout.setAutoCreateGaps (true);
                f1Layout.setAutoCreateContainerGaps (true);
                f1Layout.setHorizontalGroup(
                        f1Layout.createSequentialGroup()
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(f1Layout.createParallelGroup(LEADING)
                                        .addComponent(table11)
                                        .addComponent(l11)
                                        .addComponent(l12))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
                f1Layout.setVerticalGroup(
                        f1Layout.createSequentialGroup()
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(table11)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(l11)
                                .addComponent(l12)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
                f1.setVisible(true);
            }
        });

        // Competitive delete button

        button43 = new JButton("Delete");
        button43.addActionListener((ActionEvent e) ->
                {
                    closeFrames();
                    int selected = competitiveDebateTable.getSelectedRow();
                    if (selected >= 0 && application.getCompetitives() != null){
                        application.deleteCompetitive(selected);
                        updateCompetitiveDebateTable();
                        serialize();
                    }
                }
        );

        // Practice debate table text
        l202 = new JLabel("Practice:       ");

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
                    return application.getPractices().get(row).getDateString();
            }
            public String getColumnName (int column)
            {
                if (column == 0)
                    return "Motion";
                else
                    return "Date";
            }
        };
        practiceDebateTable = new JTable (practiceDebateTableModel);
        practiceDebateTable.getColumnModel().getColumn(0).setPreferredWidth(400);
        practiceDebateTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        practiceDebateScrollPane = new JScrollPane(practiceDebateTable);

        // Practice table info button

        button44 = new JButton(("More info"));
        button44.addActionListener((ActionEvent e) ->
        {
            closeFrames();
            int selected = practiceDebateTable.getSelectedRow (); // returns -1 if none selected
            if (selected >= 0 && application.getPractices() != null) {

                // Title
                f2 = new JFrame(application.getPractices().get(selected).getMotion()
                        + " " + application.getPractices().get(selected).getDateString());
                f2.setSize(800, 400);
                f2.setLayout(new FlowLayout());

                // Proposition text
                l21 = new JLabel("Proposition: ");

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

                // Opposition text
                l22 = new JLabel("Opposition: ");

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

                // Winner text
                if (application.getPractices().get(selected).getPropWins())
                    l23 = new JLabel ("Proposition wins!");
                else
                    l23 = new JLabel ("Opposition wins!");

                // Layout
                f2Layout = new GroupLayout (f2.getContentPane());
                f2.getContentPane().setLayout (f2Layout);
                f2Layout.setAutoCreateGaps (true);
                f2Layout.setAutoCreateContainerGaps (true);
                f2Layout.setHorizontalGroup(
                        f2Layout.createSequentialGroup()
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(f2Layout.createParallelGroup(LEADING)
                                        .addComponent(l21)
                                        .addComponent(l22))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(f2Layout.createParallelGroup(LEADING)
                                        .addComponent(table21)
                                        .addComponent(table22)
                                        .addComponent(l23))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
                f2Layout.setVerticalGroup(
                        f2Layout.createSequentialGroup()
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(f2Layout.createParallelGroup(LEADING)
                                        .addComponent(l21)
                                        .addComponent(table21))
                                .addGroup(f2Layout.createParallelGroup(LEADING)
                                        .addComponent(l22)
                                        .addComponent(table22))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(l23)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
                f2.setVisible(true);
            }
        });

        // Practice delete button

        button45 = new JButton("Delete");
        button45.addActionListener((ActionEvent e) ->
                {
                    closeFrames();
                    int selected = practiceDebateTable.getSelectedRow();
                    if (selected >= 0 && application.getPractices() != null){
                        application.deletePractice(selected);
                        updatePracticeDebateTable();
                        serialize();
                    }
                }
        );

        // Debate panel layout
        debateLayout = new GroupLayout (debatePanel);
        debatePanel.setLayout (debateLayout);
        debateLayout.setAutoCreateGaps (true);
        debateLayout.setAutoCreateContainerGaps (true);
        debateLayout.setHorizontalGroup(
                debateLayout.createSequentialGroup()
                        .addComponent(button41)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(debateLayout.createParallelGroup(LEADING)
                                .addGroup(debateLayout.createSequentialGroup()
                                        .addComponent(l201)
                                        .addComponent(competitiveDebateScrollPane)
                                        .addGroup(debateLayout.createParallelGroup(LEADING)
                                                .addComponent(button42)
                                                .addComponent(button43)))
                                .addGroup(debateLayout.createSequentialGroup()
                                        .addComponent(l202)
                                        .addComponent(practiceDebateScrollPane)
                                        .addGroup(debateLayout.createParallelGroup(LEADING)
                                                .addComponent(button44)
                                                .addComponent(button45))))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(button41));
        debateLayout.setVerticalGroup(
                debateLayout.createSequentialGroup()
                        .addComponent(button41)
                        .addGroup(debateLayout.createParallelGroup(LEADING)
                                .addComponent(l201)
                                .addComponent(competitiveDebateScrollPane)
                                .addGroup(debateLayout.createSequentialGroup()
                                        .addComponent(button42)
                                        .addComponent(button43)))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(debateLayout.createParallelGroup(LEADING)
                                .addComponent(l202)
                                .addComponent(practiceDebateScrollPane)
                                .addGroup(debateLayout.createSequentialGroup()
                                        .addComponent(button44)
                                        .addComponent(button45)))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(button41));

        // DEBATER WINDOW

        // Debater back button
        button51 = new JButton("Back");
        button51.addActionListener((ActionEvent e) ->
        {
            cardLayout.show(getContentPane(), HISTORY_PANEL_NAME);
            closeFrames();
        });

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
            public String getColumnName (int column)
            {
                if (column == 0)
                    return "Name";
                else
                    return "Win rate";
            }
        };
        debaterTable = new JTable (debaterTableModel);
        debaterScrollPane = new JScrollPane(debaterTable);

        // Debater table info button

        button52 = new JButton(("More info"));
        button52.addActionListener((ActionEvent e) ->
        {
            closeFrames();
            int selected = debaterTable.getSelectedRow (); // returns -1 if none selected
            if (selected >= 0 && application.getDebaters() != null) {
                f3 = new JFrame(application.getDebaters().get(selected).getName());
                f3.setSize(400, 500);
                l31 = new JLabel ("Total win rate: " + String.format("%.2g%n", application.getDebaters().get(selected).winRate()));
                l34 = new JLabel ("Total debates: " + application.getDebaters().get(selected).getDebates());
                tableModel31 = new AbstractTableModel ()
                {
                    public int getColumnCount () { return 3; } // two fields in data class
                    public int getRowCount () { return 4; } // one row for each data item
                    // returns the value that is shown in specific cell
                    public Object getValueAt (int row, int column)
                    {
                        if (column == 0)
                            return (row+1);
                        else if (column == 1)
                            return String.format("%.2g%n", application.getDebaters().get(selected).winRate(row));
                        else
                            return application.getDebaters().get(selected).totalDebates(row);
                    }
                    public String getColumnName (int column)
                    {
                        if (column == 0)
                            return "Speech";
                        else if (column == 1)
                            return "Win rate";
                        else
                            return "Debates";
                    }
                };
                table31 = new JTable (tableModel31);
                scrollPane31 = new JScrollPane(table31);
                l32 = new JLabel ("Competitive win rate: " + String.format("%.2g%n", application.getDebaters().get(selected).competitiveWinRate()));
                l35 = new JLabel ("Competitive debates: " + application.getDebaters().get(selected).totalCompetitiveDebates());
                tableModel32 = new AbstractTableModel ()
                {
                    public int getColumnCount () { return 3; } // two fields in data class
                    public int getRowCount () { return 4; } // one row for each data item
                    // returns the value that is shown in specific cell
                    public Object getValueAt (int row, int column)
                    {
                        if (column == 0)
                            return (row+1);
                        else if (column == 1)
                            return String.format("%.2g%n", application.getDebaters().get(selected).competitiveWinRate(row));
                        else
                            return application.getDebaters().get(selected).totalCompetitiveDebates(row);
                    }
                    public String getColumnName (int column)
                    {
                        if (column == 0)
                            return "Speech";
                        else if (column == 1)
                            return "Win rate";
                        else
                            return "Debates";
                    }
                };
                table32 = new JTable (tableModel32);
                scrollPane32 = new JScrollPane(table32);
                l33 = new JLabel ("Practice win rate: " + String.format("%.2g%n", application.getDebaters().get(selected).practiceWinRate()));
                l36 = new JLabel ("Practice debates: " + application.getDebaters().get(selected).totalPracticeDebates());
                tableModel33 = new AbstractTableModel ()
                {
                    public int getColumnCount () { return 3; } // two fields in data class
                    public int getRowCount () { return 4; } // one row for each data item
                    // returns the value that is shown in specific cell
                    public Object getValueAt (int row, int column)
                    {
                        if (column == 0)
                            return (row+1);
                        else if (column == 1)
                            return String.format("%.2g%n", application.getDebaters().get(selected).practiceWinRate(row));
                        else
                            return application.getDebaters().get(selected).totalPracticeDebates(row);
                    }
                    public String getColumnName (int column)
                    {
                        if (column == 0)
                            return "Speech";
                        else if (column == 1)
                            return "Win rate";
                        else
                            return "Debates";
                    }
                };
                table33 = new JTable (tableModel33);
                scrollPane33 = new JScrollPane(table33);

                f3Layout = new GroupLayout (f3.getContentPane());
                f3.getContentPane().setLayout (f3Layout);
                f3Layout.setAutoCreateGaps (true);
                f3Layout.setAutoCreateContainerGaps (true);
                f3Layout.setHorizontalGroup(
                        f3Layout.createSequentialGroup()
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(f3Layout.createParallelGroup(LEADING)
                                        .addComponent(l31)
                                        .addComponent(l34)
                                        .addComponent(l32)
                                        .addComponent(l35)
                                        .addComponent(l33)
                                        .addComponent(l36))
                                .addGroup(f3Layout.createParallelGroup(LEADING)
                                        .addComponent(scrollPane31)
                                        .addComponent(scrollPane32)
                                        .addComponent(scrollPane33))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
                f3Layout.setVerticalGroup(
                        f3Layout.createSequentialGroup()
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(f3Layout.createParallelGroup(LEADING)
                                        .addGroup(f3Layout.createSequentialGroup()
                                                .addComponent(l31)
                                                .addComponent(l34))
                                        .addComponent(scrollPane31))
                                .addGroup(f3Layout.createParallelGroup(LEADING)
                                        .addGroup(f3Layout.createSequentialGroup()
                                                .addComponent(l32)
                                                .addComponent(l35))
                                        .addComponent(scrollPane32))
                                .addGroup(f3Layout.createParallelGroup(LEADING)
                                        .addGroup(f3Layout.createSequentialGroup()
                                                .addComponent(l33)
                                                .addComponent(l36))
                                        .addComponent(scrollPane33))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
                f3.setVisible(true);
            }
        });

        // Debater delete button

        button53 = new JButton("Delete");
        button53.addActionListener((ActionEvent e) ->
                {
                    closeFrames();
                    int selected = debaterTable.getSelectedRow();
                    if (selected >= 0 && application.getDebaters() != null){
                        if (application.getDebaters().get(selected).getDebates()==0){
                            application.deleteDebater(selected);
                            updateDebaterTable();
                            serialize();
                        }
                        else {
                            f = new JFrame("Alert");
                            f.add(new JLabel("Cannot delete an active debater."));
                            f.setSize(300, 300);
                            f.setVisible(true);
                        }
                    }
                }
        );

        // Debater panel layout
        debaterLayout = new GroupLayout (debaterPanel);
        debaterPanel.setLayout (debaterLayout);
        debaterLayout.setAutoCreateGaps (true);
        debaterLayout.setAutoCreateContainerGaps (true);
        debaterLayout.setHorizontalGroup(
                debaterLayout.createSequentialGroup()
                        .addComponent(button51)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(debaterScrollPane)
                        .addGroup(debaterLayout.createParallelGroup(LEADING)
                                .addComponent(button52)
                                .addComponent(button53))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(button51));
        debaterLayout.setVerticalGroup(
                debaterLayout.createSequentialGroup()
                        .addComponent(button51)
                        .addGroup(debaterLayout.createParallelGroup(LEADING)
                                .addComponent(debaterScrollPane)
                                .addGroup(debaterLayout.createSequentialGroup()
                                        .addComponent(button52)
                                        .addComponent(button53)))
                        .addComponent(button51));

        // COMPETITIVE WINDOW

        // Competitive back button

        button61 = new JButton("Back");
        button61.addActionListener((ActionEvent e) ->
        {
            cardLayout.show(getContentPane(), MAIN_PANEL_NAME);
            closeFrames();
        });





        // Competitive text
        l400 = new JLabel("Competitive");
        l400.setFont(BOLD);
        // Competitive motion text
        l401 = new JLabel("Motion:    ");

        // Text field
        textField1 = new JTextField(30);

        textField1.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                updateMotion(textField1.getText());
            }
        });
        // Competitive date text
        l409 = new JLabel("Date:        ");

        // Competitive date text field
        textField4 = new JTextField();
        textField4.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                updateDate(textField4.getText());
            }
        });
        // Competitive enemy text
        l402 = new JLabel("Enemy:     ");

        // Text field
        textField2 = new JTextField(10);
        textField2.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                updateEnemy(textField2.getText());
            }
        });

        // Competitive syk text
        l403 = new JLabel("Syk side:  ");

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

        // Competitive winner text
        l404 = new JLabel("Winner:    ");

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

        // Competitive prop table

        l405 = new JLabel("Speech 1: ");
        textField11 = new JTextField(10);
        textField11.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                updateSpeaker(textField11.getText(), true, 1);
            }
        });
        l406 = new JLabel("Speech 2: ");
        textField12 = new JTextField(10);
        textField12.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                updateSpeaker(textField12.getText(), true, 2);
            }
        });
        l407 = new JLabel("Speech 3: ");
        textField13 = new JTextField(10);
        textField13.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                updateSpeaker(textField13.getText(), true, 3);
            }
        });
        l408 = new JLabel("Speech 4: ");
        textField14 = new JTextField(10);
        textField14.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                updateSpeaker(textField14.getText(), true, 4);
            }
        });

        // Competitive add button
        button62 = new JButton(("Add"));
        button62.addActionListener((ActionEvent e) ->
        {
            closeFrames();
            String message = addNewCompetitiveDebate();
            if(message!="") {
                f4 = new JFrame("Alert");
                f4.add(new JLabel(message));
                f4.setSize(300, 300);
                f4.setVisible(true);
            }
        });

        // Competitive panel layout
        competitiveLayout = new GroupLayout (competitivePanel);
        competitivePanel.setLayout (competitiveLayout);
        competitiveLayout.setAutoCreateGaps (true);
        competitiveLayout.setAutoCreateContainerGaps (true);
        competitiveLayout.setHorizontalGroup(
                competitiveLayout.createSequentialGroup()
                        .addComponent(button61)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(competitiveLayout.createParallelGroup(LEADING)
                                .addComponent(l400)
                                .addGroup(competitiveLayout.createSequentialGroup()
                                        .addComponent(l401)
                                        .addComponent(textField1))
                                .addGroup(competitiveLayout.createSequentialGroup()
                                        .addComponent(l409)
                                        .addComponent(textField4))
                                .addGroup(competitiveLayout.createSequentialGroup()
                                        .addComponent(l402)
                                        .addComponent(textField2))
                                .addGroup(competitiveLayout.createSequentialGroup()
                                        .addComponent(l403)
                                        .addComponent(sykPropCheckBox)
                                        .addComponent(sykOppCheckBox))
                                .addGroup(competitiveLayout.createSequentialGroup()
                                        .addComponent(l404)
                                        .addComponent(propCheckBox1)
                                        .addComponent(oppCheckBox1))
                                .addGroup(competitiveLayout.createSequentialGroup()
                                        .addComponent(l405)
                                        .addComponent(textField11))
                                .addGroup(competitiveLayout.createSequentialGroup()
                                        .addComponent(l406)
                                        .addComponent(textField12))
                                .addGroup(competitiveLayout.createSequentialGroup()
                                        .addComponent(l407)
                                        .addComponent(textField13))
                                .addGroup(competitiveLayout.createSequentialGroup()
                                        .addComponent(l408)
                                        .addComponent(textField14)))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(button62));
        competitiveLayout.setVerticalGroup(
                competitiveLayout.createSequentialGroup()
                        .addComponent(button61)
                        .addComponent(l400)
                        .addGroup(competitiveLayout.createParallelGroup(LEADING)
                                .addComponent(l401)
                                .addComponent(textField1))
                        .addGroup(competitiveLayout.createParallelGroup(LEADING)
                                .addComponent(l409)
                                .addComponent(textField4))
                        .addGroup(competitiveLayout.createParallelGroup(LEADING)
                                        .addComponent(l402)
                                        .addComponent(textField2))
                        .addGroup(competitiveLayout.createParallelGroup(LEADING)
                                        .addComponent(l403)
                                        .addComponent(sykPropCheckBox)
                                        .addComponent(sykOppCheckBox))
                        .addGroup(competitiveLayout.createParallelGroup(LEADING)
                                        .addComponent(l404)
                                        .addComponent(propCheckBox1)
                                        .addComponent(oppCheckBox1))
                        .addGroup(competitiveLayout.createParallelGroup(LEADING)
                                        .addComponent(l405)
                                        .addComponent(textField11))
                        .addGroup(competitiveLayout.createParallelGroup(LEADING)
                                        .addComponent(l406)
                                        .addComponent(textField12))
                        .addGroup(competitiveLayout.createParallelGroup(LEADING)
                                        .addComponent(l407)
                                        .addComponent(textField13))
                        .addGroup(competitiveLayout.createParallelGroup(LEADING)
                                        .addComponent(l408)
                                        .addComponent(textField14))
                        .addComponent(button62));

        // PRACTICE WINDOW

        // Practice back button
        button71 = new JButton("Back");
        button71.addActionListener((ActionEvent e) ->
        {
            cardLayout.show(getContentPane(), MAIN_PANEL_NAME);
            closeFrames();
        });

        // Practice text
        l500 = new JLabel("Practice");
        l500.setFont(BOLD);

        // Practice motion text
        l501 = new JLabel("Motion:   ");

        // Text field
        textField3 = new JTextField(30);
        textField3.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                updateMotion(textField3.getText());
            }
        });

        // Practice date text
        l513 = new JLabel("Date:       ");

        // Practice date text field
        textField5 = new JTextField();
        textField5.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                updateDate(textField5.getText());
            }
        });

        // Practice winner text
        l502 = new JLabel("Winner:   ");

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

        // Practice prop table

        l511 = new JLabel("Team Proposition");
        l503 = new JLabel("Speech 1: ");
        textField21 = new JTextField(10);
        textField21.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                updateSpeaker(textField21.getText(), true, 1);
            }
        });
        l504 = new JLabel("Speech 2: ");
        textField22 = new JTextField(10);
        textField22.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                updateSpeaker(textField22.getText(), true, 2);
            }
        });
        l505 = new JLabel("Speech 3: ");
        textField23 = new JTextField(10);
        textField23.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                updateSpeaker(textField23.getText(), true, 3);
            }
        });
        l506 = new JLabel("Speech 4: ");
        textField24 = new JTextField(10);
        textField24.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                updateSpeaker(textField24.getText(), true, 4);
            }
        });

        // Practice opp table

        l512 = new JLabel("Team Opposition");
        l507 = new JLabel("Speech 1: ");
        textField25 = new JTextField(10);
        textField25.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                updateSpeaker(textField25.getText(), false, 1);
            }
        });
        l508 = new JLabel("Speech 2: ");
        textField26 = new JTextField(10);
        textField26.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                updateSpeaker(textField26.getText(), false, 2);
            }
        });
        l509 = new JLabel("Speech 3: ");
        textField27 = new JTextField(10);
        textField27.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                updateSpeaker(textField27.getText(), false, 3);
            }
        });
        l510 = new JLabel("Speech 4: ");
        textField28 = new JTextField(10);
        textField28.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                updateSpeaker(textField28.getText(), false, 4);
            }
        });

        // Practice add button
        button72 = new JButton(("Add"));
        button72.addActionListener((ActionEvent e) ->
        {
            closeFrames();
            String message = addNewPracticeDebate();
            if(message!="") {
                f5 = new JFrame("Alert");
                f5.add(new JLabel(message));
                f5.setSize(300, 300);
                f5.setVisible(true);
            }
        });

        // Practice panel layout
        practiceLayout = new GroupLayout (practicePanel);
        practicePanel.setLayout (practiceLayout);
        practiceLayout.setAutoCreateGaps (true);
        practiceLayout.setAutoCreateContainerGaps (true);
        practiceLayout.setHorizontalGroup(
                practiceLayout.createSequentialGroup()
                        .addComponent(button71)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(practiceLayout.createParallelGroup(LEADING)
                                .addComponent(l500)
                                .addGroup(practiceLayout.createSequentialGroup()
                                        .addComponent(l501)
                                        .addComponent(textField3))
                                .addGroup(practiceLayout.createSequentialGroup()
                                        .addComponent(l513)
                                        .addComponent(textField5))
                                .addGroup(practiceLayout.createSequentialGroup()
                                        .addComponent(l502)
                                        .addComponent(propCheckBox2)
                                        .addComponent(oppCheckBox2))
                                .addComponent(l511)
                                .addGroup(practiceLayout.createSequentialGroup()
                                        .addComponent(l503)
                                        .addComponent(textField21))
                                .addGroup(practiceLayout.createSequentialGroup()
                                        .addComponent(l504)
                                        .addComponent(textField22))
                                .addGroup(practiceLayout.createSequentialGroup()
                                        .addComponent(l505)
                                        .addComponent(textField23))
                                .addGroup(practiceLayout.createSequentialGroup()
                                        .addComponent(l506)
                                        .addComponent(textField24))
                                .addComponent(l512)
                                .addGroup(practiceLayout.createSequentialGroup()
                                        .addComponent(l507)
                                        .addComponent(textField25))
                                .addGroup(practiceLayout.createSequentialGroup()
                                        .addComponent(l508)
                                        .addComponent(textField26))
                                .addGroup(practiceLayout.createSequentialGroup()
                                        .addComponent(l509)
                                        .addComponent(textField27))
                                .addGroup(practiceLayout.createSequentialGroup()
                                        .addComponent(l510)
                                        .addComponent(textField28)))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(button72));
        practiceLayout.setVerticalGroup(
                practiceLayout.createSequentialGroup()
                        .addComponent(button71)
                        .addComponent(l500)
                        .addGroup(practiceLayout.createParallelGroup(LEADING)
                                .addComponent(l501)
                                .addComponent(textField3))
                        .addGroup(practiceLayout.createParallelGroup(LEADING)
                                .addComponent(l513)
                                .addComponent(textField5))
                        .addGroup(practiceLayout.createParallelGroup(LEADING)
                                .addComponent(l502)
                                .addComponent(propCheckBox2)
                                .addComponent(oppCheckBox2))
                        .addComponent(l511)
                        .addGroup(practiceLayout.createParallelGroup(LEADING)
                                .addComponent(l503)
                                .addComponent(textField21))
                        .addGroup(practiceLayout.createParallelGroup(LEADING)
                                .addComponent(l504)
                                .addComponent(textField22))
                        .addGroup(practiceLayout.createParallelGroup(LEADING)
                                .addComponent(l505)
                                .addComponent(textField23))
                        .addGroup(practiceLayout.createParallelGroup(LEADING)
                                .addComponent(l506)
                                .addComponent(textField24))
                        .addComponent(l512)
                        .addGroup(practiceLayout.createParallelGroup(LEADING)
                                .addComponent(l507)
                                .addComponent(textField25))
                        .addGroup(practiceLayout.createParallelGroup(LEADING)
                                .addComponent(l508)
                                .addComponent(textField26))
                        .addGroup(practiceLayout.createParallelGroup(LEADING)
                                .addComponent(l509)
                                .addComponent(textField27))
                        .addGroup(practiceLayout.createParallelGroup(LEADING)
                                .addComponent(l510)
                                .addComponent(textField28))
                        .addComponent(button72));


        // Visibility
        setVisible(true);
    }

    // Methods

    public void closeFrames(){
        Frame[] frames = getFrames();
        for (int i=1; i<frames.length; i++) {
            frames[i].dispose();
        }
    }

    public void serialize(){
        try (ObjectOutputStream out =
                     new ObjectOutputStream(new FileOutputStream(application.getFname()))){
            ArrayList<Practice> practices = application.getPractices();
            out.writeObject(practices);
            ArrayList<Competitive> competitives = application.getCompetitives();
            out.writeObject(competitives);
            ArrayList<Debater> debaters = application.getDebaters();
            out.writeObject(debaters);
        } catch (Exception ex) {System.out.println(ex);}
    }

    public boolean correctDate(){
        String[] parts = date.split("/");
        if (parts.length != 3)
            return false;
        try {
            int day = Integer.parseInt(parts[0]);
            if(day<1 || day>31)
                return false;
            int month = Integer.parseInt(parts[1]);
            if(month<1 || month>12)
                return false;
            int year = Integer.parseInt(parts[2]);
            int[] dateList = {day, month, year};
            this.dateList = dateList;
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    boolean checkMotionType(String type){
        for (int i = 0; i < application.getOfficialMotionTypes().length; i++) {
            if(type.equals(application.getOfficialMotionTypes()[i]))
                return false;
        }
        int result = JOptionPane.showConfirmDialog (this,
                "Are you sure you want to use a non-official motion type?", null, JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.YES_OPTION)
            return false;
        return true;
    }

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

    public void updateDate(String text){
        date = text;
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

    public void clearCompetitiveText(){
        textField1.setText("This house");
        textField2.setText("");
        textField4.setText(date);
        sykPropCheckBox.setSelected(false);
        sykOppCheckBox.setSelected(false);
        propCheckBox1.setSelected(false);
        oppCheckBox1.setSelected(false);
        textField11.setText("");
        textField12.setText("");
        textField13.setText("");
        textField14.setText("");
    }

    public void clearPracticeText(){
        textField3.setText("This house");
        textField5.setText(date);
        propCheckBox2.setSelected(false);
        oppCheckBox2.setSelected(false);
        textField21.setText("");
        textField22.setText("");
        textField23.setText("");
        textField24.setText("");
        textField25.setText("");
        textField26.setText("");
        textField27.setText("");
        textField28.setText("");
    }

    public String addNewCompetitiveDebate() {
        String[] words = motion.split(" ");
        if (words.length < 3) return "Motion too short.";
        if (!words[0].equals("This") || !words[1].equals("house")) return "Motion must begin by 'This house'.";
        if(checkMotionType(words[2])) return "";
        if (date.equals("")) return "Date missing.";
        if (!correctDate()) return "Date must be of the form day/month/year";
        if (enemy.equals("")) return "Enemy team missing.";
        if (!sykPropHasValue) return "Indicate SYK's side.";
        if (!propWinsHasValue) return "Indicate the winning side.";
        for (int i = 0; i < 4; i++) {
            if (names1[i] == null) return "A debater name missing.";
            if (names1[i].equals("")) return "A debater name missing.";
        }
        application.addCompetitive(motion, dateList, enemy, sykProp, propWins, names1);
        serialize();
        return "New competitive debate added.";
    }

    // A method called every time "Add" is pressed
    public String addNewPracticeDebate() {
        String[] words = motion.split(" ");
        // Check if the input meets the requirements
        if (words.length < 3) return "Motion too short.";
        if (!words[0].equals("This") || !words[1].equals("house")) return "Every motion must begin by 'This house'.";
        if(checkMotionType(words[2])) return "";
        if (date.equals("")) return "Date missing.";
        // Implementing the more complex correctDate() method
        if (!correctDate()) return "Date must be of the form 'day/month/year'.";
        if (!propWinsHasValue) return "Indicate the winning side.";
        // Looping through every debater
        for (int i = 0; i < 4; i++) {
            if (names1[i] == null || names2[i] == null) return "A debater name missing.";
            if (names1[i].equals("") || names2[i].equals("")) return "A debater name missing.";
        }
        // Referring to the application class to save the debate to the debate list
        application.addPractice(motion, dateList, propWins, names1, names2);
        // Immediately saving the debate list with the new debate
        serialize();
        // Returning a text for the pop-up window
        return "New practice debate added.";
    }
}