/**
 * 
 */
package com.cuas.model;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Cüneyt AŞKIN
 * Model for Form fields
 *
 */

public class Dealer implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank(message="Blank.dealer.identity")
	private String identity;
	
	@NotBlank(message="Blank.dealer.firstName")
	private String firstName;
	
	@NotBlank(message="NotBlank.newDealer.lastName")
	private String lastName;
	
	@NotBlank(message="Blank.dealer.phoneNumber")
	private String phoneNumber;
	
	@NotBlank(message="Blank.dealer.email")
	private String email;
	
	@NotBlank(message="Blank.dealer.birthOfDate")
	private String birthOfDate;
	
	@NotBlank(message="Blank.dealer.adress")
	private String adress;
	
	@NotNull(message="Blank.dealer.isRetailSale")
	private Boolean isRetailSale; // PERAKENDE TİCARETİ  İLE UĞRAŞTINIZ MI?
	
	@NotBlank(message="Blank.dealer.whyChoose")
	private String whyChoose; // LOKUMCU BABA'YI TERCİH ETMENİZİN SEBEBİ NEDİR?
	
	@NotBlank(message="Blank.dealer.whichRegion")
	private String whichRegion; // HANGİ İL/İLÇE/SEMT İÇİN LOKUMCU BABA İŞLETMECİLİĞİ DÜŞÜNÜYORSUNUZ?
	
	private double investmentAmount;// YATIRIM MİKTARINIZ NEDİR?
	
	@NotBlank(message="Blank.dealer.whatElseWant")
	private String whatElseWant;// EKLEMEK İSTEDİKLERİNİZ:

	public Dealer() {}

	public Dealer(String identity, String firstName, String lastName, String phoneNumber, String email,
			String birthOfDate, String adress, Boolean isRetailSale, String whyChoose, String whichRegion,
			double investmentAmount, String whatElseWant) {
		super();
		this.identity = identity;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.birthOfDate = birthOfDate;
		this.adress = adress;
		this.isRetailSale = isRetailSale;
		this.whyChoose = whyChoose;
		this.whichRegion = whichRegion;
		this.investmentAmount = investmentAmount;
		this.whatElseWant = whatElseWant;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirthOfDate() {
		return birthOfDate;
	}

	public void setBirthOfDate(String birthOfDate) {
		this.birthOfDate = birthOfDate;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public Boolean getIsRetailSale() {
		return isRetailSale;
	}

	public void setIsRetailSale(Boolean isRetailSale) {
		this.isRetailSale = isRetailSale;
	}

	public String getWhyChoose() {
		return whyChoose;
	}

	public void setWhyChoose(String whyChoose) {
		this.whyChoose = whyChoose;
	}

	public String getWhichRegion() {
		return whichRegion;
	}

	public void setWhichRegion(String whichRegion) {
		this.whichRegion = whichRegion;
	}

	public double getInvestmentAmount() {
		return investmentAmount;
	}

	public void setInvestmentAmount(double investmentAmount) {
		this.investmentAmount = investmentAmount;
	}

	public String getWhatElseWant() {
		return whatElseWant;
	}

	public void setWhatElseWant(String whatElseWant) {
		this.whatElseWant = whatElseWant;
	}

	@Override
	public String toString() {
		return "Dealer [identity=" + identity + ", firstName=" + firstName + ", lastName=" + lastName + ", phoneNumber="
				+ phoneNumber + ", email=" + email + ", birthOfDate=" + birthOfDate + ", adress=" + adress
				+ ", isRetailSale=" + isRetailSale + ", whyChoose=" + whyChoose + ", whichRegion=" + whichRegion
				+ ", investmentAmount=" + investmentAmount + ", whatElseWant=" + whatElseWant + "]";
	}
}