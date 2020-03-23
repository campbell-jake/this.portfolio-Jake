package com.techelevator;

import java.math.BigDecimal;

public class Slot {
					
		/*******************************************************************************************
 		* Instance variables
		******************************************************************************************/

		private String name;
		private BigDecimal price;
		private String type;
		private int quantity;
		private int sold;
					
		/*******************************************************************************************
		 * 5 argument constructor
		 ******************************************************************************************/

		public Slot(String name, BigDecimal price, String type, int quantity, int sold) {
			this.name= name; 
			this.price = price;
			this.quantity = quantity;
			this.type = type;
			this.sold = sold;
		}
			
		/*******************************************************************************************
		 * Getters
		 ******************************************************************************************/

		public String getName() {
			return name;
		}
		
		public BigDecimal getPrice() {
			return price;
		}
			
		public String getType() {
			return type;
		}

		public int getQuantity() {
			return quantity;
		}
			
		public int getSold() {
			return sold;
		}

		/*******************************************************************************************
		 * Setters
		 ******************************************************************************************/

		public void setName(String name) {
			this.name = name;
		}

		public void setPrice(BigDecimal price) {
			this.price = price;
		}

		public void setType(String type) {
			this.type = type;
		}

		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
			
		public void setSold(int sold) {
			this.sold = sold;
		}

		/*******************************************************************************************
		 * Method toString Override
		 ******************************************************************************************/
			
		@Override
		public String toString() {
			return String.format(getType() + "|" + getName() + "|" + getPrice() + "|" + getQuantity() + "\n");
		}
			
	}
		
		
	
	
	

