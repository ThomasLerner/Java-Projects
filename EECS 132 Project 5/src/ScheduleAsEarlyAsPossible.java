import java.util.ListIterator;

/**
 * @author Tom Lerner
 * 
 * Never got around to this
 *
 */

public class ScheduleAsEarlyAsPossible implements ScheduleMetric {

	/* If job fits in schedule, logs job as ScheduleSlot
	 * 
	 * @see ScheduleMetric#scheduleJob(Schedule, Job)
	 */
	public boolean scheduleJob(Schedule schedule, Job job) {

		int optimalStop = job.getEarliestStart() + job.getDuration();

		int otherScheduledStart = 0;
		int otherScheduledStop = 0;
		int nextScheduledStart = 0;

		if(optimalStop > job.getDeadline()) {
			return false;
		}
		else if(schedule.isEmpty()) {
			schedule.addToFront(new ScheduleSlot(job, job.getEarliestStart()));
			return true;
		}
		ListIterator<ScheduleSlot> iter = (ListIterator<ScheduleSlot>)schedule.iterator();
		schedule.setNode(schedule.getFront());

		/*
		 * Loops through schedule, starting from back and moving toward front, looking for open spots to fit a job
		 * Only inserts a job if it will not overlap with other jobs
		 */
		while(schedule.getNode() != schedule.getBack()) {

			otherScheduledStart = schedule.getNode().getElement().getStartTime();
			otherScheduledStop = otherScheduledStart + schedule.getNode().getElement().getJob().getDuration();
			nextScheduledStart = schedule.getNode().getNext().getElement().getStartTime();


			if(otherScheduledStart >= optimalStop) {
				iter.add(new ScheduleSlot(job, job.getEarliestStart()));
				return true;
			}
			else if(nextScheduledStart - otherScheduledStop >= job.getDuration() && otherScheduledStop + job.getDuration() <= job.getDeadline()) {
				iter.add(new ScheduleSlot(job, otherScheduledStop));
				return true;
			}
			iter.previous();
		}
		return false;
	}
}

