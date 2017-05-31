/**
 * @author Tom Lerner
 * 
 * Stores a Job and its start time for storage in a schedule
 *
 */
public class ScheduleSlot {
	private Job job;
	private int startTime;

	/**
	 * Constructor
	 * 
	 * @param job
	 * @param startTime
	 */
	public ScheduleSlot(Job job, int startTime) {
		this.job = job;
		this.startTime = startTime;
	}

	/**
	 * @return
	 */
	public Job getJob() {
		return job;
	}

	/**
	 * @return
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime
	 */
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}
}