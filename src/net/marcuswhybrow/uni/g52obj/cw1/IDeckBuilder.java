
package net.marcuswhybrow.uni.g52obj.cw1;

/**
 *
 * @author marcus
 */
public interface IDeckBuilder
{
	/**
	 *
	 * @param name
	 */
	public void newCard(String name);

	/**
	 *
	 * @param name
	 * @param value
	 */
	public void addProperty(String name, int value);

	/**
	 * 
	 * @return
	 */
	public Deck getDeck();
}
