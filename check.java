import java.util.*;

public class check {
    private static Scanner scanner=new Scanner(System.in);
    private static Random random=new Random();

    public static void main(String[] args) {
while (true) {  //заицклить игру
    System.out.println("Начало нового раунда:");
    String[][] board = createBoard();
    startGameRoundLoop(board);
}
    }

    public static void startGameRoundLoop(String[][] board) { //кто ходит (ход, печать)//1 цикл

//        boolean q = areCellsTaken(board);
        do {
            makeTurn(board);
            print(board);
            makeBotTurn(board);
            print(board);
            String gameState = checkGameState(board);
            if (!Objects.equals(gameState, "Игра еще не закончена")) {
                System.out.println(gameState);
                return;
            }
        } while (true);
    }
    static  String[][] createBoard(){
        String [][] board=new String[3][3];
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col]=" ";

            }
        }
        return board;
    }
    static void makeTurn(String[][] board){
        int[] input = inputCellCoordinates(board);
        board[input[0]][input[1]]="x";
    }
    static int[]  inputCellCoordinates(String [][] board){
        System.out.println("вставьте 2 числа от 0 до 2");
        do {
            String [] coordinates =scanner.nextLine().split(" ");
            int row= Integer.parseInt(coordinates[0]);
            int col= Integer.parseInt(coordinates[1]);
            if ((row<0 || row>2) || col<0 || col>2) {
                System.out.println("Некорректно");
            } else if (board[row][col]!=" ") {
                System.out.println("cell is busy!");
            } else return new int[]{row, col};
        } while (true);
    }

    static void makeBotTurn(String[][]board){
        int[] randomEmptyCell = getRandomEmptyCell(board);
        board[randomEmptyCell[0]][randomEmptyCell[1]]="o";
    }
    static int[] getRandomEmptyCell(String[][] board){
        do {
            int raw = random.nextInt(3);
            int col = random.nextInt(3);
            if (board[raw][col]!=" "){

            }
            else return new int[]{raw, col};
        } while(true);   }

    static String checkGameState(String[][]board){ //возврат результата
        ArrayList <Integer> sum=new ArrayList<>();
        //проход по строкам
        for (int row = 0; row < 3; row++) {
            int rowSum=0;
            for (int col = 0; col < 3; col++) {
                rowSum+=calculateValue(board[row][col]);
            }
            sum.add(rowSum);
        }
        // проход колонкам
        for (int col = 0; col < 3; col++) {
            int colSum = 0;
            for (int row = 0; row < 3; row++) {
                colSum += calculateValue(board[row][col]);
            }
            sum.add(colSum);
        }
        // проход по левой диагонали
        int leftDiagonalSum = 0;
        for (int i = 0; i < 3; i++) {
            leftDiagonalSum += calculateValue(board[i][i]);
        }
        sum.add(leftDiagonalSum);

        // проход по правой диагонали
        int rightDiagonalSum = 0;
        for (int i = 0; i < 3; i++) {
            rightDiagonalSum += calculateValue(board[i][(3 - 1) - i]);
        }
        sum.add(rightDiagonalSum);

      //проверка суммы
        if (sum.contains(3)) {
            return "Крестики выиграли";
        } else if (sum.contains(-3)) {
            return "нолики выиграли";
        } else if (areCellsTaken(board)) {
            return "Ничья";
        } else {
            return "Игра еще не закончена";
        }
    }
    static int calculateValue(String cellState){//обозначание Х и О
        if (cellState=="x"){
            return 1;
        } else if (cellState=="o") {
            return -1;
        } else return 0;
    }
    static boolean areCellsTaken(String[][]board){//проход по всем значениям. Если все пустые - фолс
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if(board[row][col] == " ")
                    return false;
                else return true;
            }
        }
        return false;
    }
    static void print(String[][]board){
        System.out.println("---------");
        for (int raw = 0; raw < 3; raw++) {
            String line="|";
            for (int col = 0; col < 3; col++) {
                line+=board[raw][col]+ " ";
            }
            line += "|";
            System.out.println(line);
        }
        System.out.println("---------");
    }
}
