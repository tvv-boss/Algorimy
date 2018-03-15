import java.util.ArrayList;
import java.util.Scanner;

//   Домашнее задание #2. Виталий Тымкив
public class Main {
    public static int k = 0;
    public static int n = 0;
    public static int m = 0;
    public static int z = 0;
    public static int [][] map = {
            {0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 1, 0, 0, 0},
            {0, 1, 1, 0, 0, 0, 0, 1, 1, 0},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {0, 1, 1, 0, 0, 0, 0, 1, 1, 0},
            {0, 0, 0, 1, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 1, 1, 0, 0, 0, 0}
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(" Imput digital min :");
        int x1 = scanner.nextInt();
        System.out.println(" Imput  digital max:");
        int y1 = scanner.nextInt();
        z = x1;
        homework23(x1, y1);
        System.out.println("Колво решений рекурсивно " + homework23r(y1));
        homework21r(y1);
        System.out.println(" Двоичное " + y1 + " Десятичного ");
        System.out.println("");
        homework21(4, 4 );
        TowerOfHanoi ( 1 , 2 , 3 , 4 );
        System.out.println(" Колво ходов " + m);
        int x = 3;
        int y = 8;
//        System.out.println("Степень рекурс 1 " + Integer.toString(pow(x, y)));
        System.out.println("Степень рекурс 2 " + Integer.toString(pow1(x, y)));
        System.out.println("Степень рекурс 3 быстро " + Double.toString(pow2(x, y)));
        System.out.println("Быстро в степень standart " + homework16(x, y));
    }
//    Калькулятор через массив
    public static void homework23(int x1, int y1) {
        ArrayList<Integer> arr = new ArrayList<>();
        ArrayList<Integer> arrvar = new ArrayList<>();
        for (int i = 0; i < y1-x1+1; i++) {
            arr.add(i + x1);
            arrvar.add(0);
        }
        int m = 2;
        arrvar.set(0, 1);
        if (arr.get(0)% m == 0) {
            arrvar.set(0, arrvar.get(0)+ 1);
        }
        for (int k = 1; k < arr.size(); k++) {
            arrvar.set(k, arrvar.get(k)+ arrvar.get(k-1));
            if (arr.get(k)% m == 0 && k >= m) {
                int in = arr.get(k)/ m;
                int index = arr.indexOf(in);
                arrvar.set(k, arrvar.get(k)+ arrvar.get(index));
            }else  if(arr.get(k)% m == 0 && k < m )arrvar.set(k, arrvar.get(k)+ arrvar.get(k - 1));
        }
        for (int i = 0; i < arr.size(); i++) {
            System.out.print(arr.get(i) + " ");
        }
        System.out.println("");
        for (int i = 0; i < arr.size(); i++) {
            System.out.print(arrvar.get(i) + " ");
        }
        System.out.println("");
        System.out.println("Колво решений " + arrvar.get(arrvar.size()-1));
    }
//    Калькулятор через рекурсию
    public static int homework23r(int y) {
//        1.	Если N - любое число, не делящееся на 2, то KN=KN-1
//        2.	Если N - число, делящееся на 2, то KN=KN-1+KN/2.
        int f = 0;
        if (y  <  z) f = 1;
        else if (y % 2 == 0) f = homework23r(y-1)+homework23r(y/2);
        else  f =homework23r(y-1);
        return f;
    }
// Перевод десятичного в двоичное
    public static int homework21r(int y) {
        int c = y % 2;
        y = y /2;
        if (y > 0) {
//            System.out.print(c);
            homework21r(y);
        }
        System.out.print(c);
        return c;
    }
    // Возведение в степень без рекурсии ускоренно
    public static long  homework16(int x, int n) {
        long result = 1;
        while(n > 0) {
            if (n % 2 == 0) {
                n /= 2;
                x *= x;
            }else {
                n--;
                result *= x;
            }
        }
        return result;
    }
    // Заполнение области массива с рекурсией
    public static  int homework21( int x , int y) {
        for (int i = 0; i < map.length ; i++) {
            for (int j = 0; j < map.length ; j++) {
                System.out.print(map[i][j]+ " ");
            }
            System.out.println("");
        }
        System.out.println("Колво рекурсов -------   " + n);
        n++;
            if ( map [ x ][ y ] == 0){
                k ++;
                map [ x ][ y ] = 8;
                homework21 ( x , y - 1 );
                homework21 ( x - 1 , y );
                homework21 ( x , y + 1 );
                homework21 ( x + 1 , y );
            }else if (k >= 100) return 0;

        return 0;
    }
    // Ханойская башня практика
    public static void TowerOfHanoi(int from , int to , int other , int n) {
            m++;
            if ( n > 1 ) TowerOfHanoi ( from , other , to , n - 1 );
            System.out.println( from + " " + to );
            if ( n > 1 ) TowerOfHanoi ( other , to , from , n - 1 );
    }
//    public static int pow(int x, int y) {
//        if (y > 1)
//            return x * pow(x, y - 1);
//        else if (y == 1)
//            return x;
//        else if (y == 0)
//            return 1;
//        else
//            return 0;
//    }
// Возведение в степень c рекурсией
    public static int pow1(int x, int y) {
        if (y == 0) return 1;
        else return x*pow1(x,y-1);
    }
    // Возведение в степень c рекурсией ускоренно
    public static double pow2(int x, int y) {
        if(y > 0) {
            if(y % 2 == 1) {
                return x * pow2(x, y - 1);
            }else {
                double res = pow2(x, y / 2);
                return res * res;
            }
        }else return 1;
    }
}
