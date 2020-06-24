package graph;

public class MaxAreaOfIsland {
    public static void main(String[] args) {
        int[][] grid = {{0,0,1,0,0,0,0,1,0,0,0,0,0},
                        {0,0,0,0,0,0,0,1,1,1,0,0,0},
                        {0,1,1,0,1,0,0,0,0,0,0,0,0},
                        {0,1,0,0,1,1,0,0,1,0,1,0,0},
                        {0,1,0,0,1,1,0,0,1,1,1,0,0},
                        {0,0,0,0,0,0,0,0,0,0,1,0,0},
                        {0,0,0,0,0,0,0,1,1,1,0,0,0},
                        {0,0,0,0,0,0,0,1,1,0,0,0,0}};

        System.out.println("Max area is " + compute(grid));
  }

    public static int compute(int[][] grid) {
        int l = grid.length, w = grid[0].length;
        int[][] visited = new int[l][w];

        int maxArea = 0;
        for (int i=0;i<l-1;i++) {
            for(int j=0;j<w-1;j++){
                int area = dfs(grid, visited, i, j, 0);
                System.out.println(String.format("Area at index %d,%d is %d", i, j, area));
                maxArea = Math.max(maxArea, area);
            }
        }
        return maxArea;
    }

    public static int dfs(int[][] grid, int[][] visited, int i, int j, int area) {
        int l = grid.length, w = grid[0].length;
        int ret = area;
        if (i>=0 && i < l && j >=0 && j < w && grid[i][j] != 0 && visited[i][j] == 0) {
            visited[i][j] = 1;
            ret = area + 1;
            ret = dfs(grid, visited, i + 1, j, ret);
            ret = dfs(grid, visited, i - 1, j, ret);
            ret = dfs(grid, visited, i, j + 1, ret);
            ret = dfs(grid, visited, i, j - 1, ret);
        }
        return ret;
    }
}
