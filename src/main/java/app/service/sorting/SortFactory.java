package app.service.sorting;

import java.util.Properties;
import app.domain.shared.Constants;
import app.service.PropertiesUtils;

/**
 * Sort strategy factory.
 * 
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
 */
public class SortFactory {
    private SortFactory() {}

    public static ISortStrategy getSortStrategy() {
        Properties props = PropertiesUtils.getProperties();

        String sortStrategyName = props.getProperty(Constants.PARAMS_SORTING_ALGORITHM);

        try {
            Class<?> sortStrategyClass = Class.forName(sortStrategyName);

            ISortStrategy sortStrategy = (ISortStrategy) sortStrategyClass.getDeclaredConstructor().newInstance();

            return sortStrategy;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
