
package net.marcuswhybrow.uni.g52obj.cw1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * Extends the Turn Behaviour class and allows a human to choose from a list
 * of values the property which they wish to compare against the competing
 * players values.
 *
 * @author Marcus Whybrow
 */
public final class HumanTurnBehaviour implements ITurnBehaviour
{
	// Public Methods
	

	/**
	 * Displays to the user the choices for the current card, and prompts the
	 * user for a choice.
	 * @return The name of the property the user chose
	 */
	public Property takeTurn(Card card)
	{
		int choice = 0;
		Property property;
		String line = null;
		BufferedReader in;

		// Print the card details for the user to see
		card.printCard();

		// Get a choice from the user
		while(true)
		{
			System.out.print("\nPlease select a category to compete with: ");

			try
			{
				// Read in from the console
				in = new BufferedReader(new InputStreamReader(System.in));
				line = in.readLine();
				choice = Integer.parseInt(line);

				// Attempt to get the chosen property from the card
				property = card.getProperty(choice);

				// return that property or try again
				if(property != null)
				{
					return property;
				}
				else
				{
					System.out.println("That number is not an option!");
					continue;
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


	// Private Methods
	// This class has no private methods


	// Instance Variables
	// This class is a behaviour and thus has no instance variables
}
