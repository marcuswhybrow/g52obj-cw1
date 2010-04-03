
package net.marcuswhybrow.uni.g52obj.cw1;

import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Iterator;

/**
 *
 * @author marcus
 */
public class Card
{
	private String _title;
	private LinkedHashMap<String, Integer> _properties;
	private int _numProperties;

	public Card(String title)
	{
		_title = title;
		_properties = new LinkedHashMap<String, Integer>();
	}

	public void addProperty(String key, int value)
	{
		_properties.put(key, value);
		_numProperties ++;
	}

	public Map.Entry getProperty(int id)
	{
		Iterator properties = _properties.entrySet().iterator();
		Map.Entry entry = null;

		for(; id > 0 && properties.hasNext(); id--)
		{
			if(id == 1)
			{
				entry = (Map.Entry) properties.next();
			}
		}

		return entry;
	}

	public int getPropertyValue(String key)
	{
		return _properties.get(key);
	}

	public String toString()
	{
		return _title;
	}

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
