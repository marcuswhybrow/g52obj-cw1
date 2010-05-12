package net.marcuswhybrow.uni.g52obj.cw1;

/**
 * Represents a proeprty and its value
 *
 * @author Marcus Whybrow
 */
public class Property
{
	// Public Methods


	/**
	 * Creates a new property with a name and value, these value are immutable
	 * after creation
	 *
	 * @param name The name of the property
	 * @param value The value of the property
	 */
	public Property(String name, int value)
	{
		_name = name;
		_value = value;
	}

	/**
	 * Get the name of this property
	 *
	 * @return The name of this property
	 */
	public String getName()
	{
		return _name;
	}

	/**
	 * Get the value of this property
	 *
	 * @return The value of this property
	 */
	public int getValue()
	{
		return _value;
	}

	/**
	 * Get the string representation of this object
	 * 
	 * @return The name and the value in a single string seperated by a colon.
	 */
	@Override
	public String toString()
	{
		return _name + " : " + _value;
	}

	/**
	 * Determins the unique hash for this property, two properties with the same
	 * hash are also both equal.
	 *
	 * @return The hash for this property.
	 */
	@Override
	public int hashCode()
	{
		int hash = 3;
		hash = 71 * hash + (this._name != null ? this._name.hashCode() : 0);
		return hash;
	}

	/**
	 * Tests whether two proeprties are equal. Two properties are equal if both
	 * the name and value are the same.
	 *
	 * @param obj The other property to compare this property to
	 * @return True if the the properties are equal
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;

		final Property other = (Property) obj;

		if ((this._name == null) ? (other._name != null) : !this._name.equals(other._name)) return false;
		if (this._value != other._value) return false;

		return true;
	}
	
	// Private Mehtods
	// There are no private methods in this class


	// Instance Variables


	/** The name of this property */
	private String _name;
	/** The value of this property */
	private int _value;
}
