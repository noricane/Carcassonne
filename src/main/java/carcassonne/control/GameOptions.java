package carcassonne.control;

import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.GraphicsEnvironment;

/**
 * Singleton that stores the game options and other information. There is only one option instance
 * at a time. The use of singletons is heavily discussed.
 * @author Timur
 */
public final class GameOptions {
    private static GameOptions instance;

    /**
     * Access method for the GameProperties instance. Secures that only one property object can
     * exist at a time.
     * @return the instance.
     */
    public static GameOptions getInstance() {
        if (instance == null) {
            instance = new GameOptions();
        }
        return instance;
    }

    /**
     * is the width value of the resolution.
     */
    public final int resolutionWidth;

    /**
     * is the height value of the resolution.
     */
    public final int resolutionHeight;

    /**
     * is the name of the operating system.
     */
    public final String operatingSystemName;

    /**
     * maximal amount of players.
     */
    public final int maximalPlayers;

    /**
     * width and height of a tile in pixels.
     */
    public final int tileSize;
    
    /**
     * Font type of the button.
     */
    public final Font buttonFont;

    private int taskBarHeight;

    /**
     * Simple constructor that loads the information.
     */
    private GameOptions() {
        GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        DisplayMode displayMode = environment.getDefaultScreenDevice().getDisplayMode();
        resolutionWidth = displayMode.getWidth();
        resolutionHeight = displayMode.getHeight();
        operatingSystemName = System.getProperty("os.name");
        switch (operatingSystemName) {
        case "Windows 7":
            taskBarHeight = 40;
            break;
        case "Mac OS X":
            taskBarHeight = 22;
            break;
        default:
            taskBarHeight = 40;
        }
        buttonFont = new Font("Helvetica", Font.BOLD, 12);
        maximalPlayers = 4;
        tileSize =  100;
    }

    /**
     * Getter for the frame height, which depends on the resolution height.
     * @return the frame height.
     */
    public int getGridHeight() {
        return (resolutionHeight - taskBarHeight) / 100;
    }

    /**
     * Getter for the frame width, which depends on the resolution width.
     * @return the frame width.
     */
    public int getGridWidth() {
        return resolutionWidth / 100;
    }

}