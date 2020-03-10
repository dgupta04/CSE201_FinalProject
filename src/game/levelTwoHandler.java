package game;

import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.net.MalformedURLException;

public class levelTwoHandler extends clickable{

    private Map map;
    static  boolean playOnce;
    private pauseMenu pm;

    public levelTwoHandler(){
    }

    public void setMap(Map map){
        this.map = map;
    }
    public void mouseEnter(MouseEvent mouseEvent) {
        ImageView i = (ImageView) mouseEvent.getSource();
        Glow glow = new Glow();
        if (!playOnce){
            super.play();
            playOnce = true;
        }
        glow.setLevel(0.3);
        i.setEffect(glow);
    }

    public void mouseExit(MouseEvent mouseEvent) {
        ImageView i = (ImageView) mouseEvent.getSource();
        Glow glow = new Glow();
        glow.setLevel(0.0);
        i.setEffect(glow);
        playOnce = false;
    }

    public void pauseMenuHandler(MouseEvent mouseEvent) throws Exception {

        if (this.pm == null){
            this.pm = new pauseMenu(this.map);
        }
        BoxBlur blur = new BoxBlur();
        blur.setHeight(1080.0);
        blur.setWidth(1920.0);
        blur.setInput(new GaussianBlur(100));
        this.map.getLevel(2).getRoot().setEffect(blur);
        this.pm.show();
    }
}
