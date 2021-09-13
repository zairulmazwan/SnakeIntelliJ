import java.awt.Color;
import java.util.Random;
import javax.swing.JButton;

//test update

public class SnakeMove {


    public static JButton [][] move(JButton [][] b, SnakeBody snake) {

        int len = b.length;
        Cell head = snake.snakeBody.getLast(); //the reason why head is tail because the second cell added was to the right and the current direction is right
        Cell tail = snake.snakeBody.getFirst(); // linkedlist is FIFO, first in first out, first in is the head.
        Direction dir = snake.dir;

        System.out.println("Cur dir "+snake.dir);


        if (dir==Direction.RIGHT) {
            int row = head.getRow();
            int col = head.getCol();

            if (col+1<len) { //this rule is important to avoid exceed the board size
                if ((b[row][col+1].getBackground()==Color.lightGray)) { //when right key is pressed, column is increased

                    b[tail.row][tail.col].setBackground(Color.lightGray); //when the snake moves one step, the last cell colour turns from black to gray
                    System.out.println("Head "+snake.snakeBody.getLast().col);
                    snake.snakeBody.remove(snake.snakeBody.getFirst());
                    Cell newHead = new Cell(row, col+1); //get the n+1 cell ahead as the new head
                    snake.setHead(newHead);
                    snake.snakeBody.add(newHead);

                    //b[row][col+1].setBackground(Color.black); //change the cell colour to black
                    b[snake.head.row][snake.head.col].setBackground(Color.black); //change the cell colour to black

                }

                if (b[row][col+1].getBackground()==Color.green) { //when the next col to the right is a green cell
                    eatFood(b, snake, row, col+1); //call the eatFood function
                    SnakeMove.randomFood(b);
                }
            }
        }
        else if(dir==Direction.DOWN) {
            int row = head.getRow();
            int col = head.getCol();

            if (row+1<len) {
                if ((b[row+1][col].getBackground()==Color.lightGray)) {

                    b[tail.row][tail.col].setBackground(Color.lightGray);
                    System.out.println("Head "+snake.snakeBody.getLast().col);
                    snake.snakeBody.remove(snake.snakeBody.getFirst());
                    Cell newHead = new Cell(row+1, col);
                    snake.setHead(newHead);
                    snake.snakeBody.add(newHead);

                    b[row+1][col].setBackground(Color.black);

                }
                if (b[row+1][col].getBackground()==Color.green) {
                    eatFood(b, snake, row+1, col);
                    SnakeMove.randomFood(b);
                }
            }
        }
        else if(dir==Direction.LEFT) {
            int row = head.getRow();
            int col = head.getCol();

            if (col<len && col != 0) {
                if ((b[row][col-1].getBackground()==Color.lightGray)) {

                    b[tail.row][tail.col].setBackground(Color.lightGray);
                    System.out.println("Head "+snake.snakeBody.getLast().col);
                    snake.snakeBody.remove(snake.snakeBody.getFirst());
                    Cell newHead = new Cell(row, col-1);
                    snake.setHead(newHead);
                    snake.snakeBody.add(newHead);

                    b[row][col-1].setBackground(Color.black);

                }
                if (b[row][col-1].getBackground()==Color.green) {
                    eatFood(b, snake, row, col-1);
                    SnakeMove.randomFood(b);
                }
            }
        }
        else {
            int row = head.getRow();
            int col = head.getCol();

            if (row<len && row!=0) {
                if ((b[row-1][col].getBackground()==Color.lightGray)) {

                    b[tail.row][tail.col].setBackground(Color.lightGray);
                    System.out.println("Head "+snake.snakeBody.getLast().col);
                    snake.snakeBody.remove(snake.snakeBody.getFirst());
                    Cell newHead = new Cell(row-1, col);
                    snake.setHead(newHead);
                    snake.snakeBody.add(newHead);

                    b[row-1][col].setBackground(Color.black);

                }
                if (b[row-1][col].getBackground()==Color.green) {
                    eatFood(b, snake, row-1, col);
                    SnakeMove.randomFood(b);
                }
            }

        }
        return b;
    }


    public static void randomFood (JButton [][] b) {

        Random r = new Random ();

        int row = r.nextInt(b.length);
        int col = r.nextInt(b.length);

        while (b[row][col].getBackground()==Color.black) {
            row = r.nextInt(b.length);
            col = r.nextInt(b.length);

        }

        b[row][col].setBackground(Color.green);
    }

    public static void eatFood (JButton [][] b, SnakeBody snake, int row, int col) {

        if (b[row][col].getBackground()==Color.green) {
            Cell newHead = new Cell(row, col); //the row and col here are the green cell
            snake.setHead(newHead); //this is set because in this game, the head is the tail
            snake.snakeBody.add(newHead); //when a new cell is added its actually the tail. But the cell is already set as head

            b[row][col].setBackground(Color.black); //set the green cell become black - snake body
        }


    }
}
