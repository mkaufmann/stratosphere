/***********************************************************************************************************************
 *
 * Copyright (C) 2010 by the Stratosphere project (http://stratosphere.eu)
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 **********************************************************************************************************************/

package eu.stratosphere.pact.runtime.util;

import java.util.Iterator;

import eu.stratosphere.pact.common.type.PactRecord;


/**
* Utility class that turns a standard {@link java.util.Iterator} for {@link PactRecord}s into a
* {@link LastRepeatableIterator}.
* 
*  @author Stephan Ewen
*/
public class PactRecordRepeatableIterator implements LastRepeatableIterator<PactRecord>
{
	private final Iterator<PactRecord> input;
	
	private final PactRecord copy;
	
	private PactRecord repeater;
	
	// --------------------------------------------------------------------------------------------
	
	public PactRecordRepeatableIterator(Iterator<PactRecord> input)
	{
		this.input = input;
		this.copy = new PactRecord();
		this.repeater = new PactRecord();
	}
	
	// --------------------------------------------------------------------------------------------

	/* (non-Javadoc)
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public boolean hasNext() {
		return this.input.hasNext();
	}

	/* (non-Javadoc)
	 * @see java.util.Iterator#next()
	 */
	@Override
	public PactRecord next() {
		PactRecord rec = this.input.next();
		rec.copyTo(this.copy);
		return rec;
	}

	/* (non-Javadoc)
	 * @see java.util.Iterator#remove()
	 */
	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
	
	/* (non-Javadoc)
	 * @see eu.stratosphere.pact.runtime.util.LastRepeatableIterator#repeatLast()
	 */
	@Override
	public PactRecord repeatLast() {
		this.copy.copyTo(this.repeater);
		return this.repeater;
	}
}