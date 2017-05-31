/**
 * @author Tom Lerner
 * 
 * Interface for ScheduleAsLateAsPossible and ScheduleAsEarlyAsPossible
 *
 */
public interface ScheduleMetric {
	public boolean scheduleJob(Schedule schedule, Job job);

}
