import java.awt.Color;
import java.awt.GridLayout;
import java.io.File;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Canvas extends JFrame {

	private static final long serialVersionUID = 1L;

    private JPanel panel;
    private JLabel[][] gridBoxes;
    private ImageIcon boulder, path, end;
    
    
    Canvas() {
    	createBoard();	
    }
    
    
    private void createBoard() {
    	setTitle("Find The Shortest Path"); 
    	setSize(Parameters.X_PIXEL_SIZE, Parameters.Y_PIXEL_SIZE);
    	
    	panel = new JPanel(new GridLayout(Parameters.X_BOXES, Parameters.Y_BOXES));
    	panel.setBackground(Color.DARK_GRAY);
    	setContentPane(panel);
    	
    	loadImages();
    	initializeBoard();
    	
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
    	setResizable(false);
    	setLocationRelativeTo(null);
    	setVisible(true);
    }

    
    private void loadImages() {
    	try {
    	    boulder = new ImageIcon(ImageIO.read(new File("resources/boulder.png")));
    	    path = new ImageIcon(ImageIO.read(new File("resources/path.png")));
    	    end = new ImageIcon(ImageIO.read(new File("resources/end.png")));
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    
    private void initializeBoard() {
    	gridBoxes = new JLabel[Parameters.X_BOXES][Parameters.Y_BOXES];
    	
    	for (int x=0; x<Parameters.X_BOXES; x++) {
    		for (int y=0; y<Parameters.Y_BOXES; y++) {
    			gridBoxes[x][y] = new JLabel();
    			gridBoxes[x][y].setVerticalAlignment(JLabel.CENTER);
    			gridBoxes[x][y].setHorizontalAlignment(JLabel.CENTER);
    	    	panel.add(gridBoxes[x][y]);
    	    	
    	    	if ((x==0) || (x==Parameters.X_BOXES-1) || (y==0) || (y==Parameters.Y_BOXES-1)) {
    	    		// Create a frame boulder around the board
    	    		changeLabelTo(Parameters.BOULDER, x, y);
    	    	} else if ((x==Parameters.INITIAL_X_POSITION) && (y==Parameters.INITIAL_Y_POSITION)) {
    	    		// Set initial position
    	    		changeLabelTo(Parameters.PATH, x, y);
    	    	} else if ((x==Parameters.FINAL_X_POSITION) && (y==Parameters.FINAL_Y_POSITION)) {
    	    		// Set Final position
    	    		changeLabelTo(Parameters.END, x, y);
    	    	} else {
    	    		// Put boulders on random positions on the board
    	    		Random rand = new Random();
    	    		double chanceOfBoulder = rand.nextDouble();
    	    		
    	    		if (chanceOfBoulder <= Parameters.PERCENT_CHANCE) {
    	    			changeLabelTo(Parameters.BOULDER, x, y);
    	    		}
    	    	}
    		}
    	}
    }
    
    
    public void printBoard(JLabel[][] solution) {
    	for (int x=0; x<Parameters.X_BOXES; x++) {
    		for (int y=0; y<Parameters.Y_BOXES; y++) {
    			gridBoxes[x][y].setIcon(solution[x][y].getIcon());
    		}
    		
    		changeLabelTo(Parameters.PATH, Parameters.FINAL_X_POSITION, Parameters.FINAL_Y_POSITION);
    	}
    }
    
    
    public void changeLabelTo(String object, int x, int y) {
    	switch(object) {
    	case Parameters.PATH: gridBoxes[x][y].setIcon(path); break;
    	case Parameters.BOULDER: gridBoxes[x][y].setIcon(boulder); break;
    	case Parameters.END: gridBoxes[x][y].setIcon(end); break;
    	case Parameters.EMPTY: gridBoxes[x][y].setIcon(new ImageIcon()); break;
    	}
    }
    
    public boolean isBoulder(int x, int y) {
    	return (boulder.equals(gridBoxes[x][y].getIcon()));
    }
    
    public boolean isEnd(int x, int y) {
    	return (end.equals(gridBoxes[x][y].getIcon()));
    }
    
    public JLabel getJLabel(int x, int y) {
    	return gridBoxes[x][y];
    }
}
