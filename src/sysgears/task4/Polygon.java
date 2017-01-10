package sysgears.task4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * Draws none-intersection Polygon. Algorithm is following:
 * 	1. Find the most left point (x = Xmin), or choose one of the most left points (point A).
 * 	2. Find the most right point (x = Xmax), or choose one of the most right points (point B).
 * 	3. Divide all the other points in three areas: above AB line, under AB line and at AB line.
 * 	4. Sort all the points from "aboveAB" area by 'x' coordinate  increasingly.
 * 	5. Sort all the points from "underAB" area by 'x' coordinate  decreasingly.
 * 	6. Draw Polygon in the following order: A -> sorted points from i.4 -> sorted points from i.4 -> B.
 *
 */
public class Polygon {
	
	/**
	 * 
	 * Draws non-intersection polygon
	 * 
	 * @param pointList input list of points
	 * @return list of points in sorted order
	 * @throws Exception if amount of points is less then 3 or if it is impossible to draw none-intersection Polygon using this List of Points
	 * 
	 */
	public static List<Point> drawNonIntersectionalPolygon (List<Point> pointList) throws Exception {

		// To avoid dublicated points
		Set<Point> pointSet = new HashSet<>();
		pointSet.addAll(pointList);
		pointList.clear();
		pointList.addAll(pointSet);
		
		List<Point> polygonList = new ArrayList<Point>();
		List<Point> aboveABList = new ArrayList<Point>();
		List<Point> underABList = new ArrayList<Point>();
		List<Point> onABList = new ArrayList<Point>();
		List<Point> polygonInitList = new ArrayList<Point>();
		
		if (pointList.size() < 3) {
			throw new Exception("It is imposible to draw none-intersection Polygon - not enough points!");
		}
		divideIntoThreeParts(pointList, aboveABList, underABList, onABList);
		
		polygonInitList = drawNonIntersectionaInitlPolygon(aboveABList, underABList, onABList, pointList.get(pointList.size() - 1), pointList.get(0));

		try {
			polygonList.addAll(polygonInitList);
		} catch (NullPointerException e) {
			throw new Exception("It is impossible to draw none-intersection Polygon using this List of Points!");
		}
		polygonList.add(pointList.get(0));
		return polygonList;
	}
	
	/**
	 * 
	 * Divides all the points into three areas
	 * 
	 * @param pointList input list of points
	 * @param aboveABList list for points from 'aboveAB' area
	 * @param underABList list for points from 'underAB' area
	 * @param onABList list for points from 'onAB' area
	 * 
	 */
	private static void divideIntoThreeParts(List<Point> pointList, List<Point> aboveABList, List<Point> underABList, List<Point> onABList) {
		Collections.sort(pointList);

		Point xMin = pointList.get(0);
		Point xMax = pointList.get(pointList.size() - 1);
		
		onABList.add(pointList.get(0));
		onABList.add(pointList.get(pointList.size() - 1));

		for (int i = 1; i < pointList.size() - 1; i++) {
			if (getPointArea(pointList.get(i), xMin, xMax) > 0) {
				aboveABList.add(pointList.get(i));
			} else if (getPointArea(pointList.get(i), xMin, xMax) < 0) {
				underABList.add(pointList.get(i));
			} else {
				onABList.add(pointList.get(i));
			}
		}
	}
	
	/**
	 * 
	 * Service method. Prepares all the points for drawing the polygon
	 * 
	 * @param aboveABList list of point from 'aboveAB' area
	 * @param underABList list of point from 'underAB' area
	 * @param atABList list of point from 'atAB' area
	 * @param pointB the most right point
	 * @param pointA the most left point
	 * @return list of points
	 * 
	 */
	private static List<Point> drawNonIntersectionaInitlPolygon(List<Point> aboveABList, List<Point> underABList, List<Point> atABList, Point pointB, Point pointA) {
		List<Point> polygonInitList = new ArrayList<Point>();
		List<Point> onAboveABList = new ArrayList<Point>();
		
		if (aboveABList.isEmpty() && underABList.isEmpty()) {
			return null;
		}
		Collections.sort(atABList);
	
		if (!aboveABList.isEmpty() && !underABList.isEmpty()) {
			Collections.sort(underABList);
			polygonInitList.add(pointA);
			polygonInitList.addAll(underABList);
			polygonInitList.add(pointB);
			onAboveABList.addAll(aboveABList);
			
			for (int i = 1; i < atABList.size() - 1; i++) {
				onAboveABList.add(atABList.get(i));
			}
			Collections.sort(onAboveABList);
			Collections.reverse(onAboveABList);
			polygonInitList.addAll(onAboveABList);
		} else if (underABList.isEmpty()) {
			Collections.sort(atABList);
			polygonInitList.addAll(atABList);
			Collections.sort(aboveABList);
			Collections.reverse(aboveABList);
			polygonInitList.addAll(aboveABList);
		} else if (aboveABList.isEmpty()) {
			Collections.sort(underABList);
			polygonInitList.add(pointA);
			polygonInitList.addAll(underABList);
			polygonInitList.add(pointB);
			
			Collections.sort(atABList);
			Collections.reverse(atABList);

			for (int i = 1; i < atABList.size() - 1; i++) {
				polygonInitList.add(atABList.get(i));
			}
		}
		
		return polygonInitList;
	}
	
	/**
	 * 
	 * Checks the location of pointP relative to pointA-pointB line.
	 * 
	 * @param pointP point to be checked
	 * @param pointA the most left point
	 * @param pointB the most right point
	 * @return some number. If it is > 0, then pointP is above the pointA-pointB line, if it is < 0, then pointP is under the pointA-pointB line, if it is = 0, then pointP is at the pointA-pointB line
	 *
	 */
	private static double getPointArea(Point pointP, Point pointA, Point pointB) {
		return ((pointB.getX() - pointA.getX())*(pointP.getY() - pointA.getY()) - (pointB.getY() - pointA.getY())*(pointP.getX() - pointA.getX()));
	}
}
