package org.cytoscape.rest.task;

import org.cytoscape.work.AbstractTaskManager;
import org.cytoscape.work.Task;
import org.cytoscape.work.TaskFactory;
import org.cytoscape.work.TaskIterator;
import org.cytoscape.work.TaskMonitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RestTaskManager extends AbstractTaskManager {
	
	private static final Logger logger = LoggerFactory.getLogger(RestTaskManager.class);


	public RestTaskManager() {
		super(null);
	}

	@Override
	public Object getConfiguration(TaskFactory factory, Object tunableContext) {
		return null;
	}

	@Override
	public void execute(final TaskIterator iterator) {
		final TaskMonitor tM = new HeadlessTaskMonitor();
		
		// Run in the same thread.
		while(iterator.hasNext()) {
			final Task task = iterator.next();
			try {
				task.run(tM);
			} catch (Exception e) {
				logger.error("Could not finish task.", e);
			}
		}
		
	}

}
