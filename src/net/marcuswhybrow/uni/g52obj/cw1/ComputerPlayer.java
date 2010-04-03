
package net.marcuswhybrow.uni.g52obj.cw1;

import java.util.Iterator;
import java.util.Map;
import java.util.ArrayList;

/**
 *
 * @author marcus
 */
public class ComputerPlayer extends Player
{
	/** The top card in the computers deck */
	private Card _nextCard;

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
		String property;
		
		_nextCard = _deck.lookAtTopCard();
		
		_nextCard.printCard();

		return (String) _nextCard.getProperty(1).getKey();
	}
}
