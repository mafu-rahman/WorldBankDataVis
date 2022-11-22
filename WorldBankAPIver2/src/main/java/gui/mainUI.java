package gui;

import dataFetchers.DATAPARSER;
import java.awt.BorderLayout;
import java.awt.Color;

import org.jfree.data.category.DefaultCategoryDataset;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import dataFetchers.Fetcher;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.plaf.basic.BasicOptionPaneUI.ButtonActionListener;

import login.loginUI;

public class mainUI extends JFrame implements IuiPanel{

	public static void main(String[] args) {
		new mainUI();	
	}

	/*
	 * Class Attributes
	 */
	private static JFrame frame;
	private static JPanel panel;
	
	private topPanel topPanel;
	private bottomPanel bottomPanel;
	
	
	
	private ArrayList<JsonArray> retrievedJsonArray;
	private ArrayList<Visualization> visual;
	
	private JPanel west;
	private int index;

	/**
	 * Constructor Method
	 */
	public mainUI() {
		frame = new JFrame();
		panel = new JPanel();
		panel.setLayout(null);
		frame.setTitle("Country Statistics");
		
		this.topPanel = new topPanel(frame);
		this.bottomPanel = new bottomPanel(frame);
		
		index = -1;
		
		frame.setSize(1200, 800);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	public void setupPanel() {
		// TODO Auto-generated method stub
		
	}
	
}
