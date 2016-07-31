package carcassonne.view;

import java.awt.GridBagConstraints;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import carcassonne.control.MainController;
import carcassonne.model.tile.TileFactory;
import carcassonne.model.tile.TileType;

/**
 * GUI class for the small tile placement GUI. It lets the user look at the tile to place and rotate
 * it.
 * @author Timur
 */
public class PlacementGUI extends SmallGUI {
    private static final long serialVersionUID = -5179683977081970564L;

    public static void main(String[] args) {
        new PlacementGUI(null);
    }

    private JButton buttonSkip;
    private JLabel tileLabel;
    private JButton buttonRotateLeft;
    private JButton buttonRotateRight;

    /**
     * TODO comment the placement GUI.
     * @param controller
     * @param title
     */
    public PlacementGUI(MainController controller) {
        super(controller, ""); // TODO take tile.
        buildContent();
        finishFrame();
    }

    // build the GUI content
    private void buildContent() {
        tileLabel = new JLabel(TileFactory.createTile(TileType.Null).getImage());
        // create buttons:
        buttonSkip = new JButton(new ImageIcon("src/main/ressources/icons/skip.png"));
        buttonRotateLeft = new JButton(new ImageIcon("src/main/ressources/icons/left.png"));
        buttonRotateRight = new JButton(new ImageIcon("src/main/ressources/icons/right.png"));
        // set tool tips:
        buttonSkip.setToolTipText("Don't place tile and skip turn");
        buttonRotateLeft.setToolTipText("Rotate left");
        buttonRotateRight.setToolTipText("Rotate right");
        // set constraints:
        constraints.fill = GridBagConstraints.BOTH;
        // add buttons:
        add(buttonRotateLeft, constraints);
        add(buttonSkip, constraints);
        add(buttonRotateRight, constraints);
        // change constraints and add label:
        constraints.gridy = 1;
        constraints.gridx = 0;
        constraints.gridwidth = 3;
        add(tileLabel, constraints);
    }
}