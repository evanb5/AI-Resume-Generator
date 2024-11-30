package view;
import javax.swing.*;
import java.awt.event.*;
import interface_adapter.history.HistoryController;
import interface_adapter.history.HistoryPresenter;

public class HistoryView extends JPanel{
    private ViewManager viewManager;
    private HistoryController controller;
    private HistoryPresenter presenter;

    private JButton CVhistorybutton;
    private JButton refresh;
    private JButton resumehistorybutton;
    private JButton suggestionhistorybutton;
    private JButton back;
    private JTextArea CVnumbver;
    private JTextArea resumenumber;
    private JTextArea suggestionnumber;

    public HistoryView(ViewManager viewManager, HistoryPresenter presenter, HistoryController controller) {
        this.viewManager = viewManager;
        this.controller = controller;
        this.presenter = presenter;

        // Initialize components
        CVnumbver = new JTextArea(10, 2);
        CVnumbver.setEditable(false);
        resumenumber = new JTextArea(10, 2);
        resumenumber.setEditable(false);
        suggestionnumber = new JTextArea(10, 2);
        suggestionnumber.setEditable(false);
        suggestionnumber.setText("the number of suggestion created is" + presenter.getViewModel().getSuggestion());
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        CVhistorybutton = new JButton("CVHistory");
        resumehistorybutton = new JButton("ResumeHistory");
        suggestionhistorybutton = new JButton("Suggestion History");
        refresh = new JButton("Refresh");
        back = new JButton("Back");


        // Layout components
        add(CVnumbver);
        add(CVhistorybutton);
        add(resumenumber);
        add(resumehistorybutton);
        add(suggestionnumber);
        add(suggestionhistorybutton);
        add(refresh);
        add(back);

        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.historyinput();
                CVnumbver.setText("the number of CV created is" + presenter.getViewModel().getCv());
                resumenumber.setText("the number of resume created is" + presenter.getViewModel().getResume());
                suggestionnumber.setText("the number of suggestion created is" + presenter.getViewModel().getSuggestion());

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

        suggestionhistorybutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
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
        CVnumbver.setText("the number of CV created is" + presenter.getViewModel().getCv());
        resumenumber.setText("the number of resume created is" + presenter.getViewModel().getResume());
        suggestionnumber.setText("the number of suggestion created is" + presenter.getViewModel().getSuggestion());
    }

}
