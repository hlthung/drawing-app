package src;

//This Main file used the concept of MVC.
public class Main {
	public static void main(String[] args) {
		Model model = new Model();
		View v = new View(model);
		v.setVisible(true);
	}

}
