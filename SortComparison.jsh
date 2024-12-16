import java.util.ArrayList;

int cardCompare(String card1, String card2) {
    // Set Priority
	String order = "HCDS";
	
	// Getting letters and numbers for comparison
	char suit1 = card1.charAt(card1.length() - 1);
    int num1 = Integer.parseInt(card1.substring(0, card1.length() - 1));

    char suit2 = card2.charAt(card2.length() - 1);
    int num2 = Integer.parseInt(card2.substring(0, card2.length() - 1));

    // Comparison
    int suitComparison = Integer.compare(order.indexOf(suit1), order.indexOf(suit2));
    if (suitComparison != 0) {
        return suitComparison;
    }

    return Integer.compare(num1, num2);
}

ArrayList<String> bubbleSort(ArrayList<String> array){
	// Getting arry length
	int n = array.size();
	boolean sorted=true;
	for (int i = n - 1; i >= 0; i--) {
		for (int j = 0; j < i; j++) {
			// Comparing and sorting array
			if (cardCompare(array.get(j),array.get(j+1))>0){
			String temp = array.get(j);
			array.set(j, array.get(j+1));
			array.set(j+1, temp);
			sorted = false;
				}
		}
	if (sorted) {
		break;
		}
	}
	return array;
}

ArrayList<String> merge(ArrayList<String> left, ArrayList<String> right){
	// Creating placeholder variable
	ArrayList<String> result = new ArrayList<String>();
	int i = 0, j = 0;
	// Comparison
	while (i < left.size() && j < right.size()) {
		if ( cardCompare(left.get(i),right.get(j)) <= 0) {
			result.add(left.get(i++));
		} else {
			result.add(right.get(j++));
		}
	}
	// Combine sorted lists and then return the result
	result.addAll(left.subList(i, left.size()));
	result.addAll(right.subList(j, right.size()));
	return result;
	
	
	
}

ArrayList<String> mergeSort(ArrayList<String> array){
	// Recursion
	if (array.size() <=1){
		return array;
	}
	//Splitting the array in half
	int mid = array.size()/2;
	ArrayList<String> left = new ArrayList<String>(array.subList(0, mid));
	ArrayList<String> right = new ArrayList<String>(array.subList(mid,array.size()));
	
	//Returns solution or does another iteration
	return merge(mergeSort(left), mergeSort(right));
}

long measureBubbleSort(String fileName)
{
	// Create array to store contents of file
	ArrayList<String> sortTest = new ArrayList<String>();
	try {

	Scanner scanner = new Scanner(new File("C:\\Users\\andre\\Downloads\\"+fileName));
	// Get File Put terms in array
	while (scanner.hasNext()) {
	sortTest.add(scanner.nextLine());
	}
	scanner.close();
	} catch( IOException io ) {
		System.out.print("File "+fileName+" not found");
	}
	
	// Bubble Sort Runtime
	
	long start = System.currentTimeMillis();
	bubbleSort(sortTest);
	long end = System.currentTimeMillis();
	long totalTime = end - start;
	System.out.println(totalTime);
	
	return totalTime;
}	

long measureMergeSort(String fileName)
{
	//Copypasta
	
	// Create array to store contents of file
	ArrayList<String> sortTest = new ArrayList<String>();
	try {

	Scanner scanner = new Scanner(new File("C:\\Users\\andre\\Downloads\\"+fileName));
	// Get File Put terms in array
	while (scanner.hasNext()) {
	sortTest.add(scanner.nextLine());
	}
	scanner.close();
	} catch( IOException io ) {
		System.out.print("File "+fileName+" not found");
	}
	
	// Merge Sort Runtime
	ArrayList<String> Test = new ArrayList<String>();
	long start = System.currentTimeMillis();
	mergeSort(sortTest);
	long end = System.currentTimeMillis();
	long totalTime = end - start;
	System.out.println(totalTime);
	System.out.println(Test);
	return totalTime;
}	

void sortComparison(String[] string){
	
	// sort files and get times
	
	//List for results
	ArrayList<String> Bresults = new ArrayList<String>();
	ArrayList<String> Mresults = new ArrayList<String>();
	
	for(int i = 0; i < 3; i++){
	
		// bubblesort and store results
		String r = measureBubbleSort(string[i])+"";
		Bresults.add(r);
	}
	
		
	for(int i = 0; i < 3; i++){
		// mergesort and store results
		String r = measureMergeSort(string[i])+"";
		Mresults.add(r);
	}
	
		
	
	// write files into excel
	try {
		FileWriter writer = new FileWriter("C:\\Users\\andre\\Downloads\\sortComparison.csv");
		writer.write(",10,100,10000\n");
		writer.write("BubbleSort," + String.join(",", Bresults) + "\n");
		writer.write("MergeSort," + String.join(",", Mresults) + "\n");
		writer.close();
	} catch (IOException e) {
		System.out.println("An error occurred while writing to the file: " + e.getMessage());
	}
	
}


sortComparison(new String[] {"sort10.txt", "sort100.txt", "sort10000.txt"})
                    