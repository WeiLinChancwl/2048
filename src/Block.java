import javax.swing.*;
import java.awt.*;

public class Block extends JLabel{
	/**
	 * 2048”Œœ∑øÈ¿‡
	 */
	private static final long serialVersionUID = 1L;
	private int value;
	public Block() {
		value = 0;
		setFont(new Font("font", Font.PLAIN, 40));
		setBackground(Color.lightGray);
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
		String text = String.valueOf(value);
		if(value != 0) {
			setText(text);
		} else {
			setText("");
		}
		setColor();
	}

	public void setColor() {
		switch (value) {
		    case 0:
		      setBackground(Color.lightGray);
		      break;
		    case 2:
		      setBackground(new Color(238, 228, 218));
		      break;
		    case 4:
		      setBackground(new Color(238, 224, 198));
		      break;
		    case 8:
		      setBackground(new Color(243, 177, 116));
		      break;
		    case 16:
		      setBackground(new Color(243, 177, 116));
		      break;
		    case 32:
		      setBackground(new Color(248, 149, 90));
		      break;
		    case 64:
		      setBackground(new Color(249, 94, 50));
		      break;
		    case 128:
		      setBackground(new Color(239, 207, 108));
		      break;
		    case 256:
		      setBackground(new Color(239, 207, 99));
		      break;
		    case 512:
		      setBackground(new Color(239, 203, 82));
		      break;
		    case 1024:
		      setBackground(new Color(239, 199, 57));
		      break;
		    case 2048:
		      setBackground(new Color(239, 195, 41));
		      break;
		    case 4096:
		      setBackground(new Color(255, 60, 57));
		      break;
	    }	
	}
}
