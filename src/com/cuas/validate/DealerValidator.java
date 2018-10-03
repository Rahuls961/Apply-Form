package com.cuas.validate;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.cuas.model.Dealer;

/**
 * @author Cuneyt AŞKIN
 *         Validator form field and error message 
 *         1-identity 
 *         2-phone 
 *         3-investment amount
 *         4-birthOfDate
 */
@Component
public class DealerValidator implements Validator {

	public void validate(Object target, Errors errors) {
		
		Dealer dealer = (Dealer) target;
		// Identity Size[11]
		if (!dealer.getIdentity().isEmpty() && dealer.getIdentity().trim().length() != 11) {
			errors.rejectValue("identity", "Size.newDealer.identity");			
		}

		// Phone Number Size[11] 
		if (!dealer.getPhoneNumber().isEmpty() && dealer.getPhoneNumber().trim().length() != 11) {
			errors.rejectValue("phoneNumber", "Size.newDealer.phoneNumber");			
		}

		// Email @ Check
		if (!dealer.getEmail().isEmpty() && !dealer.getEmail().contains("@")) {
			errors.rejectValue("email", "Invalid.newDealer.email");
		}

		// İnvestment Amount not negative
		if (dealer.getInvestmentAmount() < 0) {
			errors.rejectValue("investmentAmount", "Negative.newDealer.investmentAmount");
		}
		else if(dealer.getInvestmentAmount() == 0){
			errors.rejectValue("investmentAmount", "NotNull.newDealer.investmentAmount");
		}	
	}

	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}
}
