import java.awt.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;


//   Домашнее задание #4. Виталий Тымкив
public class Main2 {
    public static int z = 0;
    public static int path = 0;
    public static int[][] map1 = {
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(" Imput digital min :");
        int x1 = scanner.nextInt();
        System.out.println(" Imput  digital max:");
        int y1 = scanner.nextInt();
        z = x1;
        System.out.println("Колво решений рекурсивно " + homework43r(y1));
        System.out.println(" Imput строку массива :");
        String s1 = scanner.next();
        System.out.println(" Imput  строку для поска последовательности :");
        String s2 = scanner.next();
        homework41(s1, s2);
        homework45();
        homework44(0, 0);
    }

    //    Калькулятор через рекурсию
    public static int homework43r(int y) {
        int f = 0;
        if (y < z) f = 1;
        else if (y % 2 == 0) f = homework43r(y - 1) + homework43r(y / 2);
        else f = homework43r(y - 1);
        return f;
    }

    //  Наибольшая общая подпоследовательность
    public static void homework41(String x, String y) {
        char[] arrmap = x.toCharArray();
        char[] arrstr = y.toCharArray();
        int intmap = arrmap.length + 1;
        int intstr = arrstr.length + 1;
        int[][] arr = new int[intstr][intmap];
        for (int i = 1; i < intstr; i++) {
            for (int j = 1; j < intmap; j++) {
                arr[i][j] = arr[i - 1][j];
                if (arrstr[i - 1] == arrmap[j - 1]) {
                    arr[i][j] = arr[i][j - 1] + 1;
                } else arr[i][j] = Math.max(arr[i][j - 1], arr[i - 1][j]);
            }
        }
        for (int i = 0; i < intstr; i++) {
            for (int j = 0; j < intmap; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    //  Маршруты с препятствием
    public static void homework44(int x, int y) {
        int nmass = 4;
        int[][] arr = new int[nmass][nmass];
        int[][] arrbl = new int[nmass][nmass];
        int flag = 0;
        arrbl[1][0] = 8;
        arrbl[2][0] = 8;
        arrbl[1][2] = 8;
        arrbl[2][2] = 8;
        for (int i = 0; i < arr.length; i++) {
            if (arrbl[0][i] == 8) break;
            else arr[0][i] = 1;
        }

        for (int i = 1; i < arr.length; i++) {
            if (arrbl[i][0] == 8 || flag == 1) {
                arr[i][0] = 0;
                flag = 1;
            } else arr[i][0] = 1;

            for (int j = 1; j < arr.length; j++) {
                if (arrbl[i][j] == 8) {
                    arr[i][j] = 0;
                } else arr[i][j] = arr[i][j - 1] + arr[i - 1][j];
            }
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                System.out.printf("%6d", arr[i][j]);
            }
            System.out.println();
        }
    }

//  Ходы конем по доске
    public static void homework45() {
        if (findPath(0, 0, 0, map1.length)) {
            print();
        } else {
            System.out.println("LoL");
        }
    }

    public static boolean findPath(int row, int column, int index, int N) {
        if (map1[row][column] != 0) {
            return false;
        }
        map1[row][column] = path++;
        if (index == N * N - 1) {
            return true;
        }
        if (canMove(row + 2, column + 1, N)
                && findPath(row + 2, column + 1, index + 1, N)) {
            return true;
        }
        if (canMove(row + 1, column + 2, N)
                && findPath(row + 1, column + 2, index + 1, N)) {
            return true;
        }
        if (canMove(row - 1, column + 2, N)
                && findPath(row - 1, column + 2, index + 1, N)) {
            return true;
        }
        if (canMove(row - 2, column + 1, N)
                && findPath(row - 2, column + 1, index + 1, N)) {
            return true;
        }
        if (canMove(row - 2, column - 1, N)
                && findPath(row - 2, column - 1, index + 1, N)) {
            return true;
        }
        if (canMove(row - 1, column - 2, N)
                && findPath(row - 1, column - 2, index + 1, N)) {
            return true;
        }
        if (canMove(row + 1, column - 2, N)
                && findPath(row + 1, column - 2, index + 1, N)) {
            return true;
        }
        if (canMove(row + 2, column - 1, N)
                && findPath(row + 2, column - 1, index + 1, N)) {
            return true;
        }
        map1[row][column] = 0;
        path--;
        return false;
    }

    public static boolean canMove(int row, int col, int N) {
        if (row >= 0 && col >= 0 && row < N && col < N) {
            return true;
        }
        return false;
    }

    public static void print() {
        for (int i = 0; i < map1.length; i++) {
            for (int j = 0; j < map1.length; j++) {
                System.out.printf("%4d", map1[i][j]);
            }
            System.out.println();
        }
    }
}