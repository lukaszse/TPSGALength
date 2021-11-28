package pl.com.seremak.TSPGALength.RouteLengtCalculator;


import io.vavr.collection.Array;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import pl.com.seremak.TSPGALength.locationReader.LocationReader;
import pl.com.seremak.TSPGALength.model.Location;
import pl.com.seremak.TSPGALength.model.Route;

@Slf4j
@Singleton
public class RouteCalculationService {

    public static final String SIZE_NOT_EQUALS_ERROR_MESSAGE = "Length of result route (%d) and source route (%d) is not equal";
    public static final String CANNOT_FIND_LOCATION_ERROR_MESSAGE = "Cannot find location with given number";

    public void run(final String inputFilePath, final String sourceFilePath) {
        var sourceLocations = LocationReader.readLocation(sourceFilePath);
        var resultTuple = LocationReader.readResultRoute(inputFilePath);
        var resultLength = resultTuple._1;
        var resultRoute = resultTuple._2;
        var calculatedRouteLength = buildRouteFromResult(resultRoute, sourceLocations);
        log.info("Read route length = {}", resultLength);
        log.info("Calculated route length = {}", calculatedRouteLength.getRouteLength());

        if(resultLength.equals(calculatedRouteLength.getRouteLength())) {
            log.info("RESULT CORRECT!");
        } else {
            log.error("RESULT INCORRECT!");
        }
    }

    private Route buildRouteFromResult(final Array<Integer> resultRoute, final Array<Location> sourceLocations) {
        if(resultRoute.length() != sourceLocations.length()) {
            log.error(SIZE_NOT_EQUALS_ERROR_MESSAGE.formatted(resultRoute.length(), sourceLocations.length()));
            throw new IllegalArgumentException(SIZE_NOT_EQUALS_ERROR_MESSAGE);
        }

        var resultLocations = resultRoute
                .map(locationNumber -> sourceLocations.find(location -> location.getLocation() == locationNumber))
                .map(locations -> locations.getOrElseThrow(() -> new IllegalArgumentException(CANNOT_FIND_LOCATION_ERROR_MESSAGE)))
                .collect(Array.collector());
        return Route.of(resultLocations);
    }
}
