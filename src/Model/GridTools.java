package Model;

/**
 * GridTools class contains all static method to check if the grid of the game has a winner or not.
 * @author lngeth
 */
public class GridTools {
    /**
     * Check in the Grid if there is a the same symbol on the diagonal
     * @param grid the 2D array of the game
     * @return true if there is 3 same symbol on diagonal, false if not
     */
    public static boolean isDiagonal(Grid grid){
        String pattern;
        int count;

        if (grid.getTab()[0][0] != null){
            count = 0;
            pattern = grid.getTab()[0][0];
            for (int diag = 0; diag < grid.getTab().length; diag++){
                if (grid.getTab()[diag][diag] != null && grid.getTab()[diag][diag].equals(pattern)){
                    count++;
                }
            }
            if (count == 3){
                return true;
            }
        }
        if (grid.getTab()[2][0] != null){
            count = 0;
            pattern = grid.getTab()[2][0];
            int col = 2;
            for (int line = 0; line < grid.getTab().length; line++){
                if (grid.getTab()[col][line] != null && grid.getTab()[col][line].equals(pattern)){
                    count++;
                }
                col--;
            }
            if (count == 3){
                return true;
            }
        }
        return false;
    }

    /**
     * Check if there is 3 same symbol on each column of the Grid
     * @param grid the 2D arrays of the game
     * @return true if 3 same symbol on a column, false if not
     */
    public static boolean isColumn(Grid grid){
        String pattern;
        int count;
        for (int col = 0; col < grid.getTab().length; col++){
            if (grid.getTab()[col][0] != null){
                count = 0;
                pattern = grid.getTab()[col][0];
                for (int line = 0; line < grid.getTab().length; line++){
                    if (grid.getTab()[col][line] != null && grid.getTab()[col][line].equals(pattern)){
                        count++;
                    }
                }
                if (count == 3){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Check if there is 3 same symbol on each line of the Grid
     * @param grid the 2D arrays of the game
     * @return true if 3 same symbol on a line, false if not
     */
    public static boolean isLine(Grid grid){
        String pattern;
        int count;
        for (int line = 0; line < grid.getTab().length; line++){
            if (grid.getTab()[0][line] != null){
                count = 0;
                pattern = grid.getTab()[0][line];
                for (int col = 0; col < grid.getTab().length; col++){
                    if (grid.getTab()[col][line] != null && grid.getTab()[col][line].equals(pattern)){
                        count++;
                    }
                }
                if (count == 3){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Check if each box of the Grid has a drawning
     * @param grid the 2D arrays of the game
     * @return true if all boxes of the grid has a drawing, false if not
     */
    public static boolean isPat(Grid grid) {
        for (int col = 0; col < grid.getTab().length ; col++) {
            for (int line = 0; line < grid.getTab().length ; line++) {
                if (grid.getTab()[col][line] == null) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Check if there is a winner in the Grid
     * @param grid the 2D arrays of the game
     * @return true if there is a winner, false if not
     */
    public static boolean isWinning(Grid grid){
        return isLine(grid) || isDiagonal(grid) || isColumn(grid);
    }
}