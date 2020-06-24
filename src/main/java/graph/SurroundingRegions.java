package graph;

public class SurroundingRegions {
    public static void main(String[] args) {
        char[][] grid = {
            { 'X', 'X', 'X', 'X'},
            { 'X', 'O', 'O', 'X' },
            { 'X', 'X', 'O', 'X' },
            { 'X', 'O', 'X', 'X' }};

        int l = grid.length, w = grid[0].length;
        char[][] updatedGrid = compute(grid);
        System.out.println("Updated grid :");
        for (int i = 0; i < l - 1; i++) {
            System.out.print('[');
            for (int j = 0; j < w - 1; j++) {
                System.out.print(' ' + updatedGrid[i][j]);
            }

            System.out.println(']');
        }
  }

    public static char[][] compute(char[][] grid) {
        int l = grid.length, w = grid[0].length;
        int[][] visited = new int[l][w];

        for (int i=0;i<l-1;i++) {
            dfs(grid, visited, i, 0);
            dfs(grid, visited, i, w-1);
        }
        for(int j=0;j<w-1;j++){
            dfs(grid, visited, 0, j);
            dfs(grid, visited, l-1, j);
        }

        for (int i=0;i<l-1;i++) {
            for(int j=0;j<w-1;j++){
                if (grid[i][j] == 'O' && visited[i][j] == 0) {
                    grid[i][j] = 'X';
                }
            }
        }
        return grid;
    }

    public static void dfs(char[][] grid, int[][] visited, int i, int j) {
        int l = grid.length, w = grid[0].length;
        if (i >= 0 && i < l && j >= 0 && j < w && grid[i][j] == 'O' && visited[i][j] == 0) {
            visited[i][j] = 1;
            dfs(grid, visited, i + 1, j);
            dfs(grid, visited, i - 1, j);
            dfs(grid, visited, i, j + 1);
            dfs(grid, visited, i, j - 1);
        }
    }
}
