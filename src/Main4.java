import java.awt.*;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;


//   Домашнее задание #6. Виталий Тымкив
public class Main4 {
    public static int z = 0;
    public static int path = 0;

    public static void main(String[] args) throws UnsupportedEncodingException {
        Scanner scanner = new Scanner(System.in);
//        System.out.println(" Imput digital min :");
//        int x1 = scanner.nextInt();
//        System.out.println(" Imput  digital max:");
//        int y1 = scanner.nextInt();
//        z = x1;
//        System.out.println("Колво решений рекурсивно " + homework43r(y1));
        System.out.println(" Imput строку  :");
        String s1 = scanner.next();
        homework61(s1);
//        System.out.println(" Imput  строку для поска последовательности :");
//        String s2 = scanner.next();
//        homework41(s1, s2);
//        homework45();
//        homework44(0, 0);
    }

    //
    public static void homework61(String y) throws UnsupportedEncodingException {
        char[] arr = y.toCharArray();
        String Str = new String(arr);
        byte[] bytes = Str.getBytes("ASCII");
        int sum1 = 0;
        int sum2 = 0;

        for (int i = 0; i < arr.length; i++) {
            String val = Character.toString(arr[i]);
            System.out.println(" #" + (i + 1) + " Хеш код 1 " + val.hashCode());
            System.out.println("  Хеш код 2 " + (bytes[i] << 2));
            sum1 += val.hashCode();
            sum2 += bytes[i] << 2;
        }
        System.out.println(" Summa 1 " + sum1 +" Summa 2 " + sum2);
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
}
