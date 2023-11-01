package com.borges.view;


import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.Optional;

public class BorgesAppController {
    @FXML
    private ComboBox<?> cbLoja;

    @FXML
    private DatePicker dtDatePicker;

    @FXML
    private Label lblDetailNumberNota;

    @FXML
    private TextField txtName;

    @FXML
    private VBox pnlPrincipal;

    @FXML
    private AnchorPane AncherBottom;

    @FXML
    private Button btnClear;

    @FXML
    private Label lblDetailLoja;

    @FXML
    private Button btnConfirm;

    @FXML
    private Label lblDetailDataEmissao;

    @FXML
    private Label lblName;

    @FXML
    private TextField txtChaveDanfe;

    @FXML
    private AnchorPane pnlTopPart;

    @FXML
    private Label lblDetailCnpjFornecedor;

    @FXML
    private Label lblDetailChaveDanfe;

    @FXML
    private Label lblDetailNotaFiscal;

    @FXML
    private Label lblLoja;

    @FXML
    private Label lblChaveDanfe;

    @FXML
    private Label lblDate;

    @FXML
    private SplitPane pnlSplitPlane;

    @FXML
    private Label lblMarca;

    @FXML
    private ComboBox<?> cbMarca;

    @FXML
    private Font x1;

    @FXML
    private HBox pnlBottom;

    @FXML
    private CheckBox checkDanfe;


    public static boolean onCloseQuery() {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Pergunta");
        alerta.setHeaderText("Deseja Sair?");
        ButtonType botaoNao = ButtonType.NO;
        ButtonType botaoSim = ButtonType.YES;
        alerta.getButtonTypes().setAll(botaoSim, botaoNao);
        Optional<ButtonType> opcaoClicada = alerta.showAndWait();

        return opcaoClicada.get() == botaoSim;

    }
}







