

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;


public class Assignment01 {
	
    /**
     * sorting function that implements quicksort*
     *
     * @param theActors unsorted array
     * @param <T>      Generic data type (Integer, String, Character)...
     */
    public static <T extends Comparable<T>> void sort(ArrayList<String> theActors) {
        quickSort(theActors, 0, theActors.size() - 1);
    }
	
    /**
     * Recursively quick sorting the array by randomPartitioning*
     *
     * @param theActors unsorted array
     * @param left     left boundary
     * @param right    right boundary
     * @param <T>      data type T
     */
    public static <T extends Comparable<T>> void quickSort(ArrayList<String> theActors, int left, int right) {
    	
    	if (left < right) {
    		int p = randomPartition(theActors, left, right);
    		quickSort(theActors, left, p-1);
    		quickSort(theActors, p, right);
    	}
    	

    }

    /**
     * random partitioning with Math.random() function to generate a random index *
     * *
     *
     * @param theActors unsorted array
     * @param left     left pointer
     * @param right    right pointer
     * @param <T>      data type T
     * @return perform sorting and return the index of pivot
     */
    public static <T extends Comparable<T>> int randomPartition(ArrayList<String> theActors, int left, int right) {
        int randomIndex = left + (int) (Math.random() * (right - left + 1));
        swap(theActors, randomIndex, right);
        return partition(theActors, left, right);
    }
	
	/**
     * Helper function to swap the elements in the array *
     *
     * @param theActors given array
     * @param i   index i
     * @param j   index j
     * @param <T> data type T
     */
    public static <T> void swap(ArrayList<String> theActors, int i, int j) {
        String temp = theActors.get(i);
        theActors.set(i, theActors.get(j));
        theActors.set(j, temp);
    }

    /**
     * partition function for quick sort *
     *
     * @param theActors   array to be sorted
     * @param left  left boundary
     * @param right right boundary
     * @param <T>   generic data type T
     * @return pivot index
     */
    public static <T extends Comparable<T>> int partition(ArrayList<String> theActors, int left, int right) {
        int index = left + right >> 1;
    	String pivot = theActors.get(index);
       
    	while (left <= right) {
            //1. keep checking left element in the array if is less than pivot, update the left boundary
        	while (theActors.get(left).compareTo(pivot) < 0) {
        		++left; 
        	}
            //2. keep checking pivot if is less than the right element in the array, update the right boundary
        	while (theActors.get(right).compareTo(pivot) > 0) {
        		--right;
        	}
            //3. check left with right then swap elements and update both boundary
        	if (left <= right) {
        		swap(theActors, left, right);
        		++left;
        		--right;
        	}
        	

        }
        return left;
        
    }
    
    /**
     * sorts the ArrayList of actors alphabetically by first name*
     * 
     * @param theActors is the ArrayList to be sorted
     * @return the sorted ArrayList of actors
     */
    public static ArrayList<String> sortActors(ArrayList<String> theActors) {
    	sort(theActors);
		return theActors;
    	
    }

    /**
     * takes the file and using the CSV Open library it reads all the cast information
     * and breaks it into an ArrayList of all the actor's names*
     * 
     * @param filename is the CSV file of that contains the titles of movies
     * and the actors to be read from
     * @return an ArrayList of all the actors in the file
     */
	public static ArrayList<String> makeActorsList(String filename) {
		
		ArrayList<String> actors = new ArrayList<String>();
		
		try {
			FileReader file = new FileReader(filename);
			BufferedReader buffer = new BufferedReader(file);
			CSVParser parser = CSVFormat.DEFAULT.withDelimiter(',').withHeader().parse(buffer);
			for (CSVRecord record : parser) {
				String str = record.get("cast");
				String str2 = str.replaceAll("\\{", "").replaceAll("\\}", "");
				String result = str2.replaceAll("\"", "");
				String result2 = result.replaceAll(":", ",");
				String[] arrOfStr = result2.split(", ", 1000);
				int j = 0;
				for (int i = 11; i < ((arrOfStr.length) - 1); i+=14) {
					actors.add(arrOfStr[i]);
					j++;
				}
			}
			
			buffer.close();
		}
		catch (IOException e){
			e.getStackTrace();
			System.out.println("got exception");
			e.printStackTrace();
		}
		
		sort(actors);
		return actors;
		
	}
	
	/**
	 * Using the CSV open library it searches for the actor in the cast category
	 * then if actor is found it prints the associated movie title name and
	 * character the actor played in the movie*
	 * 
	 * @param filename is the CSV file of that contains the titles of movies
     * and the actors to be read from
	 * @param theActor is the name of the actor to be searched for
	 */
	public static void printMovieList(String filename, String theActor) {
		
		System.out.println("Actor: " + theActor);
		
		try {
			FileReader file = new FileReader(filename);
			BufferedReader buffer = new BufferedReader(file);
			CSVParser parser = CSVFormat.DEFAULT.withDelimiter(',').withHeader().parse(buffer);
			for (CSVRecord record : parser) {
				String str = record.get("cast");
				String str2 = str.replaceAll("\\{", "").replaceAll("\\}", "");
				String result = str2.replaceAll("\"", "");
				String result2 = result.replaceAll(":", ",");
				String[] arrOfStr = result2.split(", ", 1000);
				int j = -11;
				for (int i = 0; i < ((arrOfStr.length) - 1); i++) {
					if(arrOfStr[i].toString().equals(theActor)) {
						
						String movieTitle = record.get("title");
						String character = arrOfStr[j + 14];
						System.out.println("* Movie: " + movieTitle + " as " + character);
					}
				}
			}
			buffer.close();
		}
		catch (IOException e){
			e.getStackTrace();
			System.out.println("got exception");
			e.printStackTrace();
		}
	}
	
	/**
	 * uses binary search to get the index of the actor
	 * with the closest name to the input
	 * 
	 * @param actors is the ArrayList of actor names
	 * @param target is the user inputed name
	 * @return the index of the actor with the closest name
	 * to the inputed name
	 */
	public static int nearestActorIndex(ArrayList actors, String target){

	  	int min = 0 ;
    	int max = actors.size() -1;
    	int index;

    	while (min <= max) {
    		int mid = (min+max)/2;
    		if (actors.get(mid).equals(target)) {
    			index = mid;
    			return index;
    		}else if (((String) actors.get(mid)).compareTo(target) < 0 ) {
    			min = mid + 1;
    		}else{
    			max = mid - 1;
    		}
    	}
    	return min;
		
	}
	
	/**
	 * uses binary search to see if the actor is in the list
	 * 
	 * @param actors is the ArrayList of actor names
	 * @param target is the user inputed name
	 * @return true if actor is in list, false if not
	 */
	public static <T extends Comparable<T>> boolean searchActors(ArrayList actors, String target){

	  	int min = 0 ;
    	int max = actors.size() -1;
    	int index;

    	while (min <= max) {
    		int mid = (min+max)/2;
    		if (actors.get(mid).toString().equals(target)) {
    			index = mid;
    			return true;
    		}else if (((String) actors.get(mid)).compareTo(target) < 0 ) {
    			min = mid + 1;
    		}else{
    			max = mid - 1;
    		}
    	}
    	return false;
		
	}
	
	public static void main(String[] args) {
		
		System.out.println("Welcome to the Movie Wall!");
		boolean exit = false;
		Scanner scnr = new Scanner(System.in);
		while(exit == false) {
			System.out.println("Enter the name of an actor (or \"EXIT\" to quit): ");
			String actor = scnr.nextLine();
			if(actor.equals("EXIT")) {
				exit = true;
			}else {
				ArrayList<String> actorsList = makeActorsList(args[0]);
				System.out.println(actor);
				if(searchActors(actorsList, actor) == false) {
					actor = actorsList.get(nearestActorIndex(actorsList, actor));
					System.out.println("No such actor. Did you mean \"" + actor + "\" (Y/N): ");
					String answer = scnr.nextLine();
					if(answer.equals("N")) {
						System.out.println("No actor found. Please try again.");
					}else {
						printMovieList(args[0], actor);
					}
				}else {
					printMovieList(args[0], actor);
				}
			}
		}
		scnr.close();
	}

}
