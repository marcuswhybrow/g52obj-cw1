
package net.marcuswhybrow.uni.g52obj.cw1;

/**
 * An abstract base class for players with the Top Trumps game
 * @author marcus
 */
public class Player implements ITurn
{
	/** The player's personal deck of cards */
	private Deck _deck;
	/** The name of the player to represent them within the game */
	private String _name;
	/** The turn behaviour that this player should employ **/
	private Turn _turn;

	/**
	 * Crates a player with an empty deck and the given name.
	 * @param name The name for the new player
	 */
	public Player(String name, Turn turn)
	{
		_deck = new Deck();
		_name = name;
		_turn = turn;
	}

	/**
	 * Set the turn type this player should employ
	 *
	 * @param turn An instance of the turn behaviour to use
	 */
	public void setTurn(Turn turn)
	{
		_turn = turn;
	}

	public Turn getTurn()
	{
		return _turn;
	}

	public Deck getDeck()
	{
		return _deck;
	}

	/**
	 * Get the string representation of the player
	 * @return The name of the player
	 */
	public String toString()
	{
		return _name;
	}
}
