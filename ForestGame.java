import java.util.Random;

public class ForestGame {
    public static void main(String[] args) {
        char[][] forest = generateForest(5, 5); // Adjust the size of the forest as needed
        displayForest(forest);
        movePlayer(forest, 'D'); // Example: Move player to the right
        displayForest(forest);
    }

    // Method to generate the forest
    public static char[][] generateForest(int rows, int cols) {
        char[][] forest = new char[rows][cols];
        Random rand = new Random();

        // Populate forest with trees and open spaces
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (rand.nextDouble() < 0.3) {
                    forest[i][j] = 'T'; // Tree
                } else {
                    forest[i][j] = '.'; // Open space
                }
            }
        }

        // Place the player at a random empty location
        int playerRow, playerCol;
        do {
            playerRow = rand.nextInt(rows);
            playerCol = rand.nextInt(cols);
        } while (forest[playerRow][playerCol] == 'T'); // Ensure the player is not placed on a tree
        forest[playerRow][playerCol] = 'P'; // Player
        return forest;
    }

    // Method to display the forest
    public static void displayForest(char[][] forest) {
        for (int i = 0; i < forest.length; i++) {
            for (int j = 0; j < forest[0].length; j++) {
                System.out.print(forest[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // Method to move the player
    public static void movePlayer(char[][] forest, char direction) {
        int playerRow = -1, playerCol = -1;

        // Find the player's current location
        outerloop:
        for (int i = 0; i < forest.length; i++) {
            for (int j = 0; j < forest[0].length; j++) {
                if (forest[i][j] == 'P') {
                    playerRow = i;
                    playerCol = j;
                    break outerloop;
                }
            }
        }

        // Update the player's location based on the direction
        switch (direction) {
            case 'W': // Up
                if (playerRow > 0 && forest[playerRow - 1][playerCol] != 'T') {
                    forest[playerRow][playerCol] = '.';
                    forest[playerRow - 1][playerCol] = 'P';
                } else {
                    System.out.println("Invalid move: Cannot move up");
                }
                break;
            case 'S': // Down
                if (playerRow < forest.length - 1 && forest[playerRow + 1][playerCol] != 'T') {
                    forest[playerRow][playerCol] = '.';
                    forest[playerRow + 1][playerCol] = 'P';
                } else {
                    System.out.println("Invalid move: Cannot move down");
                }
                break;
            case 'A': // Left
                if (playerCol > 0 && forest[playerRow][playerCol - 1] != 'T') {
                    forest[playerRow][playerCol] = '.';
                    forest[playerRow][playerCol - 1] = 'P';
                } else {
                    System.out.println("Invalid move: Cannot move left");
                }
                break;
            case 'D': // Right
                if (playerCol < forest[0].length - 1 && forest[playerRow][playerCol + 1] != 'T') {
                    forest[playerRow][playerCol] = '.';
                    forest[playerRow][playerCol + 1] = 'P';
                } else {
                    System.out.println("Invalid move: Cannot move right");
                }
                break;
            default:
                System.out.println("Invalid direction");
        }
    }
}
