
package net.marcuswhybrow.uni.g52obj.cw1;

import java.util.HashMap;
import java.util.Set;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author marcus
 */
public class Card
{
	private String _title;
	private ArrayList<Property> _properties;

	public Card(String title)
	{
		_title = title;
		_properties = new ArrayList<Property>();
	}

	public void addProperty(String key, int value)
	{
		_properties.add(new Property(key, value));
	}

	public Property getPropertyById(int id)
	{
		if(id > 0 && id <= _properties.size())
			return _properties.get(id-1);
		else
			return null;
	}

	public String toString()
	{
		return _title;
	}

	public void printCard()
	{
		int numProperties = _properties.size();

		System.out.println("Top Card is " + _title + ":\n");
		System.out.println("Categories:");

		for(int i = 1; i <= numProperties; i++)
		{
			System.out.println(i + ". " + _properties.get(i-1).getName() + ": " + _properties.get(i-1).getValue());
		}
	}
}
