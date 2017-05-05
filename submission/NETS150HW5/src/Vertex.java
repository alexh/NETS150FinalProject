
public class Vertex {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((className == null) ? 0 : className.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertex other = (Vertex) obj;
		if (className == null) {
			if (other.className != null)
				return false;
		} else if (!className.equals(other.className))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Vertex [name=" + name + ", className=" + className + "]";
	}

	String name;
	String className;
	
	public Vertex(){
		
	}
	
	public Vertex(String name, String className){
		this.name = name;
		this.className = className;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setClassName(String className){
		this.className = className;
	}
	
}
