package Stages;

import Simulation.Program;

public class WriteBack {
	private char wrRegFile;
	private char wrSrc;
	private String res;
	private String readData;
	private String WR;
	
	//without handing hazards !!
//	private char jump;
//	private String signExtendJump;
	
	public void wb() {
		getInfoWB();
		if(wrRegFile=='1') {
			Program.regFile.setWrReg(WR);
			Program.regFile.setwD(wrSrc=='1'?readData:res);
		}
		//without handling hazards !!
//		if(jump=='1') Program.pcIncremented =signExtendJump;
	}
	
	private void getInfoWB() {
		WR = (String) Program.MEM_WB.getInfo("WR");
		wrRegFile = (char) Program.MEM_WB.getInfo("wrRegFile");
		wrSrc = (char) Program.MEM_WB.getInfo("wrSrc");
		res = (String) Program.MEM_WB.getInfo("res");
		//without handing hazards !!
//		jump = (char) Program.MEM_WB.getInfo("jump");
//		signExtendJump = (String) Program.MEM_WB.getInfo("signExtendJump");
		readData = (String) Program.MEM_WB.getInfo("readData");
	}
	
	public String toString() {
		String tmp = wrRegFile=='1'?WR:"XXXXX";
		return "Write Back Stage:\n------------------------\nWR: "+tmp;
	}
}
