package Components;

public class CacheInfo {
	private char valid;
	private int tag;
	private String data[];
	private int offset;
	
	public CacheInfo() {
		valid = 0;
		tag = 0;
		data = new String[4];
		offset = 0;
		for(int i=0;i<4;i++) {
			data[i] = "00000000";
		}
	}

	public int getValid() {
		return valid;
	}

	public void setValid(char valid) {
		this.valid = valid;
	}

	public int getTag() {
		return tag;
	}

	public void setTag(int tag) {
		this.tag = tag;
	}

	public String getData(int offset,String dataReturned) {
		int i =0;
		if(offset==0 && dataReturned.length()<32) {dataReturned += data[offset+i];i++;}
		if(offset<=1 && dataReturned.length()<32) {dataReturned += data[offset+i];i++;}
		if(offset<=2 && dataReturned.length()<32) {dataReturned += data[offset+i];i++;}
		if(offset<=3 && dataReturned.length()<32) {dataReturned += data[offset+i];}
		return dataReturned;
	}

	public String setData(String data,int offset) {
		int i =0;
		if(offset==0 && data.length()>0) {this.data[offset+i] = data.substring(0,8);i++;data = data.substring(8);}
		if(offset<=1 && data.length()>0) {this.data[offset+i] = data.substring(0,8);i++;data = data.substring(8);}
		if(offset<=2 && data.length()>0) {this.data[offset+i] = data.substring(0,8);i++;data = data.substring(8);}
		if(offset<=3 && data.length()>0) {this.data[offset+i] = data.substring(0,8);i++;data = data.substring(8);}
		return i>3?"":data.substring(8);
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}
}
