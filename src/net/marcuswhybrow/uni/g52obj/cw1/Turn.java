package net.marcuswhybrow.uni.g52obj.cw1;

/**
 *
 * @author marcus
 */
public abstract class Turn
{
	/**
	 * Takes a turn in the game, requiring the player to choose a property
	 *
	 * @return The property chosen
	 */
	public abstract String takeTurn(Card card);
}
