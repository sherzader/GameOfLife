import java.awt.Color;
import java.awt.Graphics;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.swing.JPanel;
import javax.swing.JFrame;

public class GameOfLife extends JPanel {

   private boolean[][] grid;
   private boolean[][] nextGen;

   public GameOfLife(boolean[][] grid) {
      this.grid = grid;
   }

   @Override
   protected void paintComponent(Graphics g) {
      g.setColor(Color.black);
      g.fillRect(0, 0, getWidth(), getHeight());

      int width = getWidth() / grid.length;
      int height = getHeight() / grid[0].length;
      for (int row = 0; row < grid.length; row++) {
         for (int col = 0; col < grid[row].length; col++) {
            if (grid[row][col]) {
               g.setColor(Color.green);
               g.fillRect(row * width, col * height, width, height);
            }
         }
      }
   }

   /**
   * This method is called every second.
   *
   * It apply the Rules of Life.
   */
   public void tick() {
      boolean[][] nextGen = new boolean[grid.length][grid[0].length];
      for (int row = 0; row < grid.length; row++) {
         for (int col = 0; col < grid[row].length; col++) {
            int alive = 0;
            if (row + 1 < grid.length && grid[row + 1][col]) {
               alive++;
            }
            if (col + 1 < grid[row].length && grid[row][col + 1]) {
               alive++;
            }
            if (col > 0 && grid[row][col - 1]) {
               alive++;
            }
            if (row > 0 && grid[row - 1][col]) {
               alive++;
            }
            if (row > 0 && col > 0 && grid[row - 1][col - 1]) {
               alive++;
            }
            if (row > 0 && col + 1 < grid[row].length && grid[row - 1][col + 1]) {
               alive++;
            }
            if (row + 1 < grid.length && col > 0 && grid[row + 1][col - 1]) {
               alive++;
            }
            if (row + 1 < grid.length && col + 1 < grid[row].length && grid[row + 1][col + 1]) {
               alive++;
            }
            if (grid[row][col]) {
               // if parent cell is alive
               // check life around it
               if (alive < 2) {
                  nextGen[row][col] = false;
               } else if (alive == 2 || alive == 3) {
                  nextGen[row][col] = true;
               } else {
                  nextGen[row][col] = false;
               }
            } else {
               // if parent cell is dead
               // check life around it
               if (alive == 3) {
                  nextGen[row][col] = true;
               } else {
                  nextGen[row][col] = false;
               }
            }
         }
      }
       grid = nextGen;
   }

   /**
   * This method returns a grid where each cell is randomly set to be alive.
   *
   * @param percentAlive
   *          The percent chance that each cell should be alive.
   */
   private static boolean[][] generateRandomGrid(int width, int height,
         double percentAlive) {

      boolean[][] randomGrid = new boolean[width][height];

      for (int w = 0; w < randomGrid.length; w++) {
         for (int h = 0; h < randomGrid[w].length; h++) {
            boolean cell = Math.random() < percentAlive;
            randomGrid[w][h] = cell;
         }
      }

      return randomGrid;
   }

   public static void main(String[] args) {
      boolean[][] grid = generateRandomGrid(40, 40, .5);
      GameOfLife life = new GameOfLife(grid);
      Utils.launchFrame(life); // parent generation

      Executors.newScheduledThreadPool(1).scheduleAtFixedRate(life::tick, 1,
            100, TimeUnit.MILLISECONDS);

   }

}
