import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;

public class StartFrame extends JFrame implements KeyListener {
	
	Block[] block;
	JLabel gradeLabel, userLabel, maxGradeLabel;	//显示成绩块， 显示用户块， 最高分块
	String gradeText;	//成绩
	JPanel panel, panel2, panel3;
	public boolean up, down, left, right;
	int moveFlag;	//移动的总步数
	int grade;	//总成绩
	boolean numFlag;
	
	public StartFrame() {
		
		this.setTitle("2048");
		this.setSize(800, 800);
		this.setLocation(100, 100);
		//更换Java咖啡图标
		ImageIcon imageIcon = new ImageIcon(getClass().getResource("/images/title.png"));	
		this.setIconImage(imageIcon.getImage());	
		// 居中窗体
		setLocationRelativeTo(null);
		
		panel = new JPanel(new GridLayout(4, 4, 5, 5));
		panel2 = new JPanel(new GridLayout(1, 6, 5, 5));
		panel3 = new JPanel(new BorderLayout());
		
		
		JLabel userName = new JLabel("用户:");
		userName.setFont(new Font("font", Font.PLAIN, 20));
		userLabel = new JLabel(loginFrame.getUserName());
		userLabel.setHorizontalAlignment(JLabel.CENTER);
		userLabel.setFont(new Font("font", Font.PLAIN, 20));
		userLabel.setOpaque(true);
		JLabel gradeName = new JLabel("成绩:");
		gradeName.setFont(new Font("font", Font.PLAIN, 20));
		gradeLabel = new JLabel("0");
		gradeLabel.setHorizontalAlignment(JLabel.CENTER);
		gradeLabel.setFont(new Font("font", Font.PLAIN, 20));
		gradeLabel.setOpaque(true);
		JLabel maxGradeName = new JLabel("最高分:");
		maxGradeName.setFont(new Font("font", Font.PLAIN, 20));
		try {
			maxGradeLabel = new JLabel(GradeFrame.getMaxGrade(loginFrame.getUserName())+"");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		maxGradeLabel.setHorizontalAlignment(JLabel.CENTER);
		maxGradeLabel.setFont(new Font("font", Font.PLAIN, 20));
		maxGradeLabel.setOpaque(true);
		panel2.add(userName, BorderLayout.CENTER);
		panel2.add(userLabel, BorderLayout.CENTER);
		panel2.add(gradeName, BorderLayout.CENTER);
		panel2.add(gradeLabel, BorderLayout.CENTER);
		panel2.add(maxGradeName, BorderLayout.CENTER);
		panel2.add(maxGradeLabel, BorderLayout.CENTER);
		
	    block = new Block[16];
	    numFlag = true;
	    moveFlag = 0;
	    up=true;down=true;left=true;right=true;
	    addBlock();
	    for (int i = 0; i < 2; i++)
	    	appearBlock();
	    
	    panel3.add(panel2, BorderLayout.NORTH);
	    panel3.add(panel, BorderLayout.CENTER);
	    setContentPane(panel3);
	    addKeyListener(this);
	    
	    this.addWindowListener(new java.awt.event.WindowAdapter() {   
	    	public void windowClosing(java.awt.event.WindowEvent e) {  
	    		new GameFrame();
	    	}   
	    }); 
	    this.setVisible(true);
	}
	
	private void addBlock() {
		/**
		 * 初始化块
		 */
		for(int i = 0; i < 16; i++) {
			block[i] = new Block();
			block[i].setHorizontalAlignment(JLabel.CENTER);
			block[i].setOpaque(true);
			panel.add(block[i]);
		}
	}
	
	public void appearBlock() {
		/**
		 * 新增新块
		 */
		while(numFlag) {
			int index = (int)(Math.random() * 16);
			if(block[index].getValue() == 0) {
				if(Math.random() < 0.5) {
					block[index].setValue(2);
				} else {
					block[index].setValue(4);
				}
				break;
			}
		}
	}
	
	public void judgeAppear() {
		/**
		 * 判断是否还可以新增块
		 */
		int sum = 0;
		for(int i = 0; i < 16; i++) {
			if(block[i].getValue() != 0) {
				sum++;
			}
		}
		if(sum == 16) 
			numFlag = false;
	}
	
	public int Find(int i, int j, int a, int b) {
		/**
		 * 找到需要移动的块
		 * @param i:当前检测的索引号
		 * @param j:每次需要移动的数
		 * @param a:下边界
		 * @param b:上边界
		 */
		while(i < b && i >= a) {
			if(block[i].getValue() != 0) {
				return i;
			}
			i = i + j;
		}
		return -1;
	}
	
	public void upBlock() {
		/**
		 * 向上移动
		 */
		int i = 0, j = 0;
		int t = 0;
		int valueI = 0, valueJ = 0;
		int index = 0;
		for(i = 0; i < 4; i++) {
			index = i;
			for(j = i + 4; j < 16; j += 4) {
				valueI = 0;
				valueJ = 0;
				if(block[index].getValue() == 0) {
					//如果这一行的这个值是空，找到下面行的非空进行交换
					t = Find(index, 4, 0, 16);
					if(t != -1) {
						block[index].setValue(block[t].getValue());
						block[t].setValue(0);
					} else {
						break;
					}
				}
				
				//找到需要比较的第一个值
				valueI = block[index].getValue();
				
				if(block[j].getValue() == 0) {
					//如果这一行的这个值是空，找到下面行的非空进行交换
					t = Find(j, 4, 0, 16);
					if(t != -1) {
						block[j].setValue(block[t].getValue());
						block[t].setValue(0);
					} else {
						break;
					}
				}
				
				//找到需要比较的第二个值
				valueJ = block[j].getValue();
				
				//比较两个值
				if(valueI==valueJ && valueI!=0 && valueJ!=0) {
					block[index].setValue(valueI+valueJ);
					grade = grade + valueI + valueJ;
					block[j].setValue(0);
					numFlag = true;
					gradeText = String.valueOf(grade);
					gradeLabel.setText(gradeText);
				}
				
				//index指向j
				index = j;
			}
		}
	}
	
	public void downBlock() {
		/**
		 * 向下移动
		 */
		int i = 0, j = 0;
		int t = 0;
		int valueI = 0, valueJ = 0;
		int index = 0;
		for(i = 12; i < 16; i++) {
			index = i;
			for(j = i - 4; j >= 0; j -= 4) {
				valueI = 0;
				valueJ = 0;
				if(block[index].getValue() == 0) {
					//如果这一行的这个值是空，找到上面行的非空进行交换
					t = Find(index, -4, 0, 16);
					if(t != -1) {
						block[index].setValue(block[t].getValue());
						block[t].setValue(0);
					} else {
						break;
					}
				}
				
				//找到需要比较的第一个值
				valueI = block[index].getValue();
				
				if(block[j].getValue() == 0) {
					//如果这一行的这个值是空，找到上面行的非空进行交换
					t = Find(j, -4, 0, 16);
					if(t != -1) {
						block[j].setValue(block[t].getValue());
						block[t].setValue(0);
					} else {
						break;
					}
				}
				
				//找到需要比较的第二个值
				valueJ = block[j].getValue();
				
				//比较两个值
				if(valueI==valueJ && valueI!=0 && valueJ!=0) {
					block[index].setValue(valueI+valueJ);
					grade = grade + valueI + valueJ;
					block[j].setValue(0);
					numFlag = true;
					gradeText = String.valueOf(grade);
					gradeLabel.setText(gradeText);
				}
				//index指向j
				index = j;
			}
		}
	}
	
	public void rightBlock() {
		/**
		 * 向右移动
		 */
		int i = 0, j = 0;
		int t = 0;
		int valueI = 0, valueJ = 0;
		int index = 0;
		for(i = 3; i < 16; i += 4) {
			index = i;
			for(j = i - 1; j > i - 4; j --) {
				valueI = 0;
				valueJ = 0;
				if(block[index].getValue() == 0) {
					//如果这一行的这个值是空，找到左边行的非空进行交换
					t = Find(index, -1, i-3, index+1);
					if(t != -1) {
						block[index].setValue(block[t].getValue());
						block[t].setValue(0);
					} else {
						break;
					}
				}
				
				//找到需要比较的第一个值
				valueI = block[index].getValue();
				
				if(block[j].getValue() == 0) {
					//如果这一行的这个值是空，找到左边列的非空进行交换
					t = Find(j, -1, i-3, j+1);
					if(t != -1) {
						block[j].setValue(block[t].getValue());
						block[t].setValue(0);
					} else {
						break;
					}
				}
				
				//找到需要比较的第二个值
				valueJ = block[j].getValue();
				
				//比较两个值
				if(valueI==valueJ && valueI!=0 && valueJ!=0) {
					block[index].setValue(valueI+valueJ);
					grade = grade + valueI + valueJ;
					block[j].setValue(0);
					numFlag = true;
					gradeText = String.valueOf(grade);
					gradeLabel.setText(gradeText);
				}
				
				//index指向j
				index = j;
			}
		}
	}
	
	public void leftBlock() {
		/**
		 * 向左移动
		 */
		int i = 0, j = 0;
		int t = 0;
		int valueI = 0, valueJ = 0;
		int index = 0;
		for(i = 0; i < 16; i += 4) {
			index = i;
			for(j = i + 1; j < i+4; j++) {
				valueI = 0;
				valueJ = 0;
				if(block[index].getValue() == 0) {
					//如果这一行的这个值是空，找到右边列的非空进行交换
					t = Find(index, 1, index, i+4);
					if(t != -1) {
						block[index].setValue(block[t].getValue());
						block[t].setValue(0);
					} else {
						break;
					}
				}
				
				//找到需要比较的第一个值
				valueI = block[index].getValue();
				
				if(block[j].getValue() == 0) {
					//如果这一行的这个值是空，找到右边列的非空进行交换
					t = Find(j, 1, j, i+4);
					if(t != -1) {
						block[j].setValue(block[t].getValue());
						block[t].setValue(0);
					} else {
						break;
					}
				}
				
				//找到需要比较的第二个值
				valueJ = block[j].getValue();
				
				//比较两个值
				if(valueI==valueJ && valueI!=0 && valueJ!=0) {
					block[index].setValue(valueI+valueJ);
					grade = grade + valueI + valueJ;
					block[j].setValue(0);
					numFlag = true;
					gradeText = String.valueOf(grade);
					gradeLabel.setText(gradeText);
				}
				
				//index指向j
				index = j;
			}
		}
	}
	
	public void over() {
		/**
		 * 游戏结束
		 */
		if(numFlag==false && up==false && down==false && left==false && right==false) {
			block[4].setText("G");
		    block[5].setText("A");
		    block[6].setText("M");
		    block[7].setText("E");
		    block[8].setText("O");
		    block[9].setText("V");
		    block[10].setText("E");
		    block[11].setText("R"); 
		    try {
		    	//增加分数记录
				User.exportGrade(loginFrame.getUserName(), grade);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		    JOptionPane.showMessageDialog(this.panel, "你的得分为: " + grade);
		    this.addMouseListener(new MouseAdapter() {
			      public void mousePressed(MouseEvent e) {
			    	  dispose();
				      new GameFrame();
				  }
		    });
		}
	}
	
	public void win() {
		/**
		 * 游戏胜利
		 */
	    block[0].setText("Y");
	    block[1].setText("O");
	    block[2].setText("U");
	    block[13].setText("W");
	    block[14].setText("I");
	    block[15].setText("N");
	    this.addMouseListener(new MouseAdapter() {
		      public void mousePressed(MouseEvent e) {
		    	  dispose();
			      new GameFrame();
			  }
	    });
	    JOptionPane.showMessageDialog(this.panel, "Congratulations! You win!你的得分为: " + grade);
	}
	
	public void reStart() {
		/**
		 * 重新游戏
		 */
	    numFlag=true;
	    moveFlag=0;
	    up=true;down=true;left=true;right=true;
	    for(int i = 0; i < 16; i++)
	      block[i].setValue(0);
	    for (int i = 0; i < 2; i++)
	      appearBlock();
	}
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		/**
		 * 点击方向键监听
		 */
		switch (e.getKeyCode()) {
		    case KeyEvent.VK_UP:
			      if(up) {
			    	  upBlock();
			    	  moveFlag++;
			      }
			      judgeAppear();
			      appearBlock();
			      over();	
			        
			      if(numFlag == false) {
			        up = false;
			      } else {
			        up=true;down=true;left=true;right=true;
			      }
			      break;
		    case KeyEvent.VK_DOWN:
			      if(down) {
			    	  downBlock();
			    	  moveFlag++;
			      }
			      judgeAppear();
			      appearBlock();
			      over();
			      if(numFlag == false) {
			        down=false; 
			      } else {
			        up=true;down=true;left=true;right=true;
			      }
			      break;
		    case KeyEvent.VK_LEFT:
			      if(left) {
			    	  leftBlock();
			    	  moveFlag++;
			      }
			      judgeAppear();
			      appearBlock();
			      over();
			      if(numFlag==false) {
			        left=false;
			      } else {
			        up=true;down=true;left=true;right=true;
			      }
			      break;
		    case KeyEvent.VK_RIGHT:
			      if(right) {
			    	  rightBlock();
			    	  moveFlag++;
			      }
			      judgeAppear();
			      appearBlock();
			      over();
			      if(numFlag==false) {
			        right=false;
			      } else {
			        up=true;down=true;left=true;right=true;
			      }
			      break;
		}
	  
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
