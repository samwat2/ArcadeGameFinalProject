package controller;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.Move;
import model.Pieces;

public class Gameboard extends Application {
	Pieces[][] pieces = new Pieces[8][8];
	Pieces tempPiece;
	String turn = "black";
	Pieces killPiece;

	@Override
	public void start(Stage primaryStage) throws Exception {
//		pieces[0][0] = new Pieces("Black", "Black Piece.png", 0, 0);
//		pieces[0][1] = new Pieces("Black", "Black Piece", 1, 0);
//		pieces[0][2] = new Pieces("Black", "Black Piece", 2, 0);
//		pieces[0][3] = new Pieces("Black", "Black Piece", 3, 0);
//		pieces[0][4] = new Pieces("Black", "Black Piece", 4, 0);
//		pieces[0][5] = new Pieces("Black", "Black Piece", 5, 0);
//		pieces[0][6] = new Pieces("Black", "Black Piece", 6, 0);
//		pieces[0][7] = new Pieces("Black", "Black Piece", 7, 0);
//		pieces[1][0] = new Pieces("Black", "Black Piece", 0, 1);
//		pieces[1][1] = new Pieces("Black", "Black Piece", 1, 1);
//		pieces[1][2] = new Pieces("Black", "Black Piece", 2, 1);
//		pieces[1][3] = new Pieces("Black", "Black Piece", 3, 1);
//		pieces[1][4] = new Pieces("Black", "Black Piece", 4, 1);
//		pieces[1][5] = new Pieces("Black", "Black Piece", 5, 1);
//		pieces[1][6] = new Pieces("Black", "Black Piece", 6, 1);
//		pieces[1][7] = new Pieces("Black", "Black Piece", 7, 1);
		
		GridPane root = new GridPane();
		final int size = 8;
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				StackPane square = new StackPane();
				String color;
				if ((row + col) % 2 == 0) {
					color = "red";
				} else {
					color = "black";
				}
				square.setStyle("-fx-background-color: " + color + ";");

				root.add(square, col, row);
				if (pieces[row][col] != null) {
					String pieceImage = pieces[row][col].getColor().toLowerCase()
							+ pieces[row][col].getType().substring(0, 1).toUpperCase()
							+ pieces[row][col].getType().substring(1).toLowerCase();
					Image image = new Image("file:Sprites/" + pieceImage + ".png", 100, 100, true, false);
					root.add(new ImageView(image), col, row);
				}
			}
		}
		for (int i = 0; i < size; i++) {
			root.getColumnConstraints().add(new ColumnConstraints(5, 100, 100, Priority.ALWAYS, HPos.CENTER, true));
			root.getRowConstraints().add(new RowConstraints(5, 100, 100, Priority.ALWAYS, VPos.CENTER, true));
		}
		root.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
			int row = (int) (e.getY() / 100);
			int col = (int) (e.getX() / 100);
			if (tempPiece == null) {
				// System.out.printf("%s %s\n", (int)(e.getX() / 100), (int)(e.getY() / 100));
				resetBackground(root, size);
				if (pieces[row][col] != null) {
					if (pieces[row][col].color.toLowerCase().equals(turn))
						if (pieces[row][col].getMoves() != null)
							for (Move move : pieces[row][col].getMoves()) {
								if (getStackPaneFromGridPane(root, move.getCol(), move.getRow()) != null) {
									if(pieces[move.getRow()][move.getCol()] == null || (pieces[move.getRow()][move.getCol()] != null && !pieces[move.getRow()][move.getCol()].getColor().toLowerCase().equals(turn))) {
									getStackPaneFromGridPane(root, move.getCol(), move.getRow())
											.setStyle("-fx-background-color: green;");
									}
				
								}
							}
					StackPane mattePane = (StackPane) getStackPaneFromGridPane(root, (int) (e.getX() / 100),
							(int) (e.getY() / 100));

					mattePane.setStyle("-fx-background-color: yellow;");
					// mattePane.setBackground(background);
					tempPiece = pieces[row][col];
				}
			} else {
				if (getStackPaneFromGridPane(root, col, row).getStyle().equals("-fx-background-color: green;")) {
					ImageView removeImage = getImageFromGridPane(root, col, row);
					if (removeImage != null) {
						root.getChildren().remove(removeImage);
						
					}
					for(int i = 0; i <= 7; i++) {
						for(int j = 0; j <= 7; j++) {
							if(pieces[i][j] == tempPiece) {
								pieces[i][j] = null;
							}
						}
					}
					ImageView image = getImageFromGridPane(root, tempPiece.getX(), tempPiece.getY());
					root.getChildren().remove(image);
					root.add(image, col, row);
					tempPiece.move(col, row);
					pieces[row][col] = tempPiece;
					if(!gameOver()) {
					turn = turn.equals("white") ? "black" : "white";
					}else {
						
					}
				}
				tempPiece = null;
				resetBackground(root, size);
			}

		});

		primaryStage.setScene(new Scene(root, 800, 800));
		primaryStage.show();
	}

	private void resetBackground(GridPane gridPane, int size) {
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				StackPane square = getStackPaneFromGridPane(gridPane, col, row);
				String color;
				if ((row + col) % 2 == 0) {
					color = "white";
				} else {
					color = "gray";
				}
				square.setStyle("-fx-background-color: " + color + ";");
			}
		}

	}
	
	private boolean gameOver() {
		int blackPiece = 0;
		int whitePiece = 0;
		
		for(int i = 0; i < pieces.length; i++) {
			for(int j = 0; j < pieces[i].length; j++) {
				if(pieces[i][j] != null) {
					if(pieces[i][j].getColor().equalsIgnoreCase("Black")) {
						blackPiece++;
					}else {
						whitePiece++;
					}
				}
			}
		}
		
		
		return whitePiece == 0 || blackPiece == 0;
	}

	private StackPane getStackPaneFromGridPane(GridPane gridPane, int col, int row) {
		for (Node node : gridPane.getChildren()) {
			if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
				if (node instanceof StackPane)
					return (StackPane) node;
			}
		}
		return null;
	}

	private ImageView getImageFromGridPane(GridPane gridPane, int col, int row) {
		for (Node node : gridPane.getChildren()) {
			if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
				if (node instanceof ImageView)
					return (ImageView) node;
			}
		}
		return null;
	}
	}


