public class Ferzi2 {

    static int[] chessboard = {0,0,0,0,0,0,0,0};
    static int index = 0;
    static int version = 0;

    public static void main(String[] args){

        do {
            if (checking()){
                if (index == 7) {
                    int[][] arr = new int[8][8];
                    System.out.println(" Ver " +  ++version);
                    arr[0][chessboard[0]]  = 8;
                    arr[1][chessboard[1]]  = 8;
                    arr[2][chessboard[2]]  = 8;
                    arr[3][chessboard[3]]  = 8;
                    arr[4][chessboard[4]]  = 8;
                    arr[5][chessboard[5]]  = 8;
                    arr[6][chessboard[6]]  = 8;
                    arr[7][chessboard[7]]  = 8;
                    for (int i = 0; i < arr.length; i++) {
                        for (int j = 0; j < arr.length ; j++) {
                            System.out.print(arr[i][j] + " ");
                        }
                        System.out.println();
                    }
//                    System.out.println(version++ + " [0]=" + chessboard[0] + " [1]=" + chessboard[1] + " [2]=" + chessboard[2] + " [3]=" + chessboard[3] + " [4]=" + chessboard[4] + " [5]=" + chessboard[5] + " [6]=" + chessboard[6] + " [7]=" + chessboard[7]);
                    chessboard[index]++;
                }
                else {
                    index++;
                }
            }
            else {
                chessboard[index]++;
            }
        } while (chessboard[0]<8);
    }

    static boolean checking() {
        int i;

        if (index == 0) {
            return true;
        }

        if (chessboard[index]>7){
            chessboard[index] = 0;
            index--;
            return false;
        }

        for (i=0;i<index;i++){
            if ((chessboard[index] == chessboard[i])|((Math.abs(chessboard[index] - chessboard[i])) == (index-i))){
                return false;
            }
        }

        return true;
    }
}
