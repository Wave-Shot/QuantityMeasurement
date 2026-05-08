public class
QuantityMeasurementController {

    private final
    IQuantityMeasurementService
            service;

    public
    QuantityMeasurementController(
            IQuantityMeasurementService
                    service
    ) {

        this.service = service;
    }

    public void performComparison(
            QuantityDTO q1,
            QuantityDTO q2
    ) {

        System.out.println(
                service.compare(q1, q2)
        );
    }

    public void performConversion(
            QuantityDTO source,
            String target
    ) {

        System.out.println(
                service.convert(
                        source,
                        target
                )
        );
    }

    public void performAddition(
            QuantityDTO q1,
            QuantityDTO q2,
            String target
    ) {

        System.out.println(
                service.add(
                        q1,
                        q2,
                        target
                )
        );
    }

    public void performSubtraction(
            QuantityDTO q1,
            QuantityDTO q2,
            String target
    ) {

        System.out.println(
                service.subtract(
                        q1,
                        q2,
                        target
                )
        );
    }

    public void performDivision(
            QuantityDTO q1,
            QuantityDTO q2
    ) {

        System.out.println(
                service.divide(
                        q1,
                        q2
                )
        );
    }
}