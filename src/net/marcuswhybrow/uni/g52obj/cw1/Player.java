
package net.marcuswhybrow.uni.g52obj.cw1;

/**
 *
 * @author marcus
 */
public abstract class Player
{
	/** The player's personal deck of cards */
	protected Deck _deck;

	/**
	 * Crates a player with an empty deck
	 */
	public Player()
	{
		_deck = new Deck();
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
	public abstract Property takeTurn();
}
