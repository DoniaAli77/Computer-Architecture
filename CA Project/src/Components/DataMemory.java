package Components;

public class DataMemory {
	private Memory dataMem;
	private String address;
	private String readData;
	private int readMem;
	private int writeMem;
	
	public DataMemory() {
		dataMem = new Memory(1024);
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getReadData() {
		return readData;
	}

	public void setReadData(String readData) {
		this.readData = readData;
	}

	public Memory getDataMem() {
		return dataMem;
	}

	public int getReadMem() {
		return readMem;
	}

	public void setReadMem(int readMem) {
		this.readMem = readMem;
	}

	public int getWriteMem() {
		return writeMem;
	}

	public void setWriteMem(int writeMem) {
		this.writeMem = writeMem;
	}
}
