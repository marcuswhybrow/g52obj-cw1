
package net.marcuswhybrow.uni.g52obj.cw1;

/**
 *
 * @author G52OBJ
 */
public interface IDeckBuilder
{
	/**
	 * Add a new card to the deck
	 * @param name The name of the new card to add
	 */
	public void newCard(String name);

	/**
	 * Add a property to the most recently added card
	 * @param name The name of the property to add
	 * @param value The value of the property to add
	 */
	public void addProperty(String name, int value);

	/**
	 * Get the deck in its current state
	 * @return The deck in its current state
	 */
	public Deck getDeck();
}
