
package net.marcuswhybrow.uni.g52obj.cw1;

/**
 *
 * @author marcus
 */
public class DeckBuilder implements IDeckBuilder
{
	/** The the deck of cards */
	private Deck _deck;
	/** The card most recently added to the deck */
	private Card _latestAddition;

	public DeckBuilder()
	{
		_deck = new Deck();
	}

	public void newCard(String cardName)
	{
		_latestAddition = new Card(cardName);
		_deck.addCardToDeckRandomly(_latestAddition);
	}

	public void addProperty(String name, int value)
	{
		_latestAddition.addProperty(name, value);
	}

	public Deck getDeck()
	{
		return _deck;
	}
}
