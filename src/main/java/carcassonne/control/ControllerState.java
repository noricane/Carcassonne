package carcassonne.control;

import carcassonne.model.grid.GridDirection;

/**
 * Is the abstract state of the state machine.
 * @author Timur Saglam
 */
public abstract class ControllerState {

    protected MainController controller;
    protected GameOptions options;

    /**
     * Constructor of the abstract state, sets the controller from the parameter, registers the
     * state at the controller and calls the <code>entry()</code> method.
     * @param controller sets the controller.
     */
    public ControllerState(MainController controller) {
        this.controller = controller;
        options = GameOptions.getInstance();
        controller.register(this);
    }

    protected void changeState(Class<? extends ControllerState> stateType) {
        exit();
        ControllerState newState = controller.changeState(stateType);
        newState.entry();
    }

    /**
     * Entry method of the state.
     */
    protected abstract void entry();

    /**
     * Exit method of the state.
     */
    protected abstract void exit();

    /**
     * Starts new round with a specific amount of players.
     * @param playerCount sets the amount of players.
     * @return true if a new game was started.
     */
    public boolean newGame(int playerCount) {
        System.err.println("You can't start a new game right now.");
        return false;
    }

    /**
     * Starts new round with a specific amount of players.
     * @return true if the game was aborted.
     */
    public boolean abortGame() {
        System.err.println("You can't abort a game right now.");
        return false;
    }

    /**
     * Method for the view to call if a user places a tile.
     * @param x is the x coordinate.
     * @param y is the y coordinate.
     * @return true if tile was placed.
     */
    public boolean placeTile(int x, int y) {
        System.err.println("You can't place a tile right now.");
        return false;
    }

    /**
     * Method for the view to call if the user wants to skip a round.
     * @return true if turn was skipped.
     */
    public boolean skip() {
        System.err.println("You can't place a tile right now.");
        return false;
    }

    /**
     * Method for the view to call if a user mans a tile with a Meeple.
     * @return true if Meeple was placed.
     */
    public boolean placeMeeple(GridDirection position) {
        System.err.println("You can't place meeple tile right now.");
        return false;
    }
}