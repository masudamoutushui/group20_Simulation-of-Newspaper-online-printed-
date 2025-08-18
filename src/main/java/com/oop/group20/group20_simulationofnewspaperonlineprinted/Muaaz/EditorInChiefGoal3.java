package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EditorInChiefGoal3 {

    @FXML
    private TableView<Request> requestsTable;

    @FXML
    private TableColumn<Request, String> colId;

    @FXML
    private TableColumn<Request, String> colType;

    @FXML
    private TableColumn<Request, String> colAdvertiser;

    @FXML
    private TableColumn<Request, String> colStatus;

    @FXML
    private TextArea contentPreview;

    @FXML
    private ImageView adMockupView;

    @FXML
    private TextArea feedbackArea;

    @FXML
    private Button approveBtn;

    @FXML
    private Button rejectBtn;

    private final ObservableList<Request> requests = FXCollections.observableArrayList();

    private RegisteredUser user;
    @FXML
    private TextField Idinput;
    @FXML
    private TextField typeInput;
    @FXML
    private TextField advertiserinput;
    @FXML
    private TextField statusInput;
    @FXML
    private TableView<Advertisertest> advertisertsttableview;

    @FXML
    private TableColumn<Advertisertest, String> idcol;
    @FXML
    private TableColumn<Advertisertest, String> typecol;
    @FXML
    private TableColumn<Advertisertest, String> advertisercol;
    @FXML
    private TableColumn<Advertisertest, String> statuscol;
    @FXML
    private TextArea showfullAdvertiseproposal;


    public void setUser(RegisteredUser user) {
        this.user = user;
    }

    @FXML
    public void initialize() {
        // Initialize columns
        colId.setCellValueFactory(data -> data.getValue().idProperty());
        colType.setCellValueFactory(data -> data.getValue().typeProperty());
        colAdvertiser.setCellValueFactory(data -> data.getValue().advertiserProperty());
        colStatus.setCellValueFactory(data -> data.getValue().statusProperty());

        Image adSampleImage = new Image(getClass().getResourceAsStream(
                "/com/oop/group20/group20_simulationofnewspaperonlineprinted/Muaaz/ad pic.jpeg"));

        // Create an ArrayList for easier bulk addition
        List<Request> sampleRequests = new ArrayList<>();

        // Existing 12 requests
        sampleRequests.add(new Request("001", "Sponsored Article", "Acme Corp", "Pending",
                "This is a sponsored article about Acme's new product.", null));
        sampleRequests.add(new Request("002", "Ad Placement", "Beta Inc", "Pending",
                null, adSampleImage));
        sampleRequests.add(new Request("003", "Sponsored Article", "Gamma LLC", "Pending",
                "Sponsored content on Gamma's innovative solutions.", null));
        sampleRequests.add(new Request("004", "Ad Placement", "Delta Co", "Approved",
                null, adSampleImage));
        sampleRequests.add(new Request("005", "Sponsored Article", "Epsilon Ltd", "Rejected",
                "Article about Epsilon's sustainability efforts.", null));
        sampleRequests.add(new Request("006", "Ad Placement", "Zeta Enterprises", "Pending",
                null, null));
        sampleRequests.add(new Request("007", "Sponsored Article", "Eta Group", "Pending",
                "Sponsored interview with Eta's CEO.", null));
        sampleRequests.add(new Request("008", "Ad Placement", "Theta Media", "Approved",
                null, adSampleImage));
        sampleRequests.add(new Request("009", "Sponsored Article", "Iota Partners", "Pending",
                "A deep dive into Iota's market strategy.", null));
        sampleRequests.add(new Request("010", "Ad Placement", "Kappa Advertising", "Rejected",
                null, null));
        sampleRequests.add(new Request("011", "Sponsored Article", "Lambda Inc", "Pending",
                "Sponsored report on Lambda’s new tech.", null));
        sampleRequests.add(new Request("012", "Ad Placement", "Mu Marketing", "Pending",
                null, adSampleImage));

        // Extra 10 sample requests
        sampleRequests.add(new Request("013", "Banner Ad", "Nu Designs", "Pending",
                "Banner ad campaign for Nu Designs summer sale.", null));
        sampleRequests.add(new Request("014", "Sponsored Article", "Xi Clothing", "Pending",
                "Sponsored style guide featuring Xi Clothing's new line.", null));
        sampleRequests.add(new Request("015", "Ad Placement", "Omicron Digital", "Pending",
                null, adSampleImage));
        sampleRequests.add(new Request("016", "Video Ad", "Pi Productions", "Pending",
                "30-second TV commercial for Pi Productions.", null));
        sampleRequests.add(new Request("017", "Sponsored Article", "Rho Realty", "Approved",
                "Article highlighting Rho Realty's premium listings.", null));
        sampleRequests.add(new Request("018", "Ad Placement", "Sigma Services", "Rejected",
                null, adSampleImage));
        sampleRequests.add(new Request("019", "Event Promotion", "Tau Events", "Pending",
                "Promotional post for Tau’s annual festival.", null));
        sampleRequests.add(new Request("020", "Sponsored Article", "Upsilon Energy", "Pending",
                "Sponsored feature on Upsilon’s green energy solutions.", null));
        sampleRequests.add(new Request("021", "Ad Placement", "Phi Motors", "Pending",
                null, adSampleImage));
        sampleRequests.add(new Request("022", "Podcast Sponsorship", "Chi Media", "Pending",
                "Sponsorship deal for Chi Media’s new business podcast.", null));

        // Add to observable list
        requests.addAll(sampleRequests);
        requestsTable.setItems(requests);

        idcol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("id"));
        typecol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("type"));
        advertisercol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("advertiser"));
        statuscol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("status"));

// Load existing ads into table
        List<Advertisertest> adsFromFile = loadAdsFromFile();
        advertisertst.setAll(adsFromFile);
        advertisertsttableview.setItems(advertisertst);



        refreshAdsView(advertisertst);
        // Selection listener
        requestsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            showRequestDetails(newSel);
        });

        // Disable buttons until selection is made
        approveBtn.setDisable(true);
        rejectBtn.setDisable(true);
    }
    private void refreshAdsView(List<Advertisertest> ads){
        StringBuilder adDetails = new StringBuilder();

        for (Advertisertest ad: ads){
            adDetails.append("Ad Title: ").append(ad.getId()).append("\n");
            adDetails.append("Ad Description: ").append(ad.getAdvertiser()).append("\n");
            adDetails.append("Ad Date: ").append(ad.getStatus()).append("\n");
            adDetails.append("Ad Date: ").append(ad.getType()).append("\n");
            adDetails.append("-------------------------\n");
        }


        showfullAdvertiseproposal.setText(adDetails.toString());
    }






    private void showRequestDetails(Request req) {
        if (req == null) {
            contentPreview.setVisible(false);
            adMockupView.setVisible(false);
            feedbackArea.clear();
            approveBtn.setDisable(true);
            rejectBtn.setDisable(true);
            return;
        }

        approveBtn.setDisable(false);
        rejectBtn.setDisable(false);

        if ("Sponsored Article".equals(req.getType())) {
            contentPreview.setText(req.getContent() != null ? req.getContent() : "");
            contentPreview.setVisible(true);
            adMockupView.setVisible(false);
            adMockupView.setImage(null);
        } else if ("Ad Placement".equals(req.getType()) || "Banner Ad".equals(req.getType())) {
            adMockupView.setImage(req.getAdImage());
            adMockupView.setVisible(true);
            contentPreview.setVisible(false);
            contentPreview.clear();
        } else {
            contentPreview.setVisible(false);
            contentPreview.clear();
            adMockupView.setVisible(false);
            adMockupView.setImage(null);
        }

        feedbackArea.clear();
    }

    @FXML
    private void onApprove() {
        Request selected = requestsTable.getSelectionModel().getSelectedItem();
        if (selected == null) return;
        selected.setStatus("Approved");
        disableButtonsAndClearFeedback();
        requestsTable.refresh();
    }

    @FXML
    private void onReject() {
        Request selected = requestsTable.getSelectionModel().getSelectedItem();
        if (selected == null) return;
        selected.setStatus("Rejected");
        disableButtonsAndClearFeedback();
        requestsTable.refresh();
    }

    private void disableButtonsAndClearFeedback() {
        approveBtn.setDisable(true);
        rejectBtn.setDisable(true);
        feedbackArea.clear();
        contentPreview.setVisible(false);
        adMockupView.setVisible(false);
        adMockupView.setImage(null);
    }

    @FXML
    public void handleback(ActionEvent event) throws IOException {
        if (user == null) {
            System.err.println("Error: No logged-in user to pass back.");
            return;
        }

        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        UserDetailsController.openWithUser(user, stage);
    }

    ObservableList<Advertisertest> advertisertst = FXCollections.observableArrayList();

    private static final String advertisementtestfile = "advertisetest.bin";

    @FXML
    public void addadvertiserOnClick(ActionEvent actionEvent) throws IOException {

        String id = Idinput.getText();
        String type = typeInput.getText();
        String advertiser = advertiserinput.getText();
        String status = statusInput.getText();

        Advertisertest ad = new Advertisertest(id, type, advertiser, status);
        List<Advertisertest> allAds = loadAdsFromFile();
        allAds.add(ad);

        saveAdsToFile(allAds);
        advertisertst.setAll(allAds);
        Idinput.clear();
        typeInput.clear();
        advertiserinput.clear();
        statusInput.clear();
    }

    // ------------------- Read/Write Binary File Section -------------------
// This section handles reading and writing the list of Advertisertest objects
// to a binary file using ObjectInputStream and ObjectOutputStream.
// We use this to persist data across program runs and retrieve it later.
    private List<Advertisertest> loadAdsFromFile() {
         List<Advertisertest> ads = new ArrayList<>();

         try(ObjectInputStream ois = new ObjectInputStream(new java.io.FileInputStream(advertisementtestfile))){
             ads = (List<Advertisertest>) ois.readObject();

         }catch ( IOException | ClassNotFoundException e){

        }

         return ads;

    }

    private void saveAdsToFile( List<Advertisertest> ads) throws IOException {
        try(ObjectOutputStream oos = new ObjectOutputStream(new java.io.FileOutputStream(advertisementtestfile))){
            oos.writeObject(ads);

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }


    // Request model
    public static class Request {
        private final javafx.beans.property.SimpleStringProperty id;
        private final javafx.beans.property.SimpleStringProperty type;
        private final javafx.beans.property.SimpleStringProperty advertiser;
        private final javafx.beans.property.SimpleStringProperty status;

        private final String content;
        private final Image adImage;

        public Request(String id, String type, String advertiser, String status, String content, Image adImage) {
            this.id = new javafx.beans.property.SimpleStringProperty(id);
            this.type = new javafx.beans.property.SimpleStringProperty(type);
            this.advertiser = new javafx.beans.property.SimpleStringProperty(advertiser);
            this.status = new javafx.beans.property.SimpleStringProperty(status);
            this.content = content;
            this.adImage = adImage;
        }

        public String getId() {
            return id.get();
        }

        public javafx.beans.property.StringProperty idProperty() {
            return id;
        }

        public String getType() {
            return type.get();
        }

        public javafx.beans.property.StringProperty typeProperty() {
            return type;
        }

        public String getAdvertiser() {
            return advertiser.get();
        }

        public javafx.beans.property.StringProperty advertiserProperty() {
            return advertiser;
        }

        public String getStatus() {
            return status.get();
        }

        public void setStatus(String newStatus) {
            status.set(newStatus);
        }

        public javafx.beans.property.StringProperty statusProperty() {
            return status;
        }

        public String getContent() {
            return content;
        }

        public Image getAdImage() {
            return adImage;
        }
    }

}