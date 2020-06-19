package Stages;

import Simulation.Program;

public class DecodeInstruction {
	private String fetchedInstruction;
	private String pcInc;
	private String opcode;
	//Control signals
	private char immediateSl; //input1_2
	private char offset; //input2_1
	private char immediateSlt; //input2_2
	private char arithm;
	
	private char branch;
	private char jump;
	private char rev;
	private String operators;
	private char immediateOp; //input1_1
	private char wrRegFile;
	private char wrSrc;
	private char load;
	private char store;
	private char branchSrc;
	//Control Signals
	
	//immediateValues
	private String immSlt;
	private String immShift;
	private String immediate1;
	private String immediate2;
	private String immBranch;
	private String immOffset;
	private String immBranch11;
	private String immAddress;
	private String signExtendJump;
	private String shamt;
	//immediateValues
	
	private String branchAddress = "XXXX";
	
	private String flush;
		
	public void decode() {
		getInfoID();
		if(flush.equals("0")) {
			opcode = fetchedInstruction.substring(0, 4);
			shamt = fetchedInstruction.substring(25, 30);
			contUnit(opcode, fetchedInstruction.substring(29,32));
			
			Program.regFile.setWrReg(fetchedInstruction.substring(4, 9));
			Program.regFile.setRR1(branch=='0'?fetchedInstruction.substring(9,14):fetchedInstruction.substring(4, 9));
			Program.regFile.setRR2(branch=='0'?fetchedInstruction.substring(14,19):fetchedInstruction.substring(9,14));
			Program.regFile.setRR3(arithm=='1'?fetchedInstruction.substring(19,24):
				store=='0'?fetchedInstruction.substring(14,19):Program.regFile.getWrReg());
			Program.regFile.setRR4(fetchedInstruction.substring(24,29));
			
			Program.regFile.setRD1(Integer.parseInt(Program.regFile.getRR1(),2));
			Program.regFile.setRD2(Integer.parseInt(Program.regFile.getRR2(),2));
			Program.regFile.setRD3(Integer.parseInt(Program.regFile.getRR3(),2));
			Program.regFile.setRD4(Integer.parseInt(Program.regFile.getRR4(),2));
			
			immSlt = SignExtend(fetchedInstruction.substring(14, 30),16);//slt immediate
			immShift = SignExtend(fetchedInstruction.substring(9, 25),16);//shift immediate
			immediate1 = SignExtend(fetchedInstruction.substring(9, 20),21);//addi,ori immediate1
			immediate2 = SignExtend(fetchedInstruction.substring(20, 31),21);//addi,ori immediate2
			immBranch11 = SignExtend(fetchedInstruction.substring(19, 30),21);//branch11 address
			immBranch = SignExtend(fetchedInstruction.substring(14, 30), 16); //branch16 address
			immOffset = SignExtend(fetchedInstruction.substring(25, 31),26);// lw,sw offset  
			immAddress= SignExtend(fetchedInstruction.substring(9, 31),11);//lw,sw immediate
			signExtendJump = SignExtend(fetchedInstruction.substring(4,32),2) + "00"; //j
			
			setInfoID();
			controlHazard();
		}
	}

	private String SignExtend(String Immediate,int value) {
		// TODO Auto-generated method stub
				String s = "";
				for (int i = 0; i < value; i++)
					s += Immediate.charAt(0);
				s += Immediate;
				return s;
	}
	
	private void contUnit(String OpCode,String info) {
		if(OpCode.equals("0000") || OpCode.equals("0001")||
				OpCode.equals("0010")||OpCode.equals("0011")) {//Add-sub-and-multiply
			wrRegFile = '1';
			wrSrc = '0';
			store = '0';
			load = '0';
			jump = '0';
			branch = '0';
			operators = info.substring(0,2);
			rev = info.charAt(2);
			branchSrc = 'X';
			arithm = '1';
			immediateOp = '0'; 
			immediateSl = '0';
			offset = '0';
			immediateSlt = '0';
		}
		else if(OpCode.equals("0100") || OpCode.equals("0111")) {//bne bgt
			wrRegFile = '0';
			wrSrc = 'X';
			store = '0';
			load = '0';
			jump = '0';
			branch = '1';
			// resOrNot 30, number 31
			operators = info.charAt(2) =='0'?"01":"10";
			branchSrc = info.charAt(2);
			rev = info.charAt(1);
			// resOrNot 30, number 31
			arithm = '0';
			immediateOp = '0'; 
			immediateSl = '0';
			offset = '0';
			immediateSlt = '0';
		}
		else if(OpCode.equals("1000") || OpCode.equals("1001")) {//addi ori
			wrRegFile = '1';
			wrSrc = '0';
			store = '0';
			load = '0';
			jump = '0';
			branch = '0';
			operators =opcode.equals("1000")? "01":"XX";
			branchSrc = 'X';
			rev = '0';
			arithm = '0';
			immediateOp = info.charAt(2); 
			immediateSl = '0';
			offset = '1';
			immediateSlt = '1';
		}
		else if(OpCode.equals("1100")) {//lw
			wrRegFile = '1';
			wrSrc = '1';
			store = '0';
			load = '1';
			jump = '0';
			branch = '0';
			operators = "01";
			branchSrc = 'X';
			rev = '0';
			arithm = '0';
			immediateOp = '0';
			immediateSl = '0';
			immediateSlt = '0';
			offset = info.charAt(2); 
		}
		else if(OpCode.equals("1101")) {//sw
			wrRegFile = '0';
			wrSrc = 'X';
			store = '1';
			load = '0';
			jump = '0';
			branch = '0';
			operators = "01";
			branchSrc = 'X';
			rev = '0';
			arithm = '0';
			immediateOp = '0';
			immediateSl = '0';
			immediateSlt = '0';
			offset =info.charAt(2);
		}
		else if(OpCode.equals("1110")|| OpCode.equals("1111")) {//sll srl
			wrRegFile = '1';
			wrSrc = '0';
			store = '0';
			load = '0';
			immediateOp = '0';
			jump = '0';
			branch = '0';
			operators = "XX";
			branchSrc = 'X';
			rev = info.charAt(1);
			arithm = '0';
			immediateOp = '0';
			immediateSl = info.charAt(2);
			immediateSlt = '0';
			offset = '0';
		}else if(OpCode.equals("0101")) {// slt
			wrRegFile = '1';
			wrSrc = '0';
			store = '0';
			load = '0';
			jump = '0';
			branch = '0';
			operators = "XX";
			branchSrc = 'X';
			rev = info.charAt(2); 
			arithm = '0';
			immediateOp = '0';
			immediateSl = '0';
			immediateSlt = info.charAt(1);
			offset = '0';
		}else if(OpCode.equals("1010")) { //j
			//kollo hena X
			wrRegFile = 'X';
			wrSrc = 'X';
			store = 'X';
			load = 'X';
			immediateOp = 'X';
			jump = '1';
			branch = 'X';
			operators = "XX";
			branchSrc = 'X';
			rev = 'X';
			arithm = 'X';
			immediateSl = 'X';
			immediateSlt = 'X';
			offset = 'X';
		}	
		else
			System.out.println("not valid");
	}
	
	private void getInfoID() {
		fetchedInstruction = (String) Program.IF_ID.getInfo("Instruction");
		pcInc = (String) Program.IF_ID.getInfo("PCIncremented");
		flush = (String) Program.IF_ID.getInfo("flush");
	}
	
	private void setInfoID() {
		Program.ID_EXE.setInfo("immAddress", immAddress);
		Program.ID_EXE.setInfo("immediate1", immediate1);
		Program.ID_EXE.setInfo("immediate2", immediate2);
		Program.ID_EXE.setInfo("immOffset", immOffset);
		Program.ID_EXE.setInfo("immShift", immShift);
		Program.ID_EXE.setInfo("immSlt", immSlt);
		Program.ID_EXE.setInfo("rev", rev);
		Program.ID_EXE.setInfo("opCode", opcode);
		Program.ID_EXE.setInfo("immediateOp", immediateOp);
		Program.ID_EXE.setInfo("operators", operators);
		Program.ID_EXE.setInfo("load", load);
		Program.ID_EXE.setInfo("store", store);
		Program.ID_EXE.setInfo("wrRegFile", wrRegFile);
		Program.ID_EXE.setInfo("wrSrc", wrSrc);
		Program.ID_EXE.setInfo("immediateSl", immediateSl);
		Program.ID_EXE.setInfo("immediateSlt", immediateSlt);
		Program.ID_EXE.setInfo("offset", offset);
		Program.ID_EXE.setInfo("shamt", shamt);
		
		//For forwarding
		Program.ID_EXE.setInfo("RR1", Program.regFile.getRR1());
		Program.ID_EXE.setInfo("RR2", Program.regFile.getRR2());
		Program.ID_EXE.setInfo("RR3", Program.regFile.getRR3());
		Program.ID_EXE.setInfo("RR4", Program.regFile.getRR4());
		Program.ID_EXE.setInfo("WR", Program.regFile.getWrReg());
		//For forwarding
		
		Program.ID_EXE.setInfo("RD1", Program.regFile.getRD1());
		Program.ID_EXE.setInfo("RD2", Program.regFile.getRD2());
		Program.ID_EXE.setInfo("RD3", Program.regFile.getRD3());
		Program.ID_EXE.setInfo("RD4", Program.regFile.getRD4());
		
		//without handing hazards
//		Program.ID_EXE.setInfo("immBranch", immBranch);
//		Program.ID_EXE.setInfo("immBranch12", immBranch12);
//		Program.ID_EXE.setInfo("signExtendJump", signExtendJump);
//		Program.ID_EXE.setInfo("branch", branch);
//		Program.ID_EXE.setInfo("jump", jump);
//		Program.ID_EXE.setInfo("branchSrc", branchSrc);
	}
	
	private void controlHazard() {
		if(branch == '1') {
			Program.hazard = '1';
			String r1 = Program.regFile.getRD1();
			String r2 = Program.regFile.getRD2();
			String r3 = Program.regFile.getRD3();
			char res = binaryComparator(r1, r2, r3, opcode.equals("0100")?"!=":">", rev, operators);
			if(res=='1') {
				Program.pcIncremented = branchSrc == '0'? immBranch.substring(2)+"00":immBranch11.substring(2)+"00";
				branchAddress = Program.pcIncremented;
				Program.IF_flush.setData("1");
			}
		}else if(jump == '1') {
			Program.hazard = '1';
			Program.pcIncremented = signExtendJump;
			Program.IF_flush.setData("1");
		}
	}
	
	private static char binaryComparator(String r1,String r2,String r3,String operator,char rev,String num) {
		char answer = '0';
		int one=0;
		int two=0;
		int three= 0;
		if(operator.equals("!=")) {
			if(num.equals("10")) {		
				one= r1.charAt(0)=='1'?-1*Integer.parseInt(Program.flipNeg(r1),2):Integer.parseInt(r1, 2);
				two= r2.charAt(0)=='1'?-1*Integer.parseInt(Program.flipNeg(r2),2):Integer.parseInt(r2, 2);
				three= r3.charAt(0)=='1'?-1*Integer.parseInt(Program.flipNeg(r3),2):Integer.parseInt(r3, 2);
				answer= one!=two&& two!=three&& one!=three?'1':'0';
			}else if(num.equals("01")) {
				one= r1.charAt(0)=='1'?-1*Integer.parseInt(Program.flipNeg(r1),2):Integer.parseInt(r1, 2);
				two= r2.charAt(0)=='1'?-1*Integer.parseInt(Program.flipNeg(r2),2):Integer.parseInt(r2, 2);
				answer= one!=two?'1':'0';
			}
		}else if(operator.equals(">")) {
			if(num.equals("10")) {	
				one= r1.charAt(0)=='1'?-1*Integer.parseInt(Program.flipNeg(r1),2):Integer.parseInt(r1, 2);
				two= r2.charAt(0)=='1'?-1*Integer.parseInt(Program.flipNeg(r2),2):Integer.parseInt(r2, 2);
				three= r3.charAt(0)=='1'?-1*Integer.parseInt(Program.flipNeg(r3),2):Integer.parseInt(r3, 2);
				answer= one>two&& two>three?'1':'0';
			}else if(num.equals("01")) {
				one= r1.charAt(0)=='1'?-1*Integer.parseInt(Program.flipNeg(r1),2):Integer.parseInt(r1, 2);
				two= r2.charAt(0)=='1'?-1*Integer.parseInt(Program.flipNeg(r2),2):Integer.parseInt(r2, 2);
				answer= one>two?'1':'0';
			}
		}
		if(rev == '1') answer = answer=='0'?'1':'0';
		return answer;
	}
	
	public String toString() {
		String string ="Decode Stage:\n------------------------------\n"
				+"RD1: "+Program.regFile.getRD1()+"\nRD2: "+Program.regFile.getRD2()+"\n";
		
		if(operators.equals("10")) string+="RD3: "+Program.regFile.getRD3()+"\nRD4: Don't care\n";
		else if(operators.equals("11")) string+="RD3: "+Program.regFile.getRD3()+"\nRD4: "+Program.regFile.getRD4();
		else string+="RD3: Don't care\nRD4: Don't care\n";
		
		string+= "immOffset: "+immOffset
				+"\nimmBranch12: "+immBranch11
				+"\nimmBranch16: "+immBranch
				+"\nimmAddress: "+immAddress
				+"\nimmediate1: "+immediate1
				+"\nimmediate2: "+immediate2
				+"\nimmShift: "+immShift
				+"\nimmSlt: "+immSlt;
		
		string+= "\nNext PC: "+pcInc
				+"\nR1: "+Program.regFile.getRR1()
				+"\nR2: "+Program.regFile.getRR2()
				+"\nR3: "+Program.regFile.getRR3()
				+"\nR4: "+Program.regFile.getRR4()
				+"\nWR: "+Program.regFile.getWrReg()
				+ "\nWB Controls:  wrRegFile: "+wrRegFile+", wrSrc: "+wrSrc+"\n"
		        + "MEM Controls: offset: "+offset+", load: "+load+", store: "+store+"\n"
		        + "EXE Controls: immediateSl: "+immediateSl+", offset: "+offset+", immediateSlt: "+immediateSlt
		        + ", immediateOp: "+immediateOp+", operators: "+operators+", rev: "+rev
				+"\nControl Hazard: branch: "+branch+", jump: "+jump+", branch address: "+branchAddress+", branchSrc: "+branchSrc;
		
		return string;
	}
	
}
