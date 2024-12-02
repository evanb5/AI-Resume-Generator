package view;

import interface_adapter.CVhistory.CVhistoryController;
import interface_adapter.CVhistory.CVhistoryPresenter;
import interface_adapter.CVhistory.CVhistoryState;
import interface_adapter.CVhistory.CVhistoryViewModel;
import use_case.CVhistory.CVhistoryInputData;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class CVHistoryView  extends JPanel implements PropertyChangeListener {
    private ViewManager viewmanager;
    private CVhistoryController controller;
    private CVhistoryViewModel cvhistoryViewModel;

    private JButton backButton;
    private JTextArea DisplayArea;
    private JButton NextButton;
    private JButton PreviousButton;
    private int index_pointer;
    private int CV_num;

    public CVHistoryView(ViewManager viewManager, CVhistoryController controller,  CVhistoryViewModel cvhistoryViewModel) {
        this.viewmanager = viewmanager;
        this.controller = controller;
        this.cvhistoryViewModel = cvhistoryViewModel;
        this.cvhistoryViewModel.addPropertyChangeListener(this);
        this.index_pointer = -1;
        this.CV_num = 0;

        backButton = new JButton("Back");
        NextButton = new JButton("Next");
        PreviousButton = new JButton("Previous");
        DisplayArea = new JTextArea(20,50);
        DisplayArea.setEditable(false);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewManager.showHistoryView();
            }
        });

        NextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (getCV_num() != 0 && getindex_pointer() < getCV_num() - 1 && getindex_pointer() > 0) {
                    controller.CVhistory(new CVhistoryInputData(getindex_pointer()+1));
                    DisplayArea.setText(cvhistoryViewModel.getHistoryCV());
                    setindex_pointer(getindex_pointer()+1);
                }
            }
        });

        PreviousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (getCV_num() != 0 && getindex_pointer() > 0) {
                    controller.CVhistory(new CVhistoryInputData(getindex_pointer()-1));
                    DisplayArea.setText(cvhistoryViewModel.getHistoryCV());
                    setindex_pointer(getindex_pointer()-1);
                }
            }
        });
    }

    public void setindex_pointer(int index_pointer) {
        this.index_pointer = index_pointer;
    }

    public int getindex_pointer() {
        return index_pointer;
    }

    public void setCV_num(int CV_num) {
        this.CV_num = CV_num;
    }

    public int getCV_num() {
        return CV_num;
    }

    public void refreshcv() {
        removeAll();
        controller.CVhistory(new CVhistoryInputData(-1));
        setindex_pointer(-1);
        List<String> keys = new ArrayList<>(cvhistoryViewModel.getTitles());
        if (keys.isEmpty()) {
            setCV_num(0);
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            add(DisplayArea);
            add(backButton);

            DisplayArea.setText("You havent generated any Cover Letters yet");
        }else {
            JPanel titlrPanel = new JPanel();
            titlrPanel.setLayout(new BoxLayout(titlrPanel, BoxLayout.X_AXIS));
            setCV_num(cvhistoryViewModel.getTitles().size());
            for (int i = 0; i < cvhistoryViewModel.getTitles().size(); i++) {
                JButton button = new JButton(keys.get(i));
                int inner = i;
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        controller.CVhistory(new CVhistoryInputData(inner));
                        DisplayArea.setText(cvhistoryViewModel.getHistoryCV());
                        setindex_pointer(inner);
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

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        CVhistoryState state = (CVhistoryState) evt.getNewValue();
        cvhistoryViewModel.setState(state);
    }
}
