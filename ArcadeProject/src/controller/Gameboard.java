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
import model.NormPiece;
import model.Pieces;


public class Gameboard extends Application {
	NormPiece[][] pieces = new NormPiece[8][8];
	NormPiece tempPiece;
	String turn = "Black Piece";
	NormPiece killPiece;
	static GridPane root = new GridPane();
	final int size = 8;

	@Override
	public void start(Stage primaryStage) throws Exception {
		pieces[0][0] = new NormPiece("Black Piece", "Piece", 0, 0);
		pieces[0][2] = new NormPiece("Black Piece", "Piece", 2, 0);
		pieces[0][4] = new NormPiece("Black Piece", "Piece", 4, 0);
		pieces[0][6] = new NormPiece("Black Piece", "Piece", 6, 0);
		pieces[1][1] = new NormPiece("Black Piece", "Piece", 1, 1);
		pieces[1][3] = new NormPiece("Black Piece", "Piece", 3, 1);
		pieces[1][5] = new NormPiece("Black Piece", "Piece", 5, 1);
		pieces[1][7] = new NormPiece("Black Piece", "Piece", 7, 1);
		pieces[2][0] = new NormPiece("Black Piece", "Piece", 0, 2);
		pieces[2][2] = new NormPiece("Black Piece", "Piece", 2, 2);
		pieces[2][4] = new NormPiece("Black Piece", "Piece", 4, 2);
		pieces[2][6] = new NormPiece("Black Piece", "Piece", 6, 2);
//		
		pieces[7][1] = new NormPiece("Red Piece", "Piece", 1, 7);
		pieces[7][3] = new NormPiece("Red Piece", "Piece", 3, 7);
		pieces[7][5] = new NormPiece("Red Piece", "Piece", 5, 7);
		pieces[7][7] = new NormPiece("Red Piece", "Piece", 7, 7);
		pieces[6][0] = new NormPiece("Red Piece", "Piece", 0, 6);
		pieces[6][2] = new NormPiece("Red Piece", "Piece", 2, 6);
		pieces[6][4] = new NormPiece("Red Piece", "Piece", 4, 6);
		pieces[6][6] = new NormPiece("Red Piece", "Piece", 6, 6);
		pieces[5][1] = new NormPiece("Red Piece", "Piece", 1, 5);
		pieces[5][3] = new NormPiece("Red Piece", "Piece", 3, 5);
		pieces[5][5] = new NormPiece("Red Piece", "Piece", 5, 5);
		pieces[5][7] = new NormPiece("Red Piece", "Piece", 7, 5);

		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				StackPane square = new StackPane();
				String color;
				if ((row + col) % 2 == 0) {
					color = "BC1400";
				} else {
					color = "black";
				}
				square.setStyle("-fx-background-color: " + color + ";");

				root.add(square, col, row);
				if (pieces[row][col] != null) {
					String pieceImage = pieces[row][col].getColor();

					Image image = new Image("file:Sprites/" + pieceImage + ".png", 100, 100, false, false);
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
			
				resetBackground(root, size);
				if (pieces[row][col] != null) {
					if (pieces[row][col].color.equals(turn))
						if (pieces[row][col].getMoves() != null)
							//insert jump
							for (Move move : pieces[row][col].getMoves()) {
								if (getStackPaneFromGridPane(root, move.getCol(), move.getRow()) != null) {
									if (pieces[move.getRow()][move.getCol()] == null
											|| (pieces[move.getRow()][move.getCol()] != null
													&& !pieces[move.getRow()][move.getCol()].getColor().equals(turn))) {
										getStackPaneFromGridPane(root, move.getCol(), move.getRow())
												.setStyle("-fx-background-color: green;");
									}
								}
							}
					StackPane mattePane = (StackPane) getStackPaneFromGridPane(root, (int) (e.getX() / 100),
							(int) (e.getY() / 100));

					mattePane.setStyle("-fx-background-color: yellow;");
					tempPiece = pieces[row][col];
				}
			} else {
				if (getStackPaneFromGridPane(root, col, row).getStyle().equals("-fx-background-color: green;")) {
					ImageView removeImage = getImageFromGridPane(root, col, row);
					if (removeImage != null) {
						root.getChildren().remove(removeImage);

					}
					for (int i = 0; i <= 7; i++) {
						for (int j = 0; j <= 7; j++) {
							if (pieces[i][j] == tempPiece) {
								pieces[i][j] = null;
							}
						}
					}
					ImageView image = getImageFromGridPane(root, tempPiece.getX(), tempPiece.getY());
					root.getChildren().remove(image);
					root.add(image, col, row);
					tempPiece.move(col, row);
					pieces[row][col] = tempPiece;
					if (!gameOver()) {
						turn = turn.equals("Black Piece") ? "Red Piece" : "Black Piece";
					} else {
						turn = turn.equals("Red Piece") ? "Black Piece" : "Red Piece";
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
					color = "BC1400";
				} else {
					color = "black";
				}
				square.setStyle("-fx-background-color: " + color + ";");
			}
		}

	}

	private boolean gameOver() {
		int blackPiece = 0;
		int redPiece = 0;
		for (int i = 0; i < pieces.length; i++) {
			for (int j = 0; j < pieces[i].length; j++) {
				if (pieces[i][j] != null) {
					if (pieces[i][j].getColor().equalsIgnoreCase("Black")) {
						blackPiece++;
					} else {
						redPiece++;
					}
				}
			}
		}

		return redPiece == 0 || blackPiece == 0;
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

	private static void canAttack(NormPiece[][] pieces, NormPiece tempPiece) {
		boolean attack = false;
		for (Move move : tempPiece.getMoves()) {
			if (tempPiece.getColor() == "Black Piece") {
				if (pieces[move.getRow()][move.getCol()] != null
						&& pieces[move.getRow() + 1][move.getCol() + 1] == null) {
					tempPiece.getMoves().add(new Move(move.getRow() + 1, move.getCol() + 1));
					attack = true;
				}
				if (pieces[move.getRow()][move.getCol()] != null
						&& pieces[move.getRow() + 1][move.getCol() - 1] == null) {
					tempPiece.getMoves().add(new Move(move.getRow() + 1, move.getCol() - 1));
					attack = true;
				}
			}
			if (tempPiece.getColor() == "Red Piece") {
				if (pieces[move.getRow()][move.getCol()] != null
						&& pieces[move.getRow() - 1][move.getCol() + 1] == null) {
					tempPiece.getMoves().add(new Move(move.getRow() - 1, move.getCol() + 1));
					attack = true;

				if (pieces[move.getRow()][move.getCol()] != null
						&& pieces[move.getRow() - 1][move.getCol() - 1] == null) {
					tempPiece.getMoves().add(new Move(move.getRow() - 1, move.getCol() - 1));
					attack = true;
				}
			}
		}
	
	}

	
		
	}

}
