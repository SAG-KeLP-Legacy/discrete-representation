/*
 * Copyright 2014 Simone Filice and Giuseppe Castellucci and Danilo Croce and Roberto Basili
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package it.uniroma2.sag.kelp.data.representation.tree;

import it.uniroma2.sag.kelp.data.representation.Representation;
import it.uniroma2.sag.kelp.data.representation.tree.utils.TreeIO;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.SerializationUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Tree Representation used for example to represent the syntactic tree of a
 * sentence. It can be exploited by convolution kernels Nodes are labeled while
 * edges are not
 * 
 * @author Danilo Croce, Giuseppe Castellucci
 */
@JsonTypeName("T")
public class TreeRepresentation implements Representation {

	private static final long serialVersionUID = 5856527731146702094L;

	/**
	 * The root node of the tree
	 */
	private TreeNode root;

	/**
	 * The complete set of tree nodes ordered alphabetically by label. Used by
	 * several Tree Kernel functions.
	 */
	private TreeNode[] orderedNodeSetByLabel;

	/**
	 * The complete set of tree nodes ordered alphabetically by production
	 * string. Used by several Tree Kernel functions.
	 */
	private TreeNode[] orderedNodeSetByProduction;
	/**
	 * The complete set of labels of tree nodes ordered alphabetically. Used by
	 * several Tree Kernel functions.
	 */
	private String nodeNamesSorted[];

	/**
	 * The complete set of indexes of tree nodes ordered alphabetically. Used by
	 * several Tree Kernel functions.
	 */
	private int[] nodeIdsSortedByName;

	/**
	 * The complete set of indexes of tree nodes ordered alphabetically by node
	 * production. Used by several Tree Kernel functions.
	 */
	private int[] nodeIdsSortedByProduction;

	/**
	 * The complete set of production names ordered alphabetically. Used by
	 * several Tree Kernel functions.
	 */
	private String productionNamesSorted[];

	/**
	 * Compare tree nodes by Label
	 */
	private static final Comparator<TreeNode> AlphabeticalLabelComparator = new Comparator<TreeNode>() {
		public int compare(TreeNode e1, TreeNode e2) {
			return e1.getLabel().compareTo(e2.getLabel());
		}
	};

	/**
	 * Compare tree nodes by Production
	 */
	private static final Comparator<TreeNode> AlphabeticalProductionComparator = new Comparator<TreeNode>() {
		public int compare(TreeNode e1, TreeNode e2) {
			return e1.getProduction().compareTo(e2.getProduction());
		}
	};

	public TreeRepresentation() {

	}

	/**
	 * Build a tree representation from a TreeNode
	 * 
	 * @param root
	 *            Root of the Tree
	 */
	public TreeRepresentation(TreeNode root) {
		this.root = root;

		List<TreeNode> allNodes = root.getAllNodes();

		TreeNode res[] = new TreeNode[allNodes.size()];
		res = allNodes.toArray(res);
		Arrays.sort(res, AlphabeticalLabelComparator);

		orderedNodeSetByLabel = new TreeNode[res.length];
		for (int i = 0; i < res.length; i++) {
			orderedNodeSetByLabel[i] = res[i];
		}
		nodeNamesSorted = new String[orderedNodeSetByLabel.length];
		nodeIdsSortedByName = new int[orderedNodeSetByLabel.length];
		for (int i = 0; i < orderedNodeSetByLabel.length; i++) {
			nodeNamesSorted[i] = orderedNodeSetByLabel[i].getLabel();
			nodeIdsSortedByName[i] = orderedNodeSetByLabel[i].getId();
		}
		Arrays.sort(res, AlphabeticalProductionComparator);
		orderedNodeSetByProduction = new TreeNode[res.length];
		for (int i = 0; i < res.length; i++) {
			orderedNodeSetByProduction[i] = res[i];
		}

		productionNamesSorted = new String[orderedNodeSetByProduction.length];
		nodeIdsSortedByProduction = new int[orderedNodeSetByProduction.length];
		for (int i = 0; i < orderedNodeSetByLabel.length; i++) {
			productionNamesSorted[i] = orderedNodeSetByProduction[i]
					.getProduction();
			nodeIdsSortedByProduction[i] = orderedNodeSetByProduction[i]
					.getId();
		}
	}

	@Override
	public TreeRepresentation clone() {
		return SerializationUtils.clone(this);
	}

	@Override
	public boolean equals(Object representation) {
		if (representation == null) {
			return false;
		}
		if (this == representation) {
			return true;
		}
		if (representation instanceof TreeRepresentation) {
			TreeRepresentation that = (TreeRepresentation) representation;
			return that.toString().equals(this.toString());
		}
		return false;
	}

	@JsonIgnore
	public int[] getNodeIdsSortedByName() {
		return nodeIdsSortedByName;
	}

	@JsonIgnore
	public int[] getNodeIdsSortedByProduction() {
		return nodeIdsSortedByProduction;
	}

	@JsonIgnore
	public String[] getNodeNames() {
		return nodeNamesSorted;
	}

	@JsonIgnore
	public TreeNode[] getOrderedNodeSetByLabel() {
		return orderedNodeSetByLabel;
	}

	@JsonIgnore
	public TreeNode[] getOrderedNodeSetByProduction() {
		return orderedNodeSetByProduction;
	}

	@JsonIgnore
	public String[] getProductionNames() {
		return productionNamesSorted;
	}

	@JsonIgnore
	public TreeNode getRoot() {
		return root;
	}

	@Override
	public String getTextFromData() {
		return this.toString();
	}

	@Override
	public int hashCode() {
		return this.toString().hashCode();
	}

	@Override
	public void setDataFromText(String representationDescription)
			throws Exception {
		TreeRepresentation i = TreeIO
				.parseCharniakSentence(representationDescription);
		this.root = i.root;
		this.orderedNodeSetByLabel = i.orderedNodeSetByLabel;
		this.orderedNodeSetByProduction = i.orderedNodeSetByProduction;
		this.nodeNamesSorted = i.nodeNamesSorted;
		this.nodeIdsSortedByName = i.nodeIdsSortedByName;
		this.productionNamesSorted = i.productionNamesSorted;
		this.nodeIdsSortedByProduction = i.nodeIdsSortedByProduction;
	}

	@Override
	public String toString() {
		return root.toString();
	}

	/**
	 * Get the max id within all nodes
	 * 
	 * @return the max id value
	 */
	public Integer getMaxId() {
		return root.getMaxId();
	}

}
