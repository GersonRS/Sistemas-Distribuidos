package modelo;

// Clase Maze.java criada por:
// name: Torin Rudeen

/* 
* Endereço do código fonte original
* https://github.com/torinmr/Maze 
* Foram removidos alguns métodos que não eram necessários 
* para o desenvolvimento do meu labirinto
* 
*/

public class Maze
{
    private static int[][] generate(int height, int width)
    {
        height = 2*height + 1;
        width = 2*width + 1;
        int[][] maze = new int[height][width];
        boolean[][] visited = newBoolean(height, width, false);

        // fill maze with walls.
        for (int i = 0; i < height; i++)
        {
            {
                for (int j = 0; j < width; j++)
                {
                    if (i % 2 == 0 || j % 2 == 0) maze[i][j] = 1;
                }
            }
        }

        // place exit and entrance.
        int endI = (((int) ((height - 1)*Math.random()))/2)*2 + 1;
        int startI = (((int) ((height - 1)*Math.random()))/2)*2 + 1;  
        maze[endI][0] = 2;
        maze[startI][width - 1] = 3;

        // call carving function to carve maze itself.
        carve(endI, 1, maze, visited);

        return maze;
    }

    // recursive function, uses depth first search to carve out a perfect maze.
    // (perfect = every square part of maze, one and only one path between any
    // two spaces in the maze).
    private static void carve(int currentI, int currentJ, int[][] maze,
            boolean[][] visited)
    {
        // mark current cell as visited.
        visited[currentI][currentJ] = true;

        // fetch a random ordering of the cardinal directions.
        int[][] directions = randomDirections();

        // call itself recursively in the four compass directions, in the order
        // determined above.
        for (int i = 0; i < 4; i++)
        { 
            int newI = currentI + 2*directions[i][0];
            int newJ = currentJ + 2*directions[i][1];
            // ensure target square is in maze and unvisited.
            if (newI < 1 || newI >= maze.length - 1 || newJ < 1
                    || newJ >= maze[0].length - 1) continue;
            if (visited[newI][newJ] == true) continue;

            // remove the wall between current square and target square, and
            // then move to target square.
            maze[currentI + directions[i][0]][currentJ + directions[i][1]] = 0;
            carve(newI, newJ, maze, visited);
        }      

        return;
    }
    // generates a random ordering of the cardinal directions, in array form.
    private static int[][] randomDirections()
    {
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int a[] = new int[2];
        for (int i = 0; i < 3; i++)
        {
            a = directions[i];
            int random = (int) (Math.random()*(3-i));
            int target = i + random + 1;
            directions[i] = directions[target];
            directions[target] = a;
        }
        return directions;
    }

    // prints out the generated or loaded maze.
    private static void print(int[][] maze)
    {
        int height = maze.length;
        int width = maze[0].length;

        for (int i = 0; i < height; i++)
        {
            for (int j = 0; j < width; j++)
            {
                System.out.print(maze[i][j] + "  ");
            }
            System.out.println();
        }
    }


    // utility; generates a boolean array of the requested size, initialized
    // to the requested value.
    private static boolean[][] newBoolean(int a, int b, Boolean value)
    {
        boolean[][] array = new boolean[a][b];
        for (int i = 0; i < a; i++)
        {
            for (int j = 0; j < b; j++)
            {
                array[i][j] = value;
            }
        }
        return array;
    }

    //    public static void explore(int[][] maze, int currentI, int currentJ)
    //    {
    //        while (true)
    //        {
    //            if (maze[currentI][currentJ] == 3)
    //            {
    //                StdOut.println("Congratulations!");
    //                break;
    //            }
    //            
    //            int[] direction = {0, 0};
    //        }
    //        return;
    //    }
    
   
    public static int[][] createMaze(int size) {
        int[][] maze = generate(size, size);  
        print(maze);
        return maze;
    }
}
