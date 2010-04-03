
package net.marcuswhybrow.uni.g52obj.cw1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
		String property, line = null;
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
			}
			catch(NumberFormatException ex)
			{
				System.err.println("This is not a valid number: " + line);
			}
			catch(IOException ex)
			{
				System.err.println("There was an IO error: " + ex);
			}
			finally
			{
				property = (String) _nextCard.getProperty(choice).getKey();

				if(property != null)
				{
					return property;
				}
				else
				{
					System.out.println("That number is not an option!");
				}
			}
		}
	}

	public String toString()
	{
		return "Human";
	}
}
