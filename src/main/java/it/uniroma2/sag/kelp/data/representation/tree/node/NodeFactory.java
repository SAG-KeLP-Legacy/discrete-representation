package it.uniroma2.sag.kelp.data.representation.tree.node;

public class NodeFactory {

	public static TreeNode parseNode(int nodeCounter, String nodeStr,
			TreeNode father) {
		if (nodeStr.startsWith("LEX::")) {
			return new LexTreeNode(nodeCounter,
					nodeStr.replaceAll("^LEX::", ""), father);
		} else if (nodeStr.startsWith("SYNT::")) {
			return new SyntTreeNode(nodeCounter, nodeStr.replaceAll("^SYNT::",
					""), father);
		} else if (nodeStr.startsWith("POS::")) {
			return new PosTreeNode(nodeCounter,
					nodeStr.replaceAll("^POS::", ""), father);
		}

		/*
		 * TO FIX
		 * 
		 * THIS IS A LEGACY TO READ OLD FILE WHERE STRONG TYPES WERE NOT USED
		 */
		if (nodeStr.contains("::")) {
			return new LexTreeNode(nodeCounter, nodeStr, father);
		} else {
			return new SyntTreeNode(nodeCounter, nodeStr, father);
		}
	}

}
