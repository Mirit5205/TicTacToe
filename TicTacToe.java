import java.util.*;

public class TicTacToe {

    public static void main(String[] args) {
        char[][] gameBoard = initialGameBoard();
        List<Integer> takenSpots = new ArrayList<Integer>();
        printGameBoard(gameBoard);
        playGame(takenSpots, gameBoard);

    }

    public static char[][] initialGameBoard() {
        char[][] gameBoard = { {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '} };
        return gameBoard;
    }

    public static void printGameBoard(char[][] gameBoard) {
        for (char[] c : gameBoard) {
            System.out.println(c);
        }
    }

    public static void chooseSpot(char[][] gameBoard, int spot, String user) {
        char symbol = ' ';

        if (user.equals("user")) {
            symbol = 'X';
        }
        if (user.equals("cpu")) {
            symbol = 'O';
        }


        switch (spot) {
            case 1: gameBoard[0][0] = symbol;
                break;
            case 2: gameBoard[0][2] = symbol;
                break;
            case 3: gameBoard[0][4] = symbol;
                break;
            case 4: gameBoard[2][0] = symbol;
                break;
            case 5: gameBoard[2][2] = symbol;
                break;
            case 6: gameBoard[2][4] = symbol;
                break;
            case 7: gameBoard[4][0] = symbol;
                break;
            case 8: gameBoard[4][2] = symbol;
                break;
            case 9: gameBoard[4][4] = symbol;
                break;
            default:
        }
    }

    public static boolean isVerticalWin(char[][] gameBoard) {
        //check wining in first column
        if (gameBoard[0][0] == gameBoard[2][0] &&
                gameBoard[2][0] == gameBoard[4][0] &&
                !isPlaceEmpty(gameBoard[0][0])) {
            return true;
        }
        //check wining in 2nd column
        else if (gameBoard[0][2] == gameBoard[2][2] &&
                gameBoard[2][2] == gameBoard[4][2] &&
                !isPlaceEmpty(gameBoard[0][2])) {
            return true;
        }
        //check wining in 3rd column
        else if (gameBoard[0][4] == gameBoard[2][4] &&
                gameBoard[2][4] == gameBoard[4][4] &&
                !isPlaceEmpty(gameBoard[0][4])) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isHorizontalWin(char[][] gameBoard) {
        //check wining in first row
        if (gameBoard[0][0] == gameBoard[0][2] &&
                gameBoard[0][2] == gameBoard[0][4] &&
                !isPlaceEmpty(gameBoard[0][0])) {
            return true;
        }
        //check wining in 2nd row
        else if (gameBoard[2][0] == gameBoard[2][2] &&
                gameBoard[2][2] == gameBoard[2][4] &&
                !isPlaceEmpty(gameBoard[2][0])) {
            return true;
        }
        //check wining in 3rd row
        else if (gameBoard[4][0] == gameBoard[4][2] &&
                gameBoard[4][2] == gameBoard[4][4] &&
                !isPlaceEmpty(gameBoard[4][0])) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isCrossWining(char[][] gameBoard) {
        if (gameBoard[0][0] == gameBoard[2][2] &&
                gameBoard[2][2] == gameBoard[4][4] &&
                !isPlaceEmpty(gameBoard[0][0])) {
            return true;
        }

        else if (gameBoard[0][4] == gameBoard[2][2] &&
                gameBoard[2][2] == gameBoard[4][0] &&
                !isPlaceEmpty(gameBoard[0][4])) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isWining(char[][] gameBoard) {
        return isVerticalWin(gameBoard) ||
                isHorizontalWin(gameBoard) ||
                isCrossWining(gameBoard);
    }

    public static boolean isPlaceEmpty(char c) {
        return c == ' ';
    }

    public static boolean isDraw(List<Integer> takenSpots) {
          if (takenSpots.size() == 9) {
              return true;
          } else {
              return false;
          }
    }
    public static int getPosition(List<Integer> takenSpots, String s) {
        int spot;
        //cpu turn
        if (s.equals("cpu")) {
            Random rand = new Random();
            spot = rand.nextInt(9) + 1;
            //check if the spot is valid
            while (takenSpots.contains(spot)) {
                spot = rand.nextInt(9) + 1;
            }
            return spot;
        } else {
            //user turn
            Scanner sc = new Scanner(System.in);
            spot = sc.nextInt();
            //check if the spot is valid
            while (takenSpots.contains(spot)) {
                System.out.println("the spot you choose is already taken, please choose again:");
                spot = sc.nextInt();
            }
            return spot;
        }
    }
    public static void playGame(List<Integer> takenSpots, char[][] gameBoard) {
        while (true) {

            //ask for input from the user
            System.out.println("choose one number of your valid options:");
            int userSpot = getPosition(takenSpots, "user");

            //add the chosen spot to the list of taken spots
            takenSpots.add(userSpot);
            chooseSpot(gameBoard, userSpot, "user");
            printGameBoard(gameBoard);

            if (isWining(gameBoard)) {
                System.out.println("congratulations, you Win!");
                break;
            }

            if (takenSpots.size() == 9) {
                System.out.println("Nice game that end in draw");
                break;
            }

            //choose an empty spot
            int cpuSpot = getPosition(takenSpots, "cpu");

            //add the chosen spot to the list of taken spots
            takenSpots.add(cpuSpot);
            chooseSpot(gameBoard, cpuSpot, "cpu");
            printGameBoard(gameBoard);

            if (isWining(gameBoard)) {
                System.out.println("oh well, better luck next time");
                break;
            }

            if (takenSpots.size() == 9) {
                System.out.println("Nice game that end in draw");
                break;
            }
        }
    }
}
