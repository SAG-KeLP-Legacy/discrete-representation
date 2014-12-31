package it.uniroma2.sag.kelp.data.representation.tree.utils;

import it.uniroma2.sag.kelp.data.representation.tree.TypedTreeRepresentation;
import it.uniroma2.sag.kelp.data.representation.tree.node.NodeFactory;
import it.uniroma2.sag.kelp.data.representation.tree.node.TreeNode;

public class TypedTreeIO extends TreeIO {

	@Override
	protected TreeNode parseNode(int nodeCounter, String nodeStr,
			TreeNode father) {
		return NodeFactory.parseNode(nodeCounter, nodeStr, father);
	}

	/**
	 * This method allows to read a tree in the form (S(NP)(VP)) and returns the
	 * corresponding Tree Representation
	 * 
	 * @param sentence
	 *            The input Stirng
	 * @return The Tree Representation
	 * @throws TreeIOException
	 *             his exception is trown if any problem in the tree IO phase is
	 *             experimented
	 */
	public TypedTreeRepresentation parseCharniakSentence(String sentence)
			throws TreeIOException {
		String inputString = sentence.trim();
		inputString = _preprocess(inputString);
		inputString = inputString.replaceAll(" ", "");
		TreeNode root = _parseCharniakSentence(inputString, null, 1);
		return new TypedTreeRepresentation(root);
	}

}
