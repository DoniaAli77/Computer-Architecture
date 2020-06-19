package Simulation;

import java.util.ArrayList;
import java.util.Scanner;

import Components.ALU;
import Components.Cache;
import Components.DataMemory;
import Components.Memory;
import Components.PipelineRegisters;
import Components.Register;
import Components.RegisterFile;

public class Program {
	public static DataMemory dataMemory = new DataMemory();
	public static Cache dataMemCache = new Cache(128);
	public static RegisterFile regFile = new RegisterFile();
	public static ALU alu = new ALU();
	public static Memory instructionMemory = new Memory(1024);
	public static Register pcReg = new Register(32);
	public static String pcIncremented = "00000000000000000000000000000000";
	public static Register IF_flush = new Register(1);
	public static char hazard = '0';
	public static PipelineRegisters IF_ID = new PipelineRegisters();
	public static PipelineRegisters ID_EXE= new PipelineRegisters();
	public static PipelineRegisters EXE_MEM= new PipelineRegisters();
	public static PipelineRegisters MEM_WB= new PipelineRegisters();
	private static int programSize;
	private static Simulator[] runningStages = new Simulator[5];
 	
	private static void init(ArrayList<String> program) {//add $s0,$s1,$s2  000000#5#5#5#5#6
		programSize = program.size();
		for (int i = 0; i < programSize; i++) 
			instructionMemory.setValue(i,0,program.get(i));
		IF_flush.setData("0");
	}
	
	private static void run() {
		int count =0;
		boolean executed = false;
		int cycles = 1;
		while(!executed) {
			String pcBranch = "";
			String pcIncTmp = pcIncremented;
			System.out.println(cycles==1?"":"\n"+"Clock Cycle: '"+cycles+"'");
			runningStages[4] = runningStages[3];
			runningStages[3] = runningStages[2];
			runningStages[2] = runningStages[1];
			runningStages[1] = runningStages[0];
			if(runningStages[4]!=null) runningStages[4].wBack.wb();
			if(runningStages[3]!=null) runningStages[3].accessMemory.accMemStage();
			if(runningStages[2]!=null) runningStages[2].exec.exec();
			if(runningStages[1]!=null) runningStages[1].decodeInstruction.decode(); 
			if(hazard == '1') {
				if(IF_flush.getData().equals("1")) {
					pcBranch = pcIncremented;
					pcIncremented = pcIncTmp;
				}
			}
			if(count<programSize) {
				Simulator s = new Simulator();
				s.fetchInstruction.InstFetch();
				runningStages[0] = s;
				System.out.println(runningStages[0].fetchInstruction);
			}else runningStages[0] = null;
			
			if(IF_flush.getData().equals("1")) {
				runningStages[0] = null;
				IF_flush.setData("0");
				pcIncremented = pcBranch;
			}
			if(runningStages[1]!=null) System.out.println(runningStages[1].decodeInstruction+"\n"); 
			if(runningStages[2]!=null) System.out.println(runningStages[2].exec+"\n");
			if(runningStages[3]!=null) System.out.println(runningStages[3].accessMemory+"\n");
			if(runningStages[4]!=null) {System.out.println(runningStages[4].wBack+"\n");runningStages[4] = null;}
			if(hazard=='1') {
				runningStages[1] = null;
				hazard = '0';
			}
			count = Integer.parseInt(pcIncremented,2)/4;
			executed = count >= programSize && runningStages[0]==null && runningStages[1]==null &&
					runningStages[2]==null && runningStages[3]==null && runningStages[4]==null;
			cycles++;
		}
	}
	
	private static ArrayList<String> takeInstructions(){
		ArrayList<String> ins = new ArrayList<String>();
		read: while(true) {
			Scanner sc = new Scanner(System.in);
			System.out.println("Please type the instruction in binary: ");
			String s = sc.nextLine();
			if(s.length()!=32)
				System.out.println("Please write a 32-bit instruction!!");
			else ins.add(s);
			System.out.println("Type 'R' to run the instructions or 'C' to continue typing more instructions");
			String d = sc.nextLine();
			if(d.equals("R")) break read;
			while(!(d.equals("C"))) {
				System.out.println("Please type either only 'C' or 'R'");
				d = sc.nextLine();
			}
		}
		return ins;
	}
	
	public static String flipNeg(String data) {
		boolean found1 = false;
		String res = "";
		for(int i=data.length()-1;i>=0;i--){
			if(found1)
				res = data.charAt(i) == '0'?"1"+res:"0"+res;
			else{
				found1 = data.charAt(i) == '1'?true:false;
				res = ""+data.charAt(i) + res;
			}
		}
		return res;
	}
	
	public static void main(String[] args) {
		init(takeInstructions());
		run();
	}
		
}


