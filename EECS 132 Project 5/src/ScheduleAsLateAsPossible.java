import java.util.ListIterator;

/**
 * @author Tom Lerner
 * 
 * Tries to fit a job into a schedule as late as they can go without overlapping other jobs or violating job deadline or earliest start time
 *
 */
public class ScheduleAsLateAsPossible implements ScheduleMetric{

	/* If job fits in schedule, logs job as ScheduleSlot
	 * 
	 * @see ScheduleMetric#scheduleJob(Schedule, Job)
	 */
	public boolean scheduleJob(Schedule schedule, Job job) {

		int optimalStart = job.getDeadline() - job.getDuration();

		int otherScheduledStart = 0;
		int otherScheduledStop = 0;
		int previousScheduledStop = 0;

		if(optimalStart < job.getEarliestStart()) {
			return false;
		}
		else if(schedule.isEmpty()) {
			schedule.addToFront(new ScheduleSlot(job, optimalStart));
			return true;
		}
		ListIterator<ScheduleSlot> iter = (ListIterator<ScheduleSlot>)schedule.iterator();
		schedule.setNode(schedule.getBack());


		/*
		 * Loops through schedule, starting from back and moving toward front, looking for open spots to fit a job
		 * Only inserts a job if it will not overlap with other jobs
		 */
		while(iter.hasPrevious()) {

			otherScheduledStart = schedule.getNode().getElement().getStartTime();
			otherScheduledStop = otherScheduledStart + schedule.getNode().getElement().getJob().getDuration();
			previousScheduledStop = schedule.getNode().getElement().getJob().getDuration() + schedule.getNode().getElement().getStartTime();


			if(otherScheduledStop <= optimalStart) {
				iter.add(new ScheduleSlot(job, optimalStart));
				return true;
			}
			else if(otherScheduledStart - previousScheduledStop >= job.getDuration() && otherScheduledStart - job.getDuration() >= job.getEarliestStart()) {
				iter.add(new ScheduleSlot(job, otherScheduledStart - job.getDuration()));
				return true;
			}
			else if(schedule.getNode() == schedule.getFront() && otherScheduledStart - job.getDuration() >= job.getEarliestStart()) {
				iter.add(new ScheduleSlot(job, otherScheduledStart - job.getDuration()));
				return true;
			}
			iter.previous();
		}
		return false;
	}
}