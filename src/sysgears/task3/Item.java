package sysgears.task3;

public class Item {
	
	private String cipher;
	private String driverCode;
	private String wayBill;
	
	private boolean danger;
	private boolean fragile;
	
	private String temperature;
	
	private String decodedItem;

	public Item() {
	}

	public Item(String cipher, String driverCode, String wayBill, boolean danger, boolean fragile, String temperature,
			String decodedItem) {
		this.cipher = cipher;
		this.driverCode = driverCode;
		this.wayBill = wayBill;
		this.danger = danger;
		this.fragile = fragile;
		this.temperature = temperature;
		this.decodedItem = decodedItem;
	}

	@Override
	public String toString() {
		return "Item [cipher=" + cipher + ", driverCode=" + driverCode + ", wayBill=" + wayBill + ", danger=" + danger
				+ ", fragile=" + fragile + ", temperature=" + temperature + ", decodedItem=" + decodedItem + "]";
	}

	public String getCipher() {
		return cipher;
	}

	public void setCipher(String cipher) {
		this.cipher = cipher;
	}

	public String getDriverCode() {
		return driverCode;
	}

	public void setDriverCode(String driverCode) {
		this.driverCode = driverCode;
	}

	public String getWayBill() {
		return wayBill;
	}

	public void setWayBill(String wayBill) {
		this.wayBill = wayBill;
	}

	public boolean isDanger() {
		return danger;
	}

	public void setDanger(boolean danger) {
		this.danger = danger;
	}

	public boolean isFragile() {
		return fragile;
	}

	public void setFragile(boolean fragile) {
		this.fragile = fragile;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getDecodedItem() {
		return decodedItem;
	}

	public void setDecodedItem(String decodedItem) {
		this.decodedItem = decodedItem;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cipher == null) ? 0 : cipher.hashCode());
		result = prime * result + (danger ? 1231 : 1237);
		result = prime * result + ((decodedItem == null) ? 0 : decodedItem.hashCode());
		result = prime * result + ((driverCode == null) ? 0 : driverCode.hashCode());
		result = prime * result + (fragile ? 1231 : 1237);
		result = prime * result + ((temperature == null) ? 0 : temperature.hashCode());
		result = prime * result + ((wayBill == null) ? 0 : wayBill.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (cipher == null) {
			if (other.cipher != null)
				return false;
		} else if (!cipher.equals(other.cipher))
			return false;
		if (danger != other.danger)
			return false;
		if (decodedItem == null) {
			if (other.decodedItem != null)
				return false;
		} else if (!decodedItem.equals(other.decodedItem))
			return false;
		if (driverCode == null) {
			if (other.driverCode != null)
				return false;
		} else if (!driverCode.equals(other.driverCode))
			return false;
		if (fragile != other.fragile)
			return false;
		if (temperature == null) {
			if (other.temperature != null)
				return false;
		} else if (!temperature.equals(other.temperature))
			return false;
		if (wayBill == null) {
			if (other.wayBill != null)
				return false;
		} else if (!wayBill.equals(other.wayBill))
			return false;
		return true;
	}

}
