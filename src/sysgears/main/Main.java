package sysgears.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import sysgears.task1.Squarer;
import sysgears.task2.SortedIndex;
import sysgears.task3.CsvFileWriter;
import sysgears.task3.Decoder;
import sysgears.task3.Item;
import sysgears.task4.Point;
import sysgears.task4.Polygon;

public class Main {

	public static void main(String[] args) throws Exception{
		
		/*
		 * Task 1
		 */
		int n = 99;
		
		System.out.printf("Task 1.\nAproximated square Root of %d is %d\n===============================\n", n, Squarer.getRoot(n));
		
		/*
		 * Task 2
		 */
		int[] nonSortedArray = {1, 1, 4, 1, 7};
		int nSmallest = 3;
		
		SortedIndex sortedIndex = new SortedIndex();
		
		System.out.printf("Task 2.\n%d element in sorted increasingly array is %d\n===============================\n", nSmallest,  sortedIndex.getSortedIndex(nonSortedArray, nSmallest));
		
		/*
		 * Task 3
		 */
		List<String> decodedItemList = new ArrayList<String>();
		decodedItemList.add("CAZgRf820167151156145");
		decodedItemList.add("RMuiRdf010160141151156164");
		decodedItemList.add("lims8r3860lims1631411561441");
		decodedItemList.add("GZQRyr6870GZQR+0041431501451451631455A");
		decodedItemList.add("qkMfPjrd0561411551551651561511641511571567");
		decodedItemList.add("EOcTkerf389­0201511431450551431621451411550");	

		System.out.println("Task 3.");
		CsvFileWriter.writeCsvFile("OrderList.csv", decodedItemList);
		System.out.println("===============================");

		/*
		 * Task 4
		 */
		List<Point> pointList = new ArrayList<Point>();
		
		pointList.add(new Point(1, 1));
		pointList.add(new Point(2, 1));
		pointList.add(new Point(3, 1));
		pointList.add(new Point(4, 1));
		pointList.add(new Point(4, 2));
		pointList.add(new Point(5, 1));
		pointList.add(new Point(6, 1));
		pointList.add(new Point(2, 0));
		pointList.add(new Point(4, -1));

		System.out.println("Task 4.\nIn order to draw non-intersection Polygon, connect the Points:\n" + Polygon.drawNonIntersectionalPolygon(pointList));
	}
}
