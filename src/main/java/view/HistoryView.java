package view;
import javax.swing.*;
import java.awt.event.*;
import interface_adapter.history.HistoryController;
import interface_adapter.history.HistoryViewModel;


public class HistoryView extends JPanel{
    private ViewManager viewManager;
    private HistoryController controller;
    private HistoryViewModel historyViewModel;

    private JButton CVhistorybutton;
    private JButton refresh;
    private JButton resumehistorybutton;
    private JButton back;
    private JTextArea CVnumbver;
    private JTextArea resumenumber;
    private JTextArea suggestionnumber;

    public HistoryView(ViewManager viewManager, HistoryViewModel historyViewModel, HistoryController controller) {
        this.viewManager = viewManager;
        this.controller = controller;
        this.historyViewModel = historyViewModel;

        // Initialize components
        CVnumbver = new JTextArea(10, 2);
        CVnumbver.setEditable(false);
        resumenumber = new JTextArea(10, 2);
        resumenumber.setEditable(false);
        suggestionnumber = new JTextArea(10, 2);
        suggestionnumber.setEditable(false);
        suggestionnumber.setText("the number of suggestion created is" + historyViewModel.getSuggestion());

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        CVhistorybutton = new JButton("CVHistory");
        resumehistorybutton = new JButton("ResumeHistory");
        refresh = new JButton("Refresh");
        back = new JButton("Back");


        // Layout components
        add(CVnumbver);
        add(CVhistorybutton);
        add(resumenumber);
        add(resumehistorybutton);
        add(suggestionnumber);
        add(refresh);
        add(back);

        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.historyinput();
                CVnumbver.setText("the number of CV created is" + historyViewModel.getCv());
                resumenumber.setText("the number of resume created is" + historyViewModel.getResume());
                suggestionnumber.setText("the number of suggestion created is" + historyViewModel.getSuggestion());

            }
        });

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
        CVnumbver.setText("the number of CV created is" + historyViewModel.getCv());
        resumenumber.setText("the number of resume created is" + historyViewModel.getResume());
        suggestionnumber.setText("the number of suggestion created is" + historyViewModel.getSuggestion());

    }

}
