package com.qinxiaoyu.lib.graphics.swing;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Screen {


	
	/*@author:������
	 *@exception: ���������Ļ�Ĵ�С
	 *@return: ����������Ļ�Ĵ�С  getScreenResolution.width �� getScreenResolution.height
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
	 * @author:������
	 * @exception: ������汻ռ�õĴ�С�����������ȡ����Ļ��ռ�÷�Χ������������scrInsets�����left��right��top��bottom��ֵ�ֱ������Ļ�ı���ռ�õĴ�С��
	 * */
	public static Insets getScrBeOccupied()
	{
		Insets scrInsets=Toolkit.getDefaultToolkit().getScreenInsets(GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration());
		return scrInsets;
	}
	/*
	 * @author:������
	 * @exception�����ô���ȫ����ʾ
	 * */
	public static void setAppFullScreen(JFrame frame)
	{
		Insets scrInsets = getScrBeOccupied();
		Dimension scrSize = getScrResolution();
		frame.setBounds(scrInsets.left,scrInsets.top,scrSize.width-scrInsets.left-scrInsets.right,scrSize.height-scrInsets.top-scrInsets.bottom);  
	}
	/*
	 * @author:������
	 * @exception:���ô�������Ļ��������
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
