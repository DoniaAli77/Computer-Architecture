package Stages;

import Simulation.Program;

public class Execute {
	private String res;
	private String opCode;
	private char rev;
	private String operators;
	private char immediateOp;
	

	private String shamt;
	private String forward1;
	private String forward2;
	private String forward3;
	private String forward4;
	private String r1;
	private String r2;
	private String r3;
	private String r4;
	
	private char offset;
	private char immediateSlt;
	private char immediateSl;
	
	private String immediate1;
	private String immediate2;
	private String immOffset;
	private String immSlt;
	private String immShift;
	
	//without handing hazards
//	private char branchSrc;
//	private String immBranch12;
//	private String immBranch;
//	private String branchAddress;
	
	private char store;
	private String WR;
	private char wrRegFile;
	private char wrSrc;
	private char load;
	private String RD3;
	
	public void exec() {
		getInfoEXE();
		forwardingUnit();
		
		if(immediateOp =='0' && immediateSl == '1') r1 = immShift;
		else if(immediateOp =='1' && immediateSl == '0') r1 = immediate1;
		
		if(offset == '0' && immediateSlt=='1') r2 = immSlt;
		else if(offset == '1' && immediateSlt=='0') r2 = immOffset;
		else if(offset == '1' && immediateSlt=='1') r2 = immediate2;
		
		
		if(forward1.equals("01")){r1 = (String) Program.EXE_MEM.getInfo("res");}
		else if(forward1.equals("10")){r1 = (String) Program.MEM_WB.getInfo("readData");}
		if(forward2.equals("01")){r2 = (String) Program.EXE_MEM.getInfo("res");}
		else if(forward2.equals("10")){r2 = (String) Program.MEM_WB.getInfo("readData");}
		if(forward3.equals("01")){r3 = (String) Program.EXE_MEM.getInfo("res");}
		else if(forward3.equals("10")){r3 = (String) Program.MEM_WB.getInfo("readData");}
		if(forward4.equals("01")){r4 = (String) Program.EXE_MEM.getInfo("res");}
		else if(forward4.equals("10")){r4 = (String) Program.MEM_WB.getInfo("readData");}
		
		res= Program.alu.aluEvaluator(aluCont(opCode),r1, r2, r3, r4,shamt,operators, rev);
		
		//without handing hazards
//		branchAddress = branchSrc=='1'?
//				immBranch12.substring(2)+"00":immBranch.substring(2)+"00";
		
		setInfoEXE();
	}
	
	private String aluCont(String op) {
		op = op.equals("1100") || op.equals("1101") || op.equals("1000")?"0000":op;
		return op;
	}
	
	private void forwardingUnit() {
		forward1 = "00";
		forward2 = "00";
		forward3 = "00";
		forward4 = "00";
		if(Program.EXE_MEM.getInfo("wrRegFile") != null &&(char)Program.EXE_MEM.getInfo("wrRegFile") == '1') {
			if(((String)Program.ID_EXE.getInfo("RR1")).equals((String)Program.EXE_MEM.getInfo("WR")) &&
					!(((String)Program.ID_EXE.getInfo("RR1")).equals("00000")||
					((String)Program.ID_EXE.getInfo("RR1")).equals("00001"))) {
				forward1 = "01";
			}
			if(((String)Program.ID_EXE.getInfo("RR2")).equals((String)Program.EXE_MEM.getInfo("WR"))&&
					!(((String)Program.ID_EXE.getInfo("RR2")).equals("00000")||
					((String)Program.ID_EXE.getInfo("RR2")).equals("00001"))) {
				forward2 = "01";
			}
			if(((String)Program.ID_EXE.getInfo("RR3")).equals((String)Program.EXE_MEM.getInfo("WR"))&&
					!(((String)Program.ID_EXE.getInfo("RR3")).equals("00000")||
					((String)Program.ID_EXE.getInfo("RR3")).equals("00001"))) {
				forward3 = "01";
			}
			if(((String)Program.ID_EXE.getInfo("RR4")).equals((String)Program.EXE_MEM.getInfo("WR"))&&
					!(((String)Program.ID_EXE.getInfo("RR4")).equals("00000")||
					((String)Program.ID_EXE.getInfo("RR4")).equals("00001"))) {
				forward4 = "01";
			}
		}
		if(Program.MEM_WB.getInfo("wrRegFile") != null && Program.MEM_WB.getInfo("load")!=null &&
				(char)Program.MEM_WB.getInfo("wrRegFile") == '1' && (char)Program.MEM_WB.getInfo("load")=='1') {
			if(((String)Program.ID_EXE.getInfo("RR1")).equals((String)Program.MEM_WB.getInfo("WR"))&&
					!(((String)Program.ID_EXE.getInfo("RR1")).equals("00000")||
					((String)Program.ID_EXE.getInfo("RR1")).equals("00001"))) {
				forward1 = "10";
			}
			if(((String)Program.ID_EXE.getInfo("RR2")).equals((String)Program.MEM_WB.getInfo("WR"))&&
					!(((String)Program.ID_EXE.getInfo("RR2")).equals("00000")||
					((String)Program.ID_EXE.getInfo("RR2")).equals("00001"))) {
				forward2 = "10";
			}
			if(((String)Program.ID_EXE.getInfo("RR3")).equals((String)Program.MEM_WB.getInfo("WR"))&&
					!(((String)Program.ID_EXE.getInfo("RR3")).equals("00000")||
					((String)Program.ID_EXE.getInfo("RR3")).equals("00001"))) {
				forward3 = "10";
			}
			if(((String)Program.ID_EXE.getInfo("RR4")).equals((String)Program.MEM_WB.getInfo("WR"))&&
					!(((String)Program.ID_EXE.getInfo("RR4")).equals("00000")||
					((String)Program.ID_EXE.getInfo("RR4")).equals("00001"))) {
				forward4 = "10";
			}
		}
	}
	
	private void getInfoEXE() {
		immediate1 = (String) Program.ID_EXE.getInfo("immediate1");
		immediate2 = (String) Program.ID_EXE.getInfo("immediate2");
		immOffset = (String) Program.ID_EXE.getInfo("immOffset");
		immSlt = (String) Program.ID_EXE.getInfo("immSlt");
		immShift = (String) Program.ID_EXE.getInfo("immShift");
	
		rev = (char) Program.ID_EXE.getInfo("rev");
		immediateOp = (char) Program.ID_EXE.getInfo("immediateOp");
		operators = (String) Program.ID_EXE.getInfo("operators");
		opCode = (String) Program.ID_EXE.getInfo("opCode");
		
		immediateSl = (char) Program.ID_EXE.getInfo("immediateSl");
		immediateSlt = (char) Program.ID_EXE.getInfo("immediateSlt");
		offset = (char) Program.ID_EXE.getInfo("offset");		
		shamt = (String) Program.ID_EXE.getInfo("shamt");
		
		r1 = (String) Program.ID_EXE.getInfo("RD1");
		r2 = (String) Program.ID_EXE.getInfo("RD2");
		r3 = (String) Program.ID_EXE.getInfo("RD3");
		r4 = (String) Program.ID_EXE.getInfo("RD4");
		
		store = (char) Program.ID_EXE.getInfo("store");
		load = (char)Program.ID_EXE.getInfo("load");
		WR = (String) Program.ID_EXE.getInfo("WR");
		wrRegFile = (char) Program.ID_EXE.getInfo("wrRegFile");
		wrSrc = (char)Program.ID_EXE.getInfo("wrSrc");
		RD3 = (String) Program.ID_EXE.getInfo("RD3");
		
		//without handing hazards
//		branchSrc = (char) Program.ID_EXE.getInfo("branchSrc");
//		immBranch = (String) Program.ID_EXE.getInfo("immBranch");
//		immBranch12 = (String) Program.ID_EXE.getInfo("immBranch12");
	}
	
	private void setInfoEXE() {
		Program.EXE_MEM.setInfo("immAddress", Program.ID_EXE.getInfo("immAddress"));
		Program.EXE_MEM.setInfo("offset", offset);
		Program.EXE_MEM.setInfo("load", store);
		Program.EXE_MEM.setInfo("store", load);
		Program.EXE_MEM.setInfo("wrRegFile", wrRegFile);
		Program.EXE_MEM.setInfo("wrSrc", wrSrc);

		
		Program.EXE_MEM.setInfo("res", res);
		
		Program.EXE_MEM.setInfo("RD3", RD3);
		
		//For forwarding
		Program.EXE_MEM.setInfo("WR", WR);
		
		//without handing hazards
//		Program.EXE_MEM.setInfo("signExtendJump", Program.ID_EXE.getInfo("signExtendJump"));
//		Program.EXE_MEM.setInfo("branchAddress", branchAddress);
//		Program.EXE_MEM.setInfo("branch", Program.ID_EXE.getInfo("branch"));
//		Program.EXE_MEM.setInfo("jump", Program.ID_EXE.getInfo("jump"));
	}
	
	public String toString() {
		String s = "Execute Stage:\n----------------------"
				  +"\nALURes/Address: "+res;
		String readVal = store == '1'? RD3:"00000000000000000000000000000000";
		s += "\nReg value to write to memory: "+readVal
			+"\nWR: "+WR;
		
		s+="\nWB Controls: "+ 
		   "wrRegFile: "+wrRegFile+
		   ", wrSrc: "+wrSrc;
		
		s+="\nMEM Controls: offset: "+offset
		  +", load: "+load+", store: "+store
		  +"\nForwarding Unit: forward1: "+forward1+", forward2: "+forward2+", forward3: "+forward3+", forward4: "+forward4;
		return s;
	}

}
