package carcassonne.view.tertiary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import carcassonne.control.MainController;
import carcassonne.model.Round;
import carcassonne.view.GlobalKeyBindingManager;
import carcassonne.view.main.MainGUI;
import carcassonne.view.util.MouseClickListener;

/**
 * A class for the game statistics GUI that shows the final scores of a round.
 * @author Timur Saglam
 */
public class GameStatisticsGUI extends JDialog {
    private static final long serialVersionUID = 2862334382605282126L; // generated UID
    private static final int ADDITIONAL_VERTICLE_SIZE = 100; // ensures that all text is readable
    static final int SCORE_COLUMN = 5;
    static final Color HEADER_COLOR = new Color(220, 220, 220);
    static final Color BODY_COLOR = new Color(237, 237, 237);
    private final MainController controller;
    private JButton buttonClose;
    private JTable table;

    /**
     * Creates the GUI and extracts the data from the current round.
     * @param mainUI is the main user interface.
     * @param controller is the game controller.
     * @param round is the current round.
     * @mainUI is the main user interface.
     */
    public GameStatisticsGUI(MainGUI mainUI, MainController controller, Round round) {
        super(mainUI);
        this.controller = controller;
        buildTable(round);
        buildButtonClose();
        buildFrame();
    }

    private void buildTable(Round round) {
        table = new JTable(new GameStatisticsModel(round));
        // Columns:
        TableColumnModel model = table.getColumnModel();
        CellRenderer renderer = new CellRenderer(round);
        for (int i = 0; i < model.getColumnCount(); i++) {
            model.getColumn(i).setCellRenderer(renderer);
        }
        // Header:
        JTableHeader header = table.getTableHeader();
        header.setDefaultRenderer(new HeaderRenderer());
        header.setReorderingAllowed(false);
        table.setBackground(BODY_COLOR);
    }

    /**
     * Adds the global key bindings to this UI.
     * @param keyBindings are the global key bindings.
     */
    public void addKeyBindings(GlobalKeyBindingManager keyBindings) {
        InputMap inputMap = table.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = table.getActionMap();
        keyBindings.addKeyBindingsToMaps(inputMap, actionMap);
    }

    /**
     * Hides and disposes the GUI.
     */
    public void closeGUI() {
        setVisible(false);
        dispose();
    }

    private void buildButtonClose() {
        buttonClose = new JButton("Close");
        buttonClose.addMouseListener((MouseClickListener) event -> {
            setVisible(false);
            controller.requestSkip();
        });
    }

    private void buildFrame() {
        setLayout(new BorderLayout());
        add(table.getTableHeader(), BorderLayout.PAGE_START);
        add(table, BorderLayout.CENTER);
        add(buttonClose, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setMinimumSize(new Dimension(getSize().width + ADDITIONAL_VERTICLE_SIZE, getSize().height));
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                controller.requestSkip();
            }
        });
    }
}
