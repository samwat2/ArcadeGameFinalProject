package application;

import controller.Gameboard;
import controller.UNO;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

	public Button playUNO = new Button("Play UNO");
	public Button playCheckers = new Button("Play Checkers");
	public StackPane background = new StackPane();
	public Button submit = new Button("Submit");
	public UNO one = new UNO();
	public Gameboard board = new Gameboard();

	@Override
	public void start(Stage primaryStage) {
		try {

			playUNO.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					try {
						one.start(primaryStage);
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			});

			playCheckers.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					try {
						board.start(primaryStage);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});

			String url = "file:Sprites/ArcadeLogo.jpg";
			Image backGround = new Image(url);
			// ImageView view= new ImageView(url);
			BackgroundImage back = new BackgroundImage(backGround, null, null, null,
					new BackgroundSize(800, 800, false, false, true, true));

			background.setBackground(new Background(back));

			Scene scene = new Scene(background, 800, 800);

			primaryStage.setScene(scene);
			primaryStage.setTitle("Arcade: UNO or Checkers");
			background.setPadding(new Insets(20, 80, 20, 80));
			playUNO.setTranslateX(-50);
			playUNO.setTranslateY(-100);
			playCheckers.setTranslateX(100);
			playCheckers.setTranslateY(200);
			background.getChildren().add(playUNO);
			background.getChildren().add(playCheckers);

			primaryStage.setResizable(false);

			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		launch(args);
	}
}
