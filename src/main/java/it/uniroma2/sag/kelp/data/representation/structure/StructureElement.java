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

import java.io.Serializable;

/**
 * This class represent the atomic element of a discrete structure. It has been
 * designed to represent the "content" of each basic element of a structure.
 * 
 * @author Simone Filice, Danilo Croce
 * 
 */
public interface StructureElement extends Serializable {

	/**
	 * Initializes a StructureElement using its textual description provided in
	 * <code>structureElementDescription</code>
	 * 
	 * @param structureElementDescription
	 *            the textual description of the structureElement to be
	 *            initialized
	 */
	public void setDataFromText(String structureElementDescription)
			throws Exception;

	/**
	 * Returns a textual representation of the data stored in this
	 * structureElement
	 * 
	 * @return a textual representation of the data stored in this
	 *         structureElement
	 */
	public String getTextFromData();

}
