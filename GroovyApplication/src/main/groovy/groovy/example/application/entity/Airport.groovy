package groovy.example.application.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(["metaClass"])
 class Airport {
	     int id
	     String iata
	     String icao
	     String name
	     String location
	     String street_number
	     String street
	     String city
	     String county
	     String state
	     String country_iso
	     String country
	     Airport() {
			super()
			// TODO Auto-generated constructor stub
		}
		 Airport(int id, String iata, String icao, String name, String location, String street_number,
				String street, String city, String county, String state, String country_iso, String country,
				String postal_code, String phone, double latitude, double longitude, int uct, String website) {
			super()
			this.id = id
			this.iata = iata
			this.icao = icao
			this.name = name
			this.location = location
			this.street_number = street_number
			this.street = street
			this.city = city
			this.county = county
			this.state = state
			this.country_iso = country_iso
			this.country = country
			this.postal_code = postal_code
			this.phone = phone
			this.latitude = latitude
			this.longitude = longitude
			this.uct = uct
			this.website = website
		}
		@Override
		 String toString() {
			return "Airport [id=" + id + ", iata=" + iata + ", icao=" + icao + ", name=" + name + ", location="
					+ location + ", street_number=" + street_number + ", street=" + street + ", city=" + city
					+ ", county=" + county + ", state=" + state + ", country_iso=" + country_iso + ", country="
					+ country + ", postal_code=" + postal_code + ", phone=" + phone + ", latitude=" + latitude
					+ ", longitude=" + longitude + ", uct=" + uct + ", website=" + website + "]"
		}
		 int getId() {
			return id
		}
		 void setId(int id) {
			this.id = id
		}
		 String getIata() {
			return iata
		}
		 void setIata(String iata) {
			this.iata = iata
		}
		 String getIcao() {
			return icao
		}
		 void setIcao(String icao) {
			this.icao = icao
		}
		 String getName() {
			return name
		}
		 void setName(String name) {
			this.name = name
		}
		 String getLocation() {
			return location
		}
		 void setLocation(String location) {
			this.location = location
		}
		 String getStreet_number() {
			return street_number
		}
		 void setStreet_number(String street_number) {
			this.street_number = street_number
		}
		 String getStreet() {
			return street
		}
		 void setStreet(String street) {
			this.street = street
		}
		 String getCity() {
			return city
		}
		 void setCity(String city) {
			this.city = city
		}
		 String getCounty() {
			return county
		}
		 void setCounty(String county) {
			this.county = county
		}
		 String getState() {
			return state
		}
		 void setState(String state) {
			this.state = state
		}
		 String getCountry_iso() {
			return country_iso
		}
		 void setCountry_iso(String country_iso) {
			this.country_iso = country_iso
		}
		 String getCountry() {
			return country
		}
		 void setCountry(String country) {
			this.country = country
		}
		 String getPostal_code() {
			return postal_code
		}
		 void setPostal_code(String postal_code) {
			this.postal_code = postal_code
		}
		 String getPhone() {
			return phone
		}
		 void setPhone(String phone) {
			this.phone = phone
		}
		 double getLatitude() {
			return latitude
		}
		 void setLatitude(double latitude) {
			this.latitude = latitude
		}
		 double getLongitude() {
			return longitude
		}
		 void setLongitude(double longitude) {
			this.longitude = longitude
		}
		 int getUct() {
			return uct
		}
		 void setUct(int uct) {
			this.uct = uct
		}
		 String getWebsite() {
			return website
		}
		 void setWebsite(String website) {
			this.website = website
		}
		 String postal_code
	     String phone
	     double latitude
	     double longitude
	     int uct
	     String website
	}



