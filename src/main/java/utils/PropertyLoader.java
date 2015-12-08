package utils;

import java.io.IOException;
import java.util.Properties;

public class PropertyLoader {

    private static final String TEST_PROP_FILE = "/test.properties";
    private static final String NEW_LEAD_PROP_FILE = "/newlead.properties";

	public static String loadProperty(String name) {
		Properties props = new Properties();
		try {
            props.load(PropertyLoader.class.getResourceAsStream(TEST_PROP_FILE));
            props.load(PropertyLoader.class.getResourceAsStream(NEW_LEAD_PROP_FILE));
        } catch (IOException e) {
			e.printStackTrace();
		}

		String value = "";

		if (name != null) {
			value = props.getProperty(name);
		}
		return value;
	}
}