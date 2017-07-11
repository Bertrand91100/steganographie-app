package com.scholanova.groupe2.bertrand;




public class Main {



	public static void main(String[] args) {
		// TODO code application logic here
		
		DissimulationMessage dissimulation = new DissimulationMessage();		
		dissimulation.dissimulation();
		int size = dissimulation.getSize();
		
		RevelationMessage show = new RevelationMessage();
		show.revelation(size);
		
	}
}