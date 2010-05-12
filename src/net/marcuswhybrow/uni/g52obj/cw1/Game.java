
package net.marcuswhybrow.uni.g52obj.cw1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Contains cards and players, co-ordinating the distibution of cards to those
 * players and then the turn based comparision of properties between those
 * players.
 *
 * Players are ejected from the game once there individual deck of cards becomes
 * zero.
 *
 * @author Marcus Whybrow
 */
public class Game
{
	// Public Methods


	/**
	 * Creates a new game with the specified deck of cards.
	 *
	 * @param deck The deck of cards to play the game with.
	 */
	public Game(Deck deck)
	{
		_deck = deck;
		_cardsInPlay = new HashMap<Player, Card>();

		// Players play in addition order.
		// NOTE: player should be added externally, that is not from within the
		// Game class, however I was unsure wether we had control over files
		// provided initially in the coursework.
		this.addPlayer(new Player("Computer One", new ComputerTurn()));
		this.addPlayer(new Player("Computer Two", new ComputerTurn()));
		this.addPlayer(new Player("Computer Three", new ComputerTurn()));
		this.addPlayer(new Player("Computer Four", new ComputerTurn()));

		if(_numPlayers < 2)
			System.err.println("A game must have at least 2 players");
		else
		{
			// Deal cards to all players in a clockwise fashion
			while(!_deck.isEmpty())
				for(int i = 0; i < _numPlayers; i++)
					_players.get(i).getDeck().addCardToDeckTop(this._deck.takeCardFromTop());

			// Set the current player as the first player
			_currentPlayer = _players.get(0);
		}
	}

	/**
	 * Stats the game of TopTrumps and begins the game loop. The game is over
	 * once this method exits.
	 */
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

				try
				{
					// Sleep for one second
					Thread.sleep(1000);
				}
				catch (InterruptedException ex)
				{
					Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
				}

				// Clear the cards in play to start a new round
				_cardsInPlay.clear();

				// The leading player takes their turn (choosing a property)
				property = _currentPlayer.getTurn().takeTurn(_currentPlayer.getDeck().lookAtTopCard());
				allPlayers = _players.iterator();

				// Evaluate all players cards, winning or drawing cards end up
				// "cards in play" and losing cards end up in the shared deck.
				while(allPlayers.hasNext())
				{
					player = (Player) allPlayers.next();
					playersCard = player.getDeck().takeCardFromTop();

					if(_cardsInPlay.size() == 0)
					{
						_cardsInPlay.put(player, playersCard);
					}
					else
					{
						currentBestCard = (Card) _cardsInPlay.values().toArray()[0];

						switch(playersCard.compareTo(currentBestCard, property))
						{
							case -1:
								// Player's card is worse than current best
								_deck.addCardToDeckTop(playersCard);
								break;
							case 1:
								// Players card is better than current best
								_deck.addCardsToDeckTop(_cardsInPlay.values());
								_cardsInPlay.clear();
								_cardsInPlay.put(player, playersCard);
								break;
							case 0:
								// Players card is the same as current best
								_cardsInPlay.put(player, playersCard);
								break;
						}
					}
				}

				// Determine if the round was won or drawn
				switch(_cardsInPlay.size())
				{
					case 0:
						break;
					case 1:
						// A single card beat all others
						winningHand = (Map.Entry) _cardsInPlay.entrySet().toArray()[0];
						_currentPlayer = (Player) winningHand.getKey();
						winningCard = (Card) winningHand.getValue();
						System.out.println("\n>> " + _currentPlayer + " has won this round with card "
								+ winningCard + " which scored "
								+ winningCard.getPropertyValue(property)
								+ " in \"" + property + "\".");

						// Winner takes all cards
						this.takeAllCards(_currentPlayer);

						this.printDecks();
						this.askPlayersToLeave();
						continue;
					default:
						// Multiple cards drew, the same player has another turn.
						System.out.println("\n>> Players have drawn on \"" + property + "\", playing next card.");
						_deck.addCardsToDeckTop(_cardsInPlay.values());
						this.printDecks();
						this.askPlayersToLeave();
						continue;
				}
			}

			// One one player is left
			System.out.println("\n>> " + _players.get(0) + " has won the game with card "
					+ winningCard + " which scored "
					+ winningCard.getPropertyValue(property)
					+ " in " + property + ".");
		}
		else
		{
			System.err.println("Each player must start the game with at least 1 card.");
		}
	}

	/**
	 * Registers a player with the game
	 *
	 * @param player The player to add to the game
	 */
	public void addPlayer(Player player)
	{
		_players.add(player);
		_numPlayers++;
	}

	/**
	 * Deregisters a player with the game. Usually used to remove a player who
	 * has no cards.
	 *
	 * @param player The player to remove from the game
	 */
	public void removePlayer(Player player)
	{
		_players.remove(player);
		_numPlayers--;
	}


	// Private Methods


	/**
	 * Utility function to give all the cards not belonging to any player to the
	 * specified player
	 * 
	 * @param player The player to give all the cards to
	 */
	private void takeAllCards(Player player)
	{
		player.getDeck().addCardsToDeckBottom(_cardsInPlay.values());
		player.getDeck().addCardsToDeckBottom(_deck.takeAllCards());

		_cardsInPlay.clear();
		_deck = new Deck();
	}

	/**
	 * Utility function to print out the status of all the decks in the game.
	 */
	private void printDecks()
	{
		System.out.println("\n--------------------------------------------");

		for(int i = _players.size(); i > 0; i--)
		{
			Player player = _players.get(i-1);

			System.out.println(player + "(" + player.getDeck().getSize() + "): " + player.getDeck());
		}

		System.out.println("deck(" + _deck.getSize() + "): " + _deck);

		System.out.println("--------------------------------------------\n");
	}

	/**
	 * Determines whether all players in the game have at least a single card.
	 * A utility function only used at the start of the game.
	 *
	 * @return True if all current players have at least a single card.
	 */
	private boolean playersHaveCards()
	{
		Player player;

		for(int i = _players.size(); i > 0; i--)
		{
			player = _players.get(i-1);
			
			if(player.getDeck().isEmpty())
			{
				System.err.println("Each player must start the game with at least 1 card, and " + player);
				return false;
			}
		}

		return true;
	}

	/**
	 * Dermines which players at this point have no cards, and ejects them from
	 * the game.
	 */
	private void askPlayersToLeave()
	{
		Player player;

		for(int i = _players.size(); i > 0; i--)
		{
			player = _players.get(i-1);

			if(player.getDeck().isEmpty())
				this.removePlayer(player);
		}
	}


	// Private Variables

	
	/** The deck of cards owned by no player */
	private Deck _deck;

	/** The cards in contest to win the round, eventually contains one card if
	 * there is a winner, more than one card if there is a draw */
	private HashMap<Player, Card> _cardsInPlay;

	/** The players in the game */
	private ArrayList<Player> _players = new ArrayList<Player>();
	/** The player whose turn it currently is */
	private Player _currentPlayer;
	/** The number of players in the game */
	private int _numPlayers;
}
