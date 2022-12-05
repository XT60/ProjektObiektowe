//package agh.ics.oop.gui;
//
//import agh.ics.oop.*;
//import javafx.application.Application;
//
//import javafx.collections.ObservableList;
//import javafx.geometry.HPos;
//import javafx.geometry.Pos;
//import javafx.scene.Node;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.TextField;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.*;
//import javafx.scene.paint.Color;
//import javafx.scene.text.Font;
//import javafx.scene.text.FontPosture;
//import javafx.scene.text.FontWeight;
//import javafx.stage.Stage;
//
//import java.awt.*;
//import java.util.*;
//import java.util.List;
//
//import static java.lang.System.out;
//
//
//public class App extends Application implements IFrameChangeObserver{
//    ThreadedSimulationEngine engine;
//    GrassField map;
//    private int columnWidth = 60;
//    private int rowWidth = 60;
//    private Vector2d lowerLeft;
//    int mapHeight;
//    int mapWidth;
//    private Map <IMapElement, GuiElementBox> boxMap;
//    private GridPane gridPane;
//    private Stage stage;
//    private TextField textField;
//    private Label errorMessage;
//
//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        stage = primaryStage;
//        Button button = new Button("Start");
//        textField = new TextField();
//        button.setMinSize(100, 40);
//        textField.setMinSize(400, 50);
//        errorMessage = new Label();
//        errorMessage.setTextFill(Color.RED);
//        errorMessage.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 16));
//
//        button.setOnAction(actionEvent -> {
//            String moveString = textField.getText();
//                try{
//                    List<String> moves = Arrays.asList(moveString.split(" "));
//                    MoveDirection[] directions = new OptionsParser().parse(listToArray(moves));
//                    errorMessage.setText("");
//                    engine.setDirections(directions);
//                    Thread engineThread = new Thread((Runnable)engine);
//                    engineThread.start();
//                }
//                catch (IllegalArgumentException e){
//                    errorMessage.setText(e.getMessage());
//                }
//        });
//
//        HBox hBox = new HBox(button, textField);
//        VBox vBox = new VBox(gridPane, hBox, errorMessage);
//        gridPane.setAlignment(Pos.CENTER);
//        vBox.setAlignment(Pos.CENTER);
//        hBox.setAlignment(Pos.CENTER);
//        errorMessage.setAlignment(Pos.CENTER);
//        Scene scene = new Scene(vBox, 800, 800);
//        primaryStage.setScene(scene);
//        stage.show();
//    }
//
//    public void init(){
//        boxMap = new HashMap<IMapElement, GuiElementBox>();
//        try {
//            Vector2d[] positions = {new Vector2d(4, 4), new Vector2d(6, 4)};
//            map = new GrassField(10);
//            engine = new ThreadedSimulationEngine(map, positions);
//            engine.setObserver(this);
//            engine.moveDelay = 500;
//            initializeGridPane();
//        }
//        catch (IllegalArgumentException exc){
//            System.out.println(exc.toString());
//        }
//    }
//
//    private void initializeGridPane(){
//        gridPane = new GridPane();
//        gridPane.setGridLinesVisible(true);
//
//        lowerLeft = map.lowerLeftMapCorner();
//        Vector2d upperRight = map.upperRightMapCorner();
//        mapHeight = upperRight.y - lowerLeft.y + 1;
//        mapWidth =  upperRight.x - lowerLeft.x + 1;
//
//        createGrid(gridPane, mapWidth, mapHeight);
//        drawBorderLabels(gridPane, mapWidth, mapHeight, lowerLeft);
//
//        for (int i = 1 ; i < mapHeight + 1; i++) {
//            for (int j = 1; j < mapWidth + 1; j++) {
//                int worldY = upperRight.y - i + 1;
//                int worldX = lowerLeft.x + j - 1;
//                Vector2d worldPos = new Vector2d(worldX, worldY);
//                if (map.isOccupied(worldPos)){
//                    IMapElement element = (IMapElement)map.objectAt(worldPos);
//                    VBox vBox;
//                    if (boxMap.containsKey(element)){
//                        vBox = boxMap.get(element).vBox;
//                    }
//                    else{
//                        GuiElementBox elemBox = new GuiElementBox(element);
//                        boxMap.put(element, elemBox);
//                        vBox = elemBox.vBox;
//                    }
//                    gridPane.add(vBox, j, i, 1, 1);
//                    GridPane.setHalignment(vBox, HPos.CENTER);
//
//                }
//            }
//        }
//    }
//
//    private boolean isGridPositionInBounds(Vector2d gridPosition){
//        return 0 < gridPosition.x && gridPosition.x <= mapWidth &&
//                0 < gridPosition.y && gridPosition.y <= mapHeight;
//    }
//
//    private Vector2d convertToGridPosition(Vector2d worldPosition){
//        int x = worldPosition.x - lowerLeft.x + 1;
//        int y = lowerLeft.y + mapHeight - worldPosition.y;
//        return new Vector2d(x, y);
//    }
//
//    private void createGrid(GridPane gridPane,  int mapWidth, int mapHeight){
//        for (int i = 0 ; i < mapHeight + 1; i++) {
//            gridPane.getRowConstraints().add(new RowConstraints(rowWidth));
//        }
//        for (int j = 0; j < mapWidth + 1; j++) {
//            gridPane.getColumnConstraints().add(new ColumnConstraints(columnWidth));
//        }
//    }
//
//    private void drawBorderLabels(GridPane gridPane, int mapWidth, int mapHeight, Vector2d lowerLeft){
//        for (int i = 1; i < mapWidth + 1; i++) {
//            String msg = Integer.toString(lowerLeft.x + i - 1);
//            addLabel(gridPane, msg, i, 0);
//        }
//
//        for (int i = 1; i < mapHeight + 1; i++) {
//            String msg = Integer.toString(lowerLeft.y + mapHeight - i);
//            addLabel(gridPane, msg, 0, i);
//        }
//
//        addLabel(gridPane, "y/x", 0, 0);
//    }
//
//    private void addLabel(GridPane gridPane, String message, int columnIndex, int rowIndex ){
//        Label label = new Label(message);
//        gridPane.add(label, columnIndex, rowIndex, 1, 1);
//        GridPane.setHalignment(label, HPos.CENTER);
//    }
//
//    private String[] listToArray(List<String> list){
//        String[] arr = new String[list.size()];
//        Iterator<String> iterator = list.iterator();
//        int i = 0;
//        while (iterator.hasNext()){
//            arr[i] = iterator.next();
//            i ++;
//        }
//        return arr;
//    }
//
//    @Override
//    public void newFrame(IMapElement element) {
//        GuiElementBox elementBox = boxMap.get(element);
//        Vector2d newPosition = element.getPosition();
//        if (elementBox.didPositionChange(newPosition)){
//            VBox vBox = elementBox.vBox;
//            ObservableList<Node> children = gridPane.getChildren();
//            children.remove((Node)vBox);
//            Vector2d gridPosition = convertToGridPosition(newPosition);
//            VBox overlappedVBox = elementBox.overlappedVBox;
//            if (overlappedVBox != null){
//                Vector2d oldGridPosition = convertToGridPosition(elementBox.getOldPosition());
//                gridPane.add(overlappedVBox, oldGridPosition.x , oldGridPosition.y, 1, 1);
//                elementBox.overlappedVBox = null;
//            }
//            if (isGridPositionInBounds(gridPosition)){
//                for (Node child : children) {
//                    Integer column = GridPane.getColumnIndex(child);
//                    Integer row = GridPane.getRowIndex(child);
//                    if (column != null && row != null && column.intValue() == gridPosition.x && row.intValue() == gridPosition.y) {
//                        elementBox.overlappedVBox = (VBox)child;
//                        children.remove(child);
//                        break;
//                    }
//                }
//                gridPane.add(vBox, gridPosition.x , gridPosition.y, 1, 1);
//            }
//        }
//        elementBox.updateVBox(element);
//    }
//
//}
//
