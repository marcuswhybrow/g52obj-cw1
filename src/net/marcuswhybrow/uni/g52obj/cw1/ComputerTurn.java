
package net.marcuswhybrow.uni.g52obj.cw1;

import java.util.HashMap;
import java.util.Map;

/**
 * A ComputerTurn extends the Turn Behaviour class and represents the act of
 * choosing a property by assessing the known values previously seen in game.
 *
 * @author Marcus Whybrow
 */
public class ComputerTurn extends Turn
{
	// Public Methods


	/**
	 * Programatically chooses a Card property based upon known scales
	 *
	 * @param card The card to examine
	 * @return The name of the property
	 */
	public String takeTurn(Card card)
	{
		/** The property currently being assessed */
		String property;
		/** The best property found thus far at a given point in time */
		String bestProperty = null;
		/** The value of the current property */
		int value;

		card.printCard();

		// Update all property potentials
		for(Map.Entry entry : card.getProperties())
		{
			property = (String) entry.getKey();
			value = (Integer) entry.getValue();

			if(!_scales.containsKey(property))
				_scales.put(property, new Scale());

			_potential.put(property, _scales.get(property).test(value));
		}

		// Choose the property with the most potential
		for(Map.Entry entry : _potential.entrySet())
		{
			property = (String) entry.getKey();
			value = (Integer) entry.getValue();

			if(bestProperty == null || value > _potential.get(bestProperty))
				bestProperty = property;

		}

		return bestProperty;
	}


	// Private Methods
	

	/**
	 * Private class which holds the computers representation of scales. One
	 * Scale object is stored for each property known. And the 'known' scale is
	 * the range of values which have been seen by the computer (including only
	 * it's own cards, i.e. not taking into account the opponants cards).
	 *
	 * When the computer chooses the best property it does so by seeing where
	 * each current values falls on the known scale, and chooses the one closest
	 * to the top end.
	 *
	 * @author Marcus Whybrow
	 */
	private class Scale
	{
		// Public Methods
		

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

		// Private Methods
		// There are no private methods in this class

		
		// Instance Variables
		

		/** The current known minimum value */
		private int _min;
		/** The current known maximum value */
		private int _max;
	}

	// Instance Variables


	/** The record of known scales in value for each of the properties */
	private HashMap<String, Scale> _scales = new HashMap<String, Scale>();
	/** The quality of values for the current card based on known scales */
	private HashMap<String, Integer> _potential = new HashMap<String, Integer>();
}
