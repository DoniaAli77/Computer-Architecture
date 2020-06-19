package Components;

public class RegisterFile {
	private String wrReg;
	private String RR1;
	private String RR2;
	private String RR3;
	private String RR4;
	private String RD1;
	private String RD2;
	private String RD3;
	private String RD4;
	private String wD;
	private Register[] regs;
	
	public RegisterFile() {
		regs = new Register[32];
		for(int i=0;i<32;i++) {
			regs[i] = new Register(32);
			regs[i].setData(i==1?"00000000000000000000000000000001":
				"00000000000000000000000000000000");
		}
		this.wrReg = "00000";
		this.RR1 = "00000";
		this.RR2 = "00000";
		this.RR3 = "00000";
		this.RR4 = "00000";
		this.RD1 = "00000000000000000000000000000000";
		this.RD2 = "00000000000000000000000000000000";
		this.RD3 = "00000000000000000000000000000000";
		this.RD4 = "00000000000000000000000000000000";
		this.wD =  "00000000000000000000000000000000";
	}

	public String getWrReg() {
		return wrReg;
	}

	public void setWrReg(String wrReg) {
		this.wrReg = wrReg;
	}

	public String getRR1() {
		return RR1;
	}

	public void setRR1(String rR1) {
		RR1 = rR1;
//		setRD1(regs[Integer.parseInt(RR1,2)].getData());
	}

	public String getRR2() {
		return RR2;
	}

	public void setRR2(String rR2) {
		RR2 = rR2;
//		setRD2(regs[Integer.parseInt(RR2,2)].getData());
	}

	public String getRR3() {
		return RR3;
	}

	public void setRR3(String rR3) {
		RR3 = rR3;
//		setRD3(regs[Integer.parseInt(RR3,2)].getData());
	}

	public String getRR4() {
		return RR4;
	}

	public void setRR4(String rR4) {
		RR4 = rR4;
//		setRD4(regs[Integer.parseInt(RR4,2)].getData());
	}

	public String getRD1() {
		return RD1;
	}

	public void setRD1(int index) {
		RD1 = regs[index].getData();
	}

	public String getRD2() {
		return RD2;
	}

	public void setRD2(int index) {
		RD2 = regs[index].getData();
	}

	public String getRD3() {
		return RD3;
	}

	public void setRD3(int index) {
		RD3 = regs[index].getData();
	}

	public String getRD4() {
		return RD4;
	}

	public void setRD4(int index) {
		RD4 = regs[index].getData();
	}

	public String getwD() {
		return wD;
	}

	public void setwD(String wD) {
		if(this.wrReg.equals("00000"))
			this.wD = "00000000000000000000000000000000";
		else if(this.wrReg.equals("00001"))
			this.wD = "00000000000000000000000000000001";
		else {
			this.wD = wD;
			this.regs[Integer.parseInt(this.wrReg,2)].setData(this.wD);
		}
	}
}
