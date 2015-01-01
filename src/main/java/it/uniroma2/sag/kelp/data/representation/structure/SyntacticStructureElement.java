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

@JsonTypeName("SYNT")
public class SyntacticStructureElement implements StructureElement{

	private String syntacticRelation;


	public SyntacticStructureElement(){
		
	}
	
	public SyntacticStructureElement(String syntacticRelation){
		this.syntacticRelation = syntacticRelation;
	}
	
	/**
	 * @return the syntacticRelation
	 */
	public String getSyntacticRelation() {
		return syntacticRelation;
	}

	/**
	 * @param syntacticRelation the syntacticRelation to set
	 */
	public void setSyntacticRelation(String syntacticRelation) {
		this.syntacticRelation = syntacticRelation;
	}
	
	@Override
	public void setDataFromText(String structureElementDescription)
			throws Exception {
		this.syntacticRelation = structureElementDescription;
		
	}

	@Override
	public String getTextFromData() {
		return syntacticRelation;
	}
}
