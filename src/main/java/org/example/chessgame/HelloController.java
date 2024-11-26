package org.example.chessgame;



import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class HelloController {
    boolean isWhite = true; // положення білих фігур

    int[][] GridPosition = new int[][] { // розставлення фігур для кращого розуміння
            {1, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 2, 0, 2, 0, 2, 0, 2},
            {2, 0, 2, 0, 2, 0, 2, 0},
            {0, 2, 0, 2, 0, 2, 0, 2}
    };

//    int[][] GridPosition = new int[][] { // розставлення фігур для кращого розуміння
//            {0, 0, 0, 0, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 0, 0, 0}
//    };

    int white = -1, black = -1; // для полегшення сприйняття
    int step = 0;

    int attack_figure;
    int defence_figure;
    int attack_lady;
    int defence_lady;

    int count_of_white;
    int count_of_black;

    Image white_fig_img; // імпорт фото шашок
    Image black_fig_img;
    Image white_lady_img = new Image(getClass().getResource("img/standart_pack/white_lady.png").toExternalForm(), true);
    Image black_lady_img = new Image(getClass().getResource("img/standart_pack/black_lady.png").toExternalForm(), true);

    Image[] white_black; // для методу dry(неповторятись)
    Image[] white_black_lady;

    Image pointer = new Image(getClass().getResource("img/standart_pack/pointer.png").toExternalForm(), true);
    Image pointer2 = new Image(getClass().getResource("img/standart_pack/pointer2.png").toExternalForm(), true);
    Image lady_pointer = new Image(getClass().getResource("img/standart_pack/lady_pointer.png").toExternalForm(), true);
    int pointerCoords[][] = new int[3][2]; //

    int getRowFigure;
    int getColFigure;

    boolean isAttack=false; // перевірка атаки, для запуску функції на pointer-и
    boolean isAttackForAnyAttackFunc=false;
    int[][][] LadyAttackCoords = new int[][][]{
            {
                    {-1, -1},
                    {-1, -1},
                    {-1, -1},
                    {-1, -1},
                    {-1, -1},
            },
            {
                    {-1, -1},
                    {-1, -1},
                    {-1, -1},
                    {-1, -1},
                    {-1, -1},
            },
            {
                    {-1, -1},
                    {-1, -1},
                    {-1, -1},
                    {-1, -1},
                    {-1, -1},
            },
            {
                    {-1, -1},
                    {-1, -1},
                    {-1, -1},
                    {-1, -1},
                    {-1, -1},
            },
    };

    int GridAttackerNum = 0;

    int[][] GridAttackerCoords;

    int[][][] GridAttacker = new int[][][]{
            {
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
            },
            {
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
            },
            {
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
            },
            {
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
            }
    };

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private GridPane GridBoard;

    @FXML
    private ImageView img0_0;

    @FXML
    private ImageView img0_2;

    @FXML
    private ImageView img0_4;

    @FXML
    private ImageView img0_6;

    @FXML
    private ImageView img1_1;

    @FXML
    private ImageView img1_3;

    @FXML
    private ImageView img1_5;

    @FXML
    private ImageView img1_7;

    @FXML
    private ImageView img2_0;

    @FXML
    private ImageView img2_2;

    @FXML
    private ImageView img2_4;

    @FXML
    private ImageView img2_6;

    @FXML
    private ImageView img3_1;

    @FXML
    private ImageView img3_3;

    @FXML
    private ImageView img3_5;

    @FXML
    private ImageView img3_7;

    @FXML
    private ImageView img4_0;

    @FXML
    private ImageView img4_2;

    @FXML
    private ImageView img4_4;

    @FXML
    private ImageView img4_6;

    @FXML
    private ImageView img5_1;

    @FXML
    private ImageView img5_3;

    @FXML
    private ImageView img5_5;

    @FXML
    private ImageView img5_7;

    @FXML
    private ImageView img6_0;

    @FXML
    private ImageView img6_2;

    @FXML
    private ImageView img6_4;

    @FXML
    private ImageView img6_6;

    @FXML
    private ImageView img7_1;

    @FXML
    private ImageView img7_3;

    @FXML
    private ImageView img7_5;

    @FXML
    private ImageView img7_7;

    @FXML
    private ImageView lower_coaster;

    @FXML
    private ImageView upper_coaster;

    @FXML
    private ImageView winner;

    String[] white_black_str;

    String spot_of_color = "NNone";

    ImageView[][] GridBoardImg; // для розставлення фото у grid-і

    @FXML
    void mouse_clicked(MouseEvent event) { // реакція програми на натискання на зображення
        ImageView clicked_img = (ImageView) event.getSource();

        int getRowIndex = GridPane.getRowIndex(clicked_img) == null ? 0 : GridPane.getRowIndex(clicked_img);
        int getColIndex = GridPane.getColumnIndex(clicked_img) == null ? 0 : GridPane.getColumnIndex(clicked_img);

        pointerCoords[0][0]=getRowIndex;
        pointerCoords[0][1]=getColIndex;

        GridAttackerNum=0;

        Media sound = new Media(getClass().getResource("media/backgroundmusic.mp3").toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setVolume(0.1);
        mediaPlayer.setOnReady(new Runnable() {
            @Override
            public void run() {
                mediaPlayer.setVolume(0.1);
                mediaPlayer.play();
            }
        });
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                mediaPlayer.setVolume(0.1);
                mediaPlayer.seek(Duration.ZERO);
            }
        });

        int spot_row_level = 0;
        int type = getTypeOfFigure(getRowIndex, getColIndex);

        if(clicked_img.getImage()==white_fig_img || clicked_img.getImage()==black_fig_img) { // реагує тільки якщо клацнуто на фігуру
            getRowFigure = getRowIndex;
            getColFigure = getColIndex;

            if (type == white) {
                spot_of_color = "White";
                spot_row_level = isWhite ? 1 : -1;
            } else if (type == black) {
                spot_of_color = "Black";
                spot_row_level = isWhite ? -1 : 1;
            }

            System.out.println(getRowIndex + " " + getColIndex + " " + spot_of_color);

            clear_board_of_pointer();
            setDefaultAttackPointer(getRowIndex, getColIndex, spot_of_color);
            setDefaultPointer(spot_of_color, getRowIndex, getColIndex, spot_row_level);
        } else if (clicked_img.getImage()==white_lady_img || clicked_img.getImage()==black_lady_img) {
            getRowFigure = getRowIndex;
            getColFigure = getColIndex;

            clear_board_of_pointer();
            setDefaultLadyAttack(getRowIndex, getColIndex);
            setDefaultLadyPointer(getRowIndex, getColIndex);
        } else if (clicked_img.getImage()==pointer) { // якщо на pointer
            System.out.println(true);
            setDefaultStep(getRowIndex, getColIndex, clicked_img, spot_of_color);
        } else if (clicked_img.getImage()==lady_pointer) {
            setDefaultLadyStep(getRowIndex, getColIndex, clicked_img);
        } else {clear_board_of_pointer();} // якщо на порожню клітинку
        count_of_fig(GridPosition);
        lower_coaster.setImage(new Image(getClass().getResource("img/standart_pack/seat_" + white_black_str[0] + "_" + (isWhite ? 12-count_of_black : 12-count_of_white) + ".png").toExternalForm(), true));
        upper_coaster.setImage(new Image(getClass().getResource("img/standart_pack/backward_seat_" + white_black_str[1] + "_" + (isWhite ? 12-count_of_white : 12-count_of_black) + ".png").toExternalForm(), true));

        if(count_of_black==0){
            winner winner1 = new winner();
            winner1.set_image();
            winner1.winner_img.setImage(new Image(getClass().getResource("img/standart_pack/winner_background_white.png").toExternalForm(), true));
        } else if (count_of_white==0) {
            winner_black winner1 = new winner_black();
            winner1.set_image();
            winner1.winner_img.setImage(new Image(getClass().getResource("img/standart_pack/winner_background_black.png").toExternalForm(), true));
        }
    }
    @FXML
    void initialize() { // ініціалізатор, код що виконується один раз при запуску програми
        white_fig_img = new Image(getClass().getResource("img/standart_pack/white_figure.png").toExternalForm(), true);
        black_fig_img = new Image(getClass().getResource("img/standart_pack/black_figure.png").toExternalForm(), true);

        white_black = new Image[]{white_fig_img, black_fig_img};
        white_black_lady = new Image[]{white_lady_img, black_lady_img};
        Image[] img_figure = isWhite ? new Image[] {white_fig_img, black_fig_img} : new Image[] {black_fig_img, white_fig_img};

        white = isWhite == true ? 2 : 1;
        black = isWhite != true ? 2 : 1;
        white_black_str = isWhite ? new String[]{"white", "black"} : new String[]{"black", "white"};

        GridAttackerNum=0;

        lower_coaster.setImage(new Image(getClass().getResource("img/standart_pack/seat_" + white_black_str[0] + "_" + 0 + ".png").toExternalForm(), true));
        upper_coaster.setImage(new Image(getClass().getResource("img/standart_pack/backward_seat_" + white_black_str[1] + "_" + 0 + ".png").toExternalForm(), true));

        attack_figure = step%2==0 ? white : black;
        defence_figure = attack_figure == white ? black : white;
        attack_lady = step%2==0 ? 3 : 4;
        defence_lady = step%2==0 ? 4 : 3;

        GridAttackerCoords = new int[][]{
                {-1, -1},
                {-1, -1},
                {-1, -1},
                {-1, -1}
        };

        GridBoardImg = new ImageView[][]{ // заповнення зображеннями grid
                {img0_0, null, img0_2, null, img0_4, null, img0_6, null},
                {null, img1_1, null, img1_3, null, img1_5, null, img1_7},
                {img2_0, null, img2_2, null, img2_4, null, img2_6, null},
                {null, img3_1, null, img3_3, null, img3_5, null, img3_7},
                {img4_0, null, img4_2, null, img4_4, null, img4_6, null},
                {null, img5_1, null, img5_3, null, img5_5, null, img5_7},
                {img6_0, null, img6_2, null, img6_4, null, img6_6, null},
                {null, img7_1, null, img7_3, null, img7_5, null, img7_7}
        };

        int img_row = 0, img_col; // розставлення білих/чорних фігур
        for(int[] i: GridPosition){
            img_col=0;
            for(int j: i){
                if(j!=0){
                    System.out.println(img_row + " " + img_col);
                    if(j==white){
                        GridBoardImg[img_row][img_col].setImage(white_fig_img);
                    } else if (j==black) {
                        GridBoardImg[img_row][img_col].setImage(black_fig_img);
                    } else if (j==3) {
                        GridBoardImg[img_row][img_col].setImage(white_lady_img);
                    } else if (j==4) {
                        GridBoardImg[img_row][img_col].setImage(black_lady_img);
                    }
                }
                img_col++;
            }
            img_row++;
        }
        System.out.println("isDATK?: " + isAroundDoubleAttack(3, 5, 0, 0));

    }

    protected void count_of_fig(int[][] Grid){
        count_of_white=0;
        count_of_black=0;
        for(int[] i: Grid){
            for(int j: i){
                if(j==white | j==3){
                    count_of_white++;
                } else if(j==black | j==4){
                    count_of_black++;
                }
            }
        }
    }


    protected void clear_board_of_pointer(){ // очищує від pointer-ів
        int row = 0, col;
        for(int[] i: GridPosition){
            col=0;
            for(int j: i){
                if(GridPosition[row][col]==0){
                    try{
                        GridBoardImg[row][col].setImage(null);
                    }catch (Exception e){};
                }
                col++;
            }
            row++;
        }
    }

    protected int getTypeOfFigure(int row, int col){ // отримує тип фігури(1, 2, 3, 4)
        return GridPosition[row][col];
    }

    private void setDefaultPointer(String spot_of_color, int row, int col, int spot_row){ // встановлення стандартних pointer-ів.
        if(thereIsNoAttack(spot_of_color) | isAttack) return;
        System.out.println("isAtk? " + isAttack);
        if(!spot_of_color.equals("NNone") && ((step%2==0 && spot_of_color.equals("White")) || step%2!=0 && spot_of_color.equals("Black"))){
            try {
                if(GridPosition[row-spot_row][col+1]==0){
                    GridBoardImg[row-spot_row][col+1].setImage(pointer);
                    pointerCoords[1][0] = row-spot_row;
                    pointerCoords[1][1] = col+1;
                }
            } catch (Exception e){};
            System.out.println("fspot: " + (row-1) + " " + (col+1));
            try {
                if(GridPosition[row-spot_row][col-1]==0){
                    GridBoardImg[row-spot_row][col-1].setImage(pointer);
                    pointerCoords[2][0] = row-spot_row;
                    pointerCoords[2][1] = col-1;
                }
            } catch (Exception e){};
            System.out.println("sspot: " + (row-spot_row) + " " + (col-1));
        }
    }

    private boolean setDefaultAttackPointer(int row, int col, String spot_of_color){ // здійснення атак відносно клітинок навкруг
        if (!((step%2==0 && spot_of_color.equals("White")) || step%2!=0 && spot_of_color.equals("Black"))) return false; // перевірка чи шашка на чужому ході не хоче побити свою
        boolean res = false;
        try{
            if((GridBoardImg[row+1][col+1].getImage()==white_black[step%2 == 0 ? 1 : 0] | GridBoardImg[row+1][col+1].getImage()==white_black_lady[step%2 == 0 ? 1 : 0]) && getSecAttack(row+2, col+2)){
                GridBoardImg[row+2][col+2].setImage(pointer);
                GridAttacker[GridAttackerNum][row+1][col+1]=1;
                isAttack=true;
                isAttackForAnyAttackFunc=true;
                res = true;
                if(!isAroundDoubleAttack(row, col, 2, 2) && setDefaultAttackPointer(row+2, col+2, spot_of_color)){
                    GridBoardImg[row+2][col+2].setImage(pointer2);
                } else {
                    GridAttackerCoords[GridAttackerNum][0]=row+2;
                    GridAttackerCoords[GridAttackerNum][1]=col+2;
                    GridAttackerNum++;
                }
            }
        } catch (Exception e){};
        try{
            if((GridBoardImg[row+1][col-1].getImage()==white_black[step%2 == 0 ? 1 : 0] | GridBoardImg[row+1][col-1].getImage()==white_black_lady[step%2 == 0 ? 1 : 0]) && getSecAttack(row+2, col-2)){
                GridBoardImg[row+2][col-2].setImage(pointer);
                GridAttacker[GridAttackerNum][row+1][col-1]=1;
                isAttack=true;
                res = true;
                isAttackForAnyAttackFunc=true;
                if(!isAroundDoubleAttack(row, col, 2, -2) && setDefaultAttackPointer(row+2, col-2, spot_of_color)){
                    GridBoardImg[row+2][col-2].setImage(pointer2);
                } else {
                    GridAttackerCoords[GridAttackerNum][0]=row+2;
                    GridAttackerCoords[GridAttackerNum][1]=col-2;
                    GridAttackerNum++;
                }
            }
        } catch (Exception e){};
        try{
            if((GridBoardImg[row-1][col+1].getImage()==white_black[step%2 == 0 ? 1 : 0] | GridBoardImg[row-1][col+1].getImage()==white_black_lady[step%2 == 0 ? 1 : 0]) && getSecAttack(row-2, col+2)){
                GridBoardImg[row-2][col+2].setImage(pointer);
                GridAttacker[GridAttackerNum][row-1][col+1]=1;
                isAttack=true;
                res = true;
                isAttackForAnyAttackFunc=true;
                if(!isAroundDoubleAttack(row, col, -2, 2) && setDefaultAttackPointer(row-2, col+2, spot_of_color)){
                    GridBoardImg[row-2][col+2].setImage(pointer2);
                } else {
                    GridAttackerCoords[GridAttackerNum][0]=row-2;
                    GridAttackerCoords[GridAttackerNum][1]=col+2;
                    GridAttackerNum++;
                }
            }
        } catch (Exception e){};
        try{
            if((GridBoardImg[row-1][col-1].getImage()==white_black[step%2 == 0 ? 1 : 0] | GridBoardImg[row-1][col-1].getImage()==white_black_lady[step%2 == 0 ? 1 : 0]) && getSecAttack(row-2, col-2)){
                GridBoardImg[row-2][col-2].setImage(pointer);
                GridAttacker[GridAttackerNum][row-1][col-1]=1;
                isAttack=true;
                res = true;
                isAttackForAnyAttackFunc=true;
                if(!isAroundDoubleAttack(row, col, -2, -2) && setDefaultAttackPointer(row-2, col-2, spot_of_color)){
                    GridBoardImg[row-2][col-2].setImage(pointer2);
                } else {
                    GridAttackerCoords[GridAttackerNum][0]=row-2;
                    GridAttackerCoords[GridAttackerNum][1]=col-2;
                    GridAttackerNum++;
                }
            }
        } catch (Exception e){};
        return res;
    }

    private boolean getSecAttack(int row, int col){ // умова для перегляду можливості проведення другої атаки підряд
        try {
            return GridBoardImg[row][col].getImage()==null;
        } catch (Exception e){};
        return false;
    }

    private void setDefaultStep(int row, int col, ImageView img, String spot_of_fig){ // переміщує шашку на pointer
        clear_board_of_pointer();
        GridBoardImg[getRowFigure][getColFigure].setImage(null); // ставить попередній клік порожнім
        GridPosition[row][col] = GridPosition[getRowFigure][getColFigure]; // робить теперішню клітинку зайнятою
        GridPosition[getRowFigure][getColFigure]=0; // робить попередній клік порожнім
        img.setImage(white_black[step%2 == 0 ? 0 : 1]); // умова відносно кроку ставить чорну, або ж білу шашку
        TrackPosition(GridAttacker[0]);
        TrackPosition(GridAttacker[1]);
        TrackPosition(GridAttacker[2]);
        TrackPosition(GridAttacker[3]);
        delete_figure(row, col);
        turn_to_lady(row, col);
        TrackPosition(GridPosition);
        isAttack=false;
        if(isAroundDoubleAttack(row, col, 0, 0) || isAroundAnyAttack(row, col)){
            getRowFigure=row;
            getColFigure=col;
            setDefaultAttackPointer(row, col, spot_of_fig);
        } else{
            isAttackForAnyAttackFunc=false;
            step++;
            attack_figure = step%2==0 ? white : black;
            defence_figure = attack_figure == white ? black : white;
            attack_lady = step%2==0 ? 3 : 4;
            defence_lady = step%2==0 ? 4 : 3;
        }
    }

    private void TrackPosition(int[][] Grid){ // функція для моніторингу дошки
        System.out.println("/////////////////////////////////////");
        for(int i[]: Grid){
            for(int j: i){
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

    private boolean thereIsNoAttack(String spot_of_color){
        if ((step%2==0 && spot_of_color.equals("White")) || step%2!=0 && spot_of_color.equals("Black")){
            int row=0, col;
            for(int i[]: GridPosition){
                col=0;
                for(int j: i){
                    if(j==attack_figure){
                        try{
                            if((GridPosition[row+1][col+1]==defence_figure | GridPosition[row+1][col+1]==defence_lady) && GridPosition[row+2][col+2]==0){
                                return true;
                            }
                        } catch (Exception e){};
                        try{
                            if((GridPosition[row+1][col-1]==defence_figure | GridPosition[row+1][col-1]==defence_lady) && GridPosition[row+2][col-2]==0){
                                return true;
                            }
                        } catch (Exception e){};
                        try{
                            if((GridPosition[row-1][col+1]==defence_figure | GridPosition[row-1][col+1]==defence_lady) && GridPosition[row-2][col+2]==0){
                                return true;
                            }
                        } catch (Exception e){};
                        try{
                            if((GridPosition[row-1][col-1]==defence_figure | GridPosition[row-1][col-1]==defence_lady) && GridPosition[row-2][col-2]==0){
                                return true;
                            }
                        } catch (Exception e){};
                    }
                    col++;
                }
                row++;
            }
        }
        return false;
    }

    private boolean isAroundAnyAttack(int row, int col){
        if(!isAttackForAnyAttackFunc) return false;
        try{
            if((GridPosition[row+1][col+1]==defence_figure | GridPosition[row+1][col+1]==defence_lady) && GridPosition[row+2][col+2]==0){
                return true;
            }
        }catch (Exception e){};
        try{
            if((GridPosition[row+1][col-1]==defence_figure | GridPosition[row+1][col-1]==defence_lady) && GridPosition[row+2][col-2]==0){
                return true;
            }
        }catch (Exception e){};
        try{
            if((GridPosition[row-1][col+1]==defence_figure | GridPosition[row-1][col+1]==defence_lady) && GridPosition[row-2][col+2]==0){
                return true;
            }
        }catch (Exception e){};
        try{
            if((GridPosition[row-1][col-1]==defence_figure | GridPosition[row-1][col-1]==defence_lady) && GridPosition[row-2][col-2]==0){
                return true;
            }
        }catch (Exception e){};
        return false;
    }

    private boolean isAroundDoubleAttack(int row, int col, int at_row, int at_col){
        boolean res = false;
        int n = 0;
        try{
            if((GridPosition[row+at_row+1][col+at_col+1]==defence_figure | GridPosition[row+at_row+1][col+at_col+1]==defence_lady) && GridPosition[row+at_row+2][col+at_col+2]==0){
                n++;
            }
        }catch (Exception e){};
        System.out.println("n: " + n);
        try{
            if((GridPosition[row+at_row+1][col+at_col-1]==defence_figure | GridPosition[row+at_row+1][col+at_col-1]==defence_lady) && GridPosition[row+at_row+2][col+at_col-2]==0){
                n++;
            }
        }catch (Exception e){};
        System.out.println("n: " + n);
        try{
            if((GridPosition[row+at_row-1][col+at_col+1]==defence_figure | GridPosition[row+at_row-1][col+at_col+1]==defence_lady) && GridPosition[row+at_row-2][col+at_col+2]==0){
                n++;
            }
        }catch (Exception e){};
        System.out.println("n: " + n);
        try{
            if((GridPosition[row+at_row-1][col+at_col-1]==defence_figure | GridPosition[row+at_row-1][col+at_col-1]==defence_lady) && GridPosition[row+at_row-2][col+at_col-2]==0){
                n++;
            }
        }catch (Exception e){};
        System.out.println("n: " + n);
        if(n>=2) res = true;
        return res;
    }

    private void turn_to_lady(int row, int col){
        int w_edge = white == 2 ? 0 : 7;
        int b_edge = black == 2 ? 0 : 7;

        if(row == w_edge && GridPosition[row][col]==white){
            GridPosition[row][col]=3;
            GridBoardImg[row][col].setImage(white_lady_img);
        } else if (row == b_edge && GridPosition[row][col]==black){
            GridPosition[row][col]=4;
            GridBoardImg[row][col].setImage(black_lady_img);
        }
    }

    private void delete_figure(int out_row, int out_col){
        int n=0;
        for(int[] i: GridAttackerCoords){
            if(i[0]==out_row && i[1]==out_col){
                GridAttackerNum=n;
                System.out.println("GAN: " + GridAttackerNum);
            }
            n++;
        }
        int row=0, col;
        for(int[] i: GridAttacker[GridAttackerNum]){
            col=0;
            for(int j: i){
                if(j==1) {
                    GridBoardImg[row][col].setImage(null);
                    GridPosition[row][col]=0;
                }
                GridAttacker[0][row][col]=0;
                GridAttacker[1][row][col]=0;
                GridAttacker[2][row][col]=0;
                GridAttacker[3][row][col]=0;
                col++;
            }
            row++;
        }
        GridAttackerCoords = new int[][]{
                {-1, -1},
                {-1, -1},
                {-1, -1},
                {-1, -1}
        };
        GridAttackerNum=0;
    }
    // LADY CODE ///////////////////////////////////////////////////////////////////////////////////////////////////////

    private void setDefaultLadyPointer(int row, int col){
        if((step%2==0 && GridPosition[row][col]==4) || (step%2==1 && GridPosition[row][col]==3)) return;
        if(isAttack) return;
        try{
            for(int i=1; i<=7; i++){
                if(GridPosition[row+i][col+i]==0){
                    GridBoardImg[row+i][col+i].setImage(lady_pointer);
                } else break;
            }
        } catch (Exception e){};
        try{
            for(int i=1; i<=7; i++){
                if(GridPosition[row-i][col+i]==0){
                    GridBoardImg[row-i][col+i].setImage(lady_pointer);
                } else break;
            }
        } catch (Exception e){};
        try{
            for(int i=1; i<=7; i++){
                if(GridPosition[row+i][col-i]==0){
                    GridBoardImg[row+i][col-i].setImage(lady_pointer);
                } else break;
            }
        } catch (Exception e){};
        try{
            for(int i=1; i<=7; i++){
                if(GridPosition[row-i][col-i]==0){
                    GridBoardImg[row-i][col-i].setImage(lady_pointer);
                } else break;
            }
        } catch (Exception e){};
    }

    private void setDefaultLadyStep(int row, int col, ImageView img){
        GridBoardImg[row][col].setImage(null);
        GridPosition[row][col]=GridPosition[getRowFigure][getColFigure];
        GridPosition[getRowFigure][getColFigure]=0;
        img.setImage(white_black_lady[step%2==0 ? 0 : 1]);
        TrackPosition(GridAttacker[0]);
        TrackPosition(GridAttacker[1]);
        TrackPosition(GridAttacker[2]);
        TrackPosition(GridAttacker[3]);
        delete_figure_lady_edition(row, col);
        TrackPosition(GridPosition);
        clear_board_of_pointer();
        if(!setDefaultLadyAttack(row, col)){
            step++;
            attack_figure = step%2==0 ? white : black;
            defence_figure = attack_figure == white ? black : white;
            attack_lady = step%2==0 ? 3 : 4;
            defence_lady = step%2==0 ? 4 : 3;
            isAttack=false;
        } else clear_board_of_pointer();
    }

    private void delete_figure_lady_edition(int out_row, int out_col){
        int n=0;
        boolean out = false;
        for(int[][] i: LadyAttackCoords){
            for(int[] j: i){
                if(j[0]==out_row && j[1]==out_col){
                    GridAttackerNum=n;
                    System.out.println("GAN: " + GridAttackerNum);
                    out=true;
                }
            }
            if(out) break;
            n++;
        }
        int row=0, col;
        for(int[] i: GridAttacker[GridAttackerNum]){
            col=0;
            for(int j: i){
                if(j==1) {
                    GridBoardImg[row][col].setImage(null);
                    GridPosition[row][col]=0;
                }
                GridAttacker[0][row][col]=0;
                GridAttacker[1][row][col]=0;
                GridAttacker[2][row][col]=0;
                GridAttacker[3][row][col]=0;
                col++;
            }
            row++;
        }
        LadyAttackCoords = new int[][][]{
                {
                        {-1, -1},
                        {-1, -1},
                        {-1, -1},
                        {-1, -1},
                        {-1, -1},
                },
                {
                        {-1, -1},
                        {-1, -1},
                        {-1, -1},
                        {-1, -1},
                        {-1, -1},
                },
                {
                        {-1, -1},
                        {-1, -1},
                        {-1, -1},
                        {-1, -1},
                        {-1, -1},
                },
                {
                        {-1, -1},
                        {-1, -1},
                        {-1, -1},
                        {-1, -1},
                        {-1, -1},
                },
        };
        GridAttackerNum=0;
    }

    private boolean setDefaultLadyAttack(int row, int col){
        if((step%2==0 && GridPosition[row][col]==4) || (step%2==1 && GridPosition[row][col]==3)) return false;
        boolean n;
        boolean res = false;
        int count;
        try{
            n=false;
            count=0;
            for(int i=1; i<=7; i++){
                if(n) {
                    if(GridPosition[row+i][col+i]!=0){
                        break;
                    }
                    LadyAttackCoords[GridAttackerNum-1][count][0]=row+i;
                    LadyAttackCoords[GridAttackerNum-1][count][1]=col+i;
                    System.out.println("LAC++r: " + LadyAttackCoords[GridAttackerNum-1][count][0]);
                    System.out.println("LAC++c: " + LadyAttackCoords[GridAttackerNum-1][count][1]);
                    count++;
                    GridBoardImg[row+i][col+i].setImage(lady_pointer);
                } else if((GridPosition[row+i][col+i]==defence_figure || GridPosition[row+i][col+i]==defence_lady) && GridPosition[row+i+1][col+i+1]==0){
                    n=true;
                    res=true;
                    isAttack=true;
                    GridAttacker[GridAttackerNum][row+i][col+i]=1;
                    GridAttackerNum++;
                } else if (GridPosition[row+i][col+i]!=0) {
                    break;
                }
            }
        } catch (Exception e){};
        try{
            n=false;
            count=0;
            for(int i=1; i<=7; i++){
                if(n) {
                    if(GridPosition[row+i][col-i]!=0){
                        break;
                    }
                    LadyAttackCoords[GridAttackerNum-1][count][0]=row+i;
                    LadyAttackCoords[GridAttackerNum-1][count][1]=col-i;
                    System.out.println("LAC+-r: " + LadyAttackCoords[GridAttackerNum-1][count][0]);
                    System.out.println("LAC+-c: " + LadyAttackCoords[GridAttackerNum-1][count][1]);
                    count++;
                    GridBoardImg[row+i][col-i].setImage(lady_pointer);
                } else if((GridPosition[row+i][col-i]==defence_figure || GridPosition[row+i][col-i]==defence_lady) && GridPosition[row+i+1][col-i-1]==0){
                    n=true;
                    res=true;
                    isAttack=true;
                    GridAttacker[GridAttackerNum][row+i][col-i]=1;
                    GridAttackerNum++;
                } else if (GridPosition[row+i][col-i]!=0) {
                    break;
                }
            }
        } catch (Exception e){};
        try{
            n=false;
            count=0;
            for(int i=1; i<=7; i++){
                if(n) {
                    if(GridPosition[row-i][col+i]!=0){
                        break;
                    }
                    LadyAttackCoords[GridAttackerNum-1][count][0]=row-i;
                    LadyAttackCoords[GridAttackerNum-1][count][1]=col+i;
                    System.out.println("LAC-+r: " + LadyAttackCoords[GridAttackerNum-1][count][0]);
                    System.out.println("LAC-+c: " + LadyAttackCoords[GridAttackerNum-1][count][1]);
                    count++;
                    GridBoardImg[row-i][col+i].setImage(lady_pointer);
                } else if((GridPosition[row-i][col+i]==defence_figure || GridPosition[row-i][col+i]==defence_lady) && GridPosition[row-i-1][col+i+1]==0){
                    n=true;
                    res=true;
                    isAttack=true;
                    GridAttacker[GridAttackerNum][row-i][col+i]=1;
                    GridAttackerNum++;
                } else if (GridPosition[row-i][col+i]!=0) {
                    break;
                }
            }
        } catch (Exception e){};
        try{
            n=false;
            count=0;
            for(int i=1; i<=7; i++){
                if(n) {
                    if(GridPosition[row-i][col-i]!=0){
                        break;
                    }
                    LadyAttackCoords[GridAttackerNum-1][count][0]=row-i;
                    LadyAttackCoords[GridAttackerNum-1][count][1]=col-i;
                    System.out.println("LAC--r: " + LadyAttackCoords[GridAttackerNum-1][count][0]);
                    System.out.println("LAC--c: " + LadyAttackCoords[GridAttackerNum-1][count][1]);
                    count++;
                    GridBoardImg[row-i][col-i].setImage(lady_pointer);
                } else if((GridPosition[row-i][col-i]==defence_figure || GridPosition[row-i][col-i]==defence_lady) && GridPosition[row-i-1][col-i-1]==0){
                    n=true;
                    res=true;
                    isAttack=true;
                    GridAttacker[GridAttackerNum][row-i][col-i]=1;
                    GridAttackerNum++;
                } else if (GridPosition[row-i][col-i]!=0) {
                    break;
                }
            }
        } catch (Exception e){};
        return res;
    }
}
