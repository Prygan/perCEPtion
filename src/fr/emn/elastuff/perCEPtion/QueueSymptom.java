package fr.emn.elastuff.perCEPtion;

import java.util.ArrayList;
import java.util.List;

public class QueueSymptom {
	private static QueueSymptom instance = new QueueSymptom();
	private List<Symptom> queue;

	private QueueSymptom() {
		this.queue = new ArrayList<Symptom>();
	}

	public static QueueSymptom getInstance() {
		return instance;
	}

	public QueueSymptom(ArrayList<Symptom> queue) {
		this.queue = queue;
	}

	/**
	 * AddSymptom Purge the List and the added Symptom will be sort.
	 * 
	 * @param s
	 *            The symptom to add
	 */
	public void addSymptom(Symptom s) {
		// Remove all the symptom which are expired
		this.purgeQueue();
		// To know if the symptom is add when it's compared to others in the
		// list
		boolean add = false;
		for (Symptom symptom : this.queue) {
			if (s.compareTo(symptom) > 0) {
				this.queue.add(this.queue.indexOf(symptom), s);
				add = true;
				break;
			}
		}
		// If its priority is the lowest or egal to all the others symptoms, add
		// at the end
		if (!add) {
			this.queue.add(s);
		}
	}

	/**
	 * Method to pull the symptom with the highest priority (the first in the
	 * List)
	 * 
	 * @return First Symptom in a sorted List null if not symptom in the queue
	 */
	public Symptom pullSymptom() {
		this.purgeQueue();
		Symptom s = null;
		if (this.queue.size() > 0) {
			s = this.queue.get(0);
			this.queue.remove(0);
		}
		return s;

	}

	/**
	 * Remove all the symptom which are expired.
	 */
	public void purgeQueue() {
		for (Symptom s : this.queue)
			if (s.isExpired())
				this.queue.remove(s);
	}

	public List<Symptom> getQueue() {
		return queue;
	}

	public void setQueue(List<Symptom> queue) {
		this.queue = queue;
	}

	public String toString() {
		String res = "QueueSymptom { ";
		for (Symptom s : queue) {
			res += s.toString() + " ; ";
		}
		return res += " }";
	}
}
