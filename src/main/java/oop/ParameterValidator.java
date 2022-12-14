package oop;

import oop.ConfigParameters.*;
import oop.MapInterface.MapBorders.GlobeMap;
import oop.MapInterface.MapBorders.IMap;
import oop.MapInterface.MapBorders.PortalMap;
import oop.MapInterface.MapConstants;
import oop.MapInterface.MapObjects.AnimalConstants;
import oop.MapInterface.PlantsOnMap.IPlant;
import oop.MapInterface.PlantsOnMap.PlantsEquator;
import oop.MapInterface.PlantsOnMap.ToxicCorpse;
import oop.gui.SimulationWindow;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ParameterValidator {
    World world;
    Map<WorldParamType, Object> worldParams;


    /**
     * attempts to create simulation instance
     * if not successful throws exception with error message
     * @param configFileName                name of config file in config directory
     * @throws FileNotFoundException        if file was not found
     * @throws IllegalArgumentException     if given arguments are not fitting the standard
     */
    public ParameterValidator(String configFileName, Integer epochCount, Double epochDuration, String csvFilePath) throws FileNotFoundException, IllegalArgumentException{
        worldParams = loadParamWorld(configFileName);
        checkConsistency();
        SimulationWindow simulationWindow = new SimulationWindow();
        SimulationEngine simulationEngine = createSimulationEngine(worldParams, epochCount, epochDuration, simulationWindow, csvFilePath);
        simulationWindow.addSimulationEngine(simulationEngine);
        (new Thread(simulationEngine)).start();
    }


    public SimulationEngine createSimulationEngine(Map<WorldParamType, Object> worldParams, int epochCount,
                                       Double epochDuration, SimulationWindow simulationWindow, String csvFilePath) throws FileNotFoundException {
        int numberOfAnimals = (Integer) worldParams.get(WorldParamType.INIT_ANIMAL_COUNT);
        MapConstants mapConstants = new MapConstants(
                (Integer) worldParams.get(WorldParamType.MAP_WIDTH),
                (Integer) worldParams.get(WorldParamType.MAP_HEIGHT),
                (Integer)worldParams.get(WorldParamType.INIT_PLANT_COUNT),
                (Integer)worldParams.get(WorldParamType.PLANT_ENERGY),
                (Integer)worldParams.get(WorldParamType.PLANT_GROWTH_RATE)
        );

        IMap map = switch ((MapVariant)worldParams.get(WorldParamType.MAP_VARIANT)){
            case GLOBE -> new GlobeMap(mapConstants);
            case PORTAL -> new PortalMap(mapConstants);
        };

        IPlant plantMap =  switch ((PlantVariant)worldParams.get(WorldParamType.PLANT_VARIANT)){
            case TOXIC_CORPSE -> new ToxicCorpse(mapConstants, map.getDeadAnimalsHolder());
            case FERTILE_EQUATOR -> new PlantsEquator(mapConstants);
        };

        AnimalVariant animalVariant = (AnimalVariant)worldParams.get(WorldParamType.ANIMAL_VARIANT);
        MutationVariant mutationVariant = (MutationVariant) worldParams.get(WorldParamType.MUTATION_VARIANT);
        AnimalConstants animalConstants = new AnimalConstants(
                (Integer)worldParams.get(WorldParamType.PLANT_ENERGY),
                (Integer)worldParams.get(WorldParamType.REPRODUCTION_ENERGY_THRESHOLD),
                (Integer)worldParams.get(WorldParamType.REPRODUCTION_COST),
                (Integer)worldParams.get(WorldParamType.MIN_MUTATION_COUNT),
                (Integer)worldParams.get(WorldParamType.MAX_MUTATION_COUNT),
                (Integer)worldParams.get(WorldParamType.ANIMAL_GENOME_LENGTH),
                (Integer)worldParams.get(WorldParamType.INIT_ANIMAL_ENERGY)
        );

        if (csvFilePath == null){
            return new SimulationEngine(numberOfAnimals, map, plantMap, animalVariant,
                    mutationVariant, animalConstants, simulationWindow, epochCount, epochDuration);
        }
        return new SimulationEngine(numberOfAnimals, map, plantMap, animalVariant,
                mutationVariant, animalConstants, simulationWindow, epochCount, epochDuration, csvFilePath);

    }


    /**
     * Loads data from configuration file
     * @param configFileName    file name of configuration file in default config files directory
     * @return                  values of parameters
     * @throws FileNotFoundException        if file was not found
     * @throws IllegalArgumentException     if given file has inappropriate structure
     */
    private Map<WorldParamType, Object> loadParamWorld(String configFileName) throws FileNotFoundException, IllegalArgumentException{
        List<String> fileContent = getFileContent(Config.CONFIG_DIR_PATH  + '/' + configFileName);

        int len = Config.CONFIG_FILE_STRUCTURE.length;
        if (fileContent.size() < len){
            throw new IllegalArgumentException("Config file has to little arguments");
        }
        if (fileContent.size() > len){
            throw new IllegalArgumentException("Config file has much little arguments");
        }

        Map<WorldParamType, Object> paramValues = new HashMap<>();
        for(int i = 0; i < len; i++){
            String[] parts = fileContent.get(i).split(" ");
            WorldParamType type = Config.CONFIG_FILE_STRUCTURE[i];
            if (parts.length != 2){
                throw new IllegalArgumentException("Invalid input for " + type);
            }
            if (!Objects.equals(parts[0], type.getKey())){
                throw new IllegalArgumentException("Invalid key for " + type);
            }
            int value;
            try{
                value = Integer.parseInt(parts[1]);
            }
            catch (NumberFormatException e){
                throw new IllegalArgumentException("Invalid input for " + type);
            }
            Object parsedValue = type.parse(value);
            paramValues.put(type, parsedValue);
        }
        return paramValues;
    }


    /**
     * retrieve file content
     * @param filePath  path to file for read
     * @return          ArrayList where index relate to one line in file
     * @throws FileNotFoundException    if file was not found
     */
    private List<String> getFileContent(String filePath) throws FileNotFoundException{
        List<String> lines = new ArrayList<>();
        File myObj = new File(filePath);
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            lines.add(data);
        }
        myReader.close();
        return lines;
    }


    /**
     * attempts to create new simulation instance
     * if not successful returns error message
     * @param ConfigFileName  new simulation from filename
     * @return          error message or "" otherwise
     */
    public static String startNewSimulation(String ConfigFileName, Integer epochCount, Double epochDuration){
        try{
            new ParameterValidator(ConfigFileName, epochCount, epochDuration, null);
            return "";
        }
        catch (IllegalArgumentException | FileNotFoundException e){
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }


    /**
     * attempts to create new simulation instance
     * if not successful returns error message
     * @param ConfigFileName    new simulation from filename
     * @param csvFilePath       csv file for saving statistics
     * @return          error message or "" otherwise
     */
    public static String startNewSimulation(
            String ConfigFileName, String csvFilePath, Integer epochCount, Double epochDuration){
        try{
            new ParameterValidator(ConfigFileName, epochCount, epochDuration,  csvFilePath);
            return "";
        }
        catch (IllegalArgumentException | FileNotFoundException e){
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }


    /**
     * checks if data inside worldParams Map is consistent between one another
     * @throws IllegalArgumentException     if parameters are not consistent
     */
    private void checkConsistency() throws IllegalArgumentException{
        WorldParamType[] mustBePositiveParams = {
                WorldParamType.MAP_HEIGHT,
                WorldParamType.MAP_WIDTH,
                WorldParamType.INIT_PLANT_COUNT,
                WorldParamType.PLANT_ENERGY,
                WorldParamType.PLANT_GROWTH_RATE,
                WorldParamType.INIT_ANIMAL_COUNT,
                WorldParamType.INIT_ANIMAL_ENERGY,
                WorldParamType.REPRODUCTION_ENERGY_THRESHOLD,
                WorldParamType.REPRODUCTION_COST,
                WorldParamType.MIN_MUTATION_COUNT,
                WorldParamType.MAX_MUTATION_COUNT,
                WorldParamType.ANIMAL_GENOME_LENGTH,
        };

        for (WorldParamType param : mustBePositiveParams){
            mustBePositive(param);
        }

        Integer mapHeight = (Integer) getParamValue(WorldParamType.MAP_HEIGHT);
        Integer mapWidth = (Integer) getParamValue(WorldParamType.MAP_WIDTH);
        Integer mapCellCountVal = mapHeight * mapWidth;
        String mapCellCountDesc = WorldParamType.MAP_WIDTH + " * " + WorldParamType.MAP_HEIGHT;

//        INIT_PLANT_COUNT < MAP_HEIGHT * MAP_WIDTH
        mustBeLower(WorldParamType.INIT_PLANT_COUNT, mapCellCountVal, mapCellCountDesc);

//        PLANT_GROWTH_RATE < MAP_HEIGHT * MAP_WIDTH
        mustBeLower(WorldParamType.PLANT_GROWTH_RATE, mapCellCountVal, mapCellCountDesc);

//        MIN_MUTATION_COUNT < MAX_MUTATION_COUNT + 1
        mustBeLower(
                WorldParamType.MIN_MUTATION_COUNT,
                (Integer)getParamValue(WorldParamType.MAX_MUTATION_COUNT) + 1,
                "" + WorldParamType.MAX_MUTATION_COUNT
        );

//        MAX_MUTATION_COUNT < ANIMAL_GENOME_LENGTH
        mustBeLower(
                WorldParamType.MIN_MUTATION_COUNT,
                (Integer) getParamValue(WorldParamType.ANIMAL_GENOME_LENGTH),
                "" + WorldParamType.ANIMAL_GENOME_LENGTH);
    }


    /**
     * retrieves value associated with paramType from paramValues Map
     * @param paramType     type of parameter
     * @return              parameter value
     * @throws IllegalArgumentException     if parameter is not present in paramValues or its value is null
     */
    private Object getParamValue(WorldParamType paramType) throws IllegalArgumentException {
        Object val = worldParams.get(paramType);
        if (val == null)
            throw new IllegalArgumentException("no value for " + paramType + "provided");
        return val;
    }


    /**
     * makes sure that value of given parameter type is positive
     * @param paramType          parameter type
     * @throws IllegalArgumentException if value of parameter type is not positive
     */
    private void mustBePositive(WorldParamType paramType) throws IllegalArgumentException{
        Integer val = (Integer) getParamValue(paramType);
        if(val < 0)
            throw new IllegalArgumentException(paramType + "must be positive");
    }


    /**
     * makes sure that value of given parameter type lower than given limit
     * @param paramType     parameter type
     * @param limit         upper limit
     * @param limitDesc     description of a limit (used in thrown exception message)
     * @throws IllegalArgumentException if condition is not fulfilled
     */
    private void mustBeLower(WorldParamType paramType, Integer limit, String limitDesc) throws IllegalArgumentException{
        Integer val = (Integer) getParamValue(paramType);
        if(limit < val)
            throw new IllegalArgumentException(paramType + "cannot be greater than" + limitDesc);
    }
}
