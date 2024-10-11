package drivers;

import utils.TestUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * [PropertyManager] is a class that manages the properties of the project
 * It loads the properties from the configs.properties file
 */
public class PropertyManager {

    /**
     * [getProps] gets the properties from the configs.properties file
     *
     * @return an instance of [Properties] that contains the properties
     * @throws IOException
     */
    public static Properties getProps(String fileName) throws IOException {
        final Properties props = new Properties();

        InputStream is = null;
        if (props.isEmpty()) {
            try {
                TestUtils.log().info("[CONFIG] Loading config properties");
                is = PropertyManager.class.getClassLoader().getResourceAsStream(fileName);
                props.load(is);
            } catch (IOException e) {
                e.printStackTrace();
                TestUtils.log().fatal("Failed to load config properties. ABORT!!" + e);
                throw e;
            } finally {
                if (is != null) {
                    is.close();
                }
            }
        }
        return props;
    }
}
