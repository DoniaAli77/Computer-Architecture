package Stages;

import Simulation.Program;

public class FetchInstruction {
	private String instruction;
	private String flush;
	
	public void InstFetch() {
		Program.pcReg.setData(Program.pcIncremented);
		instruction = Program.instructionMemory.getValue(Integer.parseInt(Program.pcReg.getData(),2)/4,0,"");
		Program.pcIncremented = String.format("%32s",
				Integer.toBinaryString(Integer.parseInt(Program.pcIncremented,2)+4)).replaceAll(" ", "0");
		flush = Program.IF_flush.getData();
		setInfoIF();
	}
	
	private void setInfoIF() {
		Program.IF_ID.setInfo("Instruction", instruction);
		Program.IF_ID.setInfo("PCIncremented", Program.pcIncremented);
		Program.IF_ID.setInfo("flush", flush);
	}
		
	public String toString() {
		String string = "Instruction Fetch Stage:\n---------------------------\nNext PC: "+ Program.pcIncremented + "\n"+
						"Instruction: "+ instruction;
		return string;
	}
}
