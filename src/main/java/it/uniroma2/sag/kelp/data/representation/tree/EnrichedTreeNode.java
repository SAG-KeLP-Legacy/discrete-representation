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

import it.uniroma2.sag.kelp.data.example.Example;

/**
 * Enriched Tree Node to add a generic Example within a Tree Node. It will be
 * used in further releases.
 * 
 * @author Danilo Croce
 */
public class EnrichedTreeNode extends TreeNode {

	public EnrichedTreeNode(int id, String label, TreeNode father) {
		super(id, label, father);
	}

	private static final long serialVersionUID = -5205880347397536639L;

	private Example example;

	private boolean isExampleSet;

	public Example getExample() {
		return example;
	}

	public boolean isExampleSet() {
		return isExampleSet;
	}

	public void setExample(Example example, float weight) {
		if (example != null) {
			this.example = example.clone();
		} else {
			this.example = null;
		}
		this.isExampleSet = true;
	}

	public void removeExample() {
		this.example = null;
		this.isExampleSet = false;
	}

	public void setLabel(String newLabel) {
		super.label = newLabel;
	}

}
