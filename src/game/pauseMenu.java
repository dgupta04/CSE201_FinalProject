package game;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class pauseMenu {

    private Stage stage;
    private Map map;
    private final static Stage STAGE = new Stage();
    private pauseMenuHandler handler;
    private Parent root;
    private Scene scene;

    public pauseMenu(Map map) throws Exception{

        this.stage = map.STAGE();
        this.map = map;

        STAGE.initStyle(StageStyle.UNDECORATED);
        STAGE.setHeight(1000);
        STAGE.setWidth(1000);
        STAGE.addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {
            if (KeyCode.ESCAPE == event.getCode()) {
                STAGE.close();
                mainMenu.getRoot().setEffect(null);
                this.map.getBackgroundAudio().setMute(true);
            }
        });
        STAGE.initModality(Modality.APPLICATION_MODAL);
        STAGE.initOwner(this.map.STAGE());

        //See main menu
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/game/pauseMenuPopUp.fxml"));
        this.root = loader.load();
        pauseMenuHandler helper = loader.getController();
        this.handler = helper;
        this.handler.setMap(this.map);
        this.handler.setMenu(this);

        this.scene = new Scene(this.root);
        SceneHelper.makeDraggable(this.scene, this.stage);
    }

    public void show(){
        this.map.getBackgroundAudio().setMute(false);
        pauseMenu.STAGE.setScene(this.scene);
        BoxBlur blur = new BoxBlur();
        blur.setHeight(1080.0);
        blur.setWidth(1920.0);
        blur.setInput(new GaussianBlur(100));
        pauseMenu.STAGE.show();
        this.map.getCurrentLevel().getRoot().setEffect(blur);
        this.map.getBackgroundAudio().setMute(false);
    }

    public Map getMap(){
        return this.map;
    }
    public static Stage STAGE(){
        return STAGE;
    }
}
