package net.marcuswhybrow.uni.g52obj.cw1;

/**
 * This is a behaviour interface which represents taking a turn in the game.
 *
 * @author Marcus Whybrow
 */
public interface ITurnBehaviour
{
	/**
	 * Presents the behaviour with a card to analyse, returning the chosen
	 * property
	 *
	 * @return The property chosen
	 */
	public Property takeTurn(Card card);
}
