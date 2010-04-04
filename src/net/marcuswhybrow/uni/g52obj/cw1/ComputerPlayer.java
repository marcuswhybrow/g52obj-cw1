
package net.marcuswhybrow.uni.g52obj.cw1;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author marcus
 */
public class ComputerPlayer extends Player
{
	/** The top card in the computers deck */
	private Card _nextCard;

	private HashMap<String, Scale> _scales = new HashMap<String, Scale>();

	private HashMap<String, Integer> _potential = new HashMap<String, Integer>();

	public ComputerPlayer()
	{
		super("Computer");
	}

	public ComputerPlayer(String name)
	{
		super(name);
	}

	public ComputerPlayer(Deck deck)
	{
		super(deck);
	}

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

	public class Scale
	{
		/** The current known minimum value */
		private int _min;
		/** The current known maximum value */
		private int _max;

		public Scale()
		{
			_min = 0;
			_max = 0;
		}

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
