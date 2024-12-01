// interface_adapter/give_suggestions/GiveSuggestionsPresenter.java
package interface_adapter.give_suggestions;

import use_case.give_suggestions.*;

public class GiveSuggestionsPresenter implements GiveSuggestionsOutputBoundary {
    private GiveSuggestionsViewModel viewModel;

    public GiveSuggestionsPresenter(GiveSuggestionsViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void present(GiveSuggestionsOutputData outputData) {
        final GiveSuggestionsState state = new GiveSuggestionsState();
        state.setSuggestions(outputData.getSuggestions());
        state.setMessage(outputData.getMessage());
        viewModel.setState(state);
        viewModel.firePropertyChanged();

        viewModel.setSuggestions(outputData.getSuggestions());
        viewModel.setMessage(outputData.getMessage());
    }

    public GiveSuggestionsViewModel getViewModel() {
        return viewModel;
    }
}
