package tester;


public class Mobile implements Comparable<Object>
{
	private long imeiNo;
	private String modelNo;
	private String brandName;
	private double price;
	
	
	public Mobile(long imeiNo, String modelNo, String brandName, double price)
	{
		this.imeiNo = imeiNo;
		this.modelNo = modelNo;
		this.brandName = brandName;
		this.price = price;
	}
	
	public Mobile(long imeiNo)
	{
		this.imeiNo = imeiNo;
	}

	@Override
	public int compareTo(Object o) 
	{
		if(o instanceof Mobile)
			return ((Long)(this.imeiNo)).compareTo(((Mobile) o).imeiNo);
		else
			throw new RuntimeException("Object passed is not an instance of Mobile!!");
	}

	@Override
	public String toString() 
	{
		return "Mobile [imeiNo=" + imeiNo + ", modelNo=" + modelNo + ", brandName=" + brandName + ", price=" + price
				+ "]";
	}

	@Override
	public int hashCode() 
	{
		return ((Long)(this.imeiNo)).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mobile other = (Mobile) obj;
		if (imeiNo != other.imeiNo)
			return false;
		return true;
	}
}
