package net.marcuswhybrow.uni.g52obj.cw1;

/**
 * This is a behaviour class which represents taking a turn in the game.
 * This class is abstract and contains no function and could therefor be an
 * interface, however if any functionality common to all turn behaviours should
 * arrise it could be added here.
 *
 * @author Marcus Whybrow
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
