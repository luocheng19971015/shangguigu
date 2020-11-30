package datastructure.recursion;

import org.junit.Test;

public class Maze {

    @Test
    public void test(){
        int[][] maze = createMaze(8, 7);
        for (int i = 0;i < maze.length;i++){
            for (int j = 0;j < maze[0].length;j++){
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        //输出新的地图，即小球走过的路线
        //findWay1(maze,1,1);
        findWay2(maze,1,1);
        for (int i = 0;i < maze.length;i++){
            for (int j = 0;j < maze[0].length;j++){
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }
    }

    //利用二维数组模拟迷宫
    public int[][] createMaze(int row,int col){
        if (row < 3 || col < 3){
            throw new RuntimeException("迷宫太小！");
        }

        int[][] maze = new int[row][col];

        //用1表示墙
        for (int i = 0;i < col;i++){
            maze[0][i] = 1;
            maze[row - 1][i] = 1;
        }

        for (int i = 1;i < row - 1;i++){
            maze[i][0] = 1;
            maze[i][col - 1] = 1;
        }

        if (row > 3 && col > 4){
            if (row % 2 == 0){
                maze[row / 2 - 1][1] = 1;
                maze[row / 2 - 1][2] = 1;
            }

            if (row % 2 == 1){
                maze[(row - 1) / 1][1] = 1;
                maze[(row - 1) / 1][2] = 1;
            }
        }

        return maze;
    }

    //使用递归给小球寻路（需要知道地图、起始位置）
    //引用数据类型递归中可以共享
    //1：墙；2：通；3：禁；0：未
    //需要制定一个规则（可自己定）：下->右->上->左，不通则回溯
    //仅求通路
    //假定(row - 2,col - 2)为终点
    public boolean findWay1(int[][] maze,int x,int y){
        int row = maze.length;
        int col = maze[0].length;
        if (maze[row - 2][col - 2] == 2){
            return true;
        }

        if (maze[x][y] == 0){//该点未走过
            //先假设能走通
            maze[x][y] = 2;
            if (findWay1(maze,x + 1,y)){
                return true;
            }else if (findWay1(maze,x,y + 1)){
                return true;
            }else if (findWay1(maze,x - 1,y)){
                return true;
            }else if (findWay1(maze,x,y - 1)){
                return true;
            }else {
                maze[x][y] = 3;//说明该店走不通
                return false;
            }
        }

        return false;
    }

    //修改策略：上->右->下->左
    public boolean findWay2(int[][] maze,int x,int y){
        int row = maze.length;
        int col = maze[0].length;
        if (maze[row - 2][col - 2] == 2){
            return true;
        }

        if (maze[x][y] == 0){//该点未走过
            //先假设能走通
            maze[x][y] = 2;
            if (findWay2(maze,x - 1,y)){
                return true;
            }else if (findWay2(maze,x,y + 1)){
                return true;
            }else if (findWay2(maze,x + 1,y)){
                return true;
            }else if (findWay2(maze,x,y - 1)){
                return true;
            }else {
                maze[x][y] = 3;//说明该店走不通
                return false;
            }
        }

        return false;
    }
}
