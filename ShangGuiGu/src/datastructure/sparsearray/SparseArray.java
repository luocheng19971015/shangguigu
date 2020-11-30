package datastructure.sparsearray;

public class SparseArray {
    public static void main(String[] args) throws Exception {

        int[][] chessArray = createChessArray(11, 11);
        chessArray[1][2] = 1;
        chessArray[2][3] = 2;
        printChessArray(chessArray);

        System.out.println("================================");

        //将二维数组转化为稀疏数组

        //遍历棋盘非零值个数
        int sum = traverseElement(chessArray);
        //创建稀疏数组
        int[][] sparseArray = createChessArray(sum + 1, 3);
        //给稀疏数组赋值
        sparseArray = valuesOfSparseArray(chessArray,sparseArray);
        printChessArray(sparseArray);

        System.out.println("====================");

        //稀疏数组还原二维数组
        int[][] array = returnChessArray(sparseArray);
        printChessArray(array);


    }

    //创建棋盘
    public static int[][] createChessArray(int row,int col) throws Exception {
        if (row <= 0 || col <= 0){
            throw new Exception("无法正确创建棋盘！");
        }
        int[][] chessArray = new int[row][col];
        return chessArray;
    }

    //打印棋盘
    public static void printChessArray(int[][] chessArray){
        for (int[] row:chessArray){
            for (int data:row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
    }

    //遍历棋盘元素
    public static int traverseElement(int[][] chessArray){
        int sum = 0;

        for (int[] row:chessArray){
            for (int data:row){
                if (data != 0){
                    sum++;
                }
            }
        }

        return sum;
    }

    //稀疏数组赋值
    public static int[][] valuesOfSparseArray(int[][] chessArray,int[][] sparseArray){
        sparseArray[0][0] = chessArray.length;
        sparseArray[0][1] = chessArray[0].length;
        sparseArray[0][2] = sparseArray.length - 1;

        int index = 1;
        for (int i = 0;i < chessArray.length;i++){
            for (int j = 0;j < chessArray[0].length;j++){
                if (chessArray[i][j] != 0){
                    sparseArray[index][0] = i;
                    sparseArray[index][1] = j;
                    sparseArray[index][2] = chessArray[i][j];
                    index++;
                }
            }
        }

        return sparseArray;
    }

    //稀疏数组还原二维数组
    public static int[][] returnChessArray(int[][] sparseArray) throws Exception {
        int row = sparseArray[0][0];
        int col = sparseArray[0][1];
        int sum = sparseArray[0][2];

        int[][] chessArray = createChessArray(row, col);
        for (int i = 1;i <= sum;i++){
            chessArray[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }

        return chessArray;
    }

}
