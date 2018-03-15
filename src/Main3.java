import java.awt.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Stack;


//   Домашнее задание #5. Виталий Тымкив
public class Main3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(" Imput digital :");
        int x1 = scanner.nextInt();
        homework51(x1);
        homework52(x1);
        System.out.println(" Imput строку массива :");
        String s1 = scanner.next();
        System.out.println(homework53(s1));
    }

    //    Stack Java
    public static void homework51(int y) {
        Stack st1 = new Stack();
        int c;
        while (y != 0) {
            c = y % 2;
            st1.push(new Integer(c));
            y = y / 2;
        }
        System.out.print(" Двоичка : ");
        while (!st1.empty()){
            System.out.print(st1.pop());
        }
        System.out.println();
    }
    //    Stack реализация массив
    public static void homework52(int y) {
        int c;
        ArrayList<Integer> arr = new ArrayList<>();
        while (y != 0) {
            c = y % 2;
            arr.add(0, c);
            y = y / 2;
        }
        System.out.print(" Двоичка массив : ");
        for (Integer x: arr) {
            System.out.print(x);
        }
        System.out.println();
    }
    // скобочная последовательность
    public static boolean homework53(String s) {
        char[] str = s.toCharArray();
        Stack stack = new Stack();
        char check;
        for (char c: str) {
            if (c == '{' || c == '[' || c == '(') {
                stack.push(c);
            } else {
                if (!stack.isEmpty()) {
                    check = (char) stack.pop();
                    if (c == '}' && check != '{') {
                        return false;
                    } else if (c == ']' && check != '[') {
                        return false;
                    } else if (c == ')' && check != '(') {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }

        if (!stack.isEmpty()) {
            return false;
        }

        return true;
    }

    //  стек на базе списка
    public class Stacknew {
        private int[] elements;
        private int size;
        private int current;

        public Stacknew(int size) {
            this.size = size;
            this.elements = new int[this.size];
            this.current = 0;
        }

        public void push(int e) {
            if (elements.length == this.current) {
                throw new ArrayIndexOutOfBoundsException("Stack is overflow");
            } else {
                this.elements[this.current] = e;
                this.current++;
            }
        }

        public int pop() {
            if (this.current == 0) {
                throw new IllegalArgumentException("Can't handle zero-length arrays.");
            } else {
                this.current--;
                return this.elements[this.current];
            }
        }
        public void printstack() {
            while (this.current != 0) {
                System.out.println(this.elements[this.current]);
                this.current++;
            }
        }
    }
    //  очередь
        class Queue {
         int size;
         int head; // голова
         int tail; // хвост
         int[] data;

                 Queue(int size) {
                            data = new int [this.size = size];
                         }

                 void add(int value) {
                             if (++tail == size)
                                     tail = 0;
                             data[tail] = value;
                         }

                 int extract() {
                             if (++head == size)
                                     head = 0;
                             return data[head];
                         }

                 boolean isEmpty() {
                             return head == tail;
                        }
     }
}
