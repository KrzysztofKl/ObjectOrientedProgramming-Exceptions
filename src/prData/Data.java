package prData;

//run configuration -- arguments -- program arguments (example 0 10 1 2 3 4)
//Z:\POO\test

public class Data {

	private double[] data;
	private String[] errors;
	private double min;
	private double max;
	
	
	public Data(String[] d, double min, double max) {
		
		double[] tempData = new double[d.length];
		String[] tempError = new String[d.length];

		int tempDataCounter=0;
		int tempErrorCounter=0;
		
		for (int i = 0; i< d.length; i++){
			try{
				tempData[tempDataCounter] = Double.parseDouble(d[i]);
				tempDataCounter++;
				
				
			}catch(NumberFormatException e){
				tempError[tempErrorCounter] = d[i];
				tempErrorCounter++;
			}
		}
		
		data = new double[tempDataCounter];
		errors = new String[tempErrorCounter];
		
		System.arraycopy(tempData, 0, data, 0, tempDataCounter);
		System.arraycopy(tempError, 0, errors, 0, tempErrorCounter);
		
		this.min = min;
		this.max = max;
	}
	
	
	public double averageCalculation() throws DataException{
		double elementsNumber = 0;
		double elementsSum = 0;

		for (int i = 0; i < data.length; i++) {
			if (data[i] <= max && data[i] >= min) {
				elementsSum += data[i];
				elementsNumber++;
			}
		}

		if (elementsNumber == 0) {
			throw new DataException("ERROR");
			
		} else {
			return elementsSum / elementsNumber;
		}

	}
	
	
	public double standardDeviationCalculation() throws DataException {

		try {
			double elementsNumber = 0;
			double aravage = averageCalculation();
			double values = 0;

			for (int i = 0; i < data.length; i++) {
				if (data[i] <= max && data[i] >= min) {
					values += (data[i] - aravage) * (data[i] - aravage);
					elementsNumber++;
				}
			}
			return Math.sqrt(values / elementsNumber);
		} catch (Exception e) {
			throw new DataException("ERROR");
		}
	}
	
	
	public void setRange(String minmax) {
		String[] parts = minmax.split(";");

		try {
			 String str1 = parts[0];
			 String str2 = parts[1];

			// String[] parts = minmax.split(";");
			this.min = Double.parseDouble(str1);
			this.max = Double.parseDouble(str2);

		} catch (IndexOutOfBoundsException e) {
			System.out.println("Error, no enough values (" + minmax + ")");
		} catch (NumberFormatException e) {
			System.out.println("Error, parsing string to real number (For input string: '" + parts[1] + "')");
		}
	}
	
	
	public double[] getData() {
		return data;
	}

	public String[] getErrors() {
		return errors;
	}
	
	public String toString() {
		String maxMin = "";
		String dataList = "";
		String errorsList = "";
		String count = "";

		maxMin = "Min: " + min + " Max: " + max + ",";
		
		for (int i = 0; i < data.length; i++)
			dataList += data[i] + ", ";
		
		for (int i = 0; i < errors.length; i++)
			errorsList += errors[i] + ", ";

		
		//count = "Aravage: " + averageCalculation();
		
		try{		
		count = "Aravage: " + averageCalculation();
		}catch(DataException e){
			count = "Arravage: " + e.getMessage();
		}		
		
		try{		
		count += ", Standard Deviation: " + standardDeviationCalculation();
		}catch(DataException e){
			count += ", Standard Deviation: " + e.getMessage();
		}
		
		
		
		return maxMin + "\n[ " + dataList + "],\n[" + errorsList + "],\n" + count; 
				
	}
	
	public void dataProcessing(String[] dat) {
		
	}
	
	

}

