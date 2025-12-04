package car.dao;

import car.Brand;
import car.Car;
import car.FuelType;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 *      ArrayCarDAO is a Data Access Object (DAO)
 *      class for managing Car objects,
 *      it implements the CarDAO contract.
 *
 *      This implementation stores and manages cars using arrays.
 */

public class ArrayCarDAO implements CarDAO {

    private final Car[] carDAO;

    public ArrayCarDAO() {
        this.carDAO = new Car[]{

                new Car("123_1", new BigDecimal("89.00"), Brand.BMW, FuelType.ELECTRIC),
                new Car("123_2", new BigDecimal("79.00"), Brand.BMW, FuelType.GASOLINE),
                new Car("123_3", new BigDecimal("69.00"), Brand.VOLKSWAGEN, FuelType.ELECTRIC),
                new Car("123_4", new BigDecimal("49.00"), Brand.HONDA, FuelType.ELECTRIC),
                new Car("123_5", new BigDecimal("59.00"), Brand.HONDA, FuelType.GASOLINE),

        };
    }

    @Override
    public Car[] getCars() {
        // Copy of Car objects returned from carDAO
        return Arrays.copyOf(this.carDAO, this.carDAO.length);
    }

}
