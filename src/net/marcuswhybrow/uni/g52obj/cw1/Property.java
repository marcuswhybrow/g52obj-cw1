
package net.marcuswhybrow.uni.g52obj.cw1;

/**
 *
 * @author marcus
 */
public class Property implements Comparable
{
	/** The name of the property */
	private String _name;
	/** The value of th eproperty */
	private int _value;

	public Property(String name, int value)
	{
		_name = name;
		_value = value;
	}

	public int compareTo(Object obj)
	{
		Property property = (Property) obj;
		
		if(this._value < property.getValue())
		{
			return -1;
		}
		else if(this._value == property.getValue())
		{
			return 0;
		}
		else
		{
			return 1;
		}
	}

	public String toString()
	{
		return _name;
	}

	public String getName()
	{
		return _name;
	}

	public int getValue()
	{
		return _value;
	}
}
