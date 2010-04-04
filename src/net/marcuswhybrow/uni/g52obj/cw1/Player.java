
package net.marcuswhybrow.uni.g52obj.cw1;

/**
 * An abstract base class for players with the Top Trumps game
 * @author marcus
 */
public abstract class Player
{
	/** The player's personal deck of cards */
	protected Deck _deck;
	/** The name of the player to represent them within the game */
	private String _name;

	/**
	 * Crates a player with an empty deck and the given name.
	 * @param name The name for the new player
	 */
	public Player(String name)
	{
		_deck = new Deck();
		_name = name;
	}

	/**
	 * Creates a player with a starting deck
	 * @param deck The deck the player should start with
	 */
	public Player(Deck deck)
	{
		_deck = deck;
	}

	/**
	 * Prompts the player to choose a property as it is their turn.
	 * @return The property the player has chosen.
	 */
	public abstract String takeTurn();

	/**
	 * Get the string representation of the player
	 * @return The name of the player
	 */
	public String toString()
	{
		return _name;
	}
}
