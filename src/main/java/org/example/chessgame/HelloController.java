package org.example.chessgame;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class HelloController {
    boolean isWhite = true; // положення білих фігур

    int white = -1, black = -1; // для полегшення сприйняття
    int step = 0;

    Image white_fig_img; // імпорт фото шашок
    Image black_fig_img;

    Image[] white_black; // для методу dry(неповторятись)

    Image pointer = new Image(getClass().getResource("img/standart_pack/pointer.png").toExternalForm(), true);
    Image pointer2 = new Image(getClass().getResource("img/standart_pack/pointer2.png").toExternalForm(), true);

    int pointerCoords[][] = new int[3][2]; //

    int getRowFigure;
    int getColFigure;

    boolean isAttack=false; // перевірка атаки, для запуску функції на pointer-и

    int[][] GridAttacker = new int[][]{
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
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

    ImageView[][] GridBoardImg; // для розставлення фото у grid-і

    @FXML
    void mouse_clicked(MouseEvent event) { // реакція програми на натискання на зображення
        ImageView clicked_img = (ImageView) event.getSource();

        int getRowIndex = GridPane.getRowIndex(clicked_img) == null ? 0 : GridPane.getRowIndex(clicked_img);
        int getColIndex = GridPane.getColumnIndex(clicked_img) == null ? 0 : GridPane.getColumnIndex(clicked_img);

        pointerCoords[0][0]=getRowIndex;
        pointerCoords[0][1]=getColIndex;

        if(clicked_img.getImage()==white_fig_img || clicked_img.getImage()==black_fig_img) { // реагує тільки якщо клацнуто на фігуру
            int spot_row_level = 0;
            int type = getTypeOfFigure(getRowIndex, getColIndex);
            getRowFigure = getRowIndex;
            getColFigure = getColIndex;
            boolean isLady = false;

            String spot_of_color = "NNone";
            if (type == white) {
                spot_of_color = "White";
                spot_row_level = isWhite ? 1 : -1;
            } else if (type == black) {
                spot_of_color = "Black";
                spot_row_level = isWhite ? -1 : 1;
            } else if (type == 3) {
                spot_of_color = "White";
                isLady = true;
            } else if (type == 4) {
                spot_of_color = "Black";
                isLady = true;
            } else return;

            System.out.println(getRowIndex + " " + getColIndex + " " + spot_of_color);

            clear_board_of_pointer();
            setDefaultAttackPointer(getRowIndex, getColIndex, spot_of_color);
            setDefaultPointer(spot_of_color, getRowIndex, getColIndex, spot_row_level);
        } else if (clicked_img.getImage()==pointer) { // якщо на pointer
            System.out.println(true);
            setDefaultStep(getRowIndex, getColIndex, clicked_img);
        } else clear_board_of_pointer(); // якщо на порожню клітинку
    }

    @FXML
    void initialize() { // ініціалізатор, код що виконується один раз при запуску програми
        white_fig_img = new Image(getClass().getResource("img/standart_pack/white_figure.png").toExternalForm(), true);
        black_fig_img = new Image(getClass().getResource("img/standart_pack/black_figure.png").toExternalForm(), true);

        white_black = new Image[]{white_fig_img, black_fig_img};
        Image[] img_figure = isWhite ? new Image[] {white_fig_img, black_fig_img} : new Image[] {black_fig_img, white_fig_img};


        white = isWhite == true ? 2 : 1;
        black = isWhite != true ? 2 : 1;

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

        int img_row = 0; // розставлення білих/чорних фігур
        int img_col, img_first=1;
        for(ImageView[] i: GridBoardImg){
            img_col=0;
            if(img_row<=2 || img_row>=5){
                for(ImageView j: i){
                    if(j!=null) j.setImage(img_figure[img_first]);
                    img_col++;
                }
            }
            img_row++;
            if(img_row==4) img_first=0;
        }
    }


    protected void clear_board_of_pointer(){ // очищує від pointer-ів
        int row = 0, col;
        for(ImageView[] i: GridBoardImg){
            col=0;
            for(ImageView j: i){
                if(GridPosition[row][col]==0){
                    try{
                        j.setImage(null);
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
        if(thereIsNoAttack(spot_of_color)) return;
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
        try{
            if(GridBoardImg[row+1][col+1].getImage()==white_black[step%2 == 0 ? 1 : 0] && getSecAttack(row+2, col+2)){
                GridBoardImg[row+2][col+2].setImage(pointer);
                if(setDefaultAttackPointer(row+2, col+2, spot_of_color)){
                    GridBoardImg[row+2][col+2].setImage(pointer2);
                }
                GridAttacker[row+1][col+1]=1;
                isAttack=true;
                return true;
            }
        } catch (Exception e){};
        try{
            if(GridBoardImg[row+1][col-1].getImage()==white_black[step%2 == 0 ? 1 : 0] && getSecAttack(row+2, col-2)){
                GridBoardImg[row+2][col-2].setImage(pointer);
                if(setDefaultAttackPointer(row+2, col-2, spot_of_color)){
                    GridBoardImg[row+2][col-2].setImage(pointer2);
                }
                GridAttacker[row+1][col-1]=1;
                isAttack=true;
                return true;
            }
        } catch (Exception e){};
        try{
            if(GridBoardImg[row-1][col+1].getImage()==white_black[step%2 == 0 ? 1 : 0] && getSecAttack(row-2, col+2)){
                GridBoardImg[row-2][col+2].setImage(pointer);
                if(setDefaultAttackPointer(row-2, col+2, spot_of_color)){
                    GridBoardImg[row-2][col+2].setImage(pointer2);
                }
                GridAttacker[row-1][col+1]=1;
                isAttack=true;
                return true;
            }
        } catch (Exception e){};
        try{
            if(GridBoardImg[row-1][col-1].getImage()==white_black[step%2 == 0 ? 1 : 0] && getSecAttack(row-2, col-2)){
                GridBoardImg[row-2][col-2].setImage(pointer);
                if(setDefaultAttackPointer(row-2, col-2, spot_of_color)){
                    GridBoardImg[row-2][col-2].setImage(pointer2);
                }
                GridAttacker[row-1][col-1]=1;
                isAttack=true;
                return true;
            }
        } catch (Exception e){};
        return false;
    }

    private boolean getSecAttack(int row, int col){ // умова для перегляду можливості проведення другої атаки підряд
        try {
            return GridBoardImg[row][col].getImage()==null;
        } catch (Exception e){};
        return false;
    }

    private void setDefaultStep(int row, int col, ImageView img){ // переміщує шашку на pointer
        clear_board_of_pointer();
        GridBoardImg[getRowFigure][getColFigure].setImage(null); // ставить попередній клік порожнім
        GridPosition[row][col] = GridPosition[getRowFigure][getColFigure]; // робить теперішню клітинку зайнятою
        GridPosition[getRowFigure][getColFigure]=0; // робить попередній клік порожнім
        img.setImage(white_black[step%2 == 0 ? 0 : 1]); // умова відносно кроку ставить чорну, або ж білу шашку
        delete_figure();
        step++;
        TrackPosition(GridPosition);
        isAttack=false;
    }

    private void TrackPosition(int[][] Grid){ // функція для моніторингу дошки
        for(int i[]: Grid){
            for(int j: i){
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

    private boolean thereIsNoAttack(String spot_of_color){
        if ((step%2==0 && spot_of_color.equals("White")) || step%2!=0 && spot_of_color.equals("Black")){
            int attack_figure = step%2==0 ? white : black;
            int defence_figure = attack_figure == white ? black : white;
            int row=0, col;
            for(int i[]: GridPosition){
                col=0;
                for(int j: i){
                    if(j==attack_figure){
                        try{
                            if(GridPosition[row+1][col+1]==defence_figure && GridPosition[row+2][col+2]==0){
                                return true;
                            }
                        } catch (Exception e){};
                        try{
                            if(GridPosition[row+1][col-1]==defence_figure && GridPosition[row+2][col-2]==0){
                                return true;
                            }
                        } catch (Exception e){};
                        try{
                            if(GridPosition[row-1][col+1]==defence_figure && GridPosition[row-2][col+2]==0){
                                return true;
                            }
                        } catch (Exception e){};
                        try{
                            if(GridPosition[row-1][col-1]==defence_figure && GridPosition[row-2][col-2]==0){
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

    private void delete_figure(){
        int row=0, col;
        for(int[] i: GridAttacker){
            col=0;
            for(int j: i){
                if(j==1) {
                    GridBoardImg[row][col].setImage(null);
                    GridAttacker[row][col]=0;
                    GridPosition[row][col]=0;
                }
                col++;
            }
            row++;
        }
    }
}
