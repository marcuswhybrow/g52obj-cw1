
package net.marcuswhybrow.uni.g52obj.cw1;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A ComputerTurn extends the Turn Behaviour class and represents the act of
 * choosing a property by assessing the known values previously seen in game.
 *
 * @author Marcus Whybrow
 */
public final class ComputerTurnBehaviour implements ITurnBehaviour
{
	// Public Methods


	/**
	 * Programatically chooses a Card property based upon known scales
	 *
	 * @param card The card to examine
	 * @return The name of the property
	 */
	public Property takeTurn(Card card)
	{
		/** The best property found thus far at a given point in time */
		Property bestProperty = null;

		card.printCard();

		// Some fake waiting so an observer may view what the computer is doing
		try
		{
			// Sleep for one second
			Thread.sleep(1000);
		}
		catch (InterruptedException ex)
		{
			Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
		}

		// Update all property potentials
		for(Property property : card.getProperties())
		{
			if(!_scales.containsKey(property.getName()))
				_scales.put(property.getName(), new Scale());

			_potential.put(property.getName(), _scales.get(property.getName()).test(property.getValue()));
		}

		// Choose the property with the most potential
		for(Map.Entry entry : _potential.entrySet())
		{
			Property property = card.getProperty((String) entry.getKey());
			int value = (Integer) entry.getValue();

			if(bestProperty == null || value > _potential.get(bestProperty.getName()))
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

		@Override
		public String toString()
		{
			return "{" + _min + " -> " + _max + "}";
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
