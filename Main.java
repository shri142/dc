import java.util.*;
class BullyAlgorithm {
 private int processId;
 private boolean coordinator;
 private List<Integer> processes;
 public BullyAlgorithm(int processId, List<Integer> processes) {
 this.processId = processId;
 this.coordinator = false;
 this.processes = processes;
 }
 public void election() {
 int maxId = Collections.max(processes);
 if (processId == maxId) {
 coordinator = true;
 System.out.println("Process " + processId + " became the coordinator.");
 announceElectionResult();
 } else {
 int higherProcessId = findHigherProcessId();
 if (higherProcessId != -1) {
 System.out.println("Process " + processId + " detected that process " + higherProcessId + " is alive and is the coordinator.");
 System.out.println("Process " + processId + " sends an Election message to process " + higherProcessId);
 } else {
 coordinator = true;
 System.out.println("Process " + processId + " becomes the coordinator as no higher process responded.");
 announceElectionResult();
 }
 }
 }
 private int findHigherProcessId() {
 int higherProcessId = -1;
 for (int id : processes) {
 if (id > processId && id > higherProcessId) {
 higherProcessId = id;
 }
 }
 return higherProcessId;
 }
 private void announceElectionResult() {
 System.out.println("Coordinator elected: Process " + processId);
 for (int id : processes) {
 if (id != processId) {
 System.out.println("Process " + id + " is notified that Process " + processId + " is the coordinator.");
 }
 }
 }
}
public class Main {
 public static void main(String[] args) {
 List<Integer> processes = new ArrayList<>(Arrays.asList(1, 2, 
3, 4, 5));
 int processId = 4; // Change this value to represent the process ID of the current process
 BullyAlgorithm bullyAlgorithm = new BullyAlgorithm(processId, processes);
 // Print dashed lines above the output
 printDashedLine();
 bullyAlgorithm.election();
 // Print dashed lines below the output
 printDashedLine();
 }
 private static void printDashedLine() {
 System.out.println("\n---------------------------------------\n");
 }
}
