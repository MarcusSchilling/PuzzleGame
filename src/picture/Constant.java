package picture;

import java.awt.*;

public class Constant {


    public static final int ROWS = 3;
    public static final int COLS = 3;


    //general
    public static final int MARGIN = 10;
    public static final int WIDTH_WINDOW = 1000;
    public static final int HEIGHT_WINDOW = 1000;
    public static final Color RESULT = new Color(51, 25, 0, 100);
    public static final String GAME_TITLE = "Puzzle Game";
    public static final String KEY_TO_OPEN_IMAGE_PATH_EXPLORER = "F1";


    //puzzle generator
    //from one to 9 the lower the degree the more easy the game gets
    public static final int DEGREE_OF_DIFFICULTY = 3;


    //moves field
    public static final int WIDTH_MOVE_FIELD = 50;
    public static final int HEIGHT_MOVE_FIELD = 50;
    public static final int X_MIN_POS_MOVE_FIELD = 10;
    public static final int Y_MIN_POS_MOVE_FIELD = 10;

    //Field
    public static final int HEIGHT_FIELD = 700;
    public static final int WIDTH_FIELD = 700;
    public static final int MIN_TOP_POSITION_FIELD = 112;
    public static final int MIN_LEFT_POSITION_FIELD = 106;
    public static final int MAX_TOP_POSITION_FIELD = MIN_TOP_POSITION_FIELD + ((HEIGHT_FIELD / COLS) * (COLS));
    public static final int MAX_LEFT_POSITION_FIELD = MIN_LEFT_POSITION_FIELD + ((WIDTH_FIELD / ROWS) * (ROWS));

    //TextField path
    public static final int HEIGHT_TEXT_FIELD = 30;
    public static final int WIDTH_TEXT_FIELD = 400;

    //Button
    public static final int BUTTON_HEIGHT = 50;
    public static final int MIN_TOP_BUTTON_POSITION = 73;
    public static final int MIN_LEFT_BUTTON_POSITION = (MIN_LEFT_POSITION_FIELD - (MARGIN + BUTTON_HEIGHT));

    public static final String BUTTON_TEXT_UP = "up";
    public static final String BUTTON_TEXT_DOWN = "down";
    public static final String BUTTON_TEXT_LEFT = "<";
    public static final String BUTTON_TEXT_RIGHT = ">";

    //restart button
    public static final int WIDTH_RESTART_BUTTON = 200;
    public static final int HEIGHT_RESTART_BUTTON = 40;
    public static final String RESTART_BUTTON_TEXT = "Restart";


    public static final String YOU_WON_IN_MOVES = "Du hast gewonnen in %d Spielzügen";
    public static final String YOU_WON_IN_ONE_MOVE = "Du hast gewonnen in einem Spielzug";
    public static final String YOU_WON_IN_MIN_POSSIBLE_MOVES = "Du hast in der minimalen Anzahl an Zügen gewonnen.";


    // label min Moves test show
    public static final int WIDTH_MIN_MOVES_LABEL = 50;
    public static final int HEIGHT_MIN_MOVES_LABEL = 50;
}
