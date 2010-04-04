
package net.marcuswhybrow.uni.g52obj.cw1;

import java.util.LinkedList;
import java.util.Collection;
import java.util.ArrayList;

/**
 *
 * @author marcus
 */
public class Deck
{
	/** The cards in this decl */
	private LinkedList<Card> _cards;
	/** The number of cards in this deck */
	private int _sizeOfDeck;
	
	public Deck()
	{
		_cards = new LinkedList<Card>();
		_sizeOfDeck = 0;
	}

	public int getSize()
	{
		return _sizeOfDeck;
	}

	/**
	 * Adds a card face down to the *top* of the deck.
	 * @param card The card to add to the top of the deck.
	 */
	public void addCardToDeckTop(Card card)
	{
		if(card != null)
		{
			_cards.addFirst(card);
			_sizeOfDeck++;
		}
	}

	/**
	 * Adds a card face down to the *bottom* of the deck.
	 * @param card The card to add to the bottom of the deck.
	 */
	public void addCardToDeckBottom(Card card)
	{
		if(card != null)
		{
			_cards.addLast(card);
			_sizeOfDeck++;
		}
	}

	public void addCardsToDeckTop(Collection c)
	{
		_cards.addAll(0, c);
		_sizeOfDeck += c.size();
	}

	public void addCardsToDeckBottom(Collection c)
	{
		_cards.addAll(c);
		_sizeOfDeck += c.size();
	}

	public ArrayList<Card> takeAllCards()
	{
		ArrayList cards = new ArrayList<Card>(_cards);
		_cards.clear();
		_sizeOfDeck = 0;
		return cards;
	}

	/**
	 * Adds a card at a random point in the deck.
	 * @param card The card to add into the deck.
	 */
	public void addCardToDeckRandomly(Card card)
	{
		if(card != null)
		{
			_cards.add((int) Math.round((_cards.size() * Math.random())), card);
			_sizeOfDeck++;
		}
	}

	/**
	 * Remove the card on top of the deck.
	 * @return The card from the top of the deck.
	 */
	public Card takeCardFromTop()
	{
		_sizeOfDeck--;
		return _cards.pollFirst();
	}

	/**
	 * Remove the card from the bottom of the deck (you sly dog).
	 * @return The card form the bottom of the deck.
	 */
	public Card takeCardFromBottom()
	{
		_sizeOfDeck--;
		return _cards.pollLast();
	}

	/**
	 * Returns but does not remove the top of the deck.
	 * @return The top card of the deck
	 */
	public Card lookAtTopCard()
	{
		return _cards.peekFirst();
	}

	public Card lookAtBottomCard()
	{
		return _cards.peekLast();
	}

	/**
	 * Takes the specified number of cards from the top of the current deck and
	 * puts them in a new deck in the same order.
	 * @param depth The number of cards to take
	 * @return The deck with the number of cards requested
	 */
	public Deck cutDeck(int depth)
	{
		Deck newDeck = new Deck();

		for(; depth > 0; depth--)
		{
			newDeck.addCardToDeckBottom(_cards.pollFirst());
			_sizeOfDeck--;
		}

		return newDeck;
	}

	/**
	 * Takes half the deck, taking the majority if there is a odd number of cards.
	 * @return The top half of the deck.
	 */
	public Deck halfDeck()
	{
		return cutDeck((int) Math.ceil(_sizeOfDeck/2));
	}

	/**
	 * Shuffles the deck of cards into a new unguessable order.
	 */
	public void shuffleDeck()
	{
		int swapIndex;
		Card temp;

		for(int i = 0; i < _sizeOfDeck; i++)
		{
			swapIndex = (int) Math.round(Math.random() * _sizeOfDeck);
			temp = _cards.get(i);
			_cards.set(i, _cards.get(swapIndex));
			_cards.set(swapIndex, temp);
		}
	}

	/**
	 * Determines whether the deck has no cards
	 * @return True if there are no cards left in the deck
	 */
	public boolean isEmpty()
	{
		return _cards.isEmpty();
	}

	public String toString()
	{
		return _cards.toString();
	}
}
