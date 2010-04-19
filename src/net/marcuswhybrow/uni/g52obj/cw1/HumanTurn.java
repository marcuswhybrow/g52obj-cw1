
package net.marcuswhybrow.uni.g52obj.cw1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

/**
 *
 * @author marcus
 */
public class HumanTurn extends Turn
{
	/**
	 * Displays to the user the choices for the current card, and prompts the
	 * user for a choice.
	 * @return The name of the property the user chose
	 */
	public String takeTurn(Card card)
	{
		int choice = 0;
		Map.Entry property;
		String line = null;
		BufferedReader in;

		// Print the card details for the user to see
		card.printCard();

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
				property = (Map.Entry) card.getProperty(choice);

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
