package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileOperation {
    private Properties prop;

    public PropertyFileOperation(String filePath) {
        File file = new File(filePath);
        FileInputStream inStream;
        try {
            prop = new Properties();
            inStream = new FileInputStream(file);
            prop.load(inStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getValue(String key) {
        String value = prop.getProperty(key);
        if (value == null)
            throw new NullPointerException("No such property available in property file");
        return value;
    }

}
