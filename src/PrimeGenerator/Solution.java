package PrimeGenerator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

//Find the mean, median and mode of all distances between 
//two consecutive prime numbers less than 1000000 (One million). 
//Here is sample reference for mean, median and mode.
//1 to 1000000

//prime numbers are all odd numbers except 2 (special case)
//The distances between two consecutive prime numbers are all even except from 2 to 3 (special case)
public class Solution {
	
	//Given a range of numbers, find all prime numbers and return them as an array list of integers
	public ArrayList<Integer> primeGenerator(int start, int end) {
		ArrayList<Integer> primeList = new ArrayList<Integer>();
		for(int i = start; i <= end; i++) {
			if(i == 1) { //special case 1 which is not a prime number
				continue;
			}
			if(i == 2) {
				primeList.add(i); //special case 2 which is a prime number and is even
				continue;
			}
			if(i % 2 == 0) { //every even number should not be a prime number
				continue;
			}
			boolean isPrime = true;
			for(int j = 2; j < i / 2; j++) { //if a number i is not divisible by numbers from 2 to half of itself i/2, it is a prime
				if(i % j == 0) { //if for any j that i is divisible by j, we say this number is not a prime number
					isPrime = false;
				}
			}
			if(isPrime) { //else they are prime numbers
				primeList.add(i);
			}
		}
		return primeList;
	}
	
	//Given an array list of prime numbers
	public ArrayList<Integer> primeDistances(ArrayList<Integer> a) {
		ArrayList<Integer> distanceArray = new ArrayList<Integer>();
		for(int i = 0; i < a.size() - 1; i++) {
			distanceArray.add(a.get(i + 1) - a.get(i));
		}
		return distanceArray;
	}
	
	//Calculate the mean of the distances
	public double getMean(ArrayList<Integer> a) {
		int numOfDistances = a.size();
		int sumOfDistances = 0;
		for(int i = 0; i < a.size(); i++) {
			sumOfDistances = sumOfDistances + a.get(i);
		}
		return (double)sumOfDistances/numOfDistances;
	}
	
	//Calculate the median of the distances
	public double getMedian(ArrayList<Integer> a) {
		Collections.sort(a);
		int median = 0;
		if(a.size() % 2 == 0) { //if the number of distances is even
			median = (a.get(a.size() / 2) + a.get(a.size() / 2 + 1)) / 2;
		}else { //if the number of distance is odd
			median = (a.get((a.size() + 1) / 2 - 1));
		}
		return median;
	}
	
	//Find the mode/modes which is the value/values that occur the most using hash map
	public ArrayList<Integer> getMode(ArrayList<Integer> a) {
		HashMap<Integer, Integer> distanceMap = new HashMap<Integer, Integer>();
		ArrayList<Integer> distanceMode = new ArrayList<Integer>();
		int HighestValue = 0;
		
		for(int i = 0; i < a.size(); i++) {
			//if number already in the hash map, its value + 1.
			if(distanceMap.containsKey(a.get(i))) {
				distanceMap.put(a.get(i), distanceMap.get(a.get(i)) + 1);
			//else give it a value 1.
			}else {
				distanceMap.put(a.get(i), 1);
			}
		}
		
		//Determine which value is the highest value
		for(int key : distanceMap.keySet()) {
	        if(distanceMap.get(key) > HighestValue) {
	        	HighestValue = distanceMap.get(key);
	        }
	    }
		
		//Determine how many highest value, then add them to the array list
	    for(int key : distanceMap.keySet()) {
	        if(distanceMap.get(key) == HighestValue)
	        	distanceMode.add(key);
	    }
		return distanceMode;
	}
	
	
	//main
	public static void main(String[] args) {
		Solution s = new Solution();
		int start = 1;
		int end = 1000000;
		ArrayList<Integer> a = s.primeGenerator(start, end);
		ArrayList<Integer> b = s.primeDistances(a);
		
		System.out.println("======================================================");
		System.out.print("Mean of distances: ");
		System.out.printf("%.2f", s.getMean(b)); //print only two decimal digits
		System.out.println();
		System.out.print("Median of distances: ");
		System.out.println(s.getMedian(b));
		System.out.print("Mode/Modes of distances: ");
		System.out.println(s.getMode(b));
		
		System.out.println("======================================================");
		System.out.print("Number of primes: ");
		System.out.println(a.size());
		
//		System.out.println("======================================================");
//		System.out.println("Primes from " + start + " to " + end);
//		for(int i = 0; i < a.size(); i++) {
//			System.out.println(a.get(i));
//		}
//		
//		System.out.println("======================================================");
//		System.out.print("Number of distances: ");
//		System.out.println(b.size());
//		
//		System.out.println("======================================================");
//		System.out.println("Prime distances from " + start + " to " + end);
//		for(int i = 0; i < b.size(); i++) {
//			System.out.println(b.get(i));
//		}
	}
}
