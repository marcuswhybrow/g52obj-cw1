
package net.marcuswhybrow.uni.g52obj.cw1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author marcus
 */
public class Game
{
	/** The deck of cards owned by no player */
	private Deck _deck;

	/** The cards in contest to win the round, eventually contains one card if
	 * there is a winner, more than one card if there is a draw */
	private HashMap<Player, Card> _cardsInPlay;

	private final boolean HUMAN_GOES_FIRST = true;

	/** The plyaers in the game */
	private ArrayList<Player> _players = new ArrayList<Player>();
	/** The player whose turn it currently is */
	private Player _currentPlayer;
	/** The number of players in the game */
	private int _numPlayers;

	private String _propertyName;
	private int _propertyValue;

	public Game(Deck deck)
	{
		_deck = deck;
		_cardsInPlay = new HashMap<Player, Card>();

		// Should really be done external to the game.
		// Players join the game in clockwise fashion.
		this.addPlayer(new HumanPlayer("Marcus"));
		this.addPlayer(new HumanPlayer("Chris"));

		if(_numPlayers < 2)
		{
			System.err.println("A game must have at least 2 players");
		}
		else
		{
			// Deal cards to all players in a clockwise fashion
			while(!_deck.isEmpty())
			{
				for(int i = 0; i < _numPlayers; i++)
				{
					_players.get(i)._deck.addCardToDeckTop(this._deck.takeCardFromTop());
				}
			}

			// Set the current player as the first player
			_currentPlayer = _players.get(0);
		}
	}

	public void playGame()
	{
		String property = null;
		Iterator allPlayers;
		Player player;
		Card playersCard, currentBestCard, winningCard = null;
		Map.Entry winningHand;

		if(playersHaveCards())
		{
			while(_numPlayers > 1)
			{
				System.out.println("\n============================================");
				System.out.println("NEW ROUND");
				System.out.println("============================================\n");

				// Clear the cards in play to start a new round
				_cardsInPlay.clear();

				System.out.println(">> " + _currentPlayer + " is taking their go.");

				// The leading player takes their turn (choosing a property)
				property = _currentPlayer.takeTurn();
				allPlayers = _players.iterator();

				System.out.println(">> " + _currentPlayer + " chose " + property);

				// Evaluate all players cards, winning or drawing cards end up
				// "cards in play" and losing cards end up in the shared deck.
				while(allPlayers.hasNext())
				{
					player = (Player) allPlayers.next();
					playersCard = player._deck.takeCardFromTop();

					System.out.println(">> " + player + "'s card '" + playersCard + "' has " + playersCard.getPropertyValue(property) + " for " + property);

					if(_cardsInPlay.size() == 0)
					{
						_cardsInPlay.put(player, playersCard);
						System.out.println(">> " + player + "'s card is currenty the best card");
					}
					else
					{
						currentBestCard = (Card) _cardsInPlay.values().toArray()[0];

						switch(playersCard.compareTo(currentBestCard, property))
						{
							case -1:
								// Player's card is worse than current best
								_deck.addCardToDeckTop(playersCard);
								System.out.println(">> " + player + "'s card has been added to the shared deck");
								break;
							case 1:
								// Players card is better than current best
								_deck.addCardsToDeckTop(_cardsInPlay.values());
								_cardsInPlay.clear();
								_cardsInPlay.put(player, playersCard);
								System.out.println(">> " + player + "'s card is currenty the best card");
								break;
							case 0:
								// Players card is the same as current best
								_cardsInPlay.put(player, playersCard);
								System.out.println(">> " + player + "'s card is as good as the current best");
								break;
						}
					}
				}

				System.out.println(">> " + "There is " + _cardsInPlay.size() + " card(s) in play");

				// Determine if the round was won or drawn
				switch(_cardsInPlay.size())
				{
					case 0:
						System.err.println("No cards won the round, this is strange.");
						break;
					case 1:
						// A single card beat all others
						winningHand = (Map.Entry) _cardsInPlay.entrySet().toArray()[0];
						_currentPlayer = (Player) winningHand.getKey();
						winningCard = (Card) winningHand.getValue();
						System.out.println(_currentPlayer + " has won this round with card " 
								+ winningCard + " which scored "
								+ winningCard.getPropertyValue(property)
								+ " in category " + property);

						// Winner takes all cards
						this.takeAllCards(_currentPlayer);

						this.printDecks();
						this.askPlayersToLeave();
						continue;
					default:
						// Multiple cards drew, the same player has another turn.
						System.out.println("Players have drawn on " + property + ", playing next card.");
						_deck.addCardsToDeckTop(_cardsInPlay.values());
						this.printDecks();
						this.askPlayersToLeave();
						continue;
				}
			}

			// One one player is left
			System.out.println(_players.get(0) + " has won the game with card "
					+ winningCard + " which scored "
					+ winningCard.getPropertyValue(property)
					+ "in " + property + ".");
		}
		else
		{
			System.err.println("Each player must start the game with at least 1 card.");
		}
	}

	private void takeAllCards(Player player)
	{
		player._deck.addCardsToDeckBottom(_cardsInPlay.values());
		player._deck.addCardsToDeckBottom(_deck.takeAllCards());

		_cardsInPlay.clear();
		_deck = new Deck();
	}

	private void printDecks()
	{
		System.out.println("\n--------------------------------------------");

		for(int i = _players.size(); i > 0; i--)
		{
			Player player = _players.get(i-1);

			System.out.println(player + ": " + player._deck.toString());
		}

		System.out.println("--------------------------------------------\n");
	}

	/**
	 * Determines whether all players in the game have at least a single card.
	 * @return True if all current players have at least a single card.
	 */
	private boolean playersHaveCards()
	{
		Player player;

		for(int i = _players.size(); i > 0; i--)
		{
			player = _players.get(i-1);
			
			if(player._deck.isEmpty())
			{
				System.err.println("Each player must start the game with at least 1 card, and " + player);
				return false;
			}
		}

		return true;
	}

	/**
	 * Dermines which players at this point have no cards, and removes them
	 * from the game.
	 */
	private void askPlayersToLeave()
	{
		Player player;

		for(int i = _players.size(); i > 0; i--)
		{
			player = _players.get(i-1);

			if(player._deck.isEmpty())
			{
				this.removePlayer(player);
			}
		}
	}

	public void addPlayer(Player player)
	{
		_players.add(player);
		_numPlayers++;
	}

	public void removePlayer(Player player)
	{
		_players.remove(player);
		_numPlayers--;
	}
}
