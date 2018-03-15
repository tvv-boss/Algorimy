import java.util.*;

//   Домашнее задание #8. Виталий Тымкив. Сложные сортировки
public class Main8 {
    public static int k = 100;
    public static int[] map1 = new int[k];
    public static int m = 10000;
    public static int[] map2 = new int[m];
    public static int n = 100000;
    public static int[] map3 = new int[n];

    public static void main(String[] args) {
        int[] arr1;
        map1 = randommap(map1);
        map2 = randommap(map2);
        map3 = randommap(map3);
        homework81(map1);
        homework81(map2);
        homework81(map3);

        long tworkB = System.nanoTime();
        arr1 = mergeSort(map1);
        System.out.println(" Cлиянием Сортировка ------" + arr1.length);
//        for (int c : arr1) {
//            System.out.print(c + " ");
//        }
        System.out.println();
        System.out.println(" Time " + (System.nanoTime() - tworkB));
        tworkB = System.nanoTime();
        arr1 = mergeSort(map2);
        System.out.println(" Cлиянием Сортировка ------" + arr1.length);
//        for (int c : arr1) {
//            System.out.print(c + " ");
//        }
        System.out.println();
        System.out.println(" Time " + (System.nanoTime() - tworkB));
        tworkB = System.nanoTime();
        arr1 = mergeSort(map3);
        System.out.println(" Cлиянием Сортировка ------" + arr1.length);
//        for (int c : arr1) {
//            System.out.print(c + " ");
//        }
        System.out.println();
        System.out.println(" Time " + (System.nanoTime() - tworkB));
        tworkB = System.nanoTime();
        arr1 = quickSort(map1, 0 , map1.length-1);
        System.out.println(" Быстрая Сортировка ------" + arr1.length);
//        for (int c : arr1) {
//            System.out.print(c + " ");
//        }
        System.out.println();
        System.out.println(" Time " + (System.nanoTime() - tworkB));
        tworkB = System.nanoTime();
        arr1 = quickSort(map2, 0 , map2.length-1);
        System.out.println(" Быстрая Сортировка ------" + arr1.length);
//        for (int c : arr1) {
//            System.out.print(c + " ");
//        }
        System.out.println();
        System.out.println(" Time " + (System.nanoTime() - tworkB));
        tworkB = System.nanoTime();
        arr1 = quickSort(map3, 0 , map3.length-1);
        System.out.println(" Быстрая Сортировка ------" + arr1.length);
//        for (int c : arr1) {
//            System.out.print(c + " ");
//        }
        System.out.println();
        System.out.println(" Time " + (System.nanoTime() - tworkB));
    }

    public static void homework81(int[] array) {
        long tworkB = System.nanoTime();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int element : array) {
            if (element < min) min = element;
            if (element > max) max = element;
        }
        int[] buckets = new int[max - min + 1];
        for (int element : array) {
            buckets[element - min]++;
        }
        int arrayIndex = 0;
        for (int i = 0; i < buckets.length; i++) {
            for (int j = buckets[i]; j > 0; j--) {
                array[arrayIndex++] = i + min;
            }
        }
        System.out.println(" Сортировка подсчетом ------" + array.length);
//        for (int c : array) {
//            System.out.print(c + " ");
//        }
        System.out.println();
        System.out.println(" Time " + (System.nanoTime() - tworkB));
    }

    //cортировка слиянием
    public static int[] mergeSort(int[] array) {
//        long tworkB = System.nanoTime();
        if (array.length <= 1) {
            return array;
        }

        int mid = array.length / 2;
        int[] left = new int[mid];
        int[] right = new int[mid + array.length % 2];

        int j = 0;
        for (int i = 0; i < array.length; i++) {
            if (i < array.length / 2) {
                left[i] = array[i];
            } else {
                right[j++] = array[i];
            }
        }
        return Merge(mergeSort(left), mergeSort(right));
    }

    public static int[] Merge(int[] left, int[] right) {
        int a = 0, b = 0;
        int[] merged = new int[left.length + right.length];
        //забиваем отсортированный массив из левой и правой частей
        for (int i = 0; i < left.length + right.length; i++) {
            //поочередно берем меньший член из крайних левого и правого
            if (b < right.length && a < left.length)
                if (left[a] > right[b] && b < right.length)
                    merged[i] = right[b++];
                else
                    merged[i] = left[a++];
            else if (b < right.length)
                merged[i] = right[b++];
            else
                merged[i] = left[a++];
        }
        return merged;
    }

    //быстрая сортировка
    public static int[] quickSort(int[] array, int low, int high) {
        int i = low;
        int j = high;
        //выбираем середину в качестве опорного элемента
        int mid = array[(low + high) / 2];
        //сдвигаем меньшие члены влево от опорного, а большие вправо
        do {
            while (array[i] < mid)
                ++i;
            while (mid < array[j])
                --j;
            if (i <= j) {
                if (i < j) {
                    int t = array[i];
                    array[i] = array[j];
                    array[j] = t;
                }
                ++i;
                --j;
            }
        } while (i <= j);

        //теперь также сортируем левую и правую часть
        if (low < j) quickSort(array, low, j);
        if (i < high) quickSort(array, i, high);
        return array;
    }

     // Рандомим  массив
    public static int[] randommap(int[] arr) {
        int[] temparr = arr;
        for (int i = 0; i < temparr.length; i++) {
            temparr[i] = (int) (Math.random() * temparr.length * 10);
        }
        return temparr;
    }
}


