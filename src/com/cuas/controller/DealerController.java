/**
 * 
 */
package com.cuas.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.cuas.model.Dealer;
import com.cuas.service.DealerService;
import com.cuas.validate.DealerValidator;

/**
 * @author Cuneyt AŞKIN Handles requests for the DealerForm Page.
 */
@Controller
public class DealerController {
	List<String> cityList = new ArrayList<>();
	List<String> townList = new ArrayList<>();

	private DealerService dealerService;

	@Autowired
	private DealerValidator dealerDalivator;

	@Autowired
	public void setDealerService(DealerService dealerService) {
		this.dealerService = dealerService;
	}

	/** Redirect index.jsp to dealer_form */
	@RequestMapping(value = "/redirect")
	public String redirect() {
		return "redirect:apply";
	}

	/** Dealer Apply Forms */
	@RequestMapping(value = "/apply", method = RequestMethod.GET)
	public String getNewDealerForm(@ModelAttribute("newDealer") Dealer newDealer, Model model,
			HttpServletRequest request) {
		try {
			request.getSession().setAttribute("listCity", prepareWhichRegionCityList());
			request.getSession().setAttribute("listTown", chooseTown());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "dealer_form";
	}

	/** Added new Dealer */
	@RequestMapping(value = "/apply", method = RequestMethod.POST)
	public String processAddNewDealerForm(@ModelAttribute("newDealer") @Valid Dealer newerDealerAdded,
			BindingResult bindingResult, Model model, HttpServletRequest request) {

		// Validation Code
		dealerDalivator.validate(newerDealerAdded, bindingResult);

		try {
			if (bindingResult.hasErrors()) {
				return "dealer_form";
			}

			// create table and Database
			this.dealerService.createDealerTableAndDatabase();
			Dealer oldDealer = this.dealerService.findDealerById(newerDealerAdded.getIdentity());
			// System.out.println("Sout>>>New Dealer Adding..." + oldDealer);
			if (oldDealer == null) {
				// Save data to db by Service:DealerServiceImp
				this.dealerService.saveDealer(newerDealerAdded);
				request.getSession().setAttribute("successInfo", "Bayilik Ön Başvurunuz Başarıyla Alınmıştır.");
			} else if (oldDealer != null && oldDealer.getIdentity().equals(newerDealerAdded.getIdentity())) {
				request.getSession().setAttribute("serverError",
						"Daha önce başvurunuz alınmıştır.En yakın zamanda size dönüş sağlanacaktır.");
			}
		} catch (Exception ex) {
			request.getSession().setAttribute("serverError", "Sunucu bakımdadır. Lütfen daha sonra tekrar deneyiniz.");
		}
		return "redirect:/apply";
	}

	/**
	 * ReferenceData for isRetailSale, chooseLanguageOptions, whichRegionCityList
	 */
	@ModelAttribute("conditionMapRetailSale")
	public HashMap<String, String> prepareConditionMap() {
		HashMap<String, String> isRetailSaleConditions = new HashMap<String, String>();
		isRetailSaleConditions.put("Yes", "  Yes");
		isRetailSaleConditions.put("No", "  No");
		return isRetailSaleConditions;
	}

	@ModelAttribute("chooseLanguageOptions")
	public HashMap<String, String> chooseLanguage() {
		HashMap<String, String> chooseLanguageOptions = new HashMap<String, String>();
		chooseLanguageOptions.put("TR", "TR");
		chooseLanguageOptions.put("EN", "EN");
		return chooseLanguageOptions;
	}

	public List<String> prepareWhichRegionCityList() throws FileNotFoundException, IOException, ParseException {
		return cityList;
	}

	/**
	 * JSON data parsing file and will be add Dropdown
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws ParseException
	 */
	public List<String> chooseTown() throws FileNotFoundException, IOException, ParseException {
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader("il_ilce.json"));
		JSONArray jsonArray = (JSONArray) obj;

		/** Get City */
		Iterator<?> i = jsonArray.iterator();
		while (i.hasNext()) {
			JSONObject jsonObject = (JSONObject) i.next();
			String city = (String) jsonObject.get("il");
			cityList.add((String) jsonObject.get("il"));

			/** Get Town */
			jsonArray = (JSONArray) jsonObject.get("ilceleri");
			if (city.equals("Tekirdağ"))
				for (int a = 0; a < jsonArray.size(); a++) {
					townList.add((String) jsonArray.get(a));
				}
		}
		return townList;
	}
}
