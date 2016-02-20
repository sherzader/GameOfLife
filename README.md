#[Conway's Game of Life](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life)
![Game Image](gameoflife.png =250px)

##To run simulation:
1. Download .zip file
2. Navigate to root of game_of_life folder in your shell terminal
3. Compile the java program `$javac GameOfLife.java`
4. Run the program `$java GameOfLife`

###The simulation is a two-dimensional grid of cells. Each cell can either be alive or dead, which update on each tick.

###Every second, the grid is updated based on the following Rules:
1. Any live cell with fewer than two live neighbors dies, as if caused by under-population.
2. Any live cell with two or three live neighbors lives on to the next generation.
3. Any live cell with more than three live neighbors dies, as if by overcrowding.
4. Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.


###Fun To-do's:
- [ ] let user click the mouse to set that cell to the "alive" state
- [ ] let user press spacebar to pause/unpause the simulation
- [ ] let user press -/+ to slow down or speed up the simulation
