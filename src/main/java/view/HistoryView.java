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
        CVnumbver = new JTextArea(2,1);
        resumenumber = new JTextArea(2,1);
        suggestionnumber = new JTextArea(2,1);
        CVhistorybutton = new JButton("CVHistory");
        resumehistorybutton = new JButton("ResumeHistory");
        suggestionhistorybutton = new JButton("SuggestionHistory");

        // Layout components
        add(new JLabel("number of CV created"));
        add(CVhistorybutton);
        add(resumehistorybutton);
        add(suggestionhistorybutton);

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
