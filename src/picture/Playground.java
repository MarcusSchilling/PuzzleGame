package picture;

import image.ImagePath;
import image.ImageSplit;
import logic.PictureGameService;
import logic.PuzzleSolver;
import logic.RandomPuzzleGenerator;
import picture.design.*;
import run.Picture;
import util.OpenDialogClosed;
import util.SetupInspection;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static picture.Constant.DEGREE_OF_DIFFICULTY;

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

    public Playground(Picture picture) throws Exception {
        SetupInspection setupInspection = new SetupInspection();
        this.inGame = true;
        this.surface = picture;
        this.picture = picture;
        this.pictures = new Pictures(this);
        this.pictures.addPlayingField();
        this.gameService = new PictureGameService(Pictures.fields);
        this.gameData = new GameData(this, gameService);
        this.gameData.drawMoveField();
        this.buttons = new ButtonsHandler(this);
        this.buttons.addButtons();
        this.restartButton = new RestartButton(this);
        this.restartButton.drawButton();
        this.picturePath = new CustomPicturePath(this, picture);
        this.picturePath.drawTextArea();
        this.picturePath.disableTextArea();
        this.puzzleGenerator = new RandomPuzzleGenerator(Pictures.fields);
    }

    public void startGame() {
        int minMovesForSolvingPuzzle;
        inGame = true;
        setImagePath();
        buttons.turnButtonsOn();
        pictures.makeFieldsVisible();
        pictures.actualizeImages(images);
        gameService.resetGameService();
        PuzzleSolver solver = new PuzzleSolver(Pictures.fields, puzzleGenerator);
        do {
            puzzleGenerator.createPuzzle();
            minMovesForSolvingPuzzle = solver.solve();
        } while (minMovesForSolvingPuzzle != DEGREE_OF_DIFFICULTY);
        gameData.actualizeMovesFieldText();
        resultDesign = new ResultDesign(this, gameService, minMovesForSolvingPuzzle);
        resetDesign();
    }

    private void setImagePath() {
        try {
            picturePath.disableTextArea();
            if (!ImagePath.isGlobal()) {
                ImagePath.setImgPath(picturePath.getPath().toString());
                ImagePath.setImgPathGlobal(true);
                loadImage(false);
            } else {
                loadImage(true);
            }
        } catch (IOException e) {
            CustomPicturePath customPicturePath = new CustomPicturePath(this, picture);
            try {
                customPicturePath.openExplorerForPicturePath();
            } catch (OpenDialogClosed openDialogClosed) {
                return;
            }
            ImagePath.setImgPathGlobal(true);
            setImagePath();
        }
    }

    private void loadImage(boolean isGlobalPath) throws IOException {
        ImageSplit.split(ImagePath.getImgPath(), isGlobalPath);
        images = Arrays.asList(ImageSplit.loadImages());
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