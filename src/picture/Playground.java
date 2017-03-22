package picture;

import image.ImagePath;
import image.ImageSplit;
import logic.PictureGameService;
import logic.PuzzleSolver;
import logic.RandomPuzzleGenerator;
import picture.design.*;
import run.Picture;
import util.SetupInspection;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

import static picture.design.Pictures.fields;

public class Playground {


    private List<ImageIcon> images;

    private PictureGameService gameService;
    private RandomPuzzleGenerator puzzleGenerator;

    private Picture picture;
    private JFrame surface;
    private ButtonsHandler buttons;
    private Pictures pictures;
    private GameData gameData;
    private ResultDesign resultDesign;
    private RestartButton restartButton;
    private CustomPicturePath picturePath;
    private boolean inGame;

    public Playground(Picture picture, String imgPathIn) throws Exception {
        SetupInspection setupInspection = new SetupInspection();
        inGame = true;
        surface = (JFrame) picture;
        this.picture = picture;
        ImagePath.setImgPath(imgPathIn);
        ImageSplit.split(ImagePath.getImgPath(), false);
        images = Arrays.asList(ImageSplit.loadImages());
        gameService = new PictureGameService(Arrays.asList(ImageSplit.loadImages()));
        pictures = new Pictures(this);
        pictures.addPlayingField();
        gameData = new GameData(this, gameService);
        gameData.drawMoveField();
        buttons = new ButtonsHandler(this, gameService, gameData);
        buttons.addButtons();
        resultDesign = new ResultDesign(this, gameService);
        restartButton = new RestartButton(this);
        restartButton.drawButton();
        picturePath = new CustomPicturePath(this, picture);
        picturePath.drawTextArea();
        picturePath.diableTextArea();
        puzzleGenerator = new RandomPuzzleGenerator(Pictures.fields);
    }

    public void startGame() {
        inGame = true;
        picturePath.diableTextArea();
        try {
            if (!ImagePath.isGlobal()) {
                ImagePath.setImgPath(picturePath.getPath().toString());
                ImagePath.setImgPathGlobal(true);
                loadImage(false);
            } else {
                loadImage(true);
            }
        } catch (Exception e) {

        }
        resetDesign();
        buttons.turnButtonsOn();
        pictures.makeFieldsVisible();
        pictures.actualizeImages(images);
        gameService.resetGameService();
        gameData.actualizeMovesFieldText();
        puzzleGenerator.createPuzzle();
        // as long as the puzzle is the same as the result the game doesn't starts
        if (gameService.isCorrect()) {
            startGame();
        }
        PuzzleSolver solver = new PuzzleSolver(fields, puzzleGenerator);
        System.out.println("Solved in " + solver.solve());
        System.out.println("Created with " + puzzleGenerator.getChanges() + "moves");
    }

    private void loadImage(boolean isGlobalPath) throws Exception {
        ImageSplit.split(ImagePath.getImgPath(), isGlobalPath);
        images = Arrays.asList(ImageSplit.loadImages());
        gameService.setImagesCorrect(Arrays.asList(ImageSplit.loadImages()));
        picture.setImageFavicon(ImageSplit.loadFullImage(ImagePath.getImgPath(), isGlobalPath));
    }

    private void resetDesign() {
        restartButton.turnButtonOff();
        resultDesign.makeResultDesignsInvisible();
    }

    public void drawToContentPane(Component[][] comp) {
        for (Component[] componentArray : comp) {
            for (Component component : componentArray) {
                surface.add(component);
            }
        }
    }

    public void drawToContentPane(Component[] comp) {
        for (Component component : comp) {
            surface.add(component);
        }
    }

    public void drawToContentPane(Component comp) {
        surface.getContentPane().add(comp);
    }

    protected void removeFromContentPane(Component comp) {
        surface.remove(comp);
    }

    public void evaluation() {
        if (gameService.isCorrect()) {
            stopGame();
        }
    }

    private void stopGame() {
        inGame = false;
        picturePath.enableTextArea();
        buttons.turnButtonsOff();
        resultDesign.drawLabels();
        resultDesign.fillLabelsWithGameResult();
        resultDesign.makeResultDesignsVisible();
        pictures.makeFieldsInvisible();
        restartButton.turnButtonOn();
    }

    public void buttonExecution() {
        gameService.newAttempt();
        gameData.actualizeMovesFieldText();
        evaluation();
    }

    public boolean inGame() {
        return inGame;
    }

}