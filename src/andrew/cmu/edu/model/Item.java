
package andrew.cmu.edu.model;

import java.math.BigDecimal;

public class Item {
    
  private String code;
  private String name;
  private String description;
  private double price;
  private double weight;

  public double getWeight() {
	return weight;
}

public void setWeight(double weight) {
	this.weight = weight;
}

public Item(String code,String name,String description,double price,double weight) {
    this.code=code;
    this.name=name;
    this.description=description;
    this.price=price;
    this.weight = weight;
  }

  public String getCode() {
    return code;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public double getPrice() {
    return price;
  }

  public String getFormattedPrice() {
    return "$"+new BigDecimal(price).movePointLeft(2);
  }

  @Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((code == null) ? 0 : code.hashCode());
	return result;
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Item other = (Item) obj;
	if (code == null) {
		if (other.code != null)
			return false;
	} else if (!code.equals(other.code))
		return false;
	return true;
}
  
  
}

