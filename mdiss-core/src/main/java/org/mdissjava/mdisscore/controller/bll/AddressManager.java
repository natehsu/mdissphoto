package org.mdissjava.mdisscore.controller.bll;

import java.util.List;

import org.mdissjava.mdisscore.model.pojo.City;
import org.mdissjava.mdisscore.model.pojo.Country;
import org.mdissjava.mdisscore.model.pojo.State;

public interface AddressManager {
	List<Country> getAllCountries();
	List<State> getStateList(Country Country);
	List<City> getCityList(Country Country,State State);
	
	
}
