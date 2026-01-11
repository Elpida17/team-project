package com.example.demo.dto;

/**
 * Χρησιμοποιείται για τη μεταφορά των κριτηρίων φιλτραρίσματος από το Frontend.
 * Τα πεδία είναι public για λόγους ταχύτητας.
 */

public class ProductSearchDto {
	public String type;
    public String brand;
    public Double minPrice;
    public Double maxPrice;
}
