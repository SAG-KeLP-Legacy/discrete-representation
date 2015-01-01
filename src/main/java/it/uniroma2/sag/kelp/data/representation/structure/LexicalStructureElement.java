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

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("LEX")
public class LexicalStructureElement implements StructureElement{

	public static final String POS_LEMMA_SEPARATOR = "::";
	
	private String lemma;

	private String pos;
	
	public LexicalStructureElement(){
		
	}
	
	public LexicalStructureElement(String lemma, String pos){
		this.lemma = lemma;
		this.pos = pos;
	}
	
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

	@Override
	public void setDataFromText(String structureElementDescription)
			throws Exception {
		int separator = structureElementDescription.indexOf(POS_LEMMA_SEPARATOR);
		
		if(separator==-1){
			throw new IllegalArgumentException("Cannot initialize a LexicalStructureElement due to a misformatting in the input"
					+ " structureElementDescription: " + structureElementDescription);
		}else{
			this.pos = structureElementDescription.substring(0, separator);
			this.lemma = structureElementDescription.substring(separator+POS_LEMMA_SEPARATOR.length());
		}
	}

	@Override
	public String getTextFromData() {
		return pos + POS_LEMMA_SEPARATOR + lemma;
	}

}
