package it.uniroma2.sag.kelp.data.representation.structure;

public class MainNewTreeProva {

	public static void main(String[] args) throws Exception {
		String tree1desc = "(ROOT(PRD(WRB(how::w))(AMOD(RB(far::r))))(VBZ(be::v))(SBJ(PRP(it::p)))(TMP(IN(from::i))(PMOD(NNP(denver::n)))(PMOD(TO(to::t))(PMOD(NNP(aspen::n))))))";
		TreeRepresentation tree1 = new TreeRepresentation();
		tree1.setDataFromText(tree1desc);
		
		String tree2desc = tree1.toString();
		System.out.println(tree2desc);
		TreeRepresentation tree2 = new TreeRepresentation();
		tree2.setDataFromText(tree2desc);
		System.out.println(tree2.toString());
	}

}
