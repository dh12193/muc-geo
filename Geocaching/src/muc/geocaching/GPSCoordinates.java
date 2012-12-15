package muc.geocaching;

public class GPSCoordinates {
	private double longitude;
	private double latitude;
	public double getLongitude() {
		return longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public GPSCoordinates(double longitude, double latitude) {
		super();
		this.longitude = longitude;
		this.latitude = latitude;
	}
}
