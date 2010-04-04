
package net.marcuswhybrow.uni.g52obj.cw1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * A human player who is propmted for input to choose which property should be
 * compared with the other players
 * @author marcus
 */
public class HumanPlayer extends Player
{
	/** The top card in the players deck */
	private Card _nextCard;

	/**
	 * Creates a new human player with the default name
	 */
	public HumanPlayer()
	{
		super("Human");
	}

	/**
	 * Creates a new human player with the specified name
	 * @param name The name for the new player
	 */
	public HumanPlayer(String name)
	{
		super(name);
	}

	/**
	 * Displays to the user the choices for the current card, and prompts the
	 * user for a choice.
	 * @return The name of the property the user chose
	 */
	public String takeTurn()
	{
		int choice = 0;
		Map.Entry property;
		String line = null;
		BufferedReader in;

		// Get a copy of the next card
		_nextCard = _deck.lookAtTopCard();

		// Print the card details for the user to see
		_nextCard.printCard();

		// Get a choice from the user
		while(true)
		{
			System.out.print("Please select a category to compete with: ");

			try
			{
				// Read in from the console
				in = new BufferedReader(new InputStreamReader(System.in));
				line = in.readLine();
				choice = Integer.parseInt(line);

				// Attempt to get the chosen property from the card
				property = (Map.Entry) _nextCard.getProperty(choice);

				// return that property or try again
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
}
