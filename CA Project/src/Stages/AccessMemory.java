package Stages;

import Simulation.Program;

public class AccessMemory {
	private String immAddress;
	private char load;
	private char store;
	private char offset;
	private String res;
	private String readData;
	private String RD3;
	
	private String WR;
	private char wrRegFile;
	private char wrSrc;
	
	//without handing hazards
//	private char branch;
//	private String branchAddress;
		
	public void accMemStage() {
		getInfoMEM();
		String add = offset=='1'?res:immAddress;
		Program.dataMemory.setAddress(add);
		int addi = Integer.parseInt(add,2);
		int addUsed = (int)addi/4;
		int offset = addi%4;
		if(load == '1') { //lw
			if(Program.dataMemCache.infoAccess(add)) {
				readData = Program.dataMemCache.getDataCache(add);
			}else {
				readData = Program.dataMemory.getDataMem().getValue(addUsed,offset,"");
				Program.dataMemCache.addAddress(add, readData);
			}
			Program.dataMemory.setReadData(readData);
		}else if(store == '1') { //sw
			Program.dataMemCache.addAddress(add, RD3);
			Program.dataMemory.getDataMem().setValue(addUsed,offset,RD3);
		}
		//without handing hazards
//		this.incrementPC();
		this.setInfoMEM();
	}
	
	//without handling hazards
//	private void incrementPC() {
//		if(branch=='1' && Program.alu.getZERO()=='1') {
//			Program.pcIncremented = branchAddress;
//		}
//	}
	
	private void getInfoMEM() {
		immAddress = (String) Program.EXE_MEM.getInfo("immAddress");
		RD3 = (String) Program.EXE_MEM.getInfo("RD3");
		offset = (char) Program.EXE_MEM.getInfo("offset");
		load = (char) Program.EXE_MEM.getInfo("load");
		store = (char) Program.EXE_MEM.getInfo("store");
		res = (String) Program.EXE_MEM.getInfo("res");
		wrSrc = (char)Program.EXE_MEM.getInfo("wrSrc");
		wrRegFile = (char)Program.EXE_MEM.getInfo("wrRegFile");
		WR = (String)Program.ID_EXE.getInfo("WR");
		//without handing hazards
//		branchAddress = (String) Program.EXE_MEM.getInfo("branchAddress");
//		branch = (char) Program.EXE_MEM.getInfo("branch");
	}
	
	private void setInfoMEM() {		
		Program.MEM_WB.setInfo("wrSrc",wrSrc);
		Program.MEM_WB.setInfo("wrRegFile",wrRegFile);
		
		Program.MEM_WB.setInfo("WR", WR);
		Program.MEM_WB.setInfo("res", res);
		Program.MEM_WB.setInfo("readData", readData);
		
		//without handing hazards
//		Program.MEM_WB.setInfo("signExtend28", Program.EXE_MEM.getInfo("signExtend28"));
//		Program.MEM_WB.setInfo("jump", Program.EXE_MEM.getInfo("jump"));
		
	}
	
	public String toString() {
		String memoryRead = load ==1?readData:"Don't care";
		String s = "Memory Stage:\n------------------------\n"
				+  "ALU Result: "+res+"\nMemory Word read: "+memoryRead
				+  "\nWR: "+WR+"\nWB Controls: "
				+  "wrRegFile: "+wrRegFile
				+  ", wrSrc: "+wrSrc;
		return s;
	}
}
