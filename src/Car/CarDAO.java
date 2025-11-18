package Car;

import java.math.BigDecimal;

/**
 *      Data Access Object (DAO) class for managing Car objects.
 *      This implementation stores and manages cars using arrays.
 */

public class CarDAO {

    private static final Car[] carDAO;

    static {

        carDAO = new Car[]{

                new Car("123_1", new BigDecimal("89.00"), Brand.BMW, FuelType.ELECTRIC),
                new Car("123_2", new BigDecimal("79.00"), Brand.BMW, FuelType.GASOLINE),
                new Car("123_3", new BigDecimal("69.00"), Brand.VOLKSWAGEN, FuelType.ELECTRIC),
                new Car("123_4", new BigDecimal("49.00"), Brand.HONDA, FuelType.ELECTRIC),
                new Car("123_5", new BigDecimal("59.00"), Brand.HONDA,  FuelType.GASOLINE),

        };
    }

    public static Car[] getCarsFromDAO() {
        return carDAO;
    }

}
