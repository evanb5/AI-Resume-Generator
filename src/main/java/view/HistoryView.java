package view;
import javax.swing.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import interface_adapter.history.HistoryController;
import interface_adapter.history.HistoryPresenter;
import interface_adapter.history.HistoryState;
import interface_adapter.history.HistoryViewModel;

public class HistoryView extends JPanel implements PropertyChangeListener {
    private ViewManager viewManager;
    private HistoryController controller;
    private HistoryViewModel historyViewModel;

    private JButton CVhistorybutton;
    private JButton refresh;
    private JButton resumehistorybutton;
    private JButton back;
    private JTextArea CVnumbver;
    private JTextArea resumenumber;

    public HistoryView(ViewManager viewManager, HistoryViewModel historyViewModel, HistoryController controller) {
        this.viewManager = viewManager;
        this.controller = controller;
        this.historyViewModel = historyViewModel;
        this.historyViewModel.addPropertyChangeListener(this);


        // Initialize components
        CVnumbver = new JTextArea(10, 2);
        CVnumbver.setEditable(false);
        resumenumber = new JTextArea(10, 2);
        resumenumber.setEditable(false);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        CVhistorybutton = new JButton("Cover Letter History");
        resumehistorybutton = new JButton("Resume History");
        refresh = new JButton("Refresh");
        back = new JButton("Back");


        // Layout components
        add(CVnumbver);
        add(CVhistorybutton);
        add(resumenumber);
        add(resumehistorybutton);
        add(refresh);
        add(back);

        //Add action listeners
        CVhistorybutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewManager.showHistoryCVView();
            }
        });

        resumehistorybutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {viewManager.showPastResumesView();}
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewManager.showUserInputView();
            }
        });
    }
    public void refreshnow() {
        controller.historyinput();
        CVnumbver.setText("the number of Cover Letters created is: " + historyViewModel.getCv());
        resumenumber.setText("the number of resume created is: " + historyViewModel.getResume());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        HistoryState state = (HistoryState) evt.getNewValue();
        historyViewModel.setCv(state.getCv());
    }
}
