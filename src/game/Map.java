package game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;


public class Map extends Application implements Serializable {

    private User user;
    private MediaPlayer backgroundAudio;
    private Stage stage;
    private Scene currentLevel;
    private Scene[] level;
    private Parent[] rootOfLevel;
    private levelOneHandler handlerOne;
    private levelTwoHandler handlerTwo;
    private levelThreeHandler handlerThree;
    private levelFourHandler handlerFour;
    private levelFiveHandler handlerFive;

    public Map() {
        this.rootOfLevel = new Parent[5];
        try{
            FXMLLoader loaderOne = new FXMLLoader(getClass().getResource("/game/levelOne.fxml"));
            Parent rootOne = loaderOne.load();
            this.handlerOne = loaderOne.getController();
            this.rootOfLevel[0] = rootOne;

            FXMLLoader loaderTwo = new FXMLLoader(getClass().getResource("/game/levelTwo.fxml"));
            Parent rootTwo = loaderTwo.load();
            this.handlerTwo = loaderTwo.getController();
            this.rootOfLevel[1] = rootTwo;
            this.handlerTwo.setMap(this);

            FXMLLoader loaderThree = new FXMLLoader(getClass().getResource("/game/levelThree.fxml"));
            Parent rootThree = loaderThree.load();
            this.handlerThree = loaderThree.getController();
            this.rootOfLevel[2] = rootThree;

            FXMLLoader loaderFour = new FXMLLoader(getClass().getResource("/game/levelFour.fxml"));
            Parent rootFour = loaderFour.load();
            this.handlerFour = loaderFour.getController();
            this.rootOfLevel[3] = rootFour;

            FXMLLoader loaderFive = new FXMLLoader(getClass().getResource("/game/levelFive.fxml"));
            Parent rootFive = loaderFive.load();
            this.handlerFive = loaderFive.getController();
            this.rootOfLevel[4] = rootFive;

        } catch (IOException e){
            System.out.println("FATAL ! FILE NOT FOUND");
            e.printStackTrace();
        }

        this.level = new Scene[5];
        this.level[0] = new Scene(this.getLevelRoot(1));
        this.level[1] = new Scene(this.getLevelRoot(2));
        this.level[2] = new Scene(this.getLevelRoot(3));
        this.level[3] = new Scene(this.getLevelRoot(4));
        this.level[4] = new Scene(this.getLevelRoot(5));

        SceneHelper.makeDraggable(this.level[0],this.stage);
        SceneHelper.makeDraggable(this.level[1],this.stage);
        SceneHelper.makeDraggable(this.level[2],this.stage);
        SceneHelper.makeDraggable(this.level[3],this.stage);
        SceneHelper.makeDraggable(this.level[4],this.stage);
        //ONLY FOR STATIC GUI DEMO
        this.currentLevel = this.getLevel(2);
    }

    public Scene getLevel(int i){
        return this.level[i-1];
    }

    private Parent getLevelRoot(int i){
        return this.rootOfLevel[i-1];
    }

    public void start(Stage stage ) throws Exception{

        this.stage = stage;

        this.stage.initStyle(StageStyle.UNDECORATED);
        this.stage.setHeight(1000);
        this.stage.setWidth(1000);
        this.stage.addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {
            if (KeyCode.ESCAPE == event.getCode()) {
                this.stage.close();
            }
        });

        this.initializeBackgroundAudio();
        new logoReveal(this);
    }

    private void initializeBackgroundAudio() throws java.net.MalformedURLException{
        File audioFile = new File("../background.mp3");
        Media bgMusic = new Media(audioFile.toURI().toURL().toString());
        this.backgroundAudio = new   javafx.scene.media.MediaPlayer(bgMusic);
        this.backgroundAudio.seek(Duration.seconds(2));
        this.backgroundAudio.setOnEndOfMedia(() -> {
            this.backgroundAudio.seek(Duration.ZERO);
        });
    }

    public void createNewGame(String[] args){
        launch(args);
    }

    public Stage STAGE(){
        return this.stage;
    }

    public MediaPlayer getBackgroundAudio(){
        return this.backgroundAudio;
    }

    public static void main(String[] args) {

        Map map = new Map();
        map.createNewGame(args);
    }

    public Scene getCurrentLevel(){
        return this.currentLevel;
    }
}
