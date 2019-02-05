package net.oaster2000.sin.util;

public class MathsUtils {

	public static float lerp(float point1, float point2, float alpha)
	{
	    return point1 + alpha * (point2 - point1);
	}
	
}
