package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
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
    private TableColumn<Article, String> AuthorCol;
    @FXML
    private TableColumn<Article, String> idCol;
    @FXML
    private TableView<Article> tableView;
    @FXML
    private TableColumn<Article, String> NameCol;

    private ObservableList<Article> articles = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Set up columns
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        NameCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        AuthorCol.setCellValueFactory(new PropertyValueFactory<>("author"));

        // Load articles from reviewed_articles.bin (binary file)
        loadArticlesFromBinaryFile("reviewed_articles.bin");

        // Set articles to table
        tableView.setItems(articles);

        // Show content when a row is selected
        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                articleTextArea.setText(newSelection.getContent());
                grammarResultArea.clear();
                ethicalResultArea.clear();
                qualityResultArea.clear();
            }
        });
    }

    private void loadArticlesFromBinaryFile(String filePath) {
        articles.clear();
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("File " + filePath + " not found.");
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = ois.readObject();

            System.out.println("Object read from file: " + obj.getClass().getName());

            if (obj instanceof List<?>) {
                List<?> list = (List<?>) obj;
                System.out.println("List size: " + list.size());
                for (Object item : list) {
                    if (item instanceof Article) {
                        System.out.println("Adding article: " + ((Article) item).getTitle());
                        articles.add((Article) item);
                    } else {
                        System.out.println("Found non-Article item: " + item.getClass().getName());
                    }
                }
            } else {
                System.out.println("Object is not a List, but: " + obj.getClass().getName());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
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

    private String checkGrammar(String text) {
        StringBuilder result = new StringBuilder();
        if (!Character.isUpperCase(text.charAt(0))) {
            result.append("• The first letter should be capitalized.\n");
        }

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

        if (text.contains("  ")) {
            result.append("• Found double spaces — consider fixing.\n");
        }

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

    private String checkQuality(String text) {
        int wordCount = text.split("\\s+").length;
        String[] sentences = text.split("[.!?]+");
        int sentenceCount = sentences.length;

        StringBuilder quality = new StringBuilder();

        if (wordCount < 2000) {
            quality.append("• Article is too short (")
                    .append(wordCount)
                    .append(" words). Minimum 2000 words required.\n");
        }

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

        if (sentenceCount == 0 || sentences[0].trim().length() < 5) {
            quality.append("• Article may lack meaningful sentences.\n");
        }

        return quality.length() > 0 ? quality.toString() : "✅ Content quality looks good.";
    }

    @FXML
    public void editOnAction(ActionEvent actionEvent) {
        articleTextArea.setEditable(true);
        articleTextArea.requestFocus();
    }

    @FXML
    public void RejectOnAction(ActionEvent actionEvent) {
        // do nothing
    }

    @FXML
    public void ApproveOnAction(ActionEvent actionEvent) {
        Article selected = tableView.getSelectionModel().getSelectedItem();

        if (selected != null) {
            // Read existing approved articles
            List<Article> approvedArticles = readApprovedArticles();

            // Check if already approved to avoid duplicates (optional)
            boolean alreadyApproved = approvedArticles.stream()
                    .anyMatch(a -> a.getId().equals(selected.getId()));

            if (!alreadyApproved) {
                approvedArticles.add(selected);
                writeApprovedArticles(approvedArticles);
            }
        }
    }

    private List<Article> readApprovedArticles() {
        File file = new File("approved.bin");
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = ois.readObject();
            if (obj instanceof List<?>) {
                List<?> list = (List<?>) obj;
                List<Article> approvedList = new ArrayList<>();
                for (Object item : list) {
                    if (item instanceof Article) {
                        approvedList.add((Article) item);
                    }
                }
                return approvedList;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private void writeApprovedArticles(List<Article> approvedArticles) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("approved.bin"))) {
            oos.writeObject(approvedArticles);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void backOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/oop/group20/group20_simulationofnewspaperonlineprinted/Muaaz/EditorInChiefGoal1.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Dashboard");
        stage.show();
    }

    @FXML
    public void nextOnAction(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/oop/group20/group20_simulationofnewspaperonlineprinted/Muaaz/EditorInChiefGoal2.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Dashboard");
        stage.show();
    }
}
