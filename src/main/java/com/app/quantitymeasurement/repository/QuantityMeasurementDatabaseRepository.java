package com.app.quantitymeasurement.repository;

import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.app.quantitymeasurement.exception.DatabaseException;
import com.app.quantitymeasurement.util.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class
QuantityMeasurementDatabaseRepository
        implements
        IQuantityMeasurementRepository {

    private final ConnectionPool pool;

    public
    QuantityMeasurementDatabaseRepository() {

        try {

            pool =
                    ConnectionPool.getInstance();

        } catch (SQLException e) {

            throw new DatabaseException(
                    "Pool initialization failed",
                    e
            );
        }
    }

    @Override
    public void save(
            QuantityMeasurementEntity entity
    ) {

        String sql =
                """
                INSERT INTO quantity_measurements
                (operation,result,error,error_message)
                VALUES(?,?,?,?)
                """;

        Connection connection =
                pool.getConnection();

        try (
                PreparedStatement statement =
                        connection.prepareStatement(
                                sql
                        )
        ) {

            statement.setString(
                    1,
                    entity.getOperation()
            );

            statement.setString(
                    2,
                    entity.getResult()
            );

            statement.setBoolean(
                    3,
                    entity.hasError()
            );

            statement.setString(
                    4,
                    entity.getErrorMessage()
            );

            statement.executeUpdate();

        } catch (SQLException e) {

            throw new DatabaseException(
                    "Save failed",
                    e
            );

        } finally {

            pool.releaseConnection(
                    connection
            );
        }
    }

    @Override
    public List<QuantityMeasurementEntity>
    getAllMeasurements() {

        List<QuantityMeasurementEntity>
                results =
                new ArrayList<>();

        Connection connection =
                pool.getConnection();

        try (
                Statement statement =
                        connection.createStatement();

                ResultSet resultSet =
                        statement.executeQuery(
                                """
                                SELECT * FROM
                                quantity_measurements
                                """
                        )
        ) {

            while (resultSet.next()) {

                results.add(
                        new
                                QuantityMeasurementEntity(
                                resultSet.getString(
                                        "operation"
                                ),
                                resultSet.getString(
                                        "result"
                                ),
                                resultSet.getBoolean(
                                        "error"
                                ),
                                resultSet.getString(
                                        "error_message"
                                )
                        )
                );
            }

        } catch (SQLException e) {

            throw new DatabaseException(
                    "Retrieve failed",
                    e
            );

        } finally {

            pool.releaseConnection(
                    connection
            );
        }

        return results;
    }

    @Override
    public void deleteAllMeasurements() {

        Connection connection =
                pool.getConnection();

        try (
                Statement statement =
                        connection.createStatement()
        ) {

            statement.executeUpdate(
                    """
                    DELETE FROM
                    quantity_measurements
                    """
            );

        } catch (SQLException e) {

            throw new DatabaseException(
                    "Delete failed",
                    e
            );

        } finally {

            pool.releaseConnection(
                    connection
            );
        }
    }

    @Override
    public int getTotalCount() {

        Connection connection =
                pool.getConnection();

        try (
                Statement statement =
                        connection.createStatement();

                ResultSet resultSet =
                        statement.executeQuery(
                                """
                                SELECT COUNT(*) AS total
                                FROM quantity_measurements
                                """
                        )
        ) {

            resultSet.next();

            return resultSet.getInt(
                    "total"
            );

        } catch (SQLException e) {

            throw new DatabaseException(
                    "Count failed",
                    e
            );

        } finally {

            pool.releaseConnection(
                    connection
            );
        }
    }
}