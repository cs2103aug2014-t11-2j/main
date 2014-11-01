package logic;

import java.util.ListIterator;

import logic.Assignment.AssignmentType;

/*
 * Comparison for Time, Date and ID.
 * returns boolean 
 * or
 * -1 (A < B), 0 (A = B), 1 (A > B)
 */
public class Comparator {

	private static final int SMALLER = -1;
	private static final int SAME = 0;
	private static final int LARGER = 1;

	// compares id with nextId, return true if idA is bigger else return false
	// the format is 250920140001
	protected static boolean serialNumberComparator(String idA, String idB) {

		int checkDate = dateComparator(idA.substring(0, 8), idB.substring(0, 8));
		boolean serialCheck = false;

		if(Id._IDFormatValid(idA) && Id._IDFormatValid(idB)) {
			if (checkDate == SAME) {

				idA = Id.removeFrontZero(idA.substring(8));

				idB = Id.removeFrontZero(idB.substring(8));

				if (Integer.parseInt(idA) > Integer.parseInt(idB)) {
					serialCheck = true;
				}
			} else if (checkDate == LARGER) {
				serialCheck = true;
			}
		}
		return serialCheck;
	}

	protected static int dateComparator(String dateA, String dateB) {

		String yearA = dateA.trim().substring(4, 8);
		String yearB = dateB.trim().substring(4, 8);

		String monthA = dateA.trim().substring(2, 4);
		String monthB = dateB.trim().substring(2, 4);

		String dayA = dateA.trim().substring(0, 2);
		String dayB = dateB.trim().substring(0, 2);

		yearA = Id.removeFrontZero(yearA);
		yearB = Id.removeFrontZero(yearB);

		monthA = Id.removeFrontZero(monthA);
		monthB = Id.removeFrontZero(monthB);

		dayA = Id.removeFrontZero(dayA);
		dayB = Id.removeFrontZero(dayB);

		if (dateA.equals(dateB)) {
			return SAME;
		} else if (Integer.parseInt(yearA) > Integer.parseInt(yearB)) {
			return LARGER;
		} else if (Integer.parseInt(yearA) < Integer.parseInt(yearB)) {
			return SMALLER;
		} else if (Integer.parseInt(monthA) > Integer.parseInt(monthB)) {
			return LARGER;
		} else if (Integer.parseInt(monthA) < Integer.parseInt(monthB)) {
			return SMALLER;
		} else if (Integer.parseInt(dayA) > Integer.parseInt(dayB)) {
			return LARGER;
		}
		return SMALLER;
	}

	protected static int timeComparator(String timeA, String timeB) {

		String hourA = timeA.trim().substring(0, 2);
		String hourB = timeB.trim().substring(0, 2);

		String minA = timeA.trim().substring(2, 4);
		String minB = timeB.trim().substring(2, 4);

		if(hourA.equals(hourB)) {
			if(!minA.equals(minB)) {

				if(minA.equals("00") && !minB.equals("00")) {
					return SMALLER;
				} else if(!minA.equals("00") && minB.equals("00")) {
					return LARGER;
				} else if(!minA.equals("00") && !minB.equals("00")) {
					minA = Id.removeFrontZero(minA);
					minB = Id.removeFrontZero(minB);

					if(Integer.parseInt(minA) > Integer.parseInt(minB)) {
						return LARGER;
					} else {
						return SMALLER;
					}
				}
			} else {
				return SAME;
			}
		} else if(hourA.equals("00") && !hourB.equals("00")) {
			return SMALLER;
		} else if(!hourA.equals("00") && hourB.equals("00")) {
			return LARGER;
		} else if(!hourA.equals("00") && !hourB.equals("00")) {
			hourA = Id.removeFrontZero(hourA);
			hourB = Id.removeFrontZero(hourB);

			if(Integer.parseInt(hourA) > Integer.parseInt(hourB)) {
				return LARGER;
			} else {
				return SMALLER;
			}
		}
		return SAME;
	}

	protected static int addToBigBuffer(Appointment newAppointment) {

		Appointment nextAppointmentInBuffer = new Appointment();
		Appointment oldAppointmentInBuffer = new Appointment();
		Task nextTaskInBuffer = new Task();
		Task oldTaskInBuffer = new Task();
		int count = 0;
		boolean previousIsTask = false;
		boolean previousIsAppointment = false;

		ListIterator<Assignment> buffer = InternalStorage.getBuffer().listIterator();

		while(buffer.hasNext()) {
			Assignment assignment = buffer.next();

			if(count == 0) {
				if (assignment.getAssignType().equals(AssignmentType.APPOINTMENT)) {

					nextAppointmentInBuffer = ((Appointment) assignment);

					previousIsAppointment = true;

					if (dateComparator(newAppointment.getEndDate(),
							nextAppointmentInBuffer.getEndDate()) == LARGER) {
						break;
					} else if(dateComparator(newAppointment.getEndDate(),
							nextAppointmentInBuffer.getEndDate()) == SAME) {
						if(timeComparator(newAppointment.getEndTime(),
								nextAppointmentInBuffer.getEndTime()) == LARGER) {
							break;
						}
					}
				} else if(assignment.getAssignType().equals(AssignmentType.TASK)) {

					nextTaskInBuffer = ((Task) assignment);

					previousIsTask = true;

					if (dateComparator(newAppointment.getEndDate(),
							nextTaskInBuffer.getEndDate()) == SMALLER) {
						break;
					} else if(dateComparator(newAppointment.getEndDate(),
							nextTaskInBuffer.getEndDate()) == SAME) {
						if(timeComparator(newAppointment.getEndTime(),
								nextTaskInBuffer.getEndTime()) == LARGER) {
							break;
						}
					}
				}
			} else {
				if (assignment.getAssignType().equals(AssignmentType.APPOINTMENT)) {

					nextAppointmentInBuffer = ((Appointment) assignment);

					if(previousIsAppointment) {
						if (dateComparator(newAppointment.getEndDate(),
								oldAppointmentInBuffer.getEndDate()) == LARGER &&
								dateComparator(newAppointment.getEndDate(),
										nextAppointmentInBuffer.getEndDate()) == SMALLER) {
							break;
						} else if(dateComparator(newAppointment.getEndDate(),
								nextAppointmentInBuffer.getEndDate()) == SAME && 
								dateComparator(newAppointment.getEndDate(),
										oldAppointmentInBuffer.getEndDate()) == SAME) {

							if(timeComparator(newAppointment.getEndTime(),
									nextAppointmentInBuffer.getEndTime()) == SMALLER &&
									timeComparator(newAppointment.getEndTime(),
											oldAppointmentInBuffer.getEndTime()) == LARGER) {
								break;
							}
						}
					} else if(previousIsTask) {
						if (dateComparator(newAppointment.getEndDate(),
								oldTaskInBuffer.getEndDate()) == LARGER &&
								dateComparator(newAppointment.getEndDate(),
										nextAppointmentInBuffer.getEndDate()) == SMALLER) {
							break;
						} else if(dateComparator(newAppointment.getEndDate(),
								oldTaskInBuffer.getEndDate()) == SAME &&
								dateComparator(newAppointment.getEndDate(),
										nextAppointmentInBuffer.getEndDate()) == SMALLER) {
							break;
						} else if(dateComparator(newAppointment.getEndDate(),
								oldTaskInBuffer.getEndDate()) == SAME &&
								dateComparator(newAppointment.getEndDate(),
										nextAppointmentInBuffer.getEndDate()) == SAME) {

							if(timeComparator(newAppointment.getEndTime(),
									nextAppointmentInBuffer.getEndTime()) == SMALLER &&
									timeComparator(newAppointment.getEndTime(),
											oldTaskInBuffer.getEndTime()) == LARGER) {
								break;
							} else if(timeComparator(newAppointment.getEndTime(),
									nextAppointmentInBuffer.getEndTime()) == SMALLER &&
									timeComparator(newAppointment.getEndTime(),
											oldTaskInBuffer.getEndTime()) == SAME) {
								break;
							}
						}
					}
				} else if (assignment.getAssignType().equals(AssignmentType.TASK)) {

					nextTaskInBuffer = ((Task) assignment);

					if(previousIsAppointment) {
						if (dateComparator(newAppointment.getEndDate(),
								oldAppointmentInBuffer.getEndDate()) == LARGER &&
								dateComparator(newAppointment.getEndDate(),
										nextTaskInBuffer.getEndDate()) == SMALLER) {
							break;
						} else if(dateComparator(newAppointment.getEndDate(),
								nextTaskInBuffer.getEndDate()) == SAME && 
								dateComparator(newAppointment.getEndDate(),
										oldAppointmentInBuffer.getEndDate()) == SAME) {

							if(timeComparator(newAppointment.getEndTime(),
									nextTaskInBuffer.getEndTime()) == SMALLER &&
									timeComparator(newAppointment.getEndTime(),
											oldAppointmentInBuffer.getEndTime()) == LARGER) {
								break;
							}
						}
					} else if(previousIsTask) {
						if (dateComparator(newAppointment.getEndDate(),
								oldTaskInBuffer.getEndDate()) == LARGER &&
								dateComparator(newAppointment.getEndDate(),
										nextTaskInBuffer.getEndDate()) == SMALLER) {
							break;
						} else if(dateComparator(newAppointment.getEndDate(),
								oldTaskInBuffer.getEndDate()) == SAME &&
								dateComparator(newAppointment.getEndDate(),
										nextTaskInBuffer.getEndDate()) == SMALLER) {
							break;
						} else if(dateComparator(newAppointment.getEndDate(),
								oldTaskInBuffer.getEndDate()) == SAME &&
								dateComparator(newAppointment.getEndDate(),
										nextTaskInBuffer.getEndDate()) == SAME) {

							if(timeComparator(newAppointment.getEndTime(),
									nextTaskInBuffer.getEndTime()) == SMALLER &&
									timeComparator(newAppointment.getEndTime(),
											oldTaskInBuffer.getEndTime()) == LARGER) {
								break;
							} else if(timeComparator(newAppointment.getEndTime(),
									nextTaskInBuffer.getEndTime()) == SMALLER &&
									timeComparator(newAppointment.getEndTime(),
											oldTaskInBuffer.getEndTime()) == SAME) {
								break;
							}
						}
					}
				}
			}
			oldAppointmentInBuffer = nextAppointmentInBuffer;
			oldTaskInBuffer = nextTaskInBuffer;
			count++;
		}
		return count;
	}

	protected static int addTaskToBigBuffer(Task newTask) {

		Appointment nextAppointmentInBuffer = new Appointment();
		Appointment oldAppointmentInBuffer = new Appointment();
		Task nextTaskInBuffer = new Task();
		Task oldTaskInBuffer = new Task();
		int count = 0;
		boolean previousIsTask = false;
		boolean previousIsAppointment = false;

		ListIterator<Assignment> buffer = InternalStorage.getBuffer().listIterator();

		while(buffer.hasNext()) {
			Assignment assignment = buffer.next();

			if(count == 0 || (!previousIsTask && !previousIsAppointment)) {
				if (assignment.getAssignType().equals(AssignmentType.APPOINTMENT)) {

					nextAppointmentInBuffer = ((Appointment) assignment);

					previousIsAppointment = true;

					if (dateComparator(newTask.getEndDate(),
							nextAppointmentInBuffer.getEndDate()) == SMALLER) {
						break;
					} else if(dateComparator(newTask.getEndDate(),
							nextAppointmentInBuffer.getEndDate()) == SAME) {
						if(timeComparator(newTask.getEndTime(),
								nextAppointmentInBuffer.getEndTime()) == SMALLER) {
							break;
						}
					}
				} else if(assignment.getAssignType().equals(AssignmentType.TASK)) {

					nextTaskInBuffer = ((Task) assignment);

					previousIsTask = true;

					if (dateComparator(newTask.getEndDate(),
							nextTaskInBuffer.getEndDate()) == LARGER) {
						break;
					} else if(dateComparator(newTask.getEndDate(),
							nextTaskInBuffer.getEndDate()) == SAME) {
						if(timeComparator(newTask.getEndTime(),
								nextTaskInBuffer.getEndTime()) == SMALLER) {
							break;
						}
					}
				}
			} else {
				if (assignment.getAssignType().equals(AssignmentType.APPOINTMENT)) {

					nextAppointmentInBuffer = ((Appointment) assignment);

					if(previousIsAppointment) {
						if (dateComparator(newTask.getEndDate(),
								oldAppointmentInBuffer.getEndDate()) == LARGER &&
								dateComparator(newTask.getEndDate(),
										nextAppointmentInBuffer.getEndDate()) == SMALLER) {
							break;
						} else if(dateComparator(newTask.getEndDate(),
								nextAppointmentInBuffer.getEndDate()) == SAME && 
								dateComparator(newTask.getEndDate(),
										oldAppointmentInBuffer.getEndDate()) == SAME) {

							if(timeComparator(newTask.getEndTime(),
									nextAppointmentInBuffer.getEndTime()) == SMALLER &&
									timeComparator(newTask.getEndTime(),
											oldAppointmentInBuffer.getEndTime()) == LARGER) {
								break;
							}
						}
					} else if(previousIsTask) {

						if (dateComparator(newTask.getEndDate(),
								oldTaskInBuffer.getEndDate()) == LARGER &&
								dateComparator(newTask.getEndDate(),
										nextAppointmentInBuffer.getEndDate()) == SMALLER) {
							break;
						} else if(dateComparator(newTask.getEndDate(),
								oldTaskInBuffer.getEndDate()) == SAME &&
								dateComparator(newTask.getEndDate(),
										nextAppointmentInBuffer.getEndDate()) == SMALLER) {
							break;
						} else if(dateComparator(newTask.getEndDate(),
								oldTaskInBuffer.getEndDate()) == SAME &&
								dateComparator(newTask.getEndDate(),
										nextAppointmentInBuffer.getEndDate()) == SAME) {

							if(timeComparator(newTask.getEndTime(),
									nextAppointmentInBuffer.getEndTime()) == SMALLER &&
									timeComparator(newTask.getEndTime(),
											oldTaskInBuffer.getEndTime()) == LARGER) {
								break;
							} else if(timeComparator(newTask.getEndTime(),
									nextAppointmentInBuffer.getEndTime()) == SMALLER &&
									timeComparator(newTask.getEndTime(),
											oldTaskInBuffer.getEndTime()) == SAME) {
								break;
							}
						}
					}
				} else if (assignment.getAssignType().equals(AssignmentType.TASK)) {

					nextTaskInBuffer = ((Task) assignment);

					if(previousIsAppointment) {
						if (dateComparator(newTask.getEndDate(),
								oldAppointmentInBuffer.getEndDate()) == LARGER &&
								dateComparator(newTask.getEndDate(),
										nextTaskInBuffer.getEndDate()) == SMALLER) {
							break;
						} else if(dateComparator(newTask.getEndDate(),
								nextTaskInBuffer.getEndDate()) == SAME && 
								dateComparator(newTask.getEndDate(),
										oldAppointmentInBuffer.getEndDate()) == SAME) {

							if(timeComparator(newTask.getEndTime(),
									nextTaskInBuffer.getEndTime()) == SMALLER &&
									timeComparator(newTask.getEndTime(),
											oldAppointmentInBuffer.getEndTime()) == LARGER) {
								break;
							}
						}
					} else if(previousIsTask) {
						if(dateComparator(newTask.getEndDate(),
								nextTaskInBuffer.getEndDate()) == SAME) {
							break;
						}
						if (dateComparator(newTask.getEndDate(),
								oldTaskInBuffer.getEndDate()) == LARGER &&
								dateComparator(newTask.getEndDate(),
										nextTaskInBuffer.getEndDate()) == SMALLER) {
							break;
						} else if(dateComparator(newTask.getEndDate(),
								oldTaskInBuffer.getEndDate()) == SAME &&
								dateComparator(newTask.getEndDate(),
										nextTaskInBuffer.getEndDate()) == SMALLER) {
							break;
						} else if(dateComparator(newTask.getEndDate(),
								oldTaskInBuffer.getEndDate()) == SAME &&
								dateComparator(newTask.getEndDate(),
										nextTaskInBuffer.getEndDate()) == SAME) {

							if(timeComparator(newTask.getEndTime(),
									nextTaskInBuffer.getEndTime()) == SMALLER &&
									timeComparator(newTask.getEndTime(),
											oldTaskInBuffer.getEndTime()) == LARGER) {
								break;
							} else if(timeComparator(newTask.getEndTime(),
									nextTaskInBuffer.getEndTime()) == SMALLER &&
									timeComparator(newTask.getEndTime(),
											oldTaskInBuffer.getEndTime()) == SAME) {
								break;
							}
						}
					}
				}
			}
			oldAppointmentInBuffer = nextAppointmentInBuffer;
			oldTaskInBuffer = nextTaskInBuffer;
			count++;
		}
		return count;
	}

	protected static boolean isClashing(Appointment newAppointment) {

		boolean isClashing = false;
		ListIterator<Assignment> buffer = InternalStorage.getBuffer().listIterator();
		Appointment checkAppointment = new Appointment();

		while(buffer.hasNext()) {
			Assignment assignment = buffer.next();
			if(!assignment.getIsDone()) {
				if(assignment.getAssignType().equals(AssignmentType.APPOINTMENT)) {
					checkAppointment = ((Appointment) assignment); 

					if(dateComparator(newAppointment.getEndDate(), 
							checkAppointment.getEndDate()) == SAME &&
							Comparator.dateComparator(newAppointment.getStartDate(), 
									checkAppointment.getStartDate()) == SAME) {

						isClashing = isClashingTime(newAppointment, checkAppointment);
					} else {
						isClashing = isClashingDate(newAppointment, checkAppointment);
					}
					if(isClashing) {
						break;
					}
				}
			}
		}
		return isClashing;
	}

	private static boolean isClashingDate(Appointment newAppointment, Appointment checkAppointment) {

		boolean isClashing = true;

		if(dateComparator(checkAppointment.getStartDate(), newAppointment.getStartDate()) == LARGER &&
				dateComparator(checkAppointment.getStartDate(), newAppointment.getEndDate()) == SAME) {
			isClashing = false;
		} else if(dateComparator(checkAppointment.getStartDate(), newAppointment.getStartDate()) == LARGER &&
				dateComparator(checkAppointment.getStartDate(), newAppointment.getEndDate()) == LARGER) {
			isClashing = false;
		} else if(dateComparator(checkAppointment.getEndDate(), newAppointment.getEndDate()) == SMALLER &&
				dateComparator(checkAppointment.getEndDate(), newAppointment.getStartDate()) == SMALLER) {
			isClashing = false;
		} else if(dateComparator(checkAppointment.getEndDate(), newAppointment.getEndDate()) == SMALLER &&
				dateComparator(checkAppointment.getEndDate(), newAppointment.getStartDate()) == SAME) {
			isClashing = false;
		} 
		return isClashing;
	}

	private static boolean isClashingTime(Appointment newAppointment, Appointment checkAppointment) {

		boolean isClashing = true;

		if(timeComparator(checkAppointment.getStartTime(), newAppointment.getStartTime()) == LARGER &&
				timeComparator(checkAppointment.getStartTime(), newAppointment.getEndTime()) == SAME) {
			isClashing = false;
		} else if(timeComparator(checkAppointment.getStartTime(), newAppointment.getStartTime()) == LARGER ||
				timeComparator(checkAppointment.getStartTime(), newAppointment.getEndTime()) == LARGER) {
			isClashing = false;
		} else if(timeComparator(checkAppointment.getEndTime(), newAppointment.getEndTime()) == SMALLER &&
				timeComparator(checkAppointment.getEndTime(), newAppointment.getStartTime()) == SMALLER) {
			isClashing = false;
		} else if(timeComparator(checkAppointment.getEndTime(), newAppointment.getEndTime()) == SMALLER &&
				timeComparator(checkAppointment.getEndTime(), newAppointment.getStartTime()) == SAME) {
			isClashing = false;
		} 
		return isClashing;
	}
}
