import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		FileParser fp = new FileParser("../test.txt");
		GraphApplet graph = new GraphApplet();
		graph.init();
	      JFrame frame = new JFrame("MyPanel");
	      frame.getContentPane().add(graph);
	      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      frame.pack();
	      frame.setLocationRelativeTo(null);
	      frame.setVisible(true);
	}

}
