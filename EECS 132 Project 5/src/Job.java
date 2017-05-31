import java.util.Comparator;

/**
 * @author Tom Lerner
 *
 * Contains comparators
 * This type is to be packaged in a JobScheduler and then a Schedule
 *
 */
public class Job implements Comparable<Job>{
	private int id;
	private int earliestStart;
	private int deadline;
	private int duration;
	private int profit;


	/**
	 * Constructor for Job
	 * 
	 * @param id
	 * @param earliestStart
	 * @param deadline
	 * @param duration
	 * @param profit
	 */
	public Job(int id, int earliestStart, int deadline, int duration, int profit) {
		this.id = id;
		this.earliestStart = earliestStart;
		this.deadline = deadline;
		this.duration = duration;
		this.profit = profit;
	}

	/**
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return
	 */
	public int getEarliestStart() {
		return earliestStart;
	}

	/**
	 * @param earliestStart
	 */
	public void setEarliestStart(int earliestStart) {
		this.earliestStart = earliestStart;
	}

	/**
	 * @return
	 */
	public int getDeadline() {
		return deadline;
	}

	/**
	 * @param deadline
	 */
	public void setDeadline(int deadline) {
		this.deadline = deadline;
	}

	/**
	 * @return
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * @param duration
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}

	/**
	 * @return
	 */
	public int getProfit() {
		return profit;
	}

	/**
	 * @param profit
	 */
	public void setProfit(int profit) {
		this.profit = profit;
	}

	/* 
	 * Checks if two jobs are equal
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(obj == null) {
			return false;
		}
		if(getClass() != obj.getClass()) {
			return false;
		}

		Job other = (Job) obj;

		if(deadline != other.deadline) {
			return false;
		}
		if(duration != other.duration) {
			return false;
		}
		if(earliestStart != other.earliestStart) {
			return false;
		}
		if(id != other.id) {
			return false;
		}
		if(profit != other.profit) {
			return false;
		}
		return true;
	}

	/* 
	 * Gives correct output for comparators
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Job arg0) {
		if(this.getEarliestStart() > arg0.getEarliestStart()) {
			return 1;
		}
		else if(this.getEarliestStart() < arg0.getEarliestStart()) {
			return -1;
		}
		else {
			return 0;
		}
	}

	/**
	 * Orders by earliest start time
	 * 
	 * @return
	 */
	public static Comparator<Job> getStartComparator() {
		return new Comparator<Job>() {

			@Override
			public int compare(Job a, Job b) {
				Integer c = a.getEarliestStart();
				Integer d = b.getEarliestStart();
				return c.compareTo(d);
			}

		};
	}


	/**
	 * Orders by profit
	 * 
	 * @return
	 */
	public static Comparator<Job> getProfitComparator() {
		return new Comparator<Job>() {

			@Override
			public int compare(Job a, Job b) {
				Integer c = a.getProfit();
				Integer d = b.getProfit();
				return c.compareTo(d);
			}

		};
	}
}