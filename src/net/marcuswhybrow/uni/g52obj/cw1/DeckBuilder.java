
package net.marcuswhybrow.uni.g52obj.cw1;

/**
 * Builds a new deck by adding cards to a deck, and also allowing properties to
 * be added to the most recently added card
 * @author marcus
 */
public class DeckBuilder implements IDeckBuilder
{
	/** The the deck of cards */
	private Deck _deck;
	/** The card most recently added to the deck */
	private Card _latestAddition;

	/**
	 * Creates a new deck builder with an empty deck
	 */
	public DeckBuilder()
	{
		_deck = new Deck();
	}

	/**
	 * Adds a new card to the deck
	 * @param title The title of the new card
	 */
	public void newCard(String title)
	{
		_latestAddition = new Card(title);
		_deck.addCardToDeckRandomly(_latestAddition);
	}

	/**
	 * Adds a property to the most recentyl added card
	 * @param name The name of the property
	 * @param value The value of the property
	 */
	public void addProperty(String name, int value)
	{
		_latestAddition.addProperty(name, value);
	}

	/**
	 * Get the deck of cards in its current state
	 * @return The current deck of cards
	 */
	public Deck getDeck()
	{
		return _deck;
	}
}
