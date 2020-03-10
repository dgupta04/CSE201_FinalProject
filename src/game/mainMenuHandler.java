package game;


import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class mainMenuHandler extends clickable {

    private mainMenu menu;
    private Map map;
    private Help h;
    static Stage dialog;
    static boolean playOnce;

    public mainMenuHandler(){
        System.out.println("HERE");
    }

    public void setMap(Map map){
        System.out.println("WORKS");
        this.map = map;
    }

    public void setMenu(mainMenu menu){
        this.menu = menu;
    }

    public void exitGame(MouseEvent mouseEvent) {
        System.out.println("Exit Map");
        this.map.STAGE().close();
    }

    public void showHelp(MouseEvent mouseEvent) throws Exception {

        System.out.println("Show Help");
        if (this.h == null){
            this.h = new Help(this.map);
        }
        this.h.show();
    }

    public void loadGame(MouseEvent mouseEvent) {
        System.out.println("Load Map");
    }

    public void createNewGame(MouseEvent mouseEvent)throws Exception {
        System.out.println("Creating a new Map");
        this.map.getBackgroundAudio().setMute(true);
        this.map.STAGE().setScene(this.map.getCurrentLevel());
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

    public void removeGlow(MouseEvent mouseEvent) {
        super.glowRemover(mouseEvent);
        playOnce = false;
    }
}