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

package it.uniroma2.sag.kelp.data.representation.tree.node;

import it.uniroma2.sag.kelp.data.example.Example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A TreeNode represents a generic node in a TreeRepresentation
 * 
 * @author Danilo Croce, Giuseppe Castellucci
 * 
 */
public class TreeNode implements Serializable {

	private static final long serialVersionUID = 8192423989557300729L;

	/**
	 * The node label
	 */
	protected String label;

	/**
	 * The node identifier, used in the tree kernel implementations to access
	 * caches. It MUST be unique for each node in the tree.
	 */
	private Integer id;

	/**
	 * The father of the node within the hosting tree representation
	 */
	private TreeNode father;

	/**
	 * The children nodes
	 */
	private ArrayList<TreeNode> children;

	/**
	 * A string encoding the node production, e.g. in (S(NP)(VP) the production
	 * string is S->NP,VP
	 */
	private String production;

	/**
	 * Additional representation that can be attached to a node. For example a
	 * node can be eniched with a vector.
	 */
	private Example additionalRepresentation;

	/**
	 * True if the additional representation has been set
	 */
	private boolean isAdditionalRepresentationSet;

	public TreeNode(int id, String label, TreeNode father) {
		this.id = id;
		this.label = label;
		this.children = new ArrayList<TreeNode>();
		this.father = father;
	}

	/**
	 * @return The additional representation
	 */
	public Example getAdditionalRepresentation() {
		return additionalRepresentation;
	}

	/**
	 * Get recursively all Tree Nodes below the target node
	 * 
	 * @return the list of all nodes
	 */
	public List<TreeNode> getAllNodes() {
		ArrayList<TreeNode> res = new ArrayList<TreeNode>();
		res.add(this);
		for (TreeNode child : children) {
			res.addAll(child.getAllNodes());
		}
		return res;
	}

	/**
	 * Get the direct children of the target node
	 * 
	 * @return the children of the target node
	 */
	public ArrayList<TreeNode> getChildren() {
		return children;
	}

	/**
	 * Get the father of the target node
	 * 
	 * @return the node father. Null if the target node does not have any
	 *         father.
	 */
	public TreeNode getFather() {
		return father;
	}

	public Integer getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}

	/**
	 * Get the max id within all node under the target node
	 * 
	 * @return the max id value
	 */
	public Integer getMaxId() {
		int resId = this.id;
		for (TreeNode child : getChildren()) {
			int childId = child.getMaxId();
			if (childId > resId) {
				resId = childId;
			}
		}
		return resId;
	}

	public int getNoOfChildren() {
		if (children == null)
			return 0;
		return children.size();
	}

	/**
	 * Get the node production in the form of string. It is a string encoding
	 * the node production, e.g. in (S(NP)(VP) the production string is S->NP,VP
	 * 
	 * @return the node production as a string
	 */
	public String getProduction() {
		if (production != null)
			return production;

		production = getLabel() + "->";

		for (TreeNode child : children) {
			production += child.getLabel() + " ";
		}

		return production;
	}

	@JsonIgnore
	public String getType() {
		return "NO";
	}

	public boolean hasChildren() {
		if (children != null && children.size() > 0)
			return true;
		return false;
	}

	public boolean isAdditionalRepresentationSet() {
		return isAdditionalRepresentationSet;
	}

	/**
	 * @param additionalRepresentation
	 *            the additional representation
	 */
	public void setAdditionalRepresentation(Example additionalRepresentation) {
		this.additionalRepresentation = additionalRepresentation;
		this.isAdditionalRepresentationSet = true;
	}

	public void setAdditionalRepresentationSet(
			boolean isAdditionalRepresentationSet) {
		this.isAdditionalRepresentationSet = isAdditionalRepresentationSet;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();
		b.append("(");
		b.append(getType() + "::" + getLabel());
		if (children != null && children.size() > 0) {
			for (TreeNode node : children) {
				b.append(node.toString());
			}
		}
		b.append(")");
		return b.toString().trim();
	}

}
