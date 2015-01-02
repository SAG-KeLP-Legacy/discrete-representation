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

import it.uniroma2.sag.kelp.data.representation.structure.StructureElement;
import it.uniroma2.sag.kelp.data.representation.structure.StructureElementFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * A TreeNode represents a generic node in a TreeRepresentation
 * 
 * @author Danilo Croce, Giuseppe Castellucci, Simone Filice
 * 
 */
public class TreeNode implements Serializable {

	private static final long serialVersionUID = 3112378816044567678L;
	
	
	
	private StructureElement content;
	
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
	 * A string encoding the node production, e.g. in (S(NP)(VP)) the production
	 * string is S->NP,VP
	 */
	private String production;
	
	public TreeNode(int id, StructureElement content, TreeNode father) {
		this.id = id;
		this.content = content;
		this.children = new ArrayList<TreeNode>();
		this.father = father;
	}
	
	public StructureElement getContent(){
		return this.content;
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
	 * the node production, e.g. in (S(NP)(VP)) the production string is S->NP,VP
	 * 
	 * @return the node production as a string
	 */
	public String getProduction() {
		if (production != null)
			return production;

		production = this.content.getTextFromData() + "->";

		for (TreeNode child : children) {
			production += child.getContent().getTextFromData() + " ";
		}

		return production;
	}

	public boolean hasChildren() {
		if (children != null && children.size() > 0)
			return true;
		return false;
	}

	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();
		b.append("(");
		b.append(StructureElementFactory.getTextualRepresentation(content));
		if (children != null && children.size() > 0) {
			for (TreeNode node : children) {
				b.append(node.toString());
			}
		}
		b.append(")");
		return b.toString().trim();
	}
	
}
