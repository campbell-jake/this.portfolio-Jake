package com.techelevator.model.park;

public class Park {
	
	/************************************************************************************************************************************
	 * Instance Variables
	 ***********************************************************************************************************************************/

	private String parkCode;
	private String parkName;
	private String state;
	private int acreage;
	private int elevation;
	private float milesOfTrails;
	private int numberOfCampsites;
	private String climate;
	private int yearFounded;
	private int annualVisitorCount;
	private String inspirationalQuote;
	private String quoteSource;
	private String parkDescription;
	private int entryFee;
	private int numberAnimalSpecies;

	/************************************************************************************************************************************
	 * Getters
	 ***********************************************************************************************************************************/

	public String getParkCode() {
		return parkCode;
	}
	public String getParkName() {
		return parkName;
	}
	public String getState() {
		return state;
	}
	public int getAcreage() {
		return acreage;
	}
	public int getElevation() {
		return elevation;
	}
	public float getMilesOfTrails() {
		return milesOfTrails;
	}
	public int getNumberOfCampsites() {
		return numberOfCampsites;
	}
	public String getClimate() {
		return climate;
	}
	public int getYearFounded() {
		return yearFounded;
	}
	public int getAnnualVisitorCount() {
		return annualVisitorCount;
	}
	public String getInspirationalQuote() {
		return inspirationalQuote;
	}
	public String getQuoteSource() {
		return quoteSource;
	}
	public String getParkDescription() {
		return parkDescription;
	}
	public int getEntryFee() {
		return entryFee;
	}
	public int getNumberAnimalSpecies() {
		return numberAnimalSpecies;
	}
	
	/************************************************************************************************************************************
	 * Setters 
	 ***********************************************************************************************************************************/

	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}
	public void setParkName(String parkName) {
		this.parkName = parkName;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setAcreage(int acreage) {
		this.acreage = acreage;
	}
	public void setElevation(int elevation) {
		this.elevation = elevation;
	}
	public void setMilesOfTrails(float milesOfTrails) {
		this.milesOfTrails = milesOfTrails;
	}
	public void setNumberOfCampsites(int numberOfCampsites) {
		this.numberOfCampsites = numberOfCampsites;
	}
	public void setClimate(String climate) {
		this.climate = climate;
	}
	public void setYearFounded(int yearFounded) {
		this.yearFounded = yearFounded;
	}
	public void setAnnualVisitorCount(int annualVisitorCount) {
		this.annualVisitorCount = annualVisitorCount;
	}
	public void setInspirationalQuote(String inspirationalQuote) {
		this.inspirationalQuote = inspirationalQuote;
	}
	public void setQuoteSource(String quoteSource) {
		this.quoteSource = quoteSource;
	}
	public void setParkDescription(String parkDescription) {
		this.parkDescription = parkDescription;
	}
	public void setEntryFee(int entryFee) {
		this.entryFee = entryFee;
	}
	public void setNumberAnimalSpecies(int numberAnimalSpecies) {
		this.numberAnimalSpecies = numberAnimalSpecies;
	}
}
