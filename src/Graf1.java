import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

// Домашняя работа №7 Тымкив Виталий Графы, матрицы смежности

public class Graf1 {
//    public static int count;
    public static int currentIndex = -1;

        public static Integer next(String numbers[]) {
        ++currentIndex;
        while (currentIndex < numbers.length
                && numbers[currentIndex].equals(""))
            ++currentIndex;
        return currentIndex < numbers.length ? Integer
                .parseInt(numbers[currentIndex]) : null;
    }

    public static void main(String[] args) throws IOException {
        // запись матрицы из файла
        FileInputStream inFile = new FileInputStream("C:/soft/JAVA/Algorimy/src/1.txt");
        byte[] str = new byte[inFile.available()];
        inFile.read(str);
        String text = new String(str);

        String[] numbers = text.split("\\D");
        int i, j;
        int n = next(numbers), m = next(numbers);
        int matr[][] = new int[n][m];

        for (i = 0; i < n; ++i)
            for (j = 0; j < m; ++j)
                matr[i][j] = next(numbers);

        for (i = 0; i < n; ++i, System.out.println())
            for (j = 0; j < m; ++j)
                System.out.print(matr[i][j] + " ");

        Graph graph = new Graph();
        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        graph.insertVertex("D");
        graph.insertVertex("E");
        graph.insertVertex("F");

        graph.insertEdge(0, 1);
        graph.insertEdge(0, 2);
        graph.insertEdge(1, 3);
        graph.insertEdge(2, 4);
        graph.insertEdge(2, 5);

        graph.dfs(0);
        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        graph.insertVertex("D");
        graph.insertVertex("E");
        graph.insertVertex("F");

        graph.insertEdge(0, 1);
        graph.insertEdge(0, 2);
        graph.insertEdge(1, 3);
        graph.insertEdge(2, 4);
        graph.insertEdge(2, 5);
        graph.bfs(0);
    }

    public static class Vertex {
        private String label;
        private boolean isVisited;

        public Vertex(String label)
        {
            this.label = label;
            isVisited = false;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public boolean isVisited() {
            return isVisited;
        }

        public void setVisited(boolean isVisited) {
            this.isVisited = isVisited;
        }
    }
    public static class Graph {
        //максимальное количество вершин в графе
        public final int VERTEX_MAX = 100;
        //массив для хранения вершин
        public Vertex[] vertexList;
        //текущее количество вершин в графе
        public int vertexCount;
        //матрица смежности
        public int[][] matrix;
        public static int count;
        public Graph()
        {
            matrix = new int[VERTEX_MAX][VERTEX_MAX];
            //перед началом работы заполняем матрицу смежности нулями
            for(int i = 0; i < VERTEX_MAX; i++)
                for(int j = 0; j < VERTEX_MAX; j++)
                    matrix[i][j] = 0;
            vertexCount = 0;
            vertexList = new Vertex[VERTEX_MAX];
        }

        public void insertVertex(String key)
        {
            Vertex v = new Vertex(key);
            vertexList[count++] = v;
        }
        public void insertEdge(int begin, int end)
        {
            matrix[begin][end] = 1;
            matrix[end][begin] = 1;
        }
        //добавление вершины
        public void addVertex(String label)
        {
            vertexList[vertexCount++] = new Vertex(label);
        }
        //добавление ребра
        public void addEdge(int begin, int end)
        {
            matrix[begin][end] = 1;
            matrix[end][begin] = 0;
        }
        //обход в ширину
        void bfs(int v)
        {
            vertexList[v].isVisited = true;
            JQueue queue = new JQueue(100);
            queue.insert(v);
            int vertex;
            //выведем вершину, с которой начинается обход, на экран
            System.out.println(vertexList[v].getLabel());
            while(!queue.isEmpty())//пока очередь не опустеет
            {
                int current = queue.pop();
                while((vertex = getSuccessor(current)) != -1)
                {
                    vertexList[vertex].isVisited = true;
                    queue.insert(vertex);
                    //вывод вершины на экран
                    System.out.println(vertexList[vertex].getLabel());
                }
            }
            //сброс флагов
            for(int j = 0; j < vertexCount; j++)
                vertexList[j].isVisited = false;
        }
        //метод возвращает непосещенную вершину, смежную по отношению к v
        int getSuccessor (int v)
        {
            for(int j = 0; j < vertexCount; j++)
                if(matrix[v][j] == 1 && vertexList[j].isVisited == false)
                    return j; //возвращает первую найденную вершину
            return -1; //таких вершин нет
        }
        // обход в глубину
        void dfs(int v){
            Stack stack = new Stack();
            vertexList[v].setVisited(true);//алгоритм начинает обход с вершины 0
            stack.push(0);//занесение в стек
            int i = 0;
//выведем вершину, с которой начинается обход, на экран
            System.out.println(vertexList[v].getLabel());
            while(!stack.isEmpty()) //пока стек не опустеет
            {
                int current = (int) stack.peek();
                //получение непосещенной вершины, смежной с текущей
                int vertex = getSuccessor (current);
                if(vertex == -1)
                    stack.pop();//элемент извлекается из стека
                else //если вершина найдена
                {
                    vertexList[vertex].setVisited(true);//пометка
                    System.out.println(vertexList[current].getLabel());//вывод
                    stack.push(vertex);//занесение в стек
                }
            }
//сброс флагов
            for(int j = 0; j < vertexCount; j++)//сброс флагов
                vertexList[j].setVisited(false);
        }
    }
    public static class JQueue {

        private int[] array;//массив для хранения элементов
        private int size;//количество элементов в очереди
        private int count1;//текущее количество элементов
        private int front;//элемент, который находится в начале очереди
        private int rear;//элемент, который находится в конце очереди

        public JQueue(int queueSize) {
            size = queueSize;
            array = new int[size];
            front = 0;
            rear = -1;
            count1 = 0;//элементов в очереди пока нет
        }
        //извлечение элемента из начала очереди
        public int pop()
        {
            int temp = array[front++];
            if(front == size)
                front = 0;
            count1--;
            return temp;
        }
        //получаем размер очереди(как удобно, в жизни б так)
        public int size()
        {
            return count1;
        }
        //проверка, пуста ли очередь (а вот это в реальной жизни вряд ли пригодилось)
        public boolean isEmpty()
        {
            return (count1 == 0);
        }
       //вставка элемента в конец очереди
        public void insert(int value)
        {
            if(rear == size - 1)
                rear = -1;
            array[++rear] = value;
            count1++;
        }
    }
}



