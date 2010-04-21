
package net.marcuswhybrow.uni.g52obj.cw1;

import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Represents a single card in the Top Trumps game, contains the cards title
 * and the properties which define it.
 * 
 * @author marcus
 */
public class Card
{
	/** The title of the card */
	private String _title;
	/** The properties assigned to this card */
	private LinkedHashMap<String, Integer> _properties;
	/** The number of properties this card has */
	private int _numProperties;

	/**
	 * Creates a new card with the specified title
	 * @param title The title for the new card
	 */
	public Card(String title)
	{
		_title = title;
		_properties = new LinkedHashMap<String, Integer>();
	}

	/**
	 * Adds a property to this card
	 * @param name The name of the property
	 * @param value The value of the property
	 */
	public void addProperty(String name, int value)
	{
		_properties.put(name, value);
		_numProperties ++;
	}

	/**
	 * Gets the set of properties associated with this card
	 * @return A Set containing Map.Entry objects representing properties
	 */
	public Set<Entry<String, Integer>> getProperties()
	{
		return _properties.entrySet();
	}

	/**
	 * Get the specific Map.Entry representing a property by its number
	 * @param number The number of the property in the choice list
	 * @return The property for that number or null if number is incorrect
	 */
	public Map.Entry getProperty(int number)
	{
		try
		{
			return (Map.Entry) _properties.entrySet().toArray()[number-1];
		}
		catch(IndexOutOfBoundsException ex)
		{
			return null;
		}
	}

	/**
	 * Get the value of a property for this card by the property's name
	 * @param name The name of the property
	 * @return The value of this property
	 */
	public int getPropertyValue(String name)
	{
		return _properties.get(name);
	}

	/**
	 * Get the string representation of this card
	 * @return The title of the card
	 */
	public String toString()
	{
		return _title;
	}

	/**
	 * Utility function to print the representation of the card to the console.
	 */
	public void printCard()
	{
		Iterator properties = _properties.entrySet().iterator();
		int choiceNumber = 1;
		Map.Entry entry;

		System.out.println("Top Card is " + _title + ":\n");
		System.out.println("Categories:");

		while(properties.hasNext())
		{
			entry = (Map.Entry) properties.next();
			System.out.println(choiceNumber + ". " + entry.getKey() + ": " + entry.getValue());
			choiceNumber++;
		}
	}

	/**
	 * Compares this card to another card based on a specifc property.
	 * If this card evaluates to be less than the other card based on that property
	 * then a negative number is returned, if this card evaluates to be equal to
	 * the other card then 0 is returned and if this card evaluates to be greater
	 * than the other card a positive integer is returned.
	 * @param otherCard The card to compare with
	 * @param property The name of the subject property
	 * @return A negative, zero or positive integer
	 */
	public int compareTo(Card otherCard, String property)
	{
		int thisValue, otherValue;
		thisValue = this.getPropertyValue(property);
		otherValue = otherCard.getPropertyValue(property);

		if(thisValue < otherValue) return -1;
		else if(thisValue == otherValue) return 0;
		else return 1;
	}
}
