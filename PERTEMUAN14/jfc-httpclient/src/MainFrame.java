import org.apache.hc.client5.http.async.methods.*;
import org.apache.hc.client5.http.impl.async.CloseableHttpAsyncClient;
import org.apache.hc.client5.http.impl.async.HttpAsyncClients;
import org.apache.hc.core5.concurrent.FutureCallback;
import org.apache.hc.core5.http.HttpHost;
import org.apache.hc.core5.http.message.StatusLine;
import org.apache.hc.core5.io.CloseMode;
import org.apache.hc.core5.reactor.IOReactorConfig;
import org.apache.hc.core5.util.Timeout;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame {
    public static void main(String[] args) {
        final IOReactorConfig ioReactorConfig = IOReactorConfig.custom()
                .setSoTimeout(Timeout.ofSeconds(5))
                .build();

        final CloseableHttpAsyncClient client = HttpAsyncClients.custom()
                .setIOReactorConfig(ioReactorConfig)
                .build();

        client.start();

        final HttpHost target = new HttpHost("672fbf9066e42ceaf15e9a9b.mockapi.io");
        final String requestUrl = "/api/contacts";

        SwingUtilities.invokeLater(() -> {
            // Create main frame
            JFrame frame = new JFrame("HTTP Client Example");
            frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            frame.setSize(600, 400);
            frame.setLayout(new BorderLayout(10, 10));

            // Create components
            JLabel statusLabel = new JLabel("Press button to start downloading data", JLabel.CENTER);
            JButton startButton = new JButton("Start");
            JProgressBar progressBar = new JProgressBar(0, 100);
            JTextArea textArea = new JTextArea();
            textArea.setEditable(false);

            // Setup components
            JScrollPane scrollPane = new JScrollPane(textArea);
            JPanel buttonPanel = new JPanel(new FlowLayout());
            buttonPanel.add(startButton);
            buttonPanel.add(progressBar);

            // Add components to frame
            frame.add(statusLabel, BorderLayout.NORTH);
            frame.add(scrollPane, BorderLayout.CENTER);
            frame.add(buttonPanel, BorderLayout.SOUTH);

            // Setup window listener
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    client.close(CloseMode.GRACEFUL);
                    System.exit(0);
                }
            });

            // Create HTTP request
            final SimpleHttpRequest request = SimpleRequestBuilder.get()
                    .setHttpHost(target)
                    .setPath(requestUrl)
                    .build();

            // Setup button action
            startButton.addActionListener(e -> {
                progressBar.setIndeterminate(true);
                startButton.setEnabled(false);
                statusLabel.setText("Processing...");
                textArea.setText("");

                client.execute(
                    SimpleRequestProducer.create(request),
                    SimpleResponseConsumer.create(),
                    new FutureCallback<SimpleHttpResponse>() {
                        @Override
                        public void completed(final SimpleHttpResponse response) {
                            SwingUtilities.invokeLater(() -> {
                                try {
                                    JSONParser parser = new JSONParser();
                                    JSONArray contacts = (JSONArray) parser.parse(response.getBodyText());
                                    
                                    contacts.forEach(obj -> {
                                        JSONObject contact = (JSONObject) obj;
                                        String line = String.format("Name: %s, Phone: %s%n", 
                                            contact.get("name"), contact.get("phone"));
                                        textArea.append(line);
                                    });

                                    progressBar.setIndeterminate(false);
                                    startButton.setEnabled(true);
                                    statusLabel.setText("Process completed");
                                } catch (ParseException ex) {
                                    handleError("Error parsing JSON: " + ex.getMessage());
                                }
                            });
                        }

                        @Override
                        public void failed(final Exception ex) {
                            SwingUtilities.invokeLater(() -> 
                                handleError("Process failed: " + ex.getMessage())
                            );
                        }

                        @Override
                        public void cancelled() {
                            SwingUtilities.invokeLater(() -> {
                                progressBar.setIndeterminate(false);
                                startButton.setEnabled(true);
                                statusLabel.setText("Process cancelled");
                            });
                        }

                        private void handleError(String message) {
                            progressBar.setIndeterminate(false);
                            startButton.setEnabled(true);
                            statusLabel.setText(message);
                            textArea.append("Error: " + message + "\n");
                        }
                    }
                );
            });

            // Show frame
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}