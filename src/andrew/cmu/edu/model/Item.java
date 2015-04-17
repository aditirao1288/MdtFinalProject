
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

  public boolean equals(Object o) {
    if (this == o) return true;
    if (this == null) return false;
    if (!(o instanceof Item)) return false;
    return ((Item)o).getCode().equals(this.code);
  }
}

