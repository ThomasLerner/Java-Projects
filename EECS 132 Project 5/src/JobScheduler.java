import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
/* 
 * 
 * UNCOMMENT THIS IMPORT IF YOU WANT TO ENABLE READING FILES USING COMMAND LINE
 * THIS WAS COMMENTED OUT BECAUSE IT WOULD GIVE A WARNING FOR BEING UNUSED OTHERWISE
 * 
 * 
*/
//import java.util.Scanner;

/**
 * @author Tom Lerner
 * 
 * Main class
 * Creates instances of other classes to schedule jobs from a file
 *
 */
public class JobScheduler {
 Job[] randomJobs;

 /**
  * runs a Job array through a scheduleMetric to create a schedule
  * 
  * @param s
  * @param c
  * @param jobs
  * @return
  */
 public Schedule scheduleJobs(ScheduleMetric s, Comparator<Job> c, Job... jobs){
  randomJobs = jobs;
  Arrays.sort(jobs, c);
  Schedule schedule = new Schedule();
  for(Job x : jobs) {
   s.scheduleJob(schedule, x);
  }
  return schedule;
 }

 /**
  * Creates a file with random job data, based on the parameters given.  The job data will be 
  * sorted by earliest start time.
  * @param numJobs   the number of jobs to create
  * @param maxStart  the latest time at which a job may set as its earliest start time
  * @param maxDuration  the maximum time that a job can take to complete
  * @param maxLag   the greatest time between the earliest start time for a job and the latest time that a job must start to meet its deadline
  * @param maxProfit  the maximum amount of profit from a job
  * @return an array containing the random jobs
  */
 public Job[] createRandomJobs(int numJobs, int maxStart, int maxDuration, int maxLag, int maxProfit) {
  Job[] jobs = new Job[numJobs];

  // For each desired job, create 4 random numbers based on the parameters, use the numbers to create a job,
  // and store the job in an array.
  for (int i = 0; i < numJobs; i++) {
   int start = (int)(Math.random() * maxStart) + 1;
   int duration = (int)(Math.random() * maxDuration) + 1;
   int deadline = start + duration + (int)(Math.random() * (maxLag + 1));
   int profit = (int)(Math.random() * maxProfit) + 1;
   jobs[i] = new Job(i, start, deadline, duration, profit); 
  }

  // Sort the jobs by earliest start time
  // Arrays.sort(jobs, Job.getStartComparator());

  return jobs;
 }

 /**
  * Creates a file with job data.
  * @param fileName  the name of the file to store the job data.  The file must not exist.
  * @param jobs an array containing the jobs
  */
 public void createJobFile(String fileName, Job[] jobs) {
  // Check that the output file does not already exist
  File file = new File(fileName);
  if (file.exists()) {
   System.out.println("Error: file " + fileName + " already exists.");
   return;
  }

  try {
   BufferedWriter writer = new BufferedWriter(new FileWriter(file));

   // For each job in the array, write the job to the file as 5 numbers separated by spaces.
   // Place an each job on a new line.
   for (int i = 0; i < jobs.length; i++) {
    String s = i + " " + jobs[i].getEarliestStart() + " " + jobs[i].getDeadline() + " " + jobs[i].getDuration() + " " + jobs[i].getProfit();
    writer.write(s, 0, s.length());
    writer.newLine();
   }
   writer.flush();
   writer.close();
  }
  catch (IOException e) {
   System.out.println("Error writing to file " + fileName);
  }
 }

 /**
  * Main method
  * 
  * @param args
  */
 public static void main(String[] args) {
  JobScheduler scheduler = new JobScheduler();
  ScheduleAsLateAsPossible s = new ScheduleAsLateAsPossible();

  Job[] randomGeneratedJobs = scheduler.createRandomJobs(30, 3, 3, 50, 100);
  Schedule schedule = scheduler.scheduleJobs(s, Job.getStartComparator(), randomGeneratedJobs);
  for(ScheduleSlot x : schedule) {
   System.out.println(" ID: " + x.getJob().getId() + " Earliest: " + x.getJob().getEarliestStart() + " Deadline: " + x.getJob().getDeadline() + " Duration: " + x.getJob().getDuration() + " Profit: " + x.getJob().getProfit() + " Started: " + x.getStartTime() + " Ended: " + (x.getStartTime() + x.getJob().getDuration()));
  }


  /*
   * 
   * IF YOU WANT TO RUN USING COMMAND LINE, UNCOMMENT THESE LINES AND COMMENT OUT THE ABOVE LINES
   * 
   */
  //  JobScheduler scheduler = new JobScheduler();
  //  ScheduleAsLateAsPossible s = new ScheduleAsLateAsPossible();
  //  
  //  Scanner ss = new Scanner(args.toString());
  //  for(int x = 0; ss.hasNextInt(); x++) {
  //   scheduler.randomJobs[x] = new Job(ss.nextInt(), ss.nextInt(), ss.nextInt(), ss.nextInt(), ss.nextInt());
  //  }
  //  ss.close();
  //  Schedule schedule = scheduler.scheduleJobs(s, Job.getStartComparator(), scheduler.randomJobs);
  //  for(ScheduleSlot x : schedule) {
  //   System.out.println(" ID: " + x.getJob().getId() + " Earliest: " + x.getJob().getEarliestStart() + " Deadline: " + x.getJob().getDeadline() + " Duration: " + x.getJob().getDuration() + " Profit: " + x.getJob().getProfit() + " Started: " + x.getStartTime() + " Ended: " + (x.getStartTime() + x.getJob().getDuration()));
  //  }


 }

}