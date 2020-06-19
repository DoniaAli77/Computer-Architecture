package Components;

import Simulation.Program;

public class ALU {
	public String aluEvaluator(String aluOp,
			String r1, String r2, String r3, String r4, 
			String shift,String no,char np){
		if(aluOp.equals("0000"))
			return add(r1, r2, r3, r4,no,np);
		else if(aluOp.equals("0001"))
			return sub(r1,r2,r3,r4,no,np);	
		else if(aluOp.equals("0010"))
			return and(r1,r2,r3,r4,no,np);	
		else if(aluOp.equals("0011"))
			return mul(r1,r2,r3,r4,no,np);
		else if(aluOp.equals("1001"))
			return ori(r1,r2);
//		else if(aluOp.equals("0100"))
//			return branchnotequal(r1,r2,r3,no,np);
//		else if(aluOp.equals("0110"))
//			return branchgreater(r1,r2,r3,no,np);
		else if(aluOp.equals("0101"))
			return setlessthan(r1,r3,np);
		else if(aluOp.equals("1110"))
			return shiftleft(r1,shift,np);
		else if(aluOp.equals("1111"))
			return shiftright(r1,shift,np);
		
		return "opcode is wrong :) ";	
	}
	
	private String add(String r1, String r2, String r3, String r4,String no,char np) {
		int num=Integer.parseInt(no,2);
		int answer=0;
		int one=0;
		int two=0;
		int three=0;
		int four=0;
		if(num==0) {
			one= r1.charAt(0)=='1'?-1*Integer.parseInt(Program.flipNeg(r1),2):Integer.parseInt(r1, 2);
			answer=one;
		}
		else if(num==1){
			one= r1.charAt(0)=='1'?-1*Integer.parseInt(Program.flipNeg(r1),2):Integer.parseInt(r1, 2);
			two= r2.charAt(0)=='1'?-1*Integer.parseInt(Program.flipNeg(r2),2):Integer.parseInt(r2, 2);
			answer=one+two;
		}
		else if(num==2) {
			one= r1.charAt(0)=='1'?-1*Integer.parseInt(Program.flipNeg(r1),2):Integer.parseInt(r1, 2);
			two= r2.charAt(0)=='1'?-1*Integer.parseInt(Program.flipNeg(r2),2):Integer.parseInt(r2, 2);
			three= r3.charAt(0)=='1'?-1*Integer.parseInt(Program.flipNeg(r3),2):Integer.parseInt(r3, 2);
			answer=one+two+three;
		}
		else if(num==3) {
			one= r1.charAt(0)=='1'?-1*Integer.parseInt(Program.flipNeg(r1),2):Integer.parseInt(r1, 2);
			two= r2.charAt(0)=='1'?-1*Integer.parseInt(Program.flipNeg(r2),2):Integer.parseInt(r2, 2);
			three= r3.charAt(0)=='1'?-1*Integer.parseInt(Program.flipNeg(r3),2):Integer.parseInt(r3, 2);
			four= r4.charAt(0)=='1'?-1*Integer.parseInt(Program.flipNeg(r4),2):Integer.parseInt(r4, 2);
			answer=one+two+three+four;
		}
		
		if (np=='1') {
			answer=answer*-1;
		}
		
		return String.format("%32s", Integer.toBinaryString(answer)).replaceAll(" ", "0");			
	}
	
	private String sub(String r1, String r2, String r3, String r4,String no,char np) {
		int num=Integer.parseInt(no,2);
		int answer=0;
		int one=0;
		int two=0;
		int three=0;
		int four=0;
		if(num==0) {
			one= r1.charAt(0)=='1'?-1*Integer.parseInt(Program.flipNeg(r1),2):Integer.parseInt(r1, 2);
			answer=one;
		}
		else if(num==1){
			one= r1.charAt(0)=='1'?-1*Integer.parseInt(Program.flipNeg(r1),2):Integer.parseInt(r1, 2);
			two= r2.charAt(0)=='1'?-1*Integer.parseInt(Program.flipNeg(r2),2):Integer.parseInt(r2, 2);
			answer=one-two;
		}
		else if(num==2) {
			one= r1.charAt(0)=='1'?-1*Integer.parseInt(Program.flipNeg(r1),2):Integer.parseInt(r1, 2);
			two= r2.charAt(0)=='1'?-1*Integer.parseInt(Program.flipNeg(r2),2):Integer.parseInt(r2, 2);
			three= r3.charAt(0)=='1'?-1*Integer.parseInt(Program.flipNeg(r3),2):Integer.parseInt(r3, 2);
			answer=one-two-three;
		}
		else if(num==3) {
			one= r1.charAt(0)=='1'?-1*Integer.parseInt(Program.flipNeg(r1),2):Integer.parseInt(r1, 2);
			two= r2.charAt(0)=='1'?-1*Integer.parseInt(Program.flipNeg(r2),2):Integer.parseInt(r2, 2);
			three= r3.charAt(0)=='1'?-1*Integer.parseInt(Program.flipNeg(r3),2):Integer.parseInt(r3, 2);
			four= r4.charAt(0)=='1'?-1*Integer.parseInt(Program.flipNeg(r4),2):Integer.parseInt(r4, 2);
			answer=one-two-three-four;
		}
		if (np=='1') {
			answer=answer*-1;
		}
		return String.format("%32s", Integer.toBinaryString(answer)).replaceAll(" ", "0");
	}
	
	private String and(String r1, String r2, String r3, String r4,String no, char np) {
		int num=Integer.parseInt(no,2);
		int answer=0;
		int one=0;
		int two=0;
		int three=0;
		int four=0;
		if(num==0) {
			one= r1.charAt(0)=='1'?-1*Integer.parseInt(Program.flipNeg(r1),2):Integer.parseInt(r1, 2);
			answer=one;
		}
		else if(num==1)
		{
			one= r1.charAt(0)=='1'?-1*Integer.parseInt(Program.flipNeg(r1),2):Integer.parseInt(r1, 2);
			two= r2.charAt(0)=='1'?-1*Integer.parseInt(Program.flipNeg(r2),2):Integer.parseInt(r2, 2);
			answer=one&two;
		}
		else if(num==2) {
			one= r1.charAt(0)=='1'?-1*Integer.parseInt(Program.flipNeg(r1),2):Integer.parseInt(r1, 2);
			two= r2.charAt(0)=='1'?-1*Integer.parseInt(Program.flipNeg(r2),2):Integer.parseInt(r2, 2);
			answer=one&two&three;
		}
		else if(num==3) {
			one= r1.charAt(0)=='1'?-1*Integer.parseInt(Program.flipNeg(r1),2):Integer.parseInt(r1, 2);
			two= r2.charAt(0)=='1'?-1*Integer.parseInt(Program.flipNeg(r2),2):Integer.parseInt(r2, 2);
			three= r3.charAt(0)=='1'?-1*Integer.parseInt(Program.flipNeg(r3),2):Integer.parseInt(r3, 2);
			four= r4.charAt(0)=='1'?-1*Integer.parseInt(Program.flipNeg(r4),2):Integer.parseInt(r4, 2);
			answer=one&two&three&four;
		}
		String answerString = String.format("%32s", Integer.toBinaryString(answer)).replaceAll(" ", "0");
		
		if (np=='1') {
			return flipBits(answerString);
		}
		return answerString;
	}
	
	private String mul(String r1, String r2, String r3, String r4,String no, char np) {
		int num=Integer.parseInt(no,2);
		int answer=0;
		int one=0;
		int two=0; 
		int three=0;
		int four=0;
		if(num==0) {
			one= r1.charAt(0)=='1'?-1*Integer.parseInt(Program.flipNeg(r1),2):Integer.parseInt(r1, 2);
			answer=one;
				}
		else if(num==1)
		{
			one= r1.charAt(0)=='1'?-1*Integer.parseInt(Program.flipNeg(r1),2):Integer.parseInt(r1, 2);
			two= r2.charAt(0)=='1'?-1*Integer.parseInt(Program.flipNeg(r2),2):Integer.parseInt(r2, 2);
			answer=one*two;
		}
		else if(num==2) {
			one= r1.charAt(0)=='1'?-1*Integer.parseInt(Program.flipNeg(r1),2):Integer.parseInt(r1, 2);
			two= r2.charAt(0)=='1'?-1*Integer.parseInt(Program.flipNeg(r2),2):Integer.parseInt(r2, 2);
			three= r3.charAt(0)=='1'?-1*Integer.parseInt(Program.flipNeg(r3),2):Integer.parseInt(r3, 2);
			answer=one*two*three;
		}
		else if(num==3) {
			one= r1.charAt(0)=='1'?-1*Integer.parseInt(Program.flipNeg(r1),2):Integer.parseInt(r1, 2);
			two= r2.charAt(0)=='1'?-1*Integer.parseInt(Program.flipNeg(r2),2):Integer.parseInt(r2, 2);
			three= r3.charAt(0)=='1'?-1*Integer.parseInt(Program.flipNeg(r3),2):Integer.parseInt(r3, 2);
			four= r4.charAt(0)=='1'?-1*Integer.parseInt(Program.flipNeg(r4),2):Integer.parseInt(r4, 2);
			answer=one*two*three*four;
		
		}
		if (np=='1') {
			answer=answer*-1;
		}
		return String.format("%32s", Integer.toBinaryString(answer)).replaceAll(" ", "0");
	}
	 
	private String ori(String r1, String r2) {
		int one= r1.charAt(0)=='1'?-1*Integer.parseInt(Program.flipNeg(r1),2):Integer.parseInt(r1, 2);
		int two= r2.charAt(0)=='1'?-1*Integer.parseInt(Program.flipNeg(r2),2):Integer.parseInt(r2, 2);
		int answer=0;
		answer= one|two;
		return String.format("%32s", Integer.toBinaryString(answer)).replaceAll(" ", "0");
	}
	
//	private String branchnotequal(String r1, String r2, String r3, String n, char resornot) {
//		int one=0;
//		int two=0;
//		int three= 0;
//		int num= Integer.parseInt(n,2);
//		String answer="";
//		if(num==2) {		
//			one= r1.charAt(0)=='1'?-1*Integer.parseInt(Program.flipNeg(r1),2):Integer.parseInt(r1, 2);
//			two= r2.charAt(0)=='1'?-1*Integer.parseInt(Program.flipNeg(r2),2):Integer.parseInt(r2, 2);
//			three= r3.charAt(0)=='1'?-1*Integer.parseInt(Program.flipNeg(r3),2):Integer.parseInt(r3, 2);
//			answer= one!=two&& two!=three&& one!=three?"00000000000000000000000000000001":
//				"00000000000000000000000000000000";
//		}else if(num==1) {
//			one= r1.charAt(0)=='1'?-1*Integer.parseInt(Program.flipNeg(r1),2):Integer.parseInt(r1, 2);
//			two= r2.charAt(0)=='1'?-1*Integer.parseInt(Program.flipNeg(r2),2):Integer.parseInt(r2, 2);
//			answer= one!=two?"00000000000000000000000000000001":
//				"00000000000000000000000000000000";
//		}
//		if(resornot == '1') answer = answer.charAt(31)=='0'?"00000000000000000000000000000001":
//			"00000000000000000000000000000000";
//		ZERO = answer.charAt(31)=='0'?'1':'0';
//		return answer;
//	}
//	
//	private String branchgreater(String r1, String r2, String r3, String n, char resornot) {
//		int one=0;
//		int two=0;
//		int three= 0;
//		int num= Integer.parseInt(n,2);
//		String answer="";
//		if(num==2) {	
//			one= r1.charAt(0)=='1'?-1*Integer.parseInt(Program.flipNeg(r1),2):Integer.parseInt(r1, 2);
//			two= r2.charAt(0)=='1'?-1*Integer.parseInt(Program.flipNeg(r2),2):Integer.parseInt(r2, 2);
//			three= r3.charAt(0)=='1'?-1*Integer.parseInt(Program.flipNeg(r3),2):Integer.parseInt(r3, 2);
//			answer= one>two&& two>three?"00000000000000000000000000000001":
//				"00000000000000000000000000000000";
//		}else if(num==1) {
//			one= r1.charAt(0)=='1'?-1*Integer.parseInt(Program.flipNeg(r1),2):Integer.parseInt(r1, 2);
//			two= r2.charAt(0)=='1'?-1*Integer.parseInt(Program.flipNeg(r2),2):Integer.parseInt(r2, 2);
//			answer= one>two?"00000000000000000000000000000001":
//				"00000000000000000000000000000000";
//		}
//		if(resornot == '1') answer = answer.charAt(31)=='0'?"00000000000000000000000000000001":
//			"00000000000000000000000000000000";
//		ZERO = answer.charAt(31)=='0'?'1':'0';
//		return answer;
//	}

	


	private String setlessthan(String r1, String r2, char resornot) {
		String answer="";
		int one= r1.charAt(0)=='1'?-1*Integer.parseInt(Program.flipNeg(r1),2):Integer.parseInt(r1, 2);
		int two= r2.charAt(0)=='1'?-1*Integer.parseInt(Program.flipNeg(r2),2):Integer.parseInt(r2, 2);
		answer = one<two?"00000000000000000000000000000001":
			"00000000000000000000000000000000";
		if(resornot == '1') answer = answer.charAt(31) == '0'?"00000000000000000000000000000001":
			"00000000000000000000000000000000";
		return answer;	
	}
	

	
	private String shiftleft(String r1, String shift, char np) {
		int one= r1.charAt(0)=='1'?-1*Integer.parseInt(Program.flipNeg(r1),2):Integer.parseInt(r1, 2);
		int shft=Integer.parseInt(shift,2);
		int answer=0;
		answer=one<<shft;
		if(np=='1') {
			answer=answer*-1;
		}
		return String.format("%32s", Integer.toBinaryString(answer)).replaceAll(" ", "0");
	}
	
	
	private String shiftright(String r1, String shift, char np) {
		int one= r1.charAt(0)=='1'?-1*Integer.parseInt(Program.flipNeg(r1),2):Integer.parseInt(r1, 2);
		int shft=Integer.parseInt(shift,2);
		int answer=0;
		answer=one>>shft;
		if(np=='1') {
			answer=answer*-1;
		}
		return String.format("%32s", Integer.toBinaryString(answer)).replaceAll(" ", "0");
	}
		
	private static String flipBits(String s) {
		String an = "";
		for(int i=0;i<32;i++)
			an += s.charAt(i) == '1'?"0":"1";
		return an;
	}
		
}
