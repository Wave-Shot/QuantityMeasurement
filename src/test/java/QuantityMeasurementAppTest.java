import com.app.quantitymeasurement.dto.QuantityDTO;
import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;
import org.junit.jupiter.api.Test;
import com.app.quantitymeasurement.repository.IQuantityMeasurementRepository;
import com.app.quantitymeasurement.repository.QuantityMeasurementCacheRepository;
import com.app.quantitymeasurement.service.IQuantityMeasurementService;
import com.app.quantitymeasurement.service.QuantityMeasurementServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    @Test
    public void testService_CompareEquality() {

        IQuantityMeasurementService
                service =
                new
                        QuantityMeasurementServiceImpl(
                        QuantityMeasurementCacheRepository
                                .getInstance()
                );

        QuantityMeasurementEntity
                result =
                service.compare(
                        new QuantityDTO(
                                1.0,
                                "FEET",
                                "Length"
                        ),
                        new QuantityDTO(
                                12.0,
                                "INCHES",
                                "Length"
                        )
                );

        assertEquals(
                "true",
                result.getResult()
        );
    }

    @Test
    public void testService_Convert() {

        IQuantityMeasurementService
                service =
                new
                        QuantityMeasurementServiceImpl(
                        QuantityMeasurementCacheRepository
                                .getInstance()
                );

        QuantityMeasurementEntity
                result =
                service.convert(
                        new QuantityDTO(
                                100.0,
                                "CELSIUS",
                                "Temperature"
                        ),
                        "FAHRENHEIT"
                );

        assertTrue(
                result.getResult()
                        .contains(
                                "212.0"
                        )
        );
    }

    @Test
    public void testService_Addition() {

        IQuantityMeasurementService
                service =
                new
                        QuantityMeasurementServiceImpl(
                        QuantityMeasurementCacheRepository
                                .getInstance()
                );

        QuantityMeasurementEntity
                result =
                service.add(
                        new QuantityDTO(
                                1.0,
                                "KILOGRAM",
                                "Weight"
                        ),
                        new QuantityDTO(
                                1000.0,
                                "GRAM",
                                "Weight"
                        ),
                        "KILOGRAM"
                );

        assertTrue(
                result.getResult()
                        .contains(
                                "2.0"
                        )
        );
    }

    @Test
    public void testService_UnsupportedOperation() {

        IQuantityMeasurementService
                service =
                new
                        QuantityMeasurementServiceImpl(
                        QuantityMeasurementCacheRepository
                                .getInstance()
                );

        QuantityMeasurementEntity
                result =
                service.add(
                        new QuantityDTO(
                                100.0,
                                "CELSIUS",
                                "Temperature"
                        ),
                        new QuantityDTO(
                                50.0,
                                "CELSIUS",
                                "Temperature"
                        ),
                        "CELSIUS"
                );

        assertTrue(
                result.hasError()
        );
    }

    @Test
    public void testRepository_Save() {

        IQuantityMeasurementRepository
                repository =
                QuantityMeasurementCacheRepository
                        .getInstance();

        repository.save(
                new
                        QuantityMeasurementEntity(
                        "TEST",
                        "SUCCESS"
                )
        );

        assertFalse(
                repository
                        .getAllMeasurements()
                        .isEmpty()
        );
    }
}