package d.collection;

import java.util.Properties;
import java.util.Set;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class PropertiesSample {
    public static void main(String[] args) {
        PropertiesSample sample = new PropertiesSample();
        // sample.checkProperties();
        // sample.saveAndLoadProperties();
        sample.saveAndLoadPropertiesXML();
    }

    public void checkProperties() {
        Properties prop = System.getProperties();
        Set<Object> keySet = prop.keySet();
        for (Object tempObject:keySet) {
            System.out.println(tempObject+" = "+prop.get(tempObject));
        }
    }

    public void saveAndLoadProperties() {
        try {
            String fileName = "test.properties";
            File propertiesFile = new File(fileName);
            FileOutputStream fos = new FileOutputStream(propertiesFile);

            Properties prop = new Properties();
            prop.setProperty("Writer", "Lora");
            prop.setProperty("WriterHome", "http://www.GodOfJava.com");
            prop.store(fos, "Basic Properties file.");
            fos.close();

            FileInputStream fis = new FileInputStream(propertiesFile);
            Properties propLoaded = new Properties();
            propLoaded.load(fis);
            fis.close();
            System.out.println(propLoaded);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveAndLoadPropertiesXML() {
        try {
            String fileName = "test.xml";
            File propertiesFile = new File(fileName);
            FileOutputStream fos = new FileOutputStream(propertiesFile);

            Properties prop = new Properties();
            prop.setProperty("Writer", "Lora");
            prop.setProperty("WriterHome", "http://www.GodOfJava.com");
            prop.storeToXML(fos, "Basic XML Property file.");
            fos.close();

            FileInputStream fis = new FileInputStream(propertiesFile);
            Properties propLoaded = new Properties();
            propLoaded.loadFromXML(fis);
            fis.close();
            System.out.println(propLoaded);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}