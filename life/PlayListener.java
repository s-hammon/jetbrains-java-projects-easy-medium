package life;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class PlayListener implements ItemListener {

    private GameOfLife view;
    private Timer timer;

    @Override
    public void itemStateChanged(ItemEvent itemEvent) {
        int state = itemEvent.getStateChange();

        if (state == itemEvent.SELECTED) {
            view.setPlayState(true);
            timer.start();
        } else {
            view.setPlayState(false);
            timer.stop();
        }
    }
}
