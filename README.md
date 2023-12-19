# Sudoku Solver and Generator (Java)

## Solver
The Sudoku solver is made in an object oriented way with all methods needed being isntance methods.
The instance methods are as follows:
- Constructor: `Solver(int[][] board)`
    - This constructor initializes a Solver with the `this.board = board`
- `exchangeBoard(int[][] board)`
    - takes the parameter `board` and assigns `this.board = board`
- `safeSpotCheck(int row, int column, int number)`
    - checks if number at a certain row and column is safe to add
- `solve()`
    - the instance method to call on the `Solver` object to attempt to solve Sudoku
    - Will return true if the Sudoku was correctly solved (false otherwise)
- `isValid()`
    - instance method to check if the current `this.board` is a valid board meaning it doesn't have any invalid number locations
- `print()`
    - simple instance method to print the current value of `this.board` to the console