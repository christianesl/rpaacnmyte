package com.accenture.test.common;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JFrame;

public class FilterOption {
	
	public String setLocation() {
	    String[] choices = { "Guadalajara", "Monterrey", "Saltillo", "Queretaro", "Ciudad de México" };
	    String input = (String) JOptionPane.showInputDialog(null, "Choose a location ...",
	        "Location", JOptionPane.QUESTION_MESSAGE, null,
	        choices, // Array of choices
	        choices[1]); // Initial choice
	    System.out.println("Location: "+input);
	    
	    return(input);
	  }
	
	public int setResourceAmount() {
	    String[] choices = { "10", "20", "30", "40", "50", "100", "200", "300", "400", "500", "600", "700", "800", "900", "1000"};
	    String input = (String) JOptionPane.showInputDialog(null, "Choose the amount of resources  ...",
	        "Amount of Resources", JOptionPane.QUESTION_MESSAGE, null,
	        choices, // Array of choices
	        choices[1]); // Initial choice
	    System.out.println("Amount of Resources: "+input);
	    
	    int result = Integer.parseInt(input);	
	    
	    return(result);
	  }

    public String setSkill() {
    	String skill = "";
    	JTextField tf = new JTextField();
    	int okCxl = JOptionPane.showConfirmDialog(null, tf, "Skill needed", JOptionPane.OK_OPTION);
    	if (okCxl == JOptionPane.OK_OPTION) {
    		skill = new String(tf.getText());
    	}
    	skill = skill.toLowerCase();
    	System.out.println("Skill: "+skill);
        return(skill);
        
    }
    
    

}