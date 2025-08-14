package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ContentModerationController {

    @FXML
    private TableView<FlaggedComment> flaggedCommentsTable;
    @FXML
    private TableColumn<FlaggedComment, Number> idColumn;
    @FXML
    private TableColumn<FlaggedComment, String> authorColumn;
    @FXML
    private TableColumn<FlaggedComment, String> reasonColumn;
    @FXML
    private TableColumn<FlaggedComment, String> articleColumn;
    @FXML
    private TableColumn<FlaggedComment, String> timestampColumn;
    @FXML
    private TableColumn<FlaggedComment, String> commentColumn;

    @FXML
    private TextArea fullCommentTextArea;
    @FXML
    private Button keepButton; // renamed in FXML as "Keep as it is"
    @FXML
    private Button deleteButton;

    private ObservableList<FlaggedComment> flaggedComments = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Table column setup
        idColumn.setCellValueFactory(cell -> cell.getValue().idProperty());
        authorColumn.setCellValueFactory(cell -> cell.getValue().authorProperty());
        reasonColumn.setCellValueFactory(cell -> cell.getValue().reasonProperty());
        articleColumn.setCellValueFactory(cell -> cell.getValue().articleTitleProperty());
        timestampColumn.setCellValueFactory(cell -> cell.getValue().timestampProperty());
        commentColumn.setCellValueFactory(cell -> cell.getValue().commentSnippetProperty());

        // Load dummy comments
        loadDummyData();

        flaggedCommentsTable.setItems(flaggedComments);

        // Show full comment when selected
        flaggedCommentsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                fullCommentTextArea.setText(newSel.getFullComment());
            } else {
                fullCommentTextArea.clear();
            }
        });
    }

    private void loadDummyData() {
        flaggedComments.add(new FlaggedComment(1, "John Doe", "Spam", "Breaking News", "2025-08-14 09:30",
                "Check this out!", "Check this out! Visit my site for free stuff."));
        flaggedComments.add(new FlaggedComment(2, "Alice Smith", "Offensive Language", "Tech Trends", "2025-08-13 14:20",
                "I hate this...", "I hate this article. It's terrible and biased."));
        flaggedComments.add(new FlaggedComment(3, "Bob Johnson", "Irrelevant Content", "Sports Update", "2025-08-12 10:10",
                "Random stuff", "Random stuff that does not relate to the article at all."));
        flaggedComments.add(new FlaggedComment(4, "Charlie Brown", "Spam", "Health Tips", "2025-08-11 16:45",
                "Buy this now!", "Click here to get amazing health tips instantly!"));
        flaggedComments.add(new FlaggedComment(5, "Diana Prince", "Offensive Language", "Politics Today", "2025-08-10 12:30",
                "This is awful", "I can't believe this biased reporting. Shame on you!"));
        flaggedComments.add(new FlaggedComment(6, "Ethan Hunt", "Irrelevant Content", "Travel Diaries", "2025-08-09 09:00",
                "Unrelated comment", "This has nothing to do with the travel tips mentioned in the article."));
        flaggedComments.add(new FlaggedComment(7, "Fiona Gallagher", "Spam", "Entertainment Buzz", "2025-08-08 20:15",
                "Check out this offer", "Exclusive offer! Visit this link to win free concert tickets!"));
        flaggedComments.add(new FlaggedComment(8, "George Martin", "Offensive Language", "Book Reviews", "2025-08-07 11:40",
                "Terrible book", "This book is the worst I've ever read. Totally useless."));
        flaggedComments.add(new FlaggedComment(9, "Hannah Lee", "Irrelevant Content", "Science Weekly", "2025-08-06 08:25",
                "Not related", "Completely off-topic comment, doesn't belong here."));
    }

    @FXML
    private void handleKeepAsIs() { // Keep the comment
        flaggedCommentsTable.getSelectionModel().clearSelection();
        fullCommentTextArea.clear();
        System.out.println("Comment kept as is.");
    }

    @FXML
    private void handleDeleteComment() { // Delete only the comment
        FlaggedComment selected = flaggedCommentsTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            flaggedComments.remove(selected); // Removes just the comment
            fullCommentTextArea.clear();
            System.out.println("Deleted comment ID: " + selected.getId());
        }
    }
}
