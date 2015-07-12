package arrays;

import java.util.Arrays;

import utils.MyUtilities;

public class ArrayPrograms {
	
	/**
	 * problem 67:sorting karumachi
	 * 
	 * even nos in 1 side and odd no in 1 side
	 * @param array
	 * 
	 */
	public static void seggregateEvenAndOddNo(Integer[] array) {
		//numbers on left side of evenIdx are even nos
		int evenIdx = 0;
		//numbers on right side of oddIdx are odd nos
		int oddIdx = array.length - 1;
		int noAtEvenIdx;
		boolean isNoAtEvenIdxEven;
		int noAtOddIdx;
		boolean isNoAtOddIdxOdd;
		while(evenIdx < oddIdx) {
			noAtEvenIdx = array[evenIdx];
			noAtOddIdx= array[oddIdx];
			
			isNoAtEvenIdxEven = MyUtilities.isEven(noAtEvenIdx);
			isNoAtOddIdxOdd = MyUtilities.isOdd(noAtOddIdx);
			
			if(isNoAtEvenIdxEven && isNoAtOddIdxOdd) {
				evenIdx++;
				oddIdx --;
			} else if(!isNoAtEvenIdxEven && !isNoAtOddIdxOdd) {
				MyUtilities.swap(array, evenIdx, oddIdx);
				evenIdx++;
				oddIdx --;
			} else {
				if(isNoAtEvenIdxEven) {
					//both even
					evenIdx++;
				} else {
					//both odd
					oddIdx --;
				}
			}
		}
	}
	
	/**
	 * problem 70:sorting karumachi
	 * 
	 * array changed such that 0 comes first,then 1,then 2
	 * @param array of 0,1,2
	 * 
	 */
	public static void seggregateZeroesOnesThenTwoes(Integer[] array) {
		int leftZeroIdx = 0;
		int rightTwoIdx = array.length - 1;
		
		int itr = array.length - 1;
		while(itr > leftZeroIdx) {
			switch (array[itr]) {
			case 0:
				MyUtilities.swap(array, leftZeroIdx, itr);
				leftZeroIdx++;
				break;
			case 1:
				itr--;
				break;
			case 2:
				MyUtilities.swap(array, rightTwoIdx, itr);
				rightTwoIdx--;
				itr--;
				break;

			default:
				break;
			}
		}
	}
	
	/**
	 * array = {a1,a2...an,b1,b2...bn} -> {a1,b1,a2,b2...an,bn}
	 * @param array of length 2n
	 */
	public static void shuffleArray(Integer[] array) {
		shuffleArray(array,0,(array.length-1)/2,((array.length-1)/2)+1,array.length-1);
	}
	
	public static void shuffleArray(Integer[] array,int startFirstArray,int endFirstArray,
			int startSecondArray,int endSecondArray) {
		//swap a2,b1
		MyUtilities.swap(array, startFirstArray+1, startSecondArray);
		
		shuffleArray(array, startFirstArray+2, endFirstArray, startSecondArray+2, endSecondArray);
		//now put a2b2 in its correct place
	}
	
	/**
	 * Given 2 arrays, sum of numbers(1 from array1 and other from array2) equals k or not
	 * @param array1
	 * @param array2
	 * @param k
	 * @return
	 */
	public static boolean isSumOfTwoElementsInTwoArraysEqualsK(Integer[] array1, Integer[] array2, int k) {
		Arrays.sort(array1);
		Arrays.sort(array2);
		int array1Itr = 0;
		int array2Itr = array2.length-1;
		
		int array1No;
		int array2No;
		int sum;
		while(array1Itr <array1.length && array2Itr >=0 ) {
			array1No = array1[array1Itr];
			array2No = array2[array2Itr];
			sum = array1No + array2No;
			if(sum == k) {
				return true;
			} else if(sum < k) {
				array1Itr++;
			} else {
				array2Itr--;
			}
			
		}
		
		return false;
	}
	/**
	 * http://www.geeksforgeeks.org/find-the-minimum-distance-between-two-numbers/
	 * Given an unsorted array arr[] and two numbers x and y, find the minimum distance
	 *  between x and y in arr[]. The array might also contain duplicates. You may assume
	 *   that both x and y are different and present in arr[].
	 * @param array
	 * @param no1
	 * @param no2
	 * @return
	 */
	public static int getMinimumDistanceBtwNumbers(Integer[] array,int no1,int no2) {
		
		int itr = 0;
		int minDif = Integer.MAX_VALUE;
		int noAtItr;
		boolean prevSeenNo1 = false;
		boolean prevSeenNo2 = false;
		int prevSeenItr = 0;
		while(itr < array.length) {
			noAtItr = array[itr];
			if(noAtItr == no1) {
				if(prevSeenNo2) {
					minDif = Math.min(itr - prevSeenItr, minDif);
				}
				prevSeenItr = itr;
			} else if(noAtItr == no2) {
				if(prevSeenNo1) {
					minDif = Math.min(itr - prevSeenItr, minDif);
				}
				prevSeenItr = itr;
			} else {
				//nothing to do
			}
			itr++;
		}
		
		return minDif;
	}
	
	public static void main(String[] args) {
		Integer []array1 = new Integer[]{1,7,4,9};
		Integer []array2 = new Integer[]{3,6,8,5};
		//MyUtilities.populateIntegerArrayWithRandomNos(array,3);
		MyUtilities.printSingleDimensionArray(array1);
		MyUtilities.printSingleDimensionArray(array2);
		boolean isSum = isSumOfTwoElementsInTwoArraysEqualsK(array1, array2, 11);
		System.out.println(isSum);
	}
}
