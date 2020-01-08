package io.xlate.edi.stream;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class PropertySupport {

    protected final Set<String> supportedProperties;
    protected final Map<String, Object> properties;

    protected PropertySupport() {
        this.supportedProperties = new HashSet<>();
        this.properties = new HashMap<>();
    }

    /**
     * Query the set of properties that this factory supports.
     *
     * @param name
     *            - The name of the property (may not be null)
     * @return true if the property is supported and false otherwise
     */
    public boolean isPropertySupported(String name) {
        return supportedProperties.contains(name);
    }

    /**
     * Get the value of a feature/property from the underlying implementation
     *
     * @param name
     *            - The name of the property (may not be null)
     * @return The value of the property
     * @throws IllegalArgumentException
     *             if the property is not supported
     */
    public Object getProperty(String name) {
        if (!isPropertySupported(name)) {
            throw new IllegalArgumentException("Unsupported property: " + name);
        }

        return properties.get(name);
    }

    /**
     * Allows the user to set specific feature/property on the underlying
     * implementation. The underlying implementation is not required to support
     * every setting of every property in the specification and may use
     * IllegalArgumentException to signal that an unsupported property may not
     * be set with the specified value.
     *
     * @param name
     *            - The name of the property (may not be null)
     * @param value
     *            - The value of the property
     * @throws IllegalArgumentException
     *             if the property is not supported
     */
    public void setProperty(String name, Object value) {
        if (!isPropertySupported(name)) {
            throw new IllegalArgumentException("Unsupported property: " + name);
        }

        properties.put(name, value);
    }
}
