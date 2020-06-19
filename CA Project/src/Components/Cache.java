package Components;

public class Cache {
	private int numOfBlocks;
	private CacheInfo[] values;
	
	public Cache(int numOfBlocks) {
		this.numOfBlocks = numOfBlocks;
		values = new CacheInfo[numOfBlocks];
		for(int i=0;i<numOfBlocks;i++) {
			values[i] = new CacheInfo();
		}
	}
	
	public boolean infoAccess(String address) {
		int add = Integer.parseInt(address,2);
		int index = add%numOfBlocks;
		if(values[index].getValid() == '1') {
			if(values[index].getTag() == add/numOfBlocks) {
				if(values[index].getOffset() == Integer.parseInt(address.substring(31),2)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void addAddress(String address,String data) {
		int add = Integer.parseInt(address,2);
		int index = add%numOfBlocks;
		values[index].setValid('1');
		values[index].setTag(add/numOfBlocks);
		values[index].setOffset(Integer.parseInt(address.substring(31),2));
		String d =values[index].setData(data,values[index].getOffset());
		if(d.length()>0) {values[index+1].setData(d,0);}
	}
	
	public String getDataCache(String address) {
		String data = ""; 
		int add = Integer.parseInt(address,2);
		int index = add%numOfBlocks;
		if(infoAccess(address)) {
			data = values[index].getData(values[index].getOffset(),"");
			if(data.length()<32) data = values[index+1].getData(0,data);
		}
		return data;
	}
}
