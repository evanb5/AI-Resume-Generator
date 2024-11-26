package view;

import interface_adapter.CVhistory.CVhistoryController;
import interface_adapter.CVhistory.CVhistoryPresenter;
import use_case.CVhistory.CVhistoryInputData;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CVHistoryView  extends JPanel {
    private ViewManager viewmanager;
    private CVhistoryController controller;
    private CVhistoryPresenter presenter;

    private JButton backButton;
    private JTextArea DisplayArea;
    private JButton NextButton;
    private JButton PreviousButton;

    public CVHistoryView(ViewManager viewManager, CVhistoryController controller, CVhistoryPresenter presenter) {
        this.viewmanager = viewmanager;
        this.controller = controller;
        this.presenter = presenter;

        backButton = new JButton("Back");
        NextButton = new JButton("Next");
        PreviousButton = new JButton("Previous");
        DisplayArea = new JTextArea(20,50);
        DisplayArea.setEditable(false);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewManager.showUserInputView();
            }
        });

        NextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.CVhistory(new CVhistoryInputData(-3));
                DisplayArea.setText(presenter.getViewModel().getHistoryCV());
            }
        });

        PreviousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.CVhistory(new CVhistoryInputData(-2));
                DisplayArea.setText(presenter.getViewModel().getHistoryCV());
            }
        });
    }

    public void refreshcv() {
        removeAll();
        controller.CVhistory(new CVhistoryInputData(-4));
        List<String> keys = new ArrayList<>(presenter.getViewModel().getTitles());
        if (keys.isEmpty()) {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            add(DisplayArea);
            add(backButton);

            DisplayArea.setText("You havent produce any CV yet");
        }else {
            JPanel titlrPanel = new JPanel();
            titlrPanel.setLayout(new BoxLayout(titlrPanel, BoxLayout.X_AXIS));
            for (int i = 0; i < presenter.getViewModel().getTitles().size(); i++) {
                JButton button = new JButton(keys.get(i));
                int I = i;
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        controller.CVhistory(new CVhistoryInputData(I));
                        DisplayArea.setText(presenter.getViewModel().getHistoryCV());
                    }
                });
                titlrPanel.add(button);
            }
            add(titlrPanel);
            add(new JScrollPane(DisplayArea));
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
            buttonPanel.add(PreviousButton);
            buttonPanel.add(NextButton);
            add(buttonPanel);
            add(backButton);
            DisplayArea.setText("");
        }

    }
}
