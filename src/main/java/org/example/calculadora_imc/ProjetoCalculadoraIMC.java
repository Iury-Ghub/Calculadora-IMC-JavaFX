package org.example.calculadora_imc;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

public class ProjetoCalculadoraIMC extends Application {
    @Override
    public void start(Stage palco){
        Label etiquetaPeso = new Label("Peso:");
        Label etiquetaAltura = new Label("Altura:");

        TextField campoPeso = new TextField();
        campoPeso.setPromptText("Peso em Kg");
        TextField campoAltura = new TextField();
        campoAltura.setPromptText("Altura em M");

        Label etiquetaResultado = new Label();
        Label etiquetaClassificacao =new Label();

        Button botaoCalcular = getButton(campoPeso, campoAltura, etiquetaResultado, etiquetaClassificacao);

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));

        gridPane.add(etiquetaPeso,0,0);
        gridPane.add(campoPeso,1,0);
        gridPane.add(etiquetaAltura,0,1);
        gridPane.add(campoAltura,1,1);

        gridPane.add(botaoCalcular,0,2,2,1);
        gridPane.add(etiquetaResultado,0,3,2,1);
        gridPane.add(etiquetaClassificacao,0,4,2,1);

        GridPane.setHalignment(botaoCalcular, HPos.CENTER);
        GridPane.setHalignment(etiquetaResultado,HPos.CENTER);
        GridPane.setHalignment(etiquetaClassificacao,HPos.CENTER);

        Scene cena = new Scene(gridPane,300,200);
        palco.setTitle("Calculadora IMC");
        palco.setScene(cena);
        palco.show();
    }

    @NotNull
    private static Button getButton(TextField campoPeso, TextField campoAltura, Label etiquetaResultado,Label etiquetaClassificacao) {
        Button botaoCalcular = new Button("Calcular IMC");
        botaoCalcular.setOnAction(e -> {
            try {
                double peso = Double.parseDouble(campoPeso.getText());
                double altura = Double.parseDouble(campoAltura.getText());

                double imc = peso / (altura*altura);
                etiquetaResultado.setText(String.format("Seu IMC é: %.2f", imc));
                if(imc < 17) etiquetaClassificacao.setText("Muito abaixo do peso");
                else if(imc>=17 && imc<18.49) etiquetaClassificacao.setText("Abaixo do peso");
                else if(imc>=18.5 && imc<24.99) etiquetaClassificacao.setText("Peso normal");
                else if(imc>=25 && imc<29.99) etiquetaClassificacao.setText("Acima do peso");
                else if(imc>=30 && imc<34.99) etiquetaClassificacao.setText("Obesidade I");
                else if(imc>=35 && imc<39.99)etiquetaClassificacao.setText("Obesidade II");
                else etiquetaClassificacao.setText("Obesidade III");
            }catch (NumberFormatException ex){
                etiquetaResultado.setText("Insira um valor válido");
            }
        });
        return botaoCalcular;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
