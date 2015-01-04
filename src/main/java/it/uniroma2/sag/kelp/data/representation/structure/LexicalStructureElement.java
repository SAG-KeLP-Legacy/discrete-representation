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

package it.uniroma2.sag.kelp.data.representation.structure;

import it.uniroma2.sag.kelp.data.example.Example;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * It represent a StuctureElement that contains lexical information, i.e.
 * represent a word (the <code>lemma</code>) and a Part-of-Speech (the
 * <code>pos</code>).
 * 
 * @author Simone Filice, Danilo Croce 
 * 
 */
@JsonTypeName("LEX")
public class LexicalStructureElement implements StructureElement {

	public static final String POS_LEMMA_SEPARATOR = "::";

	/**
	 * The lemma of the represented word
	 */
	private String lemma;

	/**
	 * The Part-of-Speech of the represented word
	 */
	private String pos;

	/**
	 * Additional representation that can be attached to a node. For example a
	 * node can be eniched with a vector.
	 */
	private Example additionalRepresentation;

	/**
	 * True if the additional representation has been set
	 */
	private boolean isAdditionalRepresentationSet;

	@JsonIgnore
	private String textFromData;

	public LexicalStructureElement() {

	}

	/**
	 * @param lemma
	 *            The lemma of the represented word
	 * @param pos
	 *            The Part-of-Speech of the represented word
	 */
	public LexicalStructureElement(String lemma, String pos) {
		this.lemma = lemma;
		this.pos = pos;
		updateTextFromData();
	}

	/**
	 * @return The additional representation
	 */
	public Example getAdditionalRepresentation() {
		return additionalRepresentation;
	}

	/**
	 * @return The lemma of the represented word
	 */
	public String getLemma() {
		return lemma;
	}

	/**
	 * @return The Part-of-Speech of the represented word
	 */
	public String getPos() {
		return pos;
	}

	@Override
	public String getTextFromData() {
		if (textFromData == null) {
			updateTextFromData();
		}
		return textFromData;
	}

	/**
	 * @return <code>true</code> if the <code>additionalRepresentation</code>
	 *         has been set. <code>false</code> otherwise
	 */
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

	/**
	 * @param isAdditionalRepresentationSet
	 *            <code>true</code> if the <code>additionalRepresentation</code>
	 *            has been set. <code>false</code> otherwise
	 */
	public void setAdditionalRepresentationSet(
			boolean isAdditionalRepresentationSet) {
		this.isAdditionalRepresentationSet = isAdditionalRepresentationSet;
	}

	@Override
	public void setDataFromText(String structureElementDescription)
			throws Exception {
		int separator = structureElementDescription
				.indexOf(POS_LEMMA_SEPARATOR);

		if (separator == -1) {
			throw new IllegalArgumentException(
					"Cannot initialize a LexicalStructureElement due to a misformatting in the input"
							+ " structureElementDescription: "
							+ structureElementDescription);
		} else {
			this.lemma = structureElementDescription.substring(0, separator);
			this.pos = structureElementDescription.substring(separator
					+ POS_LEMMA_SEPARATOR.length());
		}
	}

	/**
	 * @param lemma
	 *            The lemma of the represented word
	 */
	public void setLemma(String lemma) {
		this.lemma = lemma;
		updateTextFromData();
	}

	/**
	 * @param pos
	 *            The Part-of-Speech of the represented word
	 */
	public void setPos(String pos) {
		this.pos = pos;
		updateTextFromData();
	}

	/**
	 * The text to be returned by the method <code>getTextFromData()</code> is
	 * built using the <code>lemma</code> and the <code>pos</code> of the
	 * represented word.
	 */
	private void updateTextFromData() {
		this.textFromData = lemma + POS_LEMMA_SEPARATOR + pos;
	}

}
