
package net.marcuswhybrow.uni.g52obj.cw1;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author marcus
 */
public class Game
{
	/** The deck of cards owned by no player */
	private Deck _deck;

	private final boolean HUMAN_GOES_FIRST = true;

	/** The human player */
	private Player _playerOne;
	/** The computer player */
	private Player _playerTwo;

	/** The plyaers in the game */
	private ArrayList<Player> _players = new ArrayList<Player>();

	private String _propertyName;
	private int _propertyValue;

	public Game(Deck deck)
	{
		int numPlayers;

		_deck = deck;

		_players.add(new HumanPlayer());
		_players.add(new ComputerPlayer());

		numPlayers = _players.size();

		while(!_deck.isEmpty())
		{
			for(int i = 0; i < numPlayers; i++)
			{
				_players.get(i)._deck.addCardToDeckTop(this._deck.takeCardFromTop());
			}
		}
	}

	public void playGame()
	{
		if(!_playerOne._deck.isEmpty() && !_playerTwo._deck.isEmpty())
		{
			while(true)
			{
				if(takeTurn(_playerOne, _playerTwo))
				takeTurn(_playerTwo, _playerOne);
			}
		}
		else
		{
			System.err.println("Each player must start the game with at least 1 card.");
		}
	}

	private boolean takeTurn(Player playerOne, Player playerTwo)
	{
		Property playerOneProperty, playerTwoProperty;
		Card playerOneCard, playerTwoCard;
		int playerOneValue, playerTwoValue;

		// Player one picks a property
		playerOneProperty = playerOne.takeTurn();

		// take both players cards
		playerOneCard = playerOne._deck.takeCardFromTop();
		playerTwoCard = playerTwo._deck.takeCardFromTop();

		playerTwoProperty = playerTwoCard.

		// Read both values for the chosen property
		playerOneValue = playerOneCard.getPropertyValue(property);
		playerTwoValue = playerTwoCard.getPropertyValue(property);

		// Store current property name
		_propertyName = property;

		if(playerOneValue > playerTwoValue)
		{
			// Player one gets the cards
			playerOne._deck.addCardToDeckBottom(playerTwoCard);
			playerOne._deck.addCardToDeckBottom(playerOneCard);

			// Store the hand winning value
			_propertyValue = playerOneCard.getPropertyValue(property);

			// Report that player one has won
			System.out.println(playerOne + " has won the this round with card "
				+ playerOneCard + " which scored " + playerOneValue
				+ " in category " + property);
		}
		else if(playerOneValue == playerTwoValue)
		{
			// The cards get put to the side
			_deck.addCardToDeckTop(playerOneCard);
			_deck.addCardToDeckTop(playerTwoCard);

			// Doesn't matter which card since values are the same
			_propertyValue = playerOneCard.getPropertyValue(property);

			// report that this round is a draw
			// Report that player one has won
			System.out.println("Players have drawn on " + property + ", playing next card.");

			// Check if player one has any cards left
			if(!playerOne.hasCards()) return false;
		}
		else
		{
			// Player two gets the cards
			playerTwo.addCardToDeckBottom(playerOneCard);
			playerTwo.addCardToDeckBottom(playerTwoCard);

			// Stor player two's hand winning value
			_propertyValue = playerTwoCard.getPropertyValue(property);

			// Report that player two has won
			System.out.println(playerTwo + " has won the this round with card "
				+ playerTwoCard + " which scored " + playerTwoValue
				+ " in category " + property);

			// Check if player one has any cards left
			if(!playerOne.hasCards()) return false;
		}

		// Player took there turn and is still in the game
		return true;
	}

	private void notifyWinner(Player player)
	{
		System.out.println(player.toString() + " has won the this round with card " 
				+ player.lookAtLastWinningCard() + " which scored " + _propertyValue
				+ " in category " + _propertyName);
	}
}
