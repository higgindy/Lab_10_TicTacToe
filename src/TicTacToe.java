import java.util.Scanner;

public class TicTacToe
{
    private static final int ROW = 3;

    private static final int COL = 3;

    private static final String[][] board = new String[ROW][COL];

    public static void main(String[] args)
    {
        boolean playAgain;
        Scanner scanner = new Scanner(System.in);

        do
        {
            clearBoard();
            String currentPlayer = "X";

            while (true)
            {
                display();
                System.out.println("Player " + currentPlayer + ", enter your move:");
                int row = SafeInput.getRangedInt(scanner, "Enter the row: ", 1, 3);
                int col = SafeInput.getRangedInt(scanner, "Enter the column: ", 1, 3);
                row--;
                col--;

                if (isValidMove(row, col))
                {
                    board[row][col] = currentPlayer;


                    if (isWin(currentPlayer))
                    {
                        display();
                        System.out.println("Player " + currentPlayer + " wins!");
                        break;
                    } else if (isTie())
                    {
                        display();
                        System.out.println("It's a tie!");
                        break;
                    }


                    currentPlayer = (currentPlayer.equals("X")) ? "O" : "X";
                } else
                {
                    System.out.println("Invalid move. Try again.");
                }
            }

            playAgain = SafeInput.getYNConfirm(scanner, "Would you like to play again? Input Y or N: ");
        } while (playAgain);

        scanner.close();
    }

    private static void clearBoard()
    {
        for (int i = 0; i < ROW; i++)
        {
            for (int j = 0; j < COL; j++)
            {
                board[i][j] = " ";
            }
        }
    }

    private static void display()
    {
        System.out.println("-------------");
        for (int i = 0; i < ROW; i++)
        {
            for (int j = 0; j < COL; j++)
            {
                System.out.print("| " + board[i][j] + " ");
            }
            System.out.println("|");
            System.out.println("-------------");
        }
    }

    private static boolean isValidMove(int row, int col)
    {
        if (row >= 0 && row < ROW && col >= 0 && col < COL)
        {
            return board[row][col].equals(" ");
        }
        return false;
    }

    private static boolean isWin(String player)
    {
        return isRowWin(player) || isColWin(player) || isDiagonalWin(player);
    }

    private static boolean isColWin(String player)
    {
        for (int i = 0; i < COL; i++)
        {
            if (board[0][i].equals(player) && board[1][i].equals(player) && board[2][i].equals(player))
            {
                return true;
            }
        }
        return false;
    }

    private static boolean isRowWin(String player)
    {
        for (int i = 0; i < ROW; i++)
        {
            if (board[i][0].equals(player) && board[i][1].equals(player) && board[i][2].equals(player))
            {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagonalWin(String player)
    {
        return (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player))
                || (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player));
    }

    private static boolean isTie()
    {
        for (int i = 0; i < ROW; i++)
        {
            for (int j = 0; j < COL; j++)
            {
                if (board[i][j].equals(" "))
                {
                    return false;
                }
            }
        }
        return true;
    }
}
