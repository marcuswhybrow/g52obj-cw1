
package net.marcuswhybrow.uni.g52obj.cw1;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author marcus
 */
public class ComputerTurn extends Turn
{
	/** The record of known scales in value for each of the properties */
	private HashMap<String, Scale> _scales = new HashMap<String, Scale>();
	/** The quality of values for the current card based on known scales */
	private HashMap<String, Integer> _potential = new HashMap<String, Integer>();
	
	public String takeTurn(Card card)
	{
		Iterator properties;
		Map.Entry entry;
		String property, bestProperty = null;
		int value;

		card.printCard();

		properties = card.getProperties().iterator();

		// Update all property potentials
		while(properties.hasNext())
		{
			entry = (Map.Entry) properties.next();
			property = (String) entry.getKey();
			value = (Integer) entry.getValue();

			if(!_scales.containsKey(property))
				_scales.put(property, new Scale());

			_potential.put(property, _scales.get(property).test(value));
		}

		properties = _potential.entrySet().iterator();

		// Choose the property with the most potential
		while(properties.hasNext())
		{
			entry = (Map.Entry) properties.next();
			property = (String) entry.getKey();
			value = (Integer) entry.getValue();

			if(bestProperty == null || value > _potential.get(bestProperty))
				bestProperty = property;

		}

		return bestProperty;
	}

	private class Scale
	{
		/** The current known minimum value */
		private int _min;
		/** The current known maximum value */
		private int _max;

		/**
		 * Creates a new scale object with zeroed minimum and maximum values
		 */
		public Scale()
		{
			_min = 0;
			_max = 0;
		}

		/**
		 * Tests a value agains the current known scale. If the value is outside
		 * of the currenlty known scale the scale is expanded to include it.
		 * A percentage is returned based on the values position in the currently
		 * known scale.
		 * 
		 * @param value The value to test against the scale
		 * @return The percentage position against the scale
		 */
		public int test(int value)
		{
			if(value > _max) _max = value;
			if(value < _min) _min = value;

			if(_max != 0)
				return (int) (( (double) ( value - _min) / (double) (_max - _min) ) * 100);
			else
				return 0;
		}
	}
}
