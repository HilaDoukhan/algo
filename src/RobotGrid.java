
import java.util.*;

public class RobotGrid {
    static final int N = 4; // גודל הלוח
    static final int[][] DIRECTIONS = {{0,1}, {1,0}, {0,-1}, {-1,0}};
    static final char WALL = '#', EMPTY = '.', ROBOT = 'R';

    static class Point {
        int x, y;
        Point(int x, int y) { this.x = x; this.y = y; }
    }

    static List<Point> findRobots(char[][] grid) {
        List<Point> robots = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == ROBOT) {
                    robots.add(new Point(i, j));
                }
            }
        }
        return robots;
    }

    static Point findBestMeetingPoint(char[][] grid, List<Point> robots) {
        int K = robots.size();
        int[][][] distances = new int[K][N][N];

        for (int k = 0; k < K; k++) {
            bfs(grid, robots.get(k), distances[k]);
        }

        int minMaxDist = Integer.MAX_VALUE;
        Point best = null;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == WALL) continue;

                int maxDist = 0;
                boolean reachable = true;

                for (int k = 0; k < K; k++) {
                    if (distances[k][i][j] == -1) {
                        reachable = false;
                        break;
                    }
                    maxDist = Math.max(maxDist, distances[k][i][j]);
                }

                if (reachable && maxDist < minMaxDist) {
                    minMaxDist = maxDist;
                    best = new Point(i, j);
                }
            }
        }

        return best;
    }

    static void bfs(char[][] grid, Point start, int[][] dist) {
        for (int[] row : dist) Arrays.fill(row, -1);
        Queue<Point> queue = new LinkedList<>();
        queue.add(start);
        dist[start.x][start.y] = 0;

        while (!queue.isEmpty()) {
            Point p = queue.poll();
            for (int[] d : DIRECTIONS) {
                int nx = p.x + d[0];
                int ny = p.y + d[1];

                if (nx >= 0 && nx < N && ny >= 0 && ny < N &&
                        grid[nx][ny] != WALL && dist[nx][ny] == -1) {
                    dist[nx][ny] = dist[p.x][p.y] + 1;
                    queue.add(new Point(nx, ny));
                }
            }
        }
    }

    static void printGridWithBest(char[][] grid, Point best) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == best.x && j == best.y)
                    System.out.print("* ");
                else
                    System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }
}