/**
 * @author Tom Lerner
 * 
 * Allows for processing and evaluation of subjobs
 *
 */
public class CompoundJob extends Job{

	/**
	 * Stores the Job array for a getter method
	 */
	Job[] subJobs;

	/**
	 * Constructor
	 * 
	 * @param profit
	 * @param subJobs
	 */
	public CompoundJob(int profit, Job... subJobs) {
		super(subJobs[0].getId(), subJobs[0].getEarliestStart(), subJobs[subJobs.length - 1].getDeadline(), durationAdder(subJobs),  0);
		this.subJobs = subJobs;

		for(int x = 0; x < subJobs.length; x++) {
			int othersDuration = 0;
			for(int j = x + 1; j < subJobs.length; j++) {
				othersDuration += subJobs[j].getDuration();
			}
			subJobs[x].setDeadline(subJobs[subJobs.length - 1].getDeadline() - othersDuration);
		}

		for(int x = 0; x < subJobs.length; x++) {
			int othersDuration = 0;
			for(int j = subJobs.length - (x + 1); j > 0; j--) {
				othersDuration += subJobs[j].getDuration();
			}
			subJobs[x].setEarliestStart(this.getEarliestStart() + othersDuration);
		}
	}

	/**
	 * Helper method
	 * Adds durations of subjobs
	 * 
	 * @param subJobs
	 * @return
	 */
	private static int durationAdder(Job[] subJobs) {
		int durationSum = 0;
		for(int x = 0; x < subJobs.length; x++) {
			durationSum += subJobs[x].getDuration();
		}
		return durationSum;
	}

	/**
	 * @return
	 */
	public Job[] getSubJobs() {
		return subJobs;
	}

}
