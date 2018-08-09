package com.invoice.domain;

/**
 * Item Details class containing attributes of item invoice for number conversion.
 * @author mohansar0
 *
 */
public class ItemDetails {

	private String itemName;
	private String itemQuantity;
	private String itemCategory;
	private String itemPrice;
	private String itemTaxRate;
	private String itemAmount;
	private String itemAmountinWords;
	private boolean error;
	/**
	 * @return the itemName
	 */
	public String getItemName() {
		return itemName;
	}
	/**
	 * @param itemName the itemName to set
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	/**
	 * @return the itemQuantity
	 */
	public String getItemQuantity() {
		return itemQuantity;
	}
	/**
	 * @param itemQuantity the itemQuantity to set
	 */
	public void setItemQuantity(String itemQuantity) {
		this.itemQuantity = itemQuantity;
	}
	/**
	 * @return the itemCategory
	 */
	public String getItemCategory() {
		return itemCategory;
	}
	/**
	 * @param itemCategory the itemCategory to set
	 */
	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}
	/**
	 * @return the itemPrice
	 */
	public String getItemPrice() {
		return itemPrice;
	}
	/**
	 * @param itemPrice the itemPrice to set
	 */
	public void setItemPrice(String itemPrice) {
		this.itemPrice = itemPrice;
	}
	/**
	 * @return the itemTaxRate
	 */
	public String getItemTaxRate() {
		return itemTaxRate;
	}
	/**
	 * @param itemTaxRate the itemTaxRate to set
	 */
	public void setItemTaxRate(String itemTaxRate) {
		this.itemTaxRate = itemTaxRate;
	}
	/**
	 * @return the itemAmount
	 */
	public String getItemAmount() {
		return itemAmount;
	}
	/**
	 * @param itemAmount the itemAmount to set
	 */
	public void setItemAmount(String itemAmount) {
		this.itemAmount = itemAmount;
	}
	/**
	 * @return the itemAmountinWords
	 */
	public String getItemAmountinWords() {
		return itemAmountinWords;
	}
	/**
	 * @param itemAmountinWords the itemAmountinWords to set
	 */
	public void setItemAmountinWords(String itemAmountinWords) {
		this.itemAmountinWords = itemAmountinWords;
	}
	/**
	 * @return the error
	 */
	public boolean isError() {
		return error;
	}
	/**
	 * @param error the error to set
	 */
	public void setError(boolean error) {
		this.error = error;
	}

	

}
