
package net.marcuswhybrow.uni.g52obj.cw1;

import java.util.Collection;
import java.util.LinkedHashMap;

/**
 * Represents a single card in the Top Trumps game, contains the cards title
 * and the properties which define it.
 * 
 * @author Marcus Whybrow
 */
public class Card
{
	// Public Methods


	/**
	 * Creates a new card with the specified title
	 *
	 * @param title The title for the new card
	 */
	public Card(String title)
	{
		_title = title;
		_properties = new LinkedHashMap<String, Property>();
	}

	/**
	 * Adds a property to this card
	 *
	 * @param name The name of the property
	 * @param value The value of the property
	 */
	public void addProperty(String name, int value)
	{
		_properties.put(name, new Property(name, value));
		_numProperties ++;
	}

	/**
	 * Gets the set of properties associated with this card
	 *
	 * @return A Set containing Map.Entry objects representing properties
	 */
	public Collection<Property> getProperties()
	{
		return _properties.values();
	}

	/**
	 * Get the property object associated with the name provided.
	 *
	 * @param name The name of th eproperty you wish to retrieve
	 * @return The property or null
	 */
	public Property getProperty(String name)
	{
		return _properties.get(name);
	}

	/**
	 * Get the Property by its number
	 *
	 * @param number The number of the property in the choice list
	 * @return The property for that number or null if number is incorrect
	 */
	public Property getProperty(int number)
	{
		try
		{
			return (Property) _properties.values().toArray()[number-1];
		}
		catch(IndexOutOfBoundsException ex)
		{
			return null;
		}
	}

	/**
	 * Get the string representation of this card
	 *
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
		int choiceNumber = 1;

		System.out.println("Top Card is \"" + _title + "\":\n");
		System.out.println("Categories:");

		for(Property property : _properties.values())
			System.out.println(choiceNumber++ + ". " + property);
	}

	/**
	 * Compares this card to another card based on a specifc property.
	 * If this card evaluates to be less than the other card based on that property
	 * then a negative number is returned, if this card evaluates to be equal to
	 * the other card then 0 is returned and if this card evaluates to be greater
	 * than the other card a positive integer is returned.
	 * 
	 * @param otherCard The card to compare with
	 * @param property The name of the subject property
	 * @return A negative, zero or positive integer
	 */
	public int compareTo(Property property)
	{
		int thisValue, otherValue;
		thisValue = _properties.get(property.getName()).getValue();
		otherValue = property.getValue();

		if(thisValue < otherValue) return -1;
		else if(thisValue == otherValue) return 0;
		else return 1;
	}


	// Private Methods
	// There are no private methods in this class


	// Instance Variables


	/** The title of the card */
	private String _title;
	/** The properties assigned to this card */
	private LinkedHashMap<String, Property> _properties;
	/** The number of properties this card has */
	private int _numProperties;
}
