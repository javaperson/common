package com.github.javaperson.common;

public interface Task extends Runnable
{
	/**
	 * @return returns the unique task id of the task. For future
	 *         implementations, this value has to be unique across multiple
	 *         server nodes.
	 */
	Object getId();

	/**
	 * @param id
	 *            Set the unique task id.
	 */
	void setId(Object id);
}
