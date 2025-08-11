package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CheckController {

    @FXML
    private TextArea articleTextArea;

    @FXML
    private TextArea grammarResultArea;

    @FXML
    private TextArea ethicalResultArea;

    @FXML
    private TextArea qualityResultArea;

    private static final Set<String> offensiveWords = new HashSet<>(Arrays.asList(
            "hate", "kill", "stupid", "idiot", "racist", "terrorist", "violence",
            "abuse", "drugs", "suicide", "bomb", "assault", "sexist", "homophobic",
            "nazi", "slur", "insult", "fraud", "cheat", "corrupt", "bribe"
    ));

    @FXML
    private TableColumn<checkArticle, String> AuthorCol;
    @FXML
    private TableColumn<checkArticle, String> idCol;
    @FXML
    private TableView<checkArticle> tableView;
    @FXML
    private TableColumn<checkArticle, String> NameCol;

    private ObservableList<checkArticle> articles = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Set up columns to use checkArticle properties
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        NameCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        AuthorCol.setCellValueFactory(new PropertyValueFactory<>("author"));

        // Load articles from file
        loadArticlesFromFile();

        // Set articles in the table
        tableView.setItems(articles);

        // Add listener to display full article content on selection
        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                articleTextArea.setText(newSelection.getContent());
                // Clear previous results when article changes
                grammarResultArea.clear();
                ethicalResultArea.clear();
                qualityResultArea.clear();
            }
        });
    }

    @FXML
    private void handleCheckArticle() {
        String article = articleTextArea.getText().trim();

        if (article.isEmpty()) {
            grammarResultArea.setText("⚠ Please enter an article.");
            ethicalResultArea.clear();
            qualityResultArea.clear();
            return;
        }

        grammarResultArea.setText(checkGrammar(article));
        ethicalResultArea.setText(checkEthics(article));
        qualityResultArea.setText(checkQuality(article));
    }

    /**
     * Basic grammar check (very simplified).
     */
    private String checkGrammar(String text) {
        StringBuilder result = new StringBuilder();

        // 1. Capitalization check for first letter of the text
        if (!Character.isUpperCase(text.charAt(0))) {
            result.append("• The first letter should be capitalized.\n");
        }

        // 2. Sentence ending punctuation check
        String[] sentences = text.split("(?<=[.!?])\\s+");
        int missingPunctuation = 0;
        for (String s : sentences) {
            String trimmed = s.trim();
            if (trimmed.isEmpty()) continue;
            char lastChar = trimmed.charAt(trimmed.length() - 1);
            if (lastChar != '.' && lastChar != '!' && lastChar != '?') {
                missingPunctuation++;
            }
        }
        if (missingPunctuation > 0) {
            result.append("• ").append(missingPunctuation)
                    .append(" sentence(s) may be missing ending punctuation.\n");
        }

        // 3. Simple double space check
        if (text.contains("  ")) {
            result.append("• Found double spaces — consider fixing.\n");
        }

        // 4. Check that all words are English-like (letters, apostrophes, hyphens)
        String[] words = text.split("\\s+");
        int nonEnglishWords = 0;
        for (String word : words) {
            if (!word.matches("[a-zA-Z'-]+")) {
                nonEnglishWords++;
            }
        }
        if (nonEnglishWords > 0) {
            result.append("• ").append(nonEnglishWords).append(" word(s) contain non-English characters or symbols.\n");
        }

        return result.length() > 0 ? result.toString() : "✅ No major grammar issues found.";
    }

    /**
     * Basic ethical content check.
     */
    private String checkEthics(String text) {
        StringBuilder issues = new StringBuilder();
        String lowerText = text.toLowerCase();

        for (String word : offensiveWords) {
            if (lowerText.contains(word)) {
                issues.append("• Contains potentially harmful or offensive word: ").append(word).append("\n");
            }
        }

        return issues.length() > 0
                ? issues.toString()
                : "✅ No harmful or unethical language detected.";
    }

    /**
     * Basic content quality check.
     */
    private String checkQuality(String text) {
        int wordCount = text.split("\\s+").length;
        String[] sentences = text.split("[.!?]+");
        int sentenceCount = sentences.length;

        StringBuilder quality = new StringBuilder();

        // Word count minimum check
        if (wordCount < 2000) {
            quality.append("• Article is too short (")
                    .append(wordCount)
                    .append(" words). Minimum 2000 words required.\n");
        }

        // Average sentence length check for meaningful sentences
        int shortSentences = 0;
        int longSentences = 0;
        for (String s : sentences) {
            String trimmed = s.trim();
            int length = trimmed.split("\\s+").length;
            if (length < 3) shortSentences++;
            if (length > 40) longSentences++;
        }
        if (shortSentences > 0) {
            quality.append("• ").append(shortSentences).append(" sentence(s) are very short; consider expanding for clarity.\n");
        }
        if (longSentences > 0) {
            quality.append("• ").append(longSentences).append(" sentence(s) are very long; consider breaking them up.\n");
        }

        // Check for presence of at least one meaningful sentence (basic heuristic)
        if (sentenceCount == 0 || sentences[0].trim().length() < 5) {
            quality.append("• Article may lack meaningful sentences.\n");
        }

        return quality.length() > 0 ? quality.toString() : "✅ Content quality looks good.";
    }

    private void loadArticlesFromFile() {
        String filePath = "reviewed_articles.txt"; // Adjust path if needed

        try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // split into exactly 7 parts (id, title, author, category, publishDate, content, status)
                String[] parts = line.split("\\|", 7);

                if (parts.length == 7) {
                    String id = parts[0].trim();
                    String title = parts[1].trim();
                    String author = parts[2].trim();
                    String category = parts[3].trim();     // ignored for checkArticle
                    String publishDate = parts[4].trim();  // ignored for checkArticle
                    String content = parts[5].trim();
                    String status = parts[6].trim();       // ignored for checkArticle

                    articles.add(new checkArticle(id, title, author, content));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Placeholders for buttons, implement as needed
    @FXML
    public void editOnAction(ActionEvent actionEvent) {
        articleTextArea.setEditable(true);
        articleTextArea.requestFocus();
    }


    @FXML
    public void RejectOnAction(ActionEvent actionEvent) {
        // Do nothing
    }


    @FXML
    public void ApproveOnAction(ActionEvent actionEvent) {
        String content = articleTextArea.getText().trim();
        if (!content.isEmpty()) {
            try {
                // Append the approved article content to approved.txt, with a separator for clarity
                Files.write(Paths.get("approved.txt"),
                        (content + System.lineSeparator() + "--------------------------" + System.lineSeparator()).getBytes(),
                        java.nio.file.StandardOpenOption.CREATE,
                        java.nio.file.StandardOpenOption.APPEND);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}