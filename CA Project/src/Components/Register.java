package Components;

public class Register {
	private int size;
	private String data;
	
	public Register(int size) {
		this.size = size;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		if(data.length()<this.getSize()) 
			for(int i=data.length();i<this.getSize();i++) 
				data = "0" + data;
		else if(data.length()>this.getSize()) 
			for(int i=this.getSize();i<data.length();i++)
				data = data.substring(1);
		this.data = data;
	}

	public int getSize() {
		return size;
	}
}
