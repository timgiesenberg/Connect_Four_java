/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectfour;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zeebo
 */
public class CFProperties {

    private java.util.Properties props;
    private static CFProperties instance;
    private final String filename;

    public CFProperties() {
	instance = this;
	props = new java.util.Properties();
	filename = "settings.properties";
	load();
    }

    public static CFProperties getInstance() {
	return instance;
    }

    public void setProperty(String key, String value) {
	props.setProperty(key, value);
    }

    public String getString(String key) {
	return props.getProperty(key);
    }

    public String getString(String key, String defaultValue) {
	return props.getProperty(key, defaultValue);
    }

    public void setProperty(String key, int value) {
	props.setProperty(key, String.valueOf(value));
    }

    public int getInteger(String key) {
	return new Integer(props.getProperty(key));
    }

    public int getInteger(String key, int defaultValue) {
	return new Integer(props.getProperty(key, String.valueOf(defaultValue)));
    }

    public void save() {
	try {
	    File f = new File(filename);
	    OutputStream os = new FileOutputStream(f);
	    props.storeToXML(os, "");
	} catch (IOException e) {
	}
    }

    private void load() {
	InputStream is = null;

	try {
	    File f = new File(filename);
	    is = new FileInputStream(f);
	} catch (FileNotFoundException e) {
	    is = null;
	}

	if (is == null) {
	    save();
	} else {
	    try {
		props.loadFromXML(is);
	    } catch (IOException ex) {
		Logger.getLogger(CFProperties.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}

    }

}
