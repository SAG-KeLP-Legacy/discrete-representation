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

import it.uniroma2.sag.kelp.data.representation.tree.node.TreeNode;
import it.uniroma2.sag.kelp.data.representation.tree.utils.TypedTreeIO;

import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * The Typed Tree Representation used for example to represent the syntactic
 * tree of a sentence. Each node in the tree is typed. Node types are used
 * within Convolution Kernels in which the recursive delta function depends on
 * the similarity within tree nodes. As an example of Convolution kernels
 * depending from node types, see:
 * 
 * Danilo Croce, Alessandro Moschitti, Roberto Basili, Martha Palmer: Verb
 * Classification using Distributional Similarity in Syntactic and Semantic
 * Structures. ACL (1) 2012: 263-272
 * 
 * @author Danilo Croce
 */
@JsonTypeName("TT")
public class TypedTreeRepresentation extends TreeRepresentation {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6546347162300834793L;

	public TypedTreeRepresentation() {

	}

	/**
	 * Build a tree representation from a TreeNode
	 * 
	 * @param root
	 *            Root of the Tree
	 */
	public TypedTreeRepresentation(TreeNode root) {
		super.root = root;
		this.getOrderedNodeSetByLabel();
		this.getOrderedNodeSetByProduction();
	}

	@Override
	public void setDataFromText(String representationDescription)
			throws Exception {
		TypedTreeRepresentation i = new TypedTreeIO()
				.parseCharniakSentence(representationDescription);
		super.root = i.root;
		super.orderedNodeSetByLabel = i.orderedNodeSetByLabel;
		super.orderedNodeSetByProduction = i.orderedNodeSetByProduction;
	}

}
