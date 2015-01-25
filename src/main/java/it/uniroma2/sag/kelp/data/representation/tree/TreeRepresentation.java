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
import it.uniroma2.sag.kelp.data.representation.tree.node.TreeNode;
import it.uniroma2.sag.kelp.data.representation.tree.utils.TreeIO;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.SerializationUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Tree Representation used for example to represent the syntactic tree of a
 * sentence. It can be exploited by convolution kernels
 * 
 * @author Danilo Croce, Giuseppe Castellucci
 */
@JsonTypeName("T")
public class TreeRepresentation implements Representation {

	private static final long serialVersionUID = 5856527731146702094L;

	/**
	 * The root node of the tree
	 */
	protected TreeNode root;

	/**
	 * The complete set of tree nodes ordered alphabetically by label. Used by
	 * several Tree Kernel functions.
	 */
	protected List<TreeNode> orderedNodeSetByLabel;

	/**
	 * The complete set of tree nodes ordered alphabetically by production
	 * string. Used by several Tree Kernel functions.
	 */
	protected List<TreeNode> orderedNodeSetByProduction;

	/**
	 * Compare tree nodes by Label
	 */
	private static final Comparator<TreeNode> AlphabeticalLabelComparator = new Comparator<TreeNode>() {
		public int compare(TreeNode e1, TreeNode e2) {
			return e1.getContent().getTextFromData().compareTo(e2.getContent().getTextFromData());
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
		this.getOrderedNodeSetByLabel();
		this.getOrderedNodeSetByProduction();
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

	/**
	 * 
	 * @return the complete set of nodes
	 */
	@JsonIgnore
	public List<TreeNode> getAllNodes() {
		return root.getAllNodes();
	}

	/**
	 * Get the max id within all nodes
	 * 
	 * @return the max id value
	 */
	@JsonIgnore
	public Integer getMaxId() {
		return root.getMaxId();
	}

	/**
	 * @return the complete set of nodes ordered alphabetically by the node
	 *         label
	 */
	@JsonIgnore
	public List<TreeNode> getOrderedNodeSetByLabel() {
		if (this.orderedNodeSetByLabel == null) {
			orderedNodeSetByLabel = root.getAllNodes();

			Collections
					.sort(orderedNodeSetByLabel, AlphabeticalLabelComparator);
		}
		return orderedNodeSetByLabel;
	}

	/**
	 * @return the complete set of nodes ordered alphabetically by production
	 *         string
	 */
	@JsonIgnore
	public List<TreeNode> getOrderedNodeSetByProduction() {
		if (this.orderedNodeSetByProduction == null) {
			orderedNodeSetByProduction = root.getAllNodes();

			Collections.sort(orderedNodeSetByProduction,
					AlphabeticalProductionComparator);
		}
		return orderedNodeSetByProduction;
	}

	/**
	 * @return the tree root
	 */
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
		TreeRepresentation i = new TreeIO()
				.parseCharniakSentence(representationDescription);
		this.root = i.root;
		this.orderedNodeSetByLabel = i.orderedNodeSetByLabel;
		this.orderedNodeSetByProduction = i.orderedNodeSetByProduction;
	}

	@Override
	public String toString() {
		return root.toString();
	}
}
