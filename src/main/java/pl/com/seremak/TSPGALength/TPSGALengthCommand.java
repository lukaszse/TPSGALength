package pl.com.seremak.TSPGALength;

import io.micronaut.configuration.picocli.PicocliRunner;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import pl.com.seremak.TSPGALength.RouteLengtCalculator.RouteCalculationService;

@Command(name = "TPSGALength", description = "...",
        mixinStandardHelpOptions = true)
public class TPSGALengthCommand implements Runnable {

    @Inject
    RouteCalculationService routeCalculationService;

    @Option(names = {"-v", "--verbose"}, description = "...")
    boolean verbose;

    @Option(names = {"-f", "--inputFile"}, description = "path of input file", required = true)
    String inputFilePath;

    @Option(names = {"-s", "--sourceFile"}, description = "path of source wile which stores localization data", required = true)
    String sourceFilePath;

    public static void main(String[] args) throws Exception {
        PicocliRunner.run(TPSGALengthCommand.class, args);
    }

    public void run() {
        routeCalculationService.run(inputFilePath, sourceFilePath);
    }
}
