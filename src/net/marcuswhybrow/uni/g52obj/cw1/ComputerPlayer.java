
package net.marcuswhybrow.uni.g52obj.cw1;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * A player who determins which property to pick automatcially without console
 * input.
 * @author marcus
 */
public class ComputerPlayer extends Player
{
	/** The top card in the computers deck */
	private Card _nextCard;
	/** The record of known scales in value for each of the properties */
	private HashMap<String, Scale> _scales = new HashMap<String, Scale>();
	/** The quality of values for the current card based on known scales */
	private HashMap<String, Integer> _potential = new HashMap<String, Integer>();

	/**
	 * Creates a new computer player with the default name
	 */
	public ComputerPlayer()
	{
		super("Computer");
	}

	/**
	 * Creates a new computer player with a specific name
	 * @param name The name of the new computer player
	 */
	public ComputerPlayer(String name)
	{
		super(name);
	}

	/**
	 * Chooses the highest property by learning the scale of the different
	 * properties and choosing whichever it thinks is the largest based on the
	 * known scales.
	 * @return The name of the property chosen
	 */
	public String takeTurn()
	{
		Iterator properties;
		Map.Entry entry;
		String property, bestProperty = null;
		int value;
		
		_nextCard = _deck.lookAtTopCard();		
		_nextCard.printCard();

		properties = _nextCard.getProperties().iterator();

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

	/**
	 * A private scale class to allow the computer player to keep track of the
	 * differnt scales of values know about each property
	 */
	public class Scale
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
