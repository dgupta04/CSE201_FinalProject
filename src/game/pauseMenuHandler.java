package game;

import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class pauseMenuHandler extends clickable {

    private static boolean playOnce;
    private Map map;
    private pauseMenu menu;

    public void setMap(Map map){
        this.map = map;
    }

    public void setMenu(pauseMenu menu){
        this.menu = menu;
    }

    public void removeGlow(MouseEvent mouseEvent) {
        super.glowRemover(mouseEvent);
        playOnce = false;

    }

    public void makeGlow(MouseEvent mouseEvent) {
        ImageView i = (ImageView) mouseEvent.getSource();
        Glow glow = new Glow();
        if (!playOnce){
            super.play();
            playOnce = true;
        }
        glow.setLevel(0.3);
        i.setEffect(glow);
    }


    public void closeButton(MouseEvent mouseEvent) {
        pauseMenu.STAGE().close();
        this.map.getCurrentLevel().getRoot().setEffect(null);
        this.map.getBackgroundAudio().setMute(true);
    }
}
