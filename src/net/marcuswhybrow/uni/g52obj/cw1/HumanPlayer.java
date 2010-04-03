
package net.marcuswhybrow.uni.g52obj.cw1;

import java.util.Scanner;

/**
 *
 * @author marcus
 */
public class HumanPlayer extends Player
{
	/** The top card in the players deck */
	private Card _nextCard;
	/** Input from the human player */
	private	Scanner in;

	public HumanPlayer()
	{
		super();
		in = new Scanner(System.in);
	}

	public HumanPlayer(Deck deck)
	{ 
		super(deck);
		in = new Scanner(System.in);
	}

	public Property takeTurn()
	{
		int choice;
		Property property;

		_nextCard = _deck.lookAtTopCard();

		_nextCard.printCard();

		while(true)
		{
			System.out.print("Please select a category to compete with: ");
			choice = in.nextInt();

			property = _nextCard.getPropertyById(choice);

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

	public String toString()
	{
		return "Human";
	}
}
