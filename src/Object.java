package src;
public class Object {

	String shapeName, shapePos;
	int i;
	
	public Object() {
		shapeName = shapePos = "";
		i = 0;
	}

	public Object(int i, String shapeName, String shapePos) {
		this.shapeName = shapeName;
		this.shapePos = shapePos;
		this.i = i;
	}
	
	public void setI(int i) {
		this.i = i;
	}

	public int getI() {
		return i;
	}
	
	public void setshapeName(String shapeName) {
		this.shapeName = shapeName;
	}
	
	public String getshapeName() {
		return shapeName;
	}

	public void setshapePos(String shapePos) {
		this.shapePos = shapePos;
	}

	public String getshapePos() {
		return shapePos;
	}

}
