
package net.marcuswhybrow.uni.g52obj.cw1;

/**
 * Represents players within the Top Tumps game. Encapsulates the players name,
 * deck and turn behaviour.
 *
 * @author Marcus Whybrow
 */
public class Player
{
	// Public Methods

	/**
	 * Crates a player with an empty deck and the given name.
	 * @param name The name for the new player
	 */
	public Player(String name, ITurnBehaviour turn)
	{
		_deck = new Deck();
		_name = name;
		_turnBehaviour = turn;
	}

	/**
	 * Set the turn type this player should employ
	 *
	 * @param turn An instance of the turn behaviour to use
	 */
	public void setTurn(ITurnBehaviour turn)
	{
		_turnBehaviour = turn;
	}

	/**
	 * Get the Turn behaviour used by this player
	 *
	 * @return The behaviour which used by this player
	 */
	public ITurnBehaviour getTurn()
	{
		return _turnBehaviour;
	}


	/**
	 * Get the Deck which this player holds.
	 *
	 * @return The Deck held by this player
	 */
	public Deck getDeck()
	{
		return _deck;
	}

	/**
	 * Get the string representation of the player
	 *
	 * @return The name of the player
	 */
	public String toString()
	{
		return _name;
	}


	// Private Methods
	// This class has no private methods


	// Instance Variables


	/** The player's personal deck of cards */
	private Deck _deck;
	/** The name of the player to represent them within the game */
	private String _name;
	/** The turn behaviour that this player should employ **/
	private ITurnBehaviour _turnBehaviour;
}
