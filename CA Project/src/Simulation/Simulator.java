package Simulation;

import Stages.AccessMemory;
import Stages.DecodeInstruction;
import Stages.Execute;
import Stages.FetchInstruction;
import Stages.WriteBack;

public class Simulator {
	FetchInstruction fetchInstruction;
	DecodeInstruction decodeInstruction;
	Execute exec;
	AccessMemory accessMemory;
	WriteBack wBack;
	
	public Simulator() {
		this.fetchInstruction = new FetchInstruction();
		this.decodeInstruction = new DecodeInstruction();
		this.exec = new Execute();
		this.accessMemory = new AccessMemory();
		this.wBack = new WriteBack();
	}
	
}
