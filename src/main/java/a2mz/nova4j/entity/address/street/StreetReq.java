package a2mz.nova4j.entity.address.street;

/**
 * Created by Morozov Oleksandr on 12.12.2016.
 * 414D
 */
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import a2mz.nova4j.entity.common.MethodProperties;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
		"CityRef",
		"FindByString"
})
public class StreetReq implements MethodProperties {

	@JsonProperty("CityRef")
	private String cityRef;
	@JsonProperty("FindByString")
	private String findByString;

	private StreetReq() {
	}

	public static StreetReq create() {
		return new StreetReq();
	}

	/**
	 *
	 * @return
	 * The cityRef
	 */
	@JsonProperty("CityRef")
	public String getCityRef() {
		return cityRef;
	}

	/**
	 *
	 * @param cityRef
	 * The CityRef
	 */
	@JsonProperty("CityRef")
	public void setCityRef(String cityRef) {
		this.cityRef = cityRef;
	}

	public StreetReq withCityRef(String cityRef) {
		this.cityRef = cityRef;
		return this;
	}

	/**
	 *
	 * @return
	 * The findByString
	 */
	@JsonProperty("FindByString")
	public String getFindByString() {
		return findByString;
	}

	/**
	 *
	 * @param findByString
	 * The FindByString
	 */
	@JsonProperty("FindByString")
	public void setFindByString(String findByString) {
		this.findByString = findByString;
	}

	public StreetReq withFindByString(String findByString) {
		this.findByString = findByString;
		return this;
	}

}
