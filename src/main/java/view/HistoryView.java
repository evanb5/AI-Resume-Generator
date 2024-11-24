package view;
import javax.swing.*;
import java.awt.event.*;
import interface_adapter.history.HistoryPresenter;
import interface_adapter.history.HistoryViewModel;
import session.UserSession;

public class HistoryView extends JPanel{
    private ViewManager viewManager;
    public HistoryPresenter presenter;

    private JButton CVhistorybutton;
    private JButton resumehistorybutton;
    private JButton suggestionhistorybutton;
    private JTextArea CVnumbver;
    private JTextArea resumenumber;
    private JTextArea suggestionnumber;

    HistoryViewModel viewModel = presenter.getViewModel();


    public HistoryView(ViewManager viewManager, HistoryPresenter presenter) {
        this.viewManager = viewManager;
        this.presenter = presenter;

        // Initialize components
        CVnumbver = new JTextArea(10,2);
        CVnumbver.setEditable(false);
        resumenumber = new JTextArea(10,2);
        resumenumber.setEditable(false);
        suggestionnumber = new JTextArea(10,2);
        suggestionnumber.setEditable(false);
        CVhistorybutton = new JButton("CVHistory");
        resumehistorybutton = new JButton("ResumeHistory");
        suggestionhistorybutton = new JButton("SuggestionHistory");

        // Layout components
        add(CVnumbver);
        add(resumenumber);
        add(suggestionnumber);
        add(CVhistorybutton);
        add(resumehistorybutton);
        add(suggestionhistorybutton);

        CVnumbver.setText("the number of CV created is" + viewModel.getCv());
        resumenumber.setText("the number of resume created is" + viewModel.getResume());
        suggestionnumber.setText("the number of suggestion created is" + viewModel.getSuggestion());

        //Add action listeners
        CVhistorybutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        resumehistorybutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        suggestionhistorybutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
    }
}
