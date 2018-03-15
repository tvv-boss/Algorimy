import java.util.*;

//   Домашнее задание #3. Виталий Тымкив
public class Main1 {
    public static int k = 10000;
    public static int [] map = new int[k];

    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println(" Imput digital  :");
//        k = scanner.nextInt();
        homework31();
        homework32();
        homework33();
        homework35();
        homework34();
//        sort(E[] map);
    }
    // Стандартная пузырьковая
    public static void homework31() {
        long tworkB= System.nanoTime();
        randommap();
        int count = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length-1; j++) {
                int var = map[j];
                if (map[j] > map[j+1]){
                    map[j] = map[j+1];
                    map[j+1] = var;
                    count++;
                }
            }
        }
//        for (int c: map) {
//            System.out.print(c + " ");
//        }
        System.out.println();
        System.out.println("Колво итераций пузырь - " + count);
        System.out.println("Time " + (System.nanoTime()- tworkB));
//        System.out.println();
    }
    // Сортируемый массив
    public static void randommap() {
//        Random ran = new Random();
        for (int i = 0; i < map.length; i++) {
            map[i] = (int) (Math.random()*k*10);
        }
    }
    // Улучшенная сортировка
    public static void homework32() {
        long tworkB= System.nanoTime();
        randommap();
        int count = 0;
        for (int i = 0; i < map.length; i++) {
            int flag = 1;
            for (int j = 0; j < map.length- i-1; j++) {
                int var = map[j];
                if (map[j] > map[j+1]){
                    map[j] = map[j+1];
                    map[j+1] = var;
                    count++;
                    flag = 0;
                }
            }
            if (flag == 1) break;
        }
//        for (int c: map) {
//            System.out.print(c + " ");
//        }
        System.out.println();
        System.out.println("Колво итераций улучшенный пузырь - " + count);
        System.out.println("Time " + (System.nanoTime()- tworkB));
//        System.out.println();
    }
    // шейкер
    public static void homework33() {
        long tworkB= System.nanoTime();
        randommap();
        int count = 0;
        for (int i = 0; i < map.length; i++) {
            int flag = 1;
            for (int j = i, a = map.length - i -1; j < map.length- i - 1; j++, a--) {
                int var = map[j];
                int var1 = map[a];
                if (map[j] > map[j+1]){
                    map[j] = map[j+1];
                    map[j+1] = var;
                    count++;
                    flag = 0;
                }
                if (map[a] < map[a-1]){
                    map[a] = map[a-1];
                    map[a-1] = var1;
                    count++;
                    flag = 0;
                }
            }
            if (flag ==1) break;
        }
//        for (int c: map) {
//            System.out.print(c + " ");
//        }
        System.out.println();
        System.out.println("Колво итераций шейкер - " + count);
        System.out.println("Time " + (System.nanoTime()- tworkB));
//        System.out.println();
    }
    // Бинарные сортировки с различной реализацией
    public static  void homework34() {
//       #1 вариант по методичке
        int random = (int) (Math.random()*k);
        int value = map[random];
        long tworkB= System.nanoTime();
        int count = 0;
        int aL = 0;
        int aR = map.length-1;
        int num = aL + (aR - aL)/2;
        while (aL <= aR &&  map[num] != value){
            if (map[num] < value) aL = num + 1;
            else aR = num - 1;
            count++;
            num = aL + (aR - aL) / 2;
        }
        if (map[num] == value){
            System.out.println(" Index " + num + " Digital " + value + " Count " + count);
        }else {
            System.out.println(" Fail digital " + " Count " + count);
        }
        System.out.println("Time " + (System.nanoTime()- tworkB));

        //  # 2 вариант по теории
        tworkB= System.nanoTime();
        count = 0;
        int low = 0, high = map.length;
        num = 0;
        while (low < high) {
            num = (low + high)/2;
            if (value == map[num]) {
                break;
            } else {
                if (value < map[num]) {
                    high = num;
                } else {
                    low = num + 1;
                }
                count++;
            }
        }
        if (map[num] == value){
            System.out.println(" Index " + num + " Digital " + value + " Count " + count);
        }else {
            System.out.println(" Fail digital " + " Count " + count);
        }
        System.out.println("Time " + (System.nanoTime()- tworkB));
    }
    //
    public static void homework35() {
//        сортировка системная от Java
        long tworkB= System.nanoTime();
        randommap();
        Arrays.sort(map);
//        for (int c: map) {
//            System.out.print(c + " ");
//        }
        System.out.println();
        System.out.println("Time сортировки Java " + (System.nanoTime()- tworkB));
//        System.out.println();

//      сортировка по Шеллу
        tworkB= System.nanoTime();
        randommap();
        int count = 0;
        int i, j, k, h, m=0, b=map .length;
        int[] d = { 1, 4, 10, 23, 57, 145, 356, 911, 1968, 4711, 11969, 27901,
                84801, 213331, 543749, 1355339, 3501671, 8810089, 21521774,
                58548857, 157840433, 410151271, 1131376761, 2147483647 };
        while (d[m] < b) ++m;
        while (--m >= 0){
            k = d[m];
            for (i = k; i < b; i++){     // k-сортировка
                j = i;
                h= map[i];
                while ((j >= k) && (map[j-k] > h)){
                    map[j] = map[j-k];
                    j -= k;
                    count++;
                }
                map[j] = h;
                count++;
            }
        }
//        for (int c: map) {
//            System.out.print(c + " ");
//        }
        System.out.println();
        System.out.println("Колво итераций Шелла - " + count);
        System.out.println("Time " + (System.nanoTime()- tworkB));
//        System.out.println();
    }
}

