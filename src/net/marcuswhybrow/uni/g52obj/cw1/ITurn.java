
package net.marcuswhybrow.uni.g52obj.cw1;

/**
 * Classes which implement the ITurn interface provide a method which returns
 * a Turn behaviour. This returned behaviour is then executed.
 *
 * @author Marcus Whybrow
 */
public interface ITurn
{
	/**
	 * Get the turn behaviour of the player
	 * 
	 * @return The turn behaviour
	 */
	public Turn getTurn();
}
