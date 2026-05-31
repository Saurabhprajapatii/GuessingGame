import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class TaskManagerPro extends JFrame {

    private JTextField taskField;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;

    private JLabel statusLabel;
    private JLabel countLabel;

    private DefaultListModel<String> listModel;
    private JList<String> taskList;

    private ArrayList<String> tasks;

    private static final String FILE_NAME = "tasks.txt";

    public TaskManagerPro() {

        tasks = new ArrayList<>();

        setTitle("Task Manager Pro");
        setSize(600, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initializeUI();

        loadTasks();

        setVisible(true);
    }

    private void initializeUI() {

        setLayout(new BorderLayout(10, 10));

        // Title
        JLabel titleLabel = new JLabel(
                "TASK MANAGER PRO",
                SwingConstants.CENTER);

        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        add(titleLabel, BorderLayout.NORTH);

        // Center Panel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout(10, 10));

        // Input Panel
        JPanel inputPanel = new JPanel(new BorderLayout(10, 10));

        JLabel taskLabel = new JLabel("Task:");

        taskField = new JTextField();

        inputPanel.add(taskLabel, BorderLayout.WEST);
        inputPanel.add(taskField, BorderLayout.CENTER);

        centerPanel.add(inputPanel, BorderLayout.NORTH);

        // Task List
        listModel = new DefaultListModel<>();
        taskList = new JList<>(listModel);

        JScrollPane scrollPane = new JScrollPane(taskList);

        centerPanel.add(scrollPane, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel();

        addButton = new JButton("Add Task");
        updateButton = new JButton("Update Task");
        deleteButton = new JButton("Delete Task");

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Bottom Info Panel
        JPanel infoPanel = new JPanel(new GridLayout(2, 1));

        statusLabel = new JLabel("Status: Ready");
        countLabel = new JLabel("Total Tasks: 0");

        infoPanel.add(statusLabel);
        infoPanel.add(countLabel);

        add(infoPanel, BorderLayout.EAST);

        // Button Actions
        addButton.addActionListener(e -> addTask());

        updateButton.addActionListener(e -> updateTask());

        deleteButton.addActionListener(e -> deleteTask());
    }

    private void addTask() {

        String task = taskField.getText().trim();

        if (task.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a task.",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);

            return;
        }

        tasks.add(task);
        listModel.addElement(task);

        saveTasks();

        taskField.setText("");

        statusLabel.setText("Status: Task Added Successfully");

        updateTaskCount();
    }

    private void updateTask() {

        int selectedIndex = taskList.getSelectedIndex();

        if (selectedIndex == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Select a task to update.",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);

            return;
        }

        String newTask = taskField.getText().trim();

        if (newTask.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter updated task text.",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);

            return;
        }

        tasks.set(selectedIndex, newTask);
        listModel.set(selectedIndex, newTask);

        saveTasks();

        taskField.setText("");

        statusLabel.setText("Status: Task Updated Successfully");
    }

    private void deleteTask() {

        int selectedIndex = taskList.getSelectedIndex();

        if (selectedIndex == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Select a task to delete.",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);

            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete this task?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {

            tasks.remove(selectedIndex);
            listModel.remove(selectedIndex);

            saveTasks();

            statusLabel.setText("Status: Task Deleted Successfully");

            updateTaskCount();
        }
    }

    private void saveTasks() {

        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(FILE_NAME))) {

            for (String task : tasks) {

                writer.write(task);
                writer.newLine();
            }

        } catch (IOException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error saving tasks.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadTasks() {

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(FILE_NAME))) {

            String line;

            while ((line = reader.readLine()) != null) {

                tasks.add(line);
                listModel.addElement(line);
            }

        } catch (IOException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error loading tasks.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        updateTaskCount();
    }

    private void updateTaskCount() {

        countLabel.setText("Total Tasks: " + tasks.size());
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new TaskManagerPro());
    }
}