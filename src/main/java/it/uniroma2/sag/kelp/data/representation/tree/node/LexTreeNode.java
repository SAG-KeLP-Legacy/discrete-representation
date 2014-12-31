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


/**
 * A TreeNode represents a lexical node in a TypedTreeRepresentation
 * 
 * @author Danilo Croce, Giuseppe Castellucci
 * 
 */
public class LexTreeNode extends TreeNode {

	private String lemma;

	private String pos;

	public LexTreeNode(int id, String label, TreeNode father) {
		super(id, label, father);

		String[] split = label.split("::");
		lemma = split[0];
		pos = split[1];

		setLabel(lemma + "::" + pos);
	}

	private static final long serialVersionUID = -255503614734174685L;

	public String getLemma() {
		return lemma;
	}

	public void setLemma(String lemma) {
		this.lemma = lemma;
	}

	public String getPos() {
		return pos;
	}

	public void setPos(String pos) {
		this.pos = pos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String getType() {
		return "LEX";
	}

}
