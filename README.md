# TSPGA Length
## how to run?
to check if your calculated route length is correct run the app with params:

`-s input/bier127.tsp -f TSPGA_result_input_bier127.tsp_211212_213436.txt`

where `input/bier127.tsp` is source TSP file and `TSPGA_result_input_bier127.tsp_211212_213436.txt`
is a result file in a format: 

`99 92 89 125 104 86 88 70 71 68 74 19 22 108 6 15 41 36 207408` 
where last number is the calculated route length.

## how to install?
just simply run `gradle build`. Java 15 is required.

## can I run this from jar file?
You can use also prebuilt jar files located in`/binary` directory and run it with command:

`java -jar binary/TPSGALength-0.1-all.jar -s input/bier127.tsp -f TSPGA_result_input_bier127.tsp_211212_213436.txt`
