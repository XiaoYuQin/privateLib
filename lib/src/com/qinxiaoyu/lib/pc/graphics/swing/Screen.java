package com.qinxiaoyu.lib.graphics.swing;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Screen {


	
	/*@author:秦晓宇
	 *@exception: 获得整个屏幕的大小
	 *@return: 返回整个屏幕的大小  getScreenResolution.width 和 getScreenResolution.height
	 * */
	public static Dimension getScrResolution()
	{
		Dimension scrSize=Toolkit.getDefaultToolkit().getScreenSize();  
		return scrSize;
	}
	
	public static int getScrResolutionWidth()
	{
		Dimension scrSize=Toolkit.getDefaultToolkit().getScreenSize();  
		return scrSize.width;	
	}
	
	public static int getScrResolutionHeight()
	{
		Dimension scrSize=Toolkit.getDefaultToolkit().getScreenSize();  
		return scrSize.height;	
	}
	
	/*
	 * @author:秦晓宇
	 * @exception: 获得桌面被占用的大小，此语句用于取得屏幕已占用范围，如任务栏。scrInsets对象的left、right、top、bottom的值分别就是屏幕四边已占用的大小。
	 * */
	public static Insets getScrBeOccupied()
	{
		Insets scrInsets=Toolkit.getDefaultToolkit().getScreenInsets(GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration());
		return scrInsets;
	}
	/*
	 * @author:秦晓宇
	 * @exception：设置窗口全屏显示
	 * */
	public static void setAppFullScreen(JFrame frame)
	{
		Insets scrInsets = getScrBeOccupied();
		Dimension scrSize = getScrResolution();
		frame.setBounds(scrInsets.left,scrInsets.top,scrSize.width-scrInsets.left-scrInsets.right,scrSize.height-scrInsets.top-scrInsets.bottom);  
	}
	/*
	 * @author:秦晓宇
	 * @exception:设置窗口在屏幕的正中央
	 * */
	public static void setAppInTheMiddleOfScreen(JFrame frame, int width, int height) throws ScreenException
	{
		Insets scrInsets = getScrBeOccupied();
		Dimension scrSize = getScrResolution();
		
		if((width > scrSize.width-scrInsets.left-scrInsets.right) || (height>scrSize.height-scrInsets.top-scrInsets.bottom))
			throw new ScreenException();
		
		int x =  (scrSize.width-scrInsets.left-scrInsets.right-width)/2;
		int y =  (scrSize.height-scrInsets.top-scrInsets.bottom-height)/2;
		
		frame.setBounds(x, y, width, height);
	}
	
	
	public static class ScreenException extends Exception
	{
		private static final long serialVersionUID = 1L;

		public ScreenException()
		{
			System.out.println("input witdh or height biger than screen");
		}
	}
}
