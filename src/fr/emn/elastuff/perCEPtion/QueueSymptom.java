package fr.emn.elastuff.perCEPtion;

import java.util.PriorityQueue;

public class QueueSymptom extends PriorityQueue<Symptom> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5944950698188907872L;
	
	private static QueueSymptom instance = new QueueSymptom();

	private QueueSymptom() {
	}

	public static QueueSymptom getInstance() {
		return instance;
	}

	/**
	 * AddSymptom Purge the List and the added Symptom will be sort.
	 * 
	 * @param s
	 *            The symptom to add
	 */
	public boolean addSymptom(Symptom s) {
		// Remove all the symptom which are expired
		this.purgeQueue();
		return super.add(s);
	}

	/**
	 * Purge the QueueSymptom and poll the first Symptom in the queue
	 * 
	 * @return First Symptom in a sorted queue
	 */
	public Symptom pullSymptom() {
		this.purgeQueue();
		return super.poll();

	}

	/**
	 * Remove all the symptom which are expired.
	 */
	public void purgeQueue() {
		for (Symptom s : this) {
			if (s.isExpired())
				this.remove(s);
		}
	}

	public String toString() {
		String res = "QueueSymptom { ";
		for (Symptom s : this) {
			res += s.toString() + " ; ";
		}
		return res += " }";
	}
}
