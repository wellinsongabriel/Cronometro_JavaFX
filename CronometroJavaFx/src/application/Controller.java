package application;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class Controller {
	
	@FXML
	private Label lblTempo;
	
	private int segundoAtual = 0;
	private Timeline timeline;
	
	public void initialize() {
		timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> atualizarTempo()));
		timeline.setCycleCount(Timeline.INDEFINITE);
	}
	
	private void atualizarTempo() {
		segundoAtual++;
		atualizarLblTempo();
	}

	private void atualizarLblTempo() {
		int horas = segundoAtual / 3600;
		int minutos = (segundoAtual % 3600) / 60;
		int segundos = segundoAtual % 60;
		
		lblTempo.setText(String.format("%02d:%02d:%02d", horas, minutos, segundos));
		
	}

	public void iniciarPausar() {
		if (Animation.Status.STOPPED.equals(timeline.getStatus()) 
				|| Animation.Status.PAUSED.equals(timeline.getStatus())) 
			timeline.play();
		else if (Animation.Status.RUNNING.equals(timeline.getStatus())) 
			timeline.pause();
	}
	
	public void recomecar() {
		timeline.stop();
		segundoAtual = 0;
		atualizarLblTempo();
		iniciarPausar();
	}
}
