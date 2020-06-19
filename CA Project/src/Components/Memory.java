package Components;

public class Memory {
	private String[][] data;
	private int memSize;
	
	public Memory(int memSize) {
		this.memSize = memSize;
		data = new String[memSize][4];
		for(int i=0;i<memSize;i++) {
			for(int j=0;j<4;j++) data[i][j] = "00000000";
		}
	}
	
	public String getValue(int index,int offset,String dataReturned) {
		int i =0;
		if(offset==0 && dataReturned.length()<32) {
			dataReturned += data[index][offset+i];
			i++;
		}
		if(offset<=1 && dataReturned.length()<32) {
			dataReturned += data[index][offset+i];
			i++;
		}
		if(offset<=2 && dataReturned.length()<32) {
			dataReturned += data[index][offset+i];
			i++;
		}
		if(offset<=3 && dataReturned.length()<32) {
			dataReturned += data[index][offset+i];
		}
		return dataReturned.length()==32?dataReturned:getValue(index+1, 0, dataReturned);
	}
	
	public void setValue(int index,int offset ,String data1) {
		int i =0;
		if(offset==0 && data1.length()>0) {this.data[index][offset+i] = data1.substring(0,8);i++;data1 = data1.substring(8);}
		if(offset<=1 && data1.length()>0) {this.data[index][offset+i] = data1.substring(0,8);i++;data1 = data1.substring(8);}
		if(offset<=2 && data1.length()>0) {this.data[index][offset+i] = data1.substring(0,8);i++;data1 = data1.substring(8);}
		if(offset<=3 && data1.length()>0) {this.data[index][offset+i] = data1.substring(0,8);i++;data1 = data1.substring(8);}
		if(data1.length()>0) setValue(index+1, 0, data1);
	}

	public int getMemSize() {
		return memSize;
	}
}
