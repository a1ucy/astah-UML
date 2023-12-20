import java.util.*;

public class SportConcussionAssessment {

    private static List<Map<String, Integer>> gameData = new ArrayList<>();
    private static final String[] symptoms = {
        "Headache", "Pressure in head", "Neck pain", "Nausea or vomiting", "Dizziness", 
        "Blurred vision", "Balance problems", "Sensitivity to light", "Sensitivity to noise", 
        "Feeling slowed down", "Feeling like “in a fog”", "“Don’t feel right”", 
        "Difficulty concentrating", "Difficulty remembering", "Fatigue or low energy", 
        "Confusion", "Drowsiness", "Trouble falling asleep", "More emotional", 
        "Irritability", "Sadness", "Nervous or anxious"
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Sports Concussion Assessment System");
            System.out.println("1. Symptom Entry");
            System.out.println("2. Display Symptoms Summary");
            System.out.println("3. Am I at Risk?");
            System.out.println("4. Exit");
            System.out.println("------------------------------");
            System.out.print("Select an option: ");
            

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    enterSymptoms(scanner);
                    break;
                case 2:
                    displaySummary();
                    break;
                case 3:
                    displayRiskCondition();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option, please enter a valid option.");
                    System.out.println("------------------------------");
            }
        }
    }

    private static void enterSymptoms(Scanner scanner) {
        if (gameData.size() == 5) {
            gameData.remove(0);
        }
    
        Map<String, Integer> symptomData = new HashMap<>();
        for (String symptom : symptoms) {
            int severity;
            do {
                System.out.print("Enter severity (0-6) for " + symptom + ": ");
                while (!scanner.hasNextInt()) { // Check if the input is an integer
                    System.out.println("Invalid input, please enter again.");
                    scanner.next(); // Discard the invalid input
                }
                severity = scanner.nextInt();
                if (severity < 0 || severity > 6) {
                    System.out.println("Invalid input, please enter again.");
                }
            } while (severity < 0 || severity > 6); // Continue until a valid severity is entered
            symptomData.put(symptom, severity);
        }
    
        gameData.add(symptomData);
        System.out.println("Symptoms entered successfully.");
        System.out.println("------------------------------");
    }

    private static void displaySummary() {
        for (int i = 0; i < gameData.size(); i++) {
            Map<String, Integer> symptomData = gameData.get(i);
            int totalSymptoms = (int) symptomData.values().stream().filter(v -> v > 0).count();
            int severityScore = symptomData.values().stream().mapToInt(Integer::intValue).sum();
            
            String rating = "Not enough data.";
            if (i > 0) {
                Map<String, Integer> prevData = gameData.get(i - 1);
                int symptomDifference = calculateDifference(symptomData, prevData);
                int prevSeverity = prevData.values().stream().mapToInt(Integer::intValue).sum();
                
                if (symptomDifference < 3 && severityScore < 10) {
                    rating = "No difference";
                } else if (symptomDifference < 3 && severityScore >= 10) {
                    rating = "Unsure";
                } else if (symptomDifference >= 3 || severityScore >= 15) {
                    rating = "Very different";
                }
            }

            System.out.println("Game " + (i + 1) + " Summary:");
            System.out.println("Total number of symptoms: " + totalSymptoms);
            System.out.println("Symptom severity score: " + severityScore);
            System.out.println("Overall rating: " + rating);
            System.out.println("------------------------------");
        }
    }

    private static int calculateDifference(Map<String, Integer> current, Map<String, Integer> prev) {
        int difference = 0;
        for (String symptom : symptoms) {
            difference += Math.abs(current.get(symptom)- prev.get(symptom));
        }
        return difference;
    }

    private static void displayRiskCondition() {
        if (gameData.size() < 2) {
            System.out.println("Insufficient data for risk assessment. At least 2 games' data is required.");
            System.out.println("------------------------------");
            return;
        }

        Map<String, Integer> latestData = gameData.get(gameData.size() - 1);
        int latestSeverityScore = latestData.values().stream().mapToInt(Integer::intValue).sum();

        Map<String, Integer> prevData = gameData.get(gameData.size() - 2);
        int symptomDifference = calculateDifference(latestData, prevData);
        
        String rating;
        if (symptomDifference < 3 && latestSeverityScore < 10) {
            rating = "No difference";
        } else if (symptomDifference < 3 && latestSeverityScore >= 10) {
            rating = "Unsure";
        } else if (symptomDifference >= 3 || latestSeverityScore >= 15) {
            rating = "Very different";
        } else {
            rating = "Unknown";
        }
        System.out.println("------------------------------");

        switch (rating) {
            case "No difference":
                System.out.println("\u001B[42m" + "No difference" + "\u001B[0m");  // Green text
                System.out.println("------------------------------");
                break;
            case "Unsure":
                System.out.println("\u001B[43m" + "Unsure" + "\u001B[0m");  // Yellow text
                System.out.println("------------------------------");
                break;
            case "Very different":
                System.out.println("\u001B[41m" + "Very different" + "\u001B[0m");  // Red text
                System.out.println("------------------------------");
                break;
            default:
                System.out.println("Unable to determine risk condition.");
                System.out.println("------------------------------");
        }
    }
}
