package pl.com.seremak.TSPGALength.locationReader;

import io.micronaut.core.util.StringUtils;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.Array;
import pl.com.seremak.TSPGALength.model.Location;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

import static java.lang.Integer.parseInt;

public class LocationReader {

    public static Array<Location> readLocation(final String stringPath) {
        var path = Paths.get(stringPath);
        var lines = FileLineReader.readFile(path);
        return lines.map(LocationReader::mapLineToLocation)
                .filter(Objects::nonNull);
    }

    public static Array<Tuple2<Integer, Array<Integer>>> readResultRoute(final String stringPath) {
        var path = Paths.get(stringPath);
        return FileLineReader.readFile(path)
                .map(LocationReader::toStringArray)
                .map(LocationReader::toTuple2)
                .collect(Array.collector());
    }

    public static Tuple2<Integer, Array<Integer>> toTuple2(final Array<Integer> singleResultLocations) {
        return Tuple.of(singleResultLocations.last(), singleResultLocations.removeLast(__ -> true));
    }

    public static Array<Integer> toStringArray(final String str) {
        return Arrays.stream(str.split(" "))
                .map(Integer::parseInt)
                .collect(Array.collector());
    }

    private static Location mapLineToLocation(final String stringLine) {
        return Optional.of(stringLine)
                .filter(aStringLine -> stringLine.matches("\\s*\\d+\\s+\\d+\\s+\\d+"))
                .map(aStringLine -> stringLine.split("\\s+"))
                .map(Array::of)
                .map(stringArray -> stringArray.removeFirst(string -> string.equals(StringUtils.EMPTY_STRING)))
                .map(stringArray -> new Location(parseInt(stringArray.get(0)), parseInt(stringArray.get(1)), parseInt(stringArray.get(2))))
                .orElse(null);
    }
}

