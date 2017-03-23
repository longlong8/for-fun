package breakOut;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;


public class BreakOutPanel extends JPanel implements ActionListener,KeyListener {
	
	public final static int FRAME_WIDTH = 800;
	public final static int FRAME_HEIGHT = 600;
	private final int PADDLE_MOVE = 15;
	private Timer timer;
	private BreakOutBall ball;
	private Paddle paddle;
	private ArrayList<Brick> bricks;
	private LinkedList<Color> colors;
	private Random random;

    public BreakOutPanel() {
        super(); 
		this.setBackground(Color.LIGHT_GRAY);
		this.setPreferredSize(new Dimension(FRAME_WIDTH,FRAME_HEIGHT));	
        timer = new Timer(1000/60, this);
		timer.start();
		ball = new BreakOutBall();
		paddle = new Paddle();
		this.setFocusable(true);
        this.requestFocusInWindow();
		this.addKeyListener(this);
		bricks = new ArrayList<Brick>();
		colors = new LinkedList<Color>(); 
		random = new Random();
		initBricks();
    }

    private void initBricks() {
    	int margin_left=40,margin_top=30;
    	int x=margin_left,y=margin_top,brick_width=90,brick_height=30;
    	colors.add(new Color(0, 67, 88));
    	colors.add(new Color(190,219,57));
    	colors.add(new Color(253,116,0));
    	for (int i=0;i<6;i++) {
    		for (int j=0;j<8;j++) {
    			Rectangle brick = new Rectangle(x,y,brick_width,brick_height);
    			x+=brick_width;
    			int c = random.nextInt(3);
    			bricks.add(new Brick(brick,colors.get(c)));
    		}
    		x=margin_left;
    		y+=brick_height;
    	}
    }
    public void paintComponent(Graphics g) {
        // call super class paintComponent method
        // background will not be colored otherwise
        super.paintComponent(g);
        ball.draw(g);
        paddle.draw(g);
        for (Brick b:bricks) {
        	g.setColor(Color.black);
    		g.drawRect(b.brick.x,b.brick.y,b.brick.width,b.brick.height); 
    		g.setColor(b.color);
    		g.fillRect(b.brick.x,b.brick.y,b.brick.width,b.brick.height); 
        }
    }
    
	@Override
	public void actionPerformed(ActionEvent e) {
		ball.move(ball.getMoveX(), ball.getMoveY());
		if ((ball.getX()-ball.getR()) <= 0 || (ball.getX()+ball.getR()) >= FRAME_WIDTH) {
			ball.setMoveX(ball.getMoveX()* -1);
		}
		if ((ball.getY()-ball.getR()) <= 0) {
			ball.setMoveY(ball.getMoveY()* -1);
		}
		if (ball.getY()-ball.getR()>FRAME_HEIGHT) {
        	gameOver();
		    System.out.println("GG");
		}
		if (Math.abs(ball.getY()-paddle.getY())<ball.getR()) {	
			if ((ball.getX()+ball.getR())>paddle.getX()&&ball.getX()-ball.getR()<paddle.getX()+paddle.getWidth()) {
				ball.setMoveY(-Math.abs(ball.getMoveY())); //bounce back to top
				if (ball.getX()>=paddle.getX()+40&&ball.getX()<=paddle.getX()+60) { //center of paddle
					ball.setMoveX(ball.getMoveX()/Math.abs(ball.getMoveX()));
					ball.setMoveY(-4);
				}
				else if (ball.getX()>paddle.getX()+20&&ball.getX()<paddle.getX()+40) { // left
					ball.setMoveX(-3);
					ball.setMoveY(-3);
				}
				else if (ball.getX()>paddle.getX()+60&&ball.getX()<paddle.getX()+80) { // right
					ball.setMoveX(3);
					ball.setMoveY(-3);
				}
				else if (ball.getX()<paddle.getX()+20) { //far left
					ball.setMoveX(-4);
					ball.setMoveY(-2);
				}
				else if (ball.getX()>paddle.getX()+80) { //far right
					ball.setMoveX(4);
					ball.setMoveY(-2);
				}
			}
		}
		brickBounce();
		repaint();
	}
	private void brickBounce() {
		Brick remove=null;
		Brick remove2=null;
		for (Brick b:bricks) {
			Rectangle brick = b.brick;
			//left point of the circle
			if (brick.contains(ball.getX()-ball.getR(), ball.getY())||brick.contains(ball.getX()+ball.getR(), ball.getY())) { 
				remove = b;
				ball.setMoveX(ball.getMoveX()*-1);
			}
			//top point of the circle
			if (brick.contains(ball.getX(), ball.getY()-ball.getR())||brick.contains(ball.getX(), ball.getY()+ball.getR())) { 
				remove2 = b;
				ball.setMoveY(ball.getMoveY()*-1);
			}
		}
		bricks.remove(remove);
		bricks.remove(remove2);
		if (bricks.isEmpty()) gameOver();
	}
	public void gameOver() {
		timer.stop();
		String text = "";
		if (bricks.isEmpty()) text = "You won!";
		else text = "Game Over!";
    	JOptionPane.showMessageDialog(this, text);   	
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			if (paddle.getX()>0) {
				paddle.moveX(-PADDLE_MOVE);
			}
			break;
		case KeyEvent.VK_RIGHT:
			if ((paddle.getX()+paddle.getWidth())<FRAME_WIDTH) {
				paddle.moveX(PADDLE_MOVE);
			}
			break;
		default: break;
		}
		repaint();
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub			
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub	
	}
}