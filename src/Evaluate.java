/**
 * Used to evaluate a given position.
 */
public class Evaluate {

    private final int size;
    private final int tilesToWin;
    private final int maxLevels;
   private char[][] gameBoard;

    /**
     * Constructor initializing instance variables and setting all squares to empty.
     * @param size side length of board
     * @param tilesToWin number of connected tiles to win
     * @param maxLevels max level the engine looks
     */
    public Evaluate (int size, int tilesToWin, int maxLevels){
        this.size = size;
        this.tilesToWin = tilesToWin;
        this.maxLevels = maxLevels;
        this.gameBoard = new char[size][size];
        // Initialize all squares to empty
        for (int i = 0; i<size; i++){
            for (int j= 0; j < size; j ++){
                this.gameBoard[i][j] = 'e';
            }
        }
    }

    /**
     * Creates a new dictionary
     * @return new dictionary of size 9887
     */
    public Dictionary createDictionary(){
        return new Dictionary(9887);
    }

    /**
     * Displays the current position as string and determines if the position is in the dictionary or not
     * @param dict dictionary
     * @return boolean of if position exists in dictionary
     */
    public Record repeatedState(Dictionary dict){
        StringBuilder current_board = new StringBuilder();

        for (int i = 0; i<size; i++){
            for (int j= 0; i < size; i ++){
                current_board.append(gameBoard[i][j]); // converts board to string
            }
        }
        return dict.get(current_board.toString());
    }

    /**
     * Displays position as string and adds it to dictionary if it isn't already there.
     */

    public void insertState(Dictionary dict, int score, int level){
        StringBuilder current_board = new StringBuilder();

        for (int i = 0; i<size; i++){
            for (int j= 0; i < size; i ++){
                current_board.append(gameBoard[i][j]); // converts board to string
            }
        }
        if (dict.get(current_board.toString()) == null){ // adds to dictionary if key doesn't already exit
            dict.put(new Record(current_board.toString(), score, level));
        }
    }

    /**
     * Stores the symbol when given a position
     */
    public void storePlay(int row, int col, char symbol){gameBoard[row][col] = symbol;}

    /**
     * checks if square at position is empty
     * @return whether any empty squares exist
     */
    public boolean squareIsEmpty (int row, int col){return gameBoard[row][col] == 'e';}

    /**
     * Checks if position at square is occupied by computer
     */
    public boolean tileOfComputer (int row, int col){return gameBoard[row][col] == 'c';}

    /**
     * Checks of position at square is occupied by human
     */
    public boolean tileOfHuman (int row, int col){return gameBoard[row][col] == 'h';}

    /**
     * evaluates whether one has won the position
     * @param symbol human or computer
     * @return true if won, false if not
     */
    public boolean wins (char symbol){

        // Horizontal check
        for (int i = 0; i<size; i++) { // i is the row
            for (int j = 0; j < size - (tilesToWin-1); j++) { // j is the column
                // it is unnecessary to iterate throughout the entire board as some combinations are impossible
                int count = 0;
                for (int k = 0; k< tilesToWin; k++) { // loops through relevant squares to find winning combination
                    if (gameBoard[i][j + k] == symbol) count++;
                    else break;
                    if (count == tilesToWin) return true;
                }
            }
        }
        // Vertical check, slightly modified version of horizontal check
        for (int j = 0; j<size; j++) {
            for (int i = 0; i < size - (tilesToWin-1); i++) {
                int count = 0;
                for (int k = 0; k< tilesToWin; k++) {
                    if (gameBoard[i+k][j] == symbol) count++;
                    else break;
                    if (count == tilesToWin) return true;
                }
            }
        }

        // Diagonal check in [-1,1] direction
        for (int i = tilesToWin-1; i<size; i++) {
            for (int j = 0; j < size - (tilesToWin-1); j++) { //
                // only checks a smaller square from the board as all
                // combinations can be derived from those starting positions.
                int count = 0;
                for (int k = 0; k< tilesToWin; k++) {
                    if (gameBoard[i-k][j+k] == symbol) count++;
                    else break;
                    if (count == tilesToWin) return true;
                }
            }
        }
        //Diagonal check in [-1,-1] direction
        for (int i = tilesToWin-1; i<size; i++) {
            for (int j = tilesToWin-1; j < size; j++) {
                int count = 0;
                for (int k = 0; k<tilesToWin; k++) {
                    if (gameBoard[i-k][j-k] == symbol) count++;
                    else break;
                    if (count == tilesToWin) return true;
                }
            }
        }

        return false;
    }

    /**
     * Checks if position is a draw
     * @return true if any empty squares exist on board
     */
    public boolean isDraw(){
        for (int i = 0; i<size; i++){
            for (int j= 0; i < size; i ++){
                if (gameBoard[i][j] == 'e') return false;
            }
        }
        return true;
    }

    /**
     * used to find the state of the board.
     * @return int depending on the state of the board
     */
    public int evalBoard(){
        if (wins('c')) return 3;
        else if ((wins('h'))) return 0;
        else if (isDraw()) return 2;
        else return 1;
        }
    }
