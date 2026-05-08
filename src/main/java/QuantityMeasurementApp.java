public class QuantityMeasurementApp {

    public static void main(String[] args) {

        Quantity<TemperatureUnit> celsius =
                new Quantity<>(
                        0.0,
                        TemperatureUnit.CELSIUS
                );

        Quantity<TemperatureUnit> fahrenheit =
                new Quantity<>(
                        32.0,
                        TemperatureUnit.FAHRENHEIT
                );

        Quantity<TemperatureUnit> kelvin =
                new Quantity<>(
                        273.15,
                        TemperatureUnit.KELVIN
                );

        System.out.println(
                celsius.equals(fahrenheit)
        );

        System.out.println(
                celsius.equals(kelvin)
        );

        System.out.println(
                new Quantity<>(
                        100.0,
                        TemperatureUnit.CELSIUS
                ).convertTo(
                        TemperatureUnit.FAHRENHEIT
                )
        );

        System.out.println(
                new Quantity<>(
                        32.0,
                        TemperatureUnit.FAHRENHEIT
                ).convertTo(
                        TemperatureUnit.CELSIUS
                )
        );

        System.out.println(
                new Quantity<>(
                        0.0,
                        TemperatureUnit.CELSIUS
                ).convertTo(
                        TemperatureUnit.KELVIN
                )
        );

        try {

            System.out.println(
                    celsius.add(
                            new Quantity<>(
                                    10.0,
                                    TemperatureUnit.CELSIUS
                            )
                    )
            );

        } catch (Exception e) {

            System.out.println(
                    e.getMessage()
            );
        }

        try {

            System.out.println(
                    celsius.divide(
                            new Quantity<>(
                                    2.0,
                                    TemperatureUnit.CELSIUS
                            )
                    )
            );

        } catch (Exception e) {

            System.out.println(
                    e.getMessage()
            );
        }

        Quantity<LengthUnit> feet =
                new Quantity<>(
                        10.0,
                        LengthUnit.FEET
                );

        System.out.println(
                celsius.equals(feet)
        );
    }
}