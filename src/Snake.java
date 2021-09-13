import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Snake {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new SnakePanel();
    }

}

class Cell {

    int row,  col;

    public Cell (int row, int col) {

        this.row=row;
        this.col=col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}

class SnakeBody {

    public Cell head;
    public LinkedList<Cell> snakeBody = new LinkedList<Cell>();
    public Direction dir;
    public Cell tail;

    public SnakeBody (Cell post, Direction dir) {

        this.head = post;
        snakeBody.add(head);
        this.dir = dir;
    }

    public Cell getTail (SnakeBody snakeBody) {
        return tail=snakeBody.snakeBody.getFirst();
    }

    public void setHead(Cell post) {
        this.head=post;
    }

    public void updateDir(Direction dir) {
        this.dir=dir;
    }

}


enum Direction {

    UP, DOWN, LEFT, RIGHT;
}


class SnakePanel implements KeyListener {

    int maxWidth = 30;
    int maxHeight = 30;
    JFrame frame = new JFrame();
    JButton [][] arrayButtons = new JButton [maxWidth][maxHeight];

    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();


    JLabel up = new JLabel();
    JLabel down = new JLabel();
    JLabel left = new JLabel();
    JLabel right = new JLabel();

    int upCount = 1;
    int downCount = 1;
    int leftCount = 1;
    int rightCount = 1;

    Cell init = new Cell(15,15);
    SnakeBody snake;

    Direction curDir = Direction.RIGHT;



    public void prepareFrame(){
        frame.setTitle("Snake Game");
        frame.getContentPane().setLayout(null);
        frame.setVisible(true);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel1);
        frame.add(panel2);
        frame.addKeyListener((KeyListener) this);

        panel1.setBorder(new LineBorder(Color.black, 3));
        panel1.setLayout(new GridLayout(maxWidth, maxHeight));
        panel1.setBounds(50,10,380,380);


        panel2.setBounds(50,380,380,75);
        panel2.setBorder(new LineBorder(Color.black, 3));

    }

    public void buttonArray() {

        for (int i = 0; i < maxWidth; i++) {
            for (int j = 0; j < maxHeight; j++) {
                arrayButtons[i][j] = new JButton();
                arrayButtons[i][j].setBackground(Color.lightGray);
                panel1.add(arrayButtons[i][j]);
            }
        }
        panel1.doLayout();
    }

    public void panel2GUI() {

        panel2.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel label0 = new JLabel("Press arrows Up Down Left Right");

        panel2.add(label0, c);
        panel2.doLayout();

    }

    public void setSnake() {

        Cell body = new Cell(15,16); //create a new cell - a snake has cells
        snake = new SnakeBody(init, curDir); //init is the existing cell to be added, this is to create the snake object
        snake.snakeBody.add(body); //add a new cell to snake. Snake is a linkedlist.

        //get the colour of the snake body
        for (int i=0; i<snake.snakeBody.size(); i++) {
            arrayButtons[snake.snakeBody.get(i).row][snake.snakeBody.get(i).col].setBackground(Color.black);//getting each cell (snake body)
        }
        System.out.println("Head coordinate "+snake.snakeBody.getLast().row+" "+snake.snakeBody.getLast().col);
        System.out.println("Tail coordinate "+snake.getTail(snake).row+" "+snake.getTail(snake).col);
        SnakeMove.randomFood(arrayButtons);
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int keyCode = e.getKeyCode();

        switch(keyCode) {
            case KeyEvent.VK_SPACE:
                System.out.println("Space bar is pressed.");
                break;
            case KeyEvent.VK_UP:
                System.out.println("Up arrow is pressed.");
                curDir = Direction.UP;
                System.out.println("Current direction is "+curDir);
                snake.updateDir(curDir);
                SnakeMove.move(arrayButtons, snake);
                break;
            case KeyEvent.VK_DOWN:
                System.out.println("Down arrow is pressed.");
                curDir = Direction.DOWN;
                System.out.println("Current direction is "+curDir);
                snake.updateDir(curDir);
                SnakeMove.move(arrayButtons, snake);
                break;
            case KeyEvent.VK_LEFT:
                System.out.println("Left arrow is pressed.");
                curDir = Direction.LEFT;
                System.out.println("Current direction is "+curDir);
                snake.updateDir(curDir);
                SnakeMove.move(arrayButtons, snake);
                break;
            case KeyEvent.VK_RIGHT:
                System.out.println("Right arrow is pressed.");
                curDir = Direction.RIGHT;
                System.out.println("Current direction is "+curDir);
                snake.updateDir(curDir);
                SnakeMove.move(arrayButtons, snake);
                break;
        }

    }


    @Override
    public void keyReleased(KeyEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        // TODO Auto-generated method stub

    }

    SnakePanel(){
        prepareFrame();
        buttonArray();
        panel2GUI();
        setSnake();
    }

}