
package net.marcuswhybrow.uni.g52obj.cw1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

/**
 *
 * @author marcus
 */
public class HumanPlayer extends Player
{
	/** The top card in the players deck */
	private Card _nextCard;

	public HumanPlayer()
	{
		super();
	}

	public HumanPlayer(Deck deck)
	{ 
		super(deck);
	}

	public String takeTurn()
	{
		int choice = 0;
		Map.Entry property;
		String line = null;
		BufferedReader in;

		_nextCard = _deck.lookAtTopCard();

		_nextCard.printCard();

		while(true)
		{
			System.out.print("Please select a category to compete with: ");

			try
			{
				in = new BufferedReader(new InputStreamReader(System.in));
				line = in.readLine();
				choice = Integer.parseInt(line);

				property = (Map.Entry) _nextCard.getProperty(choice);

				if(property != null)
				{
					return (String) property.getKey();
				}
				else
				{
					System.out.println("That number is not an option!");
				}
			}
			catch(NumberFormatException ex)
			{
				System.err.println("This is not a valid number: " + line);
			}
			catch(IOException ex)
			{
				System.err.println("There was an IO error: " + ex);
			}
		}
	}

	public String toString()
	{
		return "Human";
	}
}
